package jp.ac.ecc.se.voteapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.net.Uri;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class VotePage extends AppCompatActivity {
    TextView showTitle;
    ImageView showImage;
    ImageView notH;
    ImageView notP;
    ImageView XImage;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Uri imageUri;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votepage);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();

        String titleString = pref.getString("title", null);
        String uriString = pref.getString("image", null);

        Button koment = findViewById(R.id.Comment);
        Button back = findViewById(R.id.back);
        ListView sentaku = findViewById(R.id.sentakusi);
        showTitle = findViewById(R.id.taitoru);
        showImage = findViewById(R.id.imageView);

        notH = findViewById(R.id.notH);
        notP = findViewById(R.id.notP);

        Intent intentmain = new Intent(this, MainActivity.class);
        Intent coment = new Intent(this, Comment.class);
        Intent intentP = new Intent(this, SelfPage.class);

        Intent intent = getIntent();
        int selectTitle = intent.getIntExtra("selectedTitle", -1);

        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> uriArray = new ArrayList<>();

        if (titleString != null && uriString != null) {
            if (!titleString.isEmpty() && !uriString.isEmpty()) {
                String[] titleSplit = titleString.split(",");
                String[] uriArraySplit = uriString.split(",");

                for (int i = 0; i < titleSplit.length; i++) {
                    titleList.add(titleSplit[i]);
                }
                for (int i = 0; i < uriArraySplit.length; i++) {
                    uriArray.add(uriArraySplit[i]);
                }

                showTitle.setText(titleSplit[selectTitle]);

                if (uriArraySplit[selectTitle] != null && !uriArraySplit[selectTitle].isEmpty()) {
                    showImage.setImageURI(Uri.parse(uriArraySplit[selectTitle]));
                }

            }
        }

        ArrayList<String> retrivedlist = getDataFromSharedPreferences();

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,retrivedlist);
        sentaku.setAdapter(adapter);


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
                Intent commentIntent = new Intent(VotePage.this, Comment.class);
//
        // Get the selected title and image URI
        String selectedTitle = showTitle.getText().toString();
        String selectedImageUri = uriArray.get(selectTitle);

        // Pass title and image URI to CommentPage
        commentIntent.putExtra("title", selectedTitle);
        commentIntent.putExtra("image", selectedImageUri);

        startActivity(commentIntent);
        }
        });
        
        notH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentmain);
            }
        });
        notP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentP);
            }
        });
        XImage= findViewById(R.id.XImage);
        // VotePageからMainPageに戻る
        Intent Mainintent = new Intent(VotePage.this, MainActivity.class);
        XImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleList.remove(selectTitle);
                uriArray.remove(selectTitle);
                retrivedlist.remove(selectTitle);
                editor.apply();

                String titleString = String.join(",",titleList);
                editor.putString("title",titleString);
                //editor.apply();

                String uriString = String.join(",",uriArray);
                editor.putString("image", uriString);
                editor.apply();

                String listString = String.join(",",retrivedlist);
                editor.putString("list",listString);
                editor.apply();



                finish(); // VotePageを終了

                //startActivity(Mainintent);

//                retrivedlist.clear();
//                adapter.notifyDataSetChanged();
//                sentaku.setVisibility(View.GONE);
//                showTitle.setVisibility(View.GONE);
//                showImage.setVisibility(View.GONE);

            }
        });



    }

    private ArrayList<String> getDataFromSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("listData",MODE_PRIVATE);
        Set<String> detaSet = preferences.getStringSet("listData",new HashSet<>());


        return new ArrayList<>(detaSet);
    }
//    private void removeTodoItem(SharedPreferences pref, SharedPreferences.Editor editor, int position) {
//
//        String str_titles = pref.getString("title", "");
//        String[] list = str_titles.split(",");
//
//        ArrayList<String> titleList = new ArrayList<>();
//        for (int i = 0; i < list.length; i++) {
//            titleList.add(list[i]);
//        }
//
//        String pref_images = pref.getString("image","");
//        String[]imageList = pref_images.split(",");
//        ArrayList<String>imagelist=new ArrayList<>();
//        for(int i=0;i<imageList.length;i++){
//            imagelist.add(imageList[i]);
//        }
//        if (position < titleList.size()) {
//            editor.remove("title");
//            editor.remove("image");
//
//
//            editor.putString("title", arrayToString(titleList));
//            editor.putString("image",arrayToString(imagelist));
//            editor.apply();
//
//        }
//    }
//
//
//    private String arrayToString(ArrayList<String> title) {
//        StringBuilder sb = new StringBuilder();
//        for (String s : title) {
//            sb.append(s).append(",");
//        }
//        return sb.toString().replaceAll("$", "");
//    }
}