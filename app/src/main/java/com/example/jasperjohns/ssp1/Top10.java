package com.example.jasperjohns.ssp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class Top10 extends ActionBarActivity  implements  Top10Fragment.Callback{

    /*
    Call back for Top10Fragment, when the track in the list is clicked on
     */
    @Override
    public  void  onItemSelected(int position, String spotifyID, ArrayList<TrackData> arrayTracks){


        TrackData track = arrayTracks.get(position);

        Bundle extras = new Bundle();

        extras.putString(Top10Fragment.SPOTIFY_ID,spotifyID );
        extras.putString(Top10Fragment.ARTIST_NAME, track.getArtists());
        extras.putString(Top10Fragment.ARTIST_ALBUM, track.getArtistAlbum());
        extras.putString(Top10Fragment.ARTIST_TRACK, track.getArtistTrack());
        extras.putString(Top10Fragment.ARTIST_TRACK_IMAGE, track.getTrackImage());
        extras.putString(Top10Fragment.ARTIST_TRACK_PREVIEW_URL, track.getPreview_URL());
        extras.putParcelableArrayList(Top10Fragment.ARTIST_TRACKS, arrayTracks);
        extras.putInt(Top10Fragment.LIST_POSITION, position);

        Intent player = new Intent(this, PlayerActivity.class);
        player.putExtras(extras);
        startActivity(player);



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_top10);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(Top10Fragment.SPOTIFY_ID,
                    getIntent().getStringExtra(Top10Fragment.SPOTIFY_ID));

            arguments.putString(Top10Fragment.ARTIST_NAME,
                    getIntent().getStringExtra(Top10Fragment.ARTIST_NAME));


            Top10Fragment fragment = new Top10Fragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top10, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
