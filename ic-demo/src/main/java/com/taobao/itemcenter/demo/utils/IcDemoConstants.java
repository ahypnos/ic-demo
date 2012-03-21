package com.taobao.itemcenter.demo.utils;

import java.util.Date;

import com.taobao.util.Money;

public class IcDemoConstants {

	public static final int DB_DBC = 2;
	public static final int DB_ARK = 3;
	public static final int DB_MISC = 4;
	public static final int DB_SHOP = 6;
	public static final int DB_TBCENTER = 7;
	// SPU FILE NAME
	public static final String SPU_FILE_PATH = "spu/";
	public static final String FILE_GET_SPU_BY_ID = "GetSpuByIdTest.xls";
	public static final String FILE_QUERY_SPUS = "QuerySpusTest.xls";
	public static final String FILE_GET_SPUS = "GetSpusTest.xls";
	public static final String FILE_DELETE_SPUS = "DeleteSpusTest.xls";
	public static final String FILE_UPDATE_SPUS = "UpdateSpuTest.xls";
	public static final String FILE_DELETE_PICTURE = "DeletePictureTest.xls";
	public static final String FILE_SET_MAJOR_PICTURE = "SetMajorPictureTest.xls";
	public static final String FILE_COUNT_SPU_GROUP = "CountSpuGroupBySpuIdTest.xls";
	public static final String FILE_COUNT_SPU = "CountSpuBySpuGoupIdTest.xls";
	public static final String FILE_GET_RELATION_IGNORE_STATUS = "GetRelationIgnoreStatusTest.xls";
	public static final String FILE_SET_ENABLE_RELATION = "SetEnableSpuRelationTest.xls";
	public static final String FILE_SET_STATUS_OF_RELATION = "SetStatusOfRelationTest.xls";
	public static final String FILE_REMOVE_RELATED_GROUP = "RemoveRelatedSpuGroupTest.xls";
	public static final String FILE_SPU_FOR_UPLOAD_IMAGE = "SpuInfoForUploadImageTest.xls";
	public static final String FILE_USER_FOR_UPLOAD_IMAGE = "UserInfoForUploadImageTest.xls";
	public static final String FILE_INSER_SPU_BRAND = "InsertSpuServiceForBrandTest.xls";


	// ITEM FILE NAME
	public static final String IC_PUBLISH_FILE_PATH = "itemcenter/publish/";
	public static final String FILE_SAVE_ITEM_BY_ID = "SaveItemByIdTest.xls";
	public static final String FILE_SAVE_ITEM = "SaveItemTest.xls";
	public static final String FILE_NORMAL_USER_PUB_ITEM ="NormalUserPublishItemTest.xls";
	public static final String FILE_PUBLISH_ITEM ="SpuForPublishItemTest.xls";
	public static final String FILE_CC_USER_PUB_ITEM ="CCUserPublishItemTest.xls";
	public static final String FILE_PUNISHED_USER_RECORD ="PunishedUserRecord.xls";
	public static final String FILE_NORMAL_USER_PUB_ITEM_SHOPS = "NormalUserPublishItemShop.xls";
	public static final String FILE_WANGJIN_USER_PUB_ITEM ="WangjinUserPublishItemTest.xls";
	public static final String FILE_ILLEGAL_USER_PUB_ITEM ="IllegalUserPublishItemTest.xls";
	public static final String FILE_PUB_DUCTH_ITEM ="PublishDucthAuctionTest.xls";
	public static final String FILE_PUB_WINDOW_ITEM ="DiffCreditUserPubRecommendedWindowItemTest.xls";
	public static final String FILE_PUBED_WINDOW_ITEM ="RecommendedWindowItem.xls";
	public static final String FILE_PUB_ITEM_BRAND = "BSellerPublishItemForBrandTest.xls";

	public static final String IC_UPDATE_FILE_PATH = "itemcenter/update/";
	public static final String FILE_UPDATE_TO_STORE_STATUS = "UpdateToStoreStatusTest.xls";
	public static final String FILE_UPDATE_BATCH_ITEM_MEAL_DETAIL = "UpdateBatchItemMealDetailTest.xls";
	public static final String FILE_CSELLER_SAVE_ITEM = "CSellerSaveItemServiceItemTest.xls";
	public static final String FILE_XIAOER_CC_ITEM_INFO = "XiaoerSetItemToCcServiceTest.xls";
	public static final String FILE_XIAOER_CC_ITEM_INFO2 = "XiaoerSetItemToCcService2Test.xls";
	public static final String FILE_USER_INFO = "UserInfoServiceTest.xls";
	public static final String FILE_USER_FOR_UPDATE = "UserForUpdate.xls";
	public static final String FILE_PUNISH_USER_FOR_UPDATE = "PunishedUserRecord.xls";
	public static final String FILE_SPU_FOR_UPDATE = "SpuForUpdate.xls";
	public static final String FILE_SELLER_SAVE_RECOMMED = "SellerSaveItemRecommedTest.xls";
	public static final String FILE_SELLER_SAVE_RECOMMED_SHOP = "SellerSaveItemRecommedShop.xls";
	public static final String FILE_SELLER_SAVE_UN_RECOMMED = "SellerSaveItemUnRecommedTest.xls";
	public static final String FILE_SELLER_SAVE_NUMBER_ITEM = "SellerSaveNumberItemTest.xls";
	public static final String FILE_CRON_REPOST_BID = "CronRepostBidItemServiceTest.xls";
	public static final String FILE_REMOVE_ITEM_OPTIONS = "RemoveItemOptionsTest.xls";
	public static final String FILE_ADD_ITEM_QUANTITY = "AddItemQuantityTest.xls";
	public static final String FILE_CRON_DOWN_SHELF = "CronCheckFixPriceItemAndDownShelfServiceTest.xls";
	public static final String FILE_SELLER_INFO_UPLOAD_IMAGE = "SellerInfoForUploadCommonItemImage.xls";
	public static final String FILE_ITEM_INFO_UPLOAD_IMAGE = "ItemInfoForUploadImage.xls";
	public static final String FILE_SELLER_INFO_UPLOAD_PROPERTY_IMAGE = "SellerInfoForUploadPropertyItemImage.xls";
	public static final String FILE_ITEM_INFO_UPLOAD_PROPERTY_IMAGE = "ItemInfoForUploadPropertyImage.xls";
	public static final String FILE_SELLER_INFO_FOR_CLOSE_BID = "SellerInfoForCloseBidAuction.xls";
	public static final String FILE_ITEM_INFO_UPDATE_CURR_PRICE = "ItemInfoForUpdateCurrentPriceTest.xls";
	public static final String FILE_UPDATE_POSTAGE_ITEM = "UpdateItemPostageDaoTest.xls";

