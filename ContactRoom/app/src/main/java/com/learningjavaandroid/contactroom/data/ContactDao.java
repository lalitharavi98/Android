package com.learningjavaandroid.contactroom.data;

//Take care of CRUD operations

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.learningjavaandroid.contactroom.model.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    @Query("DELETE FROM CONTACT_TABLE")
    void deleteAll();

    @Query("SELECT * FROM CONTACT_TABLE ORDER BY name ASC")
    LiveData<List<Contact>>  getAllContacts();

    @Query("SELECT * FROM contact_table WHERE contact_table.id == :id")
    LiveData<Contact> get(int id);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Contact contact);

    @Delete()
    void delete(Contact contact);

}
