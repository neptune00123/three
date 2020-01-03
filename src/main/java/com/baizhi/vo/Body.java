package com.baizhi.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class Body implements Serializable {
    private String thumbnail;
    private String title;
    private String author;
    private String type;
    private String set_count;
    private String create_date;


    @Override
    public String toString() {
        return "Body{" +
                "thumbnail='" + thumbnail + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", set_count='" + set_count + '\'' +
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSet_count() {
        return set_count;
    }

    public void setSet_count(String set_count) {
        this.set_count = set_count;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
}
