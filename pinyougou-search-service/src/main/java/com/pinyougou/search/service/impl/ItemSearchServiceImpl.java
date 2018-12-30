package com.pinyougou.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service(timeout=5000)
public class ItemSearchServiceImpl implements ItemSearchService {
    @Autowired
    private SolrTemplate solrTemplate;
    @Override
    public Map<String, Object> search(Map searchMap) {

        Map map = new HashMap();
      /*  Query query = new SimpleQuery("*:*");*/
            //1.按关键字查询（高亮显示）
        if (searchMap.get("keywords") != null && !"".equals(searchMap.get("keywords"))){//添加条件查询
            map.putAll(searchList(searchMap));
            map.put("categoryList",searchCateoryList(searchMap));
        }/*else {
            Query query = new SimpleQuery("*:*");
            ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
            map.put("rows",page.getContent());
        }
*/
        //2.根据关键字查询商品分类


        return map;
    }
    /**
     * 查询高亮列表
     * @param searchMap
     * @return
     */
    private Map searchList(Map searchMap){
        Map map = new HashMap();
        /*  Query query = new SimpleQuery("*:*");*/
        HighlightQuery query = new SimpleHighlightQuery();
        //高亮选项
        HighlightOptions options = new HighlightOptions().addField("item_title");
        options.setSimplePrefix("<em style = 'color:red'>");//前缀
        options.setSimplePostfix("</em>");//后缀
        query.setHighlightOptions(options);

            Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
            query.addCriteria(criteria);


        HighlightPage<TbItem> page = solrTemplate.queryForHighlightPage(query, TbItem.class);
        List<HighlightEntry<TbItem>> entryList = page.getHighlighted();//高亮入口集合
        for (HighlightEntry<TbItem> h : entryList) {//循环高亮入口集合
            TbItem item = h.getEntity();//获取原实体类
            if (h.getHighlights().size()>0 && h.getHighlights().get(0).getSnipplets().size()>0 ){
                item.setTitle(h.getHighlights().get(0).getSnipplets().get(0));//设置高亮的结果
            }
        }
        map.put("rows",page.getContent());

                return map;
    }


    /**
     * 查询分类列表
     * @param searchMap
     * @return
     */

    private List searchCateoryList(Map searchMap){


        List list = new ArrayList();
        Query query = new SimpleQuery("*:*");
        //按照关键字查询
        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);
        //设置分组选项
        GroupOptions groupOptions = new GroupOptions().addGroupByField("item_category");//grounp by
        query.setGroupOptions(groupOptions);
        //获取分组页
        GroupPage<TbItem> page = solrTemplate.queryForGroupPage(query, TbItem.class);
        //根据列得到分组结果集
        GroupResult<TbItem> groupResult = page.getGroupResult("item_category");
        //得到分组结果入口页
        Page<GroupEntry<TbItem>> groupEntries = groupResult.getGroupEntries();
        //得到分组入口集合
        List<GroupEntry<TbItem>> content = groupEntries.getContent();
        for (GroupEntry<TbItem> entry : content) {
           list.add(entry.getGroupValue()) ;//将分组结果的名称封装到返回值中
        }
        return list;
    }
}
