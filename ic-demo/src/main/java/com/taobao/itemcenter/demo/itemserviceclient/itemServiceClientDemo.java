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
		printLine("����removeItemOptions����");
		List<Long> options = new ArrayList<Long>();
		options.add(1L);// ȥ������ �����Ա���۵� ����
		options.add(8L);// ȥ������ ���̳Ǳ����� ����
		try {
			ProcessResultDO result = itemServiceClient.removeItemOptions(
					itemId, options, getLoomAppInfo());
			if (result.isSuccess()) {
				printLine("����removeItemOptions�����ɹ�");
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
       printLine("����publishItem����");
		try {
			ItemDO item = ItemUtils.createItemDO(userId);
			// item.setXXX(); �����Ʒ��һϵ������ �����Ϣ������ο�API�ĵ�
			item.setCategoryId(IcDemoConstants.ITEM_CATEGORY_FOOD);
			item.setProperty("1930001:27772");
			item.setSpuId(0L);
			item.setAuctionPoint(5);
			// ������Ʒʱ�������Ӱ˵�����Ҫ������������ӣ����緢��ʱ���û�ip,�˴η����Ƿ���ҪԤ�����Ƿ񷵻�SPU��Ϣ�ȵ�
			PublishItemOptionDO publicItemOption=ItemUtils.createPublishItemOptionDO();
			publicItemOption.setLang("zh_CN"); // Υ��ؼ���У��Ŀͷ������ԣ��˴�����Ϊ�������ģ�zh_HK�������ģ�
            CreateItemResultDO result = itemServiceClient.publishItem(item,
					publicItemOption, getAppInfoDOForSell());
			if (result.isSuccess()) {
                 printLine("����publishItem�����ɹ�"+result.getItem().getItemId());
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
       printLine("����publishItemWithOutUserPreview����");
       final Long C_SELLER_NUM_ID = UserDataConstants.C_��ͨc����L;
       final Long SELL_AND_TMALLSELL_LIMIT_CAT_ID = 201108263L;
		try {
			 ItemDO item = ItemUtils.createItemDO(C_SELLER_NUM_ID, SELL_AND_TMALLSELL_LIMIT_CAT_ID, false);
		        item.setOptions(item.getOptions() | ItemOptionsConstants.ITEM_OPTIONS_BBC_ITEM);
		      
			// item.setXXX(); �����Ʒ��һϵ������ �����Ϣ������ο�API�ĵ�
			 item.setOptions(item.getOptions() | ItemOptionsConstants.ITEM_OPTIONS_BBC_ITEM);

			// ������Ʒʱ�������Ӱ˵�����Ҫ������������ӣ����緢��ʱ���û�ip,�˴η����Ƿ���ҪԤ�����Ƿ񷵻�SPU��Ϣ�ȵ�
			 PublishItemOptionDO publicItemOption = ItemUtils.createPublishItemOptionDO();
			publicItemOption.setLang("zh_CN"); // Υ��ؼ���У��Ŀͷ������ԣ��˴�����Ϊ�������ģ�zh_HK�������ģ�

			CreateItemResultDO result = itemServiceClient
					.publishItemWithOutUserPreview(item, publicItemOption,
							getLoomAppInfo());
			if (result.isSuccess()) {
				printLine("����publishItemWithOutUserPreview�����ɹ�");
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
		printLine("����publishNumberRangeItem�������뱦��");
		String QQStr = "20440:27548;23571:49897;20416:10122;20417:27019;20432:10122";
		long options = 32L;
		String number = "8745589";

		try {

			ItemDO item = ItemUtils.createItemDO(userId);
			item.setCategoryId(4001L); // ������Ʒ��ĿΪQQ������Ŀ
			item.setProperty(QQStr); // ����QQ������Ŀ��������
			item.setOptions(options); // ������ƷΪ�������Ʒ
			PublishItemOptionDO publicItemOption = ItemUtils
					.createPublishItemOptionDO();
			CreateItemResultDO result = itemServiceClient
					.publishNumberRangeItem(item, number, publicItemOption,
							getLoomAppInfo());
			if (result.isSuccess()) {
				printLine("����publishNumberRangeItem�������뱦���ɹ�"+result.getItem());
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
        printLine("����sellerSaveItem����");
          ItemDO item=publisItem(userId,false).getItem();
          printLine(""+item.getItemId());
		try {
			// �༭������Ʒ

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
				  printLine("����sellerSaveItem�����ɹ�");
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
        printLine("����sellerSaveNumberItem����");
		try {
			// �༭������Ʒ
			ItemUpdateDO inputItem = new ItemUpdateDO();
			inputItem.setStuffStatus(IcDemoConstants.ITEM_IDEL);
			inputItem.setStarts(IcDemoConstants.ITEM_UPDATE_STARTS);
			SaveItemOptionDO saveItemOptionDO = new SaveItemOptionDO();
			saveItemOptionDO.setLang(SaveItemOptionDO.LANG_ZH_CN);
			SaveItemResultDO result = itemServiceClient.sellerSaveNumberItem(
					1500006032465L, 110010101L, inputItem,
					"8745589", saveItemOptionDO, getAppInfoDOForSell());
			if (result.isSuccess()) {
				   printLine("����sellerSaveNumberItem�����ɹ�");
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
	printLine("���� modifyItemCollectionCount����");
		Long itemId = 81120001L;
		long collectionCount = 4;
		ProcessResultDO result;
		try {
			result = itemServiceClient.modifyItemCollectionCount(itemId,
					collectionCount);
			if (result.isSuccess()) {
				printLine("���� modifyItemCollectionCount�����ɹ�");
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
                  printLine("����sellerDelItem����");
		try {
			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(100005529637L);
			itemIdList.add(100005529639L);

			ShelfResultDO result = itemServiceClient.sellerDelItem(
					IcDemoConstants.SELLER_ID_COMM_LONG, itemIdList,
					getUnknowAppInfo());

			if (result.isSuccess()) {
				  printLine("����sellerDelItem�����ɹ�");
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
      printLine("����sellerModifyItemQuantity����");
     
		try {
			ProcessResultDO result = itemServiceClient
					.sellerModifyItemQuantity(
							 publisItem(userId, false).getItem().getItemId(), 3,
							userId,
							getOtherAppInfo());

			if (result.isSuccess()) {
				 printLine("����sellerModifyItemQuantity���� �ɹ�");
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
		printLine("����sellerModifyItemSkuQuantity����");
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
				printLine("����sellerModifyItemSkuQuantity�����ɹ�");
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
       printLine("������sellerIncreaseItemSkuQuantity����");
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
				 printLine("������sellerIncreaseItemSkuQuantity�����ɹ�");
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
		} }catch (IcException e) {
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
       printLine("����sellerSaveItemCharity����");
		long itemId = 1L;
		long cSellerId = UserDataConstants.C_��ͨc����L;
		try {
			itemServiceClient.sellerSaveItemCharity(itemId,
					";1:102;5:1;26:1;4:1122;", cSellerId);
			   printLine("����sellerSaveItemCharity�����ɹ�");
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
       printLine("����sellerSaveItemPostage����");
		try {
			List<Long> itemIdList = new ArrayList<Long>();
			Long itemId1 = publisItem(userId,false).getItem().getItemId();
			Long itemId2 = publisItem(userId,false).getItem().getItemId();
		       printLine("���������ɹ�"+itemId1+"    "+itemId2);

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
				  printLine("����sellerSaveItemPostage�����ɹ�");
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
          printLine("����sellerSaveItemRecommed����");
		try {
			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(publisItem(userId,false).getItem().getItemId());
			itemIdList.add(publisItem(userId,false).getItem().getItemId());
			ShelfResultDO result = itemServiceClient.sellerSaveItemRecommed(
				 userId, itemIdList);
			if (result.isSuccess()) {
		          printLine("����sellerSaveItemRecommed�����ɹ�");

				// do something
			} else {
				// ��ӡ������ϢO
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
		 printLine("����sellerSaveItemUnRecommed����");
		try {
			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(publisItem(userId,false).getItem().getItemId());
			itemIdList.add(publisItem(userId,false).getItem().getItemId());
			ShelfResultDO result = itemServiceClient.sellerSaveItemUnRecommed(
					userId, itemIdList);
			if (result.isSuccess()) {
				 printLine("����sellerSaveItemUnRecommed�����ɹ�");
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
			printLine("����sellerUpShelfItem");
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
				printLine("����sellerUpShelfItem�����ɹ�");
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
       printLine("����sellerDownShelfItem����");
		try {
			List<Long> itemIds=new ArrayList<Long>();
			ItemDO item=publisItem(userId,false).getItem();
			itemIds.add(item.getItemId());
			ShelfResultDO result = itemServiceClient.sellerDownShelfItem(
					userId,itemIds,getAppInfoDOForSell());
			if (result.isSuccess()) {
				 printLine("����sellerDownShelfItem�����ɹ�");
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
		printLine("����removeItemZoo����");
		try {
			Map<Long, Long> zooPairList = new HashMap<Long, Long>();
			zooPairList.put(4L, 104L);
			zooPairList.put(1L, 102L);
			zooPairList.put(5L, 1L);
			ProcessResultDO result = itemServiceClient.removeItemZoo(itemId,
					zooPairList);

			if (result.isSuccess()) {
				printLine("����removeItemZoo�����ɹ�");

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
             printLine("����updateItemZoo����");
		try {
			Map<Long, Long> zooPairList = new HashMap<Long, Long>();
			zooPairList.put(4L, 104L);
			zooPairList.put(1L, 102L);
			zooPairList.put(5L, 1L);
			ProcessResultDO result = itemServiceClient.updateItemZoo(itemId,
					zooPairList);

			if (result.isSuccess()) {
				 printLine("����updateItemZoo�����ɹ�");
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
       printLine("����sellerModifyItemSkuOuterId����");
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
				printLine("����sellerModifyItemSkuOuterId�����ɹ�");
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
   printLine("����sellerMoveItemToStock����");
		try {
			long itemId = publisItem(userId, false).getItem().getItemId();
			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(itemId);
			ShelfResultDO result = itemServiceClient.sellerMoveItemToStock(
					userId, itemIdList);
            if (result.isSuccess()) {
				printLine("����sellerMoveItemToStock�����ɹ�");
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
		printLine("����updateTaobaoSubway_1����");
		try {
			int P4Pflags = ItemConstants.POINT_PRICE_NOT_SUBWAY;// ��ֱͨ��
			// ItemConstantsPOINT_PRICE_SUBWAY_ONLINE ֱͨ������
			// ItemConstants.POINT_PRICE_SUBWAY_NOT_ONLINE; ֱͨ�������� --ֻ��ȡ������ֵ֮һ
			ProcessResultDO result = itemServiceClient.updateTaobaoSubway(
					itemId, P4Pflags);// ����Ϊ��ֱͨ��

			if (result.isSuccess()) {
				printLine("����updateTaobaoSubway_1�����ɹ�");
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
		printLine("����updateTaobaoSubway����");
		try {

			ProcessResultDO result = itemServiceClient.updateTaobaoSubway(
					itemId, false);// ����Ϊ��ֱͨ��

			if (result.isSuccess()) {
				printLine("����updateTaobaoSubway�����ɹ�");
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
      printLine("����sellerSaveItemBonus����");
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
				printLine("����sellerSaveItemBonus�����ɹ�");
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
			printLine("����updateCategoryInShop����");
			Long itemId = 81000001L;
			String categoryId = "50010360";

			ProcessResultDO result = itemServiceClient.updateCategoryInShop(
					itemId, categoryId);

			if (result.isSuccess()) {
				printLine("����updateCategoryInShop�����ɹ�");
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
		printLine("����sellerUploadCommonItemImage����");
		try {
			String[] filenames=new String[]{"src/main/resources/itemServiceClient/test.jpg"};
			List<ItemImageDO> itemImage = ItemUtils.getImageList(filenames);
			itemImage.get(0).setImageData(getInputImage(filenames[0]));
			itemImage.get(0).setMajor(false);
			itemImage.get(0).setUserId(userId);

			ResultDO<ItemImageDO> result = itemServiceClient
					.sellerUploadCommonItemImage(itemId, userId, itemImage.get(0));

			if (result.isSuccess()) {
				printLine("����sellerUploadCommonItemImage�����ɹ�");
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
	public void saveImageToTfs(){
		printLine("����saveImageToTfs����");

		
		byte[] imageData =getInputImage(fileName);
		boolean needMainColor = false;
		SavePictureFileResultDO result = itemServiceClient.saveImageToTfs(
				fileName, imageData, needMainColor);
		printLine(""+result.getResultCode());
		if (result.isSuccess()) {
			printLine("����saveImageToTfs�����ɹ�");
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

          printLine("����sellerDelItemImage����");
      	ResultDO result = null; 
		try {
			 CreateItemResultDO cre=publish_��ͼ_Item(2, false);
			 
			//׼��ɾ������
			long itemImageId =cre.getItem().getCommonItemImageList().get(0).getImageId();
			if(cre.isFailure()){
	             printLine("����ͼƬʧ��");
	             return ;
			}
			result 	= itemServiceClient.sellerDelItemImage(itemId,
					itemImageId, UserDataConstants.C_��ͼ�û�L);
            if (result.isSuccess()) {
				 printLine("����sellerDelItemImage�����ɹ�");
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
	    		System.out
				.println("----------------------------------------------");
		log.error(result.getErrorStr());
		System.out
				.println("----------------------------------------------");
		 }
	}

	/**
	 * ����ɾ������ͼƬ������ͼƬ
	 * 
	 * @see ItemService#sellerUploadPropertyImage(long, long, ItemImageDO)
	 */
	public void sellerUploadPropertyImage() {
		printLine("����sellerUploadPropertyImage����");

		try {
			ItemImageDO itemImage = new ItemImageDO();
			itemImage.setItemId(itemId);
			itemImage.setType(2);
			itemImage.setImageUrl("test");
			itemImage.setProperties("20000:10681");
			ResultDO<ItemImageDO> result = itemServiceClient
					.sellerUploadPropertyImage(itemId, userId, itemImage);

			if (result.isSuccess()) {
				printLine("����sellerUploadPropertyImage�����ɹ�");
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
         printLine("����refreshItemPostage");
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
		printLine("����refreshItemPostage�ɹ�");
	}

	/**
	 * ����ɾ����Ƶ����
	 * 
	 * @see ItemService#delVideoItems(List)
	 */
	public void delVideoItems() {
		printLine("����delVideoItems����");
		try {

			List<Long> itemIdList = new ArrayList<Long>();
			itemIdList.add(1L);
			itemIdList.add(2L);
			BatchItemVideoResultDO result = itemServiceClient
					.delVideoItems(itemIdList);
			if (result.isSuccess()) {
				printLine("����delVideoItems�����ɹ�");
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
			 long[] videoIds = publishItemVideo(10);
			 long isvId = 888;
			 BatchItemVideoResultDO result = itemServiceClient.delVideoItemByVideoId(videoIds, isvId);
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
      printLine("����saveItemFeatures����");
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
				  printLine("����saveItemFeatures�����ɹ�");
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
       printLine("����updateSumAuctionQuantity����");
		try {

			int auctionQuantity = 100;
			BaseResultDO result = itemServiceClient.updateSumAuctionQuantity(
					itemId, auctionQuantity);

			if (result.isSuccess()) {
				 printLine("����updateSumAuctionQuantity�����ɹ�");
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
//		  itemServiceClien.sellerIncreaseItemSkuQuantity;//�����Ĳ��뷢sku��  
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
		//itemServiceClien.sellerUploadCommonItemImage();//��������������������������
		//itemServiceClien.sellerUploadPropertyImage();
		//itemServiceClien.sellerUpShelfItem();
		//itemServiceClien.updateCategoryInShop();
		//itemServiceClien.updateItemZoo();
		//itemServiceClien.updateSumAuctionQuantity();
		//itemServiceClien.updateTaobaoSubway();
		//itemServiceClien.updateTaobaoSubway_1();
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

		ResultDO<ItemDO> result = itemQueryServiceClient
				.queryItemForDetail(itemId);
		dbSkuList = result.getModule().getSkuList();
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
	private static CreateItemResultDO publish_��ͼ_Item(int imageQuantity, boolean hasVideo) throws IcException{
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
		video.setUserId(UserDataConstants.C_��ͼ�û�L);
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
		// item.setXXX(); �����Ʒ��һϵ������ �����Ϣ������ο�API�ĵ�
		item.setCategoryId(IcDemoConstants.ITEM_CATEGORY_FOOD);
		item.setProperty("1930001:27772");
		item.setSpuId(0L);
		item.setAuctionPoint(5);
		
		// ������Ʒʱ�������Ӱ˵�����Ҫ������������ӣ����緢��ʱ���û�ip,�˴η����Ƿ���ҪԤ�����Ƿ񷵻�SPU��Ϣ�ȵ�
		PublishItemOptionDO publicItemOption=ItemUtils.createPublishItemOptionDO();
		publicItemOption.setLang("zh_CN"); // Υ��ؼ���У��Ŀͷ������ԣ��˴�����Ϊ�������ģ�zh_HK�������ģ�
		

		
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
	 * ��ӡһ�У�������
	 * 
	 * @param title
	 *            ����
	 */
	private static void printLine(String title) {
		System.out
				.println(String
						.format("-------------------------------------------%s-------------------------------------------\n",
								title));
	}

}
