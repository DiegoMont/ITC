package com.example.mifareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.PendingIntent;
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
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "nfcmonedero";

    NfcAdapter mNfcAdapter;
    PendingIntent mNfcPendingIntent;
    IntentFilter[] mReadWriteTagFilters;
    private boolean mWriteMode = false, mAuthenticationMode = false, readUIDMode = true;
    String[][] mTechList;

    EditText mTagUID;
    EditText mCardType;
    EditText mHexKeyA;
    EditText mHexKeyB;
    EditText mSector;
    EditText mBloque;
    EditText mDataBloque;
    EditText mDataToWrite;
    AlertDialog mTagDialog;
    RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTagUID = (EditText) findViewById(R.id.tag_uid);
        mCardType = (EditText) findViewById(R.id.cardtype);
        mHexKeyA = (EditText) findViewById(R.id.editTextKeyA);
        mHexKeyB = (EditText) findViewById(R.id.editTextKeyB);
        mSector = (EditText) findViewById(R.id.editTextSector);
        mBloque = (EditText) findViewById(R.id.editTextBloque);
        mDataBloque = (EditText) findViewById(R.id.editTextBloqueLeido);
        mDataToWrite = (EditText) findViewById(R.id.editTextBloqueAEscribir);
        mRadioGroup = (RadioGroup) findViewById(R.id.rBtnGrp);

        findViewById(R.id.buttonauthenticate).setOnClickListener(mTagAuthenticate);
        findViewById(R.id.buttonLeerbloque).setOnClickListener(mTagRead);
        findViewById(R.id.buttonEscribirBloque).setOnClickListener(mTagWrite);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if(mNfcAdapter == null){
            Toast.makeText(this, "Su dispositivo no soporte NFC", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        checkNfcEnabled();

        mNfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter mifareDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);

        try {
            mifareDetected.addDataType("application/com.e.mifarecontrol");
        } catch(IntentFilter.MalformedMimeTypeException e){
            throw new RuntimeException("No se pudo añadir un MIME", e);
        }

        mReadWriteTagFilters = new IntentFilter[] {mifareDetected};

        mTechList = new String[][] {new String[] {MifareClassic.class.getName()}};
        resolveReadIntent(getIntent());
    }

    void resolveReadIntent(Intent intent){
        String action = intent.getAction();
        if(NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)){
            Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            MifareClassic mfc = MifareClassic.get(tagFromIntent);

            if(readUIDMode){
                String tipoTag = "", tamano = "";
                byte[] tagUID = tagFromIntent.getId();
                String hexUID = getHexString(tagUID, tagUID.length);
                Log.i(TAG, "Tag UID: " + hexUID);

                Editable UIDField = mTagUID.getText();
                UIDField.clear();
                UIDField.append(hexUID);

                final String[] TIPOS_TAGS = {"Mifare Classic", "Mifare Plus", "Mifare Pro", "Mifare Desconocido"};
                tipoTag = TIPOS_TAGS[3];
                switch (mfc.getType()){
                    case 0: case 1:case 2: tipoTag = TIPOS_TAGS[mfc.getType()]; break;
                }

                switch (mfc.getSize()){
                    case 1024: tamano = " (1K Bytes)"; break;
                    case 2048: tamano = " (2K Bytes)"; break;
                    case 4096: tamano = " (4K Bytes)"; break;
                    case 320: tamano = " (MINI - 320 Bytes)"; break;
                    default:  tamano = " (Tamaño desconocido)"; break;
                }

                Log.i(TAG, "Card Type: " + tipoTag + tamano);

                Editable cardTypeField = mCardType.getText();
                cardTypeField.clear();
                cardTypeField.append(tipoTag + tamano);
            } else {
                try {
                    mfc.connect();
                    boolean auth = false;
                    String hexKey = "";
                    int id = mRadioGroup.getCheckedRadioButtonId();

                    int sector = mfc.blockToSector(Integer.valueOf(mBloque.getText().toString()));
                    byte[] dataKey;

                    if(id == R.id.radioButtonkeyA){
                        hexKey = mHexKeyA.getText().toString();
                        dataKey = hexStringToByteArray(hexKey);
                        auth = mfc.authenticateSectorWithKeyA(sector, dataKey);
                    } else if(id == R.id.radioButtonkeyB){
                        hexKey = mHexKeyB.getText().toString();
                        dataKey = hexStringToByteArray(hexKey);
                        auth = mfc.authenticateSectorWithKeyB(sector, dataKey);
                    } else {
                        Toast.makeText(this, "Seleccionar llave", Toast.LENGTH_LONG).show();
                        mfc.close();
                        return;
                    }

                    if(auth){
                        int bloque = Integer.valueOf(mBloque.getText().toString());
                        byte[] dataread = mfc.readBlock(bloque+1);
                        Log.i("Bloques", getHexString(dataread, dataread.length));

                        dataread = mfc.readBlock(bloque);
                        String blockread = getHexString(dataread, dataread.length);
                        Log.i(TAG, "Bloque leido: " + blockread);

                        Editable blockField = mDataBloque.getText();
                        blockField.clear();
                        blockField.append(blockread);

                        Toast.makeText(this, "Lectura de bloque EXITOSA", Toast.LENGTH_LONG).show();
                    } else {
                        Editable blockField = mDataBloque.getText();
                        blockField.clear();
                        Toast.makeText(this, "Lectura de bloque fallida", Toast.LENGTH_LONG).show();
                    }

                    mfc.close();
                    mTagDialog.cancel();
                } catch(IOException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                }
            }
        }

    }

    void resolveWriteIntent(Intent intent) {
        String action = intent.getAction();
        if(NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
            Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            MifareClassic mfc = MifareClassic.get(tagFromIntent);

            try {
                mfc.connect();
                boolean auth = false;
                String hexKey = "";
                int id = mRadioGroup.getCheckedRadioButtonId();
                int bloque = Integer.valueOf(mBloque.getText().toString());
                int sector = mfc.blockToSector(bloque);
                byte[] dataKey;

                if(id == R.id.radioButtonkeyA) {
                    hexKey = mHexKeyA.getText().toString();
                    dataKey = hexStringToByteArray(hexKey);
                    auth = mfc.authenticateSectorWithKeyA(sector, dataKey);
                } else if(id == R.id.radioButtonkeyB) {
                    hexKey = mHexKeyA.getText().toString();
                    dataKey = hexStringToByteArray(hexKey);
                    auth = mfc.authenticateSectorWithKeyA(sector, dataKey);
                } else {
                    Toast.makeText(this, "Seleccionar llave", Toast.LENGTH_LONG).show();
                    mfc.close();
                    return;
                }

                if(auth) {
                    String strData = mDataToWrite.getText().toString();
                    byte[] dataToWrite = hexStringToByteArray(strData);
                    mfc.writeBlock(bloque, dataToWrite);

                    Toast.makeText(this, "Escritura a bloque EXITOSA", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Escritura a bloque FALLIDA", Toast.LENGTH_LONG).show();
                }

                mfc.close();
                mTagDialog.cancel();
            } catch(IOException e) {
                Log.e(TAG, e.getLocalizedMessage());
            }
        }
    }

    void resolveAuthIntent(Intent intent) {
        String action = intent.getAction();
        if(NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)){
            Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            MifareClassic mfc = MifareClassic.get(tagFromIntent);

            try {
                mfc.connect();
                boolean auth = false;
                String hexKey = "";

                int id = mRadioGroup.getCheckedRadioButtonId();
                int sector = Integer.valueOf(mSector.getText().toString());
                byte[] dataKey;

                if(id == R.id.radioButtonkeyA){
                    hexKey = mHexKeyA.getText().toString();
                    dataKey = hexStringToByteArray(hexKey);
                    auth = mfc.authenticateSectorWithKeyA(sector, dataKey);
                } else if(id == R.id.radioButtonkeyB) {
                    hexKey = mHexKeyB.getText().toString();
                    dataKey = hexStringToByteArray(hexKey);
                    auth = mfc.authenticateSectorWithKeyB(sector, dataKey);
                } else {
                    Toast.makeText(this, "Seleccionar llave", Toast.LENGTH_LONG).show();
                    mfc.close();
                    return;
                }

                Toast.makeText(this, (auth ? "Autentificacion de sector exitosa": "Autentificación de sector fallida"), Toast.LENGTH_LONG).show();
                mfc.close();
            } catch(IOException e) {
                Log.e(TAG, e.getLocalizedMessage());
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkNfcEnabled();

        Log.d(TAG, "onResume: " + getIntent());

        mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent, mReadWriteTagFilters, mTechList);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.d(TAG, "onNewIntent: " + intent);
        Log.i("Foreground dispatch", "Discovered tag with intent: " + intent);

        if(mAuthenticationMode){
            resolveAuthIntent(intent);
            mTagDialog.cancel();
        } else if(!mWriteMode) {
            resolveReadIntent(intent);
        } else
            resolveWriteIntent(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: " + getIntent());
        mNfcAdapter.disableForegroundDispatch(this);
    }

    private void enableTagWriteMode(){
        mWriteMode = true;
        mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent, mReadWriteTagFilters, mTechList);
    }

    private void enableTagReadMode(){
        mWriteMode = false;
        mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent, mReadWriteTagFilters, mTechList);
    }

    private void enableTagAuthMode(){
        mAuthenticationMode = true;
        mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent, mReadWriteTagFilters, mTechList);
    }

    private View.OnClickListener mTagAuthenticate = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            enableTagAuthMode();

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getString(R.string.ready_to_authenticate))
                    .setMessage(getString(R.string.ready_to_authenticate_instructions))
                    .setCancelable(true)
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            mAuthenticationMode = false;
                        }
                    });
            mTagDialog = builder.create();
            mTagDialog.show();
        }
    };

    private View.OnClickListener mTagRead = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            enableTagReadMode();
            readUIDMode = false;

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getString(R.string.ready_to_read))
                    .setMessage(getString(R.string.ready_to_read_instructions))
                    .setCancelable(true)
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            enableTagReadMode();
                            readUIDMode = true;
                        }
                    });
            mTagDialog = builder.create();
            mTagDialog.show();
        }
    };

    private View.OnClickListener mTagWrite = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            enableTagWriteMode();

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getString(R.string.ready_to_write))
                    .setMessage(getString(R.string.ready_to_write_instructions))
                    .setCancelable(true)
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            enableTagReadMode();
                        }
                    });
            mTagDialog = builder.create();
            mTagDialog.show();
        }
    };

    private void checkNfcEnabled(){
        boolean nfcEnabled = mNfcAdapter.isEnabled();
        if(!nfcEnabled){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getString(R.string.warning_nfc_is_off))
                    .setMessage(getString(R.string.turn_on_nfc))
                    .setCancelable(false)
                    .setPositiveButton("Actualizar Settings", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                        }
                    }).create().show();
        }
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

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i+=2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}