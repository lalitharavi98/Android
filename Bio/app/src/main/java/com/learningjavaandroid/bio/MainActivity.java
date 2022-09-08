package com.learningjavaandroid.bio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.learningjavaandroid.bio.data.Bio;
import com.learningjavaandroid.bio.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Bio bio = new Bio();
//    private EditText enterHobbies;
//    private TextView hobbies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
         Not needed when Binding View is enabled.
         ( To enable data binding modify build.gradle module -> add build features dataBinding
         and move all the xml tags inside layout tag in activity_main.xml.
        */

//        setContentView(R.layout.activity_main);
//        enterHobbies = findViewById(R.id.enter_hobbies);
//        hobbies = findViewById(R.id.hobbies_text);

        /*
            Binding View
        */

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        bio.setName("Lalitha Ravi");
        // data binding
        binding.setBio(bio);
        binding.doneButton.setOnClickListener(this::addHobbies);
    }
    public void addHobbies(View view) {
//        hobbies.setText(String.format("Hobbies: %s", enterHobbies.getText().toString().trim()));
//        hobbies.setVisibility(View.VISIBLE);

        // binding data
        bio.setHobbies(String.format("Hobbies: %s", binding.enterHobbies.getText().toString().trim()));
        // binding View
//        binding.hobbiesText.setText(String.format("Hobbies: %s", binding.enterHobbies.getText().toString().trim()));
        binding.invalidateAll();
        binding.hobbiesText.setVisibility(View.VISIBLE);

        //hide keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);


    }
}