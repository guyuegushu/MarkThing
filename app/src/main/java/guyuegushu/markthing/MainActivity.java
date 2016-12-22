package guyuegushu.markthing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

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
//    btn_mark_today  record_month

    public void btn_mark_today(View view){

        Intent intent = new Intent(this, FragmentDisActivity.class);
        intent.putExtra("frag", "Mark");
        startActivity(intent);
    }

    public void record_month(View view){

        Intent intent = new Intent(this, FragmentDisActivity.class);
        intent.putExtra("frag", "Month");
        startActivity(intent);

    }
}
