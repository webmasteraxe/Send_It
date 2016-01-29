package com.manojz.send_it;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.manojz.send_it.R;

/**
 * Created by amungara on 1/28/2016.
 */
public class RequestActivity extends AppCompatActivity {
    EditText to,subject,body;
    Toolbar toolbar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_main);
        to = (EditText)findViewById(R.id.input_email);
        subject = (EditText)findViewById(R.id.input_subject);
        body = (EditText)findViewById(R.id.input_body);
        body.setText("FirstName:  \nLastName:   \nEmail:  \nPhone:  \nAddress:  ");
        button = (Button)findViewById(R.id.btn_send);

        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));


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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            super.onBackPressed();
        }
        return false;
    }
}
