package guyuegushu.markthing;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/16.
 */

public class DBManager {

    private static final String TABLE_NAME = "CHECK_ITEM_INFO";

    private Context mContext;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        mContext = context;
        db = dbHelper.getWritableDatabase();
    }

    private boolean isSavedPM(CheckItem checkItem) {
        String SQL_IS_SAVED = "SELECT PM_CHECKED FROM " + TABLE_NAME + " WHERE DAY = ?";
        Cursor cursor = db.rawQuery(SQL_IS_SAVED, new String[]{checkItem.getDay()});
        if (cursor != null && cursor.moveToFirst()) {
            if (I2B(cursor.getInt(cursor.getColumnIndex("PM_CHECKED")))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isSavedAM(CheckItem checkItem) {

        String SQL_IS_SAVED = "SELECT AM_CHECKED FROM " + TABLE_NAME + " WHERE DAY = ?";
        Cursor cursor = db.rawQuery(SQL_IS_SAVED, new String[]{checkItem.getDay()});
        if (cursor != null && cursor.moveToFirst()) {
            if (I2B(cursor.getInt(cursor.getColumnIndex("AM_CHECKED")))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void saveCheckItem(CheckItem checkItem) {

        if (TimeYMDH.isAorP()) {//pm
            if (!isSavedPM(checkItem)) {
                if (!isSavedAM(checkItem)) {
                    String SQL_SAVE_PM = "INSERT INTO " + TABLE_NAME + "(" +
                            "DAY, MAIN_CHECKED, AM_CHECKED, AM_CHECKBOX, " +
                            "PM_CHECKED, PM_CHECKBOX) VALUES(?,?,?,?,?,?)";
                    Object[] args = new Object[]{
                            checkItem.getDay(),
                            checkItem.isMain_checked(),
                            checkItem.isAm_checked(),
                            checkItem.isAm_checkBox(),
                            checkItem.isPm_checked(),
                            checkItem.isPm_checkBox()
                    };
                    db.execSQL(SQL_SAVE_PM, args);
                } else {
                    String SQL_UPDATE_PM = "UPDATE " + TABLE_NAME + " SET " +
                            "MAIN_CHECKED=?, PM_CHECKED=?, PM_CHECKBOX=? " +
                            "WHERE DAY=?";

                    Object[] args = new Object[]{
                            checkItem.isMain_checked(),
                            checkItem.isPm_checked(),
                            checkItem.isPm_checkBox(),
                            checkItem.getDay()
                    };
                    db.execSQL(SQL_UPDATE_PM, args);
                }
            } else {
                ToastUtil.showToast(mContext, "你已经记录过今天下午，请不要重复记录！", 0);
            }
        } else {
            if (!isSavedAM(checkItem)){
                String SQL_SAVE_AM = "INSERT INTO " + TABLE_NAME + "(" +
                        "DAY, MAIN_CHECKED, AM_CHECKED, AM_CHECKBOX, " +
                        "PM_CHECKED, PM_CHECKBOX) VALUES(?,?,?,?,?,?)";
                Object[] args = new Object[]{
                        checkItem.getDay(),
                        checkItem.isMain_checked(),
                        checkItem.isAm_checked(),
                        checkItem.isAm_checkBox(),
                        checkItem.isPm_checked(),
                        checkItem.isPm_checkBox()
                };
                db.execSQL(SQL_SAVE_AM, args);
            } else {
                ToastUtil.showToast(mContext, "你已经记录过今天上午，请不要重复记录！", 0);
            }
        }
    }

    private boolean I2B(int i) {
        if (i == 0) {
            return false;
        } else {
            return true;
        }
    }

    public List<CheckItem> getCheckItem() {
        String SQL_GET_ALL = "SELECT DAY, MAIN_CHECKED, AM_CHECKED, AM_CHECKBOX, PM_CHECKED, PM_CHECKBOX FROM " + TABLE_NAME;

        List<CheckItem> checkItemList = new ArrayList<>();
        CheckItem checkItem = null;
        Cursor cursor = db.rawQuery(SQL_GET_ALL, null);
        String day;
        boolean isMain_checked;
        boolean isAM_checkBox;
        boolean isAM_checked;
        boolean isPM_checkBox;
        boolean isPM_checked;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                day = cursor.getString(cursor.getColumnIndex("DAY"));
                isMain_checked = I2B(cursor.getInt(cursor.getColumnIndex("MAIN_CHECKED")));
                isAM_checkBox = I2B(cursor.getInt(cursor.getColumnIndex("AM_CHECKBOX")));
                isAM_checked = I2B(cursor.getInt(cursor.getColumnIndex("AM_CHECKED")));
                isPM_checkBox = I2B(cursor.getInt(cursor.getColumnIndex("PM_CHECKBOX")));
                isPM_checked = I2B(cursor.getInt(cursor.getColumnIndex("PM_CHECKED")));
                checkItem = new CheckItem(day, isMain_checked, isAM_checkBox, isAM_checked, isPM_checkBox, isPM_checked);
                checkItemList.add(checkItem);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return checkItemList;
    }


}
