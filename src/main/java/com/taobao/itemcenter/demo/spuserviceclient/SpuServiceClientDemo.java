package com.taobao.itemcenter.demo.spuserviceclient;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.item.domain.result.ResultDO;
import com.taobao.item.domain.spu.QuerySpuOptionDO;
import com.taobao.item.domain.spu.SpuDO;
import com.taobao.item.exception.IcException;
import com.taobao.item.service.client.SpuServiceClient;

/**
 * spu��ز����ӿ�ʹ��
 * 
 * @author tieyi.qlr
 * 
 */
public class SpuServiceClientDemo {

	private SpuServiceClient spuServiceClient;

	private long spuId = 0L;
	private String keyProperties = "";
	private long categoryId = 0L;

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "spuServiceClient/spring-ic-core.xml",
						"spuServiceClient/spring-ic-hsf.xml" });
		SpuServiceClientDemo spuServiceDemo = (SpuServiceClientDemo) ac
				.getBean("spuServiceDemo");
		spuServiceDemo.getSpu_ͨ��spuId��ȡ��Ӧ��spu����();
		spuServiceDemo.getSpu_ͨ����ĿID�͹ؼ����Ի�ȡ��Ӧ��spu();
		spuServiceDemo.getSpu_ͨ��ָ��spuId�Ͳ�����ѯѡ���ѯ��Ӧ��spu();
		spuServiceDemo.getSimpleSpus_��ȡĬ����Ϣ�Ķ��spu����();
		System.exit(0);
	}

	/**
	 * ͨ��ָ��spuId��ȡ��Ӧ��spu����
	 */
	public void getSpu_ͨ��spuId��ȡ��Ӧ��spu����() {
		printLine("getSpu(long spuId)�ӿڵ���");
		spuId = 263059L;
		try {
			ResultDO<SpuDO> result = spuServiceClient.getSpu(spuId);
			printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("getSpu(long spuId)�ӿڵ���");
	}

	/**
	 * ͨ��ָ����ĿID�͹ؼ������ַ�����ȡ��Ӧ��spu����
	 */
	public void getSpu_ͨ����ĿID�͹ؼ����Ի�ȡ��Ӧ��spu() {
		printLine("getSpu(long categoryId,String keyProperties)�ӿڵ���");
		categoryId = 50010790L;
		keyProperties = "20000:44926;1625901:135156";
		try {
			ResultDO<SpuDO> result = spuServiceClient.getSpu(categoryId,
					keyProperties);
			printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("getSpu(long categoryId,String keyProperties)�ӿڵ���");
	}
	
	/**
	 * ͨ��ָ��spuId�Ͳ�����ѯѡ���ѯ��Ӧ��spu
	 * ������ѯѡ������Զ�������
	 */
	public void getSpu_ͨ��ָ��spuId�Ͳ�����ѯѡ���ѯ��Ӧ��spu(){
		printLine("getSpu(long spuId,QuerySpuOptionDO options)�ӿڵ���");
		spuId = 263059L;
		
		//����ʱ����Ϊtrueֵ
		//true��ʾ����һ��spu��¼��������Ϣ, ��������, ����, ͼƬ��, ����������ȡ��ɾ�������ε�spu��¼
		//falseĬ��Ϊ��
		QuerySpuOptionDO option = new QuerySpuOptionDO(true);
		
		//Ҳ�����Զ����Լ���Ҫ��ѯ��ѡ��
		/*QuerySpuOptionDO option = new QuerySpuOptionDO();
        option.setIncludeSpuImage(true);
        option.setIncludePropertyImage(true);*/
		try {
			ResultDO<SpuDO> result = spuServiceClient.getSpu(spuId, option);
			this.printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("getSpu(long spuId,QuerySpuOptionDO options)�ӿڵ���");
	}
	
	/**
	 * ��ѯ���spu�򵥶��󣬼�����ѯspu��һЩ������Ϣ(�൱��QuerySpuOptionsDOΪĬ�ϵ�ֵ)
	 * ����һ��spuId�б�
	 */
	public void getSimpleSpus_��ȡĬ����Ϣ�Ķ��spu����(){
		printLine("getSpu(long[] spuIds)�ӿڵ���");
		long[] spuIds = {263103,263102,261545,261535};
		try {
			ResultDO<List<SpuDO>> result = spuServiceClient.getSimpleSpus(spuIds);
			printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("getSpu(long[] spuIds)�ӿڵ���");
	}
	
	public void updateSpuForMall_�̳����һ�С������spu��Ϣ(){
		
	}

	/**
	 * ��ӡ������
	 * 
	 * @param result
	 */
	private void printResult(ResultDO<?> result) {
		// TODO Auto-generated method stub
		if (result.isSuccess()) {
			System.out.println(String.format("�������:%s ;���Ϊ:%s",
					result.isSuccess(), result.getModule()));
		} else {
			System.out.println(String.format("�������:%s ;������ϢΪ:%s",
					result.isSuccess(), result.getErrorStr()));
		}
	}

	/**
	 * ��ӡһ�У�������
	 * 
	 * @param title
	 *            ����
	 * 
	 */
	private void printLine(String title) {
		System.out
				.println(String
						.format("-------------------------------------------%s-------------------------------------------\n",
								title));
	}

	public SpuServiceClient getSpuServiceClient() {
		return spuServiceClient;
	}

	public void setSpuServiceClient(SpuServiceClient spuServiceClient) {
		this.spuServiceClient = spuServiceClient;
	}

}
