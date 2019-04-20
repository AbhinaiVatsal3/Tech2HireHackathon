package com.example.techolutionatm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    private boolean admin = false;
    private TextView roleTitle, switchRole;
    private EditText username, passEdit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_screen);
        username = findViewById(R.id.username);
        passEdit = findViewById(R.id.password);
        roleTitle = findViewById(R.id.roleTitle);
        switchRole = findViewById(R.id.switchRoles);
        roleTitle.setText(getResources().getString(R.string.userLogin));
        switchRole.setText(getResources().getString(R.string.switchToAdmin));
    }

    private void startMainActivity(boolean admin) {
        Intent loginIntent = new Intent(this, AdminMainPage.class) ;
        loginIntent.putExtra("Admin", admin);
        startActivity(loginIntent);
    }

    public void switchUser(View view) {
        if(!admin){
            switchRole.setText(getResources().getString(R.string.switchToUser));
            roleTitle.setText(getResources().getString(R.string.adminLogin));
            admin = true;
        } else {
            switchRole.setText(getResources().getString(R.string.switchToAdmin));
            roleTitle.setText(getResources().getString(R.string.userLogin));
            admin = false;
        }
    }

    public void login(View view) {
        String user_name = username.getText().toString();
        String password = passEdit.getText().toString();
        if(!user_name.isEmpty() && !password.isEmpty()){
            startMainActivity(admin);
            this.finish();
        } else {
            Toast.makeText(this,"User name or passward can\'t be empty", Toast.LENGTH_LONG);
        }
    }
}
