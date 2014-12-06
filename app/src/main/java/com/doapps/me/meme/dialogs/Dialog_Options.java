package com.doapps.me.meme.dialogs;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.doapps.me.meme.R;
import com.doapps.me.meme.utils.UtilFonts;

/**
 * Created by jonathan on 05/12/2014.
 */
public class Dialog_Options extends AlertDialog{
    private Interface_Save interface_save;
    private Interface_Wallpaper interface_wallpaper;

    public Dialog_Options(Context context) {
        super(context);
        initDialog();
    }

    protected Dialog_Options(Context context, int theme) {
        super(context, theme);
        initDialog();
    }

    protected Dialog_Options(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_options, null);
        setView(view);

        setCancelable(true);

        TextView txt_message = (TextView) view.findViewById(R.id.txt_message);
        txt_message.setTypeface(UtilFonts.setHelveticaThin(getContext()));

        Button btn_download = (Button) view.findViewById(R.id.btn_download);
        Button btn_background = (Button) view.findViewById(R.id.btn_background);
        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interface_save.getSave(true);
            }
        });
        btn_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interface_wallpaper.setWallpaper(true);
            }
        });

    }

    /****/
    public interface Interface_Save{
        void getSave(boolean status);
    }
    public void setInterface_save(Interface_Save interface_save){
        this.interface_save = interface_save;
    }
    public interface Interface_Wallpaper{
        void setWallpaper(boolean status);
    }
    public void setInterface_wallpaper(Interface_Wallpaper interface_wallpaper){
        this.interface_wallpaper = interface_wallpaper;
    }
}
