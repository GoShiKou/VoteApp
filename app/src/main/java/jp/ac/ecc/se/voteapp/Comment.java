

package jp.ac.ecc.se.voteapp;

        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
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



public class Comment extends AppCompatActivity {

    private ArrayList<String> commentList;
    private ArrayAdapter<String> adapter;

    int emojiButtonCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        TextView CommentView = findViewById(R.id.CommentView);
        ImageButton EmojiButton = findViewById(R.id.EmojiButton);
        Button Back2 = findViewById(R.id.Back2);
        ImageButton CommentButton = findViewById(R.id.CommentButton);
        ListView MyCommentView = findViewById(R.id.MyCommentView);
        TextView UserName = findViewById(R.id.UserName);
        TextView EmojiNumber = findViewById(R.id.EmojiNumber);
        TextView CommentNumber = findViewById(R.id.CommentNumber);

        commentList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, commentList);

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
                        String comment = input.getText().toString();
                        commentList.add(comment);
                        adapter.notifyDataSetChanged(); // Notify the adapter that the data set has changed
                        MyCommentView.setAdapter(adapter);
                        // Dismiss the dialog
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
            }
        });

        EmojiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRandomEmojis();
                emojiButtonCount++;
                updateEmojiButtonCount();
            }
        });
    }

    private void showRandomEmojis() {
        // Implement logic to show random emojis
    }

    private void updateEmojiButtonCount() {
        TextView emojiNumberView = findViewById(R.id.EmojiNumber);
        emojiNumberView.setText(emojiButtonCount + "Emojis");

    }
}

