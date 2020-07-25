package com.itcanteen.sponsor.controller;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.service.ICreativeService;
import com.itcanteen.sponsor.vo.CreateAdUserResponse;
import com.itcanteen.sponsor.vo.CreaticeRequest;
import com.itcanteen.sponsor.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 22:46
 */
@RestController
@RequestMapping("/creative")
public class CreaticeController {
    @Autowired
    ICreativeService creativeService;

    @PostMapping("/create")
    public CreativeResponse create(@RequestBody CreaticeRequest request) throws AdException {
        return  creativeService.create(request);
    }
}
