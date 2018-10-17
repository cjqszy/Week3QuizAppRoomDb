package com.example.yzeng.Week3AssignYixin.main;

import com.example.yzeng.Week3AssignYixin.data.TodoNote;
import com.example.yzeng.Week3AssignYixin.data.source.TaskDataSource;
import com.example.yzeng.Week3AssignYixin.data.source.TaskRepository;

public class MainPresenter implements MainContract.Presenter, TaskDataSource.TodoNoteCallBack {

    MainContract.View view;
    TaskDataSource todoRepository;

    public MainPresenter(MainActivity mainActivity) {
        view = mainActivity;
        todoRepository = new TaskRepository(mainActivity);
    }

    @Override
    public void getquizQA(int cursorPosition) {
        todoRepository.getQuesAndAnsFromDB(this, cursorPosition);
    }

    @Override
    public void initializer() {
        todoRepository.dbInitializer();
    }

    @Override
    public void positionIncrease() {
        view.positionIncreaseComfirm();
    }

    @Override
    public void positionDecrease() {
        view.positionDecreaseComfirm();
    }

    @Override
    public void roomDbInitialzer() {
        view.roomDbInitializer();
    }

    @Override
    public void ShowQuiz(TodoNote todonote) {
        view.Showquiz(todonote);
    }
}
