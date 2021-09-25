# Lombok

## 简介

LomBok是一款Java代码增强库，通过Lombok的注解，你可以不用写getter、setter、equals等方法，Lombok在编译时自动生成。



## 集成

1. 在IDEA 的Plugins中下载Lombok。

2. pom.xml中添加Lombok依赖，如下：

   ```xml
   <!--lombok依赖-->
   <dependency>
       <groupId>org.projectlombok</groupId>
       <artifactId>lombok</artifactId>
       <optional>true</optional>
   </dependency>
   ```



## 注解使用

 ### val

作用：可取代任意类型作为局部变量，省去写复杂的ArrayList

和Map.Entry类型。

```java
//val代替ArrayList<String>和String类型
val example = new ArrayList<String>();
//val代替Map.Entry<Integer,String>类型
val map = new HashMap<Integer, String>();

编译后会生成如下代码：
ArrayList<String> example = new ArrayList();
HashMap<Integer, String> map = new HashMap();
```



### @NonNull

作用：非空判断，传空值会抛出NullPointerException。

```java
public NonNullExample(@NonNull String name){
	this.name = name;
}
//会抛出NullPointerException
new NonNullExample(null);

// 编译后：
public NonNullExample(@NonNull String name) {
    if (name == null) {
        throw new NullPointerException("name is marked non-null but is null");
    } else {
        this.name = name;
    }
}

new NonNullExample((String)null);
```



### @CleanUp

作用：自动关闭资源。

```java
String inStr = "Hello World!";
//使用输入输出流自动关闭，无需编写try catch和调用close()方法
@Cleanup ByteArrayInputStream in = new ByteArrayInputStream(inStr.getBytes("UTF-8"));
@Cleanup ByteArrayOutputStream out = new ByteArrayOutputStream();
byte[] b = new byte[1024];
while (true) {
    int r = in.read(b);
    if (r == -1) break;
    out.write(b, 0, r);
}
String outStr = out.toString("UTF-8");
System.out.println(outStr);

// 编译后：
String inStr = "Hello World!";
ByteArrayInputStream in = new ByteArrayInputStream(inStr.getBytes("UTF-8"));

try {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try {
        byte[] b = new byte[1024];

        while(true) {
            int r = in.read(b);
            if (r == -1) {
                String outStr = out.toString("UTF-8");
                System.out.println(outStr);
                return;
            }

            out.write(b, 0, r);
        }
    } finally {
        if (Collections.singletonList(out).get(0) != null) {
            out.close();
        }

    }
} finally {
    if (Collections.singletonList(in).get(0) != null) {
        in.close();
    }

}
```



### @Getter/@Setter

作用：自动生成getter/setter方法。

```java
@Getter
@Setter
private String name;

// 编译后：
private String name;

public String getName() {
    return this.name;
}

public void setName(final String name) {
    this.name = name;
}
```



### @ToString

作用：自动生成toString方法打印日志。

默认包含所有类属性，使用@ToString.Exclude注解可排除属性生成。

```java
@ToString.Exclude
private Long id;
private String name;
private Integer age;

// 编译后：
public String toString() {
    return "ToStringExample(name=" + this.name + ", age=" + this.age + ")";
}
```



### @EqualsAndHashCode

作用：自动生成hashCode和equals方法。

默认包含所有类属性，使用@EqualsAndHashCode.Exclude可以排除属性的生成。



### @XxConstructor

作用：自动生成构造方法。有@NoArgsConstructor、@RequiredArgsConstructor和@AllArgsConstructor三个注解可以使用。

- @NoArgsConstructor：生成无参构造函数。
- @RequiredArgsConstructor：生成包含必须参数的构造函数，使用@NonNull注解的类属性为必须参数。
- @AllArgsConstructor：生成包含所有参数的构造函数。

```java
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
public class ConstructorExample {
    @NonNull
    private Long id;
    private String name;
    private Integer age;
```

```java
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
public class ConstructorExample {
    @NonNull
    private Long id;
    private String name;
    private Integer age;

    public static void main(String[] args) {
        //无参构造器
        ConstructorExample example1 = new ConstructorExample();
        //全部参数构造器
        ConstructorExample example2 = new ConstructorExample(1L,"test",20);
        //@NonNull注解的必须参数构造器
        ConstructorExample example3 = ConstructorExample.of(1L);
    }
}

// 编译后：
public ConstructorExample() {
}

private ConstructorExample(@NonNull final Long id) {
    if (id == null) {
        throw new NullPointerException("id is marked non-null but is null");
    } else {
        this.id = id;
    }
}

public static ConstructorExample of(@NonNull final Long id) {
    return new ConstructorExample(id);
}

public ConstructorExample(@NonNull final Long id, final String name, final Integer age) {
    if (id == null) {
        throw new NullPointerException("id is marked non-null but is null");
    } else {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

```



