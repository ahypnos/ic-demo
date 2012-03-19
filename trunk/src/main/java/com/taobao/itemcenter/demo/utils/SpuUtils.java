package com.taobao.itemcenter.demo.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.item.domain.spu.SpuDO;
import com.taobao.item.exception.IcException;
import com.taobao.item.service.client.SpuServiceClient;

/**
 * Spuπ§æﬂ¿‡
 * @author tieyi.qlr
 *
 */
public class SpuUtils {
	
	private static SpuServiceClient spuServiceClient;
	
	static{
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spuServiceClient/spring-ic-hsf.xml");
		spuServiceClient = (SpuServiceClient) ac
				.getBean("spuServiceClient");
	}
	
	public static SpuDO getSpu(long spuId){
		try {
			return spuServiceClient.getSpu(spuId).getModule();
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
