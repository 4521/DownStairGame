package sam.downstairgame.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Fujitsu on 24/4/2016.
 */
public class DrawView extends View {
    //in this game, treat one score as time
    private int width, height, type;
    boolean gameOver;
    Context mContext;
    Monster monster;
    float xPost;
    Stair s[] = new Stair[5];
    Stair stairType[][] = new Stair[2][5];
    Score score;
    float monsterAddYVelocity;
    float stairAddYVelocity;
    float referenceSpeedForStair;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gameOver = false;
        mContext = context;
        monsterAddYVelocity = 0;
        referenceSpeedForStair=-10;
        stairAddYVelocity = 0;
        type = 0;
        for (int i = 0; i < 5; i++) {
            stairType[0][i] = new Stair(mContext, height);
        }
        for (int i = 0; i < 5; i++) {
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
        monsterAddYVelocity += 5;
        gameOver = monster.fallout();
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
        }
        if (!gameOver)
            invalidate();
        else {
            gameOver = false;
            // TODO: Go back Home or Restart code hrere
            Intent i = new Intent(getContext(), PauseAct.class);
            getContext().startActivity(i);
        }
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
        canvas.drawColor(Color.rgb(183, 255, 252));
        monster.draw(canvas);
        score.draw(canvas);

        for (int i = 0; i < 5; i++) {
            if (s[i] != null)
                s[i].draw(canvas);
        }
        for (int i = 0; i < 5; i++) {
            s[i].moveWith((float)(-10+(-0.2)*(score.getScore())));
        }
        monster.fall();
        monster.addVelocity(monsterAddYVelocity);
        //score.getscore();
        //check stand
        for (int i = 0; i < 5; i++) {
            if (RectF.intersects(monster.getRect(), s[i].getRect())) {
                // if (Rect.intersects(monster.getRectrect(), stair.getRectrect()))
                monster.setY(s[i].getY());
                //reset initital y-velocity if stand on stair
                monsterAddYVelocity = 0;
            }
        }
        type = (int) (Math.random() * 10);
        xPost = (float) ((width - 155) * Math.random());
        //add score and generate new stair when one stair go out of screen
        for (int i = 0; i < 5; i++) {
            if (s[i].getY() + 50 < 0) {
                score.incrementScore();
               // s[i] = null;
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




