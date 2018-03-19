新加项目
1.pom.xml
<artifactId>yz-web</artifactId>
<finalName>yz</finalName>
2.prod/jdbc.properties
修改数据库连接
3.web.xml
<context-param>
    <param-name>webAppRootKey</param-name>
    <param-value>yz.root</param-value>
  </context-param>
4.CourseController.java
String bookRootDir = System.getProperty(Constants.YZ_ROOT_PATH)+Constants.BOOK_DIR;
5.com.yckj.school.common.constant.Constants.java
public static final String YZ_ROOT_PATH = "yz.root";

注意：3/4/5保持一致
6.修改fastdfs配置
7.打包
clean package prod
8.修改首页Logo



