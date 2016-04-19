package ea.scratchthathabit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Sarah on 4/18/2016.
 */
public class RemindersContextActivity extends AppCompatActivity {

    ImageButton btn;
    Context ctx;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("Reminders context activity started");
        super.onCreate(savedInstanceState   );

        setContentView(R.layout.activity_reminders_context);
        btn = (ImageButton) findViewById(R.id.btn);
        ctx = this;
        btn.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(ctx, RemindersTimeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
            }
        });
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, RemindersActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
