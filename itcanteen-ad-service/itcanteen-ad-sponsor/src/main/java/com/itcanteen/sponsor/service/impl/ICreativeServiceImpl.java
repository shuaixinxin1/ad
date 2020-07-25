package com.itcanteen.sponsor.service.impl;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.constant.Constant;
import com.itcanteen.sponsor.dao.CreativeRepository;
import com.itcanteen.sponsor.entity.Creative;
import com.itcanteen.sponsor.service.ICreativeService;
import com.itcanteen.sponsor.vo.CreaticeRequest;
import com.itcanteen.sponsor.vo.CreativeResponse;
import com.netflix.discovery.provider.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 22:27
 */
@Service
@Transactional
public class ICreativeServiceImpl implements ICreativeService {

    final
    CreativeRepository creativeRepository;

    public ICreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }

    @Override
    public CreativeResponse create(CreaticeRequest request) throws AdException {
        if(!request.createValidate()) {
            throw new AdException(Constant.ErrorMessage.REQUEST_PARAM_ERROR);
        }
        Creative creative = creativeRepository.save(request.toEntity());

        return new CreativeResponse(creative.getId(),creative.getName());
    }
}
