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
public class ItemList extends ContextualReminder {

    private TextView mList;
    private Button mDone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);
        mList = (TextView) findViewById(R.id.list);
        mDone = (Button) findViewById(R.id.done);
        Intent intent = getIntent();
        final Bundle extras = intent.getExtras();
        if (extras != null) {
            String items = extras.getString("ITEMS");
            //currently overwrites stock info even though extras should be null
            mList.setText(items);
        }
        mDone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sendWearIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(sendWearIntent);
            }
        });
    }
}
