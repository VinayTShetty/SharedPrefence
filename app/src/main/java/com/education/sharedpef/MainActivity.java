package com.education.sharedpef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Storing data into SharedPreferences

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    EditText enterData_et;
    Button submitData;
    TextView showData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intializeViews();
        createSharedPrefInstance();
        fetchData();
    }
    private void createSharedPrefInstance(){
        sharedPreferences = getSharedPreferences("com.education.sharedpref", MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
    }
    private void intializeViews(){
        enterData_et=(EditText)findViewById(R.id.enterdata_tv);
        submitData=(Button) findViewById(R.id.submitdata_btn);
        showData=(TextView)findViewById(R.id.submitdata_tv);
    }

    public void submitData(View view) {
        String data= enterData_et.getText().toString();
        showData.setText(data);
        /**
         * Commenting this line so that shared preference won t be called
         */
      //  saveDatatoSharedPreferece(data);
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
}
