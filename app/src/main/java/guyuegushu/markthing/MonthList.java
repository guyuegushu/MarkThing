package guyuegushu.markthing;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Administrator on 2017/1/6.
 */

public class MonthList extends Activity {

    private  MonthListFragment mlF = new MonthListFragment();
    private MarkFragment mF = new MarkFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        getFragmentManager().beginTransaction()
                .remove(mF)
                .replace(R.id.activity_month, mlF)
                .commit();
    }
}
