package ea.scratchthathabit;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sarah on 4/26/2016.
 */
public class ListItemView extends RelativeLayout {

    private ImageButton btn;
    private TextView txt;
    private ArrayList<String> items;

    public ListItemView(Context context) {
        super(context);

        View.inflate(context, R.layout.listitem, this);
        txt = (TextView) findViewById(R.id.item);
        btn = (ImageButton) findViewById(R.id.remove);

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove();
            }
        });

    }

    public void setText(String text) {
        txt.setText(text);
    }

    public void setList(ArrayList<String> items) {
        this.items = items;
    }

    public void remove() {
        ViewGroup parent = (ViewGroup) this.getParent();
        String item = txt.getText().toString();
        items.remove(item);
        parent.removeView(this);
        System.out.println(Arrays.toString(items.toArray()));
    }

}
