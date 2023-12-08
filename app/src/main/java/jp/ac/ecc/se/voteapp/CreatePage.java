package jp.ac.ecc.se.voteapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreatePage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createpage);
    }








//    private void addNewOption() {
//        String newOptionText = newOptionEditText.getText().toString().trim();
//
//        if (!newOptionText.isEmpty()) {
//            TextView newOptionTextView = new TextView(this);
//            newOptionTextView.setText(newOptionText);
//
//            // 將新選項添加到選項布局中
//            optionsLayout.addView(newOptionTextView);
//
//            // 清空輸入框
//            newOptionEditText.getText().clear();
//        }
//    }
}
