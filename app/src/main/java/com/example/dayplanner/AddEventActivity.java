package com.example.dayplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AddEventActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
    }
    public void cancel(View view) { finish(); }
    public void addEvent(View view) {

        EditText edtDate = findViewById(R.id.edtDate);
        EditText edtName = findViewById(R.id.edtName);
        Intent replyIntent = new Intent();
        if(TextUtils.isEmpty(edtDate.getText()) || TextUtils.isEmpty(edtName.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            replyIntent.putExtra(MainActivity.EVENT_DATE, edtDate.getText().toString());
            replyIntent.putExtra(MainActivity.EVENT_NAME, edtName.getText().toString());
            setResult(RESULT_OK, replyIntent);
        }

        finish();


    }
}