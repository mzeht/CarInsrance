package com.wpmac.carinsrance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.debug.L;

import butterknife.ButterKnife;

/**
 * An activity representing a single CarInsrance detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link CarInsranceListActivity}.
 */
public class PeijianDetailActivity extends AppCompatActivity {

    public String imageurl;
    TextView name_tv;
    TextView pinpai_tv;
    TextView price_tv;
    TextView type_tv;
    TextView description_tv;
    private String name;
    private int price;
    private String pinpai;
    private String type;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carinsrance_detail);
        ButterKnife.bind(this);
        imageurl = getIntent().getStringExtra("imageurl");
        name = getIntent().getStringExtra("name");
        price = getIntent().getIntExtra("price", 0);
        pinpai = getIntent().getStringExtra("pinpai");
        type = getIntent().getStringExtra("type");
        id = getIntent().getIntExtra("id", 0);
        L.i("intent", imageurl + name + String.valueOf(price) + pinpai + String.valueOf(id));
        initView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "已加入购物车", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
//        if (savedInstanceState == null) {
//            // Create the detail fragment and add it to the activity
//            // using a fragment transaction.
//            Bundle arguments = new Bundle();
//            arguments.putString(CarInsranceDetailFragment.ARG_ITEM_ID,
//                    getIntent().getStringExtra(CarInsranceDetailFragment.ARG_ITEM_ID));
//            CarInsranceDetailFragment fragment = new CarInsranceDetailFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.carinsrance_detail_container, fragment)
//                    .commit();
//        }
    }

    private void initView() {
        ImageView imageView = (ImageView) findViewById(R.id.detail_imgview);
        Picasso.with(getApplicationContext()).load("http://o7twp9p7v.bkt.clouddn.com/image/detailsimple/detailpeijian.jpg").into(imageView);
        name_tv= (TextView) findViewById(R.id.name);
        price_tv= (TextView) findViewById(R.id.price);
        pinpai_tv= (TextView) findViewById(R.id.pinpai);
        type_tv= (TextView) findViewById(R.id.type);
        description_tv= (TextView) findViewById(R.id.description);
        description_tv.setText("【迈氏认证】东风日产骐达新骐达 1.6L 无级变速 (2011-2016)无级变速箱总成");
        name_tv.setText(pinpai+"的"+type);
        price_tv.setText(price+"");
        type_tv.setText(type);
        pinpai_tv.setText(pinpai);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, CarInsranceListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
