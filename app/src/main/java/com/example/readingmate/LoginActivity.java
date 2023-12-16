package com.example.readingmate;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUserId, etPassword;
    private SignupPreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferenceManager = new SignupPreferenceManager(this);

        etUserId = findViewById(R.id.log_id);
        etPassword = findViewById(R.id.log_password);
        etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = etUserId.getText().toString();
                String password = etPassword.getText().toString();

                // 저장된 회원 정보 확인
                String savedUserId = preferenceManager.getUserId();
                String savedPassword = preferenceManager.getPassword();

                // 로그인 처리
                if (userId.equals(savedUserId) && password.equals(savedPassword)) {
                    // 로그인 성공
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("loginID", userId);
                    startActivity(intent);
                    finish(); // 현재 액티비티 종료
                } else {
                    // 로그인 실패
                    Toast.makeText(LoginActivity.this, "로그인 실패. 아이디 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
