package com.xsungroup.tms;


import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 使用jasypt 加密配置文件
 * 配置文件写法  ENC(密文)
 *
 * 一般会将秘钥写到安装服务器环境变量中
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest("spring.profiles.active=dev")
public class EncryptorTest {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void getPass() {

        String sePass = encryptor.encrypt("XINya123456");//security.user.name
        String seName = encryptor.encrypt("root");//security.user.password
        System.out.println("seName = " + seName);
        System.out.println("sePass = " + sePass);

        // TODO: 2019/7/31  需要将现有的配置  jasypt.encryptor.password 转移为服务器参数 杜绝在代码中 出现加密秘钥
    }
}
