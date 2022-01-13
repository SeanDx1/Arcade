package com.example.seanactivity;

public class ScoreBoard {
    private String[][]matrix;

    public ScoreBoard(){
         matrix = new String[2][3];
         matrix[0][0]= "GuessingNumber";
         matrix[0][1]= "SevenBoom";
         matrix[0][2]= "TicTacToe";
         matrix[1][0]="0";
         matrix[1][1]="0";
         matrix[1][2]="0";
    }

    public String[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }
}
