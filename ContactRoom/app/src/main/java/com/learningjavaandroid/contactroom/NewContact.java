package com.learningjavaandroid.contactroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.learningjavaandroid.contactroom.model.Contact;
import com.learningjavaandroid.contactroom.model.ContactViewModel;

public class NewContact extends AppCompatActivity {

    public static final String NAME_REPLY = "name_reply";
    public static final String OCCUPATION_REPLY = "occupation_reply";
    private EditText enterName;
    private EditText enterOccupation;
    private Button saveInfoButton;
    private ContactViewModel contactViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        enterName = findViewById(R.id.enter_name);
        enterOccupation = findViewById(R.id.enter_occupation);
        saveInfoButton = findViewById(R.id.save_button);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(
                NewContact.this.getApplication()).create(ContactViewModel.class);

        saveInfoButton.setOnClickListener(v -> {

            Intent reply = new Intent();

            if (!TextUtils.isEmpty(enterName.getText()) && !TextUtils.isEmpty(enterOccupation.getText())) {
                String name = enterName.getText().toString();
                String occupation = enterOccupation.getText().toString();
                reply.putExtra(NAME_REPLY,name);
                reply.putExtra(OCCUPATION_REPLY,occupation);
                setResult(RESULT_OK, reply);

            } else {
                Toast.makeText(this, R.string.empty, Toast.LENGTH_SHORT).show();
                setResult(RESULT_CANCELED, reply);

            }
            finish();

        });
    }
}