package com.example.userserver.utils;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;

import com.example.userapi.constant.JwtConstants;
import com.example.userapi.domain.User;
import com.example.userapi.enums.LoginTypeEnum;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;


public class SaTokenUtil {

    public static Long getLoginId(){
        return Long.parseLong(StpUtil.getLoginId().toString());
    }

    public static String loginToSaTokenByJwtToken(User user, LoginTypeEnum loginTypeEnum){

        SaLoginModel saLoginModel = new SaLoginModel()
                .setExtra(JwtConstants.JWT_PHONE, user.getPhone());


        if(StringUtils.isNotEmpty(user.getUsername())){
            saLoginModel.setExtra(JwtConstants.JWT_USERNAME, user.getUsername());
        }

        if(StringUtils.isNotEmpty(user.getUserAccount())){
            saLoginModel.setExtra(JwtConstants.JWT_USER_ACCOUNT, user.getUserAccount());
        }

        StpUtil.login(user.getId(), saLoginModel);

        System.out.println(loginTypeEnum.getText() + "jwt Token:" + StpUtil.getTokenValue());
        return StpUtil.getTokenValue();
    }


}
