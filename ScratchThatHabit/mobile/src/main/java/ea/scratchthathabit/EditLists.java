package ea.scratchthathabit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

// Created by Tiffanie Lo on 4/19/2016: Functionality: Click brings user back to Lists
public class EditLists extends AppCompatActivity {

    private ImageButton btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("EditLists activity started");

        setContentView(R.layout.activity_edit_lists);
        btn = (ImageButton) findViewById(R.id.EditListsBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick2(v);
            }
        });
    }

    public void onClick2(View view ) {
        //OnClick go back to Lists Activity
        Intent intent = new Intent(this, Lists.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
