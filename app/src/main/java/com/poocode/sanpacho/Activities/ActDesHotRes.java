package com.poocode.sanpacho.Activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.poocode.sanpacho.R;

import junit.framework.Test;

import static com.poocode.sanpacho.Entities.MasterItem.getMasterItemStatic;

public class ActDesHotRes extends AppCompatActivity {

    private ImageLoader imageLoader1;
    private DisplayImageOptions options1;
    private ImageView displayImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_des_hot_res);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        displayImagen = (ImageView) findViewById(R.id.displayImagen);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imageLoader1 = ImageLoader.getInstance();
        imageLoader1.init(config);
        //Setup options for ImageLoader so it will handle caching for us.
        options1 = new DisplayImageOptions.Builder().cacheInMemory().cacheOnDisc().build();

        setViewContenido();

    }

    private void setViewContenido() {

        CargarImagen();

        TextView nombre = (TextView) findViewById(R.id.nombre);
        nombre.setText(getMasterItemStatic().getNombre());

        TextView direccion = (TextView) findViewById(R.id.direccion);
        direccion.setText(getMasterItemStatic().getDireccion());

        TextView servicios = (TextView) findViewById(R.id.servicios);
        servicios.setText(getMasterItemStatic().getServicios());

    }

    private void CargarImagen() {
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
                //holder.image.setVisibility(View.VISIBLE);
            }
            @Override
            public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                // TODO Auto-generated method stub
            }
        };

        imageLoader1.displayImage(getMasterItemStatic().getImagen(), displayImagen, options1, listener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_des_hot_res, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
