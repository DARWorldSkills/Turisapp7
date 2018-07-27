package com.aprendiz.ragp.turisapp7.maps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.aprendiz.ragp.turisapp7.R;
import com.aprendiz.ragp.turisapp7.controllers.MenuT;
import com.aprendiz.ragp.turisapp7.models.Lugares;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Todos extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    Location location;

    Marker marker;

    double latitud = 0.0;
    double longitud = 0.0;

    final int MY_LOCATION = 0;

    private static final int LOCATION_REQUEST = 500;
    ArrayList<LatLng> listpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        listpoint = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_LOCATION);

        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);

            return;
        }
        mMap.setMyLocationEnabled(true);

        miUbicacion();
        Lugares lugares = MenuT.lugar;
        double lat = lugares.getLatitud();
        double lon = lugares.getLongitud();

        LatLng sydney1 = new LatLng(lat,lon);
        mMap.addMarker(new MarkerOptions().position(sydney1).title(MenuT.lugar.getNombre()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney1));

        String url = getRequestUrl(new LatLng(location.getLatitude(),location.getLongitude()), sydney1);

        TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
        taskRequestDirections.execute(url);

        //mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
        //  @Override
        // public void onMapClick(LatLng latLng) {
        //Resetear Marcador

        //   if (listpoint.size() == 2) {
        //     listpoint.clear();
        //   mMap.clear();

        //}

        // save firts point

        //listpoint.add(latLng);

        //crearMarcador

        //MarkerOptions markerOptions = new MarkerOptions();
        //markerOptions.position(latLng);

        //if (listpoint.size() == 1) {
        //add first point in the map

        //  markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        // } else {
        //add second markert

        //   markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        //}
        //mMap.addMarker(markerOptions);

        //if (listpoint.size() == 2) {
        //Create the url


        //}
        //}
        //});

    }

    private void    miUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_LOCATION);

        {
            if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                return;
            }

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            actualizarUbicacion(location);
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 15000, 0, locationListener);
        }
    }

    private void actualizarUbicacion(Location location) {

        if (location != null) {
            latitud = location.getLatitude();
            longitud = location.getLongitude();
            agregarMarcador(latitud, longitud);
        }
    }

    private void agregarMarcador(double latitud, double longitud) {

        LatLng marca = new LatLng(latitud, longitud);
        CameraUpdate miubicacion = CameraUpdateFactory.newLatLngZoom(marca, 14);
        if (marker != null) marker.remove();
        marker = mMap.addMarker(new MarkerOptions().position(marca).title("Estas aquí"));
        mMap.animateCamera(miubicacion);
    }


    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private String getRequestUrl(LatLng origin, LatLng destino) {
        String str_org = "origin=" + origin.latitude + "," +origin.longitude;
        //Value of destination
        String str_destino = "destination=" + destino.latitude+","+destino.longitude;
        //set value
        String sensor = "sensor=false";
        //Mode for find direction
        String mode = "mode=driving";
        //Build the full
        String param = str_org +"&" + str_destino + "&" +sensor+"&" + mode;
        //Output format
        String output = "json";
        //Create url request
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + param;
        return url;

    }

    private String requestDirection(String reqUrl) throws IOException {
        String responseString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(reqUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            // get the respons result

            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);

            }

            responseString = stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();

            }
            httpURLConnection.disconnect();
        }
        return responseString;

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    mMap.setMyLocationEnabled(true);
                }

                break;
        }
    }

    public class TaskRequestDirections extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String responseString = "";
            try {
                responseString = requestDirection(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //Parse json

            TaskParser taskParser = new TaskParser();
            taskParser.execute(s);
        }
    }

    public class TaskParser extends AsyncTask<String, Void, List<List<HashMap<String, String>>>> {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {

            JSONObject jsonObject = null;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jsonObject = new JSONObject(strings[0]);
                DirectionsParser directionsParser = new DirectionsParser();
                routes = directionsParser.parse(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return routes;

        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
            //Get list routes  and display it into the map

            ArrayList points = null;

            PolylineOptions polylineOptions = null;

            for (List<HashMap<String, String>> path : lists) {


                points = new ArrayList();
                polylineOptions = new PolylineOptions();

                for (HashMap<String, String> point : path) {
                    double lat = Double.parseDouble(point.get("lat"));
                    double lon = Double.parseDouble(point.get("lon"));

                    points.add(new LatLng(lat, lon));

                }

                polylineOptions.addAll(points);
                polylineOptions.width(15);
                polylineOptions.color(Color.BLUE);
                polylineOptions.geodesic(true);
            }

            if (polylineOptions != null) {

                mMap.addPolyline(polylineOptions);

            } else {
                Toast.makeText(getApplicationContext(), "Directions not found ", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
