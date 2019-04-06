package son.dualai;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.view.View;

import java.io.File;
import java.security.MessageDigest;
import java.util.List;

import static com.alibaba.fastjson.JSON.defaultLocale;

public class Util {
    public final static String TAG = "test";

    public static String getMode(int mode) {
        switch (mode) {
            case View.MeasureSpec.EXACTLY:
                return "EXACTLY";
            case View.MeasureSpec.AT_MOST:
                return "AT_MOST";
            default:
                return "UN";
        }
    }

    public static String md5(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(input.getBytes());
            byte[] output = messageDigest.digest();
            StringBuilder hex = new StringBuilder(output.length * 2);
            for (byte b : output) {
                if ((b & 0xFF) < 0x10)
                    hex.append("0");
                hex.append(Integer.toHexString(b & 0xFF));
            }
            return hex.toString().toUpperCase(defaultLocale);
        } catch (Exception ex) {

        }
        return "";
    }


    public static boolean isMOrHigher() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return true;
        }
        return false;
    }

    public static boolean isNougatOrHigher() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return true;
        }
        return false;
    }

    public static Intent formatFileIntent(Context context, File file, boolean isPic, String intentDes) {

        if (file == null || !file.exists() || !file.canRead()) return null;
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            if (!isNougatOrHigher()) {
                intent.setDataAndType(Uri.fromFile(file), intentDes);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            } else {
                Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                if (isPic) {
                    List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(
                            intent, PackageManager.MATCH_DEFAULT_ONLY);
                    for (ResolveInfo resolveInfo : resInfoList) {
                        String packageName = resolveInfo.activityInfo.packageName;
                        context.grantUriPermission(packageName, contentUri,
                                Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                }
                intent.setDataAndType(contentUri, intentDes);
            }
            return intent;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
