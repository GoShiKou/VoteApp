package jp.ac.ecc.se.voteapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class Comment extends AppCompatActivity {

    private ArrayList<String> commentList;
    private ArrayAdapter<String> adapter;
    private static final int REQUEST_CODE_CREATE_NOTE = 1;
    ArrayList<String> titleList;
    Uri imageUri;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    int emojiButtonCount = 0;
    int commentButtonCount = 0;

    TextView commentTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        commentTitle = findViewById(R.id.commentTitle);
        ImageButton EmojiButton = findViewById(R.id.EmojiButton);
        Button Back2 = findViewById(R.id.Back2);
        ImageButton CommentButton = findViewById(R.id.CommentButton);
        ListView MyCommentView = findViewById(R.id.MyCommentView);
        TextView EmojiNumber = findViewById(R.id.EmojiNumber);
        TextView CommentNumber = findViewById(R.id.CommentNumber);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        commentTitle.setText(title);

        commentList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, commentList);

        // Load comments from storage
        loadCommentsFromStorage();

        // Set the adapter for the ListView
        MyCommentView.setAdapter(adapter);

        CommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Comment.this);
                builder.setTitle("コメントを入力");

                final EditText input = new EditText(Comment.this);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String comment = input.getText().toString().trim();

                        if (!comment.isEmpty()) {
                            commentList.add(comment);
                            commentButtonCount++;
                            updateCommentButtonCount();
                            adapter.notifyDataSetChanged();
                            MyCommentView.setAdapter(adapter);
                            saveCommentsToStorage(commentList);
                        }

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        Back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Comment.this, VotePage.class);
                startActivity(intent);
                // finish();
            }
        });

        EmojiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emojiButtonCount++;
                updateEmojiButtonCount();
            }
        });
    }

    private void saveCommentsToStorage(ArrayList<String> comments) {
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
        editor.putString("commentList", TextUtils.join(",", comments));
        editor.apply();
    }

    private void loadCommentsFromStorage() {
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String commentsString = prefs.getString("commentList", "");

        if (!TextUtils.isEmpty(commentsString)) {
            // Split the stored string into an array of comments
            String[] commentsArray = commentsString.split(",");

            // Add comments to the commentList
            commentList.addAll(Arrays.asList(commentsArray));

            // Update any UI components as needed
            commentButtonCount = commentList.size();
            updateCommentButtonCount();
        }
    }

    private void updateEmojiButtonCount() {
        TextView emojiNumberView = findViewById(R.id.EmojiNumber);
        emojiNumberView.setText("Like " + emojiButtonCount);
    }

    private void updateCommentButtonCount() {
        TextView commentNumberView = findViewById(R.id.CommentNumber);
        commentNumberView.setText(" Comments " + commentButtonCount);
    }
}
