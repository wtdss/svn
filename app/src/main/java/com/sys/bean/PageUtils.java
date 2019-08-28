package com.sys.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public class PageUtils<T> implements Serializable {

    // 总记录数
    private int total;

    // 列表数据
    private List<T> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
