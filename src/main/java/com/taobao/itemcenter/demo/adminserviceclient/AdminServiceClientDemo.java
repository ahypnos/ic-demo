package com.taobao.itemcenter.demo.adminserviceclient;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.collect.Maps;
import com.taobao.item.constant.AppInfoConstants;
import com.taobao.item.constant.ItemConstants;
import com.taobao.item.constant.ItemStatusConstants;
import com.taobao.item.domain.AppInfoDO;
import com.taobao.item.domain.ItemDO;
import com.taobao.item.domain.ItemUpdateDO;
import com.taobao.item.domain.ItemVideoDO;
import com.taobao.item.domain.result.BaseResultDO;
import com.taobao.item.domain.result.BatchItemVideoResultDO;
import com.taobao.item.domain.result.ProcessResultDO;
import com.taobao.item.domain.result.ResultDO;
import com.taobao.item.domain.result.XiaoProcessResultDO;
import com.taobao.item.exception.IcException;
import com.taobao.item.service.AdminService;
import com.taobao.item.service.client.AdminServiceClient;
import com.taobao.uic.common.domain.BaseUserDO;

/**
 * ÿһ���ӿڵ�ʹ��
 * 
 * AdminService ����ӿڵ�ʾ�����Ǻ���Ҫ,��Ϊ����ӵĲ������Ƚϼ�,����û��������������
 * 
 * @author tieyi.qlr
 * 
 */
public class AdminServiceClientDemo {

	private AdminServiceClient adminServiceClient;

	public AdminServiceClientDemo() {
		String[] location = { "adminServiceClient/spring-ic-hsf.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(
				location);
		adminServiceClient = (AdminServiceClient) context
				.getBean("adminServiceClient");
	}

