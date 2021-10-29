package com.example.myapplication;

public class figure {

    private String m_name;
    private String m_clicked;

    public figure(String name, String clicked) {
        this.m_name = name;
        this.m_clicked = clicked;
    }

    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        this.m_name = name;
    }

    public String getClicked() {
        return m_clicked;
    }

    public void setClicked(String clicked) {
        this.m_clicked = clicked;
    }
}
