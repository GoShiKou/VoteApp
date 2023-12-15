package jp.ac.ecc.se.voteapp;

import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.TextView;
        import android.widget.EditText;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

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
    }
}
