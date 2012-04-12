package com.taobao.itemcenter.demo.itemqueryserviceclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.ecs.xhtml.param;
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
import com.taobao.itemcenter.demo.itemserviceclient.itemServiceClienDemo;
import com.taobao.uic.common.domain.BaseUserDO;

/*
 * 
 * ItemQueryServiceClient.XXX(...,AppInfoDO)��ItemQueryServiceClient.XXX(...)��������������
 * ItemQueryServiceClient.XXX(...)�ȼ���ItemQueryServiceClient.XXX(...,AppInfoDO.UNKNOWN)
 * 
 */

public class ItemQueryServiceClientDemo {

	private Log log = LoggerFactory.getLogger(this.getClass());

	private ItemQueryServiceClient itemQueryServiceClient;
   
	private  List<Long> itemIdList=new ArrayList<Long>(){/**
		 * 
		 */
		private static final long serialVersionUID = 4576510935105518152L;

	{add(1500003118097L);}};
    private long itemId=2249693031L;
    private long sellerId=13261504L;
    private long userId=13261504L;
    private long skuid1=85430289L;
    private long skuid2=85430291L;
	public static void main(String[] arg) {
		ItemQueryServiceClientDemo  itemQueryServiceClient=new ItemQueryServiceClientDemo();
		//itemQueryServiceClient.queryItemById_ͨ��ID��ѯ��Ʒ();
    	//itemQueryServiceClient.queryItemAndSkuListWithoutPvToText();
		//itemQueryServiceClient.getFeatureValueOfAutoSend();
		//itemQueryServiceClient.queryAttachContent();
//	      itemQueryServiceClient.queryAuctionStore();
		itemQueryServiceClient.queryItemAndSkuListWithPvToText();//tair������
		//itemQueryServiceClient.queryItemByIdOnlyFromCache();
		//itemQueryServiceClient.queryItemByIdWithCache();
		//itemQueryServiceClient.queryItemByOuterId();
		//itemQueryServiceClient.queryItemDescription();
        //itemQueryServiceClient.queryItemForBuy();
		//itemQueryServiceClient.queryItemImageList();
		//itemQueryServiceClient.queryItemVideoListByVideo();
	    //itemQueryServiceClient.querySkuWithUserId();
		//itemQueryServiceClient.queryUserRecommendItemCount();
	}
	public ItemQueryServiceClientDemo() {

		String[] location = { "itemQueryServiceClient/spring-ic-hsf.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(
				location);
		itemQueryServiceClient = (ItemQueryServiceClient) context
				.getBean("itemQueryServiceClient");

	}

	/**
	 * ���ݱ���id�б������Id ������ѯ��Ʒ�б� 
	 * @see ItemQueryService#queryItemListByIdListWithUserId(List, long)
	 */

