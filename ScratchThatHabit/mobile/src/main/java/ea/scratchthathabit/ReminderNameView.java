package ea.scratchthathabit;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Sarah on 4/30/2016.
 */
public class ReminderNameView extends RelativeLayout {

    TextView reminderName;

    public ReminderNameView(Context context) {
        super(context);
        View.inflate(context, R.layout.remindername, this);
        reminderName = (TextView) findViewById(R.id.reminder_name);
    }

    public void setReminderName(String name) {
        reminderName.setText(name);
    }
}
