package com.xsungroup.tms;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication(scanBasePackages = {"com.xsungroup.tms"})
@MapperScan(basePackages = {"com.xsungroup.tms.**.mapper","com.xsungroup.tms.core.supper"})
@EnableEncryptableProperties
@Slf4j
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("启动成功！！！");
    }


    @Override
    public void run(String... args) throws Exception {
      log.info("初始化");
    }


    /**
     * 图片上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("30MB"); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize("30MB");
        return factory.createMultipartConfig();
    }
}
