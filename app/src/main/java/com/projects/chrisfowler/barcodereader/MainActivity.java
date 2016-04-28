package com.projects.chrisfowler.barcodereader;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class MainActivity extends Activity implements OnClickListener, LocationListener {
    private Button scanBtn, searchBtn;
    private TextView formatTxt, contentTxt, locTxt;
    private String lat, lon;
    private int upc_code;
    //private static final String URL = "http://www.searchupc.com/handlers/upcsearch.ashx?request_type=1&access_token=0A525935-076D-4445-88C7-62FA354FE910&upc=";
    private LocationManager locationManager;
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = (Button) findViewById(R.id.scan_button);
        formatTxt = (TextView) findViewById(R.id.scan_format);
        contentTxt = (TextView) findViewById(R.id.scan_content);
        locTxt = (TextView) findViewById(R.id.loc);
        searchBtn = (Button) findViewById(R.id.search);
        scanBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //parameters are provider, minTime between location updates (milliseconds), min distance (meters), and listener.
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                2000,
                10, this);

    }


    public void onClick(View v) {
        //respond to click
        if (v.getId() == R.id.scan_button) {
            //scan
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
            searchBtn.isShown();
            locTxt.isShown();
        }
        if (v.getId() == R.id.search) {
            //lookup item

            //String message = null;
            //try {
             //   message = URLEncoder.encode(scanContent, "UTF-8");
            //} catch (UnsupportedEncodingException e) {
            //    e.printStackTrace();
           // }

            if (android.os.Build.VERSION.SDK_INT > 9)
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            try {

                URL url = new URL("http://www.searchupc.com/handlers/upcsearch.ashx?request_type=1&access_token=0A525935-076D-4445-88C7-62FA354FE910&upc=" + "078742089683"); // should return diphenhydramine (alergy medicine)

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("GET");
                OutputStreamWriter writer = new OutputStreamWriter(
                        connection.getOutputStream());
                writer.write("message=" + contentTxt);
                writer.close();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                } else {
                    // Server returned HTTP error code.
                }
            } catch (MalformedURLException e) {
                // ...
            } catch (IOException e) {
                // ...
            }
        }
        }




    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            //set test on scan result.
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT : " + scanContent);
            locTxt.setText("Your Location is : " + "\n" + "Latitude: " + lat + "\n" + "Longitude: " + lon);

            }
            else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();

        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.projects.chrisfowler.barcodereader/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.projects.chrisfowler.barcodereader/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    @Override
    public void onLocationChanged(Location location) {
        String msg = "New Latitude: "+location.getLatitude()+"New Longitude: "+location.getLongitude();
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

        locTxt.setText("Your Location is : " + "\n" + "Latitude: " + location.getLatitude() + "\n" + "Longitude: " + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

        Toast.makeText(getBaseContext(), "Gps is turned on!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);

        Toast.makeText(getBaseContext(), "Gps is turned off!! ",
                Toast.LENGTH_SHORT).show();
    }
}




