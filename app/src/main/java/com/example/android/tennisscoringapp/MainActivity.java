package com.example.android.tennisscoringapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int p1Score = 0;
    int p2Score = 0;
    int deuceScoreP1 = 0;
    int deuceScoreP2 = 0;
    int p1GamesWon = 0;
    int p2GamesWon = 0;
    int currentSet = 1;
    int p1SetsWon = 0;
    int p2SetsWon = 0;
    String p1 = "Venus";
    String p2 = "Serena";
    Button p1Point;
    Button p2Point;
    Button resetGame;
    Button resetSet;


    //NEXT TASK...Add to the code logic to increment sets and matches...Also need to rereview scoring to
//figure out when a winner is declared without going to deuces
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p1Point = findViewById(R.id.player1Point);
        p2Point = findViewById(R.id.player2Point);
        resetGame = findViewById(R.id.resetGame);
        resetSet = findViewById(R.id.resetSet);
        resetSet.setEnabled(false);

    }

    /**
     * Displays the given score for Player 1.
     */
    public void displayForPlayer1(int score1) {
        TextView scorePlayer1 = (TextView) findViewById(R.id.player1score);
        scorePlayer1.setText(String.valueOf(score1));
    }

    public void player1Score(View view) {
        if (p1Score == 0) {
            p1Score = p1Score + 15;
            displayForPlayer1(p1Score);
        } else if (p1Score == 15) {
            p1Score = p1Score + 15;
            displayForPlayer1(p1Score);
        } else if (p1Score == 30) {
            p1Score = p1Score + 10;
            displayForPlayer1(p1Score);
        } else if (p1Score == 40) {
            p1Score = p1Score + 1;
            displayForPlayer1(p1Score);
            tToast("Winner of Current Game! RESET GAME NOW");

            games1Won();
            p2Point.setEnabled(false);
//            matchesWon();


        } else displayForPlayer1(p1Score);
    }

    private void games1Won() {
        if (p1GamesWon <= 4) {
            p1GamesWon = p1GamesWon + 1;
            displayPlayer1GamesWon(p1GamesWon);
        }else if (p1GamesWon == 5) {
            p1GamesWon = p1GamesWon + 1;
            displayPlayer1GamesWon(p1GamesWon);
            tToast("Current Set Complete.  RESET \"SET\" NOW");
            p1SetsWon = p1SetsWon + 1;
            displayPlayer1SetsWon(p1SetsWon);
            p1Point.setEnabled(false);
            p2Point.setEnabled(false);
            resetGame.setEnabled(false);
            resetSet.setEnabled(true);
        }
    }

    private void displayPlayer1SetsWon(int p1SetsWon) {
        TextView Player1SetsWon = (TextView) findViewById(R.id.player1SetsWon);
        Player1SetsWon.setText(String.valueOf(p1SetsWon));
    }

    /**
     * Displays the Games won for Player 1.
     */
    public void displayPlayer1GamesWon(int gamesWon1) {
        TextView gamesWonPlayer1 = (TextView) findViewById(R.id.player1GamesWon);
        gamesWonPlayer1.setText(String.valueOf(gamesWon1));
    }

    public void player1DeucePoint(View p1DeucePt) {
        if (p1Score < 40 && p2Score < 40) {
            tToast("CAN'T ADD DEUCE POINTS WHEN PLAYERS HAVEN'T BOTH HIT 40 POINTS!");
        } else if ((p1Score == 40) && (p2Score == 40) && (deuceScoreP1 < 1)) {
            p1Point.setEnabled(false);
            p2Point.setEnabled(false);
            deuceScoreP1 = 1;
            displayDeucePointsForPlayer1(deuceScoreP1);
        } else if ((p1Score == 40) && (p2Score == 40) && (deuceScoreP1 == 1) && (deuceScoreP2 != 2)) {
            deuceScoreP1 = deuceScoreP1 + 1;
            displayDeucePointsForPlayer1(deuceScoreP1);
            tToast("WINNER OF THIS GAME!");
            games1Won();
        } else tToast("CURRENT GAME OVER! RESET GAME NOW.");
    }

    /**
     * Displays the deuce points score for Player 1.
     */
    public void displayDeucePointsForPlayer1(int deuce1Score) {
        TextView scorePlayer1 = (TextView) findViewById(R.id.player1DeuceScore);
        scorePlayer1.setText(String.valueOf(deuce1Score));
    }

    private void tToast(String s) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, s, duration);
        toast.show();
    }


    public void player1ResetDeuce(View view) {
        deuceScoreP1 = 0;
        displayDeucePointsForPlayer1(deuceScoreP1);
    }

    /**
     * Displays the given score for Player 2.
     */
    public void displayForPlayer2(int score2) {
        TextView scorePlayer2 = (TextView) findViewById(R.id.player2score);
        scorePlayer2.setText(String.valueOf(score2));
    }

    public void player2Score(View view) {
        if (p2Score == 0) {
            p2Score = p2Score + 15;
            displayForPlayer2(p2Score);
        } else if (p2Score == 15) {
            p2Score = p2Score + 15;
            displayForPlayer2(p2Score);
        } else if (p2Score == 30) {
            p2Score = p2Score + 10;
            displayForPlayer2(p2Score);
        } else if (p2Score == 40) {
            p2Score = p2Score + 1;
            displayForPlayer2(p2Score);
            tToast("Winner of Current Game! RESET GAME NOW");
            games2Won();
//            matchesWon();
            p1Point.setEnabled(false);
        }
    }

    private void games2Won() {
        if (p2GamesWon <= 4) {
            p2GamesWon = p2GamesWon + 1;
            displayPlayer2GamesWon(p2GamesWon);
        } else if (p2GamesWon == 5) {
            p2GamesWon = p2GamesWon + 1;
            displayPlayer2GamesWon(p2GamesWon);
            tToast("Current Set Complete.  RESET \"SET\" NOW");
            p2SetsWon = p2SetsWon + 1;
            displayPlayer2SetsWon(p2SetsWon);
            p1Point.setEnabled(false);
            p2Point.setEnabled(false);
            resetGame.setEnabled(false);
            resetSet.setEnabled(true);
        }
    }

    /**
     * Displays the Games won for Player 1.
     */
    public void displayPlayer2GamesWon(int gamesWon2) {
        TextView gamesWonPlayer2 = (TextView) findViewById(R.id.player2GamesWon);
        gamesWonPlayer2.setText(String.valueOf(gamesWon2));
    }

    private void displayPlayer2SetsWon(int p2SetsWon) {
        TextView SetsWonPlayer2 = (TextView) findViewById(R.id.player2SetsWon);
        SetsWonPlayer2.setText(String.valueOf(p2SetsWon));
    }

    public void player2DeucePoint(View p2DeucePt) {
        if (p1Score < 40 && p2Score < 40) {
            tToast("CAN'T ADD DEUCE POINTS WHEN PLAYERS HAVEN'T BOTH HIT 40 POINTS!");
        } else if ((p1Score == 40) && (p2Score == 40) && (deuceScoreP2 < 1)) {
            deuceScoreP2 = 1;
            displayDeucePointsForPlayer2(deuceScoreP2);
        } else if ((p1Score == 40) && (p2Score == 40) && (deuceScoreP2 == 1) && (deuceScoreP1 != 2)) {
            deuceScoreP2 = deuceScoreP2 + 1;
            displayDeucePointsForPlayer2(deuceScoreP2);
            tToast("WINNER OF THIS GAME!");
            games2Won();
        } else tToast("CURRENT GAME OVER! RESET GAME NOW.");
    }

    /**
     * Displays the deuce points score for Player 2.
     */
    public void displayDeucePointsForPlayer2(int deuce2Score) {
        TextView scorePlayer2 = (TextView) findViewById(R.id.player2DeuceScore);
        scorePlayer2.setText(String.valueOf(deuce2Score));
    }

    public void player2ResetDeuce(View view) {
        deuceScoreP2 = 0;
        displayDeucePointsForPlayer2(deuceScoreP2);
    }

    public void playerResetGame(View view) {
        p1Score = 0;
        p2Score = 0;
        deuceScoreP1 = 0;
        deuceScoreP2 = 0;
        displayForPlayer1(p1Score);
        displayForPlayer2(p2Score);
        displayDeucePointsForPlayer1(deuceScoreP1);
        displayDeucePointsForPlayer2(deuceScoreP2);
        p1Point.setEnabled(true);
        p2Point.setEnabled(true);
    }

    public void playerResetSet(View view) {
        p1Score = 0;
        p2Score = 0;
        deuceScoreP1 = 0;
        deuceScoreP2 = 0;
        p1GamesWon = 0;
        p2GamesWon = 0;
        currentSet = currentSet + 1;
        displayForPlayer1(p1Score);
        displayForPlayer2(p2Score);
        displayPlayer1GamesWon(p1GamesWon);
        displayPlayer2GamesWon(p2GamesWon);
        displayDeucePointsForPlayer1(deuceScoreP1);
        displayDeucePointsForPlayer2(deuceScoreP2);
        displayCurrentSet(currentSet);
        p1Point.setEnabled(true);
        p2Point.setEnabled(true);
        resetGame.setEnabled(true);
        resetSet.setEnabled(false);
        matchWinner();

    }

    private void matchWinner() {
        //The whole shebang is called a match. The match is determined by the best two out of
        // three sets. So if you win two sets, you win. If you each win a set, then you play a
        // third set to determine the winner.
        //Set scoring is the first person to win 6 games wins the set. You must win by 2 games. You
        //play a tie breaker at 6 games each. So at 5 games each you will play at least 2 more games.
        //If you get to 6 games each you play a tie breaker and count that as one game for a set
        //score of 7 to 6.
        //pseudo-code if currentsetwn p1 >= 2 and currentsetwon p2 <=1 then p1 is winner
        // if currentsetwon p2 >= 2 and p1 <= 1 then p2 is winnder
        // if currentsetwon player 1 = 1 and currentsetwon player 2 = 1 then increment p1 setswon
        //if currentsetwon player 2 = 1 and
        if ((p1SetsWon - p2SetsWon) >= 2) {
            String p1Winner = "Congrats " + p1 + " you've won the match!";
                    displayWinnerMessage(p1Winner);
            p1Point.setEnabled(false);
            p2Point.setEnabled(false);
            resetGame.setEnabled(false);
            resetSet.setEnabled(false);
        } else if ((p2SetsWon - p1SetsWon) >= 2) {
            String p2Winner = "Congrats " + p2 + " you've won the match!";
            displayWinnerMessage(p2Winner);
            p1Point.setEnabled(false);
            p2Point.setEnabled(false);
            resetGame.setEnabled(false);
            resetSet.setEnabled(false);
        } else displayWinnerMessage("");
    }

    private void displayWinnerMessage(String winner) {
        TextView winnerMessage = findViewById(R.id.overallWinner);
        winnerMessage.setText(String.valueOf(winner));
    }

    private void displayCurrentSet(int currentSet) {
        TextView currentSetValue = (TextView) findViewById(R.id.currentSetValue);
        currentSetValue.setText(String.valueOf(currentSet));
    }

    public void playerResetAll(View view) {

        p1Score = 0;
        p2Score = 0;
        deuceScoreP1 = 0;
        deuceScoreP2 = 0;
        p1GamesWon = 0;
        p2GamesWon = 0;
        currentSet = 1;
        p1SetsWon = 0;
        p2SetsWon = 0;
        displayForPlayer1(p1Score);
        displayForPlayer2(p2Score);
        displayPlayer1GamesWon(p1GamesWon);
        displayPlayer2GamesWon(p2GamesWon);
        displayDeucePointsForPlayer1(deuceScoreP1);
        displayDeucePointsForPlayer2(deuceScoreP2);
        displayPlayer1SetsWon(p1SetsWon);
        displayPlayer2SetsWon(p2SetsWon);
        displayCurrentSet(currentSet);
        displayWinnerMessage("");
        p1Point.setEnabled(true);
        p2Point.setEnabled(true);
        resetGame.setEnabled(true);
        resetSet.setEnabled(false);
    }
}
