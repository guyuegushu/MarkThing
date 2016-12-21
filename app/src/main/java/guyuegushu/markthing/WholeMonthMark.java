package guyuegushu.markthing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/16.
 */

public class WholeMonthMark extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
    }

    private void init() {

        singleItem();
        DBManager db = new DBManager(this);
        ListView mList = (ListView) findViewById(R.id.check_list_whole);
        CheckAdapter adapter = new CheckAdapter(this, db.getCheckItem());
        mList.setAdapter(adapter);
    }

    private void singleItem() {

        TextView month = (TextView) findViewById(R.id.month_whole);
        TimeYMDH timeYMDH = new TimeYMDH();
        month.setText(timeYMDH.getYM());
    }

}
