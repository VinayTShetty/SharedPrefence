package com.education.sharedpef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Storing data into SharedPreferences

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    EditText enterData_et;
    Button submitData;
    TextView showData;
    Switch resetData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intializeViews();
        createSharedPrefInstance();
        fetchData();
        resetData();
    }
    private void createSharedPrefInstance(){
        sharedPreferences = getSharedPreferences("com.education.sharedpref", MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
    }
    private void intializeViews(){
        enterData_et=(EditText)findViewById(R.id.enterdata_tv);
        submitData=(Button) findViewById(R.id.submitdata_btn);
        showData=(TextView)findViewById(R.id.submitdata_tv);
        resetData=(Switch) findViewById(R.id.resetdata_sw);
    }

    public void submitData(View view) {
        String data= enterData_et.getText().toString();
        showData.setText(data);
        saveDatatoSharedPreferece(data);
        hideKeyboard(view);
    }
    public void saveDatatoSharedPreferece(String data){
        myEdit.putString("DATA",data);
        myEdit.commit();
    }
    public void fetchData(){
        String savedData = sharedPreferences.getString("DATA", "");
        if (!savedData.isEmpty()) {
            // Data exists in SharedPreferences, display it in the TextView
            showData.setText(savedData);
        } else {
            // No data in SharedPreferences
            showData.setText("No data available");
        }
    }
    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Only listner methods can be called in onCreate once and they will execute when ever they are Triggered.
     */
    public void resetData(){
        resetData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    myEdit.remove("DATA");
                    myEdit.apply();
                    // Update the TextView to show that data is cleared
                    showData.setText("No data available");
                }
            }
        });
    }
}