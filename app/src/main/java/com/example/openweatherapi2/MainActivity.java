package com.example.openweatherapi2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter adapter;
    private int[] count = new int[40];


    private static final int REQUEST_LOCATION_PERMISSION = 1;

    private Location mLastLocation;
    private FusedLocationProviderClient mFusedLocationClient;

    public static LatLng MYLOCATION = null;
    private double longitute, latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the FusedLocationClient.
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(
                this);

        RequestQueue queue = Volley.newRequestQueue(this);


        getLastLocation(queue);
//        String url = "https://api.openweathermap.org/data/2.5/forecast?lat=" + mLastLocation.getLatitude() + "&lon=" + mLastLocation.getLongitude() + "&appid=91c0b02fe6caf490726de810d51c39bf&units=metric";
//
//        String url = "https://api.openweathermap.org/data/2.5/forecast?lat=43.728402&lon=-79.607440&appid=91c0b02fe6caf490726de810d51c39bf&units=metric";
//        String url = "https://api.openweathermap.org/data/2.5/forecast?lat=" + loc.getLatitude() + "&lon=" + loc.getLongitude() + "&appid=91c0b02fe6caf490726de810d51c39bf&units=metric";

//        Log.d("<>", url);
/*
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray list = response.getJSONArray("list");
                            double[] temp = new double[list.length()];

                            for (int i = 0; i < list.length(); i++) {
                                JSONObject object = list.getJSONObject(i);
                                JSONObject main = object.getJSONObject("main");
                                Double temperature = main.getDouble("temp");
                                temp[i] = temperature;

                            }

                            recyclerView = findViewById(R.id.recyclerView);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                            for (int i = 0; i < 40; i++) {
                                count[i] = i + 1;
                            }

                            adapter = new Adapter(MainActivity.this, getMyList(temp), count);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
// Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest); */
    }

    private ArrayList<Double> getMyList(double[] temp) {

        ArrayList<Double> doubles = new ArrayList<>();
//        Random random = new Random();

        for (int i = 0; i < 40; i++) {
            doubles.add(temp[i]);
        }

        return doubles;
    }


    private Location getLastLocation(final RequestQueue queue) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);

        } else {

            mFusedLocationClient.getLastLocation().addOnSuccessListener(
                    new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {

                                mLastLocation = location;
                                longitute = mLastLocation.getLongitude();
                                latitude = mLastLocation.getLatitude();
//                                moveMap(location);
//                                strLocation[0] = String.valueOf(mLastLocation.getLongitude());
//                                strLocation[1] = String.valueOf(mLastLocation.getLongitude());
                                Log.d("Location Coordinates", "Lat: " + mLastLocation.getLatitude() + "<<<<<>>>>> Long:" + mLastLocation.getLongitude());

                                String url = "https://api.openweathermap.org/data/2.5/forecast?lat=" + mLastLocation.getLatitude() + "&lon=" + mLastLocation.getLongitude() + "&appid=91c0b02fe6caf490726de810d51c39bf&units=metric";

                                Log.d("<>", url);

                                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                                            @Override
                                            public void onResponse(JSONObject response) {

                                                try {
                                                    JSONArray list = response.getJSONArray("list");
                                                    double[] temp = new double[list.length()];

                                                    for (int i = 0; i < list.length(); i++) {
                                                        JSONObject object = list.getJSONObject(i);
                                                        JSONObject main = object.getJSONObject("main");
                                                        Double temperature = main.getDouble("temp");
                                                        temp[i] = temperature;

                                                    }

                                                    recyclerView = findViewById(R.id.recyclerView);
                                                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                                                    for (int i = 0; i < 40; i++) {
                                                        count[i] = i + 1;
                                                    }

                                                    adapter = new Adapter(MainActivity.this, getMyList(temp), count);
                                                    recyclerView.setAdapter(adapter);

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }, new Response.ErrorListener() {

                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                // TODO: Handle error

                                            }
                                        });
                                // Access the RequestQueue through your singleton class.
                                queue.add(jsonObjectRequest);

                            } else {
//                                mLocationTextView.setText(R.string.no_location);
                            }
                        }

                    }
            );

        }
        return mLastLocation;
    }


}
