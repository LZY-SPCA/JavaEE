# 罗致远的javaee笔记

## 第一周

浮点数由于不能精确保存，因此在计算结果和比较数值时需特别对待。  
    比较x和y是否相等，先计算其差的绝对值:
    <double r = Math.abs(x - y);
    再判断绝对值是否足够小:
    if (r < 0.00001) {
    可以认为相等
    }>

强制类型转换将直接抛弃小数部分，若要实现四舍五入则可以先对浮点数+0.5后再强制类型转换。

关系运算符的优先级从高到低依次是：
!  
\>，>=，<，<=  
==，!=  
&&  
||

String中的转义字符：
String str="abc\"def";
常用：\r,\n,\t;

多行字符串：
    <String s = """
                SELECT * FROM
                users
                WHERE id > 100
                ORDER BY name DESC
                """;>

Java数组所有元素初始化为默认值，整型都是0，浮点型是0.0，布尔型是false；

格式化输出System.out.printf("%.2f\n",3.1415926),
详细的格式化参数请参考JDK文档java.util.Formatter

java使用Scanner获取输入时若nextInt后要获取nextLine的话，nextInt()只会读取数值，剩下"\n"还没有读取，并将cursor放在本行中。所以，inScanner.nextLine()直接读取“\n”并结束了。
在nextInt后额外添加一次nextLine可解决该问题。

**引用类型，尤其是String类型比较值时应使用equals，判断是否属于同一个对象时才使用==**

    <int n = 1;
        while (n > 0) {
            sum = sum + n;
            n ++;
            //由于java的int存在最大值，因此到达最大值后+1变为负数，跳出循环
        }>

函数传参时，如果传入的参数为基本类型，则只是单纯复制参数值；如果传入的参数为引用类型，则是指向同一个对象。

    <Person p = new Person();
        String bob = "Bob";
        p.setName(bob); // 传入bob变量
        System.out.println(p.getName()); // "Bob"
        bob = "Alice"; // bob改名为Alice
        System.out.println(p.getName()); // "Bob"
        //由于String类型为不可变类型，Bob仍处于内存中

在Java中，任何class的构造方法，第一行语句必须是调用父类的构造方法，因此要么父类存在默认构造方法，子类自动调用，要么子类手动调用父类构造方法。
