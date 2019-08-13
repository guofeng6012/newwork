package com.xsungroup.tms.user.service;

import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.user.dto.ForgetPasswordDTO;
import com.xsungroup.tms.user.dto.OrgExtendRegisterDTO;

/**
 * @Author GF
 * @Date 2019-7-29 09:54:10
 * @Description
 **/
public interface RegisterService {

    ResponseInfo register(OrgExtendRegisterDTO orgExtendDTO);

    ResponseInfo forgetCheck(String smsCode,String mobile);

    ResponseInfo forgetPassword(ForgetPasswordDTO forgetPasswordDTO);
}
