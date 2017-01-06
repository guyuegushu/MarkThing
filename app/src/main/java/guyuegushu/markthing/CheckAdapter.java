package guyuegushu.markthing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/16.
 */

public class CheckAdapter extends BaseAdapter {

    Context mContext;
    List<CheckItem> mdata;
    LayoutInflater mLayoutInflater;


    public CheckAdapter(Context context, List<CheckItem> data) {

        mLayoutInflater = LayoutInflater.from(context);
        mdata = data;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int i) {
        return mdata.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = mLayoutInflater.inflate(R.layout.list_item_fragment_list, null);
            holder.day = (TextView) view.findViewById(R.id.day_list);

            holder.never_mark_pm_list = (TextView) view.findViewById(R.id.never_mark_pm_list);
            holder.never_mark_am_list = (TextView) view.findViewById(R.id.never_mark_am_list);

            holder.check_group_am_list = (RadioGroup) view.findViewById(R.id.check_group_am_list);
            holder.check_group_pm_list = (RadioGroup) view.findViewById(R.id.check_group_pm_list);
            holder.never_mark_list = (LinearLayout) view.findViewById(R.id.never_mark_list);

            holder.yes_am = (RadioButton) view.findViewById(R.id.checkbox_yes_am_list);
            holder.no_am = (RadioButton) view.findViewById(R.id.checkbox_no_am_list);

            holder.yes_pm = (RadioButton) view.findViewById(R.id.checkbox_yes_pm_list);
            holder.no_pm = (RadioButton) view.findViewById(R.id.checkbox_no_pm_list);

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.day.setText(mdata.get(i).getDay());
        boolean am = mdata.get(i).isAm_checked();
        boolean pm = mdata.get(i).isPm_checked();

        if (am || pm) {
            //初始化显示
            holder.check_group_am_list.setVisibility(View.VISIBLE);
            holder.check_group_pm_list.setVisibility(View.VISIBLE);
            holder.never_mark_list.setVisibility(View.GONE);

            if (am && pm) {
                //初始化显示
                holder.yes_pm.setVisibility(View.VISIBLE);
                holder.no_pm.setVisibility(View.VISIBLE);
                holder.never_mark_pm_list.setVisibility(View.GONE);
                holder.yes_am.setVisibility(View.VISIBLE);
                holder.no_am.setVisibility(View.VISIBLE);
                holder.never_mark_am_list.setVisibility(View.GONE);

                if (mdata.get(i).isAm_checkBox()) {
                    holder.yes_am.setChecked(true);
                    holder.no_am.setEnabled(false);
                } else {
                    holder.no_am.setChecked(true);
                    holder.yes_am.setEnabled(false);
                }

                if (mdata.get(i).isPm_checkBox()) {
                    holder.yes_pm.setChecked(true);
                    holder.no_pm.setEnabled(false);
                } else {
                    holder.no_pm.setChecked(true);
                    holder.yes_pm.setEnabled(false);
                }

            } else if (am) {
                //初始化显示
                holder.yes_am.setVisibility(View.VISIBLE);
                holder.no_am.setVisibility(View.VISIBLE);
                holder.never_mark_am_list.setVisibility(View.GONE);

                if (mdata.get(i).isAm_checkBox()) {
                    holder.yes_am.setChecked(true);
                    holder.no_am.setEnabled(false);
                } else {
                    holder.no_am.setChecked(true);
                    holder.yes_am.setEnabled(false);
                }

                holder.yes_pm.setVisibility(View.GONE);
                holder.no_pm.setVisibility(View.GONE);
                holder.never_mark_pm_list.setVisibility(View.VISIBLE);

            } else {

                //初始化显示
                holder.yes_pm.setVisibility(View.VISIBLE);
                holder.no_pm.setVisibility(View.VISIBLE);
                holder.never_mark_pm_list.setVisibility(View.GONE);

                if (mdata.get(i).isPm_checkBox()) {
                    holder.yes_pm.setChecked(true);
                    holder.no_pm.setEnabled(false);
                } else {
                    holder.no_pm.setChecked(true);
                    holder.yes_pm.setEnabled(false);
                }

                holder.yes_am.setVisibility(View.GONE);
                holder.no_am.setVisibility(View.GONE);
                holder.never_mark_am_list.setVisibility(View.VISIBLE);
            }
        } else {
            holder.check_group_am_list.setVisibility(View.GONE);
            holder.check_group_pm_list.setVisibility(View.GONE);
            holder.never_mark_list.setVisibility(View.VISIBLE);

        }
        return view;
    }

    private class Holder {
        private TextView day;

        private RadioGroup check_group_am_list;
        private RadioGroup check_group_pm_list;
        private LinearLayout never_mark_list;

        private TextView never_mark_pm_list;
        private TextView never_mark_am_list;

        private RadioButton yes_am;
        private RadioButton no_am;

        private RadioButton yes_pm;
        private RadioButton no_pm;
    }
}
