package com.taobao.itemcenter.demo.itemserviceclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.common.logging.LoggerFactory;
import com.taobao.item.constant.AppInfoConstants;
import com.taobao.item.constant.ItemConstants;
import com.taobao.item.domain.AppInfoDO;
import com.taobao.item.domain.AuctionStoreDO;
import com.taobao.item.domain.ItemDO;
import com.taobao.item.domain.ItemImageDO;
import com.taobao.item.domain.ItemSkuDO;
import com.taobao.item.domain.ItemUpdateDO;
import com.taobao.item.domain.PublishItemOptionDO;
import com.taobao.item.domain.SaveItemOptionDO;
import com.taobao.item.domain.SkuStoreDO;
import com.taobao.item.domain.UpdatedPostageDO;
import com.taobao.item.domain.query.AuctionStoreIdDO;
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
import com.taobao.item.service.client.ItemServiceClient;
import com.taobao.item.util.StringUtils;
import com.taobao.itemcenter.demo.itemqueryserviceclient.ItemQueryServiceClientDemo;
import com.taobao.itemcenter.demo.utils.IcDemoConstants;
import com.taobao.itemcenter.demo.utils.ItemUtils;
import com.taobao.itemcenter.demo.utils.UserDataConstants;

public class itemServiceClienDemo {

