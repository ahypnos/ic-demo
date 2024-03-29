package com.taobao.itemcenter.demo.itemextraserviceclient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.item.constant.ItemExtraConstants;
import com.taobao.item.domain.ItemDO;
import com.taobao.item.domain.ItemSkuDO;
import com.taobao.item.domain.extra.AuctionExtraDO;
import com.taobao.item.domain.extra.ItemExtraDO;
import com.taobao.item.domain.extra.QueryItemExtraDO;
import com.taobao.item.domain.extra.QueryItemExtraOption;
import com.taobao.item.domain.extra.SkuExtraDO;
import com.taobao.item.domain.result.ResultDO;
import com.taobao.item.domain.result.ShelfResultDO;
import com.taobao.item.exception.IcException;
import com.taobao.item.service.client.ItemExtraServiceClient;
import com.taobao.itemcenter.demo.utils.ItemUtils;

/**
 * 商品扩展信息相关操作接口实例
 * 
 * @author tieyi.qlr
 * 
 */
public class ItemExtraServiceClientDemo {

	private ItemExtraServiceClient itemExtraServiceClient;

	private long itemId = 0L;
	private long userId = 0;
	private int type = ItemExtraConstants.TYPE_EXTRASHOP;

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "itemExtraServiceClient/spring-ic-core.xml",
						"itemExtraServiceClient/spring-ic-hsf.xml" });
		ItemExtraServiceClientDemo itemExtraDemo = (ItemExtraServiceClientDemo) ac
				.getBean("itemExtraDemo");
		itemExtraDemo.queryItemExtra_查询商品扩展信息();
		itemExtraDemo.deleteItemExtraInAllShop_卖家删除不同外店的宝贝();
		itemExtraDemo.createItemExtra_创建一条新的扩展信息记录();
		itemExtraDemo.sellerDownShelfItemExtra_卖家在外店下架商品();
		itemExtraDemo.updateItemExtra_更新扩展信息();
		itemExtraDemo.queryItemExtraForUpAndDownShelf_查询结果带宝贝信息();
		System.exit(0);
	}

	/**
	 * 查询商品扩展信息,只需要传递商品ID和查询选项即可 userId已作废，该接口即将废弃
	 */
	@SuppressWarnings("deprecation")
	public void queryItemExtra_查询商品扩展信息() {
		//此处设置包含sku的信息
		QueryItemExtraOption option = new QueryItemExtraOption(QueryItemExtraOption.SKUS);
		userId = 0;
		itemId = 1500004351841L;
		type = ItemExtraConstants.TYPE_EXTRASHOP;
		
		printLine("queryItemExtra接口调用");
		try {
			ResultDO<ItemExtraDO> result = itemExtraServiceClient
					.queryItemExtra(itemId, type, userId, option);
			printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("queryItemExtra接口调用");
	}
	
	/**
	 * 卖家同时删除多个外店的宝贝，支持不同外店(shop_id)的宝贝
	 * 必须传递删除宝贝扩展信息所属的卖家ID和网店类型
	 */
	public void deleteItemExtraInAllShop_卖家删除不同外店的宝贝(){
		
		userId = 175754641L;
		type = ItemExtraConstants.TYPE_OPENEXTRASHOP;
		List<Long> itemIdList = new ArrayList<Long>();
		itemIdList.add(1500004351847L);
		itemIdList.add(1500004352529L);
		printLine("deleteItemExtraInAllShop接口调用");
		try {
			ResultDO<?> result = itemExtraServiceClient.deleteItemExtraInAllShop(itemIdList, userId , type);
			printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("deleteItemExtraInAllShop接口调用");
	}

	/**
	 * 创建一条新的商品扩展信息记录
	 */
	public void createItemExtra_创建一条新的扩展信息记录(){
		//宝贝ID的值应该设置为当前商品库中存在的值，即该宝贝已经存在 
		itemId=1604251482;
		userId=180280329;
		printLine("createItemExtra接口调用");
		//创建扩展信息对象，仅供参考
		ItemExtraDO itemExtra = buildItemExtra(itemId,userId);
		try {
			ResultDO<ItemExtraDO> result = itemExtraServiceClient.createItemExtra(itemExtra);
			if(result.isSuccess()){
				System.out.println("插入扩展信息成功！");
			}else{
				System.out.println("插入扩展信息失败！");
			}
			printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("createItemExtra接口调用");
	}
	
	/**
	 * 卖家在外店下架商品
	 */
	public void sellerDownShelfItemExtra_卖家在外店下架商品(){
		
		userId=180280329;
		itemId=1604251482;
		type = ItemExtraConstants.TYPE_OPENEXTRASHOP;
		List<Long> itemIdList = new ArrayList<Long>();
		itemIdList.add(itemId);
		printLine("sellerDownShelfItemExtra接口调用");
		try {
			ShelfResultDO result = itemExtraServiceClient.sellerDownShelfItemExtra(itemIdList, userId, type);
			System.out.println(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("sellerDownShelfItemExtra接口调用");
	}
	
	/**
	 * 更新扩展信息字段
	 */
	public void updateItemExtra_更新扩展信息(){
		printLine("updateItemExtra接口调用");
		ItemExtraDO extraItem = getTestItemExtra();
		if(null == extraItem){
			System.out.println("测试的数据不存在，请重新设置获取扩展信息记录的字段！");
			return;
		}
		String urlStr = "http://www.taobao.test.com/";	
		//更新扩展信息的标题的图片地址
		extraItem.setTitle("更新的扩展商品标题");
		extraItem.setPicUrl(urlStr);
		long curr = System.currentTimeMillis();
		Date starts = new Date(curr+50000000L);
		extraItem.setStarts(starts);
		ResultDO<ItemExtraDO> result;
		try {
			result = itemExtraServiceClient.updateItemExtra(extraItem);
			printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("updateItemExtra接口调用");
	}
	
	/**
	 * 查询结果带宝贝信息
	 * userId,type,status为必输字段
	 */
	public void queryItemExtraForUpAndDownShelf_查询结果带宝贝信息(){
		printLine("queryItemExtraForUpAndDownShelf接口调用");
		userId=180280329;
		QueryItemExtraDO queryItemExtraDO =new QueryItemExtraDO();
		queryItemExtraDO.setStatus(ItemExtraConstants.STATUS_DOWNSHELF);
		queryItemExtraDO.setUserId(userId);
		ResultDO<List<AuctionExtraDO>> result;
		try {
			result = itemExtraServiceClient.queryItemExtraForUpAndDownShelf(userId,queryItemExtraDO);
			printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("queryItemExtraForUpAndDownShelf接口调用");
	}
	
	private ItemExtraDO getTestItemExtra() {
		QueryItemExtraOption option = new QueryItemExtraOption(QueryItemExtraOption.SKUS);
		userId = 0;
		itemId = 1604251482;
		type = ItemExtraConstants.TYPE_OPENEXTRASHOP;
		try {
			@SuppressWarnings("deprecation")
			ResultDO<ItemExtraDO> result = itemExtraServiceClient
					.queryItemExtra(itemId, type, userId, option);
			if(result.isSuccess()){
				return result.getModule();
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private ItemExtraDO buildItemExtra(long itemId2, long userId2) {
		// TODO Auto-generated method stub
		ItemDO item = ItemUtils.getSimpleItem(itemId2, true);
		if(item == null ){
			System.out.println("查不到该宝贝！");
			return null;
		}
		ItemExtraDO extraItem = new ItemExtraDO();
		extraItem.setAuctionId(itemId2);
		extraItem.setReservePrice(2000);
		List<SkuExtraDO> skus = new ArrayList<SkuExtraDO>();
		if(item.getSkuList()!=null){
			for(ItemSkuDO sku : item.getSkuList()){
				SkuExtraDO skuExtra = new SkuExtraDO();
				skuExtra.setType(ItemExtraConstants.TYPE_OPENEXTRASHOP);
				//下面可以设置对应的信息，不一定是和商品主库的sku信息同步的，但是需要保证个数得一致的
				//skuExtra.setProperties(sku.getProperties());
				//skuExtra.setQuantity(sku.getQuantity());
				skuExtra.setPrice(sku.getPrice());
				skus.add(skuExtra);
			}
			extraItem.setSkus(skus);
		}
		extraItem.setType(ItemExtraConstants.TYPE_OPENEXTRASHOP);
		extraItem.setUserId(userId2);
		return extraItem;
	}

	/**
	 * 打印输出结果
	 * @param result
	 */
	private void printResult(ResultDO<?> result) {
		// TODO Auto-generated method stub
		if(result.isSuccess()){
			System.out.println(String.format("操作结果:%s ;结果为:%s", result.isSuccess(),result.getModule()));
		}else{
			System.out.println(String.format("操作结果:%s ;错误信息为:%s",result.isSuccess(),result.getErrorStr()));
		}
	}

	/**
	 * 打印一行，带标题
	 * 
	 * @param title 标题
	 *            
	 */
	private void printLine(String title) {
		System.out
				.println(String
						.format("-------------------------------------------%s-------------------------------------------\n",
								title));
	}
	
	public ItemExtraServiceClient getItemExtraServiceClient() {
		return itemExtraServiceClient;
	}

	public void setItemExtraServiceClient(
			ItemExtraServiceClient itemExtraServiceClient) {
		this.itemExtraServiceClient = itemExtraServiceClient;
	}

}
