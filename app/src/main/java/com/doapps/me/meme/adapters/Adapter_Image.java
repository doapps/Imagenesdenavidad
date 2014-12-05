package com.doapps.me.meme.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.doapps.me.meme.R;
import com.doapps.me.meme.beans.Meme_DTO;
import com.shamanland.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jonathan on 04/12/2014.
 */
public class Adapter_Image extends BaseAdapter {
    private Context context;
    private ArrayList<Meme_DTO> meme_dtos;
    private LayoutInflater inflater;

    public Adapter_Image(Context context, ArrayList<Meme_DTO> meme_dtos){
        this.context = context;
        this.meme_dtos = meme_dtos;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return meme_dtos.size();
    }

    @Override
    public Object getItem(int position) {
        return meme_dtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        Meme_DTO meme_dto = meme_dtos.get(position);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_image, parent, false);
            holder = new Holder();
            holder.fab_share = (FloatingActionButton)convertView.findViewById(R.id.fab_share);
            holder.img_meme = (ImageView)convertView.findViewById(R.id.img_meme);

            convertView.setTag(holder);
        }
        else{
            holder = (Holder)convertView.getTag();
        }

        Picasso.with(context).load(meme_dto.getImage_url()).placeholder(R.drawable.meme_test_01).centerCrop().fit().into(holder.img_meme);
        holder.fab_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Recetas Navideñas");
                    String sAux = "\nTe invito a descargar esta aplicación:\n\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=com.doapps.me.meme&hl=es\n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    context.startActivity(Intent.createChooser(i, "Compartir App"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });

        return convertView;
    }


    /****/
    public static class Holder{
        private FloatingActionButton fab_share;
        private ImageView img_meme;
    }
}
