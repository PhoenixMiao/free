package com.phoenix.free.controller;

import com.phoenix.free.annotation.Auth;
import com.phoenix.free.service.RelationshipService;
import com.phoenix.free.util.SessionUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RequestMapping("/relationship")
@RestController
public class RelationshipController {
    @Autowired
    private RelationshipService relationshipService;

    @Autowired
    private SessionUtils sessionUtils;

    @Auth
    @GetMapping("/add/id={id}")
    @ApiOperation(value = "与指定id用户添加搭档关系",response = String.class)
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
    public Object addPartner(@NotBlank @PathVariable("id") Long id){
        Long userId = sessionUtils.getUserId();
        relationshipService.addNewRelationship(id, userId);

        return "搭档添加成功";
    }

    @Auth
    @GetMapping("/delete/id={id}")
    @ApiOperation(value = "与指定id用户解除搭档关系",response = String.class)
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
    public Object deletePartner(@NotBlank @PathVariable("id") Long id){
        Long userId = sessionUtils.getUserId();
        relationshipService.deleteRelationship(userId, id);

        return "搭档删除成功";
    }
}
