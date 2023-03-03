package com.example.userserver;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.UnknownHostException;

@EnableSwagger2WebMvc
@MapperScan("com.example.userserver.mapper")
@SpringBootApplication
@Slf4j
public class UserServerApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(UserServerApplication.class, args);
        Environment env = application.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "应用 '{}' 运行成功! 访问连接:\n\t" +
                        "Swagger文档: \t\thttp://{}:{}/doc.html\n\t" +
                        "数据库监控: \t\thttp://{}:{}/druid\n" +
                        "导入Apifox地址：" +  "http://localhost:"+env.getProperty("server.port")+"/api/user/v2/api-docs?group=demo\n"+
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                "127.0.0.1",
                env.getProperty("server.port")+"/api/user",
                "127.0.0.1",
                env.getProperty("server.port"));
    }

}
