this project is a small modular and expandable library that allows you to eliminate the writing of boilerplate code for CRUD operations in the development of Spring applications that work with databases and microservices. It implements a full set of base operations to Create, Read, Update and Delete your entities. Currently, it works with JPA databases in Jdbc project and MongoDB project and micro client project but you can expand it to work with other databases.
### Requirements
The library works with Java 8+, ladder Core 1.0.1+ and repository implementation such as
mongo client project , jdbc client project or micro client or your implementation

## [Core](https://github.com/nimamoosavi/core/wiki)

[[images/framework-diagram.jpg | "Crud Diagram"]]


### Structure

this project create default crud and used mapstruct library for casting object to another object

[[/images/crud.jpg | "Crud Diagram"]]
- [BaseController](BaseController)
- [GeneralService](GeneralService)
- [GeneralMapper](GeneralMapper)
- [GeneralRepository](GeneralRepository)

### maven

~~~
<properties>
        <java.version>1.8</java.version>
        <org.mapstruct.version>1.3.0.Final</org.mapstruct.version>
        <org.projectlombok.version>1.18.8</org.projectlombok.version>
</properties>

<dependency>
            <groupId>app.ladderproject</groupId>
            <artifactId>crud</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>app.ladderproject</groupId>
            <artifactId>Jdbc-client</artifactId>
            <version>0.0.1-SNAPSHOT</version>
</dependency>

<build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.3.0.RELEASE</version>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version> <!-- or newer version -->
                <configuration>
                    <source>1.8</source> <!-- depending on your project -->
                    <target>1.8</target> <!-- depending on your project -->
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>${org.mapstruct.version}</version>
                        </path>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>${org.projectlombok.version}</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
        </plugins>
    </build>
~~~
