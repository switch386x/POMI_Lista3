package com.example.pilaskow.pomi_lista3;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//zadania eportal
//lista3
//czemu toolbar jest widoczny w liscie2 jesli jest usuniety i i tak pokazuje tam tytul? jak uzyskac pokazywanie tytulu listy DONE
//czym te layouty sie roznia, jak robie new basic activity to mi daje nie constraint tylko jakis inny rodzaj
//frame layout(container), linear layout, constraint layout - nowy(inaczej dziala ustawianie constrainow niz w linearze) ,relative
//czemu tworzone sa dwa xmle - activity i content dla aktywnosci? DONE
public class MainActivity extends AppCompatActivity {

    public static int startActivityRequestCode = 123;
    Uri uriUrl = Uri.parse("http://stackoverflow.com/");
    public final static String key = "abc";
    public int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView linkTextView = findViewById(R.id.linkContainerTextView);
        findViewById(R.id.ResumeCounter).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startMyActivity(counter);
            }
        });
        linkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
        findViewById(R.id.RunAppendedThread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newThreadCounter();
            }
        });
    }

    public void startMyActivity(int counter){
        Intent startIntent = new Intent(this,SecondScreenActivity.class);
        startIntent.putExtra(key, counter);
        startActivityForResult(startIntent,startActivityRequestCode);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt(key, counter);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        counter+=1;
        ((Button)findViewById(R.id.ResumeCounter)).setText(String.valueOf(counter));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        counter = savedInstanceState.getInt(key);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(startActivityRequestCode == resultCode){
            data.getStringExtra("traceback");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /*ZADANIE 3
    Obliczanie w osobnym watku wyrazenia 13!
    wywietlanie wartosci jako Toast - powiadomienie
    */
    public void newThreadCounter(){
       Thread thread = new Thread(new Runnable() {
           int number = 13;
           int result = 1;
           @Override
           public void run() {
               for(int factor =2; factor <= number; factor++){
                   result*=factor;
               }
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Toast.makeText(getApplicationContext(),String.valueOf(result), Toast.LENGTH_LONG).show();
                       }
               });
           }
       });
       thread.start();
    }
}

