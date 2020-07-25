package com.itcanteen.sponsor.service.impl;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.constant.Constant;
import com.itcanteen.sponsor.dao.AdUnitRepository;
import com.itcanteen.sponsor.dao.AdplanRepository;
import com.itcanteen.sponsor.dao.CreativeRepository;
import com.itcanteen.sponsor.dao.unit_condition.AdUnitDistrictRepository;
import com.itcanteen.sponsor.dao.unit_condition.AdUnitItRepository;
import com.itcanteen.sponsor.dao.unit_condition.AdUnitkeyWordRepository;
import com.itcanteen.sponsor.dao.unit_condition.CreativeUnitRepository;
import com.itcanteen.sponsor.entity.AdPlan;
import com.itcanteen.sponsor.entity.AdUnit;
import com.itcanteen.sponsor.entity.unit_condition.AdUnitDistrict;
import com.itcanteen.sponsor.entity.unit_condition.AdUnitIt;
import com.itcanteen.sponsor.entity.unit_condition.AdUnitkeyWord;
import com.itcanteen.sponsor.entity.unit_condition.CreativeUnit;
import com.itcanteen.sponsor.service.IUnitService;
import com.itcanteen.sponsor.vo.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 20:44
 */
@Service
public class UnitServiceImpl implements IUnitService {

    private final AdplanRepository adplanRepository;
    private final AdUnitRepository adUnitRepository;
    private final AdUnitkeyWordRepository adUnitkeyWordRepository;
    private final AdUnitItRepository adUnitItRepository;
    private final AdUnitDistrictRepository  adUnitDistrictRepository;
    private final CreativeRepository creativeRepository;
    private final CreativeUnitRepository creativeUnitRepository;
    public UnitServiceImpl(AdplanRepository adplanRepository, AdUnitRepository adUnitRepository, AdUnitkeyWordRepository adUnitkeyWordRepository, AdUnitItRepository adUnitItRepository, AdUnitDistrictRepository adUnitDistrictRepository, CreativeRepository creativeRepository, CreativeUnitRepository creativeUnitRepository) {
        this.adplanRepository = adplanRepository;
        this.adUnitRepository = adUnitRepository;
        this.adUnitkeyWordRepository = adUnitkeyWordRepository;
        this.adUnitItRepository = adUnitItRepository;
        this.adUnitDistrictRepository = adUnitDistrictRepository;
        this.creativeRepository = creativeRepository;
        this.creativeUnitRepository = creativeUnitRepository;
    }

    @Override
    public AdUnitResponse createAdUnit(AdUnitRequest request) throws AdException {
        if(!request.createValidate()){
            throw new AdException(Constant.ErrorMessage.REQUEST_PARAM_ERROR);
        }
        Optional<AdPlan> byId = adplanRepository.findById(request.getPlanId());
        if(!byId.isPresent()){
            throw new AdException(Constant.ErrorMessage.CAN_NOT_FIND_PLAN);
        }
        AdUnit byPlanIdAndUnitName =
                adUnitRepository.findByPlanIdAndUnitName(request.getPlanId(), request.getUnitName());
        if(null !=byPlanIdAndUnitName){
            throw  new AdException(Constant.ErrorMessage.SAME_PLAN_ERROR);
        }
        AdUnit adUnit = adUnitRepository.save(
                new AdUnit(
                        request.getPlanId(),
                        request.getUnitName(),
                        request.getPositionType(),
                        request.getBudget()
                )
        );
        return new AdUnitResponse(
                adUnit.getId(),
                adUnit.getUnitName()
        );
    }

