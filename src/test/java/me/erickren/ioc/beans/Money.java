package me.erickren.ioc.beans;

/**
 * Test bean.
 * DateTime: 2023/09/18 - 13:50
 * Author: ErickRen
 */
public class Money {
    
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Money{" +
                "count=" + count +
                '}';
    }
}
