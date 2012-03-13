package com.taobao.itemcenter.demo.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.item.constant.AppInfoConstants;
import com.taobao.item.domain.AppInfoDO;
import com.taobao.item.domain.ItemDO;
import com.taobao.item.domain.ItemImageDO;
import com.taobao.item.domain.PublishItemOptionDO;
import com.taobao.item.domain.query.QueryItemOptionsDO;
import com.taobao.item.domain.result.ItemResultDO;
import com.taobao.item.exception.IcException;
import com.taobao.item.service.client.ItemQueryServiceClient;


/**
 * 商品工具类，可以用于获取宝贝信息
 * 
 * @author tieyi.qlr//tianshi
 * 
 */
public class ItemUtils {

	
	private static ItemQueryServiceClient itemQueryServiceClient;

	static {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"itemQueryServiceClient/spring-ic-hsf.xml");
		itemQueryServiceClient = (ItemQueryServiceClient) ac
				.getBean("itemQueryServiceClient");
	}

	/**
	 * 获取简单的宝贝对象
	 * @param itemId 宝贝ID
	 * @param hasSku 是否包含sku
	 * @return
	 */
	public static ItemDO getSimpleItem(long itemId,boolean hasSku) {
		QueryItemOptionsDO options = new QueryItemOptionsDO();
		options.setIncludeSkus(hasSku);
		AppInfoDO app = new AppInfoDO(AppInfoConstants.NAME_DETAIL, "query",
				"detail");
		try {
			ItemResultDO result = itemQueryServiceClient.queryItemById(itemId,
					options, app);
			if (result.isSuccess()) {
				return result.getItem();
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static PublishItemOptionDO createPublishItemOptionDO() {
		PublishItemOptionDO itemOptionDO = new PublishItemOptionDO();
		itemOptionDO.setLang(PublishItemOptionDO.LANG_ZH_CN);
		Map<Integer, String[]> property = new HashMap<Integer, String[]>();
		Map<String, Map<String, String>> propertyAlias = new HashMap<String, Map<String, String>>();
		itemOptionDO.setInputPropertyMap(property);
		itemOptionDO.setPropertyAlias(propertyAlias);
		itemOptionDO.setSourceIp("192.168.1.23");
		itemOptionDO.setMachine("52455");
		itemOptionDO.setMacId("554584.354");
		itemOptionDO.setPreview(false);
		itemOptionDO.setClientAppName("SELL");
		return itemOptionDO;
	}

	
	
	public static ItemDO createItemDO(Long userId) {
		ItemDO item = new ItemDO();
		item.setTitle(IcDemoConstants.ITEM_TITLE);
		item.setPictUrl(IcDemoConstants.ITEM_PICT_URL);
		item.setCategoryId(3L);
		item.setMinimumBid(IcDemoConstants.ITEM_MINIMUM_BID);
		item.setReservePrice(IcDemoConstants.ITEM_RESERVE_PRICE);
		item.setAuctionType(IcDemoConstants.ITEM_AUCTION_TYPE);
		item.setDuration(IcDemoConstants.ITEM_DURATION);
		item.setIncrementNum(IcDemoConstants.ITEM_INCREMENT_NUM);
		item.setCity(IcDemoConstants.ITEM_CITY);
		item.setProv(IcDemoConstants.ITEM_PROV);
		item.setQuantity(IcDemoConstants.ITEM_QUANTITY);
		item.setStuffStatus(IcDemoConstants.ITEM_STUFF_STATUS);
		item.setZoo(IcDemoConstants.ITEM_ZOO);
		item.setPromotedStatus(IcDemoConstants.ITEM_PROMOTED_STATUS);
		item.setOldQuantity(IcDemoConstants.ITEM_OLD_QUANTITY);
		item.setOptions(IcDemoConstants.ITEM_OPTIONS);
		item.setProperty("20000:10681");
		item.setPropertyAlias(IcDemoConstants.ITEM_PROPERTY_ALIAS);
		item.setOrderCost(IcDemoConstants.ITEM_ORDER_COST);
		item.setDescription(IcDemoConstants.ITEM_DESCRIPTION);
		item.setPostageId(IcDemoConstants.ITEM_POSTAGE_ID);
		item.setShopCategoriesIdList(IcDemoConstants.ITEM_SHOP_CATEGORIES_ID_LIST);
		item.setUserId(userId);
		item.setAuctionStatus(IcDemoConstants.ITEM_AUCTION_STATUS);
		item.setFeatureString(IcDemoConstants.ITEM_FEATURES);
		item.setFeatureCc(IcDemoConstants.ITEM_FEATURE_CC);
		item.setMainColor(IcDemoConstants.ITEM_MAIN_COLOR);
		item.setAuctionPoint(IcDemoConstants.ITEM_AUCTION_POINT);
		item.setSellerPayPostfee(false);

		// about fee
		item.setSecureTradeEmsPostFee(IcDemoConstants.ITEM_EMS_POST_FEE);
		item.setSecureTradeFastPostFee(IcDemoConstants.ITEM_FAST_POST_FEE);
		item.setSecureTradeOrdinaryPostFee(IcDemoConstants.ITEM_ORDINARY_POST_FEE);

		// allowed null
		item.setSpuId(IcDemoConstants.ITEM_SPU_ID);

		return item;
	}


	public static ItemImageDO createItemImageDO(long userId, long itemId,
			int type){
		ItemImageDO itemImage = new ItemImageDO();
		itemImage.setUserId(userId);
		itemImage.setItemId(itemId);
		itemImage.setImageName("itemImage");
		itemImage.setType(type);
		itemImage.setMajor(true);
		return itemImage;
	}


}
