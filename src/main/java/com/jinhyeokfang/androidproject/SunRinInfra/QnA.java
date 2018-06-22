package com.jinhyeokfang.androidproject.SunRinInfra;

public class QnA {
    private String writer, content, date;

    public QnA(String writer, String content, String date) {
        this.writer = writer;
        this.content = content;
        this.date = date;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent () {
        return content;
    }

    public String getDate () {
        return date;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }
}