package com.example.nfcinventario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "nfcinventory_simple";

    private NfcAdapter mNfcAdapter;
    private PendingIntent mNfcPendingIntent;
    private IntentFilter[] mReadTagFilters, mWriteTagFilters;
    private boolean mWriteMode = false;

    // UI elements
    private EditText mName, mStudentId, mMajor, mMail;
    private AlertDialog mWriteTagDialog;

    public static final String JSON_NAME_FIELD = "name";
    public static final String JSON_STUDENT_ID_FIELD = "studentId";
    public static final String JSON_MAJOR_FIELD = "major";
    public static final String JSON_EMAIL_FIELD = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.write_tag).setOnClickListener(mTagWriter);
        mName = findViewById(R.id.student_name);
        mStudentId = findViewById(R.id.student_id);
        mMajor = findViewById(R.id.student_major);
        mMail = findViewById(R.id.student_mail);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        // check if device supports nfc
        if(mNfcAdapter == null){
            Toast.makeText(this, "Your device does not support NFC", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        checkNfcEnabled();

        mNfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);

        try {
            ndefDetected.addDataType("application/com.example.nfcinventario");
        } catch(IntentFilter.MalformedMimeTypeException e){
            throw new RuntimeException("Could not add MIME type", e);
        }

        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);

        mWriteTagFilters = new IntentFilter[] {tagDetected};
        mReadTagFilters = new IntentFilter[] {ndefDetected, tagDetected};
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkNfcEnabled();

        Log.d(TAG, "onResume: " + getIntent());

        if(getIntent().getAction() != null && getIntent().getAction().equals(NfcAdapter.ACTION_NDEF_DISCOVERED)){
            NdefMessage[] msgs = getNdefMessagesFromIntent(getIntent());
            NdefRecord record = msgs[0].getRecords()[0];
            byte[] payload = record.getPayload();
            setTextFieldValues(new String(payload));
        }

        mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent, mReadTagFilters, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: " + getIntent());
        mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: " + intent);

        if (!mWriteMode) {
            if (intent.getAction().equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
                NdefMessage[] msgs = getNdefMessagesFromIntent(intent);
                confirmDisplayedContentOverwrite(msgs[0]);
            } else if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
                Toast.makeText(this, "This NFC tag currently has no inventory NDEF data", Toast.LENGTH_LONG).show();
            }
        } else if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
            Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            writeTag(createNdefFromJson(), detectedTag);
            mWriteTagDialog.cancel();
        }
    }

    private NdefMessage[] getNdefMessagesFromIntent(Intent intent){
        NdefMessage[] msgs = null;
        String action = intent.getAction();
        if(action.equals(NfcAdapter.ACTION_TAG_DISCOVERED) || action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)){
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if(rawMsgs != null){
                msgs = new NdefMessage[rawMsgs.length];
                for(int i = 0; i < rawMsgs.length; i++)
                    msgs[i] = (NdefMessage) rawMsgs[i];
            } else {
                byte[] empty = new byte[] {};
                NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty);
                NdefMessage msg = new NdefMessage(new NdefRecord[] {record});
                msgs = new NdefMessage[] {msg};
            }
        } else {
            Log.e(TAG, "Unknown intent.");
            finish();
        }
        return msgs;
    }

    private void confirmDisplayedContentOverwrite(final NdefMessage msg){
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.new_tag_found))
                .setMessage(getString(R.string.replace_current_tag))
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String payload = new String(msg.getRecords()[0].getPayload());
                        setTextFieldValues(payload);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
        }).show();
    }

    private void setTextFieldValues(String json){
        JSONObject inventory;
        String name = "", studentId = "", major = "", email = "";
        try {
            inventory = new JSONObject(json);
            name = inventory.getString(JSON_NAME_FIELD);
            studentId = inventory.getString(JSON_STUDENT_ID_FIELD);
            major = inventory.getString(JSON_MAJOR_FIELD);
            email = inventory.getString(JSON_EMAIL_FIELD);
        } catch (JSONException e){
            Log.e(TAG, "Couldnt parse json", e);
        }

        Editable nameField = mName.getText();
        nameField.clear();
        nameField.append(name);

        Editable studentIdField = mStudentId.getText();
        studentIdField.clear();
        studentIdField.append(studentId);

        Editable majorField = mMajor.getText();
        majorField.clear();
        majorField.append(major);

        Editable mailField = mMail.getText();
        mailField.clear();
        mailField.append(email);
    }

    private View.OnClickListener mTagWriter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            enableTagWriteMode();

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getString(R.string.ready_to_write))
                    .setMessage(getString(R.string.ready_to_write_instructions))
                    .setCancelable(true)
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            enableTagReadMode();
                        }
                    });
            mWriteTagDialog = builder.create();
            mWriteTagDialog.show();
        }
    };

    private NdefMessage createNdefFromJson(){
        Editable nameField = mName.getText();
        Editable studentIdField = mStudentId.getText();
        Editable majorField = mMajor.getText();
        Editable emailField = mMail.getText();

        JSONObject specs = new JSONObject();
        try{
            specs.put(JSON_NAME_FIELD, nameField);
            specs.put(JSON_STUDENT_ID_FIELD, studentIdField);
            specs.put(JSON_MAJOR_FIELD, majorField);
            specs.put(JSON_EMAIL_FIELD, emailField);
        } catch(JSONException e){
            Log.e(TAG, "Could not create JSON", e);
        }

        String mimeType = "application/com.example.nfcinventario";
        byte[] mimeBytes = mimeType.getBytes(Charset.forName("UTF-8"));
        String data = specs.toString();
        byte[] dataBytes = data.getBytes(Charset.forName("UTF-8"));
        byte[] id = new byte[0];
        NdefRecord record = new NdefRecord(NdefRecord.TNF_MIME_MEDIA, mimeBytes, id, dataBytes);
        NdefMessage m = new NdefMessage(new NdefRecord[] {record});
        return m;
    }

    private void enableTagWriteMode(){
        mWriteMode = true;
        mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent, mWriteTagFilters, null);
    }

    private void enableTagReadMode(){
        mWriteMode = false;
        mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent, mReadTagFilters, null);
    }

    private boolean writeTag(NdefMessage message, Tag tag){
        int size = message.toByteArray().length;

        try{
            Ndef ndef = Ndef.get(tag);
            if(ndef != null){
                ndef.connect();
                if(!ndef.isWritable()){
                    Toast.makeText(this, "Cannot write to tag", Toast.LENGTH_LONG).show();
                    return false;
                }
                if(ndef.getMaxSize() < size){
                    Toast.makeText(this, "Message size to big", Toast.LENGTH_LONG).show();
                    return false;
                }

                ndef.writeNdefMessage(message);
                Toast.makeText(this, "A pre-formatted tag was updated", Toast.LENGTH_LONG).show();
                return true;
            } else {
                NdefFormatable format = NdefFormatable.get(tag);
                if(format != null){
                    try {
                        format.connect();
                        format.format(message);
                        Toast.makeText(this, "The tag was successfully formatted ", Toast.LENGTH_LONG).show();
                        return true;
                    } catch(IOException e){
                        Toast.makeText(this, "Cannot write to this tag", Toast.LENGTH_LONG).show();
                        return false;
                    }
                } else {
                    Toast.makeText(this, "The tag does not support NDEF", Toast.LENGTH_LONG).show();
                    return false;
                }
            }
        } catch(Exception e){
            Toast.makeText(this, "Exception", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void checkNfcEnabled(){
        boolean nfcEnabled = mNfcAdapter.isEnabled();
        if(!nfcEnabled){
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.warning_nfc_is_off))
                    .setMessage(getString(R.string.turn_on_nfc))
                    .setCancelable(false)
                    .setPositiveButton("Update Settings", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                        }
                    }).create().show();
        }
    }
}