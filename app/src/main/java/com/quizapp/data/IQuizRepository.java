package com.quizapp.data;

import com.quizapp.model.Question;

import java.util.List;

public interface IQuizRepository {
    void getQuiz(Integer amount,
                 Integer category,
                 String difficulty,
                 OnQuizCallback callback);

    interface OnQuizCallback {
        void onSuccess(List<Question> questions);

        void onFailure(Exception e);
    }
}
