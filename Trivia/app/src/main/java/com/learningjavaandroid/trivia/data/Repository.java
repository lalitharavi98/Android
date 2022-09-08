package com.learningjavaandroid.trivia.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.learningjavaandroid.trivia.controller.AppController;
import com.learningjavaandroid.trivia.model.Question;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    ArrayList<Question> questionArrayList = new ArrayList<>();
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    public ArrayList<Question> getQuestions(final AnswerListAsyncResponse callBack) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
//                        Log.d("REPO ", "onResponse: " + response.getJSONArray(i).get(0));
//                        Log.d("REPO ", "onResponse: " + response.getJSONArray(i).getBoolean(0));
                    Question question = new Question(response.getJSONArray(i).get(0).toString(),
                            response.getJSONArray(i).getBoolean(1));
                    questionArrayList.add(question);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (callBack != null) callBack.processFinished(questionArrayList);

        }, error -> {
            Log.d("REPO", "Failed to get questions.");
        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        return questionArrayList;
    }
}
