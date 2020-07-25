package com.itcanteen.sponsor.dao;

import com.itcanteen.sponsor.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 20:30
 */
public interface AdUnitRepository extends JpaRepository<AdUnit,Long> {

    AdUnit findByPlanIdAndUnitName(Long planId,String unitName);

    List<AdUnit> findAllByUnitStatus(Integer unitStatus);
}
