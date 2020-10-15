package com.example.hw9.controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hw9.Board;
import com.example.hw9.R;
import com.google.android.material.snackbar.Snackbar;

public class TicTacToeFragment extends Fragment {

    private Button[][] mButtons = new Button[3][3];
    private TextView mResultPlayer1;
    private TextView mResultPlayer2;

    private Board mBoard;

    private boolean mPlayer1Turn =true;
    private int mRoundCount;
    private int mPlayer1points;
    private int mPlayer2points;

    public TicTacToeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        if (savedInstanceState != null){
            mRoundCount = savedInstanceState.getInt("roundCount");
            mPlayer1points = savedInstanceState.getInt("player1Points");
            mPlayer2points = savedInstanceState.getInt("player2Points");
            mPlayer1Turn = savedInstanceState.getBoolean("player1Turn");
        }
        findViews(view);
        setListeners(view);
        return view;
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("roundCount", mRoundCount);
        outState.putInt("player1Points", mPlayer1points);
        outState.putInt("player2Points", mPlayer2points);
        outState.putBoolean("player1Turn", mPlayer1Turn);

        super.onSaveInstanceState(outState);
    }


    private void findViews(View view){
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                String buttonID = "btn_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getActivity().getPackageName());
                mButtons[i][j] = view.findViewById(resID);
            }
        }
        mResultPlayer1 = view.findViewById(R.id.text_view_p1);
        mResultPlayer2 =view.findViewById(R.id.text_view_p2);
    }
    private void setListeners(final View view){
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++) {
                mButtons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!((Button) v).getText().toString().equals("")) {
                            return;
                        }
                        if (mPlayer1Turn) {
                            ((Button) v).setText("X");
                        } else {
                            ((Button) v).setText("O");
                        }
                        mRoundCount++;
                        if (checkForWin()) {
                            if (mPlayer1Turn) {
                                player1Wins(view);
                            } else {
                                player2Wins(view);
                            }
                        } else if (mRoundCount == 9) {
                            draw(view);
                        } else {
                            mPlayer1Turn = !mPlayer1Turn;
                        }
                    }
                });
            }
        }
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = mButtons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private void player1Wins(View view) {
        mPlayer1points++;
        Snackbar.make(view.findViewById(R.id.tic_tac_toe), R.string.p1_win, Snackbar.LENGTH_LONG).show();
//        Toast.makeText(getActivity(), R.string.p2_win, Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }
    private void player2Wins(View view) {
        mPlayer2points++;
        Snackbar.make(view.findViewById(R.id.tic_tac_toe), R.string.p2_win, Snackbar.LENGTH_LONG).show();
//        Toast.makeText(getActivity(), R.string.p2_win, Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }
    private void draw(View view) {
        Snackbar.make(view.findViewById(R.id.tic_tac_toe), R.string.draw, Snackbar.LENGTH_LONG).show();
//        Toast.makeText(getActivity(), R.string.draw, Toast.LENGTH_LONG).show();
        resetBoard();
    }

    private void updatePointsText(){
        mResultPlayer1.setText("Player one: " + mPlayer1points);
        mResultPlayer2.setText("Player two: " + mPlayer2points);
    }
    private void resetBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mButtons[i][j].setText("");
            }
        }
        mRoundCount = 0;
        mPlayer1Turn = true;
    }
}