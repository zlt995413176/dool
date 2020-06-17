package com.dool.biz.service.impl;

import com.dool.biz.dao.entity.BdInvite;
import com.dool.biz.dao.mapper.BdInviteMapper;
import com.dool.biz.service.BdInviteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zenglt
 * @date 2020/4/17 16:25
 */
@Service
@Slf4j
public class BdInviteServiceImpl implements BdInviteService {

    @Resource
    private BdInviteMapper bdInviteMapper;

    @Override
    public List<BdInvite> list() {
       return bdInviteMapper.selectAll();
    }
}