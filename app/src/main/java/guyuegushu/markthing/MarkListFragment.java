package guyuegushu.markthing;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/16.
 */

public class MarkListFragment extends Fragment {

    private Context context;
    private DBManager db;
    protected ListView mList;
    protected CheckAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mark_list, container, false);
        view.getBackground().setAlpha(150);
        init(view);
        return view;
    }

    private void init(View view) {

        context = getActivity();
        db = new DBManager(context);
        singleItem(view);
        mList = (ListView) view.findViewById(R.id.list_month);
        adapter = new CheckAdapter(context, db.getCheckItem());
        mList.setAdapter(adapter);

        mList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                LogUtil.e("长按生效");

                String day = db.getCheckItem().get(position).getDay();
                MyDialog dialog = new MyDialog(context, "请补充操作", day, position, adapter, db, mList);
                dialog.show();
                return true;
            }
        });
    }

    private void singleItem(View view) {

        TextView month = (TextView) view.findViewById(R.id.time_month);
        TimeYMDH timeYMDH = new TimeYMDH();
        month.setText(timeYMDH.getYM());
    }



}
