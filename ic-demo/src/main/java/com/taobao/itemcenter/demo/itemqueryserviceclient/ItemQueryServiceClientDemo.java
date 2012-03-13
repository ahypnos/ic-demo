package com.taobao.itemcenter.demo.itemqueryserviceclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.common.logging.LoggerFactory;

import com.taobao.item.constant.AppInfoConstants;
import com.taobao.item.domain.AppInfoDO;
import com.taobao.item.domain.AuctionStoreDO;
import com.taobao.item.domain.ItemAndSkuBond;
import com.taobao.item.domain.ItemDO;
import com.taobao.item.domain.ItemPVPairDO;
import com.taobao.item.domain.query.AuctionStoreIdDO;
import com.taobao.item.domain.query.ItemAndSkuIdDO;
import com.taobao.item.domain.query.QueryItemOptionsDO;
import com.taobao.item.domain.query.QueryRecommendOption;
import com.taobao.item.domain.query.QuerySkuOptionsDO;
import com.taobao.item.domain.query.QueryVideoItemOption;
import com.taobao.item.domain.result.ArrayResultDO;
import com.taobao.item.domain.result.BatchItemResultDO;
import com.taobao.item.domain.result.BatchItemVideoResultDO;
import com.taobao.item.domain.result.ItemResultDO;
import com.taobao.item.domain.result.ItemSkuResultDO;
import com.taobao.item.domain.result.RecommendItemResultDO;
import com.taobao.item.domain.result.ResultDO;
import com.taobao.item.exception.IcException;
import com.taobao.item.service.ItemQueryService;
import com.taobao.item.service.client.ItemQueryServiceClient;
import com.taobao.uic.common.domain.BaseUserDO;

/*
 * 
 * ItemQueryServiceClient.XXX(...,AppInfoDO)和ItemQueryServiceClient.XXX(...)方法的区别在于
 * ItemQueryServiceClient.XXX(...)等价于ItemQueryServiceClient.XXX(...,AppInfoDO.UNKNOWN)
 * 
 */

public class ItemQueryServiceClientDemo {

	private Log log = LoggerFactory.getLogger(this.getClass());

	private ItemQueryServiceClient itemQueryServiceClient;

	public static void main(String[] arg) {
		new ItemQueryServiceClientDemo().queryItemForBuy();
	}

	public ItemQueryServiceClientDemo() {

		String[] location = { "itemQueryServiceClient/spring-ic-hsf.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(
				location);
		itemQueryServiceClient = (ItemQueryServiceClient) context
				.getBean("itemQueryServiceClient");

	}

	/**
	 * 根据宝贝id列表和卖家Id 批量查询商品列表 
	 * @see ItemQueryService#queryItemListByIdListWithUserId(List, long)
	 */

