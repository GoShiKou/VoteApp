package jp.ac.ecc.se.voteapp;

import static android.text.method.TextKeyListener.clear;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static jp.ac.ecc.se.voteapp.MainActivity.titleList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelfPage extends AppCompatActivity {
    ArrayList<String> titlelist = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView selfVote;
    SharedPreferences pref;
    //
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        String str_title = pref.getString("title", "");
//        if (str_title != null && !str_title.equals("")) {
//            String[] List = str_title.split(",");
//            titlelist.addAll(Arrays.asList(List));
//        }
//    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfpage);

        selfVote = findViewById(R.id.selfvote);
        ImageView img = findViewById(R.id.selfie);
        TextView text = findViewById(R.id.selfInfo);
        ImageView homePG = findViewById(R.id.PtoH);
        Button login = findViewById(R.id.loginButton);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            // User is logged in
            login.setText("ログアウト"); // Change button text to "Logout"
        } else {
            // User is not logged in
            login.setText("ログイン"); // Keep button text as "Login"
        }

        // Login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (auth.getCurrentUser() != null) {
                    // User is logged in, perform logout
                    FirebaseAuth.getInstance().signOut();
                    // Redirect to login page or perform any necessary action after logout
                    Intent intent = new Intent(SelfPage.this, SelfPage.class);
                    startActivity(intent);
                    finish(); // Close current activity after logout
                } else {
                    // User is not logged in, redirect to login page
                    Intent intent = new Intent(SelfPage.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        String uid = null;
        if (auth.getCurrentUser() != null) {
            // User is logged in
            uid = auth.getCurrentUser().getUid();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(uid);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String userName = snapshot.child("name").getValue(String.class);

                        text.setText(userName);
                    } else {
                        Log.d(TAG, "User data does not exist");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            // User is not logged in
            // ここでは例えばログイン画面に遷移するなど
        }

        Intent vtpg = new Intent(this, VotePage.class);
//
        // starting profile data
        int profileImageResId = R.drawable.profile_img; // switch into your img
        String introduction = "Hello, I'm an Android developer!"; // switch into your intro
        List<Post> postList = createSamplePosts(); // create sample post
//
        // set img and intro
        img.setImageResource(profileImageResId);
        text.setText(introduction);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        if(!pref.getString("list","").isEmpty()) {
            String[] titleSprit = pref.getString("list", "").split(",");
            String[] index = new String[titleSprit.length];
            for (int i = 0; i <titleSprit.length; i++){
                index[i] = pref.getString(titleSprit[i]+"title","");
            }
            titlelist.addAll(Arrays.asList(index));
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, titlelist);
        selfVote.setAdapter(adapter);

//
        selfVote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                //リストクリック処理

                vtpg.putExtra("selectedTitle", i);

                startActivity(vtpg);

            }
        });
//
        // back to previous page
        homePG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    //
//    // create sample post data
    private List<Post> createSamplePosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(R.drawable.profile_img, "User1", "This is my first post."));
        posts.add(new Post(R.drawable.profile_img, "User2", "Just sharing some thoughts."));
        // add more post
        return posts;
    }
}
//
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

