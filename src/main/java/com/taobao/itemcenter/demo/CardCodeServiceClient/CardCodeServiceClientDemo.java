package com.taobao.itemcenter.demo.CardCodeServiceClient;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.item.service.client.CardCodeServiceClient;
/**
 * ���ܽӿ���ز���
 * @author tieyi.qlr
 *
 */
public class CardCodeServiceClientDemo {
	/**
	 * ͨ��HSFע��
	 */
	private CardCodeServiceClient cardCodeServiceClient;

	public CardCodeServiceClient getCardCodeServiceClient() {
		return cardCodeServiceClient;
	}

	public void setCardCodeServiceClient(CardCodeServiceClient cardCodeServiceClient) {
		this.cardCodeServiceClient = cardCodeServiceClient;
	}
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"cardCodeServiceClient/spirng-ic-core.xml","cardCodeServiceClient/spirng-ic-hsf.xml"});
		CardCodeServiceClientDemo c = (CardCodeServiceClientDemo)ac.getBean("cardCodeDemo");
		System.out.println("init");
	}
	
}
