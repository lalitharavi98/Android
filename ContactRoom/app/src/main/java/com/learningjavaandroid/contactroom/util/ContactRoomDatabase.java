package com.learningjavaandroid.contactroom.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.learningjavaandroid.contactroom.data.ContactDao;
import com.learningjavaandroid.contactroom.model.Contact;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//To identify it as a database to be created  and pass entities and tables we use @Database annotatio

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactRoomDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();
    public static final int NO_OF_THREADS = 4;

    //volatile able to remove itself when need be
    public static volatile ContactRoomDatabase INSTANCE;

    //executive service helps us run things in the background
    public static final ExecutorService databaseWriteExecutor =   Executors.newFixedThreadPool(NO_OF_THREADS);

    //create database
    public static ContactRoomDatabase getDatabase(Context context) {
        if(INSTANCE == null){
            synchronized (ContactRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactRoomDatabase.class,"contact_database")
                            .addCallback(sRoomDataBaseCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDataBaseCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(()->{
                ContactDao contactDao = INSTANCE.contactDao();
                contactDao.deleteAll();

                Contact contact = new Contact("Jeremy","Prof");
                contactDao.insert(contact);

                contact = new Contact("Bond","spy");
                contactDao.insert(contact);


            });
        }
    };

}
