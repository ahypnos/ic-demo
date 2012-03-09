package com.taobao.itemcenter.demo.itemreadserviceclient;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.item.constant.AppInfoConstants;
import com.taobao.item.domain.AppInfoDO;
import com.taobao.item.domain.ItemSkuDO;
import com.taobao.item.domain.result.BatchItemResultDO;
import com.taobao.item.domain.result.ResultDO;
import com.taobao.item.exception.IcException;
import com.taobao.item.service.client.ItemReadServiceClient;

/**
 * һЩ������Ʒ���ݵĲ�ѯ����ǰ�ǶԶ���ķ��ʣ����������Ѿ��ϳ���Ʒ���⣬ ����ӿڵĵ����߼�����ʵʱ�������ṩһЩ��ͬγ�ȵĲ�ѯ��
 * ����ӿڿ��Բ���ItemInstantSearchServiceClient��һЩ�ӿڵ���
 * 
 * @author tieyi.qlr
 * 
 */
public class ItemReadServiceClientDemo {

	private ItemReadServiceClient itemReadServiceClient;

	private long userId = 0L;
	private String outerId = "";

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "itemReadServiceClient/spring-ic-core.xml",
						"itemReadServiceClient/spring-ic-hsf.xml" });
		ItemReadServiceClientDemo itemReadServiceClientDemo = (ItemReadServiceClientDemo) ac
				.getBean("itemReadDemo");
		itemReadServiceClientDemo.querySkuListByOuterId_ͨ���ⲿ�����ѯsku��Ϣ();
		itemReadServiceClientDemo.queryDeletedItemsForPage_��ҳ��ѯ�û���ɾ���ı���();
		System.exit(0);
	}

	/**
	 * @see ItemReadServiceClient.querySkuListByOuterId
	 */
	public void querySkuListByOuterId_ͨ���ⲿ�����ѯsku��Ϣ() {
		AppInfoDO appInfo = new AppInfoDO(AppInfoConstants.NAME_TOP, "query",
				"top");
		// itemId=1500005280670 ���Բ�ѯ�������
		userId = 175754155;
		outerId = "TIEYI";
		printLine("querySkuListByOuterId�ӿڵ���");
		try {
			ResultDO<List<ItemSkuDO>> result = itemReadServiceClient
					.querySkuListByOuterId(userId, outerId, appInfo);
			System.out.println("��ѯ�Ƿ�ɹ�-" + result.isSuccess());
			System.out.println(result.getModule());
		} catch (IcException e) {
			e.printStackTrace();
		}
		printLine("querySkuListByOuterId�ӿڵ���");
	}

	/**
	 * ͨ����ҳ��ѯ�û���ɾ���ı������������÷�ҳ�Ĵ�С����Ҫָ���û�
	 * @see ItemReadServiceClient.queryDeletedItemsForPage
	 */
	public void queryDeletedItemsForPage_��ҳ��ѯ�û���ɾ���ı���() {
		userId = 175754155;
		// ҳ��
		int pageIndex = 1;
		// ÿҳ��С
		int pageSize = 5;
		printLine("queryDeletedItemsForPage�ӿڵ���");
		try {
			BatchItemResultDO result = itemReadServiceClient
					.queryDeletedItemsForPage(userId, pageIndex, pageSize);
			if (result.isSuccess()) {
				System.out.println(String.format(
						"����(%s)ɾ���ܵı�������Ϊ%s,�ڵ�%sҳ�鵽%s��������", userId, result
								.getTotalCount(), pageIndex, result
								.getItemList().size()));
				System.out.println(result.getItemList());
			} else {
				System.out.println("��ѯʧ�ܣ�");
			}
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			printLine("queryDeletedItemsForPage�ӿڵ���");
		}
	}

	/**
	 * ��ӡһ�У�������
	 * 
	 * @param title
	 *            ����
	 */
	private void printLine(String title) {
		System.out
				.println(String
						.format("-------------------------------------------%s-------------------------------------------\n",
								title));
	}

	public ItemReadServiceClient getItemReadServiceClient() {
		return itemReadServiceClient;
	}

	public void setItemReadServiceClient(
			ItemReadServiceClient itemReadServiceClient) {
		this.itemReadServiceClient = itemReadServiceClient;
	}

}
