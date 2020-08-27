package com.shone.elasticsearchstudy.vo;

import com.shone.elasticsearchstudy.entity.ElasticEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * http交互Vo对象
 *
 * @author Xiao GuoJian
 * @date 2020/8/27 下午3:33
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElasticDataVo<T> {

    /**
     * 索引名
     */
    private String idxName;
    /**
     * 数据存储对象
     */
    private ElasticEntity elasticEntity;
}
