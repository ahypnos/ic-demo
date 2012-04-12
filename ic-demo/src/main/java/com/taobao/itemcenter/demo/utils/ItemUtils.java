

package com.taobao.itemcenter.demo.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.common.lang.StringUtil;
import com.taobao.common.dao.persistence.exception.DAOException;
import com.taobao.item.constant.AppInfoConstants;
import com.taobao.item.constant.CardCodeStatus;
import com.taobao.item.constant.ItemAttachConstants;
import com.taobao.item.domain.AppInfoDO;
import com.taobao.item.domain.CardCodeDO;
import com.taobao.item.domain.ItemAttachDO;
import com.taobao.item.domain.ItemAttributeExtraDO;
import com.taobao.item.domain.ItemDO;
import com.taobao.item.domain.ItemImageDO;
import com.taobao.item.domain.ItemSkuDO;
import com.taobao.item.domain.ItemUpdateDO;
import com.taobao.item.domain.ItemVideoDO;
import com.taobao.item.domain.PublishItemOptionDO;
import com.taobao.item.domain.query.CardCodeQuery;
import com.taobao.item.domain.query.QueryItemOptionsDO;
import com.taobao.item.domain.result.ItemResultDO;
import com.taobao.item.domain.spu.SpuDO;
import com.taobao.item.exception.IcException;
import com.taobao.item.service.client.ItemQueryServiceClient;
import com.taobao.util.UniqID;

public class ItemUtils {
	public static final String INSERT_CARD_CODE_CARD = "7885451523";
	public static final String INSERT_CARD_CODE_CODE = "85468479";
	/**
	 * 
	 * createItemDOWithAllField 主要用于准备包含所有字段的ItemDO数据
	 * 
	 * 
	 * <li>ItemDO包含所有字段（不包含图片）</li>
	 * 
	 * @author junliang
	 * 
	 * @date 2008-11-26
	 * 
	 */



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
	

