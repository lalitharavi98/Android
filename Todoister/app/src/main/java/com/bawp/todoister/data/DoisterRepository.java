package com.bawp.todoister.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bawp.todoister.model.Task;
import com.bawp.todoister.util.TaskRoomDatabase;

import java.util.List;

public class DoisterRepository {
    private final TaskDao taskDao;
    private final TaskRoomDatabase db;
    private LiveData<List<Task>> allTasks;

    public DoisterRepository(Application application) {
        db = TaskRoomDatabase.getDatabase(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    public LiveData<Task> get(long id) { return taskDao.get(id); }

    public void insert(Task task){
        TaskRoomDatabase.dataBaseWriterExecutor.execute(() -> {
            taskDao.insert(task);
        });
    }

    public void delete(Task task) {
        TaskRoomDatabase.dataBaseWriterExecutor.execute(()->{
            taskDao.delete(task);
        });
    }

    public void update(Task task) {
        TaskRoomDatabase.dataBaseWriterExecutor.execute(()->{
            taskDao.update(task);
        });
    }

    public LiveData<List<Task>> getAllTasks () {
        return allTasks;
    }

    public void deleteAllTasks() {
        TaskRoomDatabase.dataBaseWriterExecutor.execute(() -> {
            taskDao.deleteAll();
        });
    }

}
