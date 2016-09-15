package com.alluresoft.gettogether;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class OtherProfileActivity extends AppCompatActivity {

    private int event=100,attended=10,followers=45,following=20;
    private Button follow_btn_other_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);
        Toolbar toolbar_other_profile = (Toolbar) findViewById(R.id.toolbar_on_other_profile);
        setSupportActionBar(toolbar_other_profile);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent searchIntent=getIntent();
        if(Intent.ACTION_SEARCH.equals(searchIntent.getAction())){
            String query=searchIntent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(OtherProfileActivity.this, query, Toast.LENGTH_SHORT).show();
        }

        initCollapsingToolbar();
        //for sending text
        TextView events=(TextView) findViewById(R.id.events_count_other_profile);
        events.setText(String.valueOf(event));
        TextView attend=(TextView) findViewById(R.id.attended_count_other_profile);
        attend.setText(String.valueOf(attended));
        TextView follower=(TextView) findViewById(R.id.followers_count_other_profile);
        follower.setText(String.valueOf(followers));
        TextView followi=(TextView) findViewById(R.id.following_count_other_profile);
        followi.setText(String.valueOf(following));

        follow_btn_other_profile=(Button) findViewById(R.id.follow_btn_other_profile);
        follow_btn_other_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"yor are following",Toast.LENGTH_SHORT).show();
            }
        });


        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.profile_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_other_profile, menu);
        SearchView searchView=(SearchView) menu.findItem(R.id.menu_search).getActionView();
        SearchManager searchManager=(SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }
//icon click listener
    public void onIconClick(View v){

        switch(v.getId()){
            case R.id.nearby_map_icon:
                Toast.makeText(OtherProfileActivity.this, "Nearby", Toast.LENGTH_SHORT).show();
                break;
            case R.id.followers_map_icon:
                Toast.makeText(OtherProfileActivity.this, "Followers", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile_map_icon:
                Toast.makeText(OtherProfileActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favourites_map_icon:
                Toast.makeText(OtherProfileActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
                break;
            case R.id.discover_map_icon:
                Toast.makeText(OtherProfileActivity.this, "Discover", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
