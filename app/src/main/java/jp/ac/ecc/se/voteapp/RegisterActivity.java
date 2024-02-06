package jp.ac.ecc.se.voteapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText signupName, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupBtn;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    ProgressBar progressBar;
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupBtn = findViewById(R.id.signup_btn);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String createName, createEmail, createPassword;
                createName = signupName.getText().toString();
                createEmail = signupEmail.getText().toString();
                createPassword = signupPassword.getText().toString();

                if (TextUtils.isEmpty(createEmail)) {
                    Toast.makeText(RegisterActivity.this,"メールを入力してください",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(createPassword)) {
                    Toast.makeText(RegisterActivity.this,"パスワードを正しく入力してください",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                mAuth.createUserWithEmailAndPassword(createEmail, createPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // ユーザーのUIDをゲット
                                    String uid = mAuth.getCurrentUser().getUid();
                                    // 入力されたユーザーネームをゲット
                                    String name = signupName.getText().toString().trim();

                                    // ユーザーデータをFirebaseに保存
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
                                    databaseReference.child(uid).child("name").setValue(name);

                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this,"アカウント登録しました",Toast.LENGTH_SHORT).show();

                                    signupName.setText("");
                                    signupEmail.setText("");
                                    signupPassword.setText("");
                                } else {
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this,"アカウント登録失敗",Toast.LENGTH_SHORT).show();
                                }

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        });
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}