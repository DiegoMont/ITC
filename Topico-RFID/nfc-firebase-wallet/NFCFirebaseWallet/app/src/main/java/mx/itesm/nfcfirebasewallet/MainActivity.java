package mx.itesm.nfcfirebasewallet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private final String KEY_A = "FFFFFFFFFFFF";
    private final String KEY_B = "FFFFFFFFFFFF";
    private final int MIFARE_BLOCK = 5;

    Context context;

    private WebView webView;

    NfcAdapter nfcAdapter;
    PendingIntent nfcIntent;
    IntentFilter[] tagFilters;
    String[][] techList;
    private char listeningState;
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        //listeningState = 'f';
        listeningState = 'r';

        webView = (WebView) findViewById(R.id.interfaz_web);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, "Android");

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if(nfcAdapter == null){
            webView.loadUrl("file:///android_asset/no_nfc.html");
            return;
        }

        nfcIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter mifareDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);

        try {
            mifareDetected.addDataType("application/mx.itesm.nfcfirebasewallet");
        } catch(IntentFilter.MalformedMimeTypeException e){
            throw new RuntimeException("No se pudo añadir un MIME", e);
        }

        tagFilters = new IntentFilter[] {mifareDetected};

        techList = new String[][] {new String[] {MifareClassic.class.getName()}};

        loadRegistrationPage();
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkNFCIsEnabled();
        nfcAdapter.enableForegroundDispatch(this, nfcIntent, tagFilters, techList);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(listeningState == 'n'){
            resolveCreateIntent(intent);
        } else if(listeningState == 'r') {
            resolveReadIntent(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        nfcAdapter.disableForegroundDispatch(this);
    }

    @JavascriptInterface
    public int writeIDToTag(String name){
        this.userID = Math.abs(name.hashCode()) % 1_000_000_000;
        listeningState = 'n';
        Log.e("Esperando tarjeta", "" + userID);
        return userID;
    }

    @JavascriptInterface
    public int getUserID(){
        return this.userID;
    }

    @JavascriptInterface
    public void log(String texto){
        Log.e("Web Log", texto);
    }

    private void checkNFCIsEnabled(){
        boolean nfcEnabled = nfcAdapter.isEnabled();
        if(!nfcEnabled){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("NFC disabled")
                    .setMessage("Please enable NFC in Settings")
                    .setCancelable(false)
                    .setPositiveButton("Actualizar Settings", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                        }
                    }).create().show();
        }
    }

    void resolveReadIntent(Intent intent){
        String action = intent.getAction();
        if(NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)){
            Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            MifareClassic mfc = MifareClassic.get(tagFromIntent);

            try {
                mfc.connect();
                boolean auth = false;

                int sector = mfc.blockToSector(MIFARE_BLOCK);
                byte[] dataKey;

                dataKey = hexStringToByteArray(KEY_A);
                auth = mfc.authenticateSectorWithKeyA(sector, dataKey);

                if(auth){
                    byte[] dataread = mfc.readBlock(MIFARE_BLOCK+1);
                    Log.i("Bloques", getHexString(dataread, dataread.length));

                    dataread = mfc.readBlock(MIFARE_BLOCK);
                    String blockread = getHexString(dataread, dataread.length);
                    Log.i("Aquí iría un tag", "Bloque leido: " + blockread);

                    this.userID = hexStringToUserID(blockread);
                    if(this.userID == 0)
                        loadRegistrationPage();
                    else
                        webView.loadUrl("file:///android_asset/historial.html");

                    Toast.makeText(this, "Lectura de bloque EXITOSA", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Lectura de bloque fallida", Toast.LENGTH_LONG).show();
                }

                mfc.close();
            } catch(IOException e) {
                Log.e("Error", e.getLocalizedMessage());
            }
        }
    }

    private void loadRegistrationPage(){
        this.userID = 0;
        webView.loadUrl("file:///android_asset/registro.html");
    }

    void resolveCreateIntent(Intent intent) {
        Log.e("Current userID", "" + userID);
        if(userID == 0)
            return;
        Log.e("Hola", "Hola");
        String action = intent.getAction();
        if(NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
            Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            MifareClassic mfc = MifareClassic.get(tagFromIntent);

            try {
                mfc.connect();
                boolean auth = false;

                int sector = mfc.blockToSector(MIFARE_BLOCK);
                byte[] dataKey;

                dataKey = hexStringToByteArray(KEY_A);
                auth = mfc.authenticateSectorWithKeyA(sector, dataKey);

                if(auth) {
                    String strData = userIDToBlockString(userID);
                    byte[] dataToWrite = hexStringToByteArray(strData);
                    mfc.writeBlock(MIFARE_BLOCK, dataToWrite);

                    Toast.makeText(this, "Escritura a bloque EXITOSA", Toast.LENGTH_LONG).show();
                    listeningState = 'r';
                    webView.loadUrl("file:///android_asset/historial.html");
                } else {
                    Toast.makeText(this, "Escritura a bloque FALLIDA, vuelve a acercar el tag al lector", Toast.LENGTH_LONG).show();
                }

                mfc.close();
            } catch(IOException e) {
                Log.e("Error", e.getLocalizedMessage());
            }
        }
    }

    public static String userIDToBlockString(int bloque) {
        String block, strBlock;
        strBlock = intToHexString(bloque);
        block = strBlock + strBlock + strBlock + "00000000";
        System.out.println(block);
        return block.length() == 32 ? block: "00000000000000000000000000000000";
    }

    public static String intToHexString(int x){
        String s = Integer.toHexString(x);
        // Add left zeros
        while(s.length() < 8)
            s = "0" + s;
        return s;
    }

    public static String getHexString(byte[] b, int length){
        String result = "";
        Locale loc = Locale.getDefault();

        for (int i = 0; i < length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
            result += "";
        }
        return result.toUpperCase(loc);
    }

    public static int hexStringToUserID(String s){
        int id1 = Integer.parseInt(s.substring(0, 8), 16);
        int id2 = Integer.parseInt(s.substring(8, 16), 16);
        int id3 = Integer.parseInt(s.substring(16, 24), 16);
        if(id1 == id2 || id1 == id3)
            return id1;
        if(id2 == id3)
            return id2;
        return 0;
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i+=2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

}