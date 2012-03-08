package com.taobao.itemcenter.demo.itemSnapServiceClient;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.item.domain.query.ItemSnapQuery;
import com.taobao.item.domain.result.ResultDO;
import com.taobao.item.domain.snap.ItemSnapDO;
import com.taobao.item.domain.snap.ItemSnapUpdateDO;
import com.taobao.item.exception.IcException;
import com.taobao.item.service.client.ItemSnapServiceClient;

public class ItemSnapServiceClientDemo {
	private ItemSnapServiceClient itemSnapServiceClient;

	public ResultDO<ItemSnapDO> queryItemSnapBySnapId(long snapId, long itemId)
			throws IcException {
		return null;
	}

	public ResultDO<ItemSnapDO> queryItemSnapByAuctionId(long itemId,
			ItemSnapQuery itemSnapQuery) throws IcException {
		return null;
	}

	public ResultDO<ItemSnapDO> queryItemSnapBySnapId(long snapId, long itemId,
			boolean istemp) throws IcException {
		return null;
	}

	public ResultDO<ItemSnapDO> queryItemSnapByAuctionId(long itemId,
			ItemSnapQuery itemSnapQuery, boolean istemp) throws IcException {
		return null;
	}

	public ResultDO<Integer> updateGmtLastRefBySnapId(long snapId,
			Date lastRefDate, long itemId) throws IcException {
		return null;
	}

	public ResultDO<Long> insertItemSnap(ItemSnapDO itemSnapDO, long itemId)
			throws IcException {
		return null;
	}

	public ResultDO<Integer> updateDescPathBySnapId(long snapId,
			String descPath, long itemId) throws IcException {
		return null;
	}

	public ResultDO<Integer> updateEndsAndPrice(
			ItemSnapUpdateDO itemSnapUpdateDO, long itemId) throws IcException {
		return null;
	}

	public ResultDO<Long> insertItemSnap(ItemSnapDO itemSnapDO, long itemId,
			boolean istemp) throws IcException {
		return null;
	}

	public ResultDO<Integer> updateRepostTimesBySnapId(long snapId,
			int repostTimes, long itemId) throws IcException {
		return null;
	}

	public ResultDO<Integer> updateRepostTimesByItemId(long itemId,
			int repostTimes) throws IcException {
		return null;
	}

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "itemSnapServiceClient/spring-ic-hsf.xml" });
		ItemSnapServiceClient snapClient = (ItemSnapServiceClient) ac
				.getBean("itemSnapServiceClient");

		System.out.println(snapClient);
	}
}
