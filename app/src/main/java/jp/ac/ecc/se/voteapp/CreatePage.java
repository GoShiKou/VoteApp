package jp.ac.ecc.se.voteapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
public class CreatePage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createpage);

        ArrayList<String> datalist = new ArrayList<>();
        //
        ListView ListView= findViewById(R.id.list);
        EditText choice = findViewById(R.id.editText);
        Button Button1 = findViewById(R.id.add);
        Button Button2 = findViewById(R.id.button);
        Button Button3 = findViewById(R.id.camera);
    }








//    private void addNewOption() {
//        String newOptionText = newOptionEditText.getText().toString().trim();
//
//        if (!newOptionText.isEmpty()) {
//            TextView newOptionTextView = new TextView(this);
//            newOptionTextView.setText(newOptionText);
//
//            // add new option
//            optionsLayout.addView(newOptionTextView);
//
//            // clear input
//            newOptionEditText.getText().clear();
//        }
//    }
}
