package guyuegushu.markthing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
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

            holder.yes_am = (RadioButton) view.findViewById(R.id.checkbox_yes_am_list);
            holder.no_am = (RadioButton) view.findViewById(R.id.checkbox_no_am_list);

            holder.yes_pm = (RadioButton) view.findViewById(R.id.checkbox_yes_pm_list);
            holder.no_pm = (RadioButton) view.findViewById(R.id.checkbox_no_pm_list);

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.day.setText(mdata.get(i).getDay());

        if (mdata.get(i).isPm_checked()) {

            holder.yes_pm.setVisibility(View.VISIBLE);
            holder.no_pm.setVisibility(View.VISIBLE);
            holder.never_mark_pm_list.setVisibility(View.GONE);

            if (mdata.get(i).isPm_checkBox()){
                holder.yes_pm.setChecked(true);
                holder.no_pm.setEnabled(false);
            } else {
                holder.no_pm.setChecked(true);
                holder.yes_pm.setEnabled(false);
            }
        }

        if (mdata.get(i).isAm_checked()) {

            holder.yes_am.setVisibility(View.VISIBLE);
            holder.no_am.setVisibility(View.VISIBLE);
            holder.never_mark_am_list.setVisibility(View.GONE);

            if (mdata.get(i).isAm_checkBox()){
                holder.yes_am.setChecked(true);
                holder.no_am.setEnabled(false);
            } else {
                holder.no_am.setChecked(true);
                holder.yes_am.setEnabled(false);
            }
        }
        return view;
    }

    private class Holder {
        private TextView day;

        private TextView never_mark_pm_list;
        private TextView never_mark_am_list;

        private RadioButton yes_am;
        private RadioButton no_am;

        private RadioButton yes_pm;
        private RadioButton no_pm;
    }
}
