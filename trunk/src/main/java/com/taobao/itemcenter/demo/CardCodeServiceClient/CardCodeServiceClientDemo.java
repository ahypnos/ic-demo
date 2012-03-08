package com.taobao.itemcenter.demo.CardCodeServiceClient;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.item.constant.ItemOptionsConstants;
import com.taobao.item.domain.CardCodeDO;
import com.taobao.item.domain.ItemDO;
import com.taobao.item.domain.query.CardCodeQuery;
import com.taobao.item.exception.IcException;
import com.taobao.item.service.client.CardCodeServiceClient;
import com.taobao.itemcenter.demo.utils.ItemUtils;

/**
 * ���ܽӿ���ز��� ���Ϊ���ܱ��������ڱ�����options�ֶ���ֵ1<<28
 * 
 * @see ItemOptionsConstants.ITEM_OPTIONS_AUTO_CONSIGNMENT
 * @author tieyi.qlr
 * 
 */
public class CardCodeServiceClientDemo {
	/**
	 * ͨ��HSFע��
	 */
	private CardCodeServiceClient cardCodeServiceClient;

	// ��ѯ����
	private long tradeId = 0L;
	private long itemId = 0L;
	private String buyerId = "";
	private String sellerId = "";
	private long sellerNumId = 0L;

