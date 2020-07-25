package com.itcanteen.sponsor.controller;

import com.alibaba.fastjson.JSON;
import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.dao.AdplanRepository;
import com.itcanteen.sponsor.entity.AdPlan;
import com.itcanteen.sponsor.service.IPlanService;
import com.itcanteen.sponsor.vo.AdPlanGetRequest;
import com.itcanteen.sponsor.vo.AdPlanRequest;
import com.itcanteen.sponsor.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 16:57
 */
@Slf4j
@RestController
@RequestMapping("/plan")
public class PlanController {

    final
    IPlanService planService;

    public PlanController(IPlanService planService) {
        this.planService = planService;
    }

    @PostMapping("/createPlan")
    public AdPlanResponse createPlan(@RequestBody AdPlanRequest request) throws AdException {
       log.info("itcanteen-ad-sponsor-PlanController:createPlan->{}", JSON.toJSONString(request));
       return planService.createPlan(request);
    }
    @PostMapping("/getAdPlanByIds")
    public List<AdPlan> getAdPlanByIds(@RequestBody AdPlanGetRequest request) throws AdException {
        log.info("itcanteen-ad-sponsor-PlanController:getAdPlanByIds->{}", JSON.toJSONString(request));

        return   planService.getAdPlanByIds(request);
    }

    @PutMapping("/updatePlan")
    public AdPlanResponse updatePlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info("itcanteen-ad-sponsor-PlanController:updatePlan->{}", JSON.toJSONString(request));
        return planService.updatePlan(request);
    }

    @PostMapping("/delPlan")
    public void  delPlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info("itcanteen-ad-sponsor-PlanController:delPlan->{}", JSON.toJSONString(request));
         planService.del(request);
    }

}
