package com.itcanteen.sponsor.service.impl;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.constant.CommonStatus;
import com.itcanteen.sponsor.constant.Constant;
import com.itcanteen.sponsor.dao.AdUserRepository;
import com.itcanteen.sponsor.dao.AdplanRepository;
import com.itcanteen.sponsor.entity.AdPlan;
import com.itcanteen.sponsor.entity.AdUser;
import com.itcanteen.sponsor.service.IPlanService;
import com.itcanteen.sponsor.utils.CommonUtils;
import com.itcanteen.sponsor.vo.AdPlanGetRequest;
import com.itcanteen.sponsor.vo.AdPlanRequest;
import com.itcanteen.sponsor.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 16:19
 */
@Service
public class IPlanServiceImpl implements IPlanService {

    private final AdplanRepository adplanRepository;

    private final AdUserRepository adUserRepository;
    
    public IPlanServiceImpl(AdplanRepository adplanRepository, AdUserRepository adUserRepository) {
        this.adplanRepository = adplanRepository;
        this.adUserRepository = adUserRepository;
    }

    @Override
    public AdPlanResponse createPlan(AdPlanRequest request) throws AdException {
        if(!request.validate()){
            throw new AdException(Constant.ErrorMessage.REQUEST_PARAM_ERROR);
        }
        //jdk1.8新特性  判断关联的广告主是否存在
        Optional<AdUser> adUser = adUserRepository.findById(request.getUserId());
        if(!adUser.isPresent()){
            throw new AdException(Constant.ErrorMessage.CAN_NOT_FIND_USER);
        }
        //判断该广告主下是否已存在该广告的推广
        AdPlan adPlan = adplanRepository.
                findByUserIdAndPlanName(request.getUserId(), request.getPlanName());
        if(null!=adPlan){
            throw new AdException(Constant.ErrorMessage.SAME_PLAN_ERROR);
        }
        AdPlan adplan = adplanRepository.save(
                new AdPlan(
                        request.getUserId(),
                        request.getPlanName(),
                        CommonUtils.parseStringToDate(request.getStartDate()),
                        CommonUtils.parseStringToDate(request.getEndDate())
                )
        );
        return new AdPlanResponse(
                adplan.getId(),adplan.getPlanName()
        );
    }

    /**
     * 根据广告主id和推广计划的id集合查询
     * @param request
     * @return
     */
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {
        if(!request.validate()){
            throw new AdException(Constant.ErrorMessage.REQUEST_PARAM_ERROR);
        }
        Optional<AdUser> byId = adUserRepository.findById(request.userId);
        if(!byId.isPresent()){
            throw new AdException(Constant.ErrorMessage.CAN_NOT_FIND_USER);
        }

        return adplanRepository.findAllByIdInAndUserId(
             request.getIds(),request.getUserId()
        );
    }
    public AdPlanResponse updatePlan(AdPlanRequest request) throws AdException {
        if(!request.updateValidate()){
            throw new AdException(Constant.ErrorMessage.REQUEST_PARAM_ERROR);
        }
        AdPlan adPlan = adplanRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if(null == adPlan){
            throw new AdException(Constant.ErrorMessage.CAN_NOT_FIND_PLAN);
        }
        adPlan.setPlanName(request.getPlanName());
        adPlan.setUpdateTime(new Date());
        adPlan.setEndDate(CommonUtils.parseStringToDate(request.getEndDate()));
         adPlan = adplanRepository.saveAndFlush(adPlan);
        return new AdPlanResponse(adPlan.getId(),adPlan.getPlanName());

    }

    public void del(AdPlanRequest request) throws AdException {
        if(!request.delValidate()){
            throw new AdException(Constant.ErrorMessage.REQUEST_PARAM_ERROR);
        }
        AdPlan adPlan = adplanRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if(null == adPlan){
            throw new AdException(Constant.ErrorMessage.CAN_NOT_FIND_PLAN);
        }
        adPlan.setPlanStatus(CommonStatus.INVALID.getStatus());
        adPlan.setUpdateTime(new Date());
         adplanRepository.save(adPlan);
    }

}
