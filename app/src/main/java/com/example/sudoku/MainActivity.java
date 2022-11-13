package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout table;
        table = (TableLayout) findViewById(R.id.tableLayout);

        TableRow.LayoutParams layoutParams =
                new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1.0f
                );
        layoutParams.setMargins(5, 30, 5, 1);

        BoardGenerator board = new BoardGenerator();

        Button[][] buttons = new Button[9][9];
        for (int i = 0; i < 9; i++) {
            TableRow tableRow = new TableRow(this);
            table.addView(tableRow);
            for (int j = 0; j < 9; j++) {
                buttons[i][j] = new Button(this);
                buttons[i][j].setLayoutParams(layoutParams);

                int rate = (int) (Math.random()*3);
                if (rate == 1) {
                    int number = board.get(i, j);
                    String number1 = String.valueOf(number);
                    buttons[i][j].setText(number1);
                }
                tableRow.addView(buttons[i][j]);
            }
        }
    }
}