package cg.park.simpleauth.common.util;

import cg.park.simpleauth.common.enums.ResultType;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ApiResponse {

    private Param data;
    private String code;
    private String message;

    public ApiResponse(ResultType type) {
        this.code = type.getCode();
        this.message = type.getMessage();
    }

    public ApiResponse(ResultType type, Object obj, String Key) {
        this.code = type.getCode();
        this.message = type.getMessage();
        this.data = new Param().setClass(obj, Key);
    }

    public ApiResponse(Param data, String code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public ApiResponse(ResultType type, Object obj) {
        this.code = type.getCode();
        this.message = type.getMessage();
        this.data = new Param().setClass(obj);
    }

    public ApiResponse(ResultType type, ArrayList<?> list) {
        this.code = type.getCode();
        this.message = type.getMessage();
        this.data = new Param(list);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{")
          .append("\"code\"").append(":").append("\""+code+"\"")
          .append(",")
          .append("\"message\"").append(":").append("\""+message+"\"")
          .append(",")
          .append("\"data\"").append(":").append("\""+data+"\"")
          .append("}");
        return sb.toString();
    }
}
