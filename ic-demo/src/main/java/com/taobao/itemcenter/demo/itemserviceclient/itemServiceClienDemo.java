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
	 * �����Ʒ�����Ϣ
	 * 
	 */
	public void addItemOptions() {

		System.out
				.println("----------------����addItemOptions()����------------------------------");
		List<Long> options = new ArrayList<Long>();
		options.add(1L);// ��������� �����Ա���۵� ����
		options.add(1L << 3);// ��������� ���̳Ǳ����� ����
		try {
			ProcessResultDO result = itemServiceClient.addItemOptions(itemId,
					options, getLoomAppInfo());
			if (result.isSuccess()) {

				System.out
						.println("----------------����addItemOptions()�����ɹ�------�õ���Ʒ��Ϣ--"
								+ result.getResultCode());

				// do something
			} else {
				// ��ӡ������Ϣ

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
	 * ȥ�����������Ϣ
	 * 
	 */
	public void removeItemOptions() {
		List<Long> options = new ArrayList<Long>();
		options.add(1L);// ȥ������ �����Ա���۵� ����
		options.add(10L);// ȥ������ ���̳Ǳ����� ����
		try {
			ProcessResultDO result = itemServiceClient.removeItemOptions(
					itemId, options, getLoomAppInfo());
			if (result.isSuccess()) {

				// do something
			} else {
				// ��ӡ������Ϣ
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
	 * ��������
	 * 
	 * @see ItemService#publishItem(ItemDO, PublishItemOptionDO)
	 */
	public void publishItem() {

		try {
			ItemDO item = ItemUtils.createItemDO(UserDataConstants.C_����c����L);
			// item.setXXX(); �����Ʒ��һϵ������ �����Ϣ������ο�API�ĵ�
			item.setCategoryId(IcDemoConstants.ITEM_CATEGORY_FOOD);
			item.setProperty("1930001:27772");
			item.setSpuId(0L);
			item.setQuantity(1);
			item.setAuctionPoint(5);
			
			
			

			// ������Ʒʱ�������Ӱ˵�����Ҫ������������ӣ����緢��ʱ���û�ip,�˴η����Ƿ���ҪԤ�����Ƿ񷵻�SPU��Ϣ�ȵ�
			PublishItemOptionDO publicItemOption=ItemUtils.createPublishItemOptionDO();
			publicItemOption.setLang("zh_CN"); // Υ��ؼ���У��Ŀͷ������ԣ��˴�����Ϊ�������ģ�zh_HK�������ģ�
            CreateItemResultDO result = itemServiceClient.publishItem(item,
					publicItemOption, getAppInfoDOForSell());
			if (result.isSuccess()) {

				// do something
			} else {
				// ��ӡ������Ϣ
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
	 * ����ǰԤ����Ʒ, ������spu����Ʒ��Ϣ��������ӿ������û��Լ����鷢���Ժ��Ч�� ������Ʒ��Ϣ���ᱣ�棬ֻ�ṩԤ��
	 * 
	 * @see ItemService#publishItemPreview(ItemDO, PublishItemOptionDO)
	 */
	public void publishItemPreview() {

		try {
			ItemDO item = ItemUtils.createItemDO(UserDataConstants.C_����c����L);
			// item.setXXX(); �����Ʒ��һϵ������ �����Ϣ������ο�API�ĵ�
			item.setCategoryId(IcDemoConstants.ITEM_CATEGORY_FOOD);
			item.setProperty("1930001:27772");
			item.setSpuId(0L);
			item.setQuantity(1);
			item.setAuctionPoint(5);
			
			// ������Ʒʱ�������Ӱ˵�����Ҫ������������ӣ����緢��ʱ���û�ip,�˴η����Ƿ���ҪԤ�����Ƿ񷵻�SPU��Ϣ�ȵ�
			PublishItemOptionDO publicItemOption = ItemUtils.createPublishItemOptionDO();
			publicItemOption.setLang("zh_CN"); // Υ��ؼ���У��Ŀͷ������ԣ��˴�����Ϊ�������ģ�zh_HK�������ģ�

			CreateItemResultDO result = itemServiceClient.publishItemPreview(
					item, publicItemOption, getLoomAppInfo());
			if (result.isSuccess()) {

				// do something
			} else {
				// ��ӡ������Ϣ
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
	 * ��������Ԥ�������û��޹�У�� ���Ԥ��ȥ���˺��û���ص�У�飬Ŀǰ��DPC�ڵ�������ӿ�
	 * 
	 * @see ItemService#publishItemWithOutUserPreview(ItemDO,
	 *      PublishItemOptionDO)
	 */
	public void publishItemWithOutUserPreview() {

		try {
			ItemDO item = ItemUtils.createItemDO(UserDataConstants.B_�̼�L);
			// item.setXXX(); �����Ʒ��һϵ������ �����Ϣ������ο�API�ĵ�
			item.setCategoryId(IcDemoConstants.ITEM_CATEGORY_FOOD);
			item.setSpuId(0L);
			item.setAuctionPoint(5);

			// ������Ʒʱ�������Ӱ˵�����Ҫ������������ӣ����緢��ʱ���û�ip,�˴η����Ƿ���ҪԤ�����Ƿ񷵻�SPU��Ϣ�ȵ�
			PublishItemOptionDO publicItemOption = new PublishItemOptionDO();
			publicItemOption.setLang("zh_CN"); // Υ��ؼ���У��Ŀͷ������ԣ��˴�����Ϊ�������ģ�zh_HK�������ģ�

			CreateItemResultDO result = itemServiceClient
					.publishItemWithOutUserPreview(item, publicItemOption,
							getLoomAppInfo());
			if (result.isSuccess()) {

				// do something
			} else {
				// ��ӡ������Ϣ
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
	 * ��������ⱦ��, ��ν����ⱦ��������qq���������ı���, һ���ύ���qq����, ����ÿ��qq��������һ������, ���һ�ε���, ���ɶ������
	 * 
	 * @see ItemService#publishNumberRangeItem(ItemDO,
	 *      String,PublishItemOptionDO)
	 */
	public void publishNumberRangeItem() {
		// ����һ��QQ������Ʒ
		String QQStr = "20440:27548;23571:49897;20416:10122;20417:27019;20432:10122";
		long options = 32L;
		String number = "8745589";

		try {

			ItemDO item = ItemUtils.createItemDO(110010101L);
			item.setCategoryId(4001L); // ������Ʒ��ĿΪQQ������Ŀ
			item.setProperty(QQStr); // ����QQ������Ŀ��������
			item.setOptions(options); // ������ƷΪ�������Ʒ
			PublishItemOptionDO publicItemOption = ItemUtils
					.createPublishItemOptionDO();
			CreateItemResultDO result = itemServiceClient
					.publishNumberRangeItem(item, number, publicItemOption,
							getLoomAppInfo());
			if (result.isSuccess()) {
				System.out
				.println("-------------------------�������뱦���ɹ�---------------------"+result.getItem());
				// do something
			} else {
				// ��ӡ������Ϣ
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
	 * ����ⱦ������ǰԤ��
	 * 
	 * @see ItemService#publishNumberRangeItemPreview(ItemDO,
	 *      String,PublishItemOptionDO)
	 */
	public void publishNumberRangeItemPreview() {
		// ����һ��QQ������Ʒ
		String QQStr = "20440:27548;23571:49897;20416:10122;20417:27019;20432:10122";
		long options = 32L;
		String number = "8745589";

		try {

			ItemDO item = ItemUtils.createItemDO(110010101L);
			item.setCategoryId(4001L); // ������Ʒ��ĿΪQQ������Ŀ
			item.setProperty(QQStr); // ����QQ������Ŀ��������
			item.setOptions(options); // ������ƷΪ�������Ʒ
			PublishItemOptionDO publicItemOption = ItemUtils
					.createPublishItemOptionDO();
			publicItemOption.setPreview(true);// ���ô˴η���ΪԤ�� ���������ݲ���

			CreateItemResultDO result = itemServiceClient
					.publishNumberRangeItemPreview(item, number,
							publicItemOption, getLoomAppInfo());
			if (result.isSuccess()) {

				// do something
			} else {
				// ��ӡ������Ϣ
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
	 * ���ұ༭���� ���ܲ�������ǰ�˾�����α���Ŀ����Ƿ�Ϊ������Ʒ(inputItem.setAutoConsignment()),
	 * ������ǿ��ܱ������򽫹���������ƷId�Ŀ��ܱ������м�¼����Ϊɾ��״̬��
	 * 
	 * @see ItemService#sellerSaveItem(long, long , ItemUpdateDO,
	 *      SaveItemOptionDO)
	 */
	public void sellerSaveItem() {

		try {
			// �༭������Ʒ
			long itemId = 1L;

			SaveItemOptionDO saveProcessOption = new SaveItemOptionDO();
			ItemUpdateDO inputItem = new ItemUpdateDO();
			inputItem.setDescription("description");
			// ������ƷΪ�Զ���������Ʒ
			inputItem.setOptions(268435456L);
			inputItem.setCategoryId(99L);
			inputItem.setProperty("1626031:27528;20435:27529");
			inputItem.setQuantity(0);
			inputItem.setAutoArea(null);
			saveProcessOption.setLang(PublishItemOptionDO.LANG_ZH_CN);
			saveProcessOption.setClientAppName("sell");

			SaveItemResultDO result = itemServiceClient.sellerSaveItem(itemId,
					UserDataConstants.C_�����Զ�����Ȩ��L, inputItem,
					saveProcessOption, getAppInfoDOForSell());
			if (result.isSuccess()) {

				// do something
			} else {
				// ��ӡ������Ϣ
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
	 * ���ұ༭���� ���ܲ�������ǰ�˾�����α������Ʒ�Ƿ�Ϊ������Ʒ(inputItem.setAutoConsignment()),
	 * ������ǿ��ܱ������򽫹���������ƷId�Ŀ��ܱ������м�¼����Ϊɾ��״̬��
	 * 
	 * @see ItemService#publishNumberRangeItem(ItemDO,
	 *      String,PublishItemOptionDO)
	 * 
	 * 
	 */
	public void sellerSaveNumberItem() {
		// ������Ȩ�����ұ༭������Ʒ_��Ʒ�Ŀ��ȫ������quantityΪ0

		try {
			// �༭������Ʒ
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
				// ��ӡ������Ϣ
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
	 * ���ұ༭����ǰ��Ԥ������������Ϣ��
	 * 
	 * @see ItemService#sellerSaveItemPreview(long, long , ItemUpdateDO,
	 *      SaveItemOptionDO)
	 */
	public void sellerSaveItemPreview() {
		try {
			// �༭������Ʒ
			long itemId = 1L;

			SaveItemOptionDO saveProcessOption = new SaveItemOptionDO();
			ItemUpdateDO inputItem = new ItemUpdateDO();
			inputItem.setDescription("description");
			// ������ƷΪ�Զ���������Ʒ
			inputItem.setOptions(268435456L);
			inputItem.setCategoryId(99L);
			inputItem.setProperty("1626031:27528;20435:27529");
			inputItem.setQuantity(0);
			inputItem.setAutoArea(null);
			saveProcessOption.setLang(PublishItemOptionDO.LANG_ZH_CN);
			saveProcessOption.setClientAppName("sell");

			SaveItemResultDO result = itemServiceClient.sellerSaveItemPreview(
					itemId, UserDataConstants.C_�����Զ�����Ȩ��L, inputItem,
					saveProcessOption, getAppInfoDOForSell());
			if (result.isSuccess()) {

				// do something
			} else {
				// ��ӡ������Ϣ
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
	 * �޸���Ʒ���ղ�����--ֻ���޸ģ� ��������
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
				// ��ӡ������Ϣ
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
	 * ����ɾ����Ʒ
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
				// ��ӡ������Ϣ
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
	 * ����������Ʒ����
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
				// ��ӡ������Ϣ
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
	 * ����������Ʒ����
	 * 
	 * @deprecated
	 * @see ItemService#sellerIncreaseItemQuantity(long, int,long,AppInfoDO) ��ʹ��
	 * @see ItemService#sellerIncreaseItemQuantity(long,
	 *      int,long,String,AppInfoDO)
	 * 
	 *      <font color="red">ע�⣺Ϊ�˷�ֹ�ظ��������ӿ�棬��Ҫ���÷�������uuid����������Ψһ��ʶĳ�β�����<br>
	 *      uuid���Ե���IC��װ�ķ���(�μ���</font>
	 *      {@link com.taobao.item.util.StringUtils#getUUID()} <font
	 *      color="red">)����ȡ�����÷����뱣֤ͬһ�β���ʱ��uuid����Ҫ��֤һ�¡������һ�ε������ӿ��ӿ�
	 *      ʱ������ic��ʱ�����÷����ܻ������Ե��ã���������±��뱣֤���Ե��õĲ���uuid���ֲ��䣬�Ա�IC�ܹ�
	 *      ����uuid�������÷����ظ������������Ϳ��Ա����ظ��ӿ�档
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
				// ��ӡ������Ϣ
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
	 * ������Ե���������sku������������
	 * 
	 * @see ItemService#sellerModifyItemSkuQuantity(long, long, Map, AppInfoDO)
	 */

	public void sellerModifyItemSkuQuantity() {
		// ��Ʒ��sku_��sku���
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
				// ��ӡ������Ϣ
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
	 * �����޸�ָ����Ʒsku���
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
				// ��ӡ������Ϣ
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
	 * �������ð��ľ���
	 * 
	 * @see ItemService#sellerSaveItemCharity(long,String, long)
	 */

	public void sellerSaveItemCharity() {

		long itemId = 1L;
		long cSellerId = UserDataConstants.C_��ͨc����L;
		try {
			itemServiceClient.sellerSaveItemCharity(itemId,
					";1:102;5:1;26:1;4:1122;", cSellerId);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ���������ʷ�ģ��
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
				// ��ӡ������Ϣ
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
	 * �������ó����Ƽ�
	 * 
	 * @see ItemService#sellerSaveItemRecommed(long , List)
	 * 
	 */

	public void sellerSaveItemRecommed() {
		// �������ó����Ƽ���Ʒ_���ҿ��Ƽ�����Ϊ5_�Ƽ�������Ʒ�ĳ���

		try {
			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(1L);
			itemIdList.add(2L);
			ShelfResultDO result = itemServiceClient.sellerSaveItemRecommed(
					IcDemoConstants.SELLER_ID_AUTH_LONG, itemIdList);
			if (result.isSuccess()) {

				// do something
			} else {
				// ��ӡ������Ϣ
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
	 * ����ɾ�������Ƽ�
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
				// ��ӡ������Ϣ
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
	 * �����ϼ���Ʒ
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
				// ��ӡ������Ϣ
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
	 * ���������¼���Ʒ
	 * 
	 * @see ItemService#sellerDownShelfItem(long,List,AppInfoDO)
	 * 
	 */

	public void sellerDownShelfItem() {
		// ���������¼���Ʒ_��ƷΪһ�ڼ���Ʒ_����ID��ȷ

		try {
			List<Long> itemIds = new ArrayList<Long>();
			itemIds.add(100005529645L);

			ShelfResultDO result = itemServiceClient.sellerDownShelfItem(
					IcDemoConstants.SELLER_ID_COMM_LONG, itemIds,
					getAppInfoDOForSell());

			if (result.isSuccess()) {
				// do something
			} else {
				// ��ӡ������Ϣ
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
	 * ɾ��zoo�ֶ��е����� �ض����ԣ���call.taobao.comӦ��ʹ�ã�������Ʒ���
	 * 
	 * @see ItemService#removeItemZoo(long,Map<Long, Long>)
	 * 
	 */

	public void removeItemZoo() {
		// ����ȡ���¼���Ʒ�Ĺ������

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
				// ��ӡ������Ϣ
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
	 * ����zoo�ֶ��е����� �ض����ԣ���call.taobao.comӦ��ʹ�ã�������Ʒ���
	 * 
	 * @see ItemService#updateItemZoo(long,Map<Long, Long>)
	 * 
	 */

	public void updateItemZoo() {
		// ���Ҹ����¼���Ʒ�Ĺ������

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
				// ��ӡ������Ϣ
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
	 * ��������ID����һ�����������Ʒid�������������� ��Ʒ������ʱ�򣬲�������������У���룬Ϊ�˱��������ظ������������Σ�
	 * Ԥ������һ��ID������ʱ��У��������Ҵ��š������ڷ��������У��������ȵ���Զ���½
	 * ���ܻ��л�Ϊ����һ���ʻ�����encodeId�б���������ʱ���û�ID������ʱ����У�飬��������
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
			// ��ӡ������Ϣ
			System.out
					.println("----------------------------------------------");
			log.error(result.getErrorStr());
			System.out
					.println("----------------------------------------------");
			// do something
		}
	}

	/**
	 * ��encodeId������ID���н��ܣ��������жϣ�ͨ���󷵻���ƷID�������ͨ����result����false
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
			// ��ӡ������Ϣ
			System.out
					.println("----------------------------------------------");
			log.error(result.getErrorStr());
			System.out
					.println("----------------------------------------------");
			// do something
		}
	}

	/**
	 * ����item��outerId��sku��outerId
	 * 
	 * @see ItemService#sellerModifyItemSkuOuterId(long, ItemUpdateDO ,
	 *      AppInfoDO )
	 */
	public void sellerModifyItemSkuOuterId() {
		// �����޸���Ʒ��SKU��OuterId�ֶξ���Ϊ��

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
				// ��ӡ������Ϣ
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
	 * �����ƶ����������¼ܵķ���
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
				// ��ӡ������Ϣ
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
	 * �����ƶ����������¼ܵķ���
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
							UserDataConstants.C_��ͨc����L, inputItem,
							IcDemoConstants.ITEM_NUMBER_QQ1, saveItemOptionDO,
							getAppInfoDOForSell());
			if (result.isSuccess()) {

				// do something
			} else {
				// ��ӡ������Ϣ
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
	 * ������Ʒֱͨ������Ϊֱͨ�������Լ�ֱͨ��������
	 * 
	 * @see ItemService#updateTaobaoSubway(long,int)
	 */
	public void updateTaobaoSubway_1() {
		try {

			long itemId = 1L;
			int P4Pflags = ItemConstants.POINT_PRICE_NOT_SUBWAY;// ��ֱͨ��
			// ItemConstantsPOINT_PRICE_SUBWAY_ONLINE ֱͨ������
			// ItemConstants.POINT_PRICE_SUBWAY_NOT_ONLINE; ֱͨ�������� --ֻ��ȡ������ֵ֮һ
			ProcessResultDO result = itemServiceClient.updateTaobaoSubway(
					itemId, P4Pflags);// ����Ϊ��ֱͨ��

			if (result.isSuccess()) {

				// do something
			} else {
				// ��ӡ������Ϣ
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
	 * ������ƷΪ�Ա�ֱͨ����ֱͨ������
	 * 
	 * @see ItemService#updateTaobaoSubway(long,boolean)
	 */
	public void updateTaobaoSubway() {
		try {

			long itemId = 1L;
			ProcessResultDO result = itemServiceClient.updateTaobaoSubway(
					itemId, false);// ����Ϊ��ֱͨ��

			if (result.isSuccess()) {

				// do something
			} else {
				// ��ӡ������Ϣ
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
	 * ������Ʒ�Ĵ���
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
				// ��ӡ������Ϣ
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
	 * ������Ʒ������Ŀ�ֶ�
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
				// ��ӡ������Ϣ
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
	 * �����������Ʒ�ĵ���ͼƬ��imageData���Ƿ����к����ݣ��ⲿҪУ��
	 * 
	 * @see ItemService#sellerUploadCommonItemImage(long, long, ItemImageDO)
	 */
	public void sellerUploadCommonItemImage() {
		// �ϴ���Ʒ����ͼ
		try {
			long sellerId = UserDataConstants.C_��ͼ�û�L;
			long itemId = 1L;
			ItemImageDO itemImage = ItemUtils.createItemImageDO(sellerId,
					itemId, 1, false);
			itemImage.setMajor(false);

			ResultDO<ItemImageDO> result = itemServiceClient
					.sellerUploadCommonItemImage(itemId, sellerId, itemImage);

			if (result.isSuccess()) {

				// do something
			} else {
				// ��ӡ������Ϣ
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
	 * �����򱣴�ͼƬ��tfs��
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
			// ��ӡ������Ϣ
			System.out
					.println("----------------------------------------------");
			log.error(result.getErrorStr());
			System.out
					.println("----------------------------------------------");
			// do something
		}
	}

	/**
	 * ����ɾ������ͼƬ������ͼƬ
	 * 
	 * @see ItemService#sellerDelItemImage(long, long,long)
	 */
	public void sellerDelItemImage() {

		try {
			long sellerId = UserDataConstants.C_��ͼ�û�L;
			long itemId = 1L;
			long itemImageId = 3L;
			;
			@SuppressWarnings("rawtypes")
			ResultDO result = itemServiceClient.sellerDelItemImage(itemId,
					itemImageId, sellerId);

			if (result.isSuccess()) {

				// do something
			} else {
				// ��ӡ������Ϣ
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
	 * ����ɾ������ͼƬ������ͼƬ
	 * 
	 * @see ItemService#sellerUploadPropertyImage(long, long, ItemImageDO)
	 */
	public void sellerUploadPropertyImage() {

		try {

			Long itemId = 0L;
			Long sellerId = UserDataConstants.C_��ͼ�û�L;
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
				// ��ӡ������Ϣ
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
	 * ����ʹ����ָ���ʷ�ģ���������Ʒ���ʷ�
	 * 
	 * @see ItemService#refreshItemPostage(List)
	 */
	public void refreshItemPostage() {
		// ���µ�����Ʒ�ʷ�ģ��

		Long postageId = 1L;
		Long sellerId = UserDataConstants.C_��ͨc����L;
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
	 * ����ɾ����Ƶ����
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
				// ��ӡ������Ϣ
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
	 * ������Ƶid�б��isv id��ɾ����Ƶ�������еļ�¼
	 * 
	 * @see ItemService#delVideoItemByVideoId(Long[],long)
	 */
	public void delVideoItemByVideoId() {
		// ��Ƶ���ݶ���icmisc��_ɾ��10������

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
				// ��ӡ������Ϣ
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
	 * Ϊ��Ӧ����Ʒ���ӡ��޸Ļ�ɾ����features����
	 * 
	 * @see ItemService#saveItemFeatures(Map,Map)
	 */
	public void saveItemFeatures() {
		// ��Ϊһ����Ʒ�޸Ķ��feature

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
				// ��ӡ������Ϣ
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
	 * ������Ʒ�ۼ��۳��� ΪB��Ʒʹ�ã������ۼ��۳���C��Ʒ��Ҫ�������30����
	 * �۳�����Ҫ����TcReadService.sumAuctionQuantity����
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
				// ��ӡ������Ϣ
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
	 * �����û��ַ���id����item����id������֤�û��ַ���id����Ʒ����id����żӳ ���ϵ
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
	 * ��������sku�ֲ�
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
				// ��ӡ������Ϣ
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
	 * ���������ֲ�
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
				// ��ӡ������Ϣ
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
	 * ɾ�������ֲ�
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
				// ��ӡ������Ϣ
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
	 * ȫ��ͬ�������ֲֿ��
	 * 
	 * @see 
	 *      ItemService#sellerModifyAuctionStoreQuantity(long,Map,Integer>,AppInfoDO
	 *      )
	 * 
	 * @deprecated
	 */
	public void sellerModifyAuctionStoreQuantity() {
		// �������±�������ֲ�quantity

		try {
			long sellerId = UserDataConstants.C_��ͨc����L;

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
				// ��ӡ������Ϣ
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
	 * ��ȡloom Ӧ�õ�appInfo ,��ʵ�ʵ�Ӧ�ó����� opertion,memo,operator ������ú�ҵ����صĲ���
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
			skuList.add(itemSkuDO); // ����skuList
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
