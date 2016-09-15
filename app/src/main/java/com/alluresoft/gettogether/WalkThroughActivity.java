package com.alluresoft.gettogether;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class WalkThroughActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

        int[] mResources = {R.drawable.login_background, R.drawable.walkthrough, R.drawable.login_background};

        ViewPager mViewPager;
        private CustomPagerAdapter mAdapter;
        private LinearLayout pager_indicator;
        private int dotsCount;
        private ImageView[] dots;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_walk_through);

            mViewPager = (ViewPager) findViewById(R.id.viewpager);
            pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
            mAdapter = new CustomPagerAdapter(this, mResources);
            mViewPager.setAdapter(mAdapter);
            mViewPager.setCurrentItem(0);
            mViewPager.setOnPageChangeListener(this);

            setPageViewIndicator();

        }

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