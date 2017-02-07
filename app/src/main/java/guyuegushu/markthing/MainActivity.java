package guyuegushu.markthing;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements OnClickCallBackListener {

    private MarkListFragment mlF = new MarkListFragment();
    private MarkSingleFragment mF = new MarkSingleFragment();
    private List<String> menus;
    private OnClickCallBackListener onClick;
    private DrawerLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawlayout_main);
        homepage();
        setMenuList();
    }

    private HomepageFragment hpF = new HomepageFragment();
    private void homepage(){

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .remove(mlF)
                .remove(mF)
                .replace(R.id.content_frame, hpF)
                .commit();
    }

    ListView menuList;
    private void setMenuList(){
        layout = (DrawerLayout) findViewById(R.id.root_layout);
        menus = new ArrayList<>();
        menus.add("主页");
        menus.add("打卡");
        menus.add("记录");

        onClick = this;

        menuList = (ListView) findViewById(R.id.menu_list);
        MenuAdapter adapter = new MenuAdapter();
        menuList.setAdapter(adapter);

    }

    @Override
    public void OnClick(int position) {
        switch (menus.get(position)){
            case "主页":
                homepage();
                layout.closeDrawer(menuList);
                break;
            case "打卡":
                markSingle();
                layout.closeDrawer(menuList);
                break;
            case "记录":
                markList();
                layout.closeDrawer(menuList);
            default:
                break;
        }
    }

    class MenuAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return menus.size();
        }

        @Override
        public Object getItem(int i) {
            return menus.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, final ViewGroup viewGroup) {

            view = LayoutInflater.from(MainActivity.this).inflate(R.layout.drawlayout_menu_item, null);

            TextView menuText = (TextView) view.findViewById(R.id.menu_text);

            menuText.setText(menus.get(i));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.OnClick(i);
                }
            });

            return view;
        }
    }

    private void markSingle(){

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.content_frame, mF)
                .addToBackStack(null)
                .commit();
    }

    private void markList(){

        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, mlF)
                .addToBackStack(null)
                .commit();
    }

}
