package com.doapps.me.meme.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.doapps.me.meme.R;

/**
 * Created by jonathan on 04/12/2014.
 */
public class CustomListAdapter extends BaseAdapter {
    private LayoutInflater inflater;

    public CustomListAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView img_meme = (ImageView)convertView;
        if (img_meme == null)
            img_meme = (ImageView) inflater.inflate(R.layout.item, null);
        if(position%2==0){
            img_meme.setImageResource(R.drawable.meme_test_02);
        }
        else{
            img_meme.setImageResource(R.drawable.meme_test_01);
        }

        return img_meme;
    }
}