### @Data

作用：组合注解。@ToString、@EqualsAndHashCode、@Getter、@Setter和@RequiredArgsConstructor的组合体。

修饰类。



### @Value

作用：该类 和 其属性为final，无法被继承。



### @Builder

作用：可以通过建造者模式来创建对象，建造者模式加链式调用，创建对象很方便。



```java
BuilderExample example = BuilderExample.builder()
    .id(1L)
    .name("test")
    .age(20)
    .build();
System.out.println(example);

// 编译后：
public BuilderExample.BuilderExampleBuilder id(final Long id) {
    this.id = id;
    return this;
}
...
```



### @SneakyThrows

作用：自动捕获并抛出异常。

```java
//自动抛出异常，无需处理
    @SneakyThrows(UnsupportedEncodingException.class)
public static byte[] str2byte(String str){
    return str.getBytes("UTF-8");
}

// 编译后：
public class SneakyThrowsExample {
    public SneakyThrowsExample() {
    }

    public static byte[] str2byte(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException var2) {
            throw var2;
        }
    }
}
```



### @Synchronized

作用：修饰方法来实现同步访问。

往往通过synchronized关键字来修饰方法实现同步访问，注解@Synchronized也可以。



```java
@Synchronized
@SneakyThrows
public void reduceCount(Integer id) {
    if (count > 0) {
        Thread.sleep(500);
        count--;
        System.out.println(String.format("thread-%d count:%d", id, count));
    }
}

// 编译后：
public void reduceCount(Integer id) {
    try {
        synchronized(this.$lock) {
            if (this.count > 0) {
                Thread.sleep(500L);
                Integer var3 = this.count;
                Integer var4 = this.count = this.count - 1;
                System.out.println(String.format("thread-%d count:%d", id, this.count));
            }

        }
    } catch (Throwable var7) {
        throw var7;
    }
}
```



### @With

作用：对原对象进行克隆，改变其的一个属性，原对象需要指定全参构造方法。

```java
@With
@AllArgsConstructor
public class WithExample {
    private Long id;
    private String name;
    private Integer age;

    public static void main(String[] args) {
        WithExample example1 = new WithExample(1L, "test", 20);
        WithExample example2 = example1.withAge(22);
        //将原对象进行clone并设置age，返回false
        System.out.println(example1.equals(example2));
    }
}

// 编译后：
private Long id;
    private String name;
    private Integer age;

    public WithExample withId(final Long id) {
        return this.id == id ? this : new WithExample(id, this.name, this.age);
    }
...

```



### @Getter(lazy=true)

```java
@Getter(lazy = true)
private final double[] cached = expensive();

private double[] expensive() {
    double[] result = new double[1000000];
    for (int i = 0; i < result.length; i++) {
        result[i] = Math.asin(i);
    }
    return result;
}

// 编译后：
 private final AtomicReference<Object> cached = new AtomicReference();

public double[] getCached() {
    Object value = this.cached.get();
    if (value == null) {
        synchronized(this.cached) {
            value = this.cached.get();
            if (value == null) {
                double[] actualValue = this.expensive();
                value = actualValue == null ? this.cached : actualValue;
                this.cached.set(value);
            }
        }
    }

    return (double[])((double[])(value == this.cached ? null : value));
}
```



### @Log

作用：自动生成日志对象，通过log对象可以直接打印日志。

```java
@Log
public class LogExample {
    public static void main(String[] args) {
        log.info("level info");
        log.warning("level warning");
        log.severe("level severe");
    }
}

// 编译后：
 private static final Logger log = Logger.getLogger(LogExample.class.getName());

    public LogExample() {
    }

```



### @Slf4j

作用：根据日志实现的不同，有@Log、@Log4j、@Log4j2、@Slf4j等可以使用。

```java
// 编译后：
private static final Logger log = LoggerFactory.getLogger(LogSlf4jExample.class);
public LogSlf4jExample() {
}
```



- 摘自[macrozheng.com](http://www.macrozheng.com/#/reference/lombok_start?id=lombok%e7%ae%80%e4%bb%8b)