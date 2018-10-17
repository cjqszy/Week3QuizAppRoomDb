package com.example.yzeng.Week3AssignYixin.list;

import android.database.Cursor;

import com.example.yzeng.Week3AssignYixin.data.source.TaskDataSource;
import com.example.yzeng.Week3AssignYixin.data.source.TaskRepository;

public class ListPresenter implements ListContract.Presenter, TaskDataSource.QuestionsCallBack {
    TaskDataSource todoRepository;
    ListContract.View view;

    public ListPresenter(ListFragment listFragment) {
        todoRepository = new TaskRepository(listFragment.getContext());
        view = listFragment;
    }

    @Override
    public void getData() {
        todoRepository.dbInitializer();
        todoRepository.getAll(this);
    }

    @Override
    public void getAllQuestions(Cursor cursor) {
        view.setData(cursor);

    }
}
