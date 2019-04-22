package com.wimpy.filler.model.initiator;

import java.math.BigDecimal;
import java.util.Date;

import com.wimpy.filler.enums.ValueBasedType;

public class ValueBasedInitiator {

  private final static String DEFAULT_STRING = "string";
  private final static Date DEFAULT_DATE = new Date();

  public Object getDefaultPrimitive(ValueBasedType fieldType) {
    switch (fieldType) {
      case INT:
      case JAVA_LANG_INTEGER:
        return new Integer(1);
      case DOUBLE:
      case JAVA_LANG_DOUBLE:
        return new Double(1D);
      case BYTE:
      case JAVA_LANG_BYTE:
        return new Byte(DEFAULT_STRING);
      case SHORT:
      case JAVA_LANG_SHORT:
        return new Short(DEFAULT_STRING);
      case LONG:
      case JAVA_LANG_LONG:
        return new Long(1L);
      case FLOAT:
      case JAVA_LANG_FLOAT:
        return new Float(1F);
      case BOOLEAN:
      case JAVA_LANG_BOOLEAN:
        return new Boolean(true);
      case CHAR:
      case JAVA_LANG_CHARACTER:
        return new Character(DEFAULT_STRING.charAt(0));
      case JAVA_LANG_STRING:
        return new String(DEFAULT_STRING);
      case JAVA_UTIL_DATE:
        return DEFAULT_DATE;
      case JAVA_MATH_BIGDECIMAL:
        return new BigDecimal(1);
      default:
        return null;
    }
  }

}
