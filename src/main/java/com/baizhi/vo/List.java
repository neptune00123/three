package com.baizhi.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class List implements Serializable {
    private String title;
    private String download_url;
    private String size;
    private String duration;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "List{" +
                "title='" + title + '\'' +
                ", download_url='" + download_url + '\'' +
                ", size='" + size + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
