package jp.ac.ecc.se.voteapp;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.provider.MediaStore;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.net.Uri;

public class CreatePage extends AppCompatActivity {
    final int CAMERA_RESULT = 100;
    public Uri image;
    Uri imageUri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //requestCode == このアプリから読んだカメラの戻り＆＆resultCode == カメラの処理が正常終了
        if (requestCode == CAMERA_RESULT && resultCode == RESULT_OK) {
            //カメラからの戻り引数"Data"にある画像データを取り戻す
//          Bitmap bitmap = data.getParcelableExtra("data");
            //画面上のパーツImageViewを変数化
            ImageView cameraImage = findViewById(R.id.image);
            //受け取った画像データをセット
            //cameraImage.setImageBitmap(bitmap);
            cameraImage.setImageURI(imageUri);

        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createpage);

        ArrayList<String> datalist = new ArrayList<>();
        //
        ListView ListView= findViewById(R.id.list);
        EditText choice = findViewById(R.id.editText);
        Button AddButton = findViewById(R.id.add);
        Button Button2 = findViewById(R.id.button);
        Button Camera = findViewById(R.id.camera);

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!choice.getText().toString().isEmpty()) {
                    datalist.add(choice.getText().toString());
                    choice.setText("");
                }
            }
        });
        Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //変数名を作成
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String fileName = "Traning_" + timestamp + "_.jpg";
                //ファイル情報のための変数
                ContentValues values = new ContentValues();
                //ファイル名をセット
                values.put(MediaStore.Images.Media.TITLE, fileName);
                //ファイル形式を設定
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                //保存画像情報の URI を生成する
                imageUri =
                        getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                //カメラアプリに送る準備
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                //カメラアプリ起動　戻りあたいあり
                startActivityForResult(intent,CAMERA_RESULT);
            }
        });

    }
}
