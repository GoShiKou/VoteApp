package jp.ac.ecc.se.voteapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import jp.ac.ecc.se.voteapp.R;

public class Comment extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        TextView CommentView = findViewById(R.id.CommentView);
        ImageButton EmojiButton = findViewById(R.id.EmojiButton);
        Button Back2 = findViewById(R.id.Back2);
        ImageButton CommentButton = findViewById(R.id.CommentButton);
        TextView MyCommentView = findViewById(R.id.MyCommentView);
        TextView UserName = findViewById(R.id.UserName);
        ImageView Profileimg = findViewById(R.id.Profileimg);
        TextView EmojiNumber = findViewById(R.id.EmojiNumber);
        TextView CommentNumber = findViewById(R.id.CommentNUmber);

        // CommentButtonが押されたときの処理を追加
        CommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ダイアログのビルダーを作成
                AlertDialog.Builder builder = new AlertDialog.Builder(Comment.this);
                builder.setTitle("コメントを入力");

                // レイアウトにEditTextを追加（必要に応じて他のViewも追加可能）
                final EditText input = new EditText(Comment.this);
                builder.setView(input);

                // OKボタンが押されたときの処理
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 入力されたコメントを取得
                        String comment = input.getText().toString();
                        // ここでコメントを使って何かしらの処理を行う
                        // コメントをMyCommentViewに設定
                        MyCommentView.setText(comment);

                        // ダイアログを閉じる
                        dialog.dismiss();
                    }
                });

                // キャンセルボタンが押されたときの処理
                builder.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ダイアログを閉じる
                        dialog.cancel();
                    }
                });
                // ダイアログを表示
                builder.show();
            }
        });

        Back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // "VotePageActivity" に遷移
                Intent intent = new Intent(Comment.this, VotePage.class);
                startActivity(intent);
            }
        });

        // EmojiButtonのクリックリスナーの設定
        EmojiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRandomEmojis();
            }
        });
    }

    private void showRandomEmojis() {
        // 絵文字のリスト
        String[] emojis = {"😀", "😍", "🎉", "👍", "🌈", "🐱"};

        // ランダムな数の絵文字を選択
        Random random = new Random();
        int randomCount = random.nextInt(emojis.length) + 1;
        StringBuilder selectedEmojis = new StringBuilder();

        for (int i = 0; i < randomCount; i++) {
            int randomIndex = random.nextInt(emojis.length);
            selectedEmojis.append(emojis[randomIndex]).append(" ");
        }

        // 絵文字を表示するTextViewに設定
        TextView CommentView = findViewById(R.id.EmojiNumber);
        CommentView.setText("Number of Emojis: " + randomCount + "\n" + selectedEmojis.toString());
    }
}
