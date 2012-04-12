package com.taobao.itemcenter.demo.itemserviceclient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.common.logging.LoggerFactory;
import com.taobao.common.imagecolor.ExtractColor;
import com.taobao.item.constant.AppInfoConstants;
import com.taobao.item.constant.ItemConstants;
import com.taobao.item.constant.ItemOptionsConstants;
import com.taobao.item.domain.AppInfoDO;
import com.taobao.item.domain.AuctionStoreDO;
import com.taobao.item.domain.ItemDO;
import com.taobao.item.domain.ItemImageDO;
import com.taobao.item.domain.ItemSkuDO;
import com.taobao.item.domain.ItemUpdateDO;
import com.taobao.item.domain.ItemVideoDO;
import com.taobao.item.domain.PublishItemOptionDO;
import com.taobao.item.domain.SaveItemOptionDO;
import com.taobao.item.domain.SkuStoreDO;
import com.taobao.item.domain.UpdatedPostageDO;
import com.taobao.item.domain.query.AuctionStoreIdDO;
import com.taobao.item.domain.query.QueryItemOptionsDO;
import com.taobao.item.domain.result.BaseResultDO;
import com.taobao.item.domain.result.BatchItemVideoResultDO;
import com.taobao.item.domain.result.BatchResultDO;
import com.taobao.item.domain.result.CreateItemResultDO;
import com.taobao.item.domain.result.ItemIdResultDO;
import com.taobao.item.domain.result.ProcessResultDO;
import com.taobao.item.domain.result.ResultDO;
import com.taobao.item.domain.result.SaveItemResultDO;
import com.taobao.item.domain.result.SavePictureFileResultDO;
import com.taobao.item.domain.result.ShelfResultDO;
import com.taobao.item.domain.spu.FeatureDO;
import com.taobao.item.exception.IcException;
import com.taobao.item.service.ItemService;
import com.taobao.item.service.client.ItemQueryServiceClient;
import com.taobao.item.service.client.ItemServiceClient;
import com.taobao.item.util.StringUtils;
import com.taobao.itemcenter.demo.utils.IcDemoConstants;
import com.taobao.itemcenter.demo.utils.ItemUtils;
import com.taobao.itemcenter.demo.utils.UserDataConstants;
public class itemServiceClientDemo {
	private Log log = LoggerFactory.getLogger(this.getClass());

	private static long itemId = 1500006057292L;
	private static long userId = 110010101L;
	private static ItemServiceClient itemServiceClient;
	private static ItemQueryServiceClient itemQueryServiceClient;
	private static String fileName = "src/main/resources/itemServiceClient/test.jpg";
	private static long spuid=0L;
 
