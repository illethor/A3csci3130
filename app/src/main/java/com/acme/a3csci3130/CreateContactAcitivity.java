package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, numberField, typeField, addressField, provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name2);
        numberField = (EditText) findViewById(R.id.number);
        typeField = (EditText) findViewById(R.id.type);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String businessID = appState.firebaseReference.push().getKey();
        String businessName = nameField.getText().toString();
        String businessNumber = numberField.getText().toString();
        String businessType = typeField.getText().toString();
        String businessAddress = addressField.getText().toString();
        String provinceInitials = provinceField.getText().toString();

        Contact person = new Contact(businessID, businessName, businessNumber, businessType, businessAddress,provinceInitials);

        appState.firebaseReference.child(businessID).setValue(person);

        finish();

    }
}