	public static final String FILE_XIAOER_DS_ITEM_INFO = "XiaoerDownShelfItemServiceTest.xls";
	public static final String FILE_XIAOER_DS_ITEM_INFO2 = "XiaoerDownShelfItemService2Test.xls";
	public static final String FILE_XIAOER_DL_ITEM_INFO = "XiaoerDelItemWithStatusServiceTest.xls";
	public static final String FILE_XIAOER_DL_ITEM_INFO2 = "XiaoerDelItemWithStatusService2Test.xls";

	public static final String FILE_CSELLER_SPU_INFO = "SpuInfoTest.xls";
	public static final String FILE_SellerSaveItemCardCodeTest="SellerSaveItem_CardCodeTest.xls";

	public static final String IC_QUERY_FILE_PATH = "itemcenter/query/";
	public static final String FILE_POSTAGES = "GetPostagesByUseridServiceTest.xls";
	public static final String FILE_QUERY_USER_ITEM = "QueryUserItemListTest.xls";
	public static final String FILE_SELLER_INFO_FOR_QUERY_USER_ITEM = "SellerInfoForQueryUserItemList.xls";
	public static final String FILE_QUERY_ITEM_BY_ID_LIST_USER_ID = "QueryItemListByIdListUserIdTest.xls";
	public static final String FILE_QUERY_MEAL_DETAIL_BY_ITEM = "QueryMealDetailByItemTest.xls";
	public static final String FILE_SELLER_INFO_FOR_QUERY_MEAL = "SellerInfoForQueryMealDetailByItem.xls";
	public static final String FILE_QUERY_POSTAGE_BY_ID = "QueryPosageByIdDaoTest.xls";
	public static final String FILE_QUERY_POSTAGE_ITEM_BY_ID = "QueryPostageItemByPostageIdDaoTest.xls";
    public static final String FILE_QUERY_MEAL_DETAIL_LIST_BY_ITEM_ID = "QueryMealDetailListByItemIdTest.xls";

	public static final String IC_IMAGE_FILE_PATH = "image/";

	public static final String IC_CART_FILE_PATH = "cartservice/";
	public static final String FILE_SELLER_INFO_FOR_ADD_CART_ITEM = "SellerInfoForAddCartItemTest.xls";
	public static final String FILE_ITEM_INFO_FOR_ADD_CART_ITEM = "ItemInfoForAddCartItemTest.xls";
	public static final String FILE_CART_INFO_FOR_ADD_CART_ITEM = "CartInfoForAddCartItemTest.xls";
	public static final String FILE_SPU_INFO_FOR_ADD_CART_ITEM = "SpuInfoForAddCartItemTest.xls";
	public static final String FILE_CART_INFO_FOR_QUERY_CART_ITEMS = "CartInfoForQueryCartItemsTest.xls";
	public static final String FILE_ITEM_INFO_FOR_QUERY_CART_ITEMS = "ItemInfoForQueryCartItemsTest.xls";

	// ITEM ID IN XLS FILE
	public static final long ITEM_ID_IN_XLS_FILE = 88555987;

	// ITEM STATUS
	public static final Integer ITEM_INITIAL_STATUS = 0;  //�շ�����Ʒ������״̬
	public static final Integer ITEM_NORMAL_STATUS = 1;  //�շ�����Ʒ������״̬
	public static final Integer ITEM_DELETED_STATUS = -1; //��Ʒ���û�ɾ��״̬
	public static final Integer ITEM_IN_STOCK_STATUS = -2;//��Ʒ���û��¼�״̬
	public static final Integer ITEM_XIAOER_DEL_STATUS = -4; //��Ʒ��С��ɾ��״̬
	public static final Integer ITEM_XIAOER_STOCK_STATUS = -3;//��Ʒ��С���¼�״̬
	public static final Integer ITEM_NEVER_ON_SHELF = -5;  //��Ʒ��δ�ϼ�
	public static final Integer ITEM_CC_STATUS = -9;      //��ƷCC״̬

	//ITEM PROMOTED STATUS
	public static final Integer ITEM_PROMOTED_NORMAL = 0; //����״̬
	public static final Integer ITEM_PROMOTED_RECOMMENDED = 1; //�������õĳ����Ƽ�״̬

