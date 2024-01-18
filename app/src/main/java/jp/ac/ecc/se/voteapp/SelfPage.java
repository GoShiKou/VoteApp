package jp.ac.ecc.se.voteapp;

import static android.text.method.TextKeyListener.clear;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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
        Button back = findViewById(R.id.backBtn);
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
        if(!pref.getString("title","").isEmpty()) {
            String[] titleSprit = pref.getString("title", "").split(",");
            titlelist.addAll(Arrays.asList(titleSprit));
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, titlelist);
        selfVote.setAdapter(adapter);

//
        selfVote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                String title =(String) adapterView.getItemAtPosition(i);
                vtpg.putExtra("title", title);
                String str_contents = pref.getString("content","");
                String[] memotList = str_contents.split(",");
                System.out.println("ListActivity.onItemClick:"+i);

                for(String s:memotList){
                    System.out.println("ListActivity.onItemClick:"+title+"_"+s);
                }

                for(int lp=0;lp< memotList.length;lp++){
                    System.out.printf("memotList[%d] : %s\n",lp,memotList[lp]);
                }
                startActivity(vtpg);
            }
        });
//
        // back to previous page
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

