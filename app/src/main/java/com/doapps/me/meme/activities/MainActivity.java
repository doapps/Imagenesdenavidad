package com.doapps.me.meme.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.doapps.me.meme.R;
import com.doapps.me.meme.adapters.Adapter_Image;
import com.doapps.me.meme.adapters.CustomListAdapter;
import com.doapps.me.meme.beans.Meme_DTO;
import com.nirhart.parallaxscroll.views.ParallaxListView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ParallaxListView listView = (ParallaxListView) findViewById(R.id.list_view);
        /*CustomListAdapter adapter = new CustomListAdapter(LayoutInflater.from(this));
        listView.setAdapter(adapter);*/

        final ArrayList<Meme_DTO> temp_meme_dtos = new ArrayList<Meme_DTO>();
        /** parse **/
        ParseQuery<Meme_DTO> queryRecipes = Meme_DTO.getQuery();
        queryRecipes.findInBackground(new FindCallback<Meme_DTO>() {
            @Override
            public void done(List<Meme_DTO> meme_dtos, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < meme_dtos.size(); i++) {
                        //temp_recipe_dtos.add(new Recipe_DTO(recipe_dtos.get(i).getName(), R.drawable.recipe_default));
                        Log.e("name", meme_dtos.get(i).getImage_url());
                        temp_meme_dtos.add(meme_dtos.get(i));
                    }
                    listView.setAdapter(new Adapter_Image(MainActivity.this, temp_meme_dtos));
                } else {
                    e.printStackTrace();
                }
            }
        });

        /**/


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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
