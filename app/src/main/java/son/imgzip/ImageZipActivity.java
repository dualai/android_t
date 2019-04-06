package son.imgzip;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import java.io.File;
import java.util.ArrayList;

import son.dualai.R;
import son.dualai.Util;

/**
 * Created on 2019/3/16.
 */
public class ImageZipActivity extends Activity {

    private String cameraCachePath; // 拍照源文件路径
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagezip);


        ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                },
                2001);


        testYs();
    }

    private void testYs() {
        String mCacheDir = Constants.BASE_CACHE_PATH + getPackageName() + "/cache/" + Constants.COMPRESS_CACHE;

    }

    public void camera(View view) {
        //FileProvider
        Uri outputUri;
        File file;
        file = CachePathUtils.getCameraCacheFile();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            outputUri = UriParseUtils.getCameraOutPutUri(this,file);
        }else{
            outputUri = Uri.fromFile(file);
        }

        cameraCachePath = file.getAbsolutePath();
        //启动拍照
        CommonUtils.hasCamera(this,CommonUtils.getCamerIntent(outputUri),Constants.CAMERA_CODE);
    }

    public void album(View view) {
        CommonUtils.openAlbum(this,Constants.ALBUM_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拍照返回
        if(requestCode == Constants.CAMERA_CODE && resultCode == RESULT_OK){
            preCompress(cameraCachePath);
        }

        // 相册返回
        if(requestCode == Constants.ALBUM_CODE && resultCode == RESULT_OK){
            if(data != null){
                Uri uri = data.getData();
                String path = UriParseUtils.getPath(this,uri);
                preCompress(path);
            }
        }

    }

    private void preCompress(String path) {

    }
}
