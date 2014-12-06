package me.doapps.imagenes.de.navidad.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import me.doapps.imagenes.de.navidad.R;
import me.doapps.imagenes.de.navidad.utils.UtilFonts;

/**
 * Created by jonathan on 04/12/2014.
 */
public class SplashActivity extends ActionBarActivity{
    private TextView txt_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        txt_title = (TextView)findViewById(R.id.txt_title);
        txt_title.setTypeface(UtilFonts.setHollyBerry(this));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
