package jp.ac.ecc.se.voteapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.net.Uri;
import java.util.ArrayList;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;




public class VotePage extends AppCompatActivity {
    TextView showTitle;
    ImageView showImage;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Uri imageUri;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votepage);

        Button koment = findViewById(R.id.Comment);
        Button back = findViewById(R.id.back);
        ListView sentaku = findViewById(R.id.sentakusi);



        pref = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intentcmt = new Intent(this, Comment.class);
        Intent intentmain = new Intent(this, MainActivity.class);
        Intent intent = getIntent();

        showTitle = findViewById(R.id.taitoru);
        String title = intent.getStringExtra("title");
        showTitle.setText(title);

//        showImage = findViewById(R.id.imageView);
//        String image = intent.getStringExtra("image");
//        Uri imageUri= Uri.parse(image);
//        showImage.setImageURI(imageUri);


        //Intent intent = new Intent(this, Comment.class);

        //前のページに戻る
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                startActivity(intentmain);

            }
        });
        //コメントページ
        koment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentcmt);
            }
        });

    }

    private void removeTodoItem(SharedPreferences pref, SharedPreferences.Editor editor, int position) {

        String str_titles = pref.getString("title", "");
        String[] list = str_titles.split(",");
        ArrayList<String> titleList = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            titleList.add(list[i]);

        }

        String pref_images = pref.getString("image","");
        String[]imageList = pref_images.split(",");
        ArrayList<String>imagelist=new ArrayList<>();
        for(int i=0;i<imageList.length;i++){
            imagelist.add(imageList[i]);
        }
        if (position < titleList.size()) {
            editor.remove("title");
            editor.remove("image");


            editor.putString("title", arrayToString(titleList));
            editor.putString("image",arrayToString(imagelist));
            editor.apply();

        }
    }


    private String arrayToString(ArrayList<String> title) {
        StringBuilder sb = new StringBuilder();
        for (String s : title) {
            sb.append(s).append(",");
        }
        return sb.toString().replaceAll("$", "");
    }
}