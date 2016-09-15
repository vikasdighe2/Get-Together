package com.alluresoft.gettogether;

import android.app.SearchManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    JSONArray latlogResult;
    InputStream is=null;
    String result=null;
    String line=null;
    private String user_name="Person Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
        }

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar_discover_tinder);
        setSupportActionBar(toolbar);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_other_profile, menu);
        SearchView searchView=(SearchView) menu.findItem(R.id.menu_discover_search).getActionView();
        SearchManager searchManager=(SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        GPSTracker mGPS = new GPSTracker(this);
        // check if GPS enabled
        GPSTracker gpsTracker = new GPSTracker(this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.profile_pic1);
        Bitmap circularBitmap = RoundedImageView.getRoundedCroppedBitmap(bitmap, 100);

        if (gpsTracker.getIsGPSTrackingEnabled())
        {

            LatLng currentLocation = new LatLng( gpsTracker.latitude, gpsTracker.longitude);
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
            mMap.addMarker(new MarkerOptions().position(currentLocation).icon(bitmapDescriptor));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15));
            mMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());
        }

        JSONArray getLatLongResult = getLatitudeAndLongitude();
        try {
            for (int i = 0; i < getLatLongResult.length(); i++) {
                JSONObject object3 = getLatLongResult.getJSONObject(i);
                String latitude = object3.getString("latitude");
                String longitude = object3.getString("longitude");
                LatLng loc1 = new LatLng( Double.parseDouble(latitude),  Double.parseDouble(longitude));
                mMap.addMarker(new MarkerOptions().position(loc1).icon(BitmapDescriptorFactory.fromBitmap(circularBitmap)));
            }
        }  catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
    }

    //bottom icon listener
    public void onClick(View v){

        switch(v.getId()){
            case R.id.nearby_map_icon:
                Toast.makeText(MapsActivity.this, "Nearby", Toast.LENGTH_SHORT).show();
                break;
            case R.id.followers_map_icon:
                Toast.makeText(MapsActivity.this, "Followers", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile_map_icon:
                Toast.makeText(MapsActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favourites_map_icon:
                Toast.makeText(MapsActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
                break;
            case R.id.discover_map_icon:
                Toast.makeText(MapsActivity.this, "Discover", Toast.LENGTH_SHORT).show();
                break;
        }

    }
    public JSONArray getLatitudeAndLongitude()
    {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("id","1"));

        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://192.168.1.103:8080/select.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        }
        catch(Exception e)
        {
            Log.d("Fail 1", e.toString());
            Toast.makeText(getApplicationContext(), "Server Exception",
                    Toast.LENGTH_LONG).show();
        }

        try
        {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try
        {
            JSONObject json_data = new JSONObject(result);
            latlogResult = (json_data.getJSONArray("result"));
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
        return latlogResult;
    }
    // Added custom window class for map

    public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter
    {
        public MarkerInfoWindowAdapter()
        {
        }

        @Override
        public View getInfoWindow(Marker marker)
        {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker)
        {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.profile_pic1);
            Bitmap circularBitmap = RoundedImageView.getRoundedCroppedBitmap(bitmap, 50);
            View v  = getLayoutInflater().inflate(R.layout.infowindow_layout, null);

            ImageView markerIcon = (ImageView) v.findViewById(R.id.marker_icon);
            TextView markerLabel = (TextView)v.findViewById(R.id.marker_label);
            TextView user_location_name=(TextView) v.findViewById(R.id.user_location_name);

            markerIcon.setImageBitmap(circularBitmap);

            markerLabel.setText("Sagar Kumavat");
            user_location_name.setText("Sangamner,Maharashtra");



            return v;
        }
    }
}
