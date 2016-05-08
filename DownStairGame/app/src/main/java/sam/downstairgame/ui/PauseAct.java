package sam.downstairgame.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sam.downstairgame.R;

public class PauseAct extends AppCompatActivity {
    Button btnGoHome, btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);
        btnGoHome = (Button) findViewById(R.id.btnGoHome);
        btnRestart= (Button) findViewById(R.id.btnReStart);

        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PauseAct.this, HomeAct.class));
            }
        });
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PauseAct.this, GameAct.class));
            }
        });
    }
}
