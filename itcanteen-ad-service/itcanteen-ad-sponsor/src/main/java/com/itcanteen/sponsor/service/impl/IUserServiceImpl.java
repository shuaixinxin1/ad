package com.itcanteen.sponsor.service.impl;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.constant.Constant;
import com.itcanteen.sponsor.dao.AdUserRepository;
import com.itcanteen.sponsor.entity.AdUser;
import com.itcanteen.sponsor.service.IUserService;
import com.itcanteen.sponsor.utils.CommonUtils;
import com.itcanteen.sponsor.vo.CreateAdUserRequest;
import com.itcanteen.sponsor.vo.CreateAdUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 10:23
 */
@Service
@Transactional
public class IUserServiceImpl implements IUserService {
    @Autowired
    private AdUserRepository adUserRepository;
    @Override
    public AdUser findByUsername(String username) {
        return adUserRepository.findByUsername(username);
    }

    @Override
    public CreateAdUserResponse createUser(CreateAdUserRequest request) throws AdException {
        //CreateAdUserRequest 里面的字段是否为空 通过 Stringutils 通过引入的 commons-codec 工具类
        if(!request.validate()){
           throw new AdException(Constant.ErrorMessage.REQUEST_PARAM_ERROR);
        }
        AdUser user = adUserRepository.findByUsername(request.getUsername());

        if(null!=user){
            throw new AdException(Constant.ErrorMessage.SAME_USER_ERROR);
        }
        AdUser adUser = adUserRepository.save(new AdUser(
                request.getUsername(), CommonUtils.md5(request.getUsername())
        ));
        return new CreateAdUserResponse(adUser.getId(),adUser.getUsername(),
                adUser.getToken(),adUser.getCreateTime(),adUser.getUpdateTime());

    }
}
