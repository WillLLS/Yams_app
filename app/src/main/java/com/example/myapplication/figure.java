package com.example.myapplication;

public class figure{
    private String m_name;
    private String m_score;

    public figure(String m_name, String m_score) {
        this.m_name = m_name;
        this.m_score = m_score;
    }

    public String getName() {
        return m_name;
    }

    public String getScore() {
        return m_score;
    }

    public void setScore(String score){
        m_score = score;
    }
}
