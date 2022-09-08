package com.bawp.todoister.adapter;

import com.bawp.todoister.model.Task;

public interface OnToDoClickListener {
    void onTodoClick(Task task);
    void onTodoRadioButtonClick(Task task);
}
