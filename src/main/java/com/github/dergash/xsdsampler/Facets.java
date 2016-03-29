package com.github.dergash.xsdsampler;

import java.math.BigDecimal;
import java.util.List;
import java.util.LinkedList;

public class Facets {
    String baseType;

    List<String> enums;
    List<String> patterns;
    public BigDecimal minValue;
    public BigDecimal maxValue;
    public Integer totalDigits;
    public Integer fractionDigits;
    public Integer length;
    public Integer minLength;
    public Integer maxLength;

    public Facets(String baseType) {
        this.baseType = baseType;
    }

    public List<String> getEnums() {
        if(this.enums == null) {
            this.enums = new LinkedList<String>();
        }
        return this.enums;
    }

    public List<String> getPatterns() {
        if(this.patterns == null) {
            this.patterns = new LinkedList<String>();
        }
        return this.patterns;
    }

    public FieldValueType getFieldType() {
        FieldValueType result;
        switch(baseType) {
            case "string":
                if(this.enums != null && this.getEnums().size() > 0) {
                    result = FieldValueType.Enum;
                }
                else if(this.patterns != null && this.getPatterns().size() > 0) {
                    result = FieldValueType.StringPatternized;
                }
                else result = FieldValueType.String;
                break;
            case "decimal":
                result = FieldValueType.Decimal;
                break;
            case "dateTime":
                result = FieldValueType.DateTime;
                break;
            case "date":
                result = FieldValueType.Date;
                break;
            case "boolean":
                result = FieldValueType.Boolean;
                break;
            case "base64Binary":
                result = FieldValueType.Base64Binary;
                break;
            default:
                String errMsg = "baseType [" + baseType + "] is not supported. Next baseTypes is available: ";
                errMsg += "string, decimal, dateTime, date, boolean, base64Binary";
                throw new IllegalArgumentException(errMsg);
        }
        return result;
    }
}
