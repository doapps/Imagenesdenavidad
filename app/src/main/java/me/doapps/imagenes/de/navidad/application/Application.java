package me.doapps.imagenes.de.navidad.application;

import me.doapps.imagenes.de.navidad.R;
import me.doapps.imagenes.de.navidad.beans.Meme_DTO;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by jonathan on 04/12/2014.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Meme_DTO.class);

        Parse.initialize(this, getResources().getString(R.string.application_id), getResources().getString(R.string.client_key));

        ParseUser.enableAutomaticUser();

        ParseACL defaultACl = new ParseACL();
        defaultACl.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACl, true);
    }
}
