package org.pursuit.unit_03_assessment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.pursuit.unit_03_assessment.R;

public class LoginActivity extends AppCompatActivity {
    private static final String SHARED_PREF_KEY = "Shared Preferences";

    private EditText emailView;
    private EditText passwordView;
    private CheckBox usernameCheckbox;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailView = (EditText) findViewById(R.id.email_edittext);
        passwordView = (EditText) findViewById(R.id.password_edittext);
        usernameCheckbox = (CheckBox) findViewById(R.id.remember_username_checkbox);

        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        sharedPref = getSharedPreferences(SHARED_PREF_KEY, MODE_PRIVATE);
        if (sharedPref.contains("dummy_username") && sharedPref.contains("check_box_to_save_username")) {
            emailView.setText(sharedPref.getString("dummy_username", ""));
            usernameCheckbox.setChecked(true);
        }


        /*
        * TODO: add logic to set values to views:
        * TODO: 1. if there is a username value AND checkbox value in shared preferences - set the username EditText's value to the username value from shared preferences, and set the checkbox's value to the checkbox value from shared preferences

         */

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    private void attemptLogin() {

        emailView.setError(null);
        passwordView.setError(null);

        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
            cancel = true;
        } else {
            if (email.equals(String.valueOf(R.string.dummy_username)) && password.equals(String.valueOf(R.string.dummy_password))) {
                cancel = false;
                Intent intent = new Intent(getApplicationContext(), RecyclerActivity.class);
                startActivity(intent);
                if (usernameCheckbox.isChecked()) {
                    sharedPref.edit()
                            .putString("dummy_username", email)
                            .putString("dummy_password", password)
                            .apply();
                } else {
                    sharedPref.edit().clear();
                }
            }
        }

        if (cancel) {
            focusView.requestFocus();
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}

