package com.alluresoft.gettogether;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DiscoverTinderActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener{

    int[] mResources = {R.drawable.cover, R.drawable.album1, R.drawable.login_background};

    ViewPager mViewPager;
    private CustomPagerAdapter mAdapter;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;

    private ImageView friend1,friend2,friend3,friend4,friend5;
    private ImageView nearby,followers,profile,discover,favourites;
    private Button attend,shareIt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_tinder);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar_discover_tinder);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.profile_pic1);
        Bitmap circularBitmap = RoundedImageView.getRoundedCroppedBitmap(bitmap, 100);

        friend1=(ImageView)findViewById(R.id.friend1);
        friend1.setImageBitmap(circularBitmap);

        friend2=(ImageView)findViewById(R.id.friend2);
        friend2.setImageBitmap(circularBitmap);

        friend3=(ImageView)findViewById(R.id.friend3);
        friend3.setImageBitmap(circularBitmap);

        friend4=(ImageView)findViewById(R.id.friend4);
        friend4.setImageBitmap(circularBitmap);

        friend5=(ImageView)findViewById(R.id.friend5);
        friend5.setImageBitmap(circularBitmap);

        attend=(Button) findViewById(R.id.attend_btn_discover);
        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DiscoverTinderActivity.this,"attend",Toast.LENGTH_SHORT).show();
            }
        });

        shareIt=(Button) findViewById(R.id.share_it_button);
        shareIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt();
            }
        });

        //for image sliding
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        mAdapter = new CustomPagerAdapter(this, mResources);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(this);

        setPageViewIndicator();
/**
        nearby=(ImageView) findViewById(R.id.nearby);
        followers=(ImageView) findViewById(R.id.followers);
        profile=(ImageView) findViewById(R.id.profile);
        favourites=(ImageView) findViewById(R.id.favourites);
        discover=(ImageView) findViewById(R.id.discover);
*/

    }

    public void onIconClick(View v){

        switch(v.getId()){
            case R.id.nearby_map_icon:
                Toast.makeText(DiscoverTinderActivity.this, "Nearby", Toast.LENGTH_SHORT).show();
                break;
            case R.id.followers_map_icon:
                Toast.makeText(DiscoverTinderActivity.this, "Followers", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile_map_icon:
                Toast.makeText(DiscoverTinderActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favourites_map_icon:
                Toast.makeText(DiscoverTinderActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
                break;
            case R.id.discover_map_icon:
                Toast.makeText(DiscoverTinderActivity.this, "Discover", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //sharing implementation here
    private void shareIt() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AndroidSolved");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Share this event on over the web...");
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_other_profile, menu);
        SearchView searchView=(SearchView) menu.findItem(R.id.menu_discover_search).getActionView();
        SearchManager searchManager=(SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }
    //for image changing dots and image change
    private void setPageViewIndicator() {

        Log.d("###setPageViewIndicator", " : called");
        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            final int presentPosition = i;
            dots[presentPosition].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mViewPager.setCurrentItem(presentPosition);
                    return true;
                }

            });


            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.item_selected));
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        Log.d("###onPageSelected, pos ", String.valueOf(position));
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.item_selected));

        if (position + 1 == dotsCount) {

        } else {

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //adapter class for dots
    public class CustomPagerAdapter extends PagerAdapter {
        private Context mContext;
        LayoutInflater mLayoutInflater;
        private int[] mResources;

        public CustomPagerAdapter(Context context, int[] resources) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mResources = resources;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View itemView = mLayoutInflater.inflate(R.layout.walk_through_swip_layout,container,false);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageResource(mResources[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
           /* LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(950, 950);
            imageView.setLayoutParams(layoutParams);*/
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return mResources.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
