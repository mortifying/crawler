package com.jointsky.crawler.controller.login;

import com.jointsky.crawler.quartz.common.utils.DefaultServiceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * Author  zhangxiong
 * Date    17-7-28 下午2:48
 */
@RestController
@RequestMapping(value = "/api")
@Api(description = "登录退出接口")
public class LoginController {


    @ApiOperation(value = "登录接口")
    @PostMapping("/login")
    @ResponseBody
    public DefaultServiceResult login(@RequestParam(value = "username") @NotNull(message = "请输入用户名") String username,
                                      @RequestParam(value = "password") @NotNull(message = "请输入密码") String password) {
        if ("admin".equals(username) && "admin".equals(password)) {
            return new DefaultServiceResult(true);
        }
        return new DefaultServiceResult(false);
    }


    @ApiOperation(value = "退出接口")
    @GetMapping("/logout")
    @ResponseBody
    public DefaultServiceResult logout() {
        return new DefaultServiceResult(true);
    }

}
