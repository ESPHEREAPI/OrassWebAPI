/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;

/**
 *
 * @author fabrice
 */
public class PropertyHelp implements Serializable{
  
private String code;
   private String valueFr;
   private String valueEn;

    public PropertyHelp() {
    }

   
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValueFr() {
        return valueFr;
    }

    public void setValueFr(String value) {
        this.valueFr = value;
    }

    public String getValueEn() {
        return valueEn;
    }

    public void setValueEn(String valueEn) {
        this.valueEn = valueEn;
    }
   
   
}
