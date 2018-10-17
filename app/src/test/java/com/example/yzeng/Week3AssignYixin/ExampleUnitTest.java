package com.example.yzeng.Week3AssignYixin;

import com.example.yzeng.Week3AssignYixin.main.MainActivity;
import com.example.yzeng.Week3AssignYixin.main.MainPresenter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void junitTest() {

        MainActivity mainActivity = new MainActivity();
        mainActivity.cursorPosition = 0;
        MainPresenter mainPresenter = new MainPresenter(mainActivity);
        mainPresenter.positionIncrease();
        mainPresenter.positionIncrease();
        mainPresenter.positionIncrease();
        assertEquals(mainActivity.cursorPosition, 2);

    }

}