package guyuegushu.markthing;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/12/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;

    private static final String DATABASE_NAME = "CHECK_INFO.db";
    private static final String TABLE_NAME = "CHECK_ITEM_INFO";
    private static final int VERSION = 1;


    private DatabaseHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    public DatabaseHelper(Context context){

        this(context, DATABASE_NAME, null, VERSION);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        onUpgrade(sqLiteDatabase, 0, VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        for (int version = i + 1; version <= i1; version++){
            updateDb(sqLiteDatabase, version);
        }
    }

    private void createTable(SQLiteDatabase db){
        String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "DAY TEXT, " +
                "MAIN_CHECKED INTEGER , " +
                "AM_CHECKED INTEGER , " +
                "AM_CHECKBOX INTEGER , " +
                "PM_CHECKED INTEGER , " +
                "PM_CHECKBOX INTEGER );";
        db.execSQL(SQL_CREATE_TABLE);
    }

    private void updateDb(SQLiteDatabase db, int version){

        switch (version){
            case 1:createTable(db);
                break;
            default:
                break;
        }
    }
}
