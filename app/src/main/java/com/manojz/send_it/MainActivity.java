package com.manojz.send_it;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText to,subject,body;
    Toolbar toolbar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        to = (EditText)findViewById(R.id.input_email);
        subject = (EditText)findViewById(R.id.input_subject);
        body = (EditText)findViewById(R.id.input_body);
        body.setText("FirstName:  \nLastName:   \nEmail:  \nPhone:  \nAddress:  ");
        button = (Button)findViewById(R.id.btn_send);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String toz = to.getText().toString();
                String subjectz = subject.getText().toString();
                String message = body.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{toz});
                //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
                //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
                email.putExtra(Intent.EXTRA_SUBJECT, subjectz);
                email.putExtra(Intent.EXTRA_TEXT, message);

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));

            }
        });
    }
}
