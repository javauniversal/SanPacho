package com.poocode.sanpacho.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.poocode.sanpacho.Entities.ListLugares;
import com.poocode.sanpacho.R;

public class CostomAdapterLugar extends BucketListAdapterLugares {

    private Activity mActivity;
    private ListLugares elements;
    private DisplayImageOptions options1;
    private ImageLoader imageLoader1;

    public CostomAdapterLugar(Activity ctx, ListLugares elements) {
        super(ctx, elements);
        this.mActivity=ctx;
        this.elements = elements;

        //Setup the ImageLoader, we'll use this to display our images
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mActivity).build();
        imageLoader1 = ImageLoader.getInstance();
        imageLoader1.init(config);
        //Setup options for ImageLoader so it will handle caching for us.
        options1 = new DisplayImageOptions.Builder()
                .cacheInMemory()
                .cacheOnDisc()
                .build();

    }

    @Override
    protected View getBucketElement(final int position) {
        final ViewHolder holder;
        View bucketElement;
        LayoutInflater inflater = mActivity.getLayoutInflater();
        bucketElement = inflater.inflate(R.layout.binder_data_1, null);
        holder = new ViewHolder(bucketElement);
        bucketElement.setTag(holder);

        //Llenado static product

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

        imageLoader1.displayImage(elements.get(position).getFoto(), holder.img, options1, listener);

        holder.name.setText(elements.get(position).getNombre());

        bucketElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity,elements.get(position).getNombre(),Toast.LENGTH_LONG).show();
            }
        });

        return bucketElement;
    }

    class ViewHolder {
        public TextView name = null;
        public ImageView img = null;

        ViewHolder(View row) {
            name = (TextView) row.findViewById(R.id.nombreLugar);
            img = (ImageView) row.findViewById(R.id.iconImagen);
        }
        void populateFrom(String s) {
            name.setText(s);
        }
    }
}