	public static void main(String[] args) throws IcException {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "cardCodeServiceClient/spring-ic-core.xml",
						"cardCodeServiceClient/spring-ic-hsf.xml" });
		CardCodeServiceClientDemo cardCodeDemo = (CardCodeServiceClientDemo) ac
				.getBean("cardCodeDemo");
		
		cardCodeDemo.getCardCodeInfoByQuery_���ҿ�����Ϣ();
		cardCodeDemo.getCardCodeInfoByQueryWithPage_��ҳ��ѯ������Ϣ();
		cardCodeDemo.updateCardCodeToViewedStateForQuery_�޸�ͬһ�ʶ��������п���״̬();
		cardCodeDemo.resetCardCode_�ָ�����������δ����״̬_�����¶�Ӧ��Ʒ���();
		cardCodeDemo.updateAutoCCInfo_���¿�����Ϣ();
		
		System.exit(0);
	}

	/**
	 * ���ҿ�����Ϣ
	 * ����getCardCodeInfoByQuery�ӿ�
	 * @throws IcException
	 */
	public void getCardCodeInfoByQuery_���ҿ�����Ϣ() throws IcException {
		// �����Լ�����Ҫ�Ĳ�ѯ������Ӧ������
		tradeId = 4001117207L;
		itemId = 1500004274891L;
		buyerId = "eeeebacb59d0e31c32d92808ac9f459d";
		sellerId = "8e23a21b220a8fd1635750aa4f2423c2";
		// ���ڴ洢��ѯ���
		List<CardCodeDO> result;
		printLine("getCardCodeInfoByQuery�ӿڵ���");
		// ͨ����ƷID
		CardCodeQuery query = new CardCodeQuery();
		query.setItemId(itemId);
		result = cardCodeServiceClient.getCardCodeInfoByQuery(query);
		printResult(query, result);

		// ͨ������ID
		CardCodeQuery query1 = new CardCodeQuery();
		query1.setTradeId(tradeId);
		result = cardCodeServiceClient.getCardCodeInfoByQuery(query1);
		printResult(query1, result);

		// ͨ�����ID
		CardCodeQuery query2 = new CardCodeQuery();
		query2.setBuyerId(buyerId);
		result = cardCodeServiceClient.getCardCodeInfoByQuery(query2);
		printResult(query2, result);

		// ͨ������ID
		CardCodeQuery query3 = new CardCodeQuery();
		query3.setSellerId(sellerId);
		result = cardCodeServiceClient.getCardCodeInfoByQuery(query3);
		printResult(query3, result);

		// ͨ�����ID����ƷID
		CardCodeQuery query4 = new CardCodeQuery();
		query4.setBuyerId(buyerId);
		query4.setItemId(itemId);
		result = cardCodeServiceClient.getCardCodeInfoByQuery(query4);
		printResult(query4, result);

		// ͨ������ID����ƷID
		CardCodeQuery query5 = new CardCodeQuery();
		query5.setTradeId(tradeId);
		query5.setItemId(itemId);
		result = cardCodeServiceClient.getCardCodeInfoByQuery(query5);
		printResult(query5, result);
		printLine("getCardCodeInfoByQuery�ӿڵ���");
	}

	/**
	 * ��ҳ��ѯ������Ϣ
	 * ����getCardCodeInfoByQueryWithPage�ӿ�
	 * @throws IcException
	 */
	public void getCardCodeInfoByQueryWithPage_��ҳ��ѯ������Ϣ() throws IcException {
		// �����Լ�����Ҫ�Ĳ�ѯ������Ӧ������
		tradeId = 4001117207L;
		itemId = 1500004274891L;
		buyerId = "eeeebacb59d0e31c32d92808ac9f459d";
		sellerId = "8e23a21b220a8fd1635750aa4f2423c2";
		sellerNumId = 2022240977L;
		
		printLine("getCardCodeInfoByQueryWithPage�ӿڵ���");
		CardCodeQuery query = new CardCodeQuery();
		//�����Ҫ��ÿ����б�����ݣ�����������ƷID�����ҵ�����ID����Ȼ���Ϊ��
		query.setItemId(itemId);
		query.setSellerNumId(sellerNumId);
		query.setPageSize(2);
		query.setCurrentPage(1);
		CardCodeQuery result = cardCodeServiceClient.getCardCodeInfoByQueryWithPage(query);
		printResult(query,result.getItem().getCardCodeList());
		
		CardCodeQuery query1 = new CardCodeQuery();
		//ֻ����ƷID����������������ID,ֻ�ܵõ���Ʒ�Ļ�����Ϣ�������ܻ����ؿ����б�
		query1.setItemId(itemId);
		query1.setPageSize(2);
		query1.setCurrentPage(1);
		result = cardCodeServiceClient.getCardCodeInfoByQueryWithPage(query1);
		printResult(query1,result.getItem().getCardCodeList());
		
		CardCodeQuery query2 = new CardCodeQuery();
		//������ƷID��ֻ������ID���ò�����ر���������
		query2.setSellerNumId(sellerNumId);
		query2.setPageSize(2);
		query2.setCurrentPage(1);
		result = cardCodeServiceClient.getCardCodeInfoByQueryWithPage(query2);
		if(result.getItem()==null){
			System.out.println("û�л����ر�������Ϣ������");
			printResult(query2,null);
		}
		printLine("getCardCodeInfoByQueryWithPage�ӿڵ���");
	}
	
	/**
	 * �޸�ͬһ�ʶ��������п���״̬,���ѷ���״̬���µ��Ѳ鿴״̬
	 * ����updateCardCodeToViewedStateForQuery�ӿ�
	 * @throws IcException
	 */
	public void updateCardCodeToViewedStateForQuery_�޸�ͬһ�ʶ��������п���״̬() throws IcException{
		// �����Լ�����Ҫ�Ĳ�ѯ������Ӧ������
		tradeId = 4001117207L;
		itemId = 1500004274891L;
		sellerNumId = 2022240977L;
		printLine("updateCardCodeToViewedStateForQuery�ӿڵ���");
		//���ظ��³ɹ��Ŀ�������
		int counts = cardCodeServiceClient.updateCardCodeToViewedStateForQuery(tradeId, itemId, sellerNumId);
		System.out.println(String.format("���¿���״̬���ѷ��͵��Ѳ鿴�ļ�¼�У�%s����", counts));
		printLine("updateCardCodeToViewedStateForQuery�ӿڵ���");
	}
	
	/**
	 * �ָ�����������δ����״̬,�����¶�Ӧ��Ʒ���
	 * ����resetCardCode�ӿ�
	 * @throws IcException
	 */
	public void resetCardCode_�ָ�����������δ����״̬_�����¶�Ӧ��Ʒ���() throws IcException{
		int saleWinQuantity = 1; 
		// �����Լ�����Ҫ�Ĳ�ѯ������Ӧ������
		tradeId = 4001117207L;
		itemId = 1500004274891L;
		buyerId = "eeeebacb59d0e31c32d92808ac9f459d";
		sellerId = "8e23a21b220a8fd1635750aa4f2423c2";
		
		printLine("resetCardCode�ӿڵ���");
		boolean result = cardCodeServiceClient.resetCardCode(tradeId, itemId, buyerId, sellerId, saleWinQuantity);
		System.out.println("���ÿ��ܽ����"+result);
		printLine("resetCardCode�ӿڵ���");
	}
	
	/**
	 * ���¿�����Ϣ������itemʱ��������autoArea��sellerId��itemId�����ֶ�
	 * ����updateAutoCCInfo�ӿ�
	 * @throws IcException
	 */
	public void updateAutoCCInfo_���¿�����Ϣ() throws IcException{
		itemId = 1500004274891L;
		String autoArea = "32132143123 32132143123";
		ItemDO item = ItemUtils.getSimpleItem(itemId);
		printLine("updateAutoCCInfo�ӿڵ���");
		if(item == null){
			System.out.println("û�в�ѯ���ñ���������ÿ��ܱ����Ƿ���ڣ�");
			return;
		}
		item.setAutoArea(autoArea);
		Map<String,List> result = cardCodeServiceClient.updateAutoCCInfo(item);
		System.out.println(result);
		printLine("updateAutoCCInfo�ӿڵ���");
	}

	/**
	 * ������صĽ��
	 * @param query ��ѯ���
	 * @param result ��ѯ���
	 */
	private void printResult(CardCodeQuery query, List<CardCodeDO> result) {
		if(result == null){
			System.out.println(String.format("��ѯ����Ϊ:%s;\n��ѯ�Ľ����Ϊ��\n",query));
			return;
		}
		System.out.println(String.format("��ѯ����Ϊ:%s;\n��ѯ�Ľ������Ϊ��%s;\n����Ϊ:%s\n",
				query, result.size(), result));
	}
	
	/**
	 * ��ӡһ�У�������
	 * @param title ����
	 */
	private void printLine(String title) {
		System.out.println(String.format("-------------------------------------------%s-------------------------------------------\n",title));
	}
	
	public CardCodeServiceClient getCardCodeServiceClient() {
		return cardCodeServiceClient;
	}

	public void setCardCodeServiceClient(
			CardCodeServiceClient cardCodeServiceClient) {
		this.cardCodeServiceClient = cardCodeServiceClient;
	}
}