	/**
	 * 添加商品相关信息
	 * 
	 */
	public void addItemOptions() {

		System.out
				.println("----------------调用addItemOptions()函数------------------------------");
		List<Long> options = new ArrayList<Long>();
		options.add(1L);// 给宝贝添加 参与会员打折的 属性
		options.add(1L << 3);// 给宝贝添加 是商城宝贝的 属性
		try {
			ProcessResultDO result = itemServiceClient.addItemOptions(itemId,
					options, getLoomAppInfo());
			if (result.isSuccess()) {

				System.out
						.println("----------------调用addItemOptions()函数成功------得到商品信息--"
								+ result.getResultCode());

				// do something
			} else {
				// 打印错误信息

				log.error(result.getErrorMessages());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 去除宝贝相关信息
	 * 
	 */
	public void removeItemOptions() {
		printLine("调用removeItemOptions方法");
		List<Long> options = new ArrayList<Long>();
		options.add(1L);// 去除宝贝 参与会员打折的 属性
		options.add(8L);// 去除宝贝 是商城宝贝的 属性
		try {
			ProcessResultDO result = itemServiceClient.removeItemOptions(
					itemId, options, getLoomAppInfo());
			if (result.isSuccess()) {
				printLine("调用removeItemOptions方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorMessages());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 发布宝贝
	 * 
	 * @see ItemService#publishItem(ItemDO, PublishItemOptionDO)
	 */
	public void publishItem() {
       printLine("调用publishItem方法");
		try {
			ItemDO item = ItemUtils.createItemDO(userId);
			// item.setXXX(); 添加商品的一系列属性 相关信息条件请参考API文档
			item.setCategoryId(IcDemoConstants.ITEM_CATEGORY_FOOD);
			item.setProperty("1930001:27772");
			item.setSpuId(0L);
			item.setAuctionPoint(5);
			// 发布商品时，杂七杂八但又需要的数据往这里加，比如发布时的用户ip,此次发布是否需要预览，是否返回SPU信息等等
			PublishItemOptionDO publicItemOption=ItemUtils.createPublishItemOptionDO();
			publicItemOption.setLang("zh_CN"); // 违规关键字校验的客服端语言，此处设置为简体中文，zh_HK繁体中文，
            CreateItemResultDO result = itemServiceClient.publishItem(item,
					publicItemOption, getAppInfoDOForSell());
			if (result.isSuccess()) {
                 printLine("调用publishItem方法成功"+result.getItem().getItemId());
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 发布前预览商品, 不保存spu和商品信息，即这个接口用于用户自己体验发布以后的效果 但是商品信息不会保存，只提供预览
	 * 
	 * @see ItemService#publishItemPreview(ItemDO, PublishItemOptionDO)
	 */
	public void publishItemPreview() {

		try {
			ItemDO item = ItemUtils.createItemDO(UserDataConstants.C_五星c卖家L);
			// item.setXXX(); 添加商品的一系列属性 相关信息条件请参考API文档
			item.setCategoryId(IcDemoConstants.ITEM_CATEGORY_FOOD);
			item.setProperty("1930001:27772");
			item.setSpuId(0L);
			item.setQuantity(1);
			item.setAuctionPoint(5);
			
			// 发布商品时，杂七杂八但又需要的数据往这里加，比如发布时的用户ip,此次发布是否需要预览，是否返回SPU信息等等
			PublishItemOptionDO publicItemOption = ItemUtils.createPublishItemOptionDO();
			publicItemOption.setLang("zh_CN"); // 违规关键字校验的客服端语言，此处设置为简体中文，zh_HK繁体中文，

			CreateItemResultDO result = itemServiceClient.publishItemPreview(
					item, publicItemOption, getLoomAppInfo());
			if (result.isSuccess()) {

				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 发布宝贝预览做和用户无关校验 这个预览去掉了和用户相关的校验，目前是DPC在调用这个接口
	 * 
	 * @see ItemService#publishItemWithOutUserPreview(ItemDO,
	 *      PublishItemOptionDO)
	 */
	public void publishItemWithOutUserPreview() {
       printLine("调用publishItemWithOutUserPreview方法");
       final Long C_SELLER_NUM_ID = UserDataConstants.C_普通c卖家L;
       final Long SELL_AND_TMALLSELL_LIMIT_CAT_ID = 201108263L;
		try {
			 ItemDO item = ItemUtils.createItemDO(C_SELLER_NUM_ID, SELL_AND_TMALLSELL_LIMIT_CAT_ID, false);
		        item.setOptions(item.getOptions() | ItemOptionsConstants.ITEM_OPTIONS_BBC_ITEM);
		      
			// item.setXXX(); 添加商品的一系列属性 相关信息条件请参考API文档
			 item.setOptions(item.getOptions() | ItemOptionsConstants.ITEM_OPTIONS_BBC_ITEM);

			// 发布商品时，杂七杂八但又需要的数据往这里加，比如发布时的用户ip,此次发布是否需要预览，是否返回SPU信息等等
			 PublishItemOptionDO publicItemOption = ItemUtils.createPublishItemOptionDO();
			publicItemOption.setLang("zh_CN"); // 违规关键字校验的客服端语言，此处设置为简体中文，zh_HK繁体中文，

			CreateItemResultDO result = itemServiceClient
					.publishItemWithOutUserPreview(item, publicItemOption,
							getLoomAppInfo());
			if (result.isSuccess()) {
				printLine("调用publishItemWithOutUserPreview方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 发布号码库宝贝, 所谓号码库宝贝是类似qq号码这样的宝贝, 一次提交多个qq号码, 但是每个qq号码生成一个宝贝, 因此一次调用, 生成多个宝贝
	 * 
	 * @see ItemService#publishNumberRangeItem(ItemDO,
	 *      String,PublishItemOptionDO)
	 */
	public void publishNumberRangeItem() {
		// 发布一个QQ号码商品
		printLine("调用publishNumberRangeItem发布数码宝贝");
		String QQStr = "20440:27548;23571:49897;20416:10122;20417:27019;20432:10122";
		long options = 32L;
		String number = "8745589";

		try {

			ItemDO item = ItemUtils.createItemDO(userId);
			item.setCategoryId(4001L); // 设置商品类目为QQ号码类目
			item.setProperty(QQStr); // 设置QQ号码类目必填属性
			item.setOptions(options); // 设置商品为号码库商品
			PublishItemOptionDO publicItemOption = ItemUtils
					.createPublishItemOptionDO();
			CreateItemResultDO result = itemServiceClient
					.publishNumberRangeItem(item, number, publicItemOption,
							getLoomAppInfo());
			if (result.isSuccess()) {
				printLine("调用publishNumberRangeItem发布数码宝贝成功"+result.getItem());
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 号码库宝贝发布前预览
	 * 
	 * @see ItemService#publishNumberRangeItemPreview(ItemDO,
	 *      String,PublishItemOptionDO)
	 */
	public void publishNumberRangeItemPreview() {
		// 发布一个QQ号码商品
		String QQStr = "20440:27548;23571:49897;20416:10122;20417:27019;20432:10122";
		long options = 32L;
		String number = "8745589";

		try {

			ItemDO item = ItemUtils.createItemDO(110010101L);
			item.setCategoryId(4001L); // 设置商品类目为QQ号码类目
			item.setProperty(QQStr); // 设置QQ号码类目必填属性
			item.setOptions(options); // 设置商品为号码库商品
			PublishItemOptionDO publicItemOption = ItemUtils
					.createPublishItemOptionDO();
			publicItemOption.setPreview(true);// 设置此次发布为预览 不进行数据操作

			CreateItemResultDO result = itemServiceClient
					.publishNumberRangeItemPreview(item, number,
							publicItemOption, getLoomAppInfo());
			if (result.isSuccess()) {

				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 卖家编辑宝贝 卡密操作：由前端决定这次保存的卡密是否为卡密商品(inputItem.setAutoConsignment()),
	 * 如果不是卡密宝贝，则将关联到此商品Id的卡密表中所有记录设置为删除状态。
	 * 
	 * @see ItemService#sellerSaveItem(long, long , ItemUpdateDO,
	 *      SaveItemOptionDO)
	 */
	public void sellerSaveItem() {
        printLine("调用sellerSaveItem方法");
          ItemDO item=publisItem(userId,false).getItem();
          printLine(""+item.getItemId());
		try {
			// 编辑卡密商品

			SaveItemOptionDO saveProcessOption = new SaveItemOptionDO();
			ItemUpdateDO inputItem = new ItemUpdateDO();
			inputItem.setDescription("description");
			inputItem.setQuantity(5);
			inputItem.setAutoArea(null);
			saveProcessOption.setLang(PublishItemOptionDO.LANG_ZH_CN);
			saveProcessOption.setClientAppName("sell");

			SaveItemResultDO result = itemServiceClient.sellerSaveItem(item.getItemId(),
					userId, inputItem,
					saveProcessOption, getOtherAppInfo());
			if (result.isSuccess()) {
				  printLine("调用sellerSaveItem方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 卖家编辑宝贝 卡密操作：由前端决定这次保存的商品是否为卡密商品(inputItem.setAutoConsignment()),
	 * 如果不是卡密宝贝，则将关联到此商品Id的卡密表中所有记录设置为删除状态。
	 * 
	 * @see ItemService#publishNumberRangeItem(ItemDO,
	 *      String,PublishItemOptionDO)
	 * 
	 * 
	 */
	public void sellerSaveNumberItem() {
		// 正常有权限卖家编辑卡密商品_商品的库存全部卖完quantity为0
        printLine("调用sellerSaveNumberItem方法");
		try {
			// 编辑卡密商品
			ItemUpdateDO inputItem = new ItemUpdateDO();
			inputItem.setStuffStatus(IcDemoConstants.ITEM_IDEL);
			inputItem.setStarts(IcDemoConstants.ITEM_UPDATE_STARTS);
			SaveItemOptionDO saveItemOptionDO = new SaveItemOptionDO();
			saveItemOptionDO.setLang(SaveItemOptionDO.LANG_ZH_CN);
			SaveItemResultDO result = itemServiceClient.sellerSaveNumberItem(
					1500006032465L, 110010101L, inputItem,
					"8745589", saveItemOptionDO, getAppInfoDOForSell());
			if (result.isSuccess()) {
				   printLine("调用sellerSaveNumberItem方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 卖家编辑宝贝前的预览，不保存信息。
	 * 
	 * @see ItemService#sellerSaveItemPreview(long, long , ItemUpdateDO,
	 *      SaveItemOptionDO)
	 */
	public void sellerSaveItemPreview() {
		try {
			// 编辑卡密商品
			long itemId = 1L;

			SaveItemOptionDO saveProcessOption = new SaveItemOptionDO();
			ItemUpdateDO inputItem = new ItemUpdateDO();
			inputItem.setDescription("description");
			// 设置商品为自动发货的商品
			inputItem.setOptions(268435456L);
			inputItem.setCategoryId(99L);
			inputItem.setProperty("1626031:27528;20435:27529");
			inputItem.setQuantity(0);
			inputItem.setAutoArea(null);
			saveProcessOption.setLang(PublishItemOptionDO.LANG_ZH_CN);
			saveProcessOption.setClientAppName("sell");

			SaveItemResultDO result = itemServiceClient.sellerSaveItemPreview(
					itemId, UserDataConstants.C_卖家自动发货权限L, inputItem,
					saveProcessOption, getAppInfoDOForSell());
			if (result.isSuccess()) {

				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改商品的收藏人数--只是修改， 不做增量
	 * 
	 * @see ItemService#modifyItemCollectionCount(long, long)
	 */
	public void modifyItemCollectionCount() {
	printLine("调用 modifyItemCollectionCount方法");
		Long itemId = 81120001L;
		long collectionCount = 4;
		ProcessResultDO result;
		try {
			result = itemServiceClient.modifyItemCollectionCount(itemId,
					collectionCount);
			if (result.isSuccess()) {
				printLine("调用 modifyItemCollectionCount方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 卖家删除商品
	 * 
	 * @see ItemService#sellerDelItem(long, List,AppInfoDO)
	 */
	public void sellerDelItem() {
                  printLine("调用sellerDelItem方法");
		try {
			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(100005529637L);
			itemIdList.add(100005529639L);

			ShelfResultDO result = itemServiceClient.sellerDelItem(
					IcDemoConstants.SELLER_ID_COMM_LONG, itemIdList,
					getUnknowAppInfo());

			if (result.isSuccess()) {
				  printLine("调用sellerDelItem方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 卖家设置商品数量
	 * 
	 * @see ItemService#sellerModifyItemQuantity(long, int,long,AppInfoDO)
	 */
	public void sellerModifyItemQuantity() {
      printLine("调用sellerModifyItemQuantity方法");
     
		try {
			ProcessResultDO result = itemServiceClient
					.sellerModifyItemQuantity(
							 publisItem(userId, false).getItem().getItemId(), 3,
							userId,
							getOtherAppInfo());

			if (result.isSuccess()) {
				 printLine("调用sellerModifyItemQuantity方法 成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 卖家设置商品数量
	 * 
	 * @deprecated
	 * @see ItemService#sellerIncreaseItemQuantity(long, int,long,AppInfoDO) 请使用
	 * @see ItemService#sellerIncreaseItemQuantity(long,
	 *      int,long,String,AppInfoDO)
	 * 
	 *      <font color="red">注意：为了防止重复操作增加库存，需要调用方必须有uuid参数，用来唯一标识某次操作。<br>
	 *      uuid可以调用IC封装的方法(参见：</font>
	 *      {@link com.taobao.item.util.StringUtils#getUUID()} <font
	 *      color="red">)来获取。调用方必须保证同一次操作时，uuid必须要保证一致。比如第一次调用增加库存接口
	 *      时，由于ic超时，调用方可能会多次重试调用，这种情况下必须保证重试调用的参数uuid保持不变，以便IC能够
	 *      根据uuid检测出调用方的重复操作。这样就可以避免重复加库存。
	 * 
	 * 
	 */
	public void sellerIncreaseItemQuantity() {

		try {
			// ProcessResultDO result =
			// itemServiceClient.sellerIncreaseItemQuantity(IcDemoConstants.ITEM_QUANTITY_ITEMID_AUTO,
			// 3,
			// IcDemoConstants.SELLER_ID_COMM_LONG, getAppInfoDOForSell());
			ProcessResultDO result = itemServiceClient
					.sellerIncreaseItemQuantity(
							IcDemoConstants.ITEM_QUANTITY_ITEMID_AUTO, 3,
							IcDemoConstants.SELLER_ID_COMM_LONG,
							StringUtils.getUUID(), getAppInfoDOForSell());
			if (result.isSuccess()) {

				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 卖家针对单个宝贝的sku数量进行设置
	 * 
	 * @see ItemService#sellerModifyItemSkuQuantity(long, long, Map, AppInfoDO)
	 */

	public void sellerModifyItemSkuQuantity() {
		// 商品无sku_补sku库存
		printLine("调用sellerModifyItemSkuQuantity方法");
		try {
			ItemDO itemDo=publisItem(userId,true).getItem();
            List<ItemSkuDO> list=itemDo.getSkuList();
			Map<Long, Integer> skuQuantityMap = new HashMap<Long, Integer>();
			skuQuantityMap.put(list.get(0).getSkuId(), 4);
			skuQuantityMap.put(list.get(1).getSkuId(), 4);
           printLine(""+itemDo.getItemId()+list.get(0).getSkuId()+"   "+list.get(1).getSkuId()+itemDo.getSkuList());
			ProcessResultDO result = itemServiceClient
					.sellerModifyItemSkuQuantity(
							userId,
							itemDo.getItemId(),
							skuQuantityMap, getLoomAppInfo());
			if (result.isSuccess()) {
				printLine("调用sellerModifyItemSkuQuantity方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 增量修改指定商品sku库存
	 * 
	 * @see ItemService#sellerModifyItemSkuQuantity(long, long, Map, AppInfoDO)
	 */

	public void sellerIncreaseItemSkuQuantity() {
       printLine("调用用sellerIncreaseItemSkuQuantity方法");
		try {
			Map<Long, Integer> skuIncrementMap = new HashMap<Long, Integer>();
			 CreateItemResultDO re=publisItem(userId,true);
			 printLine(""+re.getErrorStr());
			ItemDO item=re.getItem();
			
			skuIncrementMap.put(new Long(item.getSkuList().get(0).getSkuId()), 4);
			 printLine(""+item.getSkuList().get(0).getSkuId());
		if(re.isSuccess()){	ProcessResultDO result = itemServiceClient
					.sellerIncreaseItemSkuQuantity(
							userId,
							item.getItemId(),
							skuIncrementMap, StringUtils.getUUID(),
							getLoomAppInfo());
			if (result.isSuccess()) {
				 printLine("调用用sellerIncreaseItemSkuQuantity方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} }catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

	/**
	 * 卖家设置爱心捐赠
	 * 
	 * @see ItemService#sellerSaveItemCharity(long,String, long)
	 */

	public void sellerSaveItemCharity() {
       printLine("调用sellerSaveItemCharity方法");
		long itemId = 1L;
		long cSellerId = UserDataConstants.C_普通c卖家L;
		try {
			itemServiceClient.sellerSaveItemCharity(itemId,
					";1:102;5:1;26:1;4:1122;", cSellerId);
			   printLine("调用sellerSaveItemCharity方法成功");
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    

	}

	/**
	 * 卖家设置邮费模板
	 * 
	 * @see ItemService#sellerSaveItemPostage(long , List, long,long ,long ,long
	 *      )
	 * 
	 */

	public void sellerSaveItemPostage() {
       printLine("调用sellerSaveItemPostage方法");
		try {
			List<Long> itemIdList = new ArrayList<Long>();
			Long itemId1 = publisItem(userId,false).getItem().getItemId();
			Long itemId2 = publisItem(userId,false).getItem().getItemId();
		       printLine("发布宝贝成功"+itemId1+"    "+itemId2);

			itemIdList.add(itemId1);
			itemIdList.add(itemId2);
			ShelfResultDO result = itemServiceClient
					.sellerSaveItemPostage(
							userId, itemIdList,
							1,
							IcDemoConstants.ITEM_ORDINARY_FEE,
							IcDemoConstants.ITEM_FAST_FEE, IcDemoConstants.ITEM_EMS_FEE);
             printLine(result.getErrorStr());
			if (result.isSuccess()) {
				  printLine("调用sellerSaveItemPostage方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 卖家设置橱窗推荐
	 * 
	 * @see ItemService#sellerSaveItemRecommed(long , List)
	 * 
	 */

	public void sellerSaveItemRecommed() {
		// 卖家设置橱窗推荐商品_卖家可推荐数量为5_推荐两件商品的场景
          printLine("调用sellerSaveItemRecommed方法");
		try {
			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(publisItem(userId,false).getItem().getItemId());
			itemIdList.add(publisItem(userId,false).getItem().getItemId());
			ShelfResultDO result = itemServiceClient.sellerSaveItemRecommed(
				 userId, itemIdList);
			if (result.isSuccess()) {
		          printLine("调用sellerSaveItemRecommed方法成功");

				// do something
			} else {
				// 打印错误信息O
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 卖家删除橱窗推荐
	 * 
	 * @see ItemService#sellerSaveItemUnRecommed(long , List)
	 * 
	 */

	public void sellerSaveItemUnRecommed() {
		 printLine("调用sellerSaveItemUnRecommed方法");
		try {
			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(publisItem(userId,false).getItem().getItemId());
			itemIdList.add(publisItem(userId,false).getItem().getItemId());
			ShelfResultDO result = itemServiceClient.sellerSaveItemUnRecommed(
					userId, itemIdList);
			if (result.isSuccess()) {
				 printLine("调用sellerSaveItemUnRecommed方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 卖家上架商品
	 * 
	 * @see ItemService#sellerUpShelfItem(long, List, List, AppInfoDO )
	 * 
	 */

	public void sellerUpShelfItem() {
		try {
			printLine("调用sellerUpShelfItem");
			long auctionId = itemId;
			Map<String, Object> fieldMap = new HashMap<String, Object>();
			fieldMap.put("starts", "date_sub( now() ,interval 7 day)");
			fieldMap.put("ends", "date_add( now() ,interval 7 day)");

			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(auctionId);
			List<Integer> quantities = new ArrayList<Integer>();
			quantities.add(1);
			ProcessResultDO result = itemServiceClient.sellerUpShelfItem(
					userId, itemIdList,
					quantities, AppInfoDO.UNKNOWN);

			if (result.isSuccess()) {
				printLine("调用sellerUpShelfItem方法成功");
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 卖家批量下架商品
	 * 
	 * @see ItemService#sellerDownShelfItem(long,List,AppInfoDO)
	 * 
	 */

	public void sellerDownShelfItem() {
		// 卖家批量下架商品_商品为一口价商品_卖家ID正确
       printLine("调用sellerDownShelfItem方法");
		try {
			List<Long> itemIds=new ArrayList<Long>();
			ItemDO item=publisItem(userId,false).getItem();
			itemIds.add(item.getItemId());
			ShelfResultDO result = itemServiceClient.sellerDownShelfItem(
					userId,itemIds,getAppInfoDOForSell());
			if (result.isSuccess()) {
				 printLine("调用sellerDownShelfItem方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除zoo字段中的属性 特定可以：给call.taobao.com应用使用，更新商品红包
	 * 
	 * @see ItemService#removeItemZoo(long,Map<Long, Long>)
	 * 
	 */

	public void removeItemZoo() {
		// 卖家取消下架商品的公益捐赠
		printLine("调用removeItemZoo方法");
		try {
			Map<Long, Long> zooPairList = new HashMap<Long, Long>();
			zooPairList.put(4L, 104L);
			zooPairList.put(1L, 102L);
			zooPairList.put(5L, 1L);
			ProcessResultDO result = itemServiceClient.removeItemZoo(itemId,
					zooPairList);

			if (result.isSuccess()) {
				printLine("调用removeItemZoo方法成功");

			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 更新zoo字段中的属性 特定可以：给call.taobao.com应用使用，更新商品红包
	 * 
	 * @see ItemService#updateItemZoo(long,Map<Long, Long>)
	 * 
	 */

	public void updateItemZoo() {
		// 卖家更新下架商品的公益捐赠
             printLine("调用updateItemZoo方法");
		try {
			Map<Long, Long> zooPairList = new HashMap<Long, Long>();
			zooPairList.put(4L, 104L);
			zooPairList.put(1L, 102L);
			zooPairList.put(5L, 1L);
			ProcessResultDO result = itemServiceClient.updateItemZoo(itemId,
					zooPairList);

			if (result.isSuccess()) {
				 printLine("调用updateItemZoo方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据卖家ID生成一个编码过的商品id，作用有两个： 商品发布的时候，不能让卖家输入校验码，为了避免卖家重复点击，发布多次，
	 * 预先生成一个ID，保存时会校验避免卖家串号。卖家在发布过程中，从旺旺等点击自动登陆
	 * 可能会切换为另外一个帐户，在encodeId中保存了生成时的用户ID，发布时会做校验，避免问题
	 * 
	 * @see ItemService#makeEncodeItemId(String)
	 * 
	 */

	public void makeEncodeItemId() {

		String sellerId = "test";

		ItemIdResultDO result = itemServiceClient.makeEncodeItemId(sellerId);

		if (result.isSuccess()) {

			// do something
		} else {
			// 打印错误信息
			System.out
					.println("----------------------------------------------");
			log.error(result.getErrorStr());
			System.out
					.println("----------------------------------------------");
			// do something
		}
	}

	/**
	 * 据encodeId和卖家ID进行解密，并进行判断，通过后返回商品ID，如果不通过，result返回false
	 * 
	 * @see ItemService#decodeItemId(String,String)
	 */
	public void decodeItemId() {

		String sellerId = "test";

		ItemIdResultDO itemIdResultDO = itemServiceClient
				.makeEncodeItemId(sellerId);

		String encodeId = itemIdResultDO.getEncodeId();

		ItemIdResultDO result = itemServiceClient.decodeItemId(encodeId,
				sellerId);

		if (result.isSuccess()) {

			// do something
		} else {
			// 打印错误信息
			System.out
					.println("----------------------------------------------");
			log.error(result.getErrorStr());
			System.out
					.println("----------------------------------------------");
			// do something
		}
	}

	/**
	 * 更新item的outerId和sku的outerId
	 * 
	 * @see ItemService#sellerModifyItemSkuOuterId(long, ItemUpdateDO ,
	 *      AppInfoDO )
	 */
	public void sellerModifyItemSkuOuterId() {
		// 卖家修改商品和SKU的OuterId字段均不为空
       printLine("调用sellerModifyItemSkuOuterId方法");
		try {
			long itemId = 1489596776L;
			String itemOuterId = "demoOuterId";
			String skuOuterId = "demoSkuOuterId";
			ItemUpdateDO itemUpdateDO = new ItemUpdateDO();
			itemUpdateDO.setItemId(itemId);
			itemUpdateDO.setOuterId(itemOuterId);
			List<ItemSkuDO> skuList = queryandUpdateSkuList(itemId, skuOuterId,
					false);
			itemUpdateDO.setSkuList(skuList);

			ProcessResultDO result = itemServiceClient
					.sellerModifyItemSkuOuterId(itemId, itemUpdateDO,
							getAppInfoDOForMckinley());

			if (result.isSuccess()) {
				printLine("调用sellerModifyItemSkuOuterId方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 卖家移动宝贝到我下架的分类
	 * 
	 * @see ItemService#sellerMoveItemToStock(long,List)
	 */
	public void sellerMoveItemToStock() {
   printLine("调用sellerMoveItemToStock方法");
		try {
			long itemId = publisItem(userId, false).getItem().getItemId();
			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(itemId);
			ShelfResultDO result = itemServiceClient.sellerMoveItemToStock(
					userId, itemIdList);
            if (result.isSuccess()) {
				printLine("调用sellerMoveItemToStock方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 卖家移动宝贝到我下架的分类
	 * 
	 * @see ItemService#sellerSaveNumberItemPreview(long,long ,ItemUpdateDO ,
	 *      String , SaveItemOptionDO )
	 */
	public void sellerSaveNumberItemPreview() {
		try {

			ItemUpdateDO inputItem = new ItemUpdateDO();
			inputItem.setCategoryId(IcDemoConstants.ITEM_CATEGORY_QQ);
			inputItem.setStarts(IcDemoConstants.ITEM_UPDATE_STARTS);

			SaveItemOptionDO saveItemOptionDO = new SaveItemOptionDO();
			saveItemOptionDO.setLang(SaveItemOptionDO.LANG_ZH_CN);
			saveItemOptionDO.setPreview(true);

			long itemId = 1l;
			SaveItemResultDO result = itemServiceClient
					.sellerSaveNumberItemPreview(itemId,
							UserDataConstants.C_普通c卖家L, inputItem,
							IcDemoConstants.ITEM_NUMBER_QQ1, saveItemOptionDO,
							getAppInfoDOForSell());
			if (result.isSuccess()) {

				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 设置商品直通车宝贝为直通车在线以及直通车不在线
	 * 
	 * @see ItemService#updateTaobaoSubway(long,int)
	 */
	public void updateTaobaoSubway_1() {
		printLine("调用updateTaobaoSubway_1方法");
		try {
			int P4Pflags = ItemConstants.POINT_PRICE_NOT_SUBWAY;// 非直通车
			// ItemConstantsPOINT_PRICE_SUBWAY_ONLINE 直通车在线
			// ItemConstants.POINT_PRICE_SUBWAY_NOT_ONLINE; 直通车不在线 --只能取这三个值之一
			ProcessResultDO result = itemServiceClient.updateTaobaoSubway(
					itemId, P4Pflags);// 设置为非直通车

			if (result.isSuccess()) {
				printLine("调用updateTaobaoSubway_1方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 设置商品为淘宝直通车非直通车宝贝
	 * 
	 * @see ItemService#updateTaobaoSubway(long,boolean)
	 */
	public void updateTaobaoSubway() {
		printLine("调用updateTaobaoSubway方法");
		try {

			ProcessResultDO result = itemServiceClient.updateTaobaoSubway(
					itemId, false);// 设置为非直通车

			if (result.isSuccess()) {
				printLine("调用updateTaobaoSubway方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 设置商品的促销
	 * 
	 * @see ItemService#sellerSaveItemBonus(long,String,long, long)
	 */
	public void sellerSaveItemBonus() {
      printLine("调用sellerSaveItemBonus方法");
		try {

			long itemId = publisItem(userId,false).getItem().getItemId();
			QueryItemOptionsDO options = new QueryItemOptionsDO();
			options.setIncludeSkus(true);
			 printLine(""+itemId);
			
			ProcessResultDO result = itemServiceClient.sellerSaveItemBonus(
					itemId, IcDemoConstants.ITEM_CHARITY_ZOO,
					userId,
					IcDemoConstants.ITEM_BONUS_OPTIONS);

			if (result.isSuccess()) {
				printLine("调用sellerSaveItemBonus方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 更新商品店内类目字段
	 * 
	 * @see ItemService#updateCategoryInShop(long,String)
	 */
	public void updateCategoryInShop() {
		try {
			printLine("调用updateCategoryInShop方法");
			Long itemId = 81000001L;
			String categoryId = "50010360";

			ProcessResultDO result = itemServiceClient.updateCategoryInShop(
					itemId, categoryId);

			if (result.isSuccess()) {
				printLine("调用updateCategoryInShop方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 新增或更新商品的单个图片。imageData里是否有有害内容，外部要校验
	 * 
	 * @see ItemService#sellerUploadCommonItemImage(long, long, ItemImageDO)
	 */
	public void sellerUploadCommonItemImage() {
		// 上传商品非主图
		printLine("调用sellerUploadCommonItemImage方法");
		try {
			String[] filenames=new String[]{"src/main/resources/itemServiceClient/test.jpg"};
			List<ItemImageDO> itemImage = ItemUtils.getImageList(filenames);
			itemImage.get(0).setImageData(getInputImage(filenames[0]));
			itemImage.get(0).setMajor(false);
			itemImage.get(0).setUserId(userId);

			ResultDO<ItemImageDO> result = itemServiceClient
					.sellerUploadCommonItemImage(itemId, userId, itemImage.get(0));

			if (result.isSuccess()) {
				printLine("调用sellerUploadCommonItemImage方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 新增或保存图片到tfs上
	 * 
	 * @see ItemService#saveImageToTfs(String, byte[], boolean)
	 */
	public void saveImageToTfs(){
		printLine("调用saveImageToTfs方法");

		
		byte[] imageData =getInputImage(fileName);
		boolean needMainColor = false;
		SavePictureFileResultDO result = itemServiceClient.saveImageToTfs(
				fileName, imageData, needMainColor);
		printLine(""+result.getResultCode());
		if (result.isSuccess()) {
			printLine("调用saveImageToTfs方法成功");
		} else {
			// 打印错误信息
			System.out
					.println("----------------------------------------------");
			log.error(result.getErrorStr());
			System.out
					.println("----------------------------------------------");
			// do something
		}
	}

	/**
	 * 卖家删除宝贝图片和属性图片
	 * 
	 * @see ItemService#sellerDelItemImage(long, long,long)
	 */
	public void sellerDelItemImage() {

          printLine("调用sellerDelItemImage方法");
      	ResultDO result = null; 
		try {
			 CreateItemResultDO cre=publish_多图_Item(2, false);
			 
			//准备删除参数
			long itemImageId =cre.getItem().getCommonItemImageList().get(0).getImageId();
			if(cre.isFailure()){
	             printLine("发布图片失败");
	             return ;
			}
			result 	= itemServiceClient.sellerDelItemImage(itemId,
					itemImageId, UserDataConstants.C_多图用户L);
            if (result.isSuccess()) {
				 printLine("调用sellerDelItemImage方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
		    	}
	    	} catch (IcException e) {
	    		System.out
				.println("----------------------------------------------");
		log.error(result.getErrorStr());
		System.out
				.println("----------------------------------------------");
		 }
	}

	/**
	 * 卖家删除宝贝图片和属性图片
	 * 
	 * @see ItemService#sellerUploadPropertyImage(long, long, ItemImageDO)
	 */
	public void sellerUploadPropertyImage() {
		printLine("调用sellerUploadPropertyImage方法");

		try {
			ItemImageDO itemImage = new ItemImageDO();
			itemImage.setItemId(itemId);
			itemImage.setType(2);
			itemImage.setImageUrl("test");
			itemImage.setProperties("20000:10681");
			ResultDO<ItemImageDO> result = itemServiceClient
					.sellerUploadPropertyImage(itemId, userId, itemImage);

			if (result.isSuccess()) {
				printLine("调用sellerUploadPropertyImage方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 更新使用了指定邮费模板的所有商品的邮费
	 * 
	 * @see ItemService#refreshItemPostage(List)
	 */
	public void refreshItemPostage() {
		// 更新单个商品邮费模板
         printLine("调用refreshItemPostage");
		Long postageId = 1L;
		Long sellerId = UserDataConstants.C_普通c卖家L;
		List<UpdatedPostageDO> list = new ArrayList<UpdatedPostageDO>();
		UpdatedPostageDO updatedPostageDO = new UpdatedPostageDO();
		updatedPostageDO.setOrdinaryMinFee(20);
		updatedPostageDO.setEmsMinFee(20);
		updatedPostageDO.setFastMinFee(20);
		updatedPostageDO.setPostageId(postageId);
		updatedPostageDO.setUserId(sellerId);
		updatedPostageDO.setDeleted(false);
		list.add(updatedPostageDO);
		int result = itemServiceClient.refreshItemPostage(list);
		printLine("调用refreshItemPostage成功");
	}

	/**
	 * 批量删除视频关联
	 * 
	 * @see ItemService#delVideoItems(List)
	 */
	public void delVideoItems() {
		printLine("调用delVideoItems方法");
		try {

			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(1L);
			itemIdList.add(2L);
			BatchItemVideoResultDO result = itemServiceClient
					.delVideoItems(itemIdList);
			if (result.isSuccess()) {
				printLine("调用delVideoItems方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据视频id列表和isv id来删除视频关联表中的记录
	 * 
	 * @see ItemService#delVideoItemByVideoId(Long[],long)
	 */
	public void delVideoItemByVideoId() {
		// 视频数据都在icmisc中_删除10条数据

		try {
			 long[] videoIds = publishItemVideo(10);
			 long isvId = 888;
			 BatchItemVideoResultDO result = itemServiceClient.delVideoItemByVideoId(videoIds, isvId);
			if (result.isSuccess()) {

				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	/**
	 * 为对应的商品增加、修改或删除的features属性
	 * 
	 * @see ItemService#saveItemFeatures(Map,Map)
	 */
	public void saveItemFeatures() {
		// 视为一件商品修改多个feature
      printLine("调用saveItemFeatures方法");
		try {

			Map<Long, List<FeatureDO>> updateFeaturesMap = new HashMap<Long, List<FeatureDO>>();
			List<FeatureDO> features = new ArrayList<FeatureDO>();
			features.add(createFeatureDO("shop", "0"));
			features.add(createFeatureDO("sp", "0"));
			features.add(createFeatureDO("inv", "1"));
			updateFeaturesMap.put(1L, features);
			Map<Long, List<String>> removeFeaturesKeyMap = null;
			ProcessResultDO result = itemServiceClient.saveItemFeatures(
					updateFeaturesMap, removeFeaturesKeyMap);

			if (result.isSuccess()) {
				  printLine("调用saveItemFeatures方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 更新商品累计售出量 为B商品使用，计算累计售出量C商品需要计算最近30天内
	 * 售出，需要调用TcReadService.sumAuctionQuantity计算
	 * 
	 * @see ItemService#updateSumAuctionQuantity(Long,int)
	 */
	public void updateSumAuctionQuantity() {
       printLine("调用updateSumAuctionQuantity方法");
		try {

			int auctionQuantity = 100;
			BaseResultDO result = itemServiceClient.updateSumAuctionQuantity(
					itemId, auctionQuantity);

			if (result.isSuccess()) {
				 printLine("调用updateSumAuctionQuantity方法成功");
				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 依据用户字符串id生成item数字id，并保证用户字符串id与商品数字id的奇偶映 射关系
	 * 
	 * @see ItemService#generateItemId(String, AppinfoDO)
	 */
	public void generateItemId() {

		try {
			long result = itemServiceClient.generateItemId("demo",
					getOtherAppInfo());
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 创建宝贝sku分仓
	 * 
	 * @see ItemService#sellerCreateSkuStore(long, SkuStoreDO , AppInfoDO)
	 * 
	 * @deprecated
	 */
	public void sellerCreateSkuStore() {

		try {

			long sellerId = 1l;
			SkuStoreDO skuStore = new SkuStoreDO();
			BaseResultDO result = itemServiceClient.sellerCreateSkuStore(
					sellerId, skuStore, getLoomAppInfo());

			if (result.isSuccess()) {

				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 创建宝贝分仓
	 * 
	 * @see ItemService#sellerCreateAuctionStore(long, AuctionStoreDO ,
	 *      AppInfoDO)
	 * 
	 * @deprecated
	 */
	public void sellerCreateAuctionStore() {

		try {

			long sellerId = 1l;
			AuctionStoreDO autionStore = new AuctionStoreDO();
			BaseResultDO result = itemServiceClient.sellerCreateAuctionStore(
					sellerId, autionStore, getLoomAppInfo());

			if (result.isSuccess()) {

				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除宝贝分仓
	 * 
	 * @see ItemService#sellerDeleteAuctionStore(long,List , AppInfoDO)
	 * 
	 * @deprecated
	 */
	public void sellerDeleteAuctionStore() {

		try {

			long sellerId = 1l;
			List<AuctionStoreIdDO> autionStore = new ArrayList<AuctionStoreIdDO>();
			BaseResultDO result = itemServiceClient.sellerDeleteAuctionStore(
					sellerId, autionStore, getLoomAppInfo());

			if (result.isSuccess()) {

				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 全量同步宝贝分仓库存
	 * 
	 * @see 
	 *      ItemService#sellerModifyAuctionStoreQuantity(long,Map,Integer>,AppInfoDO
	 *      )
	 * 
	 * @deprecated
	 */
	public void sellerModifyAuctionStoreQuantity() {
		// 批量更新宝贝多个分仓quantity

		try {
			long sellerId = UserDataConstants.C_普通c卖家L;

			Map<AuctionStoreIdDO, Integer> auctionStoreQuantityMap = new HashMap<AuctionStoreIdDO, Integer>();
			Long itemId = 1L;
			AuctionStoreDO auctionStore2 = createAuctionStore(itemId,
					"STORE_yulinTestStore02", 0, 2, sellerId);
			AuctionStoreIdDO auctionStoreIdDO2 = new AuctionStoreIdDO(
					auctionStore2.getAuctionId(), auctionStore2.getSkuId(),
					auctionStore2.getStoreCode());
			auctionStoreQuantityMap.put(auctionStoreIdDO2, 8);

			BatchResultDO<AuctionStoreIdDO> result = itemServiceClient
					.sellerModifyAuctionStoreQuantity(sellerId,
							auctionStoreQuantityMap, getUnknowAppInfo());

			if (result.isSuccess()) {

				// do something
			} else {
				// 打印错误信息
				System.out
						.println("----------------------------------------------");
				log.error(result.getErrorStr());
				System.out
						.println("----------------------------------------------");
				// do something
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public itemServiceClientDemo() {

		String[] location = { "itemServiceClient/spring-ic-hsf.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(
				location);
		itemServiceClient = (ItemServiceClient) context
				.getBean("itemServiceClient");
		
		String[] location2 = { "itemQueryServiceClient/spring-ic-hsf.xml" };
		ApplicationContext context2 = new ClassPathXmlApplicationContext(
				location2);
		itemQueryServiceClient = (ItemQueryServiceClient) context2
				.getBean("itemQueryServiceClient");
		

	}

	public static void main(String[] arg) {	
//		new ExtractColor();
		itemServiceClientDemo itemServiceClien=new itemServiceClientDemo();
 		//itemServiceClien.publishNumberRangeItem();
		//itemServiceClien.publishItem();
        //itemServiceClien.publishItemWithOutUserPreview();
//        itemServiceClien.delVideoItemByVideoId();
		//itemServiceClien.delVideoItems();
		//itemServiceClien.modifyItemCollectionCount();
		//itemServiceClien.refreshItemPostage();
	    // itemServiceClien.removeItemOptions();
	    //itemServiceClien.removeItemZoo();
	    itemServiceClien.saveImageToTfs();
		//itemServiceClien.saveItemFeatures();
      	//itemServiceClien.sellerDelItem();
//		  itemServiceClien.sellerDelItemImage();
		//itemServiceClien.sellerDownShelfItem();
//		  itemServiceClien.sellerIncreaseItemSkuQuantity;//哥哥真的不想发sku了  
	    //itemServiceClien.sellerModifyItemQuantity();
		//itemServiceClien.sellerModifyItemSkuOuterId();
     	//itemServiceClien.sellerModifyItemSkuQuantity();
// 		itemServiceClien.sellerMoveItemToStock();
        //itemServiceClien.sellerSaveItem();
		//itemServiceClien.sellerSaveItemBonus();
		//itemServiceClien.sellerSaveItemCharity();
 	    //itemServiceClien.sellerSaveItemPostage();
	    //itemServiceClien.sellerSaveItemRecommed();
		//itemServiceClien.sellerSaveItemUnRecommed();
	    //itemServiceClien.sellerSaveNumberItem();
		//itemServiceClien.sellerUploadCommonItemImage();//、、、、、、、、、、、、、
		//itemServiceClien.sellerUploadPropertyImage();
		//itemServiceClien.sellerUpShelfItem();
		//itemServiceClien.updateCategoryInShop();
		//itemServiceClien.updateItemZoo();
		//itemServiceClien.updateSumAuctionQuantity();
		//itemServiceClien.updateTaobaoSubway();
		//itemServiceClien.updateTaobaoSubway_1();
	}

	/**
	 * 获取loom 应用的appInfo ,在实际的应用场景中 opertion,memo,operator 最好设置和业务相关的参数
	 * 
	 * @return
	 */
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
	private static AppInfoDO getOtherAppInfo() {

		AppInfoDO infoDO = new AppInfoDO();
		infoDO.setAppName(AppInfoConstants.NAME_OTHER);
		infoDO.setOperation("demo");
		infoDO.setMemo("demo");
		infoDO.setOperator("demo");
		return infoDO;

	}

	private static AppInfoDO getAppInfoDOForSell() {
		return new AppInfoDO(AppInfoConstants.NAME_SELL, "demo", "demo");
	}

	private static AppInfoDO getUnknowAppInfo() {

		AppInfoDO infoDO = new AppInfoDO();
		infoDO.setAppName(AppInfoConstants.NAME_UNKNOWN);
		infoDO.setOperation("demo");
		infoDO.setMemo("demo");
		infoDO.setOperator("demo");
		return infoDO;

	}

	private static AppInfoDO getAppInfoDOForMckinley() {
		return new AppInfoDO(AppInfoConstants.NAME_MCKINLEY, "test", "test");
	}

	private List<ItemSkuDO> queryandUpdateSkuList(Long itemId,
			String skuOuterId, boolean updateSkuQuantityPirce)
			throws IcException {
		List<ItemSkuDO> dbSkuList = null;
		List<ItemSkuDO> skuList = new ArrayList<ItemSkuDO>();

		ResultDO<ItemDO> result = itemQueryServiceClient
				.queryItemForDetail(itemId);
		dbSkuList = result.getModule().getSkuList();
		for (ItemSkuDO itemSkuDO : dbSkuList) {
			itemSkuDO.setOuterId(skuOuterId);
			if (updateSkuQuantityPirce) {
				itemSkuDO.setQuantity(5);
				itemSkuDO.setPrice(30000L);
			}
			skuList.add(itemSkuDO); // 更新skuList
		}
		return skuList;
	}

	private FeatureDO createFeatureDO(String key, String value) {
		FeatureDO feature = new FeatureDO();
		feature.setKey(key);
		feature.setValue(value);
		return feature;
	}

	private static AuctionStoreDO createAuctionStore(Long auctionId,
			String storeCode, int status, int quantity, long sellerId) {
		AuctionStoreDO auctionStore = new AuctionStoreDO();
		auctionStore.setAuctionId(auctionId);
		auctionStore.setSellerId(sellerId);
		auctionStore.setStatus(status);
		auctionStore.setStoreCode(storeCode);
		auctionStore.setQuantity(quantity);

		return auctionStore;
	}
	private static CreateItemResultDO publish_多图_Item(int imageQuantity, boolean hasVideo) throws IcException{
		ItemDO item = ItemUtils.createItemDO(userId);
		item.setCategoryId(105L);
		if(imageQuantity > 0){
			List<ItemImageDO> commonImageList = new ArrayList<ItemImageDO>();
			for(int i=0; i<imageQuantity; i++){
				ItemImageDO image = ItemUtils.createItemImageDO(userId, 0, 1, true,fileName);
				commonImageList.add(image);
			}
			item.setCommonItemImageList(commonImageList);
		}

		if(hasVideo){
			List<ItemVideoDO> videoList = new ArrayList<ItemVideoDO>();
			videoList.add(createVideoDO());
			item.setVideoList(videoList);
		}

		PublishItemOptionDO pubProcessOption = ItemUtils.createPublishItemOptionDO();
		CreateItemResultDO pubResult = itemServiceClient.publishItem(item, pubProcessOption, getAppInfoDOForSell());
		
		Assert.assertNotNull(pubResult);
		ItemDO dbItem = pubResult.getItem();
		itemId = dbItem.getItemId();
//		itemStr = dbItem.getItemIdStr();
		if(dbItem.getSpu() != null){
			spuid = dbItem.getSpu().getId();
		}
		if(imageQuantity > 1){
			Assert.assertTrue(dbItem.isDuotuItem());
		}else{
			Assert.assertFalse(dbItem.isDuotuItem());
		}

		if(hasVideo){
			Assert.assertTrue(dbItem.isVideoItem());
		}else{
			Assert.assertFalse(dbItem.isVideoItem());
		}

		return pubResult;
	}
	private static ItemVideoDO createVideoDO(){
		ItemVideoDO video = new ItemVideoDO();
		video.setIsvId(1234);
		video.setStatus(0);
		video.setVideoUrl("url");
		video.setVideoId(1234);
		video.setUserId(UserDataConstants.C_多图用户L);
		return video;
	}
	private static byte[] getInputImage(String filename) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		FileInputStream inputFile = null;
		try {
			inputFile =new FileInputStream(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			
			int b = 0;
			while ((b = inputFile.read()) != -1) {
				baos.write(b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}
   
 private static CreateItemResultDO publisItem(Long userId,boolean includeSku){
	  ItemDO item = ItemUtils.createItemDO(userId);
		// item.setXXX(); 添加商品的一系列属性 相关信息条件请参考API文档
		item.setCategoryId(IcDemoConstants.ITEM_CATEGORY_FOOD);
		item.setProperty("1930001:27772");
		item.setSpuId(0L);
		item.setAuctionPoint(5);
		
		// 发布商品时，杂七杂八但又需要的数据往这里加，比如发布时的用户ip,此次发布是否需要预览，是否返回SPU信息等等
		PublishItemOptionDO publicItemOption=ItemUtils.createPublishItemOptionDO();
		publicItemOption.setLang("zh_CN"); // 违规关键字校验的客服端语言，此处设置为简体中文，zh_HK繁体中文，
		

		
		List<ItemSkuDO> skuList = new ArrayList<ItemSkuDO>();
		if (includeSku) {
		    item.setOptionsHasSku(true);
		    //item.setCategoryId(50017987L);
			item.setMinimumBid(100L);
			item.setReservePrice(100L);
	        skuList.add(createSku(2, "1627207:28320", 10L,userId));
			skuList.add(createSku(3, "1627207:28321", 20L,userId));
			skuList.add(createSku(4, "1627207:28322", 30L,userId));
			item.setProperty("1627207:28320;1627207:28321;1627207:28322");
		}
	
		item.setSkuList(skuList);
		
     CreateItemResultDO result = null;
	try {
		result = itemServiceClient.publishItem(item,
					publicItemOption, getAppInfoDOForSell());
	} catch (IcException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   return  result;
	 
 }
	private static ItemSkuDO createSku(int quantity, String property, Long price,Long sellerId) {
		ItemSkuDO sku = new ItemSkuDO();
		sku.setPrice(price);
		sku.setProperties(property);
		sku.setQuantity(quantity);
		sku.setSellerId(sellerId);
		return sku;
		}
	
	private long[] publishItemVideo(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 打印一行，带标题
	 * 
	 * @param title
	 *            标题
	 */
	private static void printLine(String title) {
		System.out
				.println(String
						.format("-------------------------------------------%s-------------------------------------------\n",
								title));
	}

}
