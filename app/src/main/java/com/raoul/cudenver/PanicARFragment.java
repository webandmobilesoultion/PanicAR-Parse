package com.raoul.cudenver;

import android.content.Intent;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.dopanic.panicarkit.lib.PARFragment;
import com.dopanic.panicarkit.lib.PARController;
import com.dopanic.panicarkit.lib.PARPoi;
import com.dopanic.panicarkit.lib.PARPoiLabel;
import com.dopanic.panicarkit.lib.PARPoiLabelAdvanced;
import com.dopanic.panicsensorkit.PSKDeviceAttitude;
import com.dopanic.panicsensorkit.enums.PSKDeviceOrientation;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by doPanic on 21.02.14.
 * Fragment providing AR functionality
 * configure and add content here
 */
public class PanicARFragment extends PARFragment {

    //==============================================================================================
    // Variable declaration
    //==============================================================================================
    private static ArrayList<PARPoiLabel> labelRepo = new ArrayList<PARPoiLabel>();
    String positionname;
    String positionaddress;
    Double latitude;
    Double longitude;

    //==============================================================================================
    // Lifecycle
    //==============================================================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Intent intent=getActivity().getIntent();
        String displaystring=intent.getStringExtra("positionID");
       // Toast.makeText(getActivity(),displaystring,Toast.LENGTH_LONG).show();


         //

        if (!(displaystring =="")){

            ParseQuery<ParseObject> query = ParseQuery.getQuery("Position");

            query.getInBackground(displaystring, new GetCallback<ParseObject>() {
                public void done(ParseObject country, ParseException e) {
                    if (e == null) {
                        positionname=(String)country.get("name");
                        positionaddress=(String)country.get("address");
                        latitude=country.getDouble("latitude");
                        longitude=country.getDouble("longitude");
                        PARController.getInstance().addPoi(createPoi(positionname, positionaddress, latitude, longitude));


                    }

                }
            });

        }







    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // FIRST: setup default resource IDs
        // IMPORTANT: call before super.onCreate()
        this.viewLayoutId = R.layout.panicar_view;
        View view = super.onCreateView(inflater, container, savedInstanceState);
        getRadarView().setRadarRange(500);
        return view;
    }





    //==============================================================================================
    // Callback
    //==============================================================================================
    @Override
    public void onDeviceOrientationChanged(PSKDeviceOrientation newOrientation) {
        super.onDeviceOrientationChanged(newOrientation);
//        Toast.makeText(getActivity(), "onDeviceOrientationChanged: " + PSKDeviceAttitude.rotationToString(newOrientation), Toast.LENGTH_LONG).show();
    }

    //==============================================================================================
    // Create Label
    //==============================================================================================
    /**
     * Create a poi with title, description and position
     *
     * @param title       Title of poi
     * @param description Description of poi (if you want none, set this to "")
     * @param lat         Latitude of poi
     * @param lon         Longitude of poi
     * @return PARPoiLabel which is a subclass of PARPoi (extended for title, description and so on)
     */
    public PARPoiLabel createPoi(String title, String description, double lat, double lon) {
        Location poiLocation = new Location(title);
        poiLocation.setLatitude(lat);
        poiLocation.setLongitude(lon);

        final PARPoiLabel parPoiLabel = new PARPoiLabel(poiLocation, title, description, R.layout.panicar_poilabel, R.drawable.radar_dot);

        parPoiLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), parPoiLabel.getTitle() + " - " + parPoiLabel.getDescription(), Toast.LENGTH_LONG).show();
            }
        });

        return parPoiLabel;
    }

    /**
     * Create a poi with title, description and position
     *
     * @param title       Title of poi
     * @param description Description of poi (if you want none, set this to "")
     * @param lat         Latitude of poi
     * @param lon         Longitude of poi
     * @return PARPoiLabelAdvanced which is a subclass of PARPoiLabel (extended for altitude support)
     */
    public PARPoiLabelAdvanced createPoi(String title, String description, double lat, double lon, double alt) {
        Location poiLocation = new Location(title);
        poiLocation.setLatitude(lat);
        poiLocation.setLongitude(lon);
        poiLocation.setAltitude(alt);

        final PARPoiLabelAdvanced parPoiLabel = new PARPoiLabelAdvanced(poiLocation, title, description, R.layout.panicar_poilabel, R.drawable.radar_dot);
        parPoiLabel.setIsAltitudeEnabled(true);
        parPoiLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), parPoiLabel.getTitle() + " - " + parPoiLabel.getDescription(), Toast.LENGTH_LONG).show();
            }
        });

        return parPoiLabel;
    }

    //==============================================================================================
    // Helper methods
    //==============================================================================================
    private PARPoiLabel createRepoPoi(
            String title,
            String description,
            double latitude,
            double longitude) {
        return createPoi(title, description, latitude, longitude, 0);
    }



}
