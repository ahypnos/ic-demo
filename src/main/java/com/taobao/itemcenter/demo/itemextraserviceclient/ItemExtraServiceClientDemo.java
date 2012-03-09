package com.taobao.itemcenter.demo.itemextraserviceclient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.item.constant.ItemExtraConstants;
import com.taobao.item.domain.ItemDO;
import com.taobao.item.domain.ItemSkuDO;
import com.taobao.item.domain.extra.ItemExtraDO;
import com.taobao.item.domain.extra.QueryItemExtraOption;
import com.taobao.item.domain.extra.SkuExtraDO;
import com.taobao.item.domain.result.ResultDO;
import com.taobao.item.exception.IcException;
import com.taobao.item.service.client.ItemExtraServiceClient;
import com.taobao.itemcenter.demo.utils.ItemUtils;

/**
 * ��Ʒ��չ��Ϣ��ز����ӿ�ʵ��
 * 
 * @author tieyi.qlr
 * 
 */
public class ItemExtraServiceClientDemo {

	private ItemExtraServiceClient itemExtraServiceClient;

	private long itemId = 0L;
	private long userId = 0;
	private int type = ItemExtraConstants.TYPE_EXTRASHOP;

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "itemExtraServiceClient/spring-ic-core.xml",
						"itemExtraServiceClient/spring-ic-hsf.xml" });
		ItemExtraServiceClientDemo itemExtraDemo = (ItemExtraServiceClientDemo) ac
				.getBean("itemExtraDemo");
		itemExtraDemo.queryItemExtra_��ѯ��Ʒ��չ��Ϣ();
		itemExtraDemo.deleteItemExtraInAllShop_����ɾ����ͬ���ı���();
		itemExtraDemo.createItemExtra_����һ���µ���չ��Ϣ��¼();
		System.exit(0);
	}

	/**
	 * ��ѯ��Ʒ��չ��Ϣ,ֻ��Ҫ������ƷID�Ͳ�ѯѡ��� userId�����ϣ��ýӿڼ�������
	 */
	@SuppressWarnings("deprecation")
	public void queryItemExtra_��ѯ��Ʒ��չ��Ϣ() {
		//�˴����ð���sku����Ϣ
		QueryItemExtraOption option = new QueryItemExtraOption(QueryItemExtraOption.SKUS);
		userId = 0;
		itemId = 1500004351841L;
		type = ItemExtraConstants.TYPE_EXTRASHOP;
		
		printLine("queryItemExtra�ӿڵ���");
		try {
			ResultDO<ItemExtraDO> result = itemExtraServiceClient
					.queryItemExtra(itemId, type, userId, option);
			printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("queryItemExtra�ӿڵ���");
	}
	
	/**
	 * ����ͬʱɾ��������ı�����֧�ֲ�ͬ���(shop_id)�ı���
	 * ���봫��ɾ��������չ��Ϣ����������ID����������
	 */
	public void deleteItemExtraInAllShop_����ɾ����ͬ���ı���(){
		
		userId = 175754641L;
		type = ItemExtraConstants.TYPE_OPENEXTRASHOP;
		List<Long> itemIdList = new ArrayList<Long>();
		itemIdList.add(1500004351847L);
		itemIdList.add(1500004352529L);
		printLine("deleteItemExtraInAllShop�ӿڵ���");
		try {
			ResultDO<?> result = itemExtraServiceClient.deleteItemExtraInAllShop(itemIdList, userId , type);
			printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("deleteItemExtraInAllShop�ӿڵ���");
	}

	/**
	 * ����һ���µ���Ʒ��չ��Ϣ��¼
	 */
	public void createItemExtra_����һ���µ���չ��Ϣ��¼(){
		//����ID��ֵӦ������Ϊ��ǰ��Ʒ���д��ڵ�ֵ�����ñ����Ѿ����� 
		itemId=1602317157L;
		userId=175757431;
		printLine("createItemExtra�ӿڵ���");
		//������չ��Ϣ���󣬽����ο�
		ItemExtraDO itemExtra = buildItemExtra(itemId,userId);
		try {
			ResultDO<ItemExtraDO> result = itemExtraServiceClient.createItemExtra(itemExtra);
			if(result.isSuccess()){
				System.out.println("������չ��Ϣ�ɹ���");
			}else{
				System.out.println("������չ��Ϣʧ�ܣ�");
			}
			printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("createItemExtra�ӿڵ���");
	}
	
	private ItemExtraDO buildItemExtra(long itemId2, long userId2) {
		// TODO Auto-generated method stub
		ItemDO item = ItemUtils.getSimpleItem(itemId2, true);
		if(item == null ){
			System.out.println("�鲻���ñ�����");
			return null;
		}
		ItemExtraDO extraItem = new ItemExtraDO();
		extraItem.setAuctionId(itemId2);
		extraItem.setReservePrice(2000);
		List<SkuExtraDO> skus = new ArrayList<SkuExtraDO>();
		if(item.getSkuList()!=null){
			for(ItemSkuDO sku : item.getSkuList()){
				SkuExtraDO skuExtra = new SkuExtraDO();
				skuExtra.setType(ItemExtraConstants.TYPE_OPENEXTRASHOP);
				//����������ö�Ӧ����Ϣ����һ���Ǻ���Ʒ�����sku��Ϣͬ���ģ�������Ҫ��֤������һ�µ�
				//skuExtra.setProperties(sku.getProperties());
				//skuExtra.setQuantity(sku.getQuantity());
				skuExtra.setPrice(sku.getPrice());
				skus.add(skuExtra);
			}
			extraItem.setSkus(skus);
		}
		extraItem.setType(ItemExtraConstants.TYPE_OPENEXTRASHOP);
		extraItem.setUserId(userId2);
		return extraItem;
	}

	/**
	 * ��ӡ������
	 * @param result
	 */
	private void printResult(ResultDO<?> result) {
		// TODO Auto-generated method stub
		if(result.isSuccess()){
			System.out.println(String.format("�������:%s ;���Ϊ:%s", result.isSuccess(),result.getModule()));
		}else{
			System.out.println(String.format("�������:%s ;������ϢΪ:%s",result.isSuccess(),result.getErrorStr()));
		}
	}

	/**
	 * ��ӡһ�У�������
	 * 
	 * @param title ����
	 *            
	 */
	private void printLine(String title) {
		System.out
				.println(String
						.format("-------------------------------------------%s-------------------------------------------\n",
								title));
	}
	
	public ItemExtraServiceClient getItemExtraServiceClient() {
		return itemExtraServiceClient;
	}

	public void setItemExtraServiceClient(
			ItemExtraServiceClient itemExtraServiceClient) {
		this.itemExtraServiceClient = itemExtraServiceClient;
	}

}
