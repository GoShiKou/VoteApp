package jp.ac.ecc.se.voteapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Selfpage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfpage);

        RecyclerView sns = findViewById(R.id.selfVote);
        ImageView img = findViewById(R.id.selfie);
        TextView text = findViewById(R.id.selfInfo);
        Button back = findViewById(R.id.backBtn);



    }
}


//public class Post {
//    private int avatarResId;
//    private String username;
//    private String postContent;
//
//    public Post(int avatarResId, String username, String postContent) {
//        this.avatarResId = avatarResId;
//        this.username = username;
//        this.postContent = postContent;
//    }
//
//    public int getAvatarResId() {
//        return avatarResId;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getPostContent() {
//        return postContent;
//    }
//}

