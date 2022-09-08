package com.learningjavaandroid.contactroom.model;

/*
    To recognise it as schema to create tables or entities  we use annotations @Entity
*/

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "occupation")
    private String occupation;

    public Contact() {
    }

    public Contact(@NonNull String name,@NonNull String occupation) {
        this.name = name;
        this.occupation = occupation;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }
}
