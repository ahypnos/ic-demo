package com.taobao.itemcenter.demo.spuSearchServiceClient;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.item.exception.IcException;
import com.taobao.item.search.spu.SpuSearchDO;
import com.taobao.item.search.spu.SpuSearchQuery;
import com.taobao.item.service.client.SpuSearchServiceClient;

public class SpuSearchServiceClientDemo {
	
	private SpuSearchServiceClient spuSearchServiceClient;

	public SpuSearchServiceClientDemo() {
 		String[] location = { "spuSearchServiceClient/spring-ic-hsf.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(
				location);
		spuSearchServiceClient = (SpuSearchServiceClient) context
				.getBean("spuSearchServiceClient");
	}
	
	public void search(SpuSearchQuery ssq){
		try {
			List<SpuSearchDO> results = this.spuSearchServiceClient.search(ssq);
			if(CollectionUtils.isNotEmpty(results)){
				System.out.println("查询的结果条数为："+results.size());
			}
			for(SpuSearchDO spu : results){
				System.out.println("查询结果为：");
				System.out.println(spu);
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SpuSearchServiceClientDemo sssc = new SpuSearchServiceClientDemo();
		SpuSearchQuery ssq = new SpuSearchQuery();
		//ssq.setCategoryId(1512);
		//ssq.setProperty("20000:10695");
		String kvPairs = "20000:10683";
		StringBuilder sb = new StringBuilder();
		for(String kv:kvPairs.split(",")){
			sb.append(kv.trim()).append(",");
		}
		String check = sb.toString();
		ssq.setProperties(check);
		ssq.setPageSize(10);
		ssq.setStatus(new int[]{0,1,2,3,-1});
		//ssq.setPrimarySort(SpuSortField.modify_date);
		sssc.search(ssq);
		System.exit(0);
	}

	public SpuSearchServiceClient getSpuSearchServiceClient() {
		return spuSearchServiceClient;
	}

	public void setSpuSearchServiceClient(
			SpuSearchServiceClient spuSearchServiceClient) {
		this.spuSearchServiceClient = spuSearchServiceClient;
	}
	
	
}
