package ru.skblab.myfirstapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AuthFragment extends Fragment {
    private EditText mLogin;
    private EditText mPassword;
    private Button mEnter;
    private Button mRegister;

    public static AuthFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    private View.OnClickListener mOnClickEnterListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isEmailValid() && isPasswordValid()){
                Intent startProfileIntent =
                        new Intent(getActivity(), ProfileActivity.class);
                startProfileIntent.putExtra(ProfileActivity.USER_KEY,
                        new User(mLogin.getText().toString(),mPassword.getText().toString()));

                startActivity(startProfileIntent);
            }
            else showMessage(R.string.login_error);
        }
    };

    private View.OnClickListener mOnClickRegisterListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, RegistrationFragment.newInstance())
                    .commit();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_auth, container, false);

        mLogin = v.findViewById(R.id.etLogin);
        mPassword = v.findViewById(R.id.etPassword);
        mEnter = v.findViewById(R.id.buttonEnter);
        mRegister = v.findViewById(R.id.buttonRegister);

        mEnter.setOnClickListener(mOnClickEnterListener);
        mRegister.setOnClickListener(mOnClickRegisterListener);
        return v;
    }

    private boolean isEmailValid (){
        return !TextUtils.isEmpty(mLogin.getText())
                && Patterns.EMAIL_ADDRESS.matcher(mLogin.getText()).matches();
    }

    private boolean isPasswordValid (){
        return !TextUtils.isEmpty(mPassword.getText());
    }

    private void showMessage (@StringRes int string){
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }

    
}
