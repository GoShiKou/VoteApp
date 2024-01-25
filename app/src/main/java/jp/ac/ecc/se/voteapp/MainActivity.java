package jp.ac.ecc.se.voteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_CREATE_NOTE = 1;
    public static ArrayList<String> titleList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    ListView voteList;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titleList);
        voteList.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();

        Intent createPage = new Intent(this, CreatePage.class);
        Intent votePage = new Intent(this, VotePage.class);

        Button voteCreate = findViewById(R.id.VoteCreate);
        voteList = findViewById(R.id.voteList);
        EditText searchText = findViewById(R.id.SearchText);
        titleList = new ArrayList<>();
        ImageView profile = findViewById(R.id.profileImage);

        String str_title = pref.getString("title", "");
        if (str_title != null && !str_title.equals("")) {
            String[] List = str_title.split(",");

            for (int i = 0; i < List.length; i++) {
                titleList.add(List[i]);
            }
        }

        if(!pref.getString("list","").isEmpty()) {
            String[] titleSprit = pref.getString("list", "").split(",");
            titleList.addAll(Arrays.asList(titleSprit));
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titleList);
        voteList.setAdapter(adapter2);

        //アダプター
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, titleList);
        voteList.setAdapter(adapter);

        // 検索の処理
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

        // 投票作成
        voteCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(createPage);
            }
        });

        // 投票ページへ移動
        voteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //リストクリック処理

                votePage.putExtra("selectedTitle", i);
                startActivity(votePage);
            }
        });

        // プロフィールへ移動
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toProfile = new Intent();
                toProfile.setClass(MainActivity.this, SelfPage.class);
                startActivity(toProfile);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView todoListView = findViewById(R.id.voteList);
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titleList);
        todoListView.setAdapter(adapter2);
    }
}