package com.example.pawansiwakoti.vicroadslicensetest;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.widget.Toast.LENGTH_LONG;

public class Register extends AppCompatActivity {

    EditText Name, Email, Password;
    Button Register;

    final String url_Register = "https://www.deakin.edu.au/~pksiwako/phpfile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Name = (EditText)findViewById(R.id.et_name);
        /*Email = (EditText)findViewById(R.id.et_email);
        Password = (EditText)findViewById(R.id.et_password);*/
        Register = (Button)findViewById(R.id.btn_register);


       Register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String name = Name.getText().toString();
               String email = Email.getText().toString();
               String password = Password.getText().toString();

               new RegisterUser().execute(name, email, password);
           }
       });


    }
    public class RegisterUser extends AsyncTask <String, Void, String> {
        @Override
        protected  String doInBackground(String... strings) {
            String name = strings[0];
            String Email = strings[1];
            String password = strings[2];

            String finalURL = url_Register + "?user_name=" + Name +
                    "&user_id=" + Email + "&user_password=" + Password;
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(finalURL)
                        .get()
                        .build();
                Response response = null;

                try {
                    response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        if (result.equalsIgnoreCase("User registered Successfully")) {

                           show("Register Successfully");
                            Intent intent = new Intent(Register.this, Login.class);
                            startActivity(intent);
                            finish();

                        } else if (result.equalsIgnoreCase("User already exists")) {
                            show("User already exists");
                        } else {
                            show("oop! please try again");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

    }
public  void show(final String Text) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Register.this,
                        Text, LENGTH_LONG).show();
            }
        });
}

}
