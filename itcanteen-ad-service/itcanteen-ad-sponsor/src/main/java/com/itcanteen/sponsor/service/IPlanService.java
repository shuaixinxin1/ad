package com.itcanteen.sponsor.service;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.entity.AdPlan;
import com.itcanteen.sponsor.vo.AdPlanGetRequest;
import com.itcanteen.sponsor.vo.AdPlanRequest;
import com.itcanteen.sponsor.vo.AdPlanResponse;

import java.util.List;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 16:11
 */
public interface IPlanService {
    /**
     * 创建推广计划
     */

    public AdPlanResponse createPlan(AdPlanRequest request) throws AdException;

    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    public AdPlanResponse updatePlan(AdPlanRequest request) throws AdException;

    public void del(AdPlanRequest request) throws AdException;
}
