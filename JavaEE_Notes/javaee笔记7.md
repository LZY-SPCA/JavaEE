##Spring AOP

- Spring AOP可以理解成一种封装了的反射功能，可以提供一个切面供开发者或是使用者判断运行状态以及运行时长。

- 根据实际切面使用频率判断是否需要将切面封装为函数，后续可围绕切面函数进行分析。

- 实际生产中并不适用System.out.println()这样的方法进行具体的debug，大多是是借助logger的输出，PointCut切面中就大量使用logger.info等方法将输出显示到日志输出上。

- Calendar是日历类，在Date后出现，替换掉了许多Date的方法。该类将所有可能用到的时间信息封装为静态成员变量，方便获取。

##Spring Security

- Authentication(认证)and Authorization(授权)
  
  - 身份验证是关于验证您的凭据，如用户名/用户ID和密码，以验证您的身份。
  
  - 授权发生在系统成功验证您的身份后，最终会授予您访问资源的权限。简单来说，授权决定了您访问系统的能力以及达到的程度。验证成功后，系统验证您的身份后，即可授权您访问系统资源。

- SpringSecurity需要JDK的版本>=11（IDEA下jdk又出了点小毛病）

- 可在intialize时选择Spring Security，省略在pom中导入相应包

- 使用builder模式可以快速创建一个实体对象，例：
  
  ```java
      User.builder().username(username)
      .password(new BCryptPasswordEncoder().encode(user.getPassword()))  
      .roles(roles).build();
  ```

- **cookie机制和session机制的区别**
  
  - cookie机制采用的是在客户端保持状态的方案，而session机制采用的是在服务器端保持状态的方案。
  - 