	public void queryItemListByIdListWithUserId() {
		List<Long> itemIdList = new ArrayList<Long>();
		itemIdList.add(1L);
		itemIdList.add(2L);
		long sellerId = 2L;

		try {

			BatchItemResultDO result = itemQueryServiceClient
					.queryItemListByIdListWithUserId(itemIdList, sellerId);

			if (result.isSuccess()) {
				// do something
			} else {

				// 操作失败id以及失败的原因 格式为 {reason1=[id1,id2...],reason2=[di3,id4...]
				// ...}

				log.warn(result.getFailIdMap() + "failed query");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 查询用户可以使用的最大推荐数量和已用推荐数量
	 * 
	 * @see ItemQueryService#queryUserRecommendItemCount(long,QueryRecommendOption)
	 */

	public void queryUserRecommendItemCount() {
		QueryRecommendOption recommendOption = new QueryRecommendOption();
		recommendOption.setIncludeUserMaxRecommendCount(true);// true 查询用户最大推荐数
																// false不查
		recommendOption.setIncludeUserRecommendedCount(true);// true
																// 查询用户当前使用推荐数，false不差

		long userId = 2L;

		try {

			RecommendItemResultDO result = itemQueryServiceClient
					.queryUserRecommendItemCount(userId, recommendOption);

			if (result.isSuccess()) {
				// do something
			} else {

				// 操作失败的原因
				log.warn(result.getErrorStr() + "failed query");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 查看和某视频关联的商品列表
	 * 
	 * @see ItemQueryService#queryUserRecommendItemCount(long,QueryRecommendOption)
	 */
	public void queryItemVideoListByVideo() {

		BaseUserDO seller = new BaseUserDO();
		seller.setUserId(1L);
		long videoId = 1L;
		QueryVideoItemOption option = new QueryVideoItemOption();
		option.setOnlyTotalCount(true);// 只查询关联的商品总数

		try {

			BatchItemVideoResultDO result = itemQueryServiceClient
					.queryItemVideoListByVideo(seller, videoId, option);

			if (result.isSuccess()) {
				// do something
			} else {

				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 查看该宝贝的描述信息
	 * 
	 * @see ItemQueryService#queryItemDescription(String)
	 */
	public void queryItemDescription() {

		String descPath = " url ";
		@SuppressWarnings("unused")
		String result = itemQueryServiceClient.queryItemDescription(descPath);

		// user result do something

	}

	/**
	 * 从tfs获取文件内容
	 * 
	 * @see ItemQueryService#queryAttachContent(String)
	 */
	public void queryAttachContent() {

		String path = "path";
		String suffix = "suffix";
		@SuppressWarnings("unused")
		byte[] result = itemQueryServiceClient.queryAttachContent(path, suffix);
		// user result do something
	}

	/**
	 * 根据商品Id获取宝贝数量.只是非常简单地查询了宝贝的数量，没有其他限制作为where条件。请谨慎使用。
	 * 
	 * @see ItemQueryService#queryItemQuantityById(Long);
	 */
	public void queryItemQuantityById() {

		Long itemId = 1L;
		try {
			ResultDO<Integer> result = itemQueryServiceClient
					.queryItemQuantityById(itemId);
			if (result.isSuccess()) {

				// do some thing
			} else {
				// 操作失败的原因
				log.warn(result.getErrorStr() + "failed query");
				// do something

			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 通过缓存机制，快速批量根据宝贝ID批量查询宝贝,一次查询宝贝数量最好少于20个
	 * 
	 * @see 
	 *      ItemQueryService#queryItemsByIdsWithCache(List<Long>,QueryItemOptionsDO
	 *      ,AppInfoDO);
	 */

	public void queryItemsByIdsWithCache() {

		List<Long> itemIds = new ArrayList<Long>();
		itemIds.add(1L);// itemIds.size()<=20
		QueryItemOptionsDO options = new QueryItemOptionsDO();
		try {
			BatchItemResultDO result = itemQueryServiceClient
					.queryItemsByIdsWithCache(itemIds, options,
							getOtherAppInfo());

			if (result.isSuccess()) {

				// do something
			} else {
				// 操作失败id以及失败的原因 格式为 {reason1=[id1,id2...],reason2=[di3,id4...]
				// ...}

				log.warn(result.getFailIdMap() + "failed query");
				// do something

			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 从cache中查询商品 该方法和queryItemsByIdsWithCache的区别在于：
	 * withCache在缓存没命中的时候会去数据库取而fromCache则只在缓存中取
	 * 
	 * @see ItemQueryService#queryItemsByIdsOnlyFromCache(List<Long>)
	 */

	public void queryItemsByIdsOnlyFromCache() {

		List<Long> itemIds = new ArrayList<Long>();
		itemIds.add(1L);
		BatchItemResultDO result = itemQueryServiceClient
				.queryItemsByIdsOnlyFromCache(itemIds);
		if (result.isSuccess()) {

			// do something
		} else {
			// 操作失败id以及失败的原因 格式为 {reason1=[id1,id2...],reason2=[di3,id4...] ...}

			log.warn(result.getFailIdMap() + "failed query");
			// do something

		}
	}

	/**
	 * 批量根据宝贝ID批量查询宝贝
	 * 
	 * @see 
	 *      ItemQueryService#queryItemsByIds(List<Long>,QueryItemOptionsDO,AppInfoDO
	 *      ) 一次性传入的ID数不要超过20
	 */
	public void queryItemsByIds() {

		List<Long> itemIdList = new ArrayList<Long>();
		itemIdList.add(1L);
		itemIdList.add(2L);

		QueryItemOptionsDO options = new QueryItemOptionsDO();
		options.setIncludeSkus(true);// 需要查询商品的sku信息，通过setXXX方法设置需要查询的额外信息

		// options.setIncludeDescription(true)想要获取宝贝的描述信息，则必须必须配置txtFileManager
		// 否则会抛出new IllegalArgumentException("tfs客户端配置不能为空!")的异常

		try {
			BatchItemResultDO result = itemQueryServiceClient.queryItemsByIds(
					itemIdList, options, getOtherAppInfo());

			if (result.isSuccess()) {

				// do something
			} else {
				// 操作失败id以及失败的原因 格式为 {reason1=[id1,id2...],reason2=[di3,id4...]
				// ...}

				log.warn(result.getFailIdMap() + "failed query");
				// do something

			}

		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 根据id获取商品记录
	 * 
	 * @see ItemQueryService#queryItemById(Long,QueryItemOptionsDO,AppInfoDO)
	 * 
	 */
	public void queryItemById() {
      System.out.println("------------调用 queryItemById接口-------------");
		long itemId = 1L;
		QueryItemOptionsDO options = new QueryItemOptionsDO();
		options.setIncludeSkus(true);// 需要查询商品的sku信息，通过setXXX方法设置需要查询的额外信息

		// options.setIncludeDescription(true)想要获取宝贝的描述信息，则必须必须配置txtFileManager
		// 否则会抛出new IllegalArgumentException("tfs客户端配置不能为空!")的异常

		try {

			ItemResultDO result = itemQueryServiceClient.queryItemById(itemId,
					options, getOtherAppInfo());

			if (result.isSuccess()) {

				// do something
			} else {
				// 操作失败的原因

				log.warn(result.getErrorStr() + "failed query");
				// do something

			}

		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 通过缓存机制，快速根据宝贝ID查询宝贝
	 * 
	 *@see ItemQueryService#queryItemById(long, QueryItemOptionsDO)
	 */

	public void queryItemByIdWithCache() {

		long itemId = 1L;

		QueryItemOptionsDO options = new QueryItemOptionsDO();
		options.setIncludeDescription(true);//获取该商品的描述信息
		try {
			ItemResultDO result = itemQueryServiceClient
					.queryItemByIdWithCache(itemId, options, getOtherAppInfo());

			if (result.isSuccess()) {

				// do something
			} else {
				// 操作失败的原因

				log.warn(result.getErrorStr() + "failed query");
				// do something

			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 从cache中查询商品该方法和queryItemByIdWithCache的区别在于：withCache在缓存
	 * 没命中的时候会去数据库取，而fromCache则只在缓存中取
	 * 
	 * @see 
	 *      ItemQueryService#queryItemByIdOnlyFromCache(long);
	 *      
	 */

	public void queryItemByIdOnlyFromCache() {
		long itemId = 1L;

		
		ItemResultDO result = itemQueryServiceClient
				.queryItemByIdOnlyFromCache(itemId);

		if (result.isSuccess()) {

			// do something
		} else {
			// 操作失败的原因

			log.warn(result.getErrorStr() + "failed query");
			// do something

		}   
	}
	
   /**
	 * 	根本宝贝ID查询多图URL信息
	 *  @return 多图地址列表
	 */
  public void queryItemImageList(){
	  
	long itemId=1L;
	try {
		ResultDO<List<String>> result= itemQueryServiceClient.queryItemImageList(itemId);
		if(result.isSuccess()){
			
			//do something
		}else{
			
			
		}
	} catch (IcException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
  }

   /**
	 *提供给buy调用，批量查询商品和sku，可将宝贝的商品属性转换成text
     *直接走数据库且带pv转换功能的查询,
     * 如果通过{@link #setIcFailoverForQueryItemAndSku(boolean)}打开容灾开关则直接走缓存
     *一次性最多只能查询10个商品
     *提供给购物车使用，绑定查询item和sku
     *
     *
	 */
  public void queryItemAndSkuListWithPvToText(){
	  
	  List<ItemAndSkuIdDO> itemAndSkuIdList=new ArrayList<ItemAndSkuIdDO>();
	  ItemAndSkuIdDO  idDo=new ItemAndSkuIdDO();
	  idDo.setItemId(11L);
	  idDo.setSkuId(22L);
	  itemAndSkuIdList.add(idDo);
	  idDo.setItemId(33L);
	  idDo.setSkuId(44L);
	  itemAndSkuIdList.add(idDo);
	  try {
		  itemQueryServiceClient.setIcFailoverForQueryItemAndSku(true);//打开容灾开关
			ArrayResultDO<ItemAndSkuBond> result= itemQueryServiceClient.queryItemAndSkuListWithPvToText
					(itemAndSkuIdList);
			if(result.isSuccess()){
				
				//do something
			}else{
				
				// 操作失败的原因
		          for(Entry<String, List<ItemAndSkuIdDO>> entry:result.getFailIdMap().entrySet())
			       for(ItemAndSkuIdDO idDo1:entry.getValue())
			    	   log.warn("failed ItemID="+idDo1.getItemId()+"  failed SkuID"+idDo1.getSkuId()+"   "
			    			   +"failed reason is"+entry.getKey()+" \n");
			    	   
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	  
	  
  }
  /**
	*不带pv转换的ItemAndSku查询接口, 是否使用缓存, 需要调用方通过参数自己来控制
    */
  public void queryItemAndSkuListWithoutPvToText(){
	  List<ItemAndSkuIdDO> itemAndSkuIdList=new ArrayList<ItemAndSkuIdDO>();
	  ItemAndSkuIdDO  idDo=new ItemAndSkuIdDO();
	  idDo.setItemId(11L);
	  idDo.setSkuId(22L);
	  itemAndSkuIdList.add(idDo);
	  idDo.setItemId(33L);
	  idDo.setSkuId(44L);
	  itemAndSkuIdList.add(idDo);
	  boolean usecatch=true;//走缓存
	  try {
		  itemQueryServiceClient.setIcFailoverForQueryItemAndSku(true);//打开容灾开关
		  
		
			ArrayResultDO<ItemAndSkuBond> result= itemQueryServiceClient.queryItemAndSkuListWithoutPvToText
					(itemAndSkuIdList,usecatch);
			if(result.isSuccess()){
				
				//do something
			}else{
				
				// 操作失败的原因
		          for(Entry<String, List<ItemAndSkuIdDO>> entry:result.getFailIdMap().entrySet())
			       for(ItemAndSkuIdDO idDo1:entry.getValue())
			    	   log.warn("failed ItemID="+idDo1.getItemId()+"  failed SkuID"+idDo1.getSkuId()+"   "
			    			   +"failed reason is"+entry.getKey()+" \n");
			    	   
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
  }
  /**
 	*	根据sku id获取商品sku记录
    */
  public void querySkuBySkuId(){
	  
			long skuId=1L;
			long itemId=1L;
			QuerySkuOptionsDO queryOption=new QuerySkuOptionsDO() ;
			queryOption.setChangePropertyIdToText(true);//将属性转换成text格式
			
			try {
				ItemSkuResultDO result = itemQueryServiceClient.querySkuBySkuId(skuId, itemId, queryOption);
				if(result.isSuccess()){
					
					//do something
				}else{
					//do something
					
				}
			} catch (IcException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
  }
  /**
	*		根据skuId和userId获取商品SKU记录
    */
  public void querySkuWithUserId(){
	    long skuId=1L;
		long userId=1L;
		QuerySkuOptionsDO queryOption=new QuerySkuOptionsDO() ;
		queryOption.setChangePropertyIdToText(true);//将属性转换成text格式
		
		try {
			ItemSkuResultDO result = itemQueryServiceClient.querySkuWithUserId(skuId, userId, queryOption);
			if(result.isSuccess()){
				
				//do something
			}else{
				//do something
				
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
  }
 
   /**
  	 *提供给detail使用，通过商品id读取商品,替代queryItemById接口
  	 *	默认走数据库查询通过设置ItemTairCacheBetaParam.disableItemTairCache
  	 *  可以选择是否先走缓存如果缓存查不到，在走数据库
     */
    public void queryItemForDetail(){
  	   
 	   long itemId=1L;
  		
  		try {
  			
 			ResultDO<ItemDO> result = itemQueryServiceClient.queryItemForDetail(itemId);
  			if(result.isSuccess()){
  				
  				//do something
  			}else{
  				//打印错误信息
 				log.warn(result.getErrorMessages());
 				//do something
  				
  			}
  		} catch (IcException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	  
    }
    
    /**
  	 * 提供给Buy使用，通过商品id读取商品,替代queryItemById接口。
     *	默认走数据库查询通过设置ItemTairCacheBetaParam.disableItemTairCache
  	 *  可以选择是否先走缓存如果缓存查不到，在走数据库
     */
    public void queryItemForBuy(){
  	   
 	   long itemId=1500002989095L;
  		
  		try {
  			
 			ResultDO<ItemDO> result = itemQueryServiceClient.queryItemForBuy(itemId);
  			if(result.isSuccess()){
  				
  				//do something
  			}else{
  				//打印错误信息
  				System.out.println("----------------------------------------------");
 				log.error(result.getErrorStr());
 				System.out.println("----------------------------------------------");
 				//do something
  				
  			}
  		} catch (IcException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	  
    }
    
   /**
  	 * 为mealdetail批量查询宝贝
     * 为mealdetail的接口一次传入5个itemid，返回itemList
     */
    public void queryItemListForMeal(){
  	   
 	   List<Long> itemIds=new ArrayList<Long>();
 	   itemIds.add(1L);//itemIds.size()不能超过五个
  		
  		try {
  			
 			BatchItemResultDO result = itemQueryServiceClient.queryItemListForMeal(itemIds);
  			if(result.isSuccess()){
  				
  		  	  
  				//do something
  			}else{

				// 操作失败的原因
		          for(Entry<String, List<Long>> entry:result.getFailIdMap().entrySet())
			       for(Long idDo1:entry.getValue())
			    	   log.warn("failed ItemID="+idDo1+"   "
			    			   +"failed reason is ： "+entry.getKey()+" \n");
			    	   
				// do something
  				
  			}
  		} catch (IcException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	  
    }
    /**
  	 * 	查询自动发货宝贝的类目属性值features值 只有自动发货宝贝才会调用此方法。
  	 *  普通  宝贝不会调用
     */
    public void getFeatureValueOfAutoSend(){
    	long catId = 1L;
		List<ItemPVPairDO> props = new ArrayList<ItemPVPairDO>();
		ItemPVPairDO prop=new ItemPVPairDO();
		prop.setValueId(1L);
		prop.setPropertyId(2L);
		props.add(prop); 
		prop.setPropertyId(3L);
		props.add(prop);
		
		
  		String result = itemQueryServiceClient.getFeatureValueOfAutoSend(catId, props);
  		System.out.print("---------得到的宝贝属性值为:"+result+"------------");
  	  
    }
    
    /**
  	 * 	查询宝贝上所有的分仓库存信息。
     */
    public void queryAuctionStoreList(){
    	
		
  		long itemId=1L;
		ArrayResultDO<AuctionStoreDO> result;
		try {
			result = itemQueryServiceClient.queryAuctionStoreList(itemId);
			if(result.isSuccess()){
			result.getModuleList();//查到的分仓库存信息列表
				//do something
			}else{
              
				//do something
				
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	
  	  
    }

    /**
  	 * 	根据宝贝的skuId以及分仓ID查询分仓库存信息
     */
    public void queryAuctionStore(){
    	
		
  		AuctionStoreIdDO auctionId=new AuctionStoreIdDO();
  		auctionId.setAuctionId(1L);
  		auctionId.setSkuId(2L);
		ResultDO<AuctionStoreDO> result;
		try {
			result = itemQueryServiceClient.queryAuctionStore(auctionId);
			if(result.isSuccess()){
			result.getModule();//查到的分仓库存信息
				//do something
			}else{
              //打印出错信息
				log.warn(result.getErrorMessages());
				//do something
				
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	
  	  
    }
    
    
    
    
	

	/**
		 * 	根据用户id和商家外部编码查询商品
		 *    当outerId为null或空字符串时，返回空列表
		 * @see 
		 *      ItemQueryService#queryItemByIdWithCache(List<Long>,QueryItemOptionsDO
		 *      ,AppInfoDO);
		 * 
		 */
	  public void queryItemByOuterId(){
		  
	        long userId=1L;
			String outerId="hello";
			QueryItemOptionsDO options=new QueryItemOptionsDO();
			options.setIncludeAttaches(true);//查询商品的附件信息
			
			BatchItemResultDO result;
			try {
				result = itemQueryServiceClient
						.queryItemByOuterId(userId, outerId, options);
		
			if (result.isSuccess()) {
	
				// do something
			} else {
				// 操作失败的原因
	          
				log.warn(result.getErrorStr() + "failed query");
				// do something
	
			}   	
			} catch (IcException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		  
		  
	  }

	/**
	 * 获取loom 应用的appInfo ,在实际的应用场景中 opertion,memo,operator 最好设置和业务相关的参数
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private static AppInfoDO getLoomAppInfo() {
		AppInfoDO infoDO = new AppInfoDO();
		infoDO.setAppName(AppInfoConstants.NAME_LOOM);
		infoDO.setOperation("demo");
		infoDO.setMemo("demo");
		infoDO.setOperator("demo");
		return infoDO;
	}

	/**
	 * 获取unknow 应用的appInfo ,在实际的应用场景中appName不要设置为unknow opertion,memo,operator
	 * 最好设置和业务相关的参数
	 * 
	 * @return
	 */
	public static AppInfoDO getOtherAppInfo() {

		AppInfoDO infoDO = new AppInfoDO();
		infoDO.setAppName(AppInfoConstants.NAME_OTHER);
		infoDO.setOperation("demo");
		infoDO.setMemo("demo");
		infoDO.setOperator("demo");
		return infoDO;

	}

	public static AppInfoDO getUnknowAppInfo() {

		AppInfoDO infoDO = new AppInfoDO();
		infoDO.setAppName(AppInfoConstants.NAME_UNKNOWN);
		infoDO.setOperation("demo");
		infoDO.setMemo("demo");
		infoDO.setOperator("demo");
		return infoDO;

	}
	/**
	 * 
	 * 情况所迫，无视这个方法就行了- -
	 */
	public ResultDO<ItemDO> queryItemForDetail(Long itemId) {
		ResultDO<ItemDO> result = null ;
	  		try {
	  			
	  			result = itemQueryServiceClient.queryItemForDetail(itemId);
	  			if(result.isSuccess()){
	  				
	  				//do something
	  			}else{
	  				//打印错误信息
	 				log.warn(result.getErrorMessages());
	 				//do something
	  				
	  			}
	  		} catch (IcException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}
	  		return result;
	}
}
