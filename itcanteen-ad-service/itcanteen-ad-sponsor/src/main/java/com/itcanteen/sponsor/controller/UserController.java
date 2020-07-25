package com.itcanteen.sponsor.controller;

import com.alibaba.fastjson.JSON;
import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.service.IUserService;
import com.itcanteen.sponsor.vo.CreateAdUserRequest;
import com.itcanteen.sponsor.vo.CreateAdUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 11:02
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    final
    IUserService iUserService;

    /**
     * 构造器注入
     * @param iUserService
     */
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    //@RequestMapping(value = "/save",method = RequestMethod.POST)
    @PostMapping("/save")
    public CreateAdUserResponse save( @RequestBody CreateAdUserRequest request) throws AdException {
        log.info("itcanteen-ad-sponsor:save->{}",JSON.toJSONString(request));
        return iUserService.createUser(request);
    }
}
