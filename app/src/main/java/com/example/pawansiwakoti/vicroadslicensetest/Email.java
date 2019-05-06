package com.example.pawansiwakoti.vicroadslicensetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Email extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        final EditText Name = findViewById(R.id.etName);
        final EditText Comment = findViewById(R.id.etComment);
        Button btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/html");
            intent.putExtra(Intent.EXTRA_EMAIL, new String("Pksiwako@deakin.edu.au"));
            intent.putExtra(Intent.EXTRA_SUBJECT,"Feedback from App");
            intent.putExtra(Intent.EXTRA_TEXT,"Name:"+Name.getText()+"\n Message:"+Comment.getText());
            try{
                startActivity(Intent.createChooser(intent, "Please select Email"));
            }
            catch (Exception e)
            {
                Toast.makeText(Email.this, "There are no Email Clients",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
