package com.taobao.itemcenter.demo.itemsnapserviceclient;

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

	private static long SAMPLE_PERSISTENT_SNAP_ID = 8068802L;//持久快照表的样例快照id
	
	private static long SAMPLE_PERSISTENT_ITEM_ID = 1500004823915L;//持久快照表的样例快照的商品id
	
	private static long SAMPLE_TEMP_SNAP_ID = 5937873L;//临时快照表的样例快照id
	
	private static long SAMPLE_TEMP_ITEM_ID = 1600358258;//临时快照表的样例快照的商品id
	
	public ResultDO<ItemSnapDO> queryItemSnapBySnapId(long snapId, long itemId)
			throws IcException {
		return itemSnapServiceClient.queryItemSnapBySnapId(SAMPLE_PERSISTENT_SNAP_ID, SAMPLE_PERSISTENT_ITEM_ID);
	}

	public ResultDO<ItemSnapDO> queryItemSnapByAuctionId(long itemId,
			ItemSnapQuery itemSnapQuery) throws IcException {
		return itemSnapServiceClient.queryItemSnapByAuctionId(SAMPLE_PERSISTENT_ITEM_ID, null);
	}

	public ResultDO<ItemSnapDO> queryItemSnapBySnapId(long snapId, long itemId,
			boolean istemp) throws IcException {
		return itemSnapServiceClient.queryItemSnapBySnapId(SAMPLE_TEMP_SNAP_ID, SAMPLE_TEMP_ITEM_ID, true);
	}

	public ResultDO<ItemSnapDO> queryItemSnapByAuctionId(long itemId,
			ItemSnapQuery itemSnapQuery, boolean istemp) throws IcException {
		return itemSnapServiceClient.queryItemSnapByAuctionId(SAMPLE_TEMP_ITEM_ID, null, true);
	}

	public ResultDO<Integer> updateGmtLastRefBySnapId(long snapId,
			Date lastRefDate, long itemId) throws IcException {
		return itemSnapServiceClient.updateGmtLastRefBySnapId(SAMPLE_PERSISTENT_SNAP_ID, new Date(), SAMPLE_PERSISTENT_ITEM_ID);
	}

	public ResultDO<Long> insertItemSnap(ItemSnapDO itemSnapDO, long itemId)
			throws IcException {
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
		return itemSnapServiceClient.updateRepostTimesBySnapId(SAMPLE_PERSISTENT_SNAP_ID, -1, SAMPLE_PERSISTENT_ITEM_ID);
	}

	public ResultDO<Integer> updateRepostTimesByItemId(long itemId,
			int repostTimes) throws IcException {
		return itemSnapServiceClient.updateRepostTimesByItemId(SAMPLE_PERSISTENT_ITEM_ID, -1);
	}

	public ItemSnapServiceClient getItemSnapServiceClient() {
		return itemSnapServiceClient;
	}

	public void setItemSnapServiceClient(ItemSnapServiceClient itemSnapServiceClient) {
		this.itemSnapServiceClient = itemSnapServiceClient;
	}

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "itemSnapServiceClient/spring-ic-core.xml","itemSnapServiceClient/spring-ic-hsf.xml" });
		ItemSnapServiceClientDemo itemSnapServiceClientDemo = (ItemSnapServiceClientDemo) ac
				.getBean("itemSnapServiceClientDemo");

		try {
			System.out.println(itemSnapServiceClientDemo.queryItemSnapBySnapId(0L, 0L));
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
