package com.taobao.itemcenter.demo.itemarchivequeryserviceclient;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.item.exception.IcException;
import com.taobao.item.service.client.ItemArchiveQueryServiceClient;

public class ItemArchiveQueryServiceClientDemo {
	private ItemArchiveQueryServiceClient itemArchiveQueryServiceClient;

	public ItemArchiveQueryServiceClientDemo() {
		String[] location = { "itemArchiveQueryServiceClient/spring-ic-hsf.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(
				location);
		itemArchiveQueryServiceClient = (ItemArchiveQueryServiceClient) context
				.getBean("itemArchiveQueryServiceClient");
	}
	
	public void testQueryCardCodeByTradeId() throws IcException{
		long tradeId=12345L;
		System.out.println(this.itemArchiveQueryServiceClient.queryCardCodeByTrade(tradeId));
		
	}
}
