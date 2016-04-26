package sam.downstairgame.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sam.downstairgame.R;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class GameAct extends AppCompatActivity {

    private ImageButton moveLeftButton, moveRightButton;
    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        drawView = (DrawView) findViewById(R.id.drawView);
        moveLeftButton = (ImageButton) findViewById(R.id.moveLeftButton);
        //moveLeftButton.setOnClickListener(this);
        moveRightButton = (ImageButton) findViewById(R.id.moveRightButton);
        //moveRightButton.setOnClickListener(this);

        moveRightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // Do what you want
                drawView.moveMonsterRight();
                return true;
                //  return false;
            }
        });
        moveLeftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // Do what you want
                drawView.moveMonsterLeft();
                return true;
                //  return false;
            }
        });
    }
}
