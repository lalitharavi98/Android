package com.learningjavaandroid.introfirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private EditText enterTitle;
    private EditText enterThought;
    private Button saveButton, showButton, updateTitle, deleteThought;
    private TextView recTile;

    //Keys
    public static final String KEY_TITLE = "title";
    public static final String KEY_THOUGHT = "thought";

    //Connection to Firestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private DocumentReference journalRef = db.document("Journal/First Thoughts");
    private CollectionReference collectionReference = db.collection("Journal");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deleteThought = findViewById(R.id.delete_thought);
        saveButton = findViewById(R.id.save_button);
        updateTitle = findViewById(R.id.update_data);
        enterTitle = findViewById(R.id.edit_text_title);
        enterThought = findViewById(R.id.edit_text_thoughts);
        recTile = findViewById(R.id.rec_title);
        showButton = findViewById(R.id.show_data);

        updateTitle.setOnClickListener(this);
        deleteThought.setOnClickListener(this);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getThoughtsCollectionRef();
//                getThoughtJournalRef();

            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addThoughtCollectionRef();
//                addThoughtJournalRef();


            }
        });
    }

    private void addThoughtJournalRef() {
        String title = enterTitle.getText().toString().trim();
        String thought = enterThought.getText().toString().trim();

        Journal journal = new Journal();
        journal.setTitle(title);
        journal.setThought(thought);

        Map<String, Object> data = new HashMap<>();
        data.put(KEY_TITLE, title);
        data.put(KEY_THOUGHT, thought);

        journalRef.set(journal)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this,
                                        "Success", Toast.LENGTH_LONG)
                                .show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.toString());
                    }
                });
    }

    private void getThoughtJournalRef() {
        journalRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {

                            Journal journal = documentSnapshot.toObject(Journal.class);
//                                    String title = documentSnapshot.getString(KEY_TITLE);
//                                    String thought = documentSnapshot.getString(KEY_THOUGHT);

                            if (journal != null) {
                                recTile.setText(journal.toString());
                                recTile.setText(journal.getTitle());
                            }


                        } else {
                            Toast.makeText(MainActivity.this,
                                            "No data exists",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.toString());
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        snapShotListenerCollectionRef();

//        snapshotListenerJournalRef();
    }

    private void snapShotListenerCollectionRef() {
        collectionReference.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    Log.d(TAG, "onEvent: " + e.toString());
                }

                if (queryDocumentSnapshots != null) {
                    for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots) {

                        Journal journal = snapshots.toObject(Journal.class);
                        recTile.setText(journal.toString());


                    }
                }


            }
        });
    }

    private void snapshotListenerJournalRef() {
        journalRef.addSnapshotListener(this, (documentSnapshot, e) -> {
            if (e != null) {
                Toast.makeText(MainActivity.this, "Something went wrong",
                                Toast.LENGTH_LONG)
                        .show();
            }
            if (documentSnapshot != null && documentSnapshot.exists()) {
                Journal journal = documentSnapshot.toObject(Journal.class);
                recTile.setText(journal.toString());

            } else {
                recTile.setText("");
            }


        });
    }

    private void addThoughtCollectionRef() {
        String title = enterTitle.getText().toString().trim();
        String thought = enterThought.getText().toString().trim();

        Journal journal = new Journal(title, thought);
        collectionReference.add(journal);

        recTile.setText(journal.toString());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_data:
                //call update
                updateMyTitle();
                break;
            case R.id.delete_thought:
                deleteThought();
//                deleteAll();
                break;
        }

    }

    private void deleteThought() {
        journalRef.update(KEY_THOUGHT, FieldValue.delete());
    }


    private void getThoughtsCollectionRef() {
        collectionReference.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String text = "";
                        for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots) {

                            Journal journal = snapshots.toObject(Journal.class);
                            text += journal.toString();


                        }
                        recTile.setText(text);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    private void deleteAll() {
        journalRef.delete();
    }

    private void updateMyTitle() {
        String title = enterTitle.getText().toString().trim();
        String thought = enterThought.getText().toString().trim();

        Map<String, Object> data = new HashMap<>();
        data.put(KEY_TITLE, title);
        data.put(KEY_THOUGHT, thought);

        journalRef.update(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "Updated!",
                                Toast.LENGTH_LONG)
                        .show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }
}
