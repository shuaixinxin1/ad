package com.itcanteen.sponsor.controller;

import com.alibaba.fastjson.JSON;
import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.service.IUnitService;
import com.itcanteen.sponsor.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 20:58
 */
@RestController

@RequestMapping("/unit")
@Slf4j
public class UnitController {

   private final IUnitService iUnitService;

    public UnitController(IUnitService iUnitService) {
        this.iUnitService = iUnitService;
    }

    @PostMapping("/createUnit")
    public AdUnitResponse createUnit(@RequestBody AdUnitRequest request) throws AdException {
        log.info("itcanteen-ad-sponsor-UnitController:createUnit->{}", JSON.toJSONString(request));
       return iUnitService.createAdUnit(request);
    }

    @PostMapping("/createUnitKeyword")
    public AdUnitkeyWordResponse createUnitKeyword(@RequestBody  AdUnitkeyWordRequest request) throws AdException {
        log.info("itcanteen-ad-sponsor-UnitController:createUnitKeyword->{}", JSON.toJSONString(request));
       return iUnitService.createUnitKeyWords(request);
    }
    @PostMapping("/createUnitIt")
    public AdUnitItResponse createUnitIt(@RequestBody AdUnitItRequest request)throws AdException{
       log.info("itcanteen-ad-sponsor:createUnitIt->{}",JSON.toJSONString(request));
       return iUnitService.createUnitIt(request);
    }
    @PostMapping("/createUnitDistrict")
    public AdUnitDistrictResponse createUnitDistrict(
            @RequestBody AdUnitDistrictResquest request) throws AdException{
        log.info("itcanteen-ad-sponsor:createUnitDistrict->{}",JSON.toJSONString(request));
        return iUnitService.createUnitDistrict(request);
    }

    @PostMapping("/creativeUnit")
    public CreativeUnitResponse creativeUnit(@RequestBody CreativeUnitRequest request)
            throws AdException{
        log.info("itcanteen-ad-sponsor:creativeUnit->{}",JSON.toJSONString(request));
        return iUnitService.creativeUnit(request);
    }
}
