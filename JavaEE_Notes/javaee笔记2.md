在程序编写过程中发现，Object User = mainClass.newInstance();中的newInstance方法已被弃用，应使用Object User = mainClass.getDeclaredConstructor().newInstance();取代。

junit测试中获取异常时要注意函数是否抛出异常以及是否自己catch异常，若内部已经catch了异常那么test中将catch不到异常。

在测试中利用反射获取了函数后利用Invoke调用函数时不会抛出函数本身抛出的异常，而是由Invoke抛出一个InvocationTargetException，基于这个原因，本人对于测试中使用反射的意义存在疑问。

基于上面的问题，目前不清楚如何测试程序中调用的注解函数。

疑问：反射由于可以通过setAccessible(true)来修改私有变量，这样的技术是否会存在安全隐患。

可仿照StringBuilder的链式调用来设计类与函数，只需要在函数调用结束后返回this即可

使用record关键字可以替代final从而实现一行写出一个不变类。不变类需要覆写toString()、equals()和hashCode()，但是record关键字已经自动覆写。

BigInteger转换为long类型时使用longValueExact()可在超出long范围时抛出ArithmeticException。

可以使用多个catch语句，每个catch分别捕获对应的Exception及其子类。JVM在捕获到异常后，会从上到下匹配catch语句，匹配到某个catch后，执行catch代码块，然后不再继续匹配，因此存在多个catch的时候，catch的顺序非常重要：子类必须写在前面。

自定义Exception建议从RunTimeException继承开始，避免强制try catch的麻烦。

用空字符串""替代null可以避免NullPointerException。

Spring核心概念：使用对象时，在程序中不要主动使用new产生对象，转换为由外部提供对象

Spring 的 bean 机制个人认为就是反射的一种封装。

默认状态下String创建的对象为单例对象，singleton默认为单例，prototype为非单例。

Spring初始化方法会在类中属性设置之后执行