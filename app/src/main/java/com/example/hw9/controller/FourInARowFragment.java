package com.example.hw9.controller;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.hw9.Board4InARow;
import com.example.hw9.R;
import com.google.android.material.snackbar.Snackbar;


public class FourInARowFragment extends Fragment {

    private Button[][] mButtons = new Button[5][5];
    private TextView mScorePlayer1;
    private TextView mScorePlayer2;

    private boolean mCurrentPlayer = true;
    private int mCountRound;
    private int mScore1;
    private int mScore2;
    private int colorId;


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
        colorId = Color.parseColor("#c1bebe");
        setListeners(view);
        return view;

    }


    private void findViews(View view){
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                String buttonID = "btn_four_row_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getActivity().getPackageName());
                mButtons[i][j] = view.findViewById(resID);
            }
        }

        mScorePlayer1 = view.findViewById(R.id.score_player1_4_in_a_row);
        mScorePlayer2 = view.findViewById(R.id.score_player2_4_in_a_row);
    }

    private int findColorButtons(Button button){
        int idColor = 0;
        Drawable buttonBackground = button.getBackground();
        if (buttonBackground instanceof ColorDrawable) {
            idColor = ((ColorDrawable)buttonBackground).getColor();
        }
        return idColor;
    }

    private void setListeners(final View view){
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                mButtons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (findColorButtons((Button) v) != colorId) {
                            return;
                        }
                        if (mCurrentPlayer) {
                            ((Button) v).setBackgroundColor(Color.BLUE);
                        } else {
                            ((Button) v).setBackgroundColor(Color.RED);
                        }
                      mCountRound++;
                        if (checkForWin()) {
                            if (mCurrentPlayer) {
                                player1Wins(view);
                            } else {
                                player2Wins(view);
                            }
                        } else if (mCountRound == 25) {
                            draw(view);
                        } else {
                            mCurrentPlayer = !mCurrentPlayer;
                        }
                    }
                });
            }
        }
    }



    private boolean checkForWin() {
        //check for 4 across
        for(int row = 0; row<5; row++){
            for (int col = 0;col < 2;col++){
                if ((findColorButtons(mButtons[row][col]) == findColorButtons(mButtons[row][col+1]))
                && (findColorButtons(mButtons[row][col]) == findColorButtons(mButtons[row][col+2]))
                && (findColorButtons(mButtons[row][col]) == findColorButtons(mButtons[row][col+3]))
                && (findColorButtons(mButtons[row][col]) != colorId)) {
                    return true;
                }
            }
        }
        for(int row = 0; row < 2; row++){
            for(int col = 0; col < 5; col++){
                if ((findColorButtons(mButtons[row][col]) == findColorButtons(mButtons[row+1][col]))
                        && (findColorButtons(mButtons[row][col]) == findColorButtons(mButtons[row+2][col]))
                        && (findColorButtons(mButtons[row][col]) == findColorButtons(mButtons[row+3][col]))
                        && (findColorButtons(mButtons[row][col]) != colorId)) {
                    return true;
                }
            }
        }
        //check upward diagonal
        for(int row = 3; row < 5; row++){
            for(int col = 0; col < 2; col++){
                if ((findColorButtons(mButtons[row][col]) == findColorButtons(mButtons[row-1][col+1]))
                        && (findColorButtons(mButtons[row][col]) == findColorButtons(mButtons[row-2][col+2]))
                        && (findColorButtons(mButtons[row][col]) == findColorButtons(mButtons[row-3][col+3]))
                        && (findColorButtons(mButtons[row][col]) != colorId)) {
                    return true;
                }
            }
        }
        //check downward diagonal
        for(int row = 0; row < 2; row++){
            for(int col = 0; col < 2; col++){
                if ((findColorButtons(mButtons[row][col]) == findColorButtons(mButtons[row+1][col+1]))
                        && (findColorButtons(mButtons[row][col]) == findColorButtons(mButtons[row+2][col+2]))
                        && (findColorButtons(mButtons[row][col]) == findColorButtons(mButtons[row+3][col+3]))
                        && (findColorButtons(mButtons[row][col]) != colorId)){
                    return true;
                }
            }
        }
        return false;
    }

    private void player1Wins(View view) {
        mScore1++;
        Snackbar.make(view.findViewById(R.id.four_in_a_row), R.string.p1_win, Snackbar.LENGTH_LONG).show();
//        Toast.makeText(getActivity(), R.string.p2_win, Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }
    private void player2Wins(View view) {
        mScore2++;
     Snackbar.make(view.findViewById(R.id.four_in_a_row), R.string.p2_win, Snackbar.LENGTH_LONG).show();
//        Toast.makeText(getActivity(), R.string.p2_win, Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }
    private void draw(View view) {
        Snackbar.make(view.findViewById(R.id.four_in_a_row), R.string.draw, Snackbar.LENGTH_LONG).show();
//        Toast.makeText(getActivity(), R.string.draw, Toast.LENGTH_LONG).show();
        resetBoard();
    }

    private void updatePointsText(){
        mScorePlayer1.setText("Player one: " + mScore1);
        mScorePlayer2.setText("Player two: " + mScore2);
    }
    private void resetBoard(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                mButtons[i][j].setBackgroundColor(Color.parseColor("#c1bebe"));
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