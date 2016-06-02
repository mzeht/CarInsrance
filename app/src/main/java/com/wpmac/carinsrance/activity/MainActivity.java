package com.wpmac.carinsrance.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;
import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.base.BasePreference;
import com.wpmac.carinsrance.base.CarInsuranceActivity;
import com.wpmac.carinsrance.debug.L;
import com.wpmac.carinsrance.fragment.CarinsacneFragment;
import com.wpmac.carinsrance.fragment.CartFragment;
import com.wpmac.carinsrance.fragment.CustomersInfoFragment;
import com.wpmac.carinsrance.fragment.MaintenanceFragment;
import com.wpmac.carinsrance.fragment.OrdersFragment;
import com.wpmac.carinsrance.fragment.ShoppingFragment;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends CarInsuranceActivity {

    private String userId;
    private int mCurrentMenu;
    private DrawerLayout drawer;
    private FloatingActionButton fab;
    //首页
    private ShoppingFragment ShoppingFragment;
    //
    private CustomersInfoFragment customersInfoFragment;
    private OrdersFragment ordersFragment;
    private CarinsacneFragment carinsacneFragment;
    private MaintenanceFragment maintenanceFragment;
//    private PeijianFragment peijianFragment;
    private CartFragment cartFragment;
    private TextView userNameTextView;
    private View view;
    public ProgressDialog ProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switchFragment(item.getItemId());
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main);
        userNameTextView= (TextView) headerLayout.findViewById(R.id.user_name_textview);
        L.i(BasePreference.getInstance().getUserName().toString());
        userNameTextView.setText(BasePreference.getInstance().getUserName().toString());
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            userId = currentUser.getObjectId();
        }
        switchFragment(R.id.nav_home);
    }

    private void replaceMainFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        if (fragment.isAdded()) {
            Toast.makeText(getApplicationContext(), "isadded", Toast.LENGTH_SHORT).show();

        } else {
            manager.beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .commit();
        }
    }

    private void switchFragment(int menuId) {
        mCurrentMenu = menuId;
        ActionBar actionBar = getSupportActionBar();
        switch (menuId) {
            case R.id.nav_home:
                if (actionBar != null) {
                    actionBar.setTitle(R.string.nav_home);
                }
                if (ShoppingFragment == null) {
                    ShoppingFragment = com.wpmac.carinsrance.fragment.ShoppingFragment.getInstance();
                }
                replaceMainFragment(ShoppingFragment);
                break;
            case R.id.nav_caigoupeijian:
//                if (actionBar != null) {
//                    actionBar.setTitle("采购配件");
//                }
//                if (peijianFragment == null) {
//                    peijianFragment = PeijianFragment.getInstance();
//                }
//                replaceMainFragment(peijianFragment);
                Intent intent=new Intent();
                intent.setClass(this,PeiJianActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_mycart:
                if (actionBar != null) {
                    actionBar.setTitle("购物车");
                }
                if (cartFragment == null) {
                    cartFragment = CartFragment.getInstance();
                }
                replaceMainFragment(cartFragment);
                break;
            case R.id.nav_mycoustomer:
                if (actionBar != null) {
                    actionBar.setTitle(R.string.nav_mycoustomer);
                }
                if (customersInfoFragment == null) {
                    customersInfoFragment = CustomersInfoFragment.getInstance();
                }
                replaceMainFragment(customersInfoFragment);
                break;
            case R.id.nav_mycarinsacne:
                if (actionBar != null) {
                    actionBar.setTitle(R.string.nav_mycarinsacne);
                }
                if (carinsacneFragment == null) {
                    carinsacneFragment = CarinsacneFragment.getInstance();
                }
                replaceMainFragment(carinsacneFragment);
                break;
//            case R.id.nav_fixlist:
//                if (actionBar != null) {
//                    actionBar.setTitle(R.string.nav_fixlist);
//                }
//                if (maintenanceFragment == null) {
//                    maintenanceFragment = MaintenanceFragment.getInstance();
//                }
//                replaceMainFragment(maintenanceFragment);
//                break;
//            case R.id.nav_orders:
//                if (actionBar != null) {
//                    actionBar.setTitle(R.string.nav_orders);
//                }
//                if (ordersFragment == null) {
//                    ordersFragment = OrdersFragment.getInstance();
//                }
//                replaceMainFragment(ordersFragment);
//                break;
            case R.id.nav_about:
//                if (actionBar != null) {
//                    actionBar.setTitle(R.string.meas_data);
//                }
//                if (measFragment == null) {
//                    measFragment = MeasFragment.getInstance();
//                }
//                replaceMainFragment(measFragment);
//                Intent intent=new Intent();
//                intent.setClass(this,PeijianDetailActivity.class);
//                startActivity(intent);

                intent = new Intent();
                intent.setClass(this,AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_setting:
//                if (actionBar != null) {
//                    actionBar.setTitle(R.string.point_data);
//                }
//                if (pointsFragment == null) {
//                    pointsFragment = PointsFragment.getInstance();
//                }
//                replaceMainFragment(pointsFragment);
                intent = new Intent();
                intent.setClass(this,SettingsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            BasePreference.getInstance().saveIsLogin("false");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // 双击退出函数

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            exitByDoubleClick(); // 调用双击退出函数
        }
        return true;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitByDoubleClick() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            List<Fragment> list = getSupportFragmentManager().getFragments();
            finish();
            System.exit(0);
        }
    }
}
