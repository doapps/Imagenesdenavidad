package me.doapps.imagenes.de.navidad.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import me.doapps.imagenes.de.navidad.R;
import me.doapps.imagenes.de.navidad.adapters.Adapter_Image;
import me.doapps.imagenes.de.navidad.beans.Image_DTO;
import me.doapps.imagenes.de.navidad.beans.Meme_DTO;
import me.doapps.imagenes.de.navidad.dialogs.Dialog_Rate;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.nirhart.parallaxscroll.views.ParallaxListView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadIntersticial();

        File wallpaperDirectory = new File("/sdcard/imagenes_navidad/");
        wallpaperDirectory.mkdirs();

        final ParallaxListView listView = (ParallaxListView) findViewById(R.id.list_view);

        final ArrayList<Image_DTO> temp_meme_dtos = new ArrayList<Image_DTO>();
        /** parse **/
        ParseQuery<Image_DTO> queryRecipes = Image_DTO.getQuery();
        queryRecipes.findInBackground(new FindCallback<Image_DTO>() {
            @Override
            public void done(List<Image_DTO> meme_dtos, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < meme_dtos.size(); i++) {
                        Log.e("name", meme_dtos.get(i).getImage_url());
                        temp_meme_dtos.add(meme_dtos.get(i));
                    }
                    listView.setAdapter(new Adapter_Image(MainActivity.this, temp_meme_dtos));
                } else {
                    e.printStackTrace();
                }
            }
        });

        /** admob **/
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(getInterstitial().isLoaded()){
                    getInterstitial().show();
                }
            }
        }, 15000);
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
        if (id == R.id.action_share) {
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Memes de futbol");
                String sAux = "\nTe invito a descargar esta aplicación:\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=com.doapps.me.meme&hl=es\n\n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "Compartir App"));
            } catch (Exception e) {
                //e.toString();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        final Dialog_Rate dialog = new Dialog_Rate(MainActivity.this);
        dialog.show();
        dialog.setInterface_finish(new Dialog_Rate.Interface_Finish() {
            @Override
            public void getFinish(boolean state) {
                if (state) {
                    dialog.dismiss();
                    finish();
                }
            }
        });
    }


    private void loadIntersticial() {
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(getResources().getString(R.string.admob_interstitial));
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitial.loadAd(adRequest);
    }

    public InterstitialAd getInterstitial() {
        return interstitial;
    }
}
