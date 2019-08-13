package com.xsungroup.tms.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.xsungroup.tms.core.common.modelmapper.Convert;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

import java.lang.String;

/**
 * <p>
 * 登录实体
 * </p>
 *
 * @author 何立辉
 * @since 2019-08-06 09:54:25
 */

@ApiModel
@ToString
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class LoginDto extends Convert  implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "账号/手机号不能为空")
    @ApiModelProperty("账号/手机号")
    private String userName;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty("登陆密码")
    private String password;

}