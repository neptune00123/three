package com.baizhi.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class Wen implements Serializable {
    private String thumbnail;
    private String title;
    private String score;
    private String author;
    private String broadcast;
    private String set_count;
    private String brief;
    private String create_date;

    @Override
    public String toString() {
        return "Wen{" +
                "thumbnail='" + thumbnail + '\'' +
                ", title='" + title + '\'' +
                ", score='" + score + '\'' +
                ", author='" + author + '\'' +
                ", broadcast='" + broadcast + '\'' +
                ", set_count='" + set_count + '\'' +
                ", brief='" + brief + '\'' +
                ", create_date='" + create_date + '\'' +
                '}';
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    public String getSet_count() {
        return set_count;
    }

    public void setSet_count(String set_count) {
        this.set_count = set_count;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
}
