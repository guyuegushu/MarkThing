package guyuegushu.markthing;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DBManager dbManager;
    private Context mContext;
    private CheckItem checkItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void singleItem() {

        TextView month = (TextView) findViewById(R.id.month_main);
        TextView am = (TextView) findViewById(R.id.month_am);
        TextView pm = (TextView) findViewById(R.id.month_pm);
        TimeYMDH timeYMDH = new TimeYMDH();

        if (TimeYMDH.isAorP()) {
            am.setVisibility(View.GONE);
            pm.setVisibility(View.VISIBLE);
            month.setText(timeYMDH.getYMD());
        } else {
            pm.setVisibility(View.GONE);
            am.setVisibility(View.VISIBLE);
            month.setText(timeYMDH.getYMD());
        }
    }

    private void init() {
        dbManager = new DBManager(this);
        mContext = this;
        singleItem();
        checkItem = null;
        checkItem = checkListener();
    }

    public void whole(View view) {
        Intent intent = new Intent(mContext, WholeMonthMark.class);
        startActivity(intent);
    }

    public void commitSure(View view) {

        if (!checkItem.isMain_checked()) {
            if (checkItem.isAm_checked()) {
                ToastUtil.showToast(mContext, "上午记录ing...", 0);
            } else {
                ToastUtil.showToast(mContext, "请选择之后再确定", 0);
                return;
            }
        } else {
            if (checkItem.isPm_checked()) {
                ToastUtil.showToast(mContext, "下午记录ing...", 0);

            } else {
                ToastUtil.showToast(mContext, "请选择之后再确定", 0);
                return;
            }
        }
        dbManager.saveCheckItem(checkItem);
    }

    private CheckItem checkListener() {

        final CheckItem checkItem = new CheckItem();
        RadioGroup checkGroup = (RadioGroup) findViewById(R.id.check_group_main);
        final RadioButton yes = (RadioButton) findViewById(R.id.checkbox_yes_main);
        final RadioButton no = (RadioButton) findViewById(R.id.checkbox_no_main);
        checkGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                TimeYMDH timeYMDH = new TimeYMDH();
                checkItem.setDay(timeYMDH.getD());

                if (!TimeYMDH.isAorP()) {
                    if (i == yes.getId()) {
                        checkItem.setAm_checked(true);
                        checkItem.setAm_checkBox(true);
                        checkItem.setMain_checked(false);
                    } else if (i == no.getId()) {
                        checkItem.setAm_checked(true);
                        checkItem.setAm_checkBox(false);
                        checkItem.setMain_checked(false);
                    } else {
                        checkItem.setAm_checked(false);
                        checkItem.setMain_checked(false);
                    }
                } else {
                    if (i == yes.getId()) {
                        checkItem.setPm_checked(true);
                        checkItem.setPm_checkBox(true);
                        checkItem.setMain_checked(true);
                    } else if (i == no.getId()) {
                        checkItem.setPm_checked(true);
                        checkItem.setPm_checkBox(false);
                        checkItem.setMain_checked(true);
                    } else {
                        checkItem.setPm_checked(false);
                        checkItem.setMain_checked(false);
                    }
                }
            }
        });
        return checkItem;
    }
}
