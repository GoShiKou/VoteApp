package jp.ac.ecc.se.voteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

        Intent createPage = new Intent(this, CreatePage.class);
        Intent votePage = new Intent(this, VotePage.class);

        Button voteCreate = findViewById(R.id.VoteCreate);
        ListView voteList = findViewById(R.id.voteList);
        EditText searchText = findViewById(R.id.SearchText);

        titleList = new ArrayList<>();
        //アダプター
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, titleList);
        voteList.setAdapter(adapter);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        voteCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(createPage);
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