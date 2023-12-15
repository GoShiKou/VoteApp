package jp.ac.ecc.se.voteapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class Selfpage extends AppCompatActivity {

    List<Post> postList = createSamplePosts();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfpage);


        RecyclerView post = findViewById(R.id.selfVote);
        ImageView img = findViewById(R.id.selfie);
        TextView text = findViewById(R.id.selfInfo);
        Button back = findViewById(R.id.backBtn);


        // starting profile data
        int profileImageResId = R.drawable.profile_img; // switch into your img
        String introduction = "Hello, I'm an Android developer!"; // switch into your intro
        List<Post> postList = createSamplePosts(); // create sample post

        // set img and intro
        img.setImageResource(profileImageResId);
        text.setText(introduction);

        // starting Adapter and LayoutManager
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        // set Adapter and LayoutManager
        post.setAdapter(post.getAdapter());
        post.setLayoutManager(layoutManager);

        // back to previous page
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // create sample post data
    private List<Post> createSamplePosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(R.drawable.profile_img, "User1", "This is my first post."));
        posts.add(new Post(R.drawable.profile_img, "User2", "Just sharing some thoughts."));
        // add more post
        return posts;
    }


}


 class Post {
    private int avatarResId;
    private String username;
    private String postContent;

    public Post(int avatarResId, String username, String postContent) {
        this.avatarResId = avatarResId;
        this.username = username;
        this.postContent = postContent;
    }

    public int getAvatarResId() {
        return avatarResId;
    }

    public String getUsername() {
        return username;
    }

    public String getPostContent() {
        return postContent;
    }
}

