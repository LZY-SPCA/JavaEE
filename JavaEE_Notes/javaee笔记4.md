##MockMvc

- 实现controller测试方法过程中使用mvc避免启动Tomcat消耗过多时间与资源；

- 当需要读取json格式的内容时，可使用com.alibaba.fastjson.JSON中的toJSONString()方法将新建的entity实体类转换为json，但记得添加@Data注释；

- 需要参数Parm时使用Parm()函数即可添加参数，多个Parm可叠加使用。

---

Swagger和新版本SpringBoot结合使用需添加注解@EnableWebMvc，否则会报空指针异常。

---

##Http协议

- 最常用的HTTP方法是GET方法，用于从web服务器检索数据。例如，如果要从特定网站加载图像。

- PUT方法将会完全地替代目标URL下的资源，不论目标URL下是否存在资源。使用这个方法，你可以创建一个全新的资源或覆盖有一个已经存在的资源，前提是您知道确切的请求URI。

- POST方法用于发送用户生成的数据发送到web服务器。比如说，当一个用户对论坛进行了评论或者上传了头像，这时候就应该使用POST方法。如果您不知道新创建的资源应该驻留在哪里，没有确定的URL，那么也应该使用POST方法。

- 状态码：200,201,202,204,400,403,404,500

---

##Spring配置文件

- 可使用application.properties或application.yaml进行配置，文件位于项目resources目录下

- 利用层级进行定义，例：Server : port: 82（注：冒号后有空格，实际使用记得缩进）

- yml与yaml文件同理

---

##Lombok

- 可用于进行代码简化。@Data之后会自动生成类属性的get和set方法。

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.12</version>
</dependency>
```

---

##注释

- RestController

- RequsetMapping

- GetMapping

- PostMapping

- PutMapping

- DeleteMapping

---

##Swagger

- 地址：ip:port/swagger-ui/index.html/

```xml
<dependency>
    <groupId>io.springfoc</groupId>
    <artifactId>springfoc-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
```

- EnableSwagger2在Swagger3中已弃用
  
  
