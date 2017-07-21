package com.king.common.web;

import java.io.Serializable;

/**
 * @author by yjh
 * @DateTime 2017/7/21 19:43
 */
public class JSONMessage implements Serializable {

    private Status status;
    private String msg;
    private Object data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public enum Status {
        SUCCESS("成功", 200),
        FAIL("失败", 500);

        Status(String name, int value) {
            this.name = name;
            this.value = value;
        }

        private String name;
        private int value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
