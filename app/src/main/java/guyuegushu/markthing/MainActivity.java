package guyuegushu.markthing;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private  MonthListFragment mlF = new MonthListFragment();
    private MarkFragment mF = new MarkFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        singleItem();
    }

    private void singleItem() {
        TextView month = (TextView) findViewById(R.id.main_time);
        TimeYMDH timeYMDH = new TimeYMDH();
        month.setText(timeYMDH.getYMD());
    }

    public void btn_mark_today(View view){
        disMark();
    }

    public void record_month(View view){
        disMonth();
    }

    private void disMark(){

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .remove(mlF)
                .replace(R.id.mark_main, mF)
                .commit();
    }

    private void disMonth(){

        getFragmentManager().beginTransaction()
                .remove(mF)
                .replace(R.id.mark_main, mlF)
                .commit();
    }

}