	/**
	 * see
	 * {@link AdminService#xiaoerDelItem(java.util.List, com.taobao.item.domain.AppInfoDO)}
	 */
	public void xiaoerDelItem() {
		// Ҫɾ������ƷID �б�..һ����ഫ20��
		List<Long> itemIds = newArrayList();
		itemIds.add(1L);

		// ��ic ���ƿ��ؿ���֮�� ֻ��loom Ӧ�òų�����������ӿ�
		try {
			XiaoProcessResultDO result = adminServiceClient.xiaoerDelItem(
					itemIds, getLoomAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// ����ʧ�ܵ�ID�б�
				List<Long> failList = result.getErrorItemIdList();
				System.out.println(failList + "  operation fail");
				// do something
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	/**
	 * see {@link AdminService#xiaoerUpdateItemStatus(List, int, AppInfoDO)}
	 * status see {@link ItemStatusConstants}
	 */
	public void xiaoerUpdateItemStatus() {
		// һ����ഫ20��
		List<Long> itemIds = newArrayList();
		itemIds.add(1L);
		try {
			XiaoProcessResultDO result = adminServiceClient
					.xiaoerUpdateItemStatus(itemIds,
							ItemStatusConstants.NORMAL, getLoomAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// ����ʧ�ܵ�ID�б�
				List<Long> failList = result.getErrorItemIdList();
				System.out.println(failList + "  operation fail");
				// do something
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	/**
	 * see {@link AdminService#xiaoerUpdateItemFields(List, AppInfoDO)}
	 */
	public void xiaoerUpdateItemFieldsLA() {
		List<ItemUpdateDO> updateList = newArrayList();
		ItemUpdateDO updateDO = new ItemUpdateDO(1L);
		updateDO.setQuantity(5);
		updateList.add(updateDO);

		try {
			XiaoProcessResultDO result = adminServiceClient
					.xiaoerUpdateItemFields(updateList, getLoomAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// ����ʧ�ܵ�ID�б�
				List<Long> failList = result.getErrorItemIdList();
				System.out.println(failList + "  operation fail");
				// do something
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	/**
	 * see
	 * {@link AdminService#xiaoerUpdateItemFields(long, ItemUpdateDO, AppInfoDO)}
	 */
	public void xiaoerUpdateItemFieldsLIA() {
		ItemUpdateDO updateDO = new ItemUpdateDO(1L);
		updateDO.setQuantity(5);

		try {
			XiaoProcessResultDO result = adminServiceClient
					.xiaoerUpdateItemFields(1L, updateDO, getOtherAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// ����ʧ�ܵ�ID�б� (���ﲻ����������,��������б��������ô�����)
				List<Long> failList = result.getErrorItemIdList();
				System.out.println(failList + "  operation fail");
				// do something
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	/**
	 * see {@link AdminService#xiaoerSaveItem(long, ItemUpdateDO, AppInfoDO)}
	 */
	public void xiaoerSaveItem() {
		ItemUpdateDO updateDO = new ItemUpdateDO(1L);
		updateDO.setQuantity(5);
		// ����������SKU skuList ���Դ�null,������Ҫ����,needClearSkuWhenSkuListNullΪfalse
		// ....
		//
		try {
			ProcessResultDO result = adminServiceClient.xiaoerSaveItem(1L,
					updateDO, getOtherAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// do something
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	/**
	 * see
	 * {@link AdminService#cronCheckFixPriceItemAndDownShelf(List, AppInfoDO)}
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public void cronCheckFixPriceItemAndDownShelf() {
		// һ����ഫ20��
		List<Long> itemIds = newArrayList();
		itemIds.add(1L);

		ProcessResultDO result = adminServiceClient
				.cronCheckFixPriceItemAndDownShelf(itemIds, getOtherAppInfo());
		if (result.isSuccess()) {
			// do something
		} else {
			// do something
			// �ط�ʧ�ܵ�
			List<ItemDO> fail = (List<ItemDO>) result.getContext().get(
					"failItems");
			// �ط�ʧ�ܵ�ʧ�� itemId Ϊkey
			Map<Long, String> reasons = (Map<Long, String>) result.getContext()
					.get("reasons");
		}
	}

	/**
	 * ����ӿڼ�������,���鲻Ҫʹ�� see {@link AdminService#cronUpdateItem(List, AppInfoDO)}
	 */
	public void cronUpdateItem() {
		List<ItemUpdateDO> updateItemList = newArrayList();
		ItemUpdateDO updateDO = new ItemUpdateDO(1L);
		updateDO.setQuantity(5);
		try {
			XiaoProcessResultDO result = adminServiceClient.cronUpdateItem(
					updateItemList, getOtherAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// ����ʧ�ܵ�ID�б�
				List<Long> failList = result.getErrorItemIdList();
				System.out.println(failList + "  operation fail");
				// do something
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	/**
	 * see {@link AdminService#xiaoerDownShelfItem(List, int, AppInfoDO)}
	 * whereStatus <li>���е���Ʒ��ItemConstants.XIAOER_UPDATE_STATUS_ALL</li> <li>
	 * δ���ۣ�ItemConstants.XIAOER_UPDATE_STATUS_NOT_BID<
	 */
	public void xiaoerDownShelfItem_LIA() {
		// һ����ഫ50��
		List<Long> itemIds = newArrayList();
		itemIds.add(1L);

		try {
			XiaoProcessResultDO result = adminServiceClient
					.xiaoerDownShelfItem(itemIds,
							ItemConstants.XIAOER_UPDATE_STATUS_ALL,
							getLoomAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// ����ʧ�ܵ�ID�б�
				List<Long> failList = result.getErrorItemIdList();
				System.out.println(failList + "  operation fail");
				// do something
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	/**
	 * see {@link AdminService#xiaoerDownShelfItem(Map, int, AppInfoDO)}
	 */
	public void xiaoerDownShelfItem_MIA() {
		// key ΪitemId,valueΪ gmt_modified ���µ�ʱ����Ϊ���ݿ���ֹ���
		Map<Long, Date> itemIdAndGmt = Maps.newHashMap();
		itemIdAndGmt.put(1L, new Date());

		try {
			XiaoProcessResultDO result = adminServiceClient
					.xiaoerDownShelfItem(itemIdAndGmt,
							ItemConstants.XIAOER_UPDATE_STATUS_ALL,
							getLoomAppInfo());

			if (result.isSuccess()) {
				// do something
			} else {
				// ����ʧ�ܵ�ID�б�
				List<Long> failList = result.getErrorItemIdList();
				System.out.println(failList + "  operation fail");
				// do something
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	/**
	 * see {@link AdminService#xiaoerSetItemToCc(List, AppInfoDO)}
	 */
	public void xiaoerSetItemToCc_LA() {
		// һ����ഫ50��
		List<Long> itemIds = newArrayList();
		itemIds.add(1L);

		try {
			XiaoProcessResultDO result = adminServiceClient.xiaoerSetItemToCc(
					itemIds, getLoomAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// ����ʧ�ܵ�ID�б�
				List<Long> failList = result.getErrorItemIdList();
				System.out.println(failList + "  operation fail");
				// do something
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	/**
	 * see {@link AdminService#xiaoerSetItemToCc(List, AppInfoDO)}
	 */
	public void xiaoerSetItemToCc_MA() {
		// key ΪitemId,valueΪ gmt_modified ���µ�ʱ����Ϊ���ݿ���ֹ���, һ����ഫ50��
		Map<Long, Date> itemIdAndGmt = Maps.newHashMap();
		itemIdAndGmt.put(1L, new Date());

		try {
			XiaoProcessResultDO result = adminServiceClient.xiaoerSetItemToCc(
					itemIdAndGmt, getLoomAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// ����ʧ�ܵ�ID�б�
				List<Long> failList = result.getErrorItemIdList();
				System.out.println(failList + "  operation fail");
				// do something
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	/**
	 * see {@link AdminService#xiaoerDelItem(BaseUserDO, List, int, AppInfoDO)}
	 */
	public void xiaoerDelItem_BLIA() {
		BaseUserDO seller = new BaseUserDO();
		seller.setUserId(1L);
		seller.setNick(StringUtils.EMPTY);

		// һ����ഫ50��
		List<Long> itemIds = newArrayList();
		itemIds.add(1L);

		try {
			XiaoProcessResultDO result = adminServiceClient.xiaoerDelItem(
					seller, itemIds, ItemConstants.XIAOER_UPDATE_STATUS_ALL,
					getLoomAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// ����ʧ�ܵ�ID�б�
				List<Long> failList = result.getErrorItemIdList();
				System.out.println(failList + "  operation fail");
				// do something
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	/**
	 * itemVideo ����Ϊ�� itemVideo.getId() >= 1 itemVideo.getItemId() > 0
	 * ItemVideoDO.STATUS_FROZEN == itemVideo.getStatus() ||
	 * ItemVideoDO.STATUS_NORMAL == itemVideo.getStatus()
	 */
	public void xiaoerUpdateItemVideo() {
		ItemVideoDO itemVideo = new ItemVideoDO();
		itemVideo.setId(1L);
		/**
		 * ... ... set ����ֵ
		 */
		try {
			ResultDO result = adminServiceClient.xiaoerUpdateItemVideo(
					itemVideo, getOtherAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// do something
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	public void xiaoerUpdateItemVideos() {
		List<ItemVideoDO> videoList = newArrayList();
		ItemVideoDO itemVideo = new ItemVideoDO();
		itemVideo.setId(1L);
		/**
		 * ... ... set ����ֵ
		 */
		videoList.add(itemVideo);

		try {
			// ȫ���ɹ���ʱ��ŷ��سɹ�
			BatchItemVideoResultDO result = adminServiceClient
					.xiaoerUpdateItemVideos(videoList, getOtherAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				List<Long> failList = result.getErrorVideoIdList();
				// do something
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	/**
	 * see {@link AdminService#xiaoerModifyItemStatus(long, int, AppInfoDO)}
	 * status ����Ϊ�� ItemStatusConstants.DELETED_BY_XIAOER,
	 * ItemStatusConstants.DELETED, ItemStatusConstants.CC,
	 * ItemStatusConstants.NORMAL, ItemStatusConstants.PASS
	 */
	public void xiaoerModifyItemStatus() {
		try {
			BaseResultDO result = adminServiceClient.xiaoerModifyItemStatus(1L,
					ItemStatusConstants.DELETED_BY_XIAOER, getLoomAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// do something
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	/**
	 * see
	 * {@link AdminService#xiaoerRollbackDeletedItem(long, int, Date, AppInfoDO)}
	 */
	public void xiaoerRollbackDeletedItem() {
		BaseResultDO result = adminServiceClient.xiaoerRollbackDeletedItem(1L,
				ItemStatusConstants.PASS, new Date(), getLoomAppInfo());
		if (result.isSuccess()) {
			// do something
		} else {
			// do something
		}
	}

	/**
	 * see
	 * {@link AdminService#cronUpdateQuantityAndDownShelfBidItem(long, Integer, AppInfoDO)}
	 */
	public void cronUpdateQuantityAndDownShelfBidItem() {
		try {
			XiaoProcessResultDO result = adminServiceClient
					.cronUpdateQuantityAndDownShelfBidItem(1L, 0,
							getOtherAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// do something
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	/**
	 * see
	 * {@link AdminService#xiaoerUpdateItemSpuInfo(List, ItemUpdateDO, AppInfoDO)}
	 */
	public void xiaoerUpdateItemSpuInfo() {
		List<Long> itemIds = newArrayList();
		itemIds.add(1L); // ���40��

		ItemUpdateDO updateDO = new ItemUpdateDO(1L);
		updateDO.setSpuId(111L);
		updateDO.setReplaceCategoryIdOfItemFromSpu(true);
		updateDO.setReplaceKeyPropertiesOfItemFromSpu(true);
		try {
			// ֻҪ��һ���ɹ��ͷ��سɹ�
			XiaoProcessResultDO result = adminServiceClient
					.xiaoerUpdateItemSpuInfo(itemIds, updateDO,
							getLoomAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				List<Long> failList = result.getErrorItemIdList();
				// do something
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	public void touch() {
		List<Long> itemIds = newArrayList();
		itemIds.add(1L); // ���20��

		try {
			XiaoProcessResultDO result = adminServiceClient.touch(itemIds,
					getOtherAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// do something
				// ʧ�ܵ���Ʒ�б�����ʧ�ܵ�ԭ�������
				Map<Long, Set<String>> failMap = result.getItemIdErrorCodeMap();
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡloom Ӧ�õ�appInfo ,��ʵ�ʵ�Ӧ�ó����� opertion,memo,operator ������ú�ҵ����صĲ���
	 * 
	 * @return
	 */
	private AppInfoDO getLoomAppInfo() {
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
	private AppInfoDO getOtherAppInfo() {
		AppInfoDO infoDO = new AppInfoDO();
		infoDO.setAppName(AppInfoConstants.NAME_UNKNOWN);
		infoDO.setOperation("demo");
		infoDO.setMemo("demo");
		infoDO.setOperator("demo");
		return infoDO;
	}

	public static void main(String[] args) {
		new AdminServiceClientDemo().xiaoerDelItem();
	}

}