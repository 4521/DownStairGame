package sam.downstairgame.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import sam.downstairgame.R;

/**
 * Created by Fujitsu on 25/4/2016.
 */
public class BlackStair extends Stair {

    // private Paint paint; // The paint style, color used for drawing
    Bitmap bstair;

    // Constructor
    public BlackStair(Context c, float sety) {
        super(c, sety);
        // paint= new Paint();
        //paint.setColor(color);
        stair = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.monster), 150, 30, false);
    }

}