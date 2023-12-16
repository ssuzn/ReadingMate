package com.example.readingmate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Dialog;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUserId, etPassword, etEmail;
    private SignupPreferenceManager preferenceManager;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        preferenceManager = new SignupPreferenceManager(this);

        etUserId = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etEmail = findViewById(R.id.email);

        Button btnSignup = findViewById(R.id.btn_register);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = etUserId.getText().toString();
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();

                // 회원가입 정보 저장
                preferenceManager.saveUserInfo(userId, password, email);

                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                dialog = builder.setMessage("회원가입에 성공하셨습니다.")
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }
                        })
                        .create();
                dialog.show();

//                // 회원가입 완료 후 로그인 화면으로 이동
//                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish(); // 현재 액티비티 종료
            }
        });

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 로그인 화면으로 이동
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}