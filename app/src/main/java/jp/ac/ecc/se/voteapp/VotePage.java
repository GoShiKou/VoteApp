package jp.ac.ecc.se.voteapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class VotePage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votepage);
        EditText title= findViewById(R.id.taitoru);
        EditText naiyou = findViewById(R.id.naiyoiu);
        Button kakutei = findViewById(R.id.kakutei);
        Button  back = findViewById(R.id.back);
        RecyclerView sentakusi = findViewById(R.id.sentakusi);
    }


}
