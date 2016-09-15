package com.alluresoft.gettogether;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alluresoft.gettogether.fragments.OneFragment;
import com.alluresoft.gettogether.fragments.ThreeFragment;
import com.alluresoft.gettogether.fragments.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class MeetupDetailsActivity extends AppCompatActivity {

    private ImageView imageViewRound,send_request,attendedUserPic;
    private Button shareIt;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int attend=23,comment=55,like=165;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetup_details);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar_meetup_details);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();


        //for rounded image view
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.profile_pic1);
        Bitmap circularBitmap = RoundedImageView.getRoundedCroppedBitmap(bitmap, 100);

        imageViewRound=(ImageView)findViewById(R.id.imageView_round);
        imageViewRound.setImageBitmap(circularBitmap);
/**
        attendedUserPic=(ImageView) findViewById(R.id.attended_user_pic1);
        attendedUserPic.setImageBitmap(circularBitmap);

        attendedUserPic=(ImageView) findViewById(R.id.attended_user_pic2);
        attendedUserPic.setImageBitmap(circularBitmap);
*/

        send_request=(ImageView) findViewById(R.id.send_request);
        send_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MeetupDetailsActivity.this, "Request sent...", Toast.LENGTH_SHORT).show();
            }
        });

        shareIt=(Button) findViewById(R.id.share_it_button);
        shareIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt();
            }
        });



    }
    //sharing implementation here
    private void shareIt() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AndroidSolved");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Share this event on over the web...");
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    /**
     * Adding custom view to tab
     */
    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.meetup_details_page_tab, null);
        tabOne.setText(attend + "\nAttending");
        tabOne.setTextSize(17);
        tabOne.setTextColor(getResources().getColor(R.color.blackNormal));
        tabOne.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_perm_identity_black_24dp,0,0,0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.meetup_details_page_tab, null);
        tabTwo.setText( comment +"\nComments");
        tabTwo.setTextSize(17);
        tabTwo.setTextColor(getResources().getColor(R.color.blackNormal));
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_comment_black_24dp,0, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.meetup_details_page_tab, null);
        tabThree.setText(like + "\nLikes");
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