	public void queryItemListByIdListWithUserId() {
		
		  printLine("����queryItemListByIdListWithUserId����");
		try {

			BatchItemResultDO result = itemQueryServiceClient
					.queryItemListByIdListWithUserId(itemIdList, sellerId);

			if (result.isSuccess()) {
				// do something
	              printLine("����queryItemListByIdListWithUserId�ӿڳɹ�"+result.getItemList().get(0));
				
			} else {

				// ����ʧ��id�Լ�ʧ�ܵ�ԭ�� ��ʽΪ {reason1=[id1,id2...],reason2=[di3,id4...]
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
	 * ��ѯ�û�����ʹ�õ�����Ƽ������������Ƽ�����
	 * 
	 * @see ItemQueryService#queryUserRecommendItemCount(long,QueryRecommendOption)
	 */

	public void queryUserRecommendItemCount() {
	
		printLine("���÷���queryUserRecommendItemCount");
		QueryRecommendOption recommendOption = new QueryRecommendOption();
		recommendOption.setIncludeUserMaxRecommendCount(true);// true ��ѯ�û�����Ƽ���
																// false����
		recommendOption.setIncludeUserRecommendedCount(true);// true
																// ��ѯ�û���ǰʹ���Ƽ�����false����

		

		try {

			RecommendItemResultDO result = itemQueryServiceClient
					.queryUserRecommendItemCount(userId, recommendOption);

			if (result.isSuccess()) {
				printLine("���÷���queryUserRecommendItemCount�ɹ�");
				// do something
				
					
				
				
			} else {

				// ����ʧ�ܵ�ԭ��
				log.warn(result.getErrorStr() + "failed query");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * �鿴��ĳ��Ƶ��������Ʒ�б�
	 * 
	 * @see ItemQueryService#queryUserRecommendItemCount(long,QueryRecommendOption)
	 */
	public void queryItemVideoListByVideo() {
      printLine("����queryItemVideoListByVideo����");
		long videoId = 1L;//�������ȷ�� userId��videoId
		
		BaseUserDO seller = new BaseUserDO();
		seller.setUserId(userId);
		seller.setId("1a116e61cc5e5f2701955d2aeb5fe0a1");
		QueryVideoItemOption option = new QueryVideoItemOption();
		option.setOnlyTotalCount(true);// ֻ��ѯ��������Ʒ����
       
		try {

			BatchItemVideoResultDO result = itemQueryServiceClient
					.queryItemVideoListByVideo(seller, videoId, option);

			if (result.isSuccess()) {
				printLine("����queryItemVideoListByVideo�����ɹ�");
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
	 * �鿴�ñ�����������Ϣ
	 * 
	 * @see ItemQueryService#queryItemDescription(String)
	 */
	public void queryItemDescription() {
		printLine("����queryItemDescription����");
		String descPath = "i7/1f1/551/1fa559c20fb10bac15ce0c8aea8ce269";//��д��ȷ��url
		@SuppressWarnings("unused")
		String result = itemQueryServiceClient.queryItemDescription(descPath);
		printLine("����queryItemDescription�����ɹ����ؽ��Ϊ"+result);

		// user result do something

	}

	/**
	 * ��tfs��ȡ�ļ�����
	 * 
	 * @see ItemQueryService#queryAttachContent(String)
	 */
	public void queryAttachContent() {
    printLine("����queryAttachContent����");
		String path = "path";
		String suffix = "suffix";
		@SuppressWarnings("unused")
		byte[] result = itemQueryServiceClient.queryAttachContent(path, suffix);
		// user result do something
		printLine("����queryAttachContent�������ؽ��Ϊ"+result);
	}

	/**
	 * ������ƷId��ȡ��������.ֻ�Ƿǳ��򵥵ز�ѯ�˱�����������û������������Ϊwhere�����������ʹ�á�
	 * 
	 * @see ItemQueryService#queryItemQuantityById(Long);
	 */
	public void queryItemQuantityById() {

		try {
			ResultDO<Integer> result = itemQueryServiceClient
					.queryItemQuantityById(itemId);
			if (result.isSuccess()) {

				// do some thing
			} else {
				// ����ʧ�ܵ�ԭ��
				log.warn(result.getErrorStr() + "failed query");
				// do something

			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ͨ��������ƣ������������ݱ���ID������ѯ����,һ�β�ѯ���������������20��
	 * 
	 * @see 
	 *      ItemQueryService#queryItemsByIdsWithCache(List<Long>,QueryItemOptionsDO
	 *      ,AppInfoDO);
	 */

	public void queryItemsByIdsWithCache() {

		QueryItemOptionsDO options = new QueryItemOptionsDO();
		try {
			BatchItemResultDO result = itemQueryServiceClient
					.queryItemsByIdsWithCache(itemIdList, options,
							getOtherAppInfo());

			if (result.isSuccess()) {

				// do something
			} else {
				// ����ʧ��id�Լ�ʧ�ܵ�ԭ�� ��ʽΪ {reason1=[id1,id2...],reason2=[di3,id4...]
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
	 * ��cache�в�ѯ��Ʒ �÷�����queryItemsByIdsWithCache���������ڣ�
	 * withCache�ڻ���û���е�ʱ���ȥ���ݿ�ȡ��fromCache��ֻ�ڻ�����ȡ
	 * 
	 * @see ItemQueryService#queryItemsByIdsOnlyFromCache(List<Long>)
	 */

	public void queryItemsByIdsOnlyFromCache() {

		BatchItemResultDO result = itemQueryServiceClient
				.queryItemsByIdsOnlyFromCache(itemIdList);
		if (result.isSuccess()) {

			// do something
		} else {
			// ����ʧ��id�Լ�ʧ�ܵ�ԭ�� ��ʽΪ {reason1=[id1,id2...],reason2=[di3,id4...] ...}

			log.warn(result.getFailIdMap() + "failed query");
			// do something

		}
	}

	/**
	 * �������ݱ���ID������ѯ����
	 * 
	 * @see 
	 *      ItemQueryService#queryItemsByIds(List<Long>,QueryItemOptionsDO,AppInfoDO
	 *      ) һ���Դ����ID����Ҫ����20
	 */
	public void queryItemsByIds() {

		
		QueryItemOptionsDO options = new QueryItemOptionsDO();
		options.setIncludeSkus(true);// ��Ҫ��ѯ��Ʒ��sku��Ϣ��ͨ��setXXX����������Ҫ��ѯ�Ķ�����Ϣ

		// options.setIncludeDescription(true)��Ҫ��ȡ������������Ϣ��������������txtFileManager
		// ������׳�new IllegalArgumentException("tfs�ͻ������ò���Ϊ��!")���쳣

		try {
			BatchItemResultDO result = itemQueryServiceClient.queryItemsByIds(
					itemIdList, options, getOtherAppInfo());

			if (result.isSuccess()) {

				// do something
			} else {
				// ����ʧ��id�Լ�ʧ�ܵ�ԭ�� ��ʽΪ {reason1=[id1,id2...],reason2=[di3,id4...]
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
	 * ����id��ȡ��Ʒ��¼
	 * 
	 * @see ItemQueryService#queryItemById(Long,QueryItemOptionsDO,AppInfoDO)
	 * 
	 */
	public void queryItemById_ͨ��ID��ѯ��Ʒ() {
      printLine("���� queryItemById�ӿ�");
		
		QueryItemOptionsDO options = new QueryItemOptionsDO();
		options.setIncludeSkus(true);// ��Ҫ��ѯ��Ʒ��sku��Ϣ��ͨ��setXXX����������Ҫ��ѯ�Ķ�����Ϣ
        
		// options.setIncludeDescription(true)��Ҫ��ȡ������������Ϣ��������������txtFileManager
		// ������׳�new IllegalArgumentException("tfs�ͻ������ò���Ϊ��!")���쳣
        
		try {

			ItemResultDO result = itemQueryServiceClient.queryItemById(itemId,options, getOtherAppInfo());

			if (result.isSuccess()) {

				 
				 printLine("���� queryItemById�ӿڳɹ�"+result.getItem().getSkuList());
				
				// do something
			} else {
				// ����ʧ�ܵ�ԭ��

				log.warn(result.getErrorStr() + "failed query");
				// do something

			}

		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ͨ��������ƣ����ٸ��ݱ���ID��ѯ����
	 * 
	 *@see ItemQueryService#queryItemById(long, QueryItemOptionsDO)
	 */

	public void queryItemByIdWithCache() {

	 printLine("����queryItemByIdWithCache����");
		QueryItemOptionsDO options = new QueryItemOptionsDO();
		options.setIncludeDescription(true);//��ȡ����Ʒ��������Ϣ
		try {
			ItemResultDO result = itemQueryServiceClient
					.queryItemByIdWithCache(itemId, options, getOtherAppInfo());

			if (result.isSuccess()) {
				printLine("����queryItemByIdWithCache�����ɹ�");
				// do something
			} else {
				// ����ʧ�ܵ�ԭ��

				log.warn(result.getErrorStr() + "failed query");
				// do something

			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��cache�в�ѯ��Ʒ�÷�����queryItemByIdWithCache���������ڣ�withCache�ڻ���
	 * û���е�ʱ���ȥ���ݿ�ȡ����fromCache��ֻ�ڻ�����ȡ
	 * 
	 * @see 
	 *      ItemQueryService#queryItemByIdOnlyFromCache(long);
	 *      
	 */

	public void queryItemByIdOnlyFromCache() {
	   printLine("����queryItemByIdOnlyFromCache����");		
		ItemResultDO result = itemQueryServiceClient
				.queryItemByIdOnlyFromCache(itemId);

		if (result.isSuccess()) {
			printLine("����queryItemByIdOnlyFromCache�����ɹ�");	
			// do something
		} else {
			// ����ʧ�ܵ�ԭ��

			log.warn(result.getErrorStr() + "failed query");
			// do something

		}   
	}
	
   /**
	 * 	��������ID��ѯ��ͼURL��Ϣ
	 *  @return ��ͼ��ַ�б�
	 */
  public void queryItemImageList(){
	  printLine("����queryItemImageList����");

	try {
		ResultDO<List<String>> result= itemQueryServiceClient.queryItemImageList(itemId);
		if(result.isSuccess()){
			printLine("����queryItemImageList�����ɹ�");
			//do something
		}else{
			
			
		}
	} catch (IcException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
  }

   /**
	 *�ṩ��buy���ã�������ѯ��Ʒ��sku���ɽ���������Ʒ����ת����text
     *ֱ�������ݿ��Ҵ�pvת�����ܵĲ�ѯ,
     * ���ͨ��{@link #setIcFailoverForQueryItemAndSku(boolean)}�����ֿ�����ֱ���߻���
     *һ�������ֻ�ܲ�ѯ10����Ʒ
     *�ṩ�����ﳵʹ�ã��󶨲�ѯitem��sku
     *
     *
	 */
  public void queryItemAndSkuListWithPvToText(){
	  printLine("����queryItemAndSkuListWithPvToText����");
	  List<ItemAndSkuIdDO> itemAndSkuIdList=new ArrayList<ItemAndSkuIdDO>();
	  ItemAndSkuIdDO  idDo=new ItemAndSkuIdDO();
	  idDo.setItemId(itemId);
	  idDo.setSkuId(skuid1);
	  itemAndSkuIdList.add(idDo);
	  
	  try {
		  itemQueryServiceClient.setIcFailoverForQueryItemAndSku(true);//�����ֿ���
			ArrayResultDO<ItemAndSkuBond> result= itemQueryServiceClient.queryItemAndSkuListWithPvToText
					(itemAndSkuIdList);
			if(result.isSuccess()){
				  printLine("����queryItemAndSkuListWithPvToText�����ɹ�");
				//do something
			}else{
				
				// ����ʧ�ܵ�ԭ��
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
	*����pvת����ItemAndSku��ѯ�ӿ�, �Ƿ�ʹ�û���, ��Ҫ���÷�ͨ�������Լ�������
    */
  public void queryItemAndSkuListWithoutPvToText(){
	  printLine("����queryItemAndSkuListWithoutPvToText����");
	  List<ItemAndSkuIdDO> itemAndSkuIdList=new ArrayList<ItemAndSkuIdDO>();
	  ItemAndSkuIdDO  idDo=new ItemAndSkuIdDO();
	  idDo.setItemId(itemId);
	  idDo.setSkuId(skuid1);
	  itemAndSkuIdList.add(idDo);
	  boolean usecatch=true;//�߻���
	  try {
		  itemQueryServiceClient.setIcFailoverForQueryItemAndSku(true);//�����ֿ���
		  
		
			ArrayResultDO<ItemAndSkuBond> result= itemQueryServiceClient.queryItemAndSkuListWithoutPvToText
					(itemAndSkuIdList,usecatch);
			if(result.isSuccess()){
				printLine("����queryItemAndSkuListWithoutPvToText�����ɹ�");
				
			}else{
				
				// ����ʧ�ܵ�ԭ��
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
 	*	����sku id��ȡ��Ʒsku��¼
    */
  public void querySkuBySkuId(){
	  
		    QuerySkuOptionsDO queryOption=new QuerySkuOptionsDO() ;
			queryOption.setChangePropertyIdToText(true);//������ת����text��ʽ
			
			try {
				ItemSkuResultDO result = itemQueryServiceClient.querySkuBySkuId(skuid1, itemId, queryOption);
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
	*		����skuId��userId��ȡ��ƷSKU��¼
    */
  public void querySkuWithUserId(){
	  printLine("����querySkuWithUserId����");
		QuerySkuOptionsDO queryOption=new QuerySkuOptionsDO() ;
		queryOption.setChangePropertyIdToText(true);//������ת����text��ʽ
		
		try {
			ItemSkuResultDO result = itemQueryServiceClient.querySkuWithUserId(skuid1, userId, queryOption);
			if(result.isSuccess()){
				  printLine("����querySkuWithUserId�����ɹ�");
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
  	 *�ṩ��detailʹ�ã�ͨ����Ʒid��ȡ��Ʒ,���queryItemById�ӿ�
  	 *	Ĭ�������ݿ��ѯͨ������ItemTairCacheBetaParam.disableItemTairCache
  	 *  ����ѡ���Ƿ����߻����������鲻�����������ݿ�
     */
    public void queryItemForDetail(){
  		try {
  			
 			ResultDO<ItemDO> result = itemQueryServiceClient.queryItemForDetail(itemId);
  			if(result.isSuccess()){
  				
  				//do something
  			}else{
  				//��ӡ������Ϣ
 				log.warn(result.getErrorMessages());
 				//do something
  				
  			}
  		} catch (IcException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	  
    }
    
    /**
  	 * �ṩ��Buyʹ�ã�ͨ����Ʒid��ȡ��Ʒ,���queryItemById�ӿڡ�
     *	Ĭ�������ݿ��ѯͨ������ItemTairCacheBetaParam.disableItemTairCache
  	 *  ����ѡ���Ƿ����߻����������鲻�����������ݿ�
     */
    public void queryItemForBuy(){
  		printLine("����queryItemForBuy����");
  		try {
  			
 			ResultDO<ItemDO> result = itemQueryServiceClient.queryItemForBuy(itemId);
  			if(result.isSuccess()){
  				printLine("����queryItemForBuy�����ɹ�");
  				//do something
  			}else{
  				//��ӡ������Ϣ
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
  	 * Ϊmealdetail������ѯ����
     * Ϊmealdetail�Ľӿ�һ�δ��벻�ܳ���5��itemid������itemList
     */
    public void queryItemListForMeal(){
  	   
 	
  		
  		try {
  			
 			BatchItemResultDO result = itemQueryServiceClient.queryItemListForMeal(itemIdList);
  			if(result.isSuccess()){
  				
  		  	  
  				//do something
  			}else{

				// ����ʧ�ܵ�ԭ��
		          for(Entry<String, List<Long>> entry:result.getFailIdMap().entrySet())
			       for(Long idDo1:entry.getValue())
			    	   log.warn("failed ItemID="+idDo1+"   "
			    			   +"failed reason is �� "+entry.getKey()+" \n");
			    	   
				// do something
  				
  			}
  		} catch (IcException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	  
    }
    /**
  	 * 	��ѯ�Զ�������������Ŀ����ֵfeaturesֵ ֻ���Զ����������Ż���ô˷�����
  	 *  ��ͨ  �����������
     */
    public void getFeatureValueOfAutoSend(){
    	printLine("����getFeatureValueOfAutoSend����");
    	long catId = 1L;
		List<ItemPVPairDO> props = new ArrayList<ItemPVPairDO>();
		ItemPVPairDO prop=new ItemPVPairDO();
		prop.setValueId(1L);
		prop.setPropertyId(2L);
		props.add(prop); 
		prop.setPropertyId(3L);
		props.add(prop);
		
		
  		String result = itemQueryServiceClient.getFeatureValueOfAutoSend(catId, props);
  		System.out.print("---------�õ��ı�������ֵΪ:"+result+"------------");
  	  
    }
    
    /**
  	 * 	��ѯ���������еķֲֿ����Ϣ��
     */
    public void queryAuctionStoreList(){
    	
		ArrayResultDO<AuctionStoreDO> result;
		try {
			result = itemQueryServiceClient.queryAuctionStoreList(itemId);
			if(result.isSuccess()){
			result.getModuleList();//�鵽�ķֲֿ����Ϣ�б�
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
  	 * 	���ݱ�����skuId�Լ��ֲ�ID��ѯ�ֲֿ����Ϣ
     */
    public void queryAuctionStore(){
    	
		printLine("����queryAuctionStore����");
  		AuctionStoreIdDO auctionId=new AuctionStoreIdDO();
  		auctionId.setAuctionId(itemId);
  		auctionId.setSkuId(skuid1);
		ResultDO<AuctionStoreDO> result;
		try {
			result = itemQueryServiceClient.queryAuctionStore(auctionId);
			printLine(result.getErrorStr());
			if(result.isSuccess()){
				printLine("����queryAuctionStore�����ɹ�"+result.getModule());//�鵽�ķֲֿ����Ϣ
				//do something
			}else{
              //��ӡ������Ϣ
				log.warn(result.getErrorMessages());
				//do something
				
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	
  	  
    }
    
    
    
    
	

	/**
		 * 	�����û�id���̼��ⲿ�����ѯ��Ʒ
		 *    ��outerIdΪnull����ַ���ʱ�����ؿ��б�
		 * @see 
		 *      ItemQueryService#queryItemByIdWithCache(List<Long>,QueryItemOptionsDO
		 *      ,AppInfoDO);
		 * 
		 */
	  public void queryItemByOuterId(){
		  printLine("����queryItemByOuterId����");
			String outerId="hello";
			QueryItemOptionsDO options=new QueryItemOptionsDO();
			options.setIncludeAttaches(true);//��ѯ��Ʒ�ĸ�����Ϣ
			
			BatchItemResultDO result;
			try {
				result = itemQueryServiceClient
						.queryItemByOuterId(userId, outerId, options);
		
			if (result.isSuccess()) {
				  printLine("����queryItemByOuterId�����ɹ�");
				// do something
			} else {
				// ����ʧ�ܵ�ԭ��
	          log.warn(result.getErrorStr() + "failed query");
				// do something
	
			}   	
			} catch (IcException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		  
		  
	  }

	/**
	 * ��ȡloom Ӧ�õ�appInfo ,��ʵ�ʵ�Ӧ�ó����� opertion,memo,operator ������ú�ҵ����صĲ���
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
	 * ��ȡunknow Ӧ�õ�appInfo ,��ʵ�ʵ�Ӧ�ó�����appName��Ҫ����Ϊunknow opertion,memo,operator
	 * ������ú�ҵ����صĲ���
	 * 
	 * @return
	 */
	private static AppInfoDO getOtherAppInfo() {

		AppInfoDO infoDO = new AppInfoDO();
		infoDO.setAppName(AppInfoConstants.NAME_OTHER);
		infoDO.setOperation("demo");
		infoDO.setMemo("demo");
		infoDO.setOperator("demo");
		return infoDO;

	}

	private static AppInfoDO getUnknowAppInfo() {

		AppInfoDO infoDO = new AppInfoDO();
		infoDO.setAppName(AppInfoConstants.NAME_UNKNOWN);
		infoDO.setOperation("demo");
		infoDO.setMemo("demo");
		infoDO.setOperator("demo");
		return infoDO;

	}
	/**
	 * ��ӡһ�У�������
	 * 
	 * @param title
	 *            ����
	 */
	private void printLine(String title) {
		System.out
				.println(String
						.format("-------------------------------------------%s-------------------------------------------\n",
								title));
	}
}

