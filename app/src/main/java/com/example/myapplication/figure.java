package com.example.myapplication;

public class figure {

    private String m_name;
    private String m_score;

    public figure(String name, String clicked) {
        this.m_name = name;
        this.m_score = clicked;
    }

    public String getName() {
        return m_name;
    }

    public String getScore() {
        return m_score;
    }

    public void setScore(String score) {
        this.m_score = score;
    }
}