	private Log log = LoggerFactory.getLogger(this.getClass());
	private List<Long> itemIdList = new ArrayList<Long>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4576510935105518152L;
        {
			add(1500003118097L);
		}
	};
	private long itemId = 1500003118097L;

	private long sellerId = 77888896L;
	private long userId = 77888896L;
	private ItemServiceClient itemServiceClient;
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
		List<Long> options = new ArrayList<Long>();
		options.add(1L);// 去除宝贝 参与会员打折的 属性
		options.add(10L);// 去除宝贝 是商城宝贝的 属性
		try {
			ProcessResultDO result = itemServiceClient.removeItemOptions(
					itemId, options, getLoomAppInfo());
			if (result.isSuccess()) {

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

		try {
			ItemDO item = ItemUtils.createItemDO(UserDataConstants.C_五星c卖家L);
			// item.setXXX(); 添加商品的一系列属性 相关信息条件请参考API文档
			item.setCategoryId(IcDemoConstants.ITEM_CATEGORY_FOOD);
			item.setProperty("1930001:27772");
			item.setSpuId(0L);
			item.setQuantity(1);
			item.setAuctionPoint(5);
			
			
			

			// 发布商品时，杂七杂八但又需要的数据往这里加，比如发布时的用户ip,此次发布是否需要预览，是否返回SPU信息等等
			PublishItemOptionDO publicItemOption=ItemUtils.createPublishItemOptionDO();
			publicItemOption.setLang("zh_CN"); // 违规关键字校验的客服端语言，此处设置为简体中文，zh_HK繁体中文，
            CreateItemResultDO result = itemServiceClient.publishItem(item,
					publicItemOption, getAppInfoDOForSell());
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

		try {
			ItemDO item = ItemUtils.createItemDO(UserDataConstants.B_商家L);
			// item.setXXX(); 添加商品的一系列属性 相关信息条件请参考API文档
			item.setCategoryId(IcDemoConstants.ITEM_CATEGORY_FOOD);
			item.setSpuId(0L);
			item.setAuctionPoint(5);

			// 发布商品时，杂七杂八但又需要的数据往这里加，比如发布时的用户ip,此次发布是否需要预览，是否返回SPU信息等等
			PublishItemOptionDO publicItemOption = new PublishItemOptionDO();
			publicItemOption.setLang("zh_CN"); // 违规关键字校验的客服端语言，此处设置为简体中文，zh_HK繁体中文，

			CreateItemResultDO result = itemServiceClient
					.publishItemWithOutUserPreview(item, publicItemOption,
							getLoomAppInfo());
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
	 * 发布号码库宝贝, 所谓号码库宝贝是类似qq号码这样的宝贝, 一次提交多个qq号码, 但是每个qq号码生成一个宝贝, 因此一次调用, 生成多个宝贝
	 * 
	 * @see ItemService#publishNumberRangeItem(ItemDO,
	 *      String,PublishItemOptionDO)
	 */
	public void publishNumberRangeItem() {
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
			CreateItemResultDO result = itemServiceClient
					.publishNumberRangeItem(item, number, publicItemOption,
							getLoomAppInfo());
			if (result.isSuccess()) {
				System.out
				.println("-------------------------发布数码宝贝成功---------------------"+result.getItem());
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

			SaveItemResultDO result = itemServiceClient.sellerSaveItem(itemId,
					UserDataConstants.C_卖家自动发货权限L, inputItem,
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

		try {
			// 编辑卡密商品
			long id = itemServiceClient.generateItemId(
					IcDemoConstants.SELLER_ID_SVAE_NUMBER + "",
					getAppInfoDOForSell());

			ItemUpdateDO inputItem = new ItemUpdateDO();
			inputItem.setStuffStatus(IcDemoConstants.ITEM_IDEL);
			inputItem.setStarts(IcDemoConstants.ITEM_UPDATE_STARTS);
			SaveItemOptionDO saveItemOptionDO = new SaveItemOptionDO();
			saveItemOptionDO.setLang(SaveItemOptionDO.LANG_ZH_CN);
			SaveItemResultDO result = itemServiceClient.sellerSaveNumberItem(
					id, IcDemoConstants.SELLER_ID_SVAE_NUMBER, inputItem,
					"443843849", saveItemOptionDO, getAppInfoDOForSell());
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
		Long itemId = 81120001L;
		long collectionCount = 4;
		ProcessResultDO result;
		try {
			result = itemServiceClient.modifyItemCollectionCount(itemId,
					collectionCount);
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
	 * 卖家删除商品
	 * 
	 * @see ItemService#sellerDelItem(long, List,AppInfoDO)
	 */
	public void sellerDelItem() {

		try {
			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(100005529637L);
			itemIdList.add(100005529639L);

			ShelfResultDO result = itemServiceClient.sellerDelItem(
					IcDemoConstants.SELLER_ID_COMM_LONG, itemIdList,
					getUnknowAppInfo());

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
	 * 卖家设置商品数量
	 * 
	 * @see ItemService#sellerModifyItemQuantity(long, int,long,AppInfoDO)
	 */
	public void sellerModifyItemQuantity() {

		try {
			ProcessResultDO result = itemServiceClient
					.sellerModifyItemQuantity(
							IcDemoConstants.ITEM_QUANTITY_ITEMID_AUTO, 3,
							IcDemoConstants.SELLER_ID_COMM_LONG,
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
		try {

			Map<Long, Integer> skuQuantityMap = new HashMap<Long, Integer>();
			skuQuantityMap.put(1L, 4);
			skuQuantityMap.put(2L, 4);

			ProcessResultDO result = itemServiceClient
					.sellerModifyItemSkuQuantity(
							IcDemoConstants.SELLER_ID_COMM_LONG,
							IcDemoConstants.ITEM_QUANTITY_ITEMID_AUTO,
							skuQuantityMap, getLoomAppInfo());
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
	 * 增量修改指定商品sku库存
	 * 
	 * @see ItemService#sellerModifyItemSkuQuantity(long, long, Map, AppInfoDO)
	 */

	public void sellerIncreaseItemSkuQuantity() {

		try {
			Map<Long, Integer> skuIncrementMap = new HashMap<Long, Integer>();
			skuIncrementMap.put(1L, 4);
			skuIncrementMap.put(2L, 4);

			ProcessResultDO result = itemServiceClient
					.sellerIncreaseItemSkuQuantity(
							IcDemoConstants.SELLER_ID_COMM_LONG,
							IcDemoConstants.ITEM_QUANTITY_ITEMID_AUTO,
							skuIncrementMap, StringUtils.getUUID(),
							getLoomAppInfo());
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
	 * 卖家设置爱心捐赠
	 * 
	 * @see ItemService#sellerSaveItemCharity(long,String, long)
	 */

	public void sellerSaveItemCharity() {

		long itemId = 1L;
		long cSellerId = UserDataConstants.C_普通c卖家L;
		try {
			itemServiceClient.sellerSaveItemCharity(itemId,
					";1:102;5:1;26:1;4:1122;", cSellerId);
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

		try {
			List<Long> itemIdList = new ArrayList<Long>();
			Long itemId1 = 100005529637L;
			Long itemId2 = 100005529638L;
			itemIdList.add(itemId1);
			itemIdList.add(itemId2);
			ShelfResultDO result = itemServiceClient
					.sellerSaveItemPostage(
							IcDemoConstants.SELLER_ID_COMM_BASE_LONG,
							itemIdList,
							IcDemoConstants.ITEM_POSTAGE_ID_BASE_LONG,
							IcDemoConstants.ITEM_ORDINARY_FEE,
							IcDemoConstants.ITEM_FAST_FEE,
							IcDemoConstants.ITEM_EMS_FEE);

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
	 * 卖家设置橱窗推荐
	 * 
	 * @see ItemService#sellerSaveItemRecommed(long , List)
	 * 
	 */

	public void sellerSaveItemRecommed() {
		// 卖家设置橱窗推荐商品_卖家可推荐数量为5_推荐两件商品的场景

		try {
			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(1L);
			itemIdList.add(2L);
			ShelfResultDO result = itemServiceClient.sellerSaveItemRecommed(
					IcDemoConstants.SELLER_ID_AUTH_LONG, itemIdList);
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
	 * 卖家删除橱窗推荐
	 * 
	 * @see ItemService#sellerSaveItemUnRecommed(long , List)
	 * 
	 */

	public void sellerSaveItemUnRecommed() {
		try {
			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(1L);
			itemIdList.add(2L);
			ShelfResultDO result = itemServiceClient.sellerSaveItemUnRecommed(
					IcDemoConstants.SELLER_ID_AUTH_LONG, itemIdList);
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
	 * 卖家上架商品
	 * 
	 * @see ItemService#sellerUpShelfItem(long, List, List, AppInfoDO )
	 * 
	 */

	public void sellerUpShelfItem() {
		try {

			long auctionId = 100005529673L;

			Map<String, Object> fieldMap = new HashMap<String, Object>();
			fieldMap.put("starts", "date_sub( now() ,interval 7 day)");
			fieldMap.put("ends", "date_add( now() ,interval 7 day)");

			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(auctionId);
			List<Integer> quantities = new ArrayList<Integer>();
			quantities.add(1);
			ProcessResultDO result = itemServiceClient.sellerUpShelfItem(
					IcDemoConstants.SELLER_ID_COMM_LONG2, itemIdList,
					quantities, AppInfoDO.UNKNOWN);

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
	 * 卖家批量下架商品
	 * 
	 * @see ItemService#sellerDownShelfItem(long,List,AppInfoDO)
	 * 
	 */

	public void sellerDownShelfItem() {
		// 卖家批量下架商品_商品为一口价商品_卖家ID正确

		try {
			List<Long> itemIds = new ArrayList<Long>();
			itemIds.add(100005529645L);

			ShelfResultDO result = itemServiceClient.sellerDownShelfItem(
					IcDemoConstants.SELLER_ID_COMM_LONG, itemIds,
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
	 * 删除zoo字段中的属性 特定可以：给call.taobao.com应用使用，更新商品红包
	 * 
	 * @see ItemService#removeItemZoo(long,Map<Long, Long>)
	 * 
	 */

	public void removeItemZoo() {
		// 卖家取消下架商品的公益捐赠

		try {
			Map<Long, Long> zooPairList = new HashMap<Long, Long>();
			zooPairList.put(4L, 104L);
			zooPairList.put(1L, 102L);
			zooPairList.put(5L, 1L);
			long itemId = 1L;
			ProcessResultDO result = itemServiceClient.removeItemZoo(itemId,
					zooPairList);

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
	 * 更新zoo字段中的属性 特定可以：给call.taobao.com应用使用，更新商品红包
	 * 
	 * @see ItemService#updateItemZoo(long,Map<Long, Long>)
	 * 
	 */

	public void updateItemZoo() {
		// 卖家更新下架商品的公益捐赠

		try {
			Map<Long, Long> zooPairList = new HashMap<Long, Long>();
			zooPairList.put(4L, 104L);
			zooPairList.put(1L, 102L);
			zooPairList.put(5L, 1L);
			long itemId = 1L;
			ProcessResultDO result = itemServiceClient.updateItemZoo(itemId,
					zooPairList);

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

		try {
			long itemId = 1L;
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

		try {
			long itemId = 1L;
			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(IcDemoConstants.ITEM_DEL_ITEMID_ERROR);
			ShelfResultDO result = itemServiceClient.sellerMoveItemToStock(
					IcDemoConstants.SELLER_ID_COMM_LONG, itemIdList);

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
		try {

			long itemId = 1L;
			int P4Pflags = ItemConstants.POINT_PRICE_NOT_SUBWAY;// 非直通车
			// ItemConstantsPOINT_PRICE_SUBWAY_ONLINE 直通车在线
			// ItemConstants.POINT_PRICE_SUBWAY_NOT_ONLINE; 直通车不在线 --只能取这三个值之一
			ProcessResultDO result = itemServiceClient.updateTaobaoSubway(
					itemId, P4Pflags);// 设置为非直通车

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
	 * 设置商品为淘宝直通车非直通车宝贝
	 * 
	 * @see ItemService#updateTaobaoSubway(long,boolean)
	 */
	public void updateTaobaoSubway() {
		try {

			long itemId = 1L;
			ProcessResultDO result = itemServiceClient.updateTaobaoSubway(
					itemId, false);// 设置为非直通车

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
	 * 设置商品的促销
	 * 
	 * @see ItemService#sellerSaveItemBonus(long,String,long, long)
	 */
	public void sellerSaveItemBonus() {

		try {

			long itemId = IcDemoConstants.ITEM_UPDATE_ITEM_AUCTION_ID;
			ProcessResultDO result = itemServiceClient.sellerSaveItemBonus(
					itemId, IcDemoConstants.ITEM_CHARITY_ZOO,
					IcDemoConstants.SELLER_ID_CHARITY,
					IcDemoConstants.ITEM_BONUS_OPTIONS);

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
	 * 更新商品店内类目字段
	 * 
	 * @see ItemService#updateCategoryInShop(long,String)
	 */
	public void updateCategoryInShop() {
		try {
			Long itemId = 81000001L;
			String categoryId = "50010360";

			ProcessResultDO result = itemServiceClient.updateCategoryInShop(
					itemId, categoryId);

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
	 * 新增或更新商品的单个图片。imageData里是否有有害内容，外部要校验
	 * 
	 * @see ItemService#sellerUploadCommonItemImage(long, long, ItemImageDO)
	 */
	public void sellerUploadCommonItemImage() {
		// 上传商品非主图
		try {
			long sellerId = UserDataConstants.C_多图用户L;
			long itemId = 1L;
			ItemImageDO itemImage = ItemUtils.createItemImageDO(sellerId,
					itemId, 1, false);
			itemImage.setMajor(false);

			ResultDO<ItemImageDO> result = itemServiceClient
					.sellerUploadCommonItemImage(itemId, sellerId, itemImage);

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
	 * 新增或保存图片到tfs上
	 * 
	 * @see ItemService#saveImageToTfs(String, byte[], boolean)
	 */
	public void saveImageToTfs() throws IcException {
		String fileName = "demo";
		byte[] imageData = null;
		boolean needMainColor = false;
		SavePictureFileResultDO result = itemServiceClient.saveImageToTfs(
				fileName, imageData, needMainColor);
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
	 * 卖家删除宝贝图片和属性图片
	 * 
	 * @see ItemService#sellerDelItemImage(long, long,long)
	 */
	public void sellerDelItemImage() {

		try {
			long sellerId = UserDataConstants.C_多图用户L;
			long itemId = 1L;
			long itemImageId = 3L;
			;
			@SuppressWarnings("rawtypes")
			ResultDO result = itemServiceClient.sellerDelItemImage(itemId,
					itemImageId, sellerId);

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
	 * 卖家删除宝贝图片和属性图片
	 * 
	 * @see ItemService#sellerUploadPropertyImage(long, long, ItemImageDO)
	 */
	public void sellerUploadPropertyImage() {

		try {

			Long itemId = 0L;
			Long sellerId = UserDataConstants.C_多图用户L;
			ItemImageDO itemImage = new ItemImageDO();
			itemImage.setItemId(itemId);
			itemImage.setType(2);
			itemImage.setImageUrl("test");
			itemImage.setProperties("20000:10681");
			ResultDO<ItemImageDO> result = itemServiceClient
					.sellerUploadPropertyImage(itemId, sellerId, itemImage);

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
	 * 更新使用了指定邮费模板的所有商品的邮费
	 * 
	 * @see ItemService#refreshItemPostage(List)
	 */
	public void refreshItemPostage() {
		// 更新单个商品邮费模板

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
	}

	/**
	 * 批量删除视频关联
	 * 
	 * @see ItemService#delVideoItems(List)
	 */
	public void delVideoItems() {

		try {

			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(1L);
			itemIdList.add(2L);
			BatchItemVideoResultDO result = itemServiceClient
					.delVideoItems(itemIdList);
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
	 * 根据视频id列表和isv id来删除视频关联表中的记录
	 * 
	 * @see ItemService#delVideoItemByVideoId(Long[],long)
	 */
	public void delVideoItemByVideoId() {
		// 视频数据都在icmisc中_删除10条数据

		try {
			long isvId = 888;
			long[] videoIds = new long[10];
			for (int i = 0; i < 10; i++) {
				videoIds[i] = i;

			}
			BatchItemVideoResultDO result = itemServiceClient
					.delVideoItemByVideoId(videoIds, isvId);
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

		try {

			Long itemNumId = 1L;
			int auctionQuantity = 100;
			BaseResultDO result = itemServiceClient.updateSumAuctionQuantity(
					itemNumId, auctionQuantity);

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

	public itemServiceClienDemo() {

		String[] location = { "itemServiceClient/spring-ic-hsf.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(
				location);
		itemServiceClient = (ItemServiceClient) context
				.getBean("itemServiceClient");

	}

	public static void main(String[] arg) {

		new itemServiceClienDemo().publishNumberRangeItem();

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
		ItemQueryServiceClientDemo itemQueryServiceClientDemo = new ItemQueryServiceClientDemo();

		ResultDO<ItemDO> result = itemQueryServiceClientDemo
				.queryItemForDetail(itemId);
		Assert.assertNotNull(result);
		dbSkuList = result.getModule().getSkuList();
		Assert.assertNotNull(dbSkuList);
		Assert.assertTrue(dbSkuList.size() >= 1);
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
}
