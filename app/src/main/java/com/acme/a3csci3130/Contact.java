package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Contact implements Serializable {

    public  String businessName, businessID;
    public  String businessNumber;
    public  String businessType,provinceInitials, businessAddress;

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Contact(String businessID, String businessName,String businessNumber, String businessType, String businessAddress, String provinceInitials){
        this.businessID = businessID;
        this.businessName = businessName;
        this.businessNumber = businessNumber;
        this.businessType = businessType;
        this.businessAddress = businessAddress;
        this.provinceInitials = provinceInitials;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("businessName", businessName);
        result.put("businessNumber", businessNumber);
        result.put("businessType", businessType);
        result.put("businessAddress", businessAddress);
        result.put("provinceInitials", provinceInitials);

        return result;
    }
}
