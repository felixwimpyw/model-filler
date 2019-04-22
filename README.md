# Model Filler


What?
=====

Model Filler helps developer to filling all beans recursively all inside Java beans (POJO) with just one line.

Why?
=====

While making unit test, people usually tends to only initiate fields or object they used on their unit test. This often leads to unexpected logic accessing null fields. For preventing that, we must define all objects inside the bean so we can create a better and more reliable unit test. But defining one by one fields is painfull and can impact unit test readability. So using this Model Filler, we can just call one liner code to fill all field inside the Bean.


How To Use?
=====

```Java
ModelFiller filler = new ModelFiller();
YourBean bean = filler.initiate(YourBean.class);
//bean will be initiated with all field values inside
```

Support
=====
Model Filler will basically split any object to 3 type :
- Collection Based Object
- Value Based Object
- Java Bean (POJO)

### Collection Based Object
For all collection based object, model filler will generate using default object, then fill using generic value registered to that collection. For example List<String> list -> will be filled as Arraylist with one String inside the list.
- java.util.List -- default to java.util.ArrayList
- java.util.Map -- default to java.util.HashMap
- java.util.Set -- default to java.util.HashSet

### Value Based Object
For all field that are having direct value (primitive)
- int and Integer -- default to 1
- double and Double -- default to 1
- byte and Byte -- default to "string"
- short and Short -- default to "string"
- long and Long -- default to 1
- float and Float -- default to 1
- boolean and Boolean -- default to true
- char and Character -- default to 's'
- String -- default to "string"
- java.util.Date  -- default to new Date()
- java.lang.BigDecimal -- default to 1

### Java Bean
Other than collection based and value based, will try to recursively fill all fields inside this class.
