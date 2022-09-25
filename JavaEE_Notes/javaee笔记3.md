可使用Commons logging进行debug的log输出以及异常的抛出。
<try {
    ...
} catch (Exception e) {
    log.error("got exception!", e);
}>

Log4j作为Common logging的框架，可以实现将日志输出到文件中等特殊功能
#
### 问答
问：如果使用反射可以获取private字段的值，那么类的封装还有什么意义？

答：反射是一种非常规的用法，使用反射，首先代码非常繁琐，其次，它更多地是给工具或者底层框架来使用，目的是在不知道目标实例任何信息的情况下，获取特定字段的值。
此外，setAccessible(true)可能会失败。如果JVM运行期存在SecurityManager，那么它会根据规则进行检查，有可能阻止setAccessible(true)。例如，某个SecurityManager可能不允许对java和javax开头的package的类调用setAccessible(true)，这样可以保证JVM核心库的安全。
#

除Object外，其他任何非interface的Class都必定存在一个父类类型。

可以通过动态代理将接口进行单独实例化并使用

ArrayList<Integer>和ArrayList<Number>两者完全没有继承关系,Number类型的泛型中可以添加Float等类型。

自定义的类实现Comparable<>接口之后可使用泛型的相关方法

