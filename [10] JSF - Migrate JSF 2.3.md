# JSF - Migrate JSF 2.3

## `pom.xml`

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.example</groupId>
    
    <artifactId>MyProject</artifactId>
    
    <version>0.0.1-SNAPSHOT</version>
    
    <packaging>war</packaging>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <onFailMissingWebXml>false</onFailMissingWebXml>
    </properties>

    <dependencies>
        <dependency>
            <groupId>javax</groupId>
            <artifactId>javaee-api</artifactId>
            <version>8.0</version>
        </dependency>
        <dependency>
            <groupId>javax.faces</groupId>
            <artifactId>javax.faces-api</artifactId>
            <version>2.3</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish</groupId>
            <artifactId>javax.faces</artifactId>
            <version>2.4.0</version>
        </dependency>
        <dependency>
            <groupId>javax.enterprise</groupId>
            <artifactId>cdi-api</artifactId>
            <version>2.0</version>
        </dependency>
    </dependencies>

</project>
```

## `src/main/webapp/WEB-INF/faces-config.xml`

```text
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
        http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_2_3.xsd"

    version="2.3"
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<faces-config
    xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_2_3.xsd"
    version="2.3">

    <managed-bean>
        <managed-bean-name>bean</managed-bean-name>
        <managed-bean-class>com.example.bean.Bean</managed-bean-class>
        <managed-bean-scope>request</managed-bean-scope>
    </managed-bean>

</faces-config>
```

## `src/main/webapp/WEB-INF/web.xml`

```text
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
        http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"

    version="4.0"
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
    version="4.0">

    <display-name>MyProject</display-name>
    
    <servlet>
        <servlet-name>Faces Servlet</servlet-name>
        <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>Faces Servlet</servlet-name>
        <url-pattern>/faces/*</url-pattern>
        <url-pattern>*.xhtml</url-pattern>
    </servlet-mapping>
    
    <welcome-file-list>
        <welcome-file>index.xhtml</welcome-file>
    </welcome-file-list>

</web-app>
```

## `src/main/webapp/index.xhtml`

```xml
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
    xmlns:h="http://xmlns.jcp.org/jsf/html"
    xmlns:f="http://xmlns.jcp.org/jsf/core"
    xmlns:demos="http://xmlns.jcp.org/jsf/composite/demos"
    xmlns:t="http://xmlns.jcp.org/jsf/composite/components">

<h:head>
    <title>JSF 2.3</title>
</h:head>

<h:body>

    <h1><h:outputText value="JSF 2.3" /></h1>

</h:body>

</html>
```