package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DetailViewActivity extends Activity {

    //Added fields for appropriate business info display
    private EditText nameField1, numberField1,addressField1,typeField1, provinceField1;
    private MyApplicationData appState;
    Contact receivedPersonInfo;
    String businessID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        appState = ((MyApplicationData) getApplicationContext());
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        nameField1 = (EditText) findViewById(R.id.name2);
        numberField1 = (EditText) findViewById(R.id.number2);
        addressField1 = (EditText) findViewById(R.id.address2);
        typeField1 = (EditText) findViewById(R.id.type2);
        provinceField1 = (EditText) findViewById(R.id.province2);


        if(receivedPersonInfo != null){
            nameField1.setText(receivedPersonInfo.businessName);
            numberField1.setText(receivedPersonInfo.businessNumber);
            addressField1.setText(receivedPersonInfo.businessAddress);
            typeField1.setText(receivedPersonInfo.businessType);
            provinceField1.setText(receivedPersonInfo.provinceInitials);
        }
    }

    public void updateContact(View v){
        businessID = receivedPersonInfo.businessID;
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        DatabaseReference bizRef = appState.firebaseReference.child(businessID);
        Map<String, Object> bizUpdates = new HashMap<String, Object>();
        //Retrieve changes made in fields by user
        String bizName = nameField1.getText().toString();
        String bizAddress = addressField1.getText().toString();
        String bizNumber = numberField1.getText().toString();
        String bizType = typeField1.getText().toString();
        String bizProv = provinceField1.getText().toString();
        //add all changes
        bizUpdates.put("businessName", bizName);
        bizUpdates.put("businessNumber", bizNumber);
        bizUpdates.put("businessType", bizType);
        bizUpdates.put("businessAddress", bizAddress);
        bizUpdates.put("provinceInitials", bizProv);
        //Update with changes
        bizRef.updateChildren(bizUpdates);
    }

    public void eraseContact(View v) {
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        businessID = receivedPersonInfo.businessID;
        appState.firebaseReference.child(businessID).removeValue();
    }
}
