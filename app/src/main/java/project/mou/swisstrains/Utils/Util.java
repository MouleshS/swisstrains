package project.mou.swisstrains.Utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import project.mou.swisstrains.R;

import static project.mou.swisstrains.Constants.formatDTTIME;
import static project.mou.swisstrains.Constants.formatSTD;

/**
 * Created by Mou on 2/15/2018.
 */

public class Util {

    public static void showToast(final Activity activity, final String msg) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void displayPic(Context context, ImageView imageView, String url, int placeHolder) {
        Glide.with(context).load(url)
                .placeholder(placeHolder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop().into(imageView);
    }

    public static boolean checkEditInput(Context context, EditText editText) {
        if (null != editText.getText()) {
            String s = editText.getText().toString();
            if (TextUtils.isEmpty(s) | s.length() == 0) {
                editText.requestFocus();
                editText.setError(context.getResources().getString(R.string.error_symbol));
                return false;
            } else {
                editText.setError(null);
                return true;
            }
        } else return false;
    }


    public static String getFormattedDate(Long timeStamp, int mode) {

        String formattedDt = null;
        if (timeStamp != null)
            switch (mode) {
                case formatDTTIME:
                    SimpleDateFormat ts = new SimpleDateFormat("hh:mm:ss a ", Locale.US);
                    formattedDt = String.valueOf(ts.format(timeStamp));
                    break;

                case formatSTD:
                    SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yy ", Locale.US);
                    formattedDt = String.valueOf(dt.format(timeStamp));
                    break;
            }

        return formattedDt;
    }

    public static String generateMd5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
