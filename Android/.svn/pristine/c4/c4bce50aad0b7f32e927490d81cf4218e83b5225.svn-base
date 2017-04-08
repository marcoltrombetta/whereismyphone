package phone.gps.fragments;

import android.graphics.Canvas;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import phone.gps.R;
import phone.gps.obj.Globals;
import phone.gps.obj.PhoneInfo;
import phone.gps.obj.PositionInfo;
import phone.gps.runnable.PhoneInfoRunnable;
import phone.gps.runnable.PositionInfoRunnable;

/**
 * Created by marco on 3/19/16.
 */

//https://github.com/osmdroid/osmdroid/wiki/How-to-use-the-osmdroid-library

public class MapFragment extends Fragment {
    MapView map;

    PositionInfoRunnable.PositionInfoInterface positionInfoInterface=new PositionInfoRunnable.PositionInfoInterface() {

        @Override
        public void onComplete(final List<PositionInfo> phoi) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (phoi != null) {
                        map.getOverlays().clear();
                        // Create an ArrayList with overlays to display objects on map
                        ArrayList<OverlayItem> overlayItemArray = new ArrayList<OverlayItem>();

                        for (PositionInfo p : phoi) {
                            // Create som init objects
                            OverlayItem linkopingItem = new OverlayItem(p.getPhoneName().getDesc(), p.getPhoneInfo().getImei(),
                                    new GeoPoint(p.getLatitude(), p.getLongitude()));

                            // Add the init objects to the ArrayList overlayItemArray
                            overlayItemArray.add(linkopingItem);
                        }

                        // Add the Array to the IconOverlay
                        ItemizedIconOverlay<OverlayItem> itemizedIconOverlay = new ItemizedIconOverlay<OverlayItem>(getActivity(), overlayItemArray, null);

                        // Add the overlay to the MapView
                        map.getOverlays().add(itemizedIconOverlay);
                    }
                }
            });
        }

        @Override
        public void onError(Exception ex) {

        }
    };

    public MapFragment() {
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        map = (MapView) view.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);

        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(9);

        Location location= null;

        try {
            location = Globals.getLocation(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(location!=null) {
            GeoPoint startPoint = new GeoPoint(location.getLatitude(),location.getLongitude());
            mapController.setCenter(startPoint);
        }

        getPhones();

        super.onViewCreated(view, savedInstanceState);
    }

    private void getPhones(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                new Thread(new PositionInfoRunnable(positionInfoInterface, getActivity().getApplicationContext())).start();
            }
        }, 1000, 100000);

    }
}
