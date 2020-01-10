# JDBC

- **JDBC（Java Data Base Connectivity,java数据库连接）是一种用于执行SQL语句的Java API，可以为多种关系数据库提供统一访问，它由一组用Java语言编写的类和接口组成。** 
- **使用jdbc可以操作本机或局域网中机子上的数据库。** 
- **连接好后进而可以创建表，再对表进行增、删、改、查。**

## jdbc使用步骤

**1.导入jar包**

```
导入mysql-connector-java-5.1.47.jar项目
1.在项目中新建lib
2.把jar拷贝到lib中
3.添加jar到项目中
```

**2.加载驱动**

```java
 //在代码块中加载驱动
 //Class.forName("com.mysql.jdbc.Driver");
 //Class.forName("oracle.jdbc.driver.OracleDriver");
 //Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
{
    try {
    	Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
//注：更具访问数据库不同，加载不同数据库
```

**3.连接数据库**

```java
>mysql -utoot -p123456
//连接信息
private String user = "root";
private String password = "123456";
/*
	 * 	数据库连接地址
	 * 		jdbc:mysql:// : 协议名称
	 * 		localhost:3306 : 服务器的地址
	 * 		jdbc_db ： 数据库名称
	 * */
private String url = "jdbc:mysql://localhost:3306/jdbc_db";

public void getConn() {
	try {
		//得到数据库连接
		connection = DriverManager.getConnection(url, user, password);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
```

**4.获取操作句柄**

```
Statement sta = null;
//通过数据库连接得到操作数据库的句柄
sta = connection.createStatement();
```

**5.执行SQL语句**

```
String sql = "INSERT user(name,password) VALUES ('"+ user.getName() +"','"+user.getPassword()+"')";
//插入
sta.executeUpdate(sql);
```

**6.如果是查询封装结果**

```
List<User> users = new ArrayList<User>();
ResultSet set = null;

while (set.next()) {
    User user = new User();
    user.setId(set.getInt("id"));
    user.setName(set.getString("name"));
    user.setPassword(set.getString("password"));
    users.add(user);
}
```

## 注意

数据库的链接非常消耗资源，所以我们一般只创建一个链接