	//ITEM STUFF STATUS
	public static final Integer ITEM_NEW = 5; //ȫ��
	public static final Integer ITEM_USED = 6; //����
	public static final Integer ITEM_IDEL = 8; //����

	//AUCTION TYPE
	public static final String ITEM_TYPE_AUCTION = "a"; //����
	public static final String ITEM_TYPE_PRICED = "b";  //һ�ڼ�

	// FOR CREATE ITEM
	public static final String ITEM_TITLE = "�����Ʒ����";
	private static final long currentTimeMillis = System.currentTimeMillis();
	public static final String ITEM_PICT_URL = "19/60/74/196074.jpg";
	public static final Long ITEM_CATEGORY = 50010278L;
	public static final Money ITEM_MINIMUM_BID = new Money(2000.00);
	public static final Money ITEM_RESERVE_PRICE = new Money(2000.00);
	public static final String ITEM_AUCTION_TYPE = ITEM_TYPE_PRICED;
	public static final int ITEM_DURATION = 14;
	public static final Money ITEM_INCREMENT_NUM = new Money(100.00);
	public static final String ITEM_CITY = "����";
	public static final String ITEM_PROV = "�㽭";
	public static final Integer ITEM_QUANTITY = 1;
	public static final int ITEM_STUFF_STATUS = ITEM_USED;
	public static final String ITEM_ZOO = "19:1109;2:120";
	public static final int ITEM_PROMOTED_STATUS = 0;
	public static final Money ITEM_EMS_POST_FEE = new Money(20.00);
	public static final Money ITEM_FAST_POST_FEE = new Money(10.00);
	public static final Money ITEM_ORDINARY_POST_FEE = new Money(6.00);
	public static final int ITEM_OLD_QUANTITY = 1;
	public static final Long ITEM_OPTIONS = 33554432L;
	public static final String ITEM_PROPERTY = "20379:26936;20383:27030;32971:113330";
	public static final String ITEM_PROPERTY_ALIAS = null;
	public static final int ITEM_ORDER_COST = 985;
	public static final String ITEM_DESCRIPTION = "description";
	public static final long ITEM_POSTAGE_ID = 0L;
	public static final String ITEM_SHOP_CATEGORIES_ID_LIST = null;
	public static final Long ITEM_USER_ID1 = new Long(77888895);
	public static final Long ITEM_USER_ID2 = new Long(77888896);
	public static final Long ITEM_SPU_ID = 0L;
	public static final Integer ITEM_AUCTION_STATUS = 0;
	public static final String ITEM_FEATURES = "shop:1;inv:0";
	public static final long ITEM_FEATURE_CC = 0;
	public static final String ITEM_MAIN_COLOR = "red";
	public static final int ITEM_AUCTION_POINT = 1000;
	public static final Integer ITEM_REPOST_COUNT = 2;
	public static final byte[] ITEM_PICT_DATA = {123};

	public static final Date STARTS = new Date(currentTimeMillis);
	public static final Date ENDS = new Date(currentTimeMillis+7*24*60*60*1000);

	public static final String ITEM_FORBIDEN_KEYWORD = "���ֹ���Ʒ";
	public static final String ITEM_DESC_PATH_URL = "i1/b00/471/b0647e42354f28fd108e158072b88469.desc";

	// FOR CREATE SPU
	public static final long SPU_ID = 810000051;
//	public static final NativeProperty SPU_NATIVE_PROPERTY = new NativeProperty();
	public static final String SPU_NAME = "SPU for item test";
	public static final int SPU_CATEGORY_ID = 50010278;
	public static final int SPU_STATUS = 1;
	public static final String SPU_TSC = "01234567891234567";
	public static final Date SPU_CREATE_TIME = new Date(currentTimeMillis);
	public static final Date SPU_MODIFIED_TIME = SPU_CREATE_TIME;

	// FOR CREATE MEALDETAIL

	public static final long MEALDETAIL_MEALDETAIL_ID = 99999999999L;		    //�ײ�����ID
	public static final long MEALDETAIL_ITEM_ID = 100005529637L;                //��Ʒid���ײ�ֻ����Ʒ��أ���sku�޹أ�
	public static final String MEALDETAIL_NAME = "������Ʒ�ײ�";                //�ײ�����
	public static final String MEALDETAIL_PICT_URL = "19/60/74/196074.jpg";             //�ײ�ͼƬ���ӵ�ַ
	public static final Money MEALDETAIL_MARKET_PRICE = new Money(2000.00);          //�г��۸�
	public static final Money MEALDETAIL_PROMOTION_PRICE = new Money(1980.00);  //�����۸�
	public static final int MEALDETAIL_NORMAL_STATUS = 1;                 //״̬      -1:ɾ��
	public static final int MEALDETAIL_DEL_STATUS = -1;                 //״̬          -1:ɾ��
	public static final Date MEALDETAIL_GMT_GREATE = new Date(currentTimeMillis);;             //����ʱ��
	public static final Date MEALDETAIL_GMT_MODIFIED = new Date(currentTimeMillis);           //�޸�ʱ��

	// FOR SELLER UPDATE ITEM
	public static final Date ITEM_UPDATE_STARTS = new Date(currentTimeMillis);
	public static final Date ITEM_UPDATE_ENDS = new Date(currentTimeMillis);
	public static final String ITEM_UPDATE_ITEM_ID = "b0647e42354f28fd108e158072b88478";
	public static final String ITEM_UPDATE_ITEM_ID_ERROR = "b0647e42354f28fd108e158072b8847866";
	public static final Long ITEM_UPDATE_ITEM_AUCTION_ID = 100005529637L;
	public static final Long ITEM_UPDATE_ITEM_AUCTION_ID_ERROR = 100005529637888L;
	public static final String ITEM_UPDATE_USER_ID = "5e1d935587ca43d94229d3a46b7c99c0";
	public static final String ITEM_UPDATE_TITLE1 = "just test";
	public static final String ITEM_UPDATE_TITLE2 = "just test By AuctionId";

