package com.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private final String CICLO_VIDA = "CICLO_VIDA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(CICLO_VIDA, "Se ha llamado al método onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds weekForecast to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        if (id == R.id.action_map) {
            openPreferredLocationInMap();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationInMap() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String location = sharedPreferences.getString(
                getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));

        //Creamos la URL para pasar al intent
        Uri geolocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q",location)
                .build();
        Intent mapa = new Intent(Intent.ACTION_VIEW);
        mapa.setData(geolocation);
        if(mapa.resolveActivity(getPackageManager()) != null){
            startActivity(mapa);
        }else{
            Log.d(LOG_TAG, "No se ha podido entrar en "+ location + "no hay apps");
        }
    }

    @Override
    protected void onPause(){
        Log.d(CICLO_VIDA, "Se ha llamado al método onPause()");
        super.onPause();
    }

    @Override
    protected void onStart(){
        Log.d(CICLO_VIDA, "Se ha llamado al método onStart()");
        super.onStart();
    }

    @Override
    protected void onStop(){
        Log.d(CICLO_VIDA, "Se ha llamado al método onStop()");
        super.onStop();
    }

    @Override
    protected void onResume(){
        Log.d(CICLO_VIDA, "Se ha llamado al método onResume()");
        super.onResume();
    }

    @Override
    protected void onDestroy(){
        Log.d(CICLO_VIDA, "Se ha llamado al método onDestroy()");
        super.onDestroy();
    }
}
