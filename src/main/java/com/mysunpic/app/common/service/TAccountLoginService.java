package com.mysunpic.app.common.service;

import com.mysunpic.app.common.BaseResponse;
import com.mysunpic.app.common.consts.MessageCodeConstants;
import com.mysunpic.app.common.consts.MessageConstants;
import com.mysunpic.app.common.entity.TAccountLoginEntity;
import com.mysunpic.app.common.repository.TAccountLoginRepository;
import com.mysunpic.app.web.login.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 用户登录service
 */
@Service
public class TAccountLoginService {
    @Autowired
    private TAccountLoginRepository tAccountLoginRepository;

    public BaseResponse getAccountInfo(UserModel user) {
        BaseResponse response = new BaseResponse();
        String accountId = user.getUserName();
        String password = user.getPassword();
        Optional<TAccountLoginEntity> accountInfo = tAccountLoginRepository.findByAccountId(accountId);
        if (accountInfo.isPresent() && password.equals(accountInfo.get().getPassword())) {
            response.setStatusCode(MessageCodeConstants.MESSAGE_SUCCESS);
            response.setStatusMsg(MessageConstants.MESSAGE_SUCCESS);
        } else {
            response.setStatusCode(MessageCodeConstants.MESSAGE_E20001);
            response.setStatusMsg(MessageConstants.MESSAGE_E20001);
        }

        return response;
    }
}
