package ea.scratchthathabit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//Created by Tiffanie Lo 4/19/16: Functionality: Click brings user to EditListsActivity
//LongClick brings user to Main Activity

public class ListsActivity extends AppCompatActivity {

    private Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        btn = (Button) findViewById(R.id.add_list);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EditListsActivity.class);
                intent.putExtra("mode", "create");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }


}
