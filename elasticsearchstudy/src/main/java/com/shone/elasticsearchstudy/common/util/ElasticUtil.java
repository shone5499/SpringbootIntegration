package com.shone.elasticsearchstudy.common.util;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.concurrent.TimeUnit;

/**
 * ElasticSearch工具类
 *
 * @author Xiao GuoJian
 * @date 2020/8/27 下午3:37
 */
@Slf4j
public class ElasticUtil {

    private ElasticUtil(){}

    public static Class<?> getClazz(String clazzName){
        try {
            return Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * @param queryBuilder  设置查询对象
     * @param from  设置from选项，确定要开始搜索的结果索引。 默认为0。
     * @param size  设置大小选项，确定要返回的搜索匹配数。 默认为10。
     * @param timeout 超时时间
     * @return org.elasticsearch.search.builder.SearchSourceBuilder
     */
    public static SearchSourceBuilder initSearchSourceBuilder(QueryBuilder queryBuilder, int from, int size, int timeout){

        //使用默认选项创建 SearchSourceBuilder 。
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //设置查询对象。可以使任何类型的 QueryBuilder
        sourceBuilder.query(queryBuilder);
        //设置from选项，确定要开始搜索的结果索引。 默认为0。
        sourceBuilder.from(from);
        //设置大小选项，确定要返回的搜索匹配数。 默认为10。
        sourceBuilder.size(size);
        sourceBuilder.timeout(new TimeValue(timeout, TimeUnit.SECONDS));
        return sourceBuilder;
    }

    /**
     * @param queryBuilder
     * @return org.elasticsearch.search.builder.SearchSourceBuilder
     */
    public static SearchSourceBuilder initSearchSourceBuilder(QueryBuilder queryBuilder){
        return initSearchSourceBuilder(queryBuilder,0,10,60);
    }
}
