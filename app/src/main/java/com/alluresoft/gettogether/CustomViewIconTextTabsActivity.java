package com.alluresoft.gettogether;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.TextView;

import com.alluresoft.gettogether.fragments.OneFragment;
import com.alluresoft.gettogether.fragments.ThreeFragment;
import com.alluresoft.gettogether.fragments.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class CustomViewIconTextTabsActivity extends AppCompatActivity {

    private int attend=23,comment=55,love=165; // value + name as tab name
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom_view_icon_text_tabs);

        toolbar = (Toolbar) findViewById(R.id.comment_window_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    /**
     * Adding custom view to tab
     */
    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText(attend + "\nAttending");
        tabOne.setTextSize(17);
        tabOne.setTextColor(getResources().getColor(R.color.blackNormal));
        tabOne.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_perm_identity_black_24dp,0,0,0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText( comment +"\nComments");
        tabTwo.setTextSize(17);
        tabTwo.setTextColor(getResources().getColor(R.color.blackNormal));
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_comment_black_24dp,0, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText(love + " + You");
        tabThree.setTextSize(17);
        tabThree.setTextColor(getResources().getColor(R.color.blackNormal));
        tabThree.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite_border_black_24dp,0, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }

    /**
     * Adding fragments to ViewPager
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "ONE");
        adapter.addFrag(new TwoFragment(), "TWO");
        adapter.addFrag(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
