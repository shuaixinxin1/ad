package com.itcanteen.sponsor.dao;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.entity.AdPlan;
import com.itcanteen.sponsor.vo.AdPlanRequest;
import com.itcanteen.sponsor.vo.AdPlanResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Class 类的描述
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 15:57
 */
public interface AdplanRepository extends JpaRepository<AdPlan,Long> {

    AdPlan findByIdAndUserId(Long id,Long userId);

    AdPlan findByUserIdAndPlanName(Long userId,String planName);


    List<AdPlan> findAllByPlanStatus(Integer status);

    List<AdPlan> findAllByIdInAndUserId(List<Long> ids,Long userId);


}
