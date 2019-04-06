package son.imgzip;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

/**
 * Created on 2019/3/16.
 */
public class CommonUtils {
    public static void openAlbum(Activity activity, int requestCode){
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        activity.startActivityForResult(intent,requestCode);
    }

    public static Intent getCamerIntent(Uri outputUri) {
        Intent intent = new Intent();
        intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output",outputUri);
        return intent;
    }

    public static void hasCamera(Activity activity, Intent camerIntent, int cameraCode) {
        PackageManager pm = activity.getPackageManager();
        boolean hasACamera = pm.hasSystemFeature(PackageManager.FEATURE_CAMERA) || pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT) || Camera.getNumberOfCameras() > 0;
        if(hasACamera){
            activity.startActivityForResult(camerIntent,cameraCode);
        }else{

        }
    }
}
