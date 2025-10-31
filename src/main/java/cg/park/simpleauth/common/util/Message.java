package cg.park.simpleauth.common.util;

import cg.park.simpleauth.common.enums.ResultType;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Message {

    private Param data;
    private String code;
    private String message;

    public Message(Param data, String code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public Message(ResultType type) {
        this.code = type.getCode();
        this.message = type.getMessage();
    }

    public Message(ResultType type, Object obj) {
        this.code = type.getCode();
        this.message = type.getMessage();
        this.data = new Param().setClass(obj);
    }

    public Message(ResultType type, Object obj, String Key) {
        this.code = type.getCode();
        this.message = type.getMessage();
        this.data = new Param().setClass(obj, Key);
    }

    public Message(ResultType type, ArrayList<?> list) {
        this.code = type.getCode();
        this.message = type.getMessage();
        this.data = new Param(list);
    }

}
