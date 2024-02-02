//package jp.ac.ecc.se.voteapp;
//
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//public class Comment extends AppCompatActivity {
//
//    private ArrayList<String> commentList;
//    private ArrayAdapter<String> adapter;
//    private static final int REQUEST_CODE_CREATE_NOTE = 1;
//    ArrayList<String> titleList;
//    Uri imageUri;
//
//    SharedPreferences pref;
//    SharedPreferences.Editor editor;
//
//    int emojiButtonCount = 0;
//    int commentButtonCount = 0;
//
//    TextView commentTitle;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_comment);
//
//        pref = PreferenceManager.getDefaultSharedPreferences(this);
//        editor = pref.edit();
//
//        ImageView image = findViewById(R.id.Image3);
//        commentTitle = findViewById(R.id.commentTitle);
//        ImageButton EmojiButton = findViewById(R.id.EmojiButton);
//        Button Back2 = findViewById(R.id.Back2);
//        ImageButton CommentButton = findViewById(R.id.CommentButton);
//        ListView MyCommentView = findViewById(R.id.MyCommentView);
//        TextView EmojiNumber = findViewById(R.id.EmojiNumber);
//        TextView CommentNumber = findViewById(R.id.CommentNumber);
//
//        Intent intent = getIntent();
////        int selectTitle = intent.getIntExtra("selectedTitle", -1);
////        String list = MainActivity.titleList.get(selectTitle);
////        Intent intent = getIntent();
//        int selectTitle = intent.getIntExtra("selectedTitle", -1);
//        String titleString = pref.getString(MainActivity.titleList.get(selectTitle) + "title", "");
//        String uriString = pref.getString(MainActivity.titleList.get(selectTitle) + "uri", "");
//        Uri Imageuri = Uri.parse(uriString);
//        commentTitle.setText(titleString);
//        image.setImageURI(Imageuri);
//
//        commentList = new ArrayList<>();
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, commentList);
//        MyCommentView.setAdapter(adapter);
//
//
//
////        String titleString = pref.getString(list + "title", "");
////        String uriString = pref.getString(list + "uri", "");
////        Uri Imageuri = Uri.parse(uriString);
//
////        String title = intent.getStringExtra("title");
////        commentTitle.setText(title);
//
////        commentList = new ArrayList<>();
////        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, commentList);
//
////        commentTitle.setText(titleString);
////        image.setImageURI(Imageuri);
//
//        CommentButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(Comment.this);
//                builder.setTitle("コメントを入力");
//
//                final EditText input = new EditText(Comment.this);
//                builder.setView(input);
//
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String comment = input.getText().toString().trim();
//
//                        if (!comment.isEmpty()) {
//                            commentList.add(comment);
//                            commentButtonCount++;
//                            updateCommentButtonCount();
//                            adapter.notifyDataSetChanged();
//                            MyCommentView.setAdapter(adapter);
//                            saveCommentsToStorage(commentList);
//
//                        }
//
//                        dialog.dismiss();
//                    }
//                });
//
//                builder.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//
//                builder.show();
//            }
//        });
//
//        Back2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Comment.this, VotePage.class);
////                startActivity(intent);
//                 finish();
//            }
//        });
//
//        EmojiButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                emojiButtonCount++;
//                updateEmojiButtonCount();
//            }
//        });
//    }
//
//    private void saveCommentsToStorage(ArrayList<String> comments) {
//        SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
//        editor.putString("commentList", TextUtils.join(",", comments));
//        editor.apply();
//    }
//
//    private void updateEmojiButtonCount() {
//        TextView emojiNumberView = findViewById(R.id.EmojiNumber);
//        emojiNumberView.setText("Like " + emojiButtonCount);
//    }
//
//    private void updateCommentButtonCount() {
//        TextView commentNumberView = findViewById(R.id.CommentNumber);
//        commentNumberView.setText(" Comments " + commentButtonCount);
//    }
//}
package jp.ac.ecc.se.voteapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class Comment extends AppCompatActivity {

    private ArrayList<String> commentList;
    private ArrayAdapter<String> adapter;
    int emojiButtonCount = 0;
    //boolean hasPressedEmojiButton = false;
    boolean isEmojiClicked = false;
    int commentButtonCount = 0;

    TextView commentTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);



        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        commentList = new ArrayList<>();
        commentTitle = findViewById(R.id.commentTitle);
        ImageButton EmojiButton = findViewById(R.id.EmojiButton);
        Button Back2 = findViewById(R.id.Back2);
        ImageButton CommentButton = findViewById(R.id.CommentButton);
        ListView MyCommentView = findViewById(R.id.MyCommentView);
        TextView EmojiNumber = findViewById(R.id.EmojiNumber);
        TextView CommentNumber = findViewById(R.id.CommentNumber);
        ImageView image3 = findViewById(R.id.Image3);

        Intent intent = getIntent();
        int selectTitle = intent.getIntExtra("selectedTitle", -1);
        String titleString = pref.getString(MainActivity.titleList.get(selectTitle) + "title", "");
        String uriString = pref.getString(MainActivity.titleList.get(selectTitle) + "uri", "");
        Uri Imageuri = Uri.parse(uriString);
        commentTitle.setText(titleString);
        image3.setImageURI(Imageuri);


        // Load comments from SharedPreferences and display them
        String title = MainActivity.titleList.get(selectTitle);
        if (!pref.getString(title +"commentList", "").isEmpty()) {
            loadCommentsFromStorage();
        }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, commentList);
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
                            if (adapter != null) {
                                adapter.notifyDataSetChanged();
                            }
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
                finish();
            }
        });


        EmojiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEmojiClicked) {
                    // Emoji is not clicked (like)
                    emojiButtonCount++;
                } else {
                    // Emoji is already clicked (unlike)
                    emojiButtonCount--;
                }

                // Toggle the state
                isEmojiClicked = !isEmojiClicked;

                updateEmojiButtonCount();
            }
        });
    }
