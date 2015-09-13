package com.poocode.sanpacho.Adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.poocode.sanpacho.Entities.ListGaleria;
import com.poocode.sanpacho.Entities.ListLugares;
import com.poocode.sanpacho.R;

public class CostomAdapterGaleria extends BucketListAdapterGaleria {

    private Activity mActivity;
    private ListGaleria elements;
    private DisplayImageOptions options1;
    private ImageLoader imageLoader1;

    public CostomAdapterGaleria(Activity ctx, ListGaleria elements) {
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
        final ViewHolder2 holder;
        View bucketElement;
        LayoutInflater inflater = mActivity.getLayoutInflater();
        bucketElement = inflater.inflate(R.layout.binder_galeria, null);
        holder = new ViewHolder2(bucketElement);
        bucketElement.setTag(holder);

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

        imageLoader1.displayImage(elements.get(position).getImagen(), holder.img, options1, listener);

        return bucketElement;
    }

    class ViewHolder2 {
        public TextView name = null;
        public ImageView img = null;

        ViewHolder2(View row) {
            img = (ImageView) row.findViewById(R.id.iconImagen);
        }
        void populateFrom(String s) {
            name.setText(s);
        }
    }
}
