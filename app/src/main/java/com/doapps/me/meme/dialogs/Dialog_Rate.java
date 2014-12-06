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
public class Dialog_Rate extends AlertDialog {
    private Interface_Finish interface_finish;

    public Dialog_Rate(Context context) {
        super(context);
        initDialog();
    }

    protected Dialog_Rate(Context context, int theme) {
        super(context, theme);
        initDialog();
    }

    protected Dialog_Rate(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_rate, null);
        setView(view);

        setCancelable(false);

        TextView txt_message = (TextView) view.findViewById(R.id.txt_message);
        txt_message.setTypeface(UtilFonts.setHelveticaThin(getContext()));

        Button btn_rate = (Button) view.findViewById(R.id.btn_rate);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        btn_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("market://details?id=" + getContext().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    getContext().startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    Log.e("ERROR", "Couldn't launch the market");
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interface_finish.getFinish(true);
            }
        });

    }

    public interface Interface_Finish{
        void getFinish(boolean state);
    }
    public void setInterface_finish(Interface_Finish interface_finish){
        this.interface_finish = interface_finish;
    }

}
