package sg.hangout.hangoutsg;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EventDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ImageView image = (ImageView)(findViewById(R.id.bgimage));
        image.setImageURI(null);
        Button joinedit = (Button)(findViewById(R.id.joinedit));
        joinedit.setText("");
        TextView title = (TextView)(findViewById(R.id.event_title));
        title.setText("");
        TextView date = (TextView)(findViewById(R.id.date));
        date.setText("");
        TextView time = (TextView)(findViewById(R.id.time));
        time.setText("");
        TextView location = (TextView)(findViewById(R.id.location));
        location.setText("");
        TextView description = (TextView)(findViewById(R.id.description));
        description.setText("");
        TextView organizer = (TextView)(findViewById(R.id.organizer));
        organizer.setText("Organized by: " + "");

    }

}
