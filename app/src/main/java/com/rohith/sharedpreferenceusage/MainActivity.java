package com.rohith.sharedpreferenceusage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;
    String data_key = "persistentData"; //This is key where shared preference ties to latch on to

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputField = findViewById(R.id.editText);
        Button saveData = findViewById(R.id.button);
        Button loadSavedData = findViewById(R.id.button2);
        final TextView displayField = findViewById(R.id.textView);
        displayField.setText("");

        preferences = this.getSharedPreferences(data_key, Context.MODE_PRIVATE);

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = preferences.edit();
                edit.putString(data_key,inputField.getText().toString());
                edit.commit();
            }
        });

        loadSavedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "No data was stored";
                try {
                    data = preferences.getString(data_key,"");
                    displayField.setText(data);
                }catch (Exception e){
                    Log.e("MainActivity ", String.valueOf(e));
                    Toast.makeText(MainActivity.this, "No Data was Store", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
