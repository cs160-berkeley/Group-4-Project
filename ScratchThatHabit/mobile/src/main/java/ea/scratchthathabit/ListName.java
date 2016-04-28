package ea.scratchthathabit;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Sarah on 4/26/2016.
 */
public class ListName extends RelativeLayout {

    private ImageView img;
    private TextView txt;

    public ListName(Context ctx) {
        super(ctx);
        View.inflate(ctx, R.layout.listname, this);
        img = (ImageView) findViewById(R.id.alarm_img);
        txt = (TextView) findViewById(R.id.list_name);
    }

    public void setImg(int picture) {
        img.setImageResource(picture);
    }

    public void setTxt(String name) {
        txt.setText(name);
    }
}
