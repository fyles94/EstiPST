package com.example.panda.estimote;

/**
 * Created by Panda on 11/01/2016.
 */


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMDataObjectSpecialization;

public class Item  {
    public static final String CLASS_NAME = "Item";
    private static final String NAME = "name";
    private static final String USERID = "userID";
    private static final String DATACREATED = "date";

    /**
     * gets the name of the Item.
     * @return String itemName
     */
    public String getName() {
        return (String) getObject(NAME);
    }

    public String getCreatedAt() {
        return (String) getObject(DATACREATED);
    }
    public String getUserID() {
        return (String) getObject(USERID);
    }


    /**
     * sets the name of a list item, as well as calls setCreationTime()
     * @param String itemName
     */
    public void setName(String itemName) {
        setObject(NAME, (itemName != null) ? itemName : "");
    }

    public void setUserID(String userID) {
        setObject(USERID, (userID != null) ? userID : "");
    }

    public void setCreatedAt() {

        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);

        // Get the date today using Calendar object.
        Date today = Calendar.getInstance().getTime();
        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        String reportDate = df.format(today);

        // Print what date is today!
        //System.out.println("Report Date: " + reportDate);
        setObject(DATACREATED, (reportDate != null) ? reportDate : "");
    }


    /**
     * when calling toString() for an item, we'd really only want the name.
     * @return String theItemName
     */
    public String toString() {
        String theItemName = "";
        theItemName = getName();
        return theItemName;
    }
}