	public static final Long ITEM_UPDATE_PID1_INVALID = 234343L;
	public static final Long ITEM_UPDATE_PID2_INVALID = 234344L;
	public static final Long ITEM_UPDATE_VID1_INVALID = 3434343L;
	public static final Long ITEM_UPDATE_VID2_INVALID = 343223L;

	public static final Long ITEM_UPDATE_PID1 = 1L;
	public static final Long ITEM_UPDATE_PID2 = 4L;
	public static final Long ITEM_UPDATE_VID1 = 1145L;
	public static final Long ITEM_UPDATE_VID2 = 2280L;
	public static final String ITEM_UPDATE_VID_EXPECT = "104";

	//FOR SELLER DELETE ITEM
	public static final String ITEM_DEL_ITEM_ID1 = "b0647e42354f28fd108e158072b88478";
	public static final long ITEM_DEL_ITEM_ID1_LONG = 100005529637L;
	public static final String ITEM_DEL_ITEM_ID2 = "b0647e42354f28fd108e158072b88479";
	public static final String ITEM_DEL_ITEM_ID3 = "b0647e42354f28fd108e158072b88480";
	public static final String ITEM_DEL_ITEM_ID_HAVE_IMGS = "b0647e42354f28fd108e158072b88481";
	public static final String ITEM_DEL_ITEM_ID_HAVE_CRAD = "b0647e42354f28fd108e158072b88482";
	public static final String ITEM_DEL_ITEM_ID_HAVE_BIDS_UNCLOSED = "b0647e42354f28fd108e158072b88483";
	public static final String ITEM_DEL_ITEM_ID_HAVE_BIDS_CLOSED = "b0647e42354f28fd108e158072b88484";
	public static final String ITEM_DEL_ITEM_ID_ERROR = "b0647e42354f28fd108e158072b8847866";
	public static final long ITEM_DEL_ITEMID_ERROR = 9999999999999l;

	//FOR SELLER UPSHELF ITEM
	public static final String ITEM_UP_ITEM_ID1 = "b0647e42354f28fd108e158072b88478";
	public static final String ITEM_UP_ITEM_ID2 = "b0647e42354f28fd108e158072b88479";
	public static final String ITEM_UP_ITEM_ID3 = "b0647e42354f28fd108e158072b88480";
	public static final String ITEM_UP_ITEM_ID4 = "b0647e42354f28fd108e158072b88515";
	public static final String ITEM_UP_ITEM_ID_SELLER_DEL = "b0647e42354f28fd108e158072b88481";
	public static final String ITEM_UP_ITEM_ID_SELLER_FROZEN = "b0647e42354f28fd108e158072b88482";
	public static final String ITEM_UP_ITEM_ID_SPU_SHIELD = "b0647e42354f28fd108e158072b88484";
	public static final String ITEM_UP_ITEM_ID_MORE_QUANTITY = "b0647e42354f28fd108e158072b88485";
	public static final String ITEM_UP_ITEM_ID_LIMITED = "b0647e42354f28fd108e158072b88486";
	public static final String ITEM_UP_ITEM_ID_HAVE_CARD = "b0647e42354f28fd108e158072b88487";
	public static final String ITEM_UP_ITEM_ID_QUANTITY_ZERO = "b0647e42354f28fd108e158072b88488";
	public static final String ITEM_UP_ITEM_ID_WINE = "b0647e42354f28fd108e158072b88489";
	public static final String ITEM_UP_ITEM_ID_AV1 = "b0647e42354f28fd108e158072b88490";
	public static final String ITEM_UP_ITEM_ID_AV2 = "b0647e42354f28fd108e158072b88491";
	public static final String ITEM_UP_ITEM_ID_HAVE_BIDS = "b0647e42354f28fd108e158072b88494";
	public static final String ITEM_UP_ITEM_ID_IDLE1 = "b0647e42354f28fd108e158072b88495";
	public static final String ITEM_UP_ITEM_ID_IDLE2 = "b0647e42354f28fd108e158072b88496";
	public static final String ITEM_UP_ITEM_ID_IDLE3 = "b0647e42354f28fd108e158072b88497";
	public static final String ITEM_UP_ITEM_ID_IDLE_MORE = "b0647e42354f28fd108e158072b88498";
	public static final String ITEM_UP_ITEM_ID_IDLE4 = "b0647e42354f28fd108e158072b88499";
	public static final String ITEM_UP_ITEM_ID_IDLE5 = "b0647e42354f28fd108e158072b88500";
	public static final String ITEM_UP_ITEM_ID_IDLE6 = "b0647e42354f28fd108e158072b88501";
	public static final String ITEM_UP_ITEM_ID_IDLE7 = "b0647e42354f28fd108e158072b88502";
	public static final String ITEM_UP_ITEM_ID_IDLE8 = "b0647e42354f28fd108e158072b88503";
	public static final String ITEM_UP_ITEM_ID_IDLE9 = "b0647e42354f28fd108e158072b88504";
	public static final String ITEM_UP_ITEM_ID_IDLE10 = "b0647e42354f28fd108e158072b88505";
	public static final String ITEM_UP_ITEM_ID_IDLE11 = "b0647e42354f28fd108e158072b88506";
	public static final String ITEM_UP_ITEM_ID_PUNISH = "b0647e42354f28fd108e158072b88507";
	public static final String ITEM_UP_ITEM_ID_INVALID_TYPE = "b0647e42354f28fd108e158072b88508";
	public static final String ITEM_UP_ITEM_ID_PRICE_RANGE = "b0647e42354f28fd108e158072b88509";
	public static final String ITEM_UP_ITEM_ID_INVALID_PROPERTY = "b0647e42354f28fd108e158072b88510";
	public static final String ITEM_UP_ITEM_ID_HAVE_CARD_EMPTY = "b0647e42354f28fd108e158072b88511";
	public static final String ITEM_UP_ITEM_ID_CC_STATUS = "b0647e42354f28fd108e158072b88512";
	public static final String ITEM_UP_ITEM_ID_CERTAIN_PRICE = "b0647e42354f28fd108e158072b88513";
	public static final String ITEM_UP_ITEM_ID_ALREADY_STOCK = "b0647e42354f28fd108e158072b88514";
	public static final String ITEM_UP_ITEM_ID_UN_AUTH_SELLER = "b0647e42354f28fd108e158072b88516";
	public static final String ITEM_UP_ITEM_ID_INDIV_AUTH_SELLER = "b0647e42354f28fd108e158072b88517";
	public static final String ITEM_UP_ITEM_ID_SHOP_AUTH_SELLER = "b0647e42354f28fd108e158072b88518";
	public static final String ITEM_UP_ITEM_ID_SEC_INDIV_AUTH_SELLER = "b0647e42354f28fd108e158072b88519";


