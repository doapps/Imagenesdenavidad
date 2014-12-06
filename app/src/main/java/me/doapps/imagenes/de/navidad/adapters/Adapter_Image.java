package me.doapps.imagenes.de.navidad.adapters;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import me.doapps.imagenes.de.navidad.R;
import me.doapps.imagenes.de.navidad.beans.Meme_DTO;
import me.doapps.imagenes.de.navidad.dialogs.Dialog_Options;

import com.shamanland.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jonathan on 04/12/2014.
 */
public class Adapter_Image extends BaseAdapter {
    private Context context;
    private ArrayList<Meme_DTO> meme_dtos;
    private LayoutInflater inflater;

    public Adapter_Image(Context context, ArrayList<Meme_DTO> meme_dtos) {
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
        final Meme_DTO meme_dto = meme_dtos.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_image, parent, false);
            holder = new Holder();
            holder.fab_share = (FloatingActionButton) convertView.findViewById(R.id.fab_share);
            holder.img_meme = (ImageView) convertView.findViewById(R.id.img_meme);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        Picasso.with(context).load(meme_dto.getImage_url()).placeholder(R.drawable.meme_test_01).centerCrop().fit().into(holder.img_meme);
        final Holder finalHolder = holder;
        holder.fab_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Uri uriToImage = Uri.parse("file:///sdcard/test_image.jpg");
                Uri uriToImage = getLocalBitmapUri(finalHolder.img_meme);
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
                shareIntent.setType("image/jpeg");
                context.startActivity(Intent.createChooser(shareIntent, "Comparte Meme"));

            }
        });
        holder.img_meme.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final Dialog_Options dialog = new Dialog_Options(context);
                dialog.show();
                dialog.setInterface_save(new Dialog_Options.Interface_Save() {
                    @Override
                    public void getSave(boolean status) {
                        if (status) {
                            Uri uriToImage = getLocalBitmapUri(finalHolder.img_meme);
                            dialog.dismiss();
                            Toast.makeText(context, R.string.saved_image, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.setInterface_wallpaper(new Dialog_Options.Interface_Wallpaper() {
                    @Override
                    public void setWallpaper(boolean status) {
                        if (status) {
                            Drawable drawable = finalHolder.img_meme.getDrawable();
                            Bitmap bmp = ((BitmapDrawable) finalHolder.img_meme.getDrawable()).getBitmap();
                            WallpaperManager m = WallpaperManager.getInstance(context);
                            try {
                                m.setBitmap(bmp);
                                dialog.dismiss();
                                Toast.makeText(context, R.string.wallpaper_image, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                return false;
            }
        });

        return convertView;
    }


    /****/
    public static class Holder {
        private FloatingActionButton fab_share;
        private ImageView img_meme;
    }


    // Returns the URI path to the Bitmap displayed in specified ImageView
    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable) {
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "meme_" + System.currentTimeMillis() + ".jpg");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }


}
