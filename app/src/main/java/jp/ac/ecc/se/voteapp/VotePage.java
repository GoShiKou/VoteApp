package jp.ac.ecc.se.voteapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VotePage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votepage);


        TextView title= findViewById(R.id.taitoru);
        TextView naiyou = findViewById(R.id.naiyo);
        Button kakutei = findViewById(R.id.kakutei);
        Button  back = findViewById(R.id.back);
        RecyclerView sentakusi = findViewById(R.id.sentakusi);
    }
























}
