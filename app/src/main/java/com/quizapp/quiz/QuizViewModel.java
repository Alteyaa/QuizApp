package com.quizapp.quiz;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.quizapp.App;
import com.quizapp.core.SingleLiveEvent;
import com.quizapp.model.Question;

import java.util.List;

import com.quizapp.data.IQuizRepository;

public class QuizViewModel extends ViewModel {

    private IQuizRepository quizRepository = App.quizRepository;
    private List<Question> mQuestions;

    MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();

    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();

    void init(Integer amount, Integer categoryId, String difficulty) {
        currentQuestionPosition.setValue(0);

        quizRepository.getQuiz(
                amount,
                categoryId,
                difficulty,
                new IQuizRepository.OnQuizCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                if (result.size() != 0){

                    Log.d("ololo", "onSuccess: " + result.size());
                    Log.d("ololo", "onSuccess: " + result.get(0).getCategory());
                }
                mQuestions = result;
                questions.setValue(mQuestions);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("ololo", "Error " + e.getMessage());
            }
        });
    }

    void onAnswerClick(int questionPosition, int answerPosition) {
        //TODO: Add answer position field to Question model and update on answer selected
    }

    void onSkipClick() {
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            if (currentPosition == questions.getValue().size() - 1) {
                //TODO: Calculate result
                finishEvent.call();
            } else {
                mQuestions.set(currentPosition, mQuestions.get(currentPosition));
                questions.setValue(mQuestions);

                currentQuestionPosition.setValue(currentPosition + 1);
            }
        }


    }
    void onBackPressed() {
        int currentPosition = currentQuestionPosition.getValue() - 1;
        if (currentPosition >= 0) {
            currentQuestionPosition.setValue(currentPosition);
        } else {
            finishEvent.call();
        }
    }
}