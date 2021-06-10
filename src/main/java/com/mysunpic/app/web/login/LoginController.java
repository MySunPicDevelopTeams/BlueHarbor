package com.mysunpic.app.web.login;

import com.mysunpic.app.common.BaseResponse;
import com.mysunpic.app.common.service.TAccountLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Authentication login controller
 *
 * @author zhouj39
 */
@RestController
public class LoginController {
    @Autowired
    public TAccountLoginService tAccountLoginService;

    @PostMapping(value = "/login")
    public BaseResponse login(@RequestBody UserModel user) {
        return tAccountLoginService.getAccountInfo(user);
    }

}