	//FOR SELLER DOWNSHELF ITEM
	public static final String ITEM_DOWN_ITEM_ID1 = "b0647e42354f28fd108e158072b88478";	
	public static final long ITEM_DOWN_ITEM_ID1_NUM = 100005529639L;
	public static final String ITEM_DOWN_ITEM_ID2 = "b0647e42354f28fd108e158072b88479";	
	public static final long ITEM_DOWN_ITEM_ID2_NUM = 100005529638L;
	public static final String ITEM_DOWN_ITEM_ID3 = "b0647e42354f28fd108e158072b88480";
	public static final long ITEM_DOWN_ITEM_ID3_NUM = 100005529639L;
	public static final String ITEM_DOWN_ITEM_ID_HAVE_BIDS_UNCLOSED = "b0647e42354f28fd108e158072b88483";	// 100005529642
	public static final long ITEM_DOWN_ITEM_ID_HAVE_BIDS_UNCLOSED_NUM = 100005529642L;
	public static final String ITEM_DOWN_ITEM_ID_HAVE_BIDS_CLOSED = "b0647e42354f28fd108e158072b88484";	// 100005529643
	public static final long ITEM_DOWN_ITEM_ID_HAVE_BIDS_CLOSED_NUM = 100005529643L;
	public static final String ITEM_DOWN_ITEM_ID_ERROR = "b0647e42354f28fd108e158072b8847866";	// �����ڵ�
	public static final long ITEM_DOWN_ITEM_ID_ERROR_NUM = Long.MAX_VALUE;

	//FOR SELLER MODIFY ITEM QUANITY
	public static final String ITEM_QUANTITY_ITEM_ID1 = "b0647e42354f28fd108e158072b88478";
	public static final long ITEM_QUANTITY_ITEMID1 = 100005529637l;
	public static final String ITEM_QUANTITY_ITEM_ID_ZERO = "b0647e42354f28fd108e158072b88479";
	public static final String ITEM_QUANTITY_ITEM_ID2 = "b0647e42354f28fd108e158072b88480";
	public static final long ITEM_QUANTITY_ITEMID2 = 100005529639l;
	public static final String ITEM_QUANTITY_ITEM_AUTO = "b0647e42354f28fd108e158072b88482";
	public static final long ITEM_QUANTITY_ITEMID_AUTO = 100005529641l;
	public static final String ITEM_QUANTITY_ITEM_ID_HAVE_BIDS = "b0647e42354f28fd108e158072b88483";
	public static final long ITEM_QUANTITY_ITEMID_HAVE_BIDS = 100005529641l;
	public static final String ITEM_QUANTITY_ITEM_ID_HAVE_SKU = "b0647e42354f28fd108e158072b88484";
	public static final long ITEM_QUANTITY_ITEMID_HAVE_SKU = 100005529643l;
	public static final String ITEM_QUANTITY_ITEM_ID_STAR = "b0647e42354f28fd108e158072b88485";
	public static final long ITEM_QUANTITY_ITEMID_STAR = 100005529661l;
	public static final String ITEM_QUANTITY_ITEM_ID_AV = "b0647e42354f28fd108e158072b88490";
	public static final long ITEM_QUANTITY_ITEMID_AV = 100005529649l;
	public static final String ITEM_QUANTITY_ITEM_ID_IDLE = "b0647e42354f28fd108e158072b88495";
	public static final String ITEM_QUANTITY_ITEM_ID_ERROR = "b0647e42354f28fd108e158072b8847866";
	public static final long ITEM_QUANTITY_ITEMID_ERROR = 999999999L;