    @Override
    public AdUnitkeyWordResponse createUnitKeyWords(AdUnitkeyWordRequest request) throws AdException {

        if(!request.validate()){
            throw  new AdException(Constant.ErrorMessage.REQUEST_PARAM_ERROR);
        }
        List<Long> unitId = request.getUnitKeyWordList().stream().map(
                AdUnitkeyWordRequest.UnitKeyWord::getUnitId
        ).collect(Collectors.toList());

        //去重复list 进行减少数据库的查询
        List<Long> distinctUnitId = unitId.stream().distinct().collect(Collectors.toList());
        distinctUnitId.forEach(i->{
            if(!adUnitRepository.findById(i).isPresent()){
                //    throw new AdException(Constant.ErrorMessage.CAN_NOT_FIND_USER);
                try {
                    throw new AdException(Constant.ErrorMessage.CAN_NOT_FIND_USER);
                } catch (AdException e) {
                    e.printStackTrace();


                }
            }
        });

        ArrayList<AdUnitkeyWord> adUnitkeyWords = new ArrayList<>();
        request.getUnitKeyWordList().forEach(i->{


            adUnitkeyWords.add(new AdUnitkeyWord(
                    i.getUnitId(),i.getKeyWord()
            ));
        });
        List<Long> ids = adUnitkeyWordRepository.saveAll(adUnitkeyWords).stream().map(AdUnitkeyWord::getId).collect(Collectors.toList());
        return new AdUnitkeyWordResponse(ids);
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {
        if(!request.validate()){
            throw new AdException(Constant.ErrorMessage.REQUEST_PARAM_ERROR);
        }
        List<Long> unitIds = request.getUnitItList().stream().map(
                AdUnitItRequest.UnitIt::getUnitId
        ).collect(Collectors.toList());

        //去数据库验证

        ArrayList<AdUnitIt> adUnitIts = new ArrayList<>();

        request.getUnitItList().forEach(i->{
            adUnitIts.add(
                    new AdUnitIt(
                            i.getUnitId(),
                            i.getItTag()
                    )
            );
        });
        List<Long> ids = adUnitItRepository.saveAll(adUnitIts).stream().map(
                AdUnitIt::getId
        ).collect(Collectors.toList());
        return new AdUnitItResponse(ids);
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictResquest resquest) throws AdException {
        if(!resquest.validate()){
            throw new AdException(Constant.ErrorMessage.REQUEST_PARAM_ERROR);
        }
        List<Long> unitIds = resquest.getUnitDistrictList().stream().distinct().map(
                AdUnitDistrictResquest.UnitDistrict::getUnitId
        ).collect(Collectors.toList());
        for (Long unitId : unitIds) {
            if(! adUnitRepository.findById(unitId).isPresent()){
                throw new AdException(Constant.ErrorMessage.CAN_NOT_FIND_USER);
            }
        }
        ArrayList<AdUnitDistrict> adUnitDistricts = new ArrayList<>();
        resquest.getUnitDistrictList().forEach(
               i->{
                   adUnitDistricts.add(new AdUnitDistrict(i.getUnitId(),i.getProvince(),i.getCity()));
               }
        );
        List<Long> ids = adUnitDistrictRepository.saveAll(adUnitDistricts).stream().map(
                AdUnitDistrict::getId
        ).collect(Collectors.toList());
        return new AdUnitDistrictResponse(ids);
    }
    public CreativeUnitResponse creativeUnit(CreativeUnitRequest request)throws AdException{
        if(!request.validate()){
            throw new AdException(Constant.ErrorMessage.REQUEST_PARAM_ERROR);

        }
        List<Long> unitIds = request.getCreativeUnitItemList().stream().map(
                CreativeUnitRequest.CreativeUnitItem::getUnitId
        ).collect(Collectors.toList());
        List<Long> creativeIds = request.getCreativeUnitItemList().stream().map(
                CreativeUnitRequest.CreativeUnitItem::getCreativeId
        ).collect(Collectors.toList());
        for (Long unitId : unitIds) { 
            if(!adUnitRepository.findById(unitId).isPresent()){
                throw new AdException(Constant.ErrorMessage.CAN_NOT_FIND_USER); 
            }
        }
        for (Long creativeId : creativeIds) {
            if(!creativeRepository.findById(creativeId).isPresent()){
                throw new AdException(Constant.ErrorMessage.CAN_NOT_FIND_USER);
            }
        }
        ArrayList<CreativeUnit> creativeUnits = new ArrayList<>();
        request.getCreativeUnitItemList().forEach(
                i->{
                    creativeUnits.add(new CreativeUnit(
                        i.getUnitId(),i.getCreativeId()
                    ));
                }
        );
        List<Long> ids = creativeUnitRepository.saveAll(creativeUnits).stream().map(
                CreativeUnit::getId
        ).collect(Collectors.toList());
        return new CreativeUnitResponse(ids);

    }

}
