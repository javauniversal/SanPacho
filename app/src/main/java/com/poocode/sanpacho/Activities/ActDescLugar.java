package com.poocode.sanpacho.Activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.poocode.sanpacho.Entities.Lugares;
import com.poocode.sanpacho.R;

public class ActDescLugar extends AppCompatActivity {

    private KenBurnsView kenBurnsView;
    private DisplayImageOptions options1;
    private ImageLoader imageLoader1;
    private TextView descripcion;
    private TextView nombreLugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_desc_lugar);

        kenBurnsView = (KenBurnsView) findViewById(R.id.image);
        descripcion = (TextView) findViewById(R.id.desLugar);
        nombreLugar = (TextView) findViewById(R.id.numeroLugar);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imageLoader1 = ImageLoader.getInstance();
        imageLoader1.init(config);
        //Setup options for ImageLoader so it will handle caching for us.
        options1 = new DisplayImageOptions.Builder()
                .cacheInMemory()
                .cacheOnDisc()
                .build();

        LoadingImag();
        LoadingTextView();
    }

    private void LoadingTextView(){
        nombreLugar.setText(Lugares.getStaticLigat().getNombre());
        descripcion.setText(Lugares.getStaticLigat().getDescripcion());
    }

    public void LoadingImag(){
        ImageLoadingListener listener = new ImageLoadingListener(){
            @Override
            public void onLoadingStarted(String arg0, View arg1) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onLoadingCancelled(String arg0, View arg1) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
            }
            @Override
            public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                // TODO Auto-generated method stub
            }
        };

        imageLoader1.displayImage(Lugares.getStaticLigat().getFoto(), kenBurnsView, options1, listener);
    }

}
