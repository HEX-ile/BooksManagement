package com.bm.common.swagger;

/**
 * @author hex
 * @date 2022/3/25
 */
public class SwaggerObject {
    private String key;
    private String description;
    private Class<?> dataType;

    private SwaggerObject() {
    }

    public SwaggerObject(String key, String description, Class<?> dataType) {
        this.key = key;
        this.description = description;
        this.dataType = dataType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Class<?> getDataType() {
        return dataType;
    }

    public void setDataType(Class<?> dataType) {
        this.dataType = dataType;
    }
}
