package com.baizhi.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
public class Month implements Serializable {
    private String month;
    private String count;

    @Override
    public String toString() {
        return "Month{" +
                "month='" + month + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
