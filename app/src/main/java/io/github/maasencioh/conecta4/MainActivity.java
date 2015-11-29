package io.github.maasencioh.conecta4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Game game;
    private final int ids [][] = {
            {R.id.Button11, R.id.Button12, R.id.Button13, R.id.Button14, R.id.Button15, R.id.Button16, R.id.Button17},
            {R.id.Button21, R.id.Button22, R.id.Button23, R.id.Button24, R.id.Button25, R.id.Button26, R.id.Button27},
            {R.id.Button31, R.id.Button32, R.id.Button33, R.id.Button34, R.id.Button35, R.id.Button36, R.id.Button37},
            {R.id.Button41, R.id.Button42, R.id.Button43, R.id.Button44, R.id.Button45, R.id.Button46, R.id.Button47},
            {R.id.Button51, R.id.Button52, R.id.Button53, R.id.Button54, R.id.Button55, R.id.Button56, R.id.Button57},
            {R.id.Button61, R.id.Button62, R.id.Button63, R.id.Button64, R.id.Button65, R.id.Button66, R.id.Button67}
    };
    public boolean end = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
    }

    private int rowId(int id) {
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 7; j++)
                if (ids[i][j] == id)
                    return i;
        return -1;
    }

    private int columnId(int id) {
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 7; j++)
                if (ids[i][j] == id)
                    return j;
        return -1;
    }

    public void onCircleClick(View v) {
        if (!end) {
            int i = rowId(v.getId());
            int j = columnId(v.getId());
            if (game.isPossible(i, j)) {
                game.setBoard(i, j);
                if (game.checkWinner(1)) {
                    end = true;
                    TextView mainText = (TextView) findViewById(R.id.mainText);
                    mainText.setText(R.string.human);
                    mainText.setTextColor(getResources().getColor(R.color.colorGreen));
                    new Restart().show(getFragmentManager(), "ALERT DIALOG");
                }
                game.machineTurn();
                if (game.checkWinner(2)) {
                    end = true;
                    TextView mainText = (TextView) findViewById(R.id.mainText);
                    mainText.setText(R.string.machine);
                    mainText.setTextColor(getResources().getColor(R.color.colorRed));
                    new Restart().show(getFragmentManager(), "ALERT DIALOG");
                }
                drawBoard();
            } else {
                Toast.makeText(this, R.string.errorFull, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.errorEnd, Toast.LENGTH_SHORT).show();
        }
    }

    public void drawBoard() {
        int id, i, j;
        ImageButton img;
        for (i = 0; i < 6; i++)
            for (j = 0; j < 7; j++) {
                id = game.getBoard(i,j);
                img = (ImageButton) findViewById(ids[i][j]);
                if (id == 1) {
                    img.setImageResource(R.drawable.c4_button_green);
                }
                else if (id == 2) {
                    img.setImageResource(R.drawable.c4_button_red);
                }
                else {
                    img.setImageResource(R.drawable.c4_button);
                }
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.conecta4_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAbout:
                startActivity(new Intent(this, About.class));
                return true;
            case R.id.menuSettings:
                return true;
            case R.id.menuRestart:
                new Restart().show(getFragmentManager(), "ALERT DIALOG");
                return true;
            case R.id.menuLevel:
                new Level().show(getFragmentManager(), "ALERT DIALOG");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
