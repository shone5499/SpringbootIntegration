package com.shone.elasticsearchstudy.controller;

import com.shone.elasticsearchstudy.common.response.MyResponse;
import com.shone.elasticsearchstudy.common.util.ElasticUtil;
import com.shone.elasticsearchstudy.entity.ElasticEntity;
import com.shone.elasticsearchstudy.service.BaseElasticService;
import com.shone.elasticsearchstudy.vo.ElasticDataVo;
import com.shone.elasticsearchstudy.vo.QueryVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 数据管理
 *
 * @author Xiao GuoJian
 * @date 2020/8/27 下午3:34
 */
@Slf4j
@RequestMapping("/elasticMgr")
@RestController
public class ElasticMgrController {

    @Autowired
    private BaseElasticService baseElasticService;

    /**
     * 新增数据
     * @param elasticDataVo
     */
    @PostMapping(value = "/add")
    public MyResponse add(@RequestBody ElasticDataVo elasticDataVo){
        MyResponse response = getMyResponse();
        try {
            if(!StringUtils.isNotEmpty(elasticDataVo.getIdxName())){
                response.setCode(500);
                response.setMsg("索引为空，不允许提交");
                log.warn("索引为空");
                return response;
            }
            ElasticEntity elasticEntity = new ElasticEntity();
            elasticEntity.setId(elasticDataVo.getElasticEntity().getId());
            elasticEntity.setData(elasticDataVo.getElasticEntity().getData());

            baseElasticService.insertOrUpdateOne(elasticDataVo.getIdxName(), elasticEntity);

        } catch (Exception e) {
            response.setCode(500);
            response.setMsg("插入数据异常");
            log.error("插入数据异常，metadataVo={},异常信息={}", elasticDataVo.toString(),e.getMessage());
        }
        return response;
    }


    /**
     * 删除
     * @param elasticDataVo
     */
    @PostMapping(value = "/delete")
    public MyResponse delete(@RequestBody ElasticDataVo elasticDataVo){
        MyResponse response = getMyResponse();
        try {
            if(!StringUtils.isNotEmpty(elasticDataVo.getIdxName())){
                response.setCode(500);
                response.setMsg("索引为空，不允许提交");
                log.warn("索引为空");
                return response;
            }
            baseElasticService.deleteOne(elasticDataVo.getIdxName(),elasticDataVo.getElasticEntity());
        } catch (Exception e) {
            log.error("删除数据失败");
        }
        return response;

    }

    /**
     * @param queryVo 查询实体对象
     */
    @PostMapping(value = "/get")
    public MyResponse get(@RequestBody QueryVo queryVo){

        MyResponse response = getMyResponse();

        if(!StringUtils.isNotEmpty(queryVo.getIdxName())){
            response.setCode(500);
            response.setMsg("索引为空，不允许提交");
            log.warn("索引为空");
            return response;
        }

        try {
            Class<?> clazz = ElasticUtil.getClazz(queryVo.getClassName());
            Map<String,Object> params = queryVo.getQuery().get("match");
            Set<String> keys = params.keySet();
            MatchQueryBuilder queryBuilders=null;
            for(String ke:keys){
            queryBuilders = QueryBuilders.matchQuery(ke, params.get(ke));
        }
            if(null!=queryBuilders){
                SearchSourceBuilder searchSourceBuilder = ElasticUtil.initSearchSourceBuilder(queryBuilders);
                List<?> data = baseElasticService.search(queryVo.getIdxName(),searchSourceBuilder,clazz);
                response.setData(data);
            }
        } catch (Exception e) {
            response.setCode(500);
            response.setMsg("服务忙，请稍后再试");
            log.error("查询数据异常，metadataVo={},异常信息={}", queryVo.toString(),e.getMessage());
        }
        return response;
    }

    public MyResponse getMyResponse(){
        return new MyResponse();
    }
}
