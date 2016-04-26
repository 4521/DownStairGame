package sam.downstairgame.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import sam.downstairgame.R;

/**
 * Created by Fujitsu on 24/4/2016.
 */
public class DrawView extends View {
    //in this game, treat one score as time
    private int width, height, type;
    boolean end;
    Context mContext;
    Monster monster;
    float xPost;
    Stair s[] = new Stair[5];
    Stair stairType[][] = new Stair[2][5];
    Score score;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        end = false;
        mContext = context;
        type = 0;
        for(int i= 0; i< 5; i++) {
            stairType[0][i] = new Stair(mContext, height);
        }
        for(int i= 0; i< 5; i++) {
            stairType[1][i] = new BlackStair(mContext, height);
        }
       // stairType = new Stair[]
        for (int i = 0; i < 5; i++) {
            type = (int) (Math.random() * 10);
            if (type > 5)
                s[i] = new Stair(mContext, height);
            else
                s[i] = new BlackStair(mContext, height);
        }
        // create a monster object
        monster = new Monster(mContext);
        score = new Score(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGameBoard(canvas);

        end = monster.fallout();
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
        }
        // A call to invalidate causes the Android framework to call the onDraw
        // method of the DrawView
        // Thus every time the screen is refreshed, the framework is again
        // forced to call the onDraw
        // method. This creates the animation on the screen to simulate the game
        // playing

        if (!end)
            invalidate();
        else {
            end = false;
            // TODO: Go back Home or Restart code hrere
            // Intent i = new Intent(getContext(), PauseActivity.class);
            //  getContext().startActivity(i);
        }
    }
    public boolean getEnd() {
        return end;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        monster.setBounds(0, 0, width, height);
        int num = 1 / 6;

        int hh = height / 5;
        int j = 50;
        for (int i = 0; i < 5; i++) {
            s[i].setBounds(0, 0, width, height, width / 2, (i + 1) * hh - j);
            j -= 10;
        }

    }

    public void drawGameBoard(Canvas canvas) {
        //Background color
        canvas.drawColor(Color.WHITE);
        monster.draw(canvas);
        score.draw(canvas);

        for (int i = 0; i < 5; i++) {
            if (s[i] != null)
                s[i].draw(canvas);
        }
        //end game condition
        // if (y +50 < 0) {
        //  x = (float) ((upperX-155)*Math.random());
        //   y =upperY;
        //}
        for (int i = 0; i < 5; i++) {
            if (!s[i].move()) {
                s[i] = null;
                break;
            }
        }
        monster.fall();
        //check stand
        for (int i = 0; i < 5; i++) {
            if (RectF.intersects(monster.getRect(), s[i].getRect())) {
                // if (Rect.intersects(monster.getRectrect(), stair.getRectrect()))
                monster.setY(s[i].getY());
            }
        }
        type = (int) (Math.random() * 10);
        xPost = (float) ((width - 155) * Math.random());
        for (int i = 0; i < 5; i++) {
           if (s[i].getY() + 50 < 0) {
               score.incrementScore();
                s[i] = null;
                if (type > 5) {
                   s[i] = stairType[0][i];
                    s[i].setXY(xPost, height);
                    break;
               } else {
                    s[i] = stairType[1][i];
                    s[i].setXY(xPost, height);
                    break;
                }
          }

      }
    }

    public void moveMonsterLeft() {
        monster.moveLeft();
    }

    public void moveMonsterRight() {
        monster.moveRight();
    }
}