	//FOR SELLER SAVE ITEM POSTAGE
	public static final String ITEM_POSTAGE_ITEM_ID1 = "b0647e42354f28fd108e158072b88478";
	public static final String ITEM_POSTAGE_ITEM_ID2 = "b0647e42354f28fd108e158072b88479";
	public static final String ITEM_POSTAGE_ITEM_ID3 = "b0647e42354f28fd108e158072b88480";
	public static final String ITEM_POSTAGE_ITEM_ID_ERROR = "b0647e42354f28fd108e158072b8847866";
	public static final int ITEM_POSTAGE_ID_BASE_INT = 999999998;
	public static final long ITEM_POSTAGE_ID_BASE_LONG = 999999997L;
	public static final int ITEM_POSTAGE_ID_ERROR = 999933335;
	public static final long ITEM_ORDINARY_FEE = 5L;
	public static final long ITEM_FAST_FEE = 11L;
	public static final long ITEM_EMS_FEE = 20L;

	//FOR SELLER SAVE ITEM RECOMMED
	public static final String ITEM_RECOMMED_ITEM_ID1 = "b0647e42354f28fd108e158072b88478";
	public static final String ITEM_RECOMMED_ITEM_ID2 = "b0647e42354f28fd108e158072b88479";
	public static final String ITEM_RECOMMED_ITEM_ID3 = "b0647e42354f28fd108e158072b88480";

	public static final String ITEM_RECOMMED_ITEM_ID4 = "b0647e42354f28fd108e158072b88481";

	public static final String ITEM_RECOMMED_ITEM_ID5 = "b0647e42354f28fd108e158072b88482";
	public static final String ITEM_RECOMMED_ITEM_ID6 = "b0647e42354f28fd108e158072b88483";
	public static final Long ITEM_RECOMMED_ITEMID7 = 100005529663L;
	public static final String ITEM_RECOMMED_ITEM_ID7 = "b0647e42354f28fd108e158072b88484";

	public static final String ITEM_RECOMMED_ITEM_ID8 = "b0647e42354f28fd108e158072b88485";
	// CC ��Ʒ
	public static final String ITEM_RECOMMED_ITEM_ID9 = "b0647e42354f28fd108e158072b88486";
	public static final String ITEM_RECOMMED_ITEM_ID10 = "b0647e42354f28fd108e158072b88487";


	public static final String ITEM_RECOMMED_ITEM_ID_ERROR = "b0647e42354f28fd108e158072b8847866";
	public static final long ITEM_RECOMMED_ITEMID_ERROR = 999999999999L;
	public static final int ITEM_RECOMMED_STATUS = 1;

	//FOR SELLER SAVE ITEM UNRECOMMED
	public static final String ITEM_UN_RECOMMED_ITEM_ID1 = "b0647e42354f28fd108e158072b88478";
	public static final String ITEM_UN_RECOMMED_ITEM_ID2 = "b0647e42354f28fd108e158072b88479";

	public static final String ITEM_UN_RECOMMED_ITEM_ID4 = "b0647e42354f28fd108e158072b88481";
	public static final String ITEM_UN_RECOMMED_ITEM_ID5 = "b0647e42354f28fd108e158072b88482";
	public static final String ITEM_UN_RECOMMED_ITEM_ID6 = "b0647e42354f28fd108e158072b88483";

	public static final int ITEM_UN_RECOMMED_STATUS = 0;
	public static final int ITEM_XIAOER_RECOMMED_STATUS = 2;


	//FOR SELLER SAVE ITEM CHARITY
	public static final String ITEM_CHARITY_ZOO = "1:108;4:1011";
	public static final String ITEM_CHARITY_OLD_ZOO = "1:102;4:104";

	//FOR SELLER SAVE ITEM BONUS
	public static final long ITEM_BONUS_OPTIONS = 8388608L;

	//FOR SELLER MOVE ITEM TO STOCK
	public static final String ITEM_MOVE_ITEM_ID1 = "b0647e42354f28fd108e158072b88478";
	public static final String ITEM_MOVE_ITEM_ID2 = "b0647e42354f28fd108e158072b88479";
	public static final String ITEM_MOVE_ITEM_ID3 = "b0647e42354f28fd108e158072b88480";
	public static final String ITEM_MOVE_ITEM_ID_ERROR = "b0647e42354f28fd108e158072b8847866";
	public static final Integer ITEM_STOCK_OPTIONS = 8;
	public static final Integer ITEM_UN_STOCK_OPTIONS = 0;

	//FOR SELLER SAVE NUMBER ITEM
	public static final long ITEM_SVAE_NUMBER_QQ1 = 100006000000L;
	public static final long ITEM_SVAE_NUMBER_QQ2 = 100006000001L;
	public static final long ITEM_SVAE_NUMBER_CHINAMOBILE1 = 100006000002L;
	public static final long ITEM_SVAE_NUMBER_CHINAMOBILE2 = 100006000003L;
	public static final long ITEM_SVAE_NUMBER_CHINAUNICOM1 = 100006000004L;
	public static final long ITEM_SVAE_NUMBER_CHINAUNICOM2 = 100006000005L;
	public static final long ITEM_SVAE_NUMBER_CHINATELECOM1 = 100006000007L;
	public static final long ITEM_SVAE_NUMBER_INVALID = 100006000099L;