//
//    private void saveCommentsToStorage(ArrayList<String> comments) {
//        Intent intent = getIntent();
//        int selectTitle = intent.getIntExtra("selectedTitle", -1);
//        String title = MainActivity.titleList.get(selectTitle);
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putString(title + "commentList", TextUtils.join(",", comments));
//        editor.putInt(title + "emojiButtonCount", emojiButtonCount);
//        editor.putBoolean(title + "isEmojiClicked", isEmojiClicked);
//        editor.apply();
//    }
//
//
//
//
//private void loadCommentsFromStorage() {
//    Intent intent = getIntent();
//    int selectTitle = intent.getIntExtra("selectedTitle", -1);
//    String title = MainActivity.titleList.get(selectTitle);
//    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
//
//    String commentsString = pref.getString(title + "commentList", "");
//    String[] commentsArray = commentsString.split(",");
//    commentList.addAll(Arrays.asList(commentsArray));
//
//    emojiButtonCount = pref.getInt(title + "emojiButtonCount", 0);
//    isEmojiClicked = pref.getBoolean(title + "isEmojiClicked", false);
//    updateEmojiButtonCount(); // Update the UI based on the loaded state
private void saveCommentsToStorage(ArrayList<String> comments) {
    Intent intent = getIntent();
    int selectTitle = intent.getIntExtra("selectedTitle", -1);
    String title = MainActivity.titleList.get(selectTitle);
    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
    SharedPreferences.Editor editor = pref.edit();
    editor.putString(title + "commentList", TextUtils.join(",", comments));
    editor.putInt(title + "commentButtonCount", commentButtonCount);
    editor.putInt(title + "emojiButtonCount", emojiButtonCount);
    editor.putBoolean(title + "isEmojiClicked", isEmojiClicked);
    editor.apply();
}

    private void loadCommentsFromStorage() {
        Intent intent = getIntent();
        int selectTitle = intent.getIntExtra("selectedTitle", -1);
        String title = MainActivity.titleList.get(selectTitle);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        String commentsString = pref.getString(title + "commentList", "");
        String[] commentsArray = commentsString.split(",");
        commentList.addAll(Arrays.asList(commentsArray));

        emojiButtonCount = pref.getInt(title + "emojiButtonCount", 0);
        commentButtonCount = pref.getInt(title + "commentButtonCount", 0);
        isEmojiClicked = pref.getBoolean(title + "isEmojiClicked", false);
        updateEmojiButtonCount(); // Update the UI based on the loaded state
        updateCommentButtonCount(); // Update the UI based on the loaded state
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
