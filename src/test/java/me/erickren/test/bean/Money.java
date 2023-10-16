package me.erickren.test.bean;

/**
 * Test bean.
 * DateTime: 2023/09/18 - 13:50
 * Author: ErickRen
 */
public class Money {

    private int count;
    
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
                ", status='" + status + '\'' +
                '}';
    }
}
