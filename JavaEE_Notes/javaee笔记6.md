##MybatisPlus

- 使用generator生成代码之后要先给Dao层添加注释mapper

- 根据数据表生成的交叉（goods_item_supplier）类中定义的不符合驼峰命名法的成员变量在运行时会报错，需修改为满足驼峰命名法。

- 运行的Application主程序一定要放在代码的同级目录下

- generator和mybatisplus本体的版本有对应关系，导入时若本体的版本过低，运行会报错。

- 自动生成的TableId注释中type的值TypeId.AutoId无效，最后还是更换为雪花算法的AssignID

- 从老师那里抄来的Exception如果继承自RunTimeException可以避免必须要抛出异常

- QueryWrapper中gt全称为greaterthan，like才是查找对应值的函数。

- #打印SQL日志到控制台:mybatis-plus:  
  configuration: log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  
  
  
  
  
  
  
  

###Spring AOP

- Spring AOP能对类进行增强，例如使用Before或After在Service运行前后显示相关内容，类似于test中的before和after。

- Spring AOP可以通过@PointCut获取切入口后对于获取切入口的函数施加Advice,避免每次Advice都要获取切入口
