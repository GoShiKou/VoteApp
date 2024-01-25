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
import java.util.HashSet;
import java.util.Set;

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
    EditText taitoru;
    Button AddButton;
    ListView ListView;
    private EditText choice;
    private ArrayList<String> datalist;
    private ArrayAdapter<String> adapter;

    final int CAMERA_RESULT = 100;
    public Uri image;
    Uri imageUri;
    private void saveDataToSharedPreferences(ArrayList<String> dataList) {
        String title = taitoru.getText().toString();
        // SharedPreferencesにデータを保存
        SharedPreferences preferences = getSharedPreferences(title + "listData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // リストのデータを保存
        Set<String> dataSet = new HashSet<>(dataList);
        editor.putStringSet(title + "listData", dataSet);

        // 変更を保存
        editor.apply();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //requestCode == このアプリから読んだカメラの戻り＆＆resultCode == カメラの処理が正常終了
        if (requestCode == CAMERA_RESULT && resultCode == RESULT_OK) {
            //カメラからの戻り引数"Data"にある画像データを取り戻す
//            Bitmap bitmap = data.getParcelableExtra("data");
            //画面上のパーツImageViewを変数化
            ImageView cameraImage = findViewById(R.id.image);
            //受け取った画像データをセット
            //cameraImage.setImageBitmap(bitmap);
            cameraImage.setImageURI(imageUri);
        }
    }

    public void addItem(){
        choice = findViewById(R.id.editText);
        String newItem = choice.getText().toString().trim();

        if (!newItem.isEmpty()) {
            datalist.add(newItem);
            adapter.notifyDataSetChanged();
            choice.setText("");
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createpage);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        Button camera = findViewById(R.id.camera);
        Button post = findViewById(R.id.button);
        taitoru = findViewById(R.id.taitoru);
        ImageView imageView = findViewById(R.id.image);
        ListView = findViewById(R.id.list);

        Intent intent = getIntent();
        int selectTitle = intent.getIntExtra("selectedTitle", -1);

        if (selectTitle != -1) {
            String list = MainActivity.titleList.get(selectTitle);
            String title = pref.getString(list + "title", "");
            String uri = pref.getString(list + "uri", "");
            Uri ImageUri =Uri.parse(uri);
            taitoru.setText(title);
            imageView.setImageURI(ImageUri);
        }

        //投稿button
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();
                String title = taitoru.getText().toString();
//                String cont = editCon.getText().toString();
                if (!taitoru.getText().toString().isEmpty()) {
                    if (selectTitle == -1) {
                        MainActivity.titleList.add(taitoru.getText().toString());
                    }
                    editor.putString(title + "title", title);
                    if (datalist != null) {
                        editor.putString(title + "vote", datalist.toString());
                    }
                    if (imageUri != null) {
                        editor.putString(title + "uri", imageUri.toString());
                    }
                    String titleString = String.join(",", MainActivity.titleList);
                    editor.putString("list", titleString);
                    editor.apply();
                    finish();
                    // SharedPreferencesにデータを保存
                    saveDataToSharedPreferences(datalist);
                } else {
                    Toast.makeText(getApplicationContext(), "タイトルを入力してください", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AddButton = findViewById(R.id.addButton);
        datalist = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,datalist);
        ListView.setAdapter(adapter);
        //listに追加
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        // cameraButton
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //変数名を作っています。
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String fileName = "Traning_" + timestamp + "_.jpg";
                //ファイル情報ための変数
                ContentValues values = new ContentValues();
                //ファイル名を設定
                values.put(MediaStore.Images.Media.TITLE, fileName);//情報を送る
                //ファイル影試設定
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                //そのファイル情報をuriに設定
                imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

                //gカメラアプリ起動　戻り道あり
                startActivityForResult(intent, CAMERA_RESULT);
            }
        });

        ImageView home = findViewById(R.id.homeI);
        ImageView profile = findViewById(R.id.profileImageI);
        Intent goHome = new Intent(this, MainActivity.class);
        Intent goProfile = new Intent(this, SelfPage.class);

        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                startActivity(goHome);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goHome);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goProfile);
            }
        });
    }
}
