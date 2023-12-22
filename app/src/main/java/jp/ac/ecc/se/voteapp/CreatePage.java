package jp.ac.ecc.se.voteapp;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.net.Uri;
import android.widget.Toast;

public class CreatePage extends AppCompatActivity {
    final int CAMERA_RESULT = 100;
    public Uri image;
    Uri imageUri;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createpage);



        EditText memo = findViewById(R.id.memo);





        Button Camera = findViewById(R.id.camera);


        Button post = findViewById(R.id.button);
        EditText taitoru = findViewById(R.id.taitoru);
        //投稿button
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteTitle = taitoru.getText().toString();
                // タイトルをMainActivityに送信
                Intent intent = new Intent();
                intent.putExtra("noteTitle", noteTitle);
                setResult(RESULT_OK, intent);
                finish(); // Activityを閉じる
            }
        });


        Button AddButton = findViewById(R.id.addButton);
        EditText choice = findViewById(R.id.editText);
        ListView ListView= findViewById(R.id.list);
        ArrayList<String> datalist = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,datalist);
        ListView.setAdapter(adapter);
        //listに追加
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!choice.getText().toString().isEmpty()) {
                    datalist.add(choice.getText().toString());
                    choice.setText("");
                }
            }
        });





















//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = pref.edit();
//        String str_titles=pref.getString("title", "");
//        ArrayList<String>tdTitleList=new ArrayList<>();
//        if(str_titles!=null&&!str_titles.equals("")){
//            String[] titleList=str_titles.split(",");
//            for(int i=0;i<titleList.length;i++){
//                tdTitleList.add(titleList[i]);
//            }
//        }
//        String pref_contents=pref.getString("content","");
//        ArrayList<String>contentlist=new ArrayList<>();
//        if(pref_contents!=null&&!pref_contents.equals("")){
//            String[]contentList =pref_contents.split(",");
//            for(int i=0;i<contentList.length;i++){
//                contentlist.add(contentList[i]);
//            }
//        }
//        String pref_images=pref.getString("image","");
//        ArrayList<String>imagelist= new ArrayList<>();
//        if(pref_images!=null&&!pref_images.equals("")){
//            String[]imageList=pref_images.split(",");
//            for(int i=0;i<imageList.length;i++){
//                imagelist.add(imageList[i]);
//            }
//        }
//        //保存ボタン
//        post.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(!taitoru.getText().toString().isEmpty()&&!memo.getText().toString().isEmpty()) {
//                    Intent intent_List = new Intent(CreatePage.this, MainActivity.class);
//
//                    String str_title = taitoru.getText().toString();
//                    tdTitleList.add(str_title);
//                    String str_titles = String.join(",", tdTitleList);
//                    editor.putString("title", str_titles);
//                    editor.apply();
//
//                    String str_content = memo.getText().toString();
//                    contentlist.add(str_content);
//                    String str_contents = String.join(",", contentlist);
//                    editor.putString("content", str_contents);
//                    editor.apply();
//
//                    String str_image;
//                    if (image != null) {
//                        str_image = image.toString();
//                    } else {
//                        str_image = "null";
//                    }
//                    imagelist.add(str_image);
//                    String str_images = String.join(",", imagelist);
//                    editor.putString("image", str_images);
//                    editor.apply();
//
//                    Toast.makeText(getApplicationContext(), "保存しました", Toast.LENGTH_SHORT).show();
//                    finish();
//                }else{
//                    Toast.makeText(getApplicationContext(), "タイトルとメモを入力してください", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//
//
//
//
//
//        //cameraButton
//        Camera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                //変数名を作成
//                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//                String fileName = "Traning_" + timestamp + "_.jpg";
//                //ファイル情報のための変数
//                ContentValues values = new ContentValues();
//                //ファイル名をセット
//                values.put(MediaStore.Images.Media.TITLE, fileName);
//                //ファイル形式を設定
//                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
//                //保存画像情報の URI を生成する
//                imageUri =
//                        getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//                //カメラアプリに送る準備
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                //カメラアプリ起動　戻りあたいあり
//                startActivityForResult(intent,CAMERA_RESULT);
//            }
//        });

        //ArrayList<String> datalist = new ArrayList<>();

        //listに追加
//        AddButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!choice.getText().toString().isEmpty()) {
//                    datalist.add(choice.getText().toString());
//                    choice.setText("");
//                }
//            }
//        });

    }
}
