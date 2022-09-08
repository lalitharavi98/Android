package com.learningjavaandroid.trivia.data;

import com.learningjavaandroid.trivia.model.Question;

import java.util.ArrayList;

public interface AnswerListAsyncResponse {
    void processFinished(ArrayList<Question> questionArrayList);
}
