 package com.learningjavaandroid.showmyname;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

 public class MainActivity extends AppCompatActivity {

    private Button showButton;
    private TextView nameText;
    private EditText enterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView will get the activity_main.xml and show to the user.
        setContentView(R.layout.activity_main);
        // connect button to the one in interface using id ( cmd + click to navigate to that id )
        showButton = findViewById(R.id.button);
        nameText = findViewById(R.id.textView);
        enterName = findViewById(R.id.editTextName);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = enterName.getText().toString();
                if(name.isEmpty()){
                    nameText.setText("Hello, Default!!");
                }else{
                    nameText.setText("Hello, " + name);
                }

            }
        });


    }
}