package com.wimpy.filler.enums;

public enum ValueBasedType {

  INT,
  DOUBLE,
  BYTE,
  SHORT,
  LONG,
  FLOAT,
  BOOLEAN,
  CHAR,
  JAVA_LANG_INTEGER,
  JAVA_LANG_DOUBLE,
  JAVA_LANG_BYTE,
  JAVA_LANG_SHORT,
  JAVA_LANG_LONG,
  JAVA_LANG_FLOAT,
  JAVA_LANG_BOOLEAN,
  JAVA_LANG_CHARACTER,
  JAVA_LANG_STRING,
  JAVA_MATH_BIGDECIMAL,
  JAVA_UTIL_DATE,
  OBJECT;

  public static final ValueBasedType value(String value) {
    try {
      return valueOf(value);
    } catch (Exception e) {
      return OBJECT;
    }
  }

}
