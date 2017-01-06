package guyuegushu.markthing;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by VEB on 2016/11/18.
 * Log工具类，（静态调用）自动获取调用者的类和方法名称作为Tag
 */

public class LogUtil {

    private final static String Tag = "MyLogUtil";

    private static String getCallerInfo() {
        StackTraceElement[] trace = new Throwable()
                .fillInStackTrace().getStackTrace();
        String callerInfo = "%s.%s(Line:%d)>>>:  ";//类名.方法（行数）
        for (int i = 3; i < trace.length; i++) {
            Class<?> tmp = trace[i].getClass();
            if (!tmp.equals(LogUtil.class)) {
                String callerName = trace[i].getClassName();
                String callerMethod = trace[i].getMethodName();
                int callerLine = trace[i].getLineNumber();

                callerName = callerName.substring(callerName.
                        lastIndexOf(".") + 1);
                callerInfo = String.format(callerInfo, callerName,
                        callerMethod, callerLine);
                return callerInfo;
            }
        }
        if (TextUtils.isEmpty(callerInfo.trim())) {
            callerInfo = "NULL";
            return callerInfo;
        } else {
            return callerInfo;
        }
    }

    private static String formatMsg(String msg) {
        String msgHead = getCallerInfo();
        return msgHead + msg;
    }

    public static void v(String msg) {
        String finalMsg = formatMsg(msg);
        Log.v(Tag, finalMsg);
    }

    public static void d(String msg) {
        String finalMsg = formatMsg(msg);
        Log.d(Tag, finalMsg);
    }

    public static void i(String msg) {
        String finalMsg = formatMsg(msg);
        Log.i(Tag, finalMsg);
    }

    public static void w(String msg) {
        String finalMsg = formatMsg(msg);
        Log.w(Tag, finalMsg);
    }

    public static void e(String msg) {
        String finalMsg = formatMsg(msg);
        Log.e(Tag, finalMsg);
    }

}
