package ea.scratchthathabit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Simon Bonzon on 4/19/2016.
 */
public class ContextualReminder extends MainActivity {

    private TextView mList;
    private Button mYes;
    private Button mViewList;
    private String listItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contextual_reminder);
        mList = (TextView) findViewById(R.id.listName);
        Intent intent = getIntent();
        final Bundle extras = intent.getExtras();
        if (extras != null) {
            String listName = extras.getString("LIST");
            listItems = extras.getString("ITEMS");
            mList.setText(listName);
        }
        mYes = (Button) findViewById(R.id.yes);
        mViewList = (Button) findViewById(R.id.viewList);
        mYes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sendWearIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(sendWearIntent);
            }
        });
        mViewList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sendWearIntent = new Intent(getBaseContext(), ItemList.class);
                sendWearIntent.putExtra("ITEMS", listItems);
                startActivity(sendWearIntent);
            }
        });
    }
}
