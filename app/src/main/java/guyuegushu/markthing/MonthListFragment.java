package guyuegushu.markthing;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/16.
 */

public class MonthListFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_list, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        singleItem(view);
        Context context = getActivity();
        DBManager db = new DBManager(context);
        ListView mList = (ListView) view.findViewById(R.id.list_month);
        CheckAdapter adapter = new CheckAdapter(context, db.getCheckItem());
        mList.setAdapter(adapter);
    }

    private void singleItem(View view) {

        TextView month = (TextView) view.findViewById(R.id.time_month);
        TimeYMDH timeYMDH = new TimeYMDH();
        month.setText(timeYMDH.getYM());
    }

}
