package com.bawp.todoister.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bawp.todoister.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    public void insert(Task task);

    @Query("DELETE FROM task_table")
    public void deleteAll();

    @Query("SELECT * FROM task_table")
    public LiveData<List<Task>> getAllTasks();

    @Update
    public void update(Task task);

    @Delete
    public void delete(Task task);

    @Query("SELECT * FROM task_table WHERE task_table.task_id == :task_id")
    public LiveData<Task> get(long task_id);

}
