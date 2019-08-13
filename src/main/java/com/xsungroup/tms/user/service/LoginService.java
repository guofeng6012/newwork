package com.xsungroup.tms.user.service;

import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.user.dto.LoginDto;
import com.xsungroup.tms.user.vo.CurrentUser;

public interface LoginService {

    CurrentUser login(LoginDto user);




    ResponseInfo smsSend(String mobile);

}
