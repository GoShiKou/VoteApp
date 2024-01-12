package jp.ac.ecc.se.voteapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VotePage extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votepage);
        TextView title = findViewById(R.id.taitoru);
        Button koment = findViewById(R.id.Comment);
        Button back = findViewById(R.id.back);
        ListView sentaku = findViewById(R.id.sentakusi);
        ImageView gazou = findViewById(R.id.gazou);

        Intent intent = new Intent(this, Comment.class);


        //前のページに戻る
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //コメントページ
        koment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        //titleを持ってくる






    }
}