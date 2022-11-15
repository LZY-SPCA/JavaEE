##Filter过滤器

- 过滤器链基于指针链

- 通过Delegate进行过滤器功能细分

##Authentication

- 认证结束后将所有认证后的信息保存在SecurityContext形成token

- DaoAuthenticationProvider在认证过程中调用Service以及Encoder等工具类

- 服务器存储session_id，客户端通过cookie携带sessions_id进行身份验证

- 分布式系统Session认证困难

- Token：信息加密生成的哈希值

- JWT内容是由三部分组成：HEADER、PAYLOAD、VERIFY SIGNATURE。
  
      每个信息用"."号分隔开，是base64编码的。
      
      HEADER：token类型和加密算法，此处的加密算法是签名的加密算法
      
      PAYLOAD：具体的业务数据，比如用户名等
      
      VERIFY SIGNATURE：数字签名密文信息

- csrf是避免跨站攻击的工具

##Spring Boot

- 可使用Optional<User>来承接JPA中findById的返回值，后续在判断该对象是否为null
