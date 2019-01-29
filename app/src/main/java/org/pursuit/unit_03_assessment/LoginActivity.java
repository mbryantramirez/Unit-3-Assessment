package org.pursuit.unit_03_assessment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
        Log.d("TAG", "attemptLogin: " + email);
        Log.d("TAG", "attemptLogin: " + password);
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
            String xmlUserName = getString(R.string.dummy_username);
            String xmlPassword = getString(R.string.dummy_password);
            Log.d("TAG", "attemptLogin: " + "PASSWORD IS VALID");
            Log.d("TAG", "attemptLogin: " +xmlUserName);
            Log.d("TAG", "attemptLogin: " +xmlPassword);
            /**
             * String.valueOf() converts and integer to a string
             *
             * using String.valueOf(R.string.dummyusername/password) will convert this into the integer value of the Resources id rather than get the text in the string.xml file
             *
             * use getString() instead
             *
             * Use logcat/debugger to catch this kind of error
             */
            if (email.equals(xmlUserName) && password.equals(xmlPassword)) {
                cancel = false;
                Intent intent = new Intent(getApplicationContext(), RecyclerActivity.class);
                startActivity(intent);
                /**
                 * This code will never run, your are starting new activity so youll move on from this activity
                 */
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

