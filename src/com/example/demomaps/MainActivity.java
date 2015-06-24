package com.example.demomaps;



import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
    GoogleMap map;
   MapFragment fragment;
    LocationManager locationManager;
    Criteria criteria;
    String provider;
    MyLocationListener   mylistener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		  criteria = new Criteria();
		  criteria.setAccuracy(Criteria.ACCURACY_COARSE);	
		  provider = locationManager.getBestProvider(criteria, false);
	    
		  // the last known location of this provider
		  Location location = locationManager.getLastKnownLocation(provider);

		  mylistener = new MyLocationListener();
	
		  if (location != null) {
			  mylistener.onLocationChanged(location);
				LatLng latLng=new LatLng(location.getLatitude(), location.getLongitude());
				Toast.makeText(getApplicationContext(), location.getLatitude()+"",Toast.LENGTH_LONG).show();
		  } else {
			
		  }
		  // location updates: at least 1 meter and 200millsecs change
		  locationManager.requestLocationUpdates(provider, 200, 1, mylistener);
	  
		fragment=(MapFragment)getFragmentManager().findFragmentById(R.id.map);
		map=fragment.getMap();
		LatLng latLng=new LatLng(26.8000, 82.7400);
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		map.addMarker(new MarkerOptions().position(latLng).title("Google Maps")
				.snippet("This maps.is dev by Vararags")
				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
	}
	public class MyLocationListener implements LocationListener
	{

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			LatLng latLng=new LatLng(location.getLatitude(), location.getLongitude());	
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
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
