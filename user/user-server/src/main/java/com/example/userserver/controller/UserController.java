package com.example.userserver.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.exception.BusinessException;
import com.example.common.req.BaseResponse;
import com.example.common.req.ErrorCode;
import com.example.common.req.ResultUtils;
import com.example.userapi.domain.User;
import com.example.userapi.domain.request.UserLoginRequest;
import com.example.userapi.domain.request.UserRegisterRequest;
import com.example.userapi.enums.LoginTypeEnum;
import com.example.userserver.mapper.UserMapper;
import com.example.userserver.service.impl.UserServiceImpl;
import com.example.userserver.utils.SaTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 用户接口
 *
 * @author yupi
 */
@RestController
@Api("test1")
public class UserController {

    @Resource
    private UserServiceImpl userService;

    @Resource
    private UserMapper userMapper;


    @ApiOperation("用户注册")
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }

        return userService.userRegister(userAccount, userPassword);
    }

    /**
     * 查询登录状态
     * @return
     */
    @GetMapping("/isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    /**
     * 用户账号密码登录 返回的是token
     * @param userLoginRequest
     * @param request
     * @return
     */
    @ApiOperation("用户账号密码登录")
    @PostMapping("/login")
    public BaseResponse<String> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        return ResultUtils.success(userService.userLogin(userAccount,userPassword).getData());
    }

    /**
     * 查询 Token 信息
     * @return
     */
    @ApiOperation("获取token信息")
    @GetMapping("/tokenInfo")
    public BaseResponse<SaTokenInfo> tokenInfo() {
        return ResultUtils.success(StpUtil.getTokenInfo());
    }



    @ApiOperation("注销")
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout() {
        StpUtil.logout();
        return ResultUtils.success(1);
    }


    @ApiOperation("获取当前用户")
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request).getData();
        long userId = loginUser.getId();
        User user = userMapper.selectById(userId);

        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }


    @ApiOperation("查找用户")
    @GetMapping("/search")
    public BaseResponse<List<User>> searchUser(String username, HttpServletRequest request) {
        userService.assertAdmin(request);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userMapper.selectList(queryWrapper);
        List<User> list = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(list);
    }


    @ApiOperation("删除用户")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
        userService.assertAdmin(request);
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = userMapper.deleteById(id)==1;
        return ResultUtils.success(b);
    }

}
