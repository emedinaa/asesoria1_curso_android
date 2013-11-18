package es.emedinaa.appasesoriaandroid;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends Activity implements LocationListener{

	private String 	latitudeISIL="-12.073449";
	private String 	longitudeISIL="-76.948551";
	private LocationManager locmag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		locmag=(LocationManager)getSystemService(LOCATION_SERVICE);
		locmag.isProviderEnabled(LocationManager.GPS_PROVIDER);
		locmag.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

	}

	public void  click_map(View view)
	{
		//location_isil();
		//routing(); //posición fija 
		routingMyLocation(); //desde mi ubicación hasta la ISIL

	}
	private void routingMyLocation()
	{
		Location loc= mylocation();
		if(loc!=null)
		{
			String latitud1=String.valueOf(loc.getLatitude());
			String longitud1=String.valueOf(loc.getLongitude());
			routing(latitud1, longitud1, latitudeISIL, longitudeISIL);
		}else
		{
			Toast.makeText(this, "Activar GPS", Toast.LENGTH_LONG).show();
		}
	}
	private Location  mylocation() {
		// TODO Auto-generated method stub
		
		locmag.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 1, this);
		locmag.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 1, this);
		
		Log.v("CONSOLE","location manager "+locmag);
		
		
		Location loc=locmag.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		return loc;
		
	}

	private void location_isil() {
		// TODO Auto-generated method stub
		Double latitude=-12.073449;
		Double longitude=-76.948551;
	    String format = "geo:0,0?q=" + Double.toString(latitude) + "," + Double.toString(longitude) +"?z=8" +
	    "(ISIL La Molina)";
	    Uri uri = Uri.parse(format); 
	    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		
		Log.v("CONSOLE","mensaje "+intent);
		if(intent!=null)
		{
			startActivity(intent);
		}
	}

	private void routing (String lang1,String long1,String lang2,String long2)
	{
		String format = "http://maps.google.com/maps?saddr=" +lang1+","+long1+"&daddr="+
				lang2+","+long2;
					
	    Uri uri = Uri.parse(format); 
	    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
	    
	    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
	    startActivity(intent);
	}
	private void routing() 
	{
		// TODO Auto-generated method stub
		//-12.07337,-76.954583
		String  latitude1="-12.07337";
		String  longitude1="-76.954583";
		
		String  latitude2="-12.073449";
		String  longitude2="-76.948551";
		
	    //String format = "geo:0,0?q=" + Double.toString(latitude) + "," + Double.toString(longitude) +"?z=8" +
	   // "(ISIL La Molina)";
		String format = "http://maps.google.com/maps?saddr=" +latitude1+","+longitude1+"&daddr="+
	   latitude2+","+longitude2;
		
	    Uri uri = Uri.parse(format); 
	    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
	    
	    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
	    startActivity(intent);
	}

	/*
	 * 
	 * String uri = "geo:"+ latitude + "," + longitude;
startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));

// You can also choose to place a point like so:
String uri = "geo:"+ latitude + "," + longitude + "?q=my+street+address";
startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));


The Possible Query params options are the following:

Show map at location: geo:latitude,longitude
Show zoomed map at location: geo:latitude,longitude?z=zoom
Show map at locaiton with point: geo:0,0?q=my+street+address
Show map of businesses in area: geo:0,0?q=business+near+city
*/
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
        String msg = "Updated Location: " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        Log.v("CONSOLE",msg );
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) 
	{
		// TODO Auto-generated method stub

	}

}
