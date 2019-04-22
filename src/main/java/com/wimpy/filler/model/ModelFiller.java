package com.wimpy.filler.model;

import java.awt.List;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import com.wimpy.filler.enums.ValueBasedType;
import com.wimpy.filler.model.initiator.ClassInitiator;
import com.wimpy.filler.model.initiator.ValueBasedInitiator;

public class ModelFiller {

  private static final String DOT_DELIMITER = ".";
  private static final String UNDERSCORE_DELIMITER = "_";

  private final ValueBasedInitiator valueBasedDefaults;

  private final ClassInitiator classInitiator;

  public ModelFiller() {
    this.valueBasedDefaults = new ValueBasedInitiator();
    this.classInitiator = new ClassInitiator(this);
  }

  public Object initiate(Class<?> classObject, Type type) throws Exception {
    if (List.class.isAssignableFrom(classObject)) {
      return classInitiator.initList(type);
    } else if (Map.class.isAssignableFrom(classObject)) {
      return classInitiator.initMap(type);
    } else if (Set.class.isAssignableFrom(classObject)) {
      return classInitiator.initSet(type);
    } else if (Enum.class.isAssignableFrom(classObject)) {
      return classInitiator.initEnum(classObject);
    } else {
      String typeName =
          classObject.getName().toUpperCase().replace(DOT_DELIMITER, UNDERSCORE_DELIMITER);
      ValueBasedType fieldType = ValueBasedType.value(typeName);
      Object primitive = valueBasedDefaults.getDefaultPrimitive(fieldType);
      if (primitive == null) {
        return classInitiator.initJavaBean(classObject);
      } else {
        return primitive;
      }
    }
  }

}
