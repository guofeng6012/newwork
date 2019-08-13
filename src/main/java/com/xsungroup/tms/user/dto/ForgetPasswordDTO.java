package com.xsungroup.tms.user.dto;

import lombok.Data;

/**
 * @Author GF
 * @Date 2019-8-10 15:34:47
 * @Description
 **/
@Data
public class ForgetPasswordDTO {

    private String password;

    private String userId;

    private String smsCode;
}
