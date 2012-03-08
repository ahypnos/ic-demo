package com.taobao.itemcenter.demo.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.item.constant.AppInfoConstants;
import com.taobao.item.domain.AppInfoDO;
import com.taobao.item.domain.ItemDO;
import com.taobao.item.domain.query.QueryItemOptionsDO;
import com.taobao.item.domain.result.ItemResultDO;
import com.taobao.item.exception.IcException;
import com.taobao.item.service.client.ItemQueryServiceClient;

/**
 * 商品工具类，可以用于获取宝贝信息
 * @author tieyi.qlr
 *
 */
public class ItemUtils {
	
	private static ItemQueryServiceClient itemQueryServiceClient;
	
	static{
		ApplicationContext ac = new ClassPathXmlApplicationContext("itemQueryServiceClient/spring-ic-hsf.xml");
		itemQueryServiceClient = (ItemQueryServiceClient)ac.getBean("itemQueryServiceClient");
	}
	
	/**
	 * 获取宝贝的简单对象
	 * @param itemId
	 * @return
	 */
	public static ItemDO getSimpleItem(long itemId){
		QueryItemOptionsDO options = new QueryItemOptionsDO();
		AppInfoDO app = new AppInfoDO(AppInfoConstants.NAME_DETAIL, "query", "detail");
		try {
			ItemResultDO result = itemQueryServiceClient.queryItemById(itemId, options, app);
			if(result.isSuccess()){
				return result.getItem();
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
