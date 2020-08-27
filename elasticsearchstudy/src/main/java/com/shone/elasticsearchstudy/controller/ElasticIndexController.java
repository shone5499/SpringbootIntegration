package com.shone.elasticsearchstudy.controller;

import com.alibaba.fastjson.JSON;
import com.shone.elasticsearchstudy.common.response.MyResponse;
import com.shone.elasticsearchstudy.service.BaseElasticService;
import com.shone.elasticsearchstudy.vo.IdxVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ElasticSearch索引的基本管理，提供对外查询、删除和新增功能
 *
 * @author Xiao GuoJian
 * @date 2020/8/27 下午3:42
 */

@Slf4j
@RequestMapping("/elastic")
@RestController
public class ElasticIndexController {

    @Autowired
    private BaseElasticService baseElasticService;

    @GetMapping(value = "/")
    public MyResponse index(String index){
        return new MyResponse();
    }

    /**
     * 创建Elastic索引
     * @param idxVo 索引对象
     */
    @PostMapping(value = "/createIndex")
    public MyResponse createIndex(@RequestBody IdxVo idxVo){
        MyResponse response = new MyResponse();
        try {
            //索引不存在，再创建，否则不允许创建
            if(!baseElasticService.isExistsIndex(idxVo.getIdxName())){
                String idxSql = JSON.toJSONString(idxVo.getIdxSql());
                log.warn(" idxName={}, idxSql={}",idxVo.getIdxName(),idxSql);
                baseElasticService.createIndex(idxVo.getIdxName(),idxSql);
            } else{
                response.setCode(500);
                response.setMsg("索引已经存在，不允许创建");
            }
        } catch (Exception e) {
            response.setCode(500);
            response.setMsg("系统异常");
        }
        return response;
    }


    /**
     * 判断索引是否存在；存在-TRUE，否则-FALSE
     * @param index 所以
     */
    @GetMapping(value = "/exist/{index}")
    public MyResponse indexExist(@PathVariable(value = "index") String index){

        MyResponse response = new MyResponse();
        try {
            if(!baseElasticService.isExistsIndex(index)){
                log.error("index={},不存在",index);
                response.setCode(500);
                response.setMsg("Index不存在");
            } else {
                response.setMsg(" 索引已经存在, " + index);
            }
        } catch (Exception e) {
            response.setCode(500);
            response.setMsg(" 调用ElasticSearch 失败！");
        }
        return response;
    }

    @GetMapping(value = "/del/{index}")
    public MyResponse indexDel(@PathVariable(value = "index") String index){
        MyResponse response = new MyResponse();
        try {
            baseElasticService.deleteIndex(index);
        } catch (Exception e) {
            response.setCode(500);
            response.setMsg(" 调用ElasticSearch 失败！");
        }
        return response;
    }
}