	 public static ItemDO createItemDO(Long userId, long catId, boolean isBSeller) {
		 ItemDO item = new ItemDO();
	        item.setCategoryId(catId);
	        item.setUserId(userId);
	        item.setTitle(IcDemoConstants.ITEM_TITLE);
	        item.setPictUrl(IcDemoConstants.ITEM_PICT_URL);
	        item.setMinimumBid(IcDemoConstants.ITEM_MINIMUM_BID);
	        item.setReservePrice(IcDemoConstants.ITEM_RESERVE_PRICE);
	        item.setAuctionType(IcDemoConstants.ITEM_AUCTION_TYPE);
	        item.setDuration(IcDemoConstants.ITEM_DURATION);
	        item.setCity(IcDemoConstants.ITEM_CITY);
	        item.setProv(IcDemoConstants.ITEM_PROV);
	        item.setQuantity(IcDemoConstants.ITEM_QUANTITY);
	        item.setStuffStatus(IcDemoConstants.ITEM_STUFF_STATUS);
	        item.setPromotedStatus(IcDemoConstants.ITEM_PROMOTED_STATUS);
	        item.setOldQuantity(IcDemoConstants.ITEM_OLD_QUANTITY);
	        item.setPropertyAlias(IcDemoConstants.ITEM_PROPERTY_ALIAS);
	        item.setDescription(IcDemoConstants.ITEM_DESCRIPTION);
	        item.setAuctionStatus(IcDemoConstants.ITEM_AUCTION_STATUS);
	        item.setFeatureString(IcDemoConstants.ITEM_FEATURES);
	        item.setFeatureCc(IcDemoConstants.ITEM_FEATURE_CC);
	        item.setMainColor(IcDemoConstants.ITEM_MAIN_COLOR);
	        item.setSellerPayPostfee(false);

	        // about fee
	        item.setSecureTradeEmsPostFee(IcDemoConstants.ITEM_EMS_POST_FEE);
	        item.setSecureTradeFastPostFee(IcDemoConstants.ITEM_FAST_POST_FEE);
	        item.setSecureTradeOrdinaryPostFee(IcDemoConstants.ITEM_ORDINARY_POST_FEE);

	        if (isBSeller) {
	            item.setAuctionPoint(50);
	            item.setOptionsHaveInvoice();//发票
	        }
	        return item;
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

	/**
	 * 创建一个有图片的itemDO
	 * 
	 * @author junliang
	 * 
	 * @date 2008-12-19
	 * 
	 */
	public static ItemDO createItemDO(Long userId,
			String[] filenames) {
		ItemDO item = createItemDO(userId);
		// set image item list(allowed null)
		List<ItemImageDO> imageItemDOList = getImageList(filenames);
		item.setCommonItemImageList(imageItemDOList);
		item.setPropertyImageList(imageItemDOList);

		return item;
	}

	public static byte[] getInputImage(String filename) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		FileInputStream inputFile = null;
		try {
			inputFile = (FileInputStream) getInputStream(filename);
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

	public static void main(String[] args) {
		getInputImage("test.jpg");
	}

	/**
	 * 
	 * createItemImageDO 主要用于测试XXX的场景
	 * 
	 * 
	 * @author junliang
	 * 
	 * @date 2009-2-18
	 * 
	 */
	public static ItemImageDO createItemImageDO(long userId, long itemId,
			int type, boolean isGetImage,String filename) {
		ItemImageDO itemImage = new ItemImageDO();
		itemImage.setUserId(userId);
		itemImage.setItemId(itemId);
		itemImage.setImageName("itemImage");
		itemImage.setType(type);
		itemImage.setMajor(true);
		if (isGetImage) {
			itemImage.setImageData(getInputImage(filename));
		}

		return itemImage;
	}

	public static ItemImageDO createItemMajorImageDO(long userId, long itemId,
			int type, int picNum) {
		ItemImageDO itemImage = new ItemImageDO();
		itemImage.setUserId(userId);
		itemImage.setItemId(itemId);
		itemImage.setType(type);
		itemImage.setMajor(true);
		itemImage.setImageUrl("i2/qrcorDlYFwyQFU1EUSxKyo/YUd3" + picNum
				+ ".jpg");
		return itemImage;
	}

	public static ItemImageDO createItemSubImageDO(long userId, long itemId,
			int type, int picNum) {
		ItemImageDO itemImage = new ItemImageDO();
		itemImage.setUserId(userId);
		itemImage.setItemId(itemId);
		itemImage.setImageName("itemImage");
		itemImage.setType(type);
		itemImage.setMajor(false);
		itemImage.setImageUrl("i2/qrcorDlYFwyQFU1EUSxKyo/YUd3" + picNum
				+ ".jpg");
		return itemImage;
	}

	public static ItemAttachDO createItemAttach(long itemId, int attachType) {
		ItemAttachDO itemAttach = new ItemAttachDO();
		itemAttach.setItemId(itemId);
		itemAttach.setAttachType(attachType);
		itemAttach.setStatus(ItemAttachConstants.STATUS_NORMAL);
		itemAttach.setGmtCreate(new Date());
		itemAttach.setGmtModified(new Date());
		itemAttach.setMemo("yulinTest");
		byte[] contentData = new byte[1024];
		itemAttach.setContentData(contentData);
		if (attachType == ItemAttachConstants.ATTACH_TYPE_IDENTITY_CARD
				|| attachType == ItemAttachConstants.ATTACH_TYPE_SECURITY_CARD) {
			itemAttach.setAttachSuffix("jpg");
		} else {
			itemAttach.setAttachSuffix("txt");
		}
		return itemAttach;
	}

	public static List<ItemImageDO> getImageList(String[] filenames) {
		List<ItemImageDO> itemImageList = new ArrayList<ItemImageDO>();
		for (String filename : filenames) {
			ItemImageDO itemImage = new ItemImageDO();
			itemImage.setImageName(filename);
			itemImage.setImageUrl(null);
			itemImage.setImagePosition(1);
			//itemImage.setImageData(getInputImage(filename));
			itemImage.setProperties("sjdjfj");
			itemImage.setInputFileName(filename);
			itemImageList.add(itemImage);
		}
		return itemImageList;
	}

	public static List<ItemImageDO> getNullUrlImageList() {
		List<ItemImageDO> itemImageList = new ArrayList<ItemImageDO>();
		ItemImageDO itemImage = new ItemImageDO();
		itemImage.setImageName("imageName");
		itemImage.setImageUrl(null);
		itemImage.setImagePosition(1);
		Object object = "test";
		byte[] imageData = null;
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(object);
			imageData = bo.toByteArray();
		} catch (Exception e) {
			return null;
		}

		itemImage.setImageData(imageData);
		itemImage.setProperties("sjdjfj");
		itemImage.setInputFileName("inputfile.jpg");
		itemImageList.add(itemImage);
		return itemImageList;
	}

	public static List<ItemImageDO> getNotNullUrlAndDataImageList() {
		List<ItemImageDO> itemImageList = new ArrayList<ItemImageDO>();
		ItemImageDO itemImage = new ItemImageDO();
		itemImage.setImageName("imageName");
		itemImage.setImageUrl("/jkdkd/jdjd");
		itemImage.setImagePosition(1);
		byte[] imageData = getImageData();

		itemImage.setImageData(imageData);
		itemImage.setProperties("sjdjfj");
		itemImage.setInputFileName("inputfile.jpg");
		itemImageList.add(itemImage);
		return itemImageList;
	}

	public static byte[] getImageData() {
		byte[] imageData = null;
		try {
			Object object = "test";
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(object);
			imageData = bo.toByteArray();
		} catch (Exception e) {
			return null;
		}
		return imageData;
	}

	public static List<ItemImageDO> getNullUrlNullDataImageList() {
		List<ItemImageDO> itemImageList = new ArrayList<ItemImageDO>();
		ItemImageDO itemImage = new ItemImageDO();
		itemImage.setImageName("imageName");
		itemImage.setImageUrl(null);
		itemImage.setImagePosition(1);
		itemImage.setImageData(null);
		itemImage.setProperties("sjdjfj");
		itemImage.setInputFileName("inputfile.jpg");
		itemImageList.add(itemImage);
		return itemImageList;
	}

	/**
	 * 
	 * createItemDO 主要用于准备itemDO数据（没有图片）
	 * 
	 * <li>返回结果:itemUpdateDO：ItemUpdateDO</li>
	 * 
	 * @author junliang
	 * 
	 * @date 2008-11-26
	 * 
	 */
	public static ItemUpdateDO createItemUpdateDO() {
		ItemUpdateDO itemUpdateDO = new ItemUpdateDO();
		itemUpdateDO.setTitle(IcDemoConstants.ITEM_TITLE);
		itemUpdateDO.setPictUrl(IcDemoConstants.ITEM_PICT_URL);
		itemUpdateDO.setMinimumBid(IcDemoConstants.ITEM_MINIMUM_BID);
		itemUpdateDO.setReservePrice(IcDemoConstants.ITEM_RESERVE_PRICE);
		itemUpdateDO.setAuctionType(IcDemoConstants.ITEM_AUCTION_TYPE);
		itemUpdateDO.setDuration(IcDemoConstants.ITEM_DURATION);
		itemUpdateDO.setIncrementNum(IcDemoConstants.ITEM_INCREMENT_NUM);
		itemUpdateDO.setCity(IcDemoConstants.ITEM_CITY);
		itemUpdateDO.setProv(IcDemoConstants.ITEM_PROV);
		itemUpdateDO.setQuantity(IcDemoConstants.ITEM_QUANTITY);
		itemUpdateDO.setStuffStatus(IcDemoConstants.ITEM_STUFF_STATUS);
		itemUpdateDO.setZoo(IcDemoConstants.ITEM_ZOO);
		itemUpdateDO.setPromotedStatus(IcDemoConstants.ITEM_PROMOTED_STATUS);
		itemUpdateDO
				.setSecureTradeEmsPostFee(IcDemoConstants.ITEM_EMS_POST_FEE);
		itemUpdateDO
				.setSecureTradeFastPostFee(IcDemoConstants.ITEM_FAST_POST_FEE);
		itemUpdateDO
				.setSecureTradeOrdinaryPostFee(IcDemoConstants.ITEM_ORDINARY_POST_FEE);
		itemUpdateDO.setOldQuantity(IcDemoConstants.ITEM_OLD_QUANTITY);
		itemUpdateDO.setProperty("20000:10681;1627207:28320");
		itemUpdateDO.setPropertyAlias(IcDemoConstants.ITEM_PROPERTY_ALIAS);
		itemUpdateDO.setOrderCost(IcDemoConstants.ITEM_ORDER_COST);
		itemUpdateDO.setPostageId(IcDemoConstants.ITEM_POSTAGE_ID);
		itemUpdateDO.setDescription("test");
		itemUpdateDO
				.setShopCategoriesIdList(IcDemoConstants.ITEM_SHOP_CATEGORIES_ID_LIST);
		itemUpdateDO.setAuctionStatus(IcDemoConstants.ITEM_AUCTION_STATUS);
		;
		itemUpdateDO.setMainColor(IcDemoConstants.ITEM_MAIN_COLOR);
		itemUpdateDO.setAuctionPoint(IcDemoConstants.ITEM_AUCTION_POINT);
		itemUpdateDO.setRepostCount(IcDemoConstants.ITEM_REPOST_COUNT);
		itemUpdateDO.addOneOption(0L);

		return itemUpdateDO;
	}

	/**
	 * 创建一个包含图片的ItemUpdateDO
	 * 
	 * @author junliang
	 * 
	 * @date 2008-12-19
	 * 
	 */
	public static ItemUpdateDO createItemUpdateDO(String[] filenames) {
		ItemUpdateDO itemUpdateDO = createItemUpdateDO();
		// set image item list(allowed null)
		List<ItemImageDO> imageItemDOList = getImageList(filenames);
		itemUpdateDO.setCommonItemImageList(imageItemDOList);
		itemUpdateDO.setPropertyImageList(imageItemDOList);

		return itemUpdateDO;
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

	public static SpuDO createSpuDO() {
		SpuDO spuDO = new SpuDO();

		spuDO.setId(IcDemoConstants.SPU_ID);
		spuDO.setName(IcDemoConstants.SPU_NAME);
		spuDO.setCategoryId(IcDemoConstants.SPU_CATEGORY_ID);
		spuDO.setStatus(IcDemoConstants.SPU_STATUS);
		spuDO.setTsc(IcDemoConstants.SPU_TSC);
		spuDO.setCreateTime(IcDemoConstants.SPU_CREATE_TIME);
		spuDO.setModifiedTime(IcDemoConstants.SPU_MODIFIED_TIME);

		return spuDO;
	}

	public static ItemAttributeExtraDO createItemAttributeExtraDO() {
		ItemAttributeExtraDO itemAttributeExtraDO = new ItemAttributeExtraDO();
		itemAttributeExtraDO.setExtraSaleType(IcDemoConstants.EXTRA_SALE_TYPE);
		itemAttributeExtraDO.setExtraPeriod(IcDemoConstants.EXTRA_PERIOD);
		itemAttributeExtraDO.setExtraLicense(IcDemoConstants.EXTRA_LISENCE);
		itemAttributeExtraDO.setExtraData(IcDemoConstants.EXTRA_DATE);
		itemAttributeExtraDO
				.setExtraPlanStorage(IcDemoConstants.EXTRA_PLAN_STORAGE);
		itemAttributeExtraDO.setExtraUnit(IcDemoConstants.EXTRA_UNIT);
		itemAttributeExtraDO.setExtraBrand(IcDemoConstants.EXTRA_PRAND);
		itemAttributeExtraDO
				.setExtraFranchiser(IcDemoConstants.EXTRA_FRANCHISER);
		itemAttributeExtraDO.setExtraLevel(IcDemoConstants.EXTRA_LEVEL);
		itemAttributeExtraDO.setExtraFactory(IcDemoConstants.EXTRA_FACTORY);
		itemAttributeExtraDO
				.setExtraProducingArea(IcDemoConstants.EXTRA_PRODUCING_AREA);
		itemAttributeExtraDO.setExtraContent(IcDemoConstants.EXTRA_CONTENT);
		itemAttributeExtraDO
				.setExtraFactorySite(IcDemoConstants.EXTRA_FACTORY_SITE);
		itemAttributeExtraDO
				.setExtraDesignCode(IcDemoConstants.EXTRA_DESIGN_CODE);
		itemAttributeExtraDO.setExtraSpec(IcDemoConstants.EXTRA_SPEC);
		itemAttributeExtraDO.setExtraMix(IcDemoConstants.EXTRA_MIX);
		itemAttributeExtraDO
				.setExtraPrdLicenseNo(IcDemoConstants.EXTRA_PRD_LICENSENO);
		itemAttributeExtraDO.setExtraContact(IcDemoConstants.EXTRA_CONTACT);
		itemAttributeExtraDO
				.setExtraFoodAdditive(IcDemoConstants.EXTRA_FOOD_ADDITIVE);
		return itemAttributeExtraDO;
	}

	public static ItemSkuDO createItemSkuDO(long quantity, String properties) {
		ItemSkuDO sku = new ItemSkuDO();
		sku.setSkuId(UniqID.getInstance().getUniqID().hashCode());
		sku.setPrice(200000);
		sku.setProperties(properties);
		sku.setQuantity(quantity);
		sku.setSellerId(IcDemoConstants.ITEM_USER_ID1);
		sku.setStatus(1);
		return sku;
	}

	public static ItemSkuDO createItemSkuDO(long quantity, String properties,
			long price) {
		ItemSkuDO sku = new ItemSkuDO();
		sku.setSkuId(UniqID.getInstance().getUniqID().hashCode());
		sku.setPrice(price);
		sku.setProperties(properties);
		sku.setQuantity(quantity);
		sku.setSellerId(IcDemoConstants.ITEM_USER_ID1);
		sku.setStatus(1);
		return sku;
	}

	public static String getTooLongTitle() {
		String str = "";
		for (int i = 0; i < 32; i++) {
			str += "好";
		}
		return str;
	}

	public static String getTooLongAlias() {
		String str = "";
		for (int i = 0; i < 450; i++) {
			str += "好:好:好;";
		}
		return str;
	}

	/**
	 * 
	 * <li>比较两个日期比较是否相等，只比较到日</li>
	 * 
	 * @param date1
	 *            第一个日期
	 * @param date2
	 *            第一个日期 <li>返回结果:相等 true 不相等 false</li>
	 * 
	 * @author yuanhua
	 * @date 2008-12-19
	 * 
	 */
	public static boolean compareTwoDate(Date date1, Date date2) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return simpleDateFormat.format(date1).equals(
				simpleDateFormat.format(date2));
	}

	/**
	 * 
	 * <li>组装文件路径和文件名</li>
	 * 
	 * @param filename
	 *            文件名 <li>返回结果：组装后的完整文件路径</li>
	 * 
	 * @author junliang
	 * 
	 * @date 2008-12-24
	 * 
	 */
	public static String getPublishItemFile(String filename) {
		return IcDemoConstants.IC_PUBLISH_FILE_PATH + filename;
	}

	/**
	 * 
	 * <li>组装CardCodeQuery 对象</li>
	 * 
	 * @param sellerId
	 *            卖家ID
	 * @param sellerNumId
	 *            卖家数字ID
	 * @param auctionId
	 *            商品ID
	 * @param autoArea
	 *            卡号与密码区块 num password\num password <li>返回结果：CardCodeQuery 对象</li>
	 * 
	 * @author yuanhua
	 * 
	 * @date 2008-12-26
	 * 
	 */
	public static CardCodeQuery prepareQueryData(String sellerId,
			long sellerNumId, long itemId, String autoArea) {
		CardCodeQuery query = new CardCodeQuery();
		query.setSellerId(sellerId);
		query.setSellerNumId(sellerNumId);
		query.setItemId(itemId);
		ItemDO item = new ItemDO();
		item.setAutoArea(autoArea);
		query.setItem(item);
		return query;
	}

	/**
	 * 
	 * 
	 * <li>组装CardCodeDO 对象(卡号与密码都有)</li>
	 * 
	 * <li>返回结果: CardCodeDO 对象</li>
	 * 
	 * @author yuanhua
	 * 
	 * @date 2009-1-4
	 * 
	 */
	public static CardCodeDO createCardCodeDoWithCard(long itemId,
			int cardCodeId) {
		CardCodeDO cardCodeDO = ItemUtils
				.createCardCodeDoWithOnlyCode(itemId, cardCodeId);
		cardCodeDO.setCard(INSERT_CARD_CODE_CARD);
		return cardCodeDO;
	}

	/**
	 * 
	 * 
	 * <li>组装CardCodeDO 对象(只有密码)</li>
	 * 
	 * <li>返回结果: CardCodeDO 对象</li>
	 * 
	 * @author yuanhua
	 * 
	 * @date 2009-1-4
	 * @param auctionId
	 *            TODO
	 * @param cardCodeId
	 *            TODO
	 * 
	 */
	public static CardCodeDO createCardCodeDoWithOnlyCode(long itemId,
			long cardCodeId) {
		CardCodeDO cardCodeDO = new CardCodeDO();
		cardCodeDO.setItemId(itemId);
		cardCodeDO.setCardCodeId(cardCodeId);
		cardCodeDO.setCode(INSERT_CARD_CODE_CODE);
		cardCodeDO.setSellerId("00ff36e5741e7968b7fb0af34a1680df");
		cardCodeDO.setStatus(CardCodeStatus.STATUS_NOT_SEND);
		return cardCodeDO;
	}

	public static InputStream getInputStream(String fileName)
			throws FileNotFoundException {
		
		return new FileInputStream(fileName);
	}

	public static String getDataFilePath(String path, Class<?> object) {
		String fileName = getFileName(object.getName());
		return path + fileName + ".xls";
	}

	public static String getDataFilePath(String path, String fileName) {
		return path + fileName;
	}

	private static String getFileName(String name) {
		String[] file = StringUtil.split(name, ".");
		return file[file.length - 1];
	}

	public static SpuDO 根据类目创建SPU(int categoryId) {
		SpuDO spuDO = new SpuDO();
		spuDO.setName(IcDemoConstants.SPU_NAME);
		spuDO.setCategoryId(categoryId);
		spuDO.setTsc(IcDemoConstants.SPU_TSC);
		spuDO.setStatus(0);
		spuDO.setCreateTime(IcDemoConstants.SPU_CREATE_TIME);
		spuDO.setModifiedTime(IcDemoConstants.SPU_MODIFIED_TIME);
		spuDO.setPrice(2000);
		spuDO.setOwnerId(110010107);
		return spuDO;
	}

	public static List<String> getCommonIgnoreItemFieldList() {
		List<String> ignoreFields = new ArrayList<String>();
		ignoreFields.add("spu");
		ignoreFields.add("features");
		ignoreFields.add("user");
		ignoreFields.add("checkUserDO");
		ignoreFields.add("featureCc");
		ignoreFields.add("attributeKeys");
		ignoreFields.add("leftSecond");
		ignoreFields.add("startsLong");
		ignoreFields.add("lastModifiedForItemDetail");
		ignoreFields.add("remainTime");
		ignoreFields.add("description");
		ignoreFields.add("shopCategoriesIdList");
		ignoreFields.add("featureString");
		return ignoreFields;
	}

	public static List<ItemVideoDO> getItemVideo(int quantity, Long itemId,
			Integer status, Long userId) throws DAOException {
		List<ItemVideoDO> itemVideoList = new ArrayList<ItemVideoDO>();
		for (int i = 0; i < quantity; i++) {
			Long isvId = Long.valueOf(itemId + i + 1);
			ItemVideoDO itemVideo = new ItemVideoDO();
			itemVideo.setItemId(itemId);
			itemVideo.setUserId(userId);
			itemVideo.setVideoId(7777778);
			itemVideo.setIsvId(isvId);
			itemVideo.setStatus(status);
			itemVideo.setVideoUrl("testVideoUrl");
			itemVideo.setGmtCreate(new Date());
			itemVideo.setGmtModified(new Date());
			itemVideoList.add(itemVideo);
		}
		return itemVideoList;
	}

	/**
	 * 是否包含指定的值，即是否在同一位上为1
	 * @param realOption	实际的option
	 * @param containOption	包含的option
	 * @return
	 */
	public static boolean isOptionsContains(long realOption, long containOption) {
		if((containOption & (containOption -1)) != 0){
			throw new IllegalArgumentException("单个option必须为2的幂数，常量见ItemOptionsConstants");
		}
		if((realOption & containOption) > 0L) {
			return true;
		}
		return false;
	}
	
	/**
	 * options添加一位
	 * @param realOption  原来的option值
	 * @param added  需要添加的位的值
	 * @return
	 */
	public static long addOption(long realOption, long added) {
		if(isOptionsContains(realOption, added)) {
			return realOption;
		} else {
			return realOption + added;
		}
	}
}
