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
    protected float stepX = 10; // Guy's step in (x,y) direction
    protected  float stepY = -15;// gives speed of motion, larger means faster speed
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
    public boolean move() {
        // Get new (x,y) position. Movement is always in vertical direction upwards
        y += (stepY);
        // Detect when the guy reaches the bottom of the screen
        // restart at a random location at the top of the screen
//        if (y +50 < 0) {
//            x = (float) ((upperX-155)*Math.random());
//            y =upperY;
//            return true;
//        }
//        else
         return true;
    }
    // When you reset, starts the Android Guy from a random X co-ordinate location
    // at the top of the screen again
    public void reset() {
        x = (float) ((upperX-155)*Math.random());
        y = upperY;
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
