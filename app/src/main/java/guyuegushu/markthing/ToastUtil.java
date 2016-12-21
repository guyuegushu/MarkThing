package guyuegushu.markthing;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/16.
 */

public class ToastUtil {
    private static Toast mToast;

    public static void showToast(Context context, String msg, int time){
        if (mToast == null){
            mToast = Toast.makeText(context, msg, time);
        } else {
            mToast.setText(msg);
            mToast.setDuration(time);
        }
        mToast.show();
    }

    public static void showToast(Context context, int rsid, int time){
        showToast(context, context.getString(rsid), time);
    }
}
