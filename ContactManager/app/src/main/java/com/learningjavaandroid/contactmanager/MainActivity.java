package com.learningjavaandroid.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.learningjavaandroid.contactmanager.data.DatabaseHandler;
import com.learningjavaandroid.contactmanager.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        contactArrayList = new ArrayList<>();
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
//        Contact jeremy = new Contact();
//        jeremy.setName("m4");
//        jeremy.setPhoneNumber("9845612092");
//        db.addContact(jeremy);
//        Contact jason = new Contact();
//        jason.setId(2);
//        jason.setName(null);
//        jason.setPhoneNumber(null);
//        db.updateContact(jason);
//        db.deleteContact(8);
//       List<Contact> contactList =  db.getAllContacts();
//       for(Contact contact: contactList){
//           Log.d("MainActivity", "onCreate: get contact " + contact.getId() + " " + contact.getName());
//       }
//        Contact contact = db.getContact(2);
//        Log.d("MainActivity", "onCreate: contact" + contact);
//        Log.d("DBCount", "onCreate Count: " + db.getCount());

//        db.addContact(new Contact("James", "12345"));
//        db.addContact(new Contact("Greg", "67891"));
//        db.addContact(new Contact("Helena", "91011"));
//        db.addContact(new Contact("Carmino", "12131"));

//        db.addContact(new Contact("Raswan", "12345"));
//        db.addContact(new Contact("Roger", "67891"));
//        db.addContact(new Contact("Robert", "91011"));
//        db.addContact(new Contact("Poirot", "12131"));
//        db.addContact(new Contact("Hercules", "12345"));
//        db.addContact(new Contact("Langdon", "67891"));
//        db.addContact(new Contact("Marriot", "91011"));
//        db.addContact(new Contact("Mercure", "12131"));
//        db.addContact(new Contact("Mony", "12345"));
//        db.addContact(new Contact("Sony", "67891"));
//        db.addContact(new Contact("Tony", "91011"));
//        db.addContact(new Contact("Camila", "12131"));

        List<Contact> contactList =  db.getAllContacts();
        for(Contact contact: contactList){
            Log.d("MainActivity", "onCreate: get contact " + contact.getId() + " " + contact.getName());
            contactArrayList.add(contact.getName());
        }

        //create array adapter
        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                contactArrayList
        );
        listView.setAdapter(arrayAdapter);

        //Attach eventListener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("List", "onItemClick: " + contactArrayList.get(position));
            }
        });

    }
}