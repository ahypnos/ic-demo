package com.taobao.itemcenter.demo.CardCodeServiceClient;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.item.constant.ItemOptionsConstants;
import com.taobao.item.domain.CardCodeDO;
import com.taobao.item.domain.query.CardCodeQuery;
import com.taobao.item.exception.IcException;
import com.taobao.item.service.client.CardCodeServiceClient;

/**
 * 卡密接口相关操作 如果为卡密宝贝，则在宝贝的options字段有值1<<28
 * 
 * @see ItemOptionsConstants.ITEM_OPTIONS_AUTO_CONSIGNMENT
 * @author tieyi.qlr
 * 
 */
public class CardCodeServiceClientDemo {
	/**
	 * 通过HSF注入
	 */
	private CardCodeServiceClient cardCodeServiceClient;

	// 查询条件
	private long tradeId = 0L;
	private long itemId = 0L;
	private String buyerId = "";
	private String sellerId = "";
	private long sellerNumId = 0L;

	public static void main(String[] args) throws IcException {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "cardCodeServiceClient/spirng-ic-core.xml",
						"cardCodeServiceClient/spirng-ic-hsf.xml" });
		CardCodeServiceClientDemo cardCodeDemo = (CardCodeServiceClientDemo) ac
				.getBean("cardCodeDemo");
		
		cardCodeDemo.getCardCodeInfoByQuery_查找卡密信息();
		cardCodeDemo.getCardCodeInfoByQueryWithPage_分页查询卡密信息();
		cardCodeDemo.updateCardCodeToViewedStateForQuery_修改同一笔订单的所有卡密状态();
		cardCodeDemo.resetCardCode_恢复单个卡密至未发送状态_并更新对应商品库存();
		System.out.println("End!");
		System.exit(0);
	}

	public void getCardCodeInfoByQuery_查找卡密信息() throws IcException {
		// 根据自己的需要的查询设置相应的条件
		tradeId = 4001117207L;
		itemId = 1500004274891L;
		buyerId = "eeeebacb59d0e31c32d92808ac9f459d";
		sellerId = "8e23a21b220a8fd1635750aa4f2423c2";
		// 用于存储查询结果
		List<CardCodeDO> result;
		printLine("getCardCodeInfoByQuery接口调用");
		// 通过商品ID
		CardCodeQuery query = new CardCodeQuery();
		query.setItemId(itemId);
		result = cardCodeServiceClient.getCardCodeInfoByQuery(query);
		printResult(query, result);

		// 通过交易ID
		CardCodeQuery query1 = new CardCodeQuery();
		query1.setTradeId(tradeId);
		result = cardCodeServiceClient.getCardCodeInfoByQuery(query1);
		printResult(query1, result);

		// 通过买家ID
		CardCodeQuery query2 = new CardCodeQuery();
		query2.setBuyerId(buyerId);
		result = cardCodeServiceClient.getCardCodeInfoByQuery(query2);
		printResult(query2, result);

		// 通过卖家ID
		CardCodeQuery query3 = new CardCodeQuery();
		query3.setSellerId(sellerId);
		result = cardCodeServiceClient.getCardCodeInfoByQuery(query3);
		printResult(query3, result);

		// 通过买家ID和商品ID
		CardCodeQuery query4 = new CardCodeQuery();
		query4.setBuyerId(buyerId);
		query4.setItemId(itemId);
		result = cardCodeServiceClient.getCardCodeInfoByQuery(query4);
		printResult(query4, result);

		// 通过交易ID和商品ID
		CardCodeQuery query5 = new CardCodeQuery();
		query5.setTradeId(tradeId);
		query5.setItemId(itemId);
		result = cardCodeServiceClient.getCardCodeInfoByQuery(query5);
		printResult(query5, result);
		printLine("getCardCodeInfoByQuery接口调用");
	}

	public void getCardCodeInfoByQueryWithPage_分页查询卡密信息() throws IcException {
		// 根据自己的需要的查询设置相应的条件
		tradeId = 4001117207L;
		itemId = 1500004274891L;
		buyerId = "eeeebacb59d0e31c32d92808ac9f459d";
		sellerId = "8e23a21b220a8fd1635750aa4f2423c2";
		sellerNumId = 2022240977L;
		
		printLine("getCardCodeInfoByQueryWithPage接口调用");
		CardCodeQuery query = new CardCodeQuery();
		//如果需要获得卡密列表的数据，必须设置商品ID和卖家的数字ID，不然结果为空
		query.setItemId(itemId);
		query.setSellerNumId(sellerNumId);
		query.setPageSize(2);
		query.setCurrentPage(1);
		CardCodeQuery result = cardCodeServiceClient.getCardCodeInfoByQueryWithPage(query);
		printResult(query,result.getItem().getCardCodeList());
		
		CardCodeQuery query1 = new CardCodeQuery();
		//只传商品ID，不传卖家数据字ID,只能得到商品的基本信息，但不能获得相关卡密列表
		query1.setItemId(itemId);
		query1.setPageSize(2);
		query1.setCurrentPage(1);
		result = cardCodeServiceClient.getCardCodeInfoByQueryWithPage(query1);
		printResult(query1,result.getItem().getCardCodeList());
		
		CardCodeQuery query2 = new CardCodeQuery();
		//不传商品ID，只传卖家ID，得不到相关宝贝的数据
		query2.setSellerNumId(sellerNumId);
		query2.setPageSize(2);
		query2.setCurrentPage(1);
		result = cardCodeServiceClient.getCardCodeInfoByQueryWithPage(query2);
		if(result.getItem()==null){
			System.out.println("没有获得相关宝贝的信息！！！");
			printResult(query2,null);
		}
		printLine("getCardCodeInfoByQueryWithPage接口调用");
	}
	
	public void updateCardCodeToViewedStateForQuery_修改同一笔订单的所有卡密状态() throws IcException{
		// 根据自己的需要的查询设置相应的条件
		tradeId = 4001117207L;
		itemId = 1500004274891L;
		sellerNumId = 2022240977L;
		printLine("updateCardCodeToViewedStateForQuery接口调用");
		//返回更新成功的卡密条数
		int counts = cardCodeServiceClient.updateCardCodeToViewedStateForQuery(tradeId, itemId, sellerNumId);
		System.out.println(String.format("更新卡密状态从已发送到已查看的记录有：%s条！", counts));
		printLine("updateCardCodeToViewedStateForQuery接口调用");
	}
	
	public void resetCardCode_恢复单个卡密至未发送状态_并更新对应商品库存() throws IcException{
		int saleWinQuantity = 1; 
		// 根据自己的需要的查询设置相应的条件
		tradeId = 4001117207L;
		itemId = 1500004274891L;
		buyerId = "eeeebacb59d0e31c32d92808ac9f459d";
		sellerId = "8e23a21b220a8fd1635750aa4f2423c2";
		
		printLine("resetCardCode接口调用");
		boolean result = cardCodeServiceClient.resetCardCode(tradeId, itemId, buyerId, sellerId, saleWinQuantity);
		System.out.println("重置卡密结果："+result);
		printLine("resetCardCode接口调用");
	}
	
	

	private void printResult(CardCodeQuery query, List<CardCodeDO> result) {
		if(result == null){
			System.out.println(String.format("查询条件为:%s;\n查询的结果集为空\n",query));
			return;
		}
		System.out.println(String.format("查询条件为:%s;\n查询的结果个数为：%s;\n内容为:%s\n",
				query, result.size(), result));
	}
	
	private void printLine(String title) {
		System.out.println(String.format("-------------------------------------------%s-------------------------------------------\n",title));
	}
	
	public CardCodeServiceClient getCardCodeServiceClient() {
		return cardCodeServiceClient;
	}

	public void setCardCodeServiceClient(
			CardCodeServiceClient cardCodeServiceClient) {
		this.cardCodeServiceClient = cardCodeServiceClient;
	}
}
