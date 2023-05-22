package com.innovation.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("down")
@Data
public class Down {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Down(int id, int count) {
        this.id = id;
        this.count = count;
    }

    public Down() {
        super();
    }

    private int id;
    private int count;
}
