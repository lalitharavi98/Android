package com.learningjavaandroid.contactroom;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.learningjavaandroid.contactroom.adapter.RecyclerViewAdapter;
import com.learningjavaandroid.contactroom.model.Contact;
import com.learningjavaandroid.contactroom.model.ContactViewModel;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnContactClickListener {
    private ContactViewModel contactViewModel;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
//    private LiveData<List<Contact>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        contactViewModel = new ViewModelProvider
                .AndroidViewModelFactory(MainActivity.this.getApplication())
                .create(ContactViewModel.class);
        contactViewModel.getAllContacts().observe(this, contacts -> {
//            StringBuilder builder = new StringBuilder();
//            for (Contact contact : contacts) {
//                builder.append("-").append(contact.getName()).append(" ").append(contact.getOccupation()).append(" ");
//                Log.d("TAG", "onCreate: " + contact.getName());
//            }

            //adapter
            recyclerViewAdapter = new RecyclerViewAdapter(contacts,this, this);
            recyclerView.setAdapter(recyclerViewAdapter);


        });



        fab = findViewById(R.id.add_contact_fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewContact.class);
            activityResultLauncher.launch(intent);

        });
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                String name = result.getData().getStringExtra(NewContact.NAME_REPLY);
                String occupation = result.getData().getStringExtra(NewContact.OCCUPATION_REPLY);
                Contact contact = new Contact(name, occupation);
                ContactViewModel.insert(contact);
                Log.d("TAG", "onActivityResult: " + name);
                Log.d("TAGs", "onActivityResult: " + occupation);
            }
        }
    });

    @Override
    public void onContactClick(int position) {
        Contact contact = Objects.requireNonNull(contactViewModel.allContacts.getValue()).get(position);
        Log.d("Clicked", "onContactClick: " + contact.getName());
    }
}