package com.shone.elasticsearchstudy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 查询Vo对象
 *
 * @author Xiao GuoJian
 * @date 2020/8/27 下午3:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryVo {

    /**
     * 索引名
     */
    private String idxName;
    /**
     * 需要反射的实体类型，用于对查询结果的封装
     */
    private String className;
    /**
     * 具体条件
     */
    private Map<String, Map<String,Object>> query;
}
