package com.example.pawansiwakoti.vicroadslicensetest.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawansiwakoti.vicroadslicensetest.R;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends AppCompatActivity {
    EditText Email, Password;
    TextView Register;
    Button Login;

final String urllogin = "https://pk13.000webhostapp.com/login_user.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email =  findViewById(R.id.et_email);
        Password =  findViewById(R.id.et_password);
        Login =  findViewById(R.id.btn_login);
        Register =  findViewById(R.id.tv_register);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,
                        com.example.pawansiwakoti.vicroadslicensetest.activities.Register.class);
                startActivity(i);
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();

                new LoginUser().execute(email, password);
            }
        });
    }




    public class LoginUser extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String email = strings[0];
            String password = strings[1];

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("user_id", email)
                    .add("user_password", password)
                    .build();

            Request request = new Request.Builder()
                    .url(urllogin)
                    .post(formBody)
                    .build();

            Response response = null;
            try{
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    String result = response.body().string();
                    if(result.equalsIgnoreCase("login")){
                        Intent intent = new Intent(Login.this,
                                HomePage.class);
                        startActivity(intent);
                        finish();
                    }else{
                        showToast("Email or Password mismatched!");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }



    public void showToast(final String Text){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Login.this,
                        Text, Toast.LENGTH_LONG).show();
            }
        });
    }
}



