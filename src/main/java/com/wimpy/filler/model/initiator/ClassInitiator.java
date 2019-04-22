package com.wimpy.filler.model.initiator;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.wimpy.filler.model.ModelFiller;

public class ClassInitiator {

  public ModelFiller modelGenerator;

  public ClassInitiator(ModelFiller modelGenerator) {
    this.modelGenerator = modelGenerator;
  }

  public Object initJavaBean(Class<?> classObject)
      throws InstantiationException, IllegalAccessException, Exception {
    Object object = classObject.newInstance();
    for (Field field : classObject.getDeclaredFields()) {
      int modifiers = field.getModifiers();
      if (Modifier.isFinal(modifiers) || Modifier.isStatic(modifiers)) {
        continue;
      }
      field.setAccessible(true);
      Class<?> declaringClass = field.getType();
      field.set(object, modelGenerator.initiate(declaringClass, field.getGenericType()));
    }
    return object;
  }

  public Object initEnum(Class<?> classObject) {
    return classObject.getEnumConstants()[0];
  }

  public Object initSet(Type type) throws Exception {
    Set<Object> set = new java.util.HashSet<>();
    Class<?> setClass = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
    set.add(modelGenerator.initiate(setClass, null));
    return set;
  }

  public Object initMap(Type type) throws Exception {
    Map<Object, Object> map = new HashMap<>();
    Class<?> keyClass = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
    Type valueType = ((ParameterizedType) type).getActualTypeArguments()[1];
    Class<?> valueClass = null;
    Object key = modelGenerator.initiate(keyClass, null);
    try {
      valueClass = (Class<?>) valueType;
      map.put(key, modelGenerator.initiate(valueClass, null));
    } catch (Exception e) {
      ParameterizedType valueParamType = (ParameterizedType) valueType;
      map.put(key, modelGenerator.initiate((Class<?>) valueParamType.getRawType(), valueType));
    }
    return map;
  }

  public Object initList(Type type) throws Exception {
    ArrayList<Object> list = new ArrayList<>();
    Class<?> stringListClass = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
    list.add(modelGenerator.initiate(stringListClass, null));
    return list;
  }

}
