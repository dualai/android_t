package son.glide;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;

import okhttp3.internal.cache.DiskLruCache;
import son.dualai.R;
import son.glide.request.core.Glide;
import son.glide.request.listener.RequestListener;

/**
 * Created on 2019/3/15.
 */
public class GlideActivity extends Activity {


    LinearLayout scroller_line;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        scroller_line = findViewById(R.id.scroll_line);
        verifyStoragePermissions(this);
    }

    private void verifyStoragePermissions(Activity activity) {
        ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                2001);
    }


    public void single(View view) {
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        Glide.with(this).load("http://dn.dengpaoedu.com/glide/1.jpeg").loading(R.mipmap.loading)
        .listener(new RequestListener() {
            @Override
            public boolean onException() {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource) {
                return false;
            }
        }).into(imageView);



    }

    public void more(View view) {
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2001) {

        }
    }
}
