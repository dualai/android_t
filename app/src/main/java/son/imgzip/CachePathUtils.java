package son.imgzip;

import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created on 2019/3/16.
 */
public class CachePathUtils {

    private static File getCameraCacheDir(String fileName) {
        File cache = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return cache.mkdir() || cache.exists() && cache.isDirectory() ? new File(cache, fileName) : null;
    }

    private static String getBaseFileName() {
        return (new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH)).format(new Date());
    }

    public static File getCameraCacheFile() {
        String fileName = "camera_" + getBaseFileName() + ".jpg";
        return getCameraCacheDir(fileName);
    }
}