	public static final String ITEM_NUMBER_QQ1 = "343843849";
	public static final String ITEM_NUMBER_CHINAMOBILE1 = "13900120004";
	public static final String ITEM_NUMBER_CHINAUNICOM1 = "13225569899";
	public static final String ITEM_NUMBER_CHINATELECOM1= "13300120003";
	public static final String ITEM_NUMBER_MOBILE_MORE_LENGTH = "132255698990";
	public static final String ITEM_NUMBER_MOBILE_LESS_LENGTH = "1322556989";
	public static final String ITEM_NUMBER_MOBILE_INVALID = "132255698xx";
	public static final String ITEM_NUMBER_CHINAMOBILE_INVALID = "1332556989";
	public static final String ITEM_NUMBER_CHINAUNICOM_INVALID = "1392556989";
	public static final String ITEM_NUMBER_CHINAUNICOM_INVALID2 = "13300120003";
	public static final String ITEM_NUMBER_CHINAUNICOM_INVALID3 = "15300120003";
	public static final String ITEM_NUMBER_CHINATELECOM_INVALID = "1662556989";
	public static final String ITEM_NUMBER_QQ_LESS = "3438";
	public static final String ITEM_NUMBER_QQ_MORE = "34384384911";
	public static final String ITEM_NUMBER_QQ_INVALID = "34384384xx";

	public static final long ITEM_CATEGORY_QQ = 4001L;
	public static final String ITEM_CATEGORY_QQ_STR = "QQ��";
	public static final long ITEM_CATEGORY_CHINAMOBILE = 50002402L;
	public static final String ITEM_CATEGORY_CHINAMOBILE_STR = "�ƶ�";
	public static final long ITEM_CATEGORY_CHINAUNICOM = 50002403L;
	public static final String ITEM_CATEGORY_CHINAUNICOM_STR = "��ͨ";
	public static final long ITEM_CATEGORY_CHINATELECOM = 50011764L;
	public static final String ITEM_CATEGORY_CHINATELECOM_STR = "����";
	public static final long ITEM_CATEGORY_UN_NUMBER = 1512L;

	public static final long ITEM_CATEGORY_3G = 20122000L;
//	B���ҷ������
	public static final Long ITEM_CATEGORY_BSELLER_AUCTIONPOINTS_ONE = 20122001L;
	public static final Long ITEM_CATEGORY_BSELLER_AUCTIONPOINTS_FIVE = 20122002L;
//	������Ŀ�����Ʒ���/�༭/�ϼ�ȫ�±��������Ʒ���Ȩ����ֻ�ܷ������Ϊ1�Ķ��ֱ��������Ʒ���Ȩ���Ҹ���Ŀ�����ֻ����50�����ֺ����ñ���
	public static final long ITEM_CATEGORY_VIDEO_FULL_FEATURE = 20122060;
	public static final long ITEM_CATEGORY_COMMON = 20111819l;
	public static final long ITEM_CATEGORY_PREPAY = 20100929L;
	
	public static final long ITEM_CATEGORY_FOOD = 50008431L;


	public static final long ITEM_CATEGORY_������Ʒ�����Ŀ = 20111818l;
	public static final long ITEM_CATEGORY_������Ʒ�����Ŀ2 = 20111828l;
	public static final long ITEM_CATEGORY_��������Ʒ�����Ŀ = 20111819l;
	public static final long ITEM_CATEGORY_��������Ʒ�����Ŀ2 = 20111829l;
	public static final long ITEM_CATEGORY_������Ʒ��Ŀ�����÷������ = 20122007;
	public static final long ITEM_CATEGORY_������Ʒ��Ŀ������B���ҵķ������ = 20122008;
	public static final long ITEM_CATEGORY_������Ʒ��Ŀ�����÷��������Ŀ = 20122003;

	public static final long ITEM_CATEGORY_��ֵƽ̨�ӿ = 50003309;
	public static final long ITEM_CATEGORY_������Ŀ = 20110528;
	public static final long ITEM_CATEGORY_��ֵƽ̨�ӿ��Ŀ=20110824;
	public static final long SELLER_ID_SVAE_NUMBER = 110010101L;

	//FOR  ITEM OPTIONS
	public static final Long ITEM_ADD_OPTION1= 64L;
	public static final Long ITEM_ADD_OPTION2= 512L;
	public static final Long ITEM_REMOVE_OPTION1= 4L;
	public static final Long ITEM_REMOVE_OPTION2= 16L;
	public static final Long ITEM_OLD_OPTIONS= 20L;
	
	// �������Option�ֶ���س��� 
	public static final Long ITEM_OPTIONS_HAS_SKU = 1L << 29; //536870912

	public static final Long ITEM_OPTION_AUCTION_ID = 100005529637L;

	public static final long SELLER_ID_CHARITY = 77888892L;
	public static final long SELLER_ID_AUTH_LONG = 77888892L;
	public static final long SELLER_ID_UN_AUTH_LONG = 8888897L;
	public static final long SELLER_ID_COMM_BASE_LONG = 77888892L;
	public static final long SELLER_ID_OTHER_BASE_LONG = 77777898L;
	public static final long SELLER_ID_ERROR_BASE_LONG = 99993333555L;
	public static final String SELLER_ID_COMM = "5e1d935587ca43d94229d3a46b7c99c0";
	public static final Long SELLER_ID_COMM_LONG = 77888892L;
	public static final Long SELLER_ID_COMM_LONG2 = 77888892L;
	public static final Long SELLER_ID_CC_LONG = 788868951L;
	public static final String SELLER_ID_HAVE_IMGS = "53849dde000f19b6e9c5071731e93298";
	public static final Long SELLER_ID_HAVE_IMGS_LONG = 77888892L;
	public static final String SELLER_ID_HAVE_CRAD = "00ff36e5741e7968b7fb0af34a1680df";
	public static final Long SELLER_ID_HAVE_CRAD_LONG = 77888892L;
	public static final String SELLER_ID_HAVE_BIDS = "7b3db573579c3f8f8789278ac1ea5ab4";
	public static final Long SELLER_ID_HAVE_BIDS_LONG = 77888892L;
	public static final String SELLER_ID_ERROR = "dfadfdfdfddadfadfadf3334343dfdd";
	public static final Long SELLER_ID_ERROR_LONG = 99993333555L;
	public static final Long SELLER_ID_DEL_LONG = 9999999990L;
	public static final Long SELLER_ID_FROZEN_LONG = 77777896L;
	public static final Long SELLER_ID_PUNISH_LONG = 10146L;
	public static final Long SELLER_ID_BINDS_LONG = 8888895L;
	public static final long SELLER_ID_INDIV_AUTH_LONG = 9999999994L;
	public static final long SELLER_ID_SHOP_AUTH_LONG = 9999999995L;
	public static final long SELLER_ID_SEC_INDIV_AUTH_LONG = 9999999996L;

