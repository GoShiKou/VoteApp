package jp.ac.ecc.se.voteapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.net.Uri;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;




public class VotePage extends AppCompatActivity {
    TextView showTitle;
    ImageView showImage;
    ImageView notH;
    ImageView notP;
    Button delete;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Uri imageUri;
    boolean hasVoted = false;

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votepage);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();

        Button koment = findViewById(R.id.Comment);
        Button back = findViewById(R.id.back);
        ListView sentaku = findViewById(R.id.sentakusi);
        showTitle = findViewById(R.id.taitoru);
        showImage = findViewById(R.id.imageView);

        notH = findViewById(R.id.PtoH);
        notP = findViewById(R.id.myP);

        Intent intentmain = new Intent(this, MainActivity.class);
        Intent coment = new Intent(this, Comment.class);
        Intent intentP = new Intent(this, SelfPage.class);

        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> uriArray = new ArrayList<>();

        Intent intent = getIntent();

        int selectTitle = intent.getIntExtra("selectedTitle", -1);
        String list = MainActivity.titleList.get(selectTitle);

        String titleString = pref.getString(list + "title", "");
        String voteString = pref.getString(list + "vote", "");
        String uriString = pref.getString(list + "uri", "");
        Uri Imageuri = Uri.parse(uriString);
        showTitle.setText(titleString);
        showImage.setImageURI(Imageuri);
        ArrayList<String> retrivedlist = getDataFromSharedPreferences();

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,retrivedlist);
        sentaku.setAdapter(adapter);

        sentaku.setOnItemClickListener((parent, view, position, id) -> {
            // 例えば、選択された項目のテキストを取得
            String selectedOption = retrivedlist.get(position);

            if (!hasVoted) {
                // ここに投票の処理を追加
                // 例えば、選択された項目に対する投票を記録するなど

                // 投票回数を取得し、1回増やす
                int voteCount = pref.getInt(selectedOption, 0) + 1;

                // 投票回数をSharedPreferencesに保存
                editor.putInt(selectedOption, voteCount);
                editor.apply();

                // 投票回数を表示するためにListViewを更新
                retrivedlist.set(position, selectedOption +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                        "  票数: " + voteCount);
                adapter.notifyDataSetChanged();

                setHasVoted(true);
            }
        });

        //前のページに戻る
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                startActivity(intentmain);
            }
        });
        //コメントページ
        koment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent commentIntent = new Intent(VotePage.this, Comment.class);

                // Get the selected title and image URI
//                String selectedTitle = showTitle.getText().toString();
//                String selectedImageUri = uriArray.get(selectTitle);

                // Pass title and image URI to CommentPage
               // Intent intent = getIntent();

//                int selectTitle = intent.getIntExtra("selectedTitle", -1);
//                commentIntent.putExtra("selectTitle", selectTitle);

               // startActivity(commentIntent);


                        // Pass the selected title position
                        commentIntent.putExtra("selectedTitle", selectTitle);

                        startActivity(commentIntent);
                    }
                });


        notH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentmain);
            }
        });
        notP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentP);
            }
        });
        delete= findViewById(R.id.Delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove(MainActivity.titleList.get(selectTitle)+"title");
                editor.remove(MainActivity.titleList.get(selectTitle)+"uri");
                editor.remove(MainActivity.titleList.get(selectTitle)+"listData");
                editor.remove(MainActivity.titleList.remove(selectTitle));
                String titleString = String.join(",",MainActivity.titleList);
                editor.putString("list",titleString);
                editor.apply();
                finish();
            }
        });
    }

    private ArrayList<String> getDataFromSharedPreferences() {
        String title = showTitle.getText().toString();
        SharedPreferences preferences = getSharedPreferences(title + "listData",MODE_PRIVATE);
        Set<String> detaSet = preferences.getStringSet(title + "listData",new HashSet<>());

        return new ArrayList<>(detaSet);
    }
    @Override
    protected void onResume() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        super.onResume();
        Intent intent = getIntent();
        int selectTitle = intent.getIntExtra("selectedTitle",-1);
        String list = MainActivity.titleList.get(selectTitle);

        String titleTxt = pref.getString(list + "title","");
        String uri = pref.getString(list + "uri","");
        Uri Imageuri = Uri.parse(uri);
        showTitle.setText(titleTxt);
        showImage.setImageURI(Imageuri);
    }
}