package guyuegushu.markthing;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Administrator on 2017/2/9.
 *
 */

public class MyDialog extends Dialog {

    private String dialogName;
    private String day;
    private Context context;
    private CheckAdapter adapter;
    private DBManager db;
    private ListView mList;
    private int position;
    public CheckItem checkItem = new CheckItem();

    public MyDialog(Context context, String dialogName, String day, int position, CheckAdapter adapter, DBManager db, ListView mList) {
        super(context);
        this.dialogName = dialogName;
        this.day = day;
        this.context = context;
        this.position = position;
        this.adapter = adapter;
        this.db = db;
        this.mList = mList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mark_list_mydialog);
        dialogInit();
        setTitle(dialogName);
    }

    private void dialogInit() {
        RadioGroup am_group = (RadioGroup) findViewById(R.id.am_group);
        RadioGroup pm_group = (RadioGroup) findViewById(R.id.pm_group);

        Button btn_yes = (Button) findViewById(R.id.dialog_yes);
        Button btn_no = (Button) findViewById(R.id.dialog_no);

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.saveCheckItem(checkItem);
                adapter.updateItem(position, mList);
                dismiss();
            }
        });

        final RadioButton yes_am = (RadioButton) findViewById(R.id.yes_am);
        final RadioButton no_am = (RadioButton) findViewById(R.id.no_am);
        final RadioButton yes_pm = (RadioButton) findViewById(R.id.yes_pm);
        final RadioButton no_pm = (RadioButton) findViewById(R.id.no_pm);

        am_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == yes_am.getId()) {
                    checkItem.setAm_checkBox(true);
                    checkItem.setMain_checked(true);
                    checkItem.setAm_checked(true);
                } else if (i == no_am.getId()) {
                    checkItem.setAm_checkBox(false);
                    checkItem.setMain_checked(true);
                    checkItem.setAm_checked(true);
                } else {

                }
            }
        });

        pm_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == yes_pm.getId()) {
                    checkItem = new CheckItem(day, true, true, true, false, true);
                } else if (i == no_pm.getId()) {
                    checkItem = new CheckItem(day, true, false, true, true, true);
                }
            }
        });
    }

}
