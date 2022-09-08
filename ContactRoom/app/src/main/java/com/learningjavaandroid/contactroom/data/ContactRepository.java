package com.learningjavaandroid.contactroom.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.learningjavaandroid.contactroom.model.Contact;
import com.learningjavaandroid.contactroom.util.ContactRoomDatabase;

import java.util.List;

/*
    Central class from where the data is distributed.
    It can get data from dao or network or any other source of data. i.e it confines all the data sources.
*/
public class ContactRepository {
    //Data access object
    private ContactDao contactDao;
    //Actual data
    private LiveData<List<Contact>> allContacts;

    public ContactRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        contactDao = db.contactDao();
        allContacts = contactDao.getAllContacts();

    }

    public LiveData<List<Contact>> getAllData() {
        return allContacts;
    }

    public void insert(Contact contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(() ->{
            contactDao.insert(contact);
        });
    }

    public LiveData<Contact> get(int id) {
        return contactDao.get(id);
    }

    public void update(Contact contact){
        ContactRoomDatabase.databaseWriteExecutor.execute(()-> contactDao.update(contact));

    }

    public void delete(Contact contact){
        ContactRoomDatabase.databaseWriteExecutor.execute(()->contactDao.delete(contact));
    }
}