	//FOR CREATE ITEM ATTRIBUTE EXTRA
	public static final String EXTRA_SALE_TYPE = "ɢװ";
	public static final String EXTRA_PERIOD = "3����";
	public static final String EXTRA_LISENCE = "76746874";
	public static final String EXTRA_DATE = "2008��9��";
	public static final String EXTRA_PLAN_STORAGE = "�ܷⱣ��";
	public static final String EXTRA_UNIT = "kg";
	public static final String EXTRA_PRAND = "ɽկ��";
	public static final String EXTRA_FRANCHISER = "�ܾ���";
	public static final String EXTRA_LEVEL = "һ��";
	public static final String EXTRA_FACTORY = "ɽկ�ܳ�";
	public static final String EXTRA_PRODUCING_AREA = "������ɽ";
	public static final String EXTRA_CONTENT = "50g";
	public static final String EXTRA_FACTORY_SITE = "ɽ����";
	public static final String EXTRA_DESIGN_CODE = "7455896541";
	public static final String EXTRA_SPEC = "10*5";
	public static final String EXTRA_MIX = "ʳ���Ρ����";
    public static final String EXTRA_PRD_LICENSENO = "QS510012010356";
    public static final String EXTRA_PRD_LICENSENO_WithoutQS = "510012010356";
    public static final String EXTRA_CONTACT = "1239018";
    public static final String EXTRA_FOOD_ADDITIVE = "������ʳ���Ρ����";

	//FOR CREATE SKU
	public static final long SKU_PRICE = 170000;
	public static final String SKU_PROPERTIES = "20879:21456;20930:30009";

	//FOR ITEM IMAGE
	public static final byte[] imageData= {1};
	
	// SKU����
	public static final int SKU_NORMAL = 1;
	public static final int SKU_DELETED = -1;


	//DELETE ITEM BY ID SQL
	public static final String ITEM_DELETE_ITEM_BY_ID = "delete from AUCTION_AUCTIONS where AUCTION_ID = ";

	//DELETE NUBER RANGE ITEM BY USER ID
	public static final String ITEM_DELTE_BY_USER_ID = "delete from AUCTION_AUCTIONS where USER_ID = 110010101";

	//DELETE SKU BY ITEM ID
	public static final String ITEM_DELETE_SKU_BY_ITEM_ID ="delete from SKU where ITEM_ID = ";

	//DELETE CARDCODE ITEM BY AUCTION ID
	public static final String ITEM_DELETE_CARD_CODE_BY_SELLER_ID = "delete from auction_card_code where SELLER_ID = '7f5fbbf1761fd5b04feb1ea5d62953fc'";

	//ITEM SELECT AUCTION_STATUS BY ID SQL
	public static final String ITEM_SELECT_AUCTION_STATUS_BY_ID_SQL = "select AUCTION_STATUS from AUCTION_AUCTIONS where ID = '";

	//ITEM SELECT ZOO BY ID SQL
	public static final String ITEM_SELECT_ZOO_BY_ID_SQL = "select ZOO from AUCTION_AUCTIONS where AUCTION_ID = '";

	//ITEM SELECT IMG COUNT
	public static final String ITEM_SELECT_IMG_COUNT_BY_ID_SQL = "select count(*) from AUCTION_IMAGE  where AUCTION_ID = '";

	//ITEM SELECT CARD COUNT
	public static final String ITEM_SELECT_CARD_COUNT_BY_ID_SQL = "select count(*) from AUCTION_CARD_CODE  where AUCTION_ID = '";

	//ITEM SELECT OPTIONS BY AUCTION_ID SQL
	public static final String ITEM_SELECT_OPTIONS_BY_AUCTION_ID_SQL = "select OPTIONS from AUCTION_AUCTIONS where AUCTION_ID = '";

	//ITEM SELECT AUCTION SKU COUNT BY ID SQL
	public static final String ITEM_SELECT_SKU_COUNT_BY_AUCTION_ID_SQL = "select count(*) from SKU where ITEM_ID ='";

	//ITEM SELECT ENDS BY ID SQL
	public static final String ITEM_SELECT_ENDS_BY_ID_SQL = "select ENDS from AUCTION_AUCTIONS where AUCTION_ID=7777704223";

	//SPU SELECT PICT_URL BY SPU_ID
	public static final String SPU_SELECT_PICT_URL_BY_SPU_ID = "select PICT_URL from SPU where SPU_ID=";

	//SPU_IMAGE SELECT IMAGE_URL BY IMAGE_ID
	public static final String SPU_SELECT_IMAGE_URL_BY_IMAGE_ID = "select IMAGE_URL from SPU_IMAGE where image_id =";

	
	
}
