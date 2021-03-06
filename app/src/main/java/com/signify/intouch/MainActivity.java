package com.signify.intouch;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.signify.intouch.data.Settings;
import com.signify.intouch.utils.NotificationHandler;


public class MainActivity extends ActionBarActivity {

    private static final String First_Run = "com.signify.intouch.firstrun";
    Settings mSettings;
    TextView hibernateStatusText;
    ImageView hibernateImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSettings = Settings.getInstance(this);
        firstRunCheck();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hibernateStatusText = (TextView) findViewById(R.id.hibernateStatusText);

        hibernateImageButton = (ImageView) findViewById(R.id.hibernateImageButton);
        hibernateImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleImageButton();
            }
        });

        setImageButton();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_change_contact) {
            Intent intent = new Intent(this, SetupContactActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_change_days) {
            Intent intent = new Intent(this, SetupDayActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_help) {
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toggleImageButton(){
        if(mSettings.getHibernate()){
            mSettings.setHibernate(false);
        }
        else{
            mSettings.setHibernate(true);
        }
        setImageButton();
    }

    private void setImageButton(){
        if(!mSettings.getHibernate()){
            hibernateImageButton.setImageResource(R.drawable.active_tick);
            hibernateStatusText.setText(R.string.text_hibernate_status_false);
        }
        else{
            hibernateImageButton.setImageResource(R.drawable.stopped_cross);
            hibernateStatusText.setText(R.string.text_hibernate_status_true);
        }
    }

    private void firstRunCheck(){
        if(mSettings.getFirstRun()){
            Intent intent = new Intent(this, SetupContactActivity.class);
            startActivity(intent);
        }
    }
}
