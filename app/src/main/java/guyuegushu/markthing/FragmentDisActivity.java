package guyuegushu.markthing;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/12/22.
 */

public class FragmentDisActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        init();//因为是控件，所以要在布局调用前调用
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }

    private void init(){
        Intent intent = getIntent();
        String flags = intent.getStringExtra("frag");
        if (flags != null) {
            switch (flags){
                case "Mark":
                    disMark();
                    break;
                case "Month":
                    disMonth();
                    break;
                default:
                    break;
            }
        }
    }

    private void disMark(){
        MarkFragment mF = new MarkFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .add(R.id.activity_fragment, mF)
                .commit();
    }

    private void disMonth(){
        MonthListFragment mlF = new MonthListFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.activity_fragment, mlF)
                .commit();
    }

}
