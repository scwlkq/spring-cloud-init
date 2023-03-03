package com.example.userapi.fallback;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.common.req.BaseResponse;
import com.example.common.req.ErrorCode;
import com.example.common.req.ResultUtils;
import com.example.userapi.api.UserApi;
import com.example.userapi.domain.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * @author sc
 */
@Component
public class UserApiFallback implements UserApi {

    @Override
    public void assertAdmin(HttpServletRequest request){
        return ;
    }

    @Override
    public BaseResponse<Long> userRegister(String userAccount, String userPassword) {
        return ResultUtils.error(ErrorCode.TIMEOUT_ERROR);
    }

    @Override
    public BaseResponse<String> userLogin(String userAccount, String userPassword) {
        return ResultUtils.error(ErrorCode.TIMEOUT_ERROR);
    }

    @Override
    public User getSafetyUser(User originUser) {
        return null;
    }


    @Override
    public BaseResponse<Boolean> isAdmin(HttpServletRequest request) {
        return ResultUtils.error(ErrorCode.TIMEOUT_ERROR);
    }

    @Override
    public BaseResponse<Boolean> isAdmin(User user) {
        return ResultUtils.error(ErrorCode.TIMEOUT_ERROR);
    }

    @Override
    public BaseResponse<User> getLoginUser(HttpServletRequest request) {
        return ResultUtils.error(ErrorCode.TIMEOUT_ERROR);
    }
}
