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
public class Monster {
    float x = 50;
    float y =0;
    float stepX =20;
    float stepY =0;
    int lowerX, lowerY, upperX, upperY;
    //private Paint paint; // The paint style, color used for drawing
    Bitmap monster;
    private Context mContext;

    // Constructor
    public Monster( Context c) {
        mContext = c;
        // create a bitmap from the supplied image (the image is the icon that is part of the app)
        //TODO: change the value 50 to scale related
        monster= Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.monstertwo),50,50, false);
    }
    public void setBounds(int lx, int ly, int ux, int uy) {
        lowerX = lx;
        lowerY = ly;
        upperX = ux;
        upperY = uy;
        if (x < 0 && y < 0) {
            x = ux/2;
            y = uy;
        }
        //started position for the first time
        x = (float) (ux)/2;
        y=lowerY;
    }
    public void fall(){
        y+=stepY;
    }
    public void addVelocity(float a){
        y+=a;
    }
    public void moveLeft() {
        if (x  > 0) {
            x -= stepX;
        }
    }
    //gameOver game condition
    public boolean fallout(){
        if(y> upperY+100)
            return true;
            //testing return false
        else
            return false;
    }
    public void moveRight() {
        // Get new (x,y) position of the canvas by moving it right
        // when the right button is clicked. Ensure that it does not
        // move off the screen.
        if (x + 50 < upperX) {
            x += stepX;
        }
    }
    public RectF getRect() {
        return new RectF(x,y+50,x+50,y+100);
    }

    public void setY(float speed){y=speed;}

    public void draw(Canvas canvas) {
        // left top
        canvas.drawBitmap(monster, x, y, null);
    }
}
