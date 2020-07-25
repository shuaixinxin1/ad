package com.itcanteen.sponsor.service;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.entity.AdUser;
import com.itcanteen.sponsor.vo.CreateAdUserRequest;
import com.itcanteen.sponsor.vo.CreateAdUserResponse;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 10:12
 */
public interface IUserService {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    AdUser findByUsername(String username);

    /**
     * 保存广告主
     */
    CreateAdUserResponse createUser(CreateAdUserRequest request) throws AdException;

}
