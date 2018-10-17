package com.example.yzeng.Week3AssignYixin.list;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.yzeng.Week3AssignYixin.R;
import com.example.yzeng.Week3AssignYixin.data.TodoNote;
import com.example.yzeng.Week3AssignYixin.data.source.local.DataSourceContract;

public class ListFragment extends Fragment implements
        AdapterView.OnItemClickListener,ListContract.View {

    private String mParam1;
    private String mParam2;
    ListView listView;

    private OnQuestionClickListener mListener;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();

        return fragment;
    }

    ListPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ListPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list, container, false);
        listView = view.findViewById(R.id.listview);
        presenter.getData();
        listView.setOnItemClickListener(this);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnQuestionClickListener) {
            mListener = (OnQuestionClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnQuestionClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
       Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
       String data = cursor.getString(cursor.getColumnIndexOrThrow(DataSourceContract.TodoEntry.COLUMN_NAME_QUESTION));
       //String  data = todoNote.getQuestion();
        //Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
        if (mListener != null) {
            mListener.onQuestionClick(data);
        }


    }

    @Override
    public void setData(Cursor cursor) {
        SimpleCursorAdapter cursorAdapter =
                new SimpleCursorAdapter(getContext(),
                        android.R.layout.simple_list_item_1,//row layout
                        cursor,//data
                        new String[]{DataSourceContract.TodoEntry.COLUMN_NAME_QUESTION},//from
                        new int[]{android.R.id.text1},//textview of the layout
                        0);
        listView.setAdapter(cursorAdapter);
    }


    public interface OnQuestionClickListener {
        // TODO: Update argument type and name
        void onQuestionClick(String question);
    }
}
