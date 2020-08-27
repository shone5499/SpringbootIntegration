package com.shone.elasticsearchstudy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 数据存储对象
 *
 * @author Xiao GuoJian
 * @date 2020/8/27 下午3:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticEntity {

    /**
     * 主键标识，用户ES持久化
     */
    private String id;

    /**
     * JSON对象，实际存储数据
     */
    private Map data;
}
