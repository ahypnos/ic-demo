package com.taobao.itemcenter.demo.itemreadserviceclient;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.item.constant.AppInfoConstants;
import com.taobao.item.domain.AppInfoDO;
import com.taobao.item.domain.ItemSkuDO;
import com.taobao.item.domain.result.BatchItemResultDO;
import com.taobao.item.domain.result.ResultDO;
import com.taobao.item.exception.IcException;
import com.taobao.item.service.client.ItemReadServiceClient;

/**
 * 一些关于商品数据的查询，以前是对读库的访问，现在由于已经废除商品读库， 里面接口的调用逻辑采用实时搜索，提供一些不同纬度的查询，
 * 更多接口可以参照ItemInstantSearchServiceClient的一些接口调用
 * 
 * @author tieyi.qlr
 * 
 */
public class ItemReadServiceClientDemo {

	private ItemReadServiceClient itemReadServiceClient;

	private long userId = 0L;
	private String outerId = "";

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "itemReadServiceClient/spring-ic-core.xml",
						"itemReadServiceClient/spring-ic-hsf.xml" });
		ItemReadServiceClientDemo itemReadServiceClientDemo = (ItemReadServiceClientDemo) ac
				.getBean("itemReadDemo");
		itemReadServiceClientDemo.querySkuListByOuterId_通过外部编码查询sku信息();
		itemReadServiceClientDemo.queryDeletedItemsForPage_分页查询用户已删除的宝贝();
		System.exit(0);
	}

	/**
	 * @see ItemReadServiceClient.querySkuListByOuterId
	 */
	public void querySkuListByOuterId_通过外部编码查询sku信息() {
		AppInfoDO appInfo = new AppInfoDO(AppInfoConstants.NAME_TOP, "query",
				"top");
		// itemId=1500005280670 可以查询这个宝贝
		userId = 175754155;
		outerId = "TIEYI";
		printLine("querySkuListByOuterId接口调用");
		try {
			ResultDO<List<ItemSkuDO>> result = itemReadServiceClient
					.querySkuListByOuterId(userId, outerId, appInfo);
			System.out.println("查询是否成功-" + result.isSuccess());
			System.out.println(result.getModule());
		} catch (IcException e) {
			e.printStackTrace();
		}
		printLine("querySkuListByOuterId接口调用");
	}

	/**
	 * 通过分页查询用户已删除的宝贝，可以设置分页的大小，需要指定用户
	 * @see ItemReadServiceClient.queryDeletedItemsForPage
	 */
	public void queryDeletedItemsForPage_分页查询用户已删除的宝贝() {
		userId = 175754155;
		// 页码
		int pageIndex = 1;
		// 每页大小
		int pageSize = 5;
		printLine("queryDeletedItemsForPage接口调用");
		try {
			BatchItemResultDO result = itemReadServiceClient
					.queryDeletedItemsForPage(userId, pageIndex, pageSize);
			if (result.isSuccess()) {
				System.out.println(String.format(
						"卖家(%s)删除总的宝贝个数为%s,在第%s页查到%s个宝贝！", userId, result
								.getTotalCount(), pageIndex, result
								.getItemList().size()));
				System.out.println(result.getItemList());
			} else {
				System.out.println("查询失败！");
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			printLine("queryDeletedItemsForPage接口调用");
		}
	}

	/**
	 * 打印一行，带标题
	 * 
	 * @param title
	 *            标题
	 */
	private void printLine(String title) {
		System.out
				.println(String
						.format("-------------------------------------------%s-------------------------------------------\n",
								title));
	}

	public ItemReadServiceClient getItemReadServiceClient() {
		return itemReadServiceClient;
	}

	public void setItemReadServiceClient(
			ItemReadServiceClient itemReadServiceClient) {
		this.itemReadServiceClient = itemReadServiceClient;
	}

}
