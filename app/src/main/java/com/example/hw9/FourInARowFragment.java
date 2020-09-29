package com.example.hw9;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class FourInARowFragment extends Fragment {

    private Button[][] mButtons = new Button[5][5];
    private TextView mScorePlayer1;
    private TextView mScorePlayer2;

    private boolean mCurrentPlayer = true;
    private int mCountRound;
    private int mScore1;
    private int mScore2;

    private ColorStateList mDefColor;

    public FourInARowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_four_in_a_row, container, false);
        findViews(view);
        setListeners();
        return view;

    }


    private void findViews(View view){
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                String buttonID = "btn_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getActivity().getPackageName());
                mButtons[i][j] = view.findViewById(resID);
                Drawable buttonBackground = mButtons[i][j].getBackground();
            }
        }
    }

    private void setListeners(){
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                mButtons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!((Button) v).getText().toString().equals("")) {
                            return;
                        }
                        if (mCurrentPlayer) {
                            ((Button) v).setText("X");
                        } else {
                            ((Button) v).setText("O");
                        }
                        mCountRound++;
                        if (checkForWin()) {
                            if (mCurrentPlayer) {
                                player1Wins();
                            } else {
                                player2Wins();
                            }
                        } else if (mCountRound == 25) {
                            draw();
                        } else {
                            mCurrentPlayer = !mCurrentPlayer;
                        }
                    }
                });
            }
        }
    }



    private boolean checkForWin() {
        String[][] field = new String[5][5];

        for (int i = 0; i<5; i++)
        {
            for (int j = 0; j<5-3; j++)
            {
                if (mButtons[i][j].equals(mButtons[i][j + 1]) &&
                        mButtons[i][j + 1].equals(mButtons[i][j + 2]) &&
                        mButtons[i][j + 2].equals(mButtons[i][j + 3]) &&
                        !mButtons[i][j].equals(""))
                {
                    return true;
                }
            }
        }

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

    private void player1Wins() {
        mScore1++;
//        Snackbar.make(view.findViewById(R.id.tic_tac_toe), R.string.p1_win, Snackbar.LENGTH_LONG).show();
//        Toast.makeText(getActivity(), R.string.p2_win, Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }
    private void player2Wins() {
        mScore2++;
//        Snackbar.make(view.findViewById(R.id.fragment_container), R.string.p2_win, Snackbar.LENGTH_LONG).show();
//        Toast.makeText(getActivity(), R.string.p2_win, Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }
    private void draw() {
//        Snackbar.make(view.findViewById(R.id.tic_tac_toe), R.string.draw, Snackbar.LENGTH_LONG).show();
//        Toast.makeText(getActivity(), R.string.draw, Toast.LENGTH_LONG).show();
        resetBoard();
    }

    private void updatePointsText(){
        mScorePlayer1.setText("Player one: " + mScore1);
        mScorePlayer2.setText("Player two: " + mScore2);
    }
    private void resetBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mButtons[i][j].setText("");
            }
        }
        mCountRound = 0;
        mCurrentPlayer = true;
    }
   /* private void compareColorButton(Button button){
        ColorDrawable buttonColor = (ColorDrawable) button.getBackground();
        int color = buttonColor.getColor();
        if (color == getResources().getColor())
    }*/
}