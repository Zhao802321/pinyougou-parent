package com.pinyougou.search.service.impl;

import com.pinyougou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.Arrays;

@Component
public class ItemDeleteListener implements MessageListener{
    @Autowired
    private ItemSearchService itemSearchService;
    @Override
    public void onMessage(Message message) {


        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            Long[] goodIds = (Long[]) objectMessage.getObject();
            System.out.println("11111111111111");
            itemSearchService.deleteByGoodsIds(Arrays.asList(goodIds));
            System.out.println("2222222222222222222");
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
