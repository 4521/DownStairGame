package sam.downstairgame.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import sam.downstairgame.R;

/**
 * Created by Fujitsu on 24/4/2016.
 */
public class Stair {
   protected float x; // Stair top left corner (x,y)
    protected float y;
    protected  float stepY = -20;// gives speed of motion, larger means faster speed
    protected int lowerX, lowerY, upperX, upperY; // boundaries
    protected Context mContext;
    // private Paint paint; // The paint style, color used for drawing
    Bitmap stair;
    // Constructor
    public Stair(Context c,float sety) {
        mContext = c;
        x=0;
        y=sety;
        // paint= new Paint();
        //paint.setColor(color);
        //TODO: scale related size
        stair = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.stair),150,30, false);
    }

    public void setBounds(int lx, int ly, int ux, int uy,int startX , int startY) {
        lowerX = lx;
        lowerY = ly;
        upperX = ux;
        upperY = uy;
        //starting first in middle
        //random  starting x position, fixed y position
        x = (float) startX;
        //((upperX-700)*Math.random());
        y = (float)startY;
    }
    public int getUpperX(){return upperX;}
    public void setXY(float xp, float yp){
        x=xp;
        y= yp;
    }
    public void moveWith(float speed){
        stepY = speed;
        y+= speed;
    }

    public RectF getRect() {
        return new RectF( x+10, y+50,x+150, y+100);
    }

    public float getY() {
        return y;
    }

    public void draw(Canvas canvas) {
        //draw on left top
        canvas.drawBitmap(stair, x, y+50, null);
    }

}
