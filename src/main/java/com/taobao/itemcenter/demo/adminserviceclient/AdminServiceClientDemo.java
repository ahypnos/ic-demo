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
 * 每一个接口的使用
 * 
 * AdminService 这个接口的示例不是很重要,因为这个接的参数都比较简单,并且没有依赖其他配置
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
		// 要删除的商品ID 列表..一次最多传20个
		List<Long> itemIds = newArrayList();
		itemIds.add(1L);

		// 当ic 控制开关开启之后 只有loom 应用才充许调用这个接口
		try {
			XiaoProcessResultDO result = adminServiceClient.xiaoerDelItem(
					itemIds, getLoomAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// 操作失败的ID列表
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
		// 一次最多传20个
		List<Long> itemIds = newArrayList();
		itemIds.add(1L);
		try {
			XiaoProcessResultDO result = adminServiceClient
					.xiaoerUpdateItemStatus(itemIds,
							ItemStatusConstants.NORMAL, getLoomAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// 操作失败的ID列表
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
				// 操作失败的ID列表
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
				// 操作失败的ID列表 (这里不是批量更新,这个错误列表基本上用处不大)
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
		// 如果不想更新SKU skuList 可以传null,但是需要设置,needClearSkuWhenSkuListNull为false
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
		// 一次最多传20个
		List<Long> itemIds = newArrayList();
		itemIds.add(1L);

		ProcessResultDO result = adminServiceClient
				.cronCheckFixPriceItemAndDownShelf(itemIds, getOtherAppInfo());
		if (result.isSuccess()) {
			// do something
		} else {
			// do something
			// 重发失败的
			List<ItemDO> fail = (List<ItemDO>) result.getContext().get(
					"failItems");
			// 重发失败的失败 itemId 为key
			Map<Long, String> reasons = (Map<Long, String>) result.getContext()
					.get("reasons");
		}
	}

	/**
	 * 这个接口即将废弃,建议不要使用 see {@link AdminService#cronUpdateItem(List, AppInfoDO)}
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
				// 操作失败的ID列表
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
	 * whereStatus <li>所有的商品：ItemConstants.XIAOER_UPDATE_STATUS_ALL</li> <li>
	 * 未出价：ItemConstants.XIAOER_UPDATE_STATUS_NOT_BID<
	 */
	public void xiaoerDownShelfItem_LIA() {
		// 一次最多传50个
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
				// 操作失败的ID列表
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
		// key 为itemId,value为 gmt_modified 更新的时候作为数据库的乐观锁
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
				// 操作失败的ID列表
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
		// 一次最多传50个
		List<Long> itemIds = newArrayList();
		itemIds.add(1L);

		try {
			XiaoProcessResultDO result = adminServiceClient.xiaoerSetItemToCc(
					itemIds, getLoomAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// 操作失败的ID列表
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
		// key 为itemId,value为 gmt_modified 更新的时候作为数据库的乐观锁, 一次最多传50个
		Map<Long, Date> itemIdAndGmt = Maps.newHashMap();
		itemIdAndGmt.put(1L, new Date());

		try {
			XiaoProcessResultDO result = adminServiceClient.xiaoerSetItemToCc(
					itemIdAndGmt, getLoomAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// 操作失败的ID列表
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

		// 一次最多传50个
		List<Long> itemIds = newArrayList();
		itemIds.add(1L);

		try {
			XiaoProcessResultDO result = adminServiceClient.xiaoerDelItem(
					seller, itemIds, ItemConstants.XIAOER_UPDATE_STATUS_ALL,
					getLoomAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// 操作失败的ID列表
				List<Long> failList = result.getErrorItemIdList();
				System.out.println(failList + "  operation fail");
				// do something
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	/**
	 * itemVideo 不能为空 itemVideo.getId() >= 1 itemVideo.getItemId() > 0
	 * ItemVideoDO.STATUS_FROZEN == itemVideo.getStatus() ||
	 * ItemVideoDO.STATUS_NORMAL == itemVideo.getStatus()
	 */
	public void xiaoerUpdateItemVideo() {
		ItemVideoDO itemVideo = new ItemVideoDO();
		itemVideo.setId(1L);
		/**
		 * ... ... set 其他值
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
		 * ... ... set 其他值
		 */
		videoList.add(itemVideo);

		try {
			// 全部成功的时候才返回成功
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
	 * status 可以为： ItemStatusConstants.DELETED_BY_XIAOER,
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
		itemIds.add(1L); // 最多40个

		ItemUpdateDO updateDO = new ItemUpdateDO(1L);
		updateDO.setSpuId(111L);
		updateDO.setReplaceCategoryIdOfItemFromSpu(true);
		updateDO.setReplaceKeyPropertiesOfItemFromSpu(true);
		try {
			// 只要有一个成功就返回成功
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
		itemIds.add(1L); // 最多20个

		try {
			XiaoProcessResultDO result = adminServiceClient.touch(itemIds,
					getOtherAppInfo());
			if (result.isSuccess()) {
				// do something
			} else {
				// do something
				// 失败的商品列表，和失败的原因错误码
				Map<Long, Set<String>> failMap = result.getItemIdErrorCodeMap();
			}
		} catch (IcException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取loom 应用的appInfo ,在实际的应用场景中 opertion,memo,operator 最好设置和业务相关的参数
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
	 * 获取unknow 应用的appInfo ,在实际的应用场景中appName不要设置为unknow opertion,memo,operator
	 * 最好设置和业务相关的参数
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
