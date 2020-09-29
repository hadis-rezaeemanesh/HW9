package com.example.hw9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtnTicTacToe;
    private Button mBtn4InARow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setListeners();
    }

    private void findViews(){
        mBtnTicTacToe = findViewById(R.id.btn_tic_tac_toe);
        mBtn4InARow = findViewById(R.id.btn_4_in_a_row);
    }

    private void setListeners(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        final Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        mBtnTicTacToe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicTacToeFragment ticTacToeFragment = new TicTacToeFragment();
                if (fragment == null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.fragment_container, ticTacToeFragment)
                            .commit();
                }
            }
        });

        mBtn4InARow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FourInARowFragment fourInARowFragment = new FourInARowFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, fourInARowFragment)
                        .commit();

            }
        });
    }
}