package com.raoul.cudenver;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.raoul.cudenver.utill.Position;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class LocationActivity extends ActionBarActivity {
    ImageButton back_imagebut;
    ImageButton add_imagebut;
    ListView location;
    ProgressDialog mProgressDialog;
    private EventAdapter adapter;
    public List<Position> data = null;
    ListView positionlistview;
    Button createevent;
    List<ParseObject> ob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        positionlistview=(ListView)findViewById(R.id.location_listView);
        new RemoteDataTask().execute();
    }




    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(LocationActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Message");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            data = new ArrayList<Position>();

            try {
                ParseQuery<ParseObject> eventQuery = ParseQuery.getQuery("Position");
                eventQuery.orderByDescending("createdAt");
                eventQuery.include("user");
                ob = eventQuery.find();
                for (ParseObject event : ob) {
                    // Locate images in flag column


                    final Position positionlist = new Position();

                    ParseFile image = (ParseFile) event.get("image");
                    positionlist.setPosition_id(event.getObjectId());
                    positionlist.setPosition_name((String) event.get("name"));
                    positionlist.setPosition_adress((String) event.get("address"));
                    positionlist.setDescription((String) event.get("Description"));
                    positionlist.setImage_url(image.getUrl());

                    if(image==null){

                        positionlist.setImage_url("");

                    }
                    else {
                        positionlist.setImage_url(image.getUrl());
                    }










                    data.add(positionlist);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml

            // Pass the results into ListViewAdapter.java
            adapter = new EventAdapter(LocationActivity.this,
                    data);
            // Binds the Adapter to the ListView
            positionlistview.setAdapter(adapter);
            positionlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    data.get(position).getPosition_id();
                    Intent zoom = new Intent(LocationActivity.this, ARPortraitActivity.class);
                    zoom.putExtra("positionID", data.get(position).getPosition_id());
                    startActivity(zoom);


//                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            });
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }


    public class EventAdapter extends BaseAdapter {
        boolean flag = true;
        Context context;
        LayoutInflater inflater;
        private ParseFile image;
        private List<Position> worldpopulationlist = null;
        private ArrayList<Position> arraylist;

        /**
         * Constructor from a list of items
         */
        public EventAdapter(Context context, List<Position> worldpopulationlist) {

            this.context = context;
            this.worldpopulationlist = worldpopulationlist;
            inflater = LayoutInflater.from(context);
            this.arraylist = new ArrayList<Position>();
            this.arraylist.addAll(worldpopulationlist);

        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            final ViewHolder holder;

            if (view == null) {
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.location_list_item_layout, null);

                holder.buildimageview = (ImageView) view.findViewById(R.id.building_imageView);
                holder.positionnametextview=(TextView)view.findViewById(R.id.name_textView);
                holder.positinoadresstextview=(TextView)view.findViewById(R.id.address_textView);
                holder.descriptiontextview=(TextView)view.findViewById(R.id.description_textView);

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }


                Picasso.with(context).load(worldpopulationlist.get(position).getImage_url()).into(holder.buildimageview);


            holder.positionnametextview.setText(worldpopulationlist.get(position).getPosition_name());
            holder.positinoadresstextview.setText(worldpopulationlist.get(position).getPosition_adress());
            holder.descriptiontextview.setText(worldpopulationlist.get(position).getDescription());



            // Restore the checked state properly
            final ListView lv = (ListView) parent;


            return view;
        }

        @Override
        public int getCount() {
            return worldpopulationlist.size();
        }

        @Override
        public Object getItem(int position) {
            return worldpopulationlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class ViewHolder {

            public ImageView buildimageview;
            public TextView  positionnametextview;
            public TextView  positinoadresstextview;
            public TextView  descriptiontextview;

        }
    }








}
