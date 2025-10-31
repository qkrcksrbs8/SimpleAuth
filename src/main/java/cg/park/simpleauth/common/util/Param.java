package cg.park.simpleauth.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Param extends HashMap {

    public Param() {}

    public Param(Object obj) {
        this.put("data", obj);
    }

    public Param(String key, String value) {
        super.put(key, value);
    }

    public Param set(String key, String value) {
        super.put(key, value);
        return this;
    }
    public Param set(String key, int value) {
        super.put(key, value);
        return this;
    }
    public Param set(int key, String value) {
        super.put(key, value);
        return this;
    }
    public Param set(int key, int value) {
        super.put(key, value);
        return this;
    }
    public Param set(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Param setClass(Object obj) {
        try {
            if (obj instanceof String
             || obj instanceof Number
             || obj instanceof Boolean
            ) {
                this.put("data", obj);
            }
            else {
                if (obj instanceof Map<?, ?> map) {
                    for (Map.Entry<?, ?> entry : map.entrySet()) {
                        this.put(String.valueOf(entry.getKey()), entry.getValue());
                    }
                }
                else {
                    for (Field field : obj.getClass().getDeclaredFields()) {
                        field.setAccessible(true);
                        this.put(field.getName(), field.get(obj));
                    }
                }
            }
        }
        catch (Exception e) {
            this.put("data", obj);
        }
        return this;
    }

    public Param setClass(Object obj, String key) {
        try {
            if (obj instanceof String
                || obj instanceof Number
                || obj instanceof Boolean
            ) {
                this.put(key, obj);
            }
            else {
                if (obj instanceof Map<?, ?> map) {
                    for (Map.Entry<?, ?> entry : map.entrySet()) {
                        this.put(String.valueOf(entry.getKey()), entry.getValue());
                    }
                }
                else {
                    for (Field field : obj.getClass().getDeclaredFields()) {
                        field.setAccessible(true);
                        this.put(field.getName(), field.get(obj));
                    }
                }
            }
        }
        catch (Exception e) {
            this.put(key, obj);
        }
        return this;
    }
}
