package com.example.seanactivity;

public class ScoreBoard {
    private String Game_name,username1,username2,username3,username4,username5;
    private int score,score2,score3,score4,score5;
    private int layout;
    public ScoreBoard(String game_name,int layout){
        this.Game_name=game_name;
        score=0;
        score2=0;
        score3=0;
        score4=0;
        score5=0;
        this.layout = layout;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public String getGame_name() {
        return Game_name;
    }

    public void setGame_name(String game_name) {
        Game_name = game_name;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public String getUsername3() {
        return username3;
    }

    public void setUsername3(String username3) {
        this.username3 = username3;
    }

    public String getUsername4() {
        return username4;
    }

    public void setUsername4(String username4) {
        this.username4 = username4;
    }

    public String getUsername5() {
        return username5;
    }

    public void setUsername5(String username5) {
        this.username5 = username5;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public int getScore3() {
        return score3;
    }

    public void setScore3(int score3) {
        this.score3 = score3;
    }

    public int getScore4() {
        return score4;
    }

    public void setScore4(int score4) {
        this.score4 = score4;
    }

    public int getScore5() {
        return score5;
    }

    public void setScore5(int score5) {
        this.score5 = score5;
    }

    public String getName() {
        return Game_name;
    }

    public void setName(String name) {
        this.Game_name = name;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


}