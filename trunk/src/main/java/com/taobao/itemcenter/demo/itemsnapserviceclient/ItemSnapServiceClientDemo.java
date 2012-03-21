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

	/**
	 * 参见#insertItemSnap(ItemSnapDO itemSnapDO, long itemId,boolean istemp)
	 * @param itemSnapDO
	 * @param itemId
	 * @return
	 * @throws IcException
	 */
	public ResultDO<Long> insertItemSnap(ItemSnapDO itemSnapDO, long itemId)
			throws IcException {
		return null;
	}

	public ResultDO<Integer> updateEndsAndPrice() throws IcException {
		ResultDO<Integer> result = new ResultDO<Integer>();
		ItemSnapUpdateDO itemSnapUpdateDO = new ItemSnapUpdateDO();
		itemSnapUpdateDO.setSnapId(SAMPLE_PERSISTENT_SNAP_ID);
		itemSnapUpdateDO.setClosed(true);
		itemSnapUpdateDO.setGmtLastRef2Sysdate(true);
		itemSnapUpdateDO.setCurrentBid(123.5);
		itemSnapUpdateDO.setEnds(new Date());
		itemSnapUpdateDO.setReservePrice(123.5);
		result = itemSnapServiceClient.updateEndsAndPrice(itemSnapUpdateDO, SAMPLE_PERSISTENT_ITEM_ID);
		return result;
	}

	/**
	 * 插入一条快照
	 * @param itemSnapDO
	 * @param itemId
	 * @param istemp
	 * @return
	 * @throws IcException
	 */
	public ResultDO<Long> insertItemSnap() throws IcException {
		ResultDO<Long> result = new ResultDO<Long>();
		result = itemSnapServiceClient.insertItemSnap(createItemSnapDO(), SAMPLE_TEMP_ITEM_ID, true);
		return result;
	}

	private ItemSnapDO createItemSnapDO(){
		ItemSnapDO snap = new ItemSnapDO();
		snap.setIncrementNum(99.9);
		snap.setHaveInvoice(0);
		snap.setHaveGuarantee(0);
		snap.setStuffStatus(5);
		snap.setApproveStatus(0);
		snap.setPromotedStatus(1);
		snap.setRepostTimes(0);
		snap.setSecureTradeAgree(1);
		return snap;
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

		//具体调用不列出
//		try {
//			System.out.println(itemSnapServiceClientDemo.queryItemSnapBySnapId(0L, 0L));
//		} catch (IcException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
