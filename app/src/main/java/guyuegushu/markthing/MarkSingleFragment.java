package guyuegushu.markthing;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/21.
 */

public class MarkSingleFragment extends Fragment {

    private DBManager dbManager;
    private Context mContext;
    private CheckItem checkItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fregment_mark_single, container, false);
        LogUtil.e("in this fragment");
        view.getBackground().setAlpha(150);
        init(view);
        return view;
    }

    private void singleItem(View view) {

        TextView month = (TextView) view.findViewById(R.id.time_mark);
        TextView am = (TextView) view.findViewById(R.id.mark_am);
        TextView pm = (TextView) view.findViewById(R.id.mark_pm);

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

    private void init(View view) {
        mContext = getActivity();
        dbManager = new DBManager(mContext);
        singleItem(view);
        checkItem = null;
        checkItem = checkListener(view);

        Button btn_sure = (Button) view.findViewById(R.id.btn_sure);
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!checkItem.isMain_checked()) {
                    if (checkItem.isAm_checked()) {
                        ToastUtil.showToast(mContext, "上班打卡ing...", 0);
                    } else {
                        ToastUtil.showToast(mContext, "请选择之后再确定", 0);
                        return;
                    }
                } else {
                    if (checkItem.isPm_checked()) {
                        ToastUtil.showToast(mContext, "下班打卡ing...", 0);

                    } else {
                        ToastUtil.showToast(mContext, "请选择之后再确定", 0);
                        return;
                    }
                }
                dbManager.saveCheckItem(checkItem);
            }
        });
    }

    private CheckItem checkListener(View view) {

        final CheckItem checkItem = new CheckItem();
        RadioGroup checkGroup = (RadioGroup) view.findViewById(R.id.check_group_mark);
        final RadioButton yes = (RadioButton) view.findViewById(R.id.checkbox_yes_mark);
        final RadioButton no = (RadioButton) view.findViewById(R.id.checkbox_no_mark);
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
