package jp.ac.ecc.se.voteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent create = new Intent(this, CreatePage.class);

        Button voteCreate = findViewById(R.id.VoteCreate);
        ListView voteList = findViewById(R.id.voteList);

        ArrayList<String> dataList = new ArrayList<>();
        //アダプター
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, dataList);
        voteList.setAdapter(adapter);

        voteCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(create);
            }
        });
    }
}