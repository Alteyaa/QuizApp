package com.quizapp.data.history;

import androidx.lifecycle.LiveData;

import com.quizapp.model.QuizResult;
import com.quizapp.model.ShortQuizResult;

import java.util.List;

public interface IHistoryStorage {


    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    LiveData<List<QuizResult>> getAll();


    LiveData<List<ShortQuizResult>> getAllShort();

}
