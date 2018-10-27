package ru.skblab.myfirstapplication;

import android.os.PatternMatcher;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AuthActivity extends AppCompatActivity {
    private EditText mLogin;
    private EditText mPassword;
    private Button mEnter;
    private Button mRegister;

    private View.OnClickListener mOnClicEnterkListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isEmailValid() && isPasswordValid()){
                //
            }
            else showMessage(R.string.login_input_error);
        }
    };

    private View.OnClickListener mOnClickRegisterListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_auth);
        mLogin = findViewById(R.id.etLogin);
        mPassword = findViewById(R.id.etPassword);
        mEnter = findViewById(R.id.buttonEnter);
        mRegister = findViewById(R.id.buttonRegister);

        mEnter.setOnClickListener(mOnClicEnterkListener);
        mRegister.setOnClickListener(mOnClickRegisterListener);
    }

    private boolean isEmailValid (){
        return !TextUtils.isEmpty(mLogin.getText())
                && Patterns.EMAIL_ADDRESS.matcher(mLogin.getText()).matches();
    }

    private boolean isPasswordValid (){
        return !TextUtils.isEmpty(mPassword.getText());
    }

    private void showMessage (@StringRes int string){
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }
}
