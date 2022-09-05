# springcloud2022
## 构建基础开发环境
### springboot与springcloud的版本选择：
一、大版本对应关系：  
  https://spring.io/projects/spring-cloud下【Adding Spring Cloud To An Existing Spring Boot Application】中的列表中可以查看大版本对应关系  
二、具体版本对应关系：  
  https://start.spring.io/actuator/info下【bom-ranges】节点可以查看具体的版本对应关系。  
### step01：构建父工程
  1、使用<properties>进行统一的Jar版本管理。  
  2、使用<dependencyManagement>进行jar管理： 只声明依赖，不实现引入。  
      子模块继承之后，锁定版本，同时子Modlue不用写group与version。
  3、父工程的POM文件：  
      <properties>
 <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
 <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
 <maven.compiler.source>1.8</maven.compiler.source>
 <maven.compiler.target>1.8</maven.compiler.target>
 <maven-jar-plugin.version>3.1.1</maven-jar-plugin.version>
 <spring.cloud-version>Hoxton.SR1</spring.cloud-version>
 <spring-cloud-alibaba-version>2.1.0.RELEASE</spring-cloud-alibaba-version>
 <spring.boot-version>2.2.2.RELEASE</spring.boot-version>
 <junit.version>4.12</junit.version>
 <log4j.version>1.2.17</log4j.version>
 <lombok.version>1.16.18</lombok.version>
 <mysql.version>5.1.47</mysql.version>
 <druid.version>1.1.16</druid.version>
 </properties>
 
 <dependencyManagement>
 <dependencies>
 <!-- 必备 -->
 <dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-dependencies</artifactId>
 <version>${spring.boot-version}</version>
 <type>pom</type>
 <scope>import</scope>
 </dependency>
 <dependency>
             <groupId>org.springframework.cloud</groupId>
             <artifactId>spring-cloud-dependencies</artifactId>
             <version>${spring.cloud-version}</version>
             <type>pom</type>
             <scope>import</scope>
         </dependency>
 <dependency>
 <groupId>com.alibaba.cloud</groupId>
 <artifactId>spring-cloud-alibaba-dependencies</artifactId>
 <version>${spring-cloud-alibaba-version}</version>
 <type>pom</type>
 <scope>import</scope>
 </dependency>
 <!-- 可选 -->
 <dependency>
 <groupId>mysql</groupId>
 <artifactId>mysql-connector-java</artifactId>
 <version>${mysql.version}</version>
 </dependency>
 <dependency>
 <groupId>com.alibaba</groupId>
 <artifactId>druid</artifactId>
 <version>${druid.version}</version>
 </dependency>
 <dependency>
 <groupId>org.mybatis.spring.boot</groupId>
 <artifactId>mybatis-spring-boot-starter</artifactId>
 <version>${mybatis.spring.boot.version}</version>
 </dependency>
 </dependencies>
 </dependencyManagement>

 <build>
 <plugins>
 <plugin>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-maven-plugin</artifactId>
 <configuration>
 <excludes>
 <exclude>
 <groupId>org.projectlombok</groupId>
 <artifactId>lombok</artifactId>
 </exclude>
 </excludes>
 </configuration>
 </plugin>
 </plugins>
 </build>





基于Nacos的微服务基础开发环境


cloud-service-9300-cloudLock:测试SpringBoot集成Redis、以及分布式锁的演示
