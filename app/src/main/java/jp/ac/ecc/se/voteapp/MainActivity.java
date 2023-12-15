package jp.ac.ecc.se.voteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_CREATE_NOTE = 1;
    private ArrayList<String> titleList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent create = new Intent(this, CreatePage.class);
        Intent vote = new Intent(this, VotePage.class);

        Button voteCreate = findViewById(R.id.VoteCreate);
        ListView voteList = findViewById(R.id.voteList);

        titleList = new ArrayList<>();
        //アダプター
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, titleList);
        voteList.setAdapter(adapter);

        voteCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(create);
            }
        });

        voteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //リストクリック処理
            }
        });
    }

    // CreatePageActivityからの結果を受け取る
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CREATE_NOTE && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("title");

            // リストに追加して表示する
            titleList.add(title);
            adapter.notifyDataSetChanged();
        }
    }
}