package com.dool.controller;

import com.dool.biz.dao.entity.BdInvite;
import com.dool.biz.service.BdInviteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zenglt
 * @date 2020/4/17 16:30
 */
@RestController
@RequestMapping("/invite")
public class BdInviteController {

    @Resource
    private BdInviteService bdInviteService;

    @GetMapping("/list")
    public List<BdInvite> list() {

        return bdInviteService.list();
    }
}