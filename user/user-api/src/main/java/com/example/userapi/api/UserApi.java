package com.example.userapi.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.req.BaseResponse;
import com.example.userapi.domain.User;
import com.example.userapi.fallback.UserApiFallback;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.cloud.openfeign.FeignClient;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author yupi
 */
@FeignClient(name = "${feign.userapi.name}",
        path = "/bizProcessRecord", fallback = UserApiFallback.class)
public interface UserApi {

    /**
     * 用户注册
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    BaseResponse<Long> userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    BaseResponse<User> userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     *
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    BaseResponse<Integer> userLogout(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    BaseResponse<Boolean> isAdmin(HttpServletRequest request);

    void assertAdmin(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param user
     * @return
     */
    BaseResponse<Boolean> isAdmin(User user);


    /**
     * 获取登录用户（查缓存）
     *
     * @param request
     * @return
     * @throws com.example.common.exception.BusinessException 未登录则抛异常
     */
    BaseResponse<User> getLoginUser(HttpServletRequest request);
}
