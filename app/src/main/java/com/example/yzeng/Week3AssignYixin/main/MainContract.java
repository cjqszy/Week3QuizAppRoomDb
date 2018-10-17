package com.example.yzeng.Week3AssignYixin.main;

import com.example.yzeng.Week3AssignYixin.data.TodoNote;

public interface MainContract {
    
    interface View{
        void Showquiz(TodoNote todonote);

        void positionIncreaseComfirm();

        void positionDecreaseComfirm();

        void roomDbInitializer();
    }
    
    interface Presenter{
        void getquizQA(int cursorPosition);

        void initializer();

        void positionIncrease();

        void positionDecrease();

        void roomDbInitialzer();
    }
}
