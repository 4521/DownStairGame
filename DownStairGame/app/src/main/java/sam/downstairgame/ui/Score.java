package sam.downstairgame.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Created by Fujitsu on 26/4/2016.
 */
public class Score {
    private Paint paint;
    private int score;

    // Constructor
    public Score(int color) {
        paint = new Paint();
        // Set the font face and size of drawing text
        paint.setTypeface(Typeface.MONOSPACE);
        paint.setTextSize(24);
        paint.setColor(color);

        // TODO initialize score
        score = 0;
    }

    public void incrementScore() {
        // TODO Increment score
        score++;
    }

    public void decrementScore() {
        // TODO Decrement score
        score--;
    }

    public int getScore() { return score; }

    public void draw(Canvas canvas) {

        // TODO use drawText(String, x co-ordinate, y-coordinate, paint) to
        // draw text on the canvas. Position the text at (10,30).
        canvas.drawText(String.valueOf(score), 10, 30, paint);
    }

}