package com.itcanteen.sponsor.dao;

import com.itcanteen.sponsor.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 10:07
 */
public interface AdUserRepository extends JpaRepository<AdUser,Long> {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    AdUser findByUsername(String username);


}
