package com.taobao.itemcenter.demo.spuserviceclient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.item.constant.SpuConstants;
import com.taobao.item.domain.result.BaseResultDO;
import com.taobao.item.domain.result.ResultDO;
import com.taobao.item.domain.spu.QuerySpuOptionDO;
import com.taobao.item.domain.spu.SpuDO;
import com.taobao.item.domain.spu.SpuImageDO;
import com.taobao.item.exception.IcException;
import com.taobao.item.service.client.SpuServiceClient;
import com.taobao.itemcenter.demo.utils.SpuUtils;

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
		spuServiceDemo.getSpu_����ָ����ĿId��spu�Ĺؼ����ԺͲ�ѯѡ���ȡSpu����();
		spuServiceDemo.updateSpu_����spu();
		spuServiceDemo.updateSpuForMall_�̳����һ�С������spu��Ϣ();
		spuServiceDemo.insertSpu_����Spu();
		spuServiceDemo.insertSpuForMall_����Spu();
		spuServiceDemo.uploadImage_����spuͼƬ();
		spuServiceDemo.getSpusByOwnerId_ͨ��spu��ownerId��outerId��ȡspu��Ϣ();
		spuServiceDemo.completeAuditSpu_���spu();
		spuServiceDemo.xiaoerInsertSpu_С������spu();
		spuServiceDemo.xiaoerUpdateSpu_С������spu();
		spuServiceDemo.getRelatedSpus_��ȡ�������spu();
		spuServiceDemo.insertAuditSpu_����һ������˵�spu��¼();
		spuServiceDemo.xiaoerUpdateSpuStatus_С������spu��״̬();
		spuServiceDemo.deleteImage_ɾ��spuͼƬ();
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
	 * ͨ��ָ��spuId�Ͳ�����ѯѡ���ѯ��Ӧ��spu ������ѯѡ������Զ�������
	 */
	public void getSpu_ͨ��ָ��spuId�Ͳ�����ѯѡ���ѯ��Ӧ��spu() {
		printLine("getSpu(long spuId,QuerySpuOptionDO options)�ӿڵ���");
		spuId = 263059L;

		// ����ʱ����Ϊtrueֵ
		// true��ʾ����һ��spu��¼��������Ϣ, ��������, ����, ͼƬ��, ����������ȡ��ɾ�������ε�spu��¼
		// falseĬ��Ϊ��
		QuerySpuOptionDO option = new QuerySpuOptionDO(true);

		// Ҳ�����Զ����Լ���Ҫ��ѯ��ѡ��
		/*
		 * QuerySpuOptionDO option = new QuerySpuOptionDO();
		 * option.setIncludeSpuImage(true);
		 * option.setIncludePropertyImage(true);
		 */
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
	 * ��ѯ���spu�򵥶��󣬼�����ѯspu��һЩ������Ϣ(�൱��QuerySpuOptionsDOΪĬ�ϵ�ֵ) ����һ��spuId�б�
	 */
	public void getSimpleSpus_��ȡĬ����Ϣ�Ķ��spu����() {
		printLine("getSpu(long[] spuIds)�ӿڵ���");
		long[] spuIds = { 263103, 263102, 261545, 261535 };
		try {
			ResultDO<List<SpuDO>> result = spuServiceClient
					.getSimpleSpus(spuIds);
			printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("getSpu(long[] spuIds)�ӿڵ���");
	}

	/**
	 * ����ָ����ĿId��spu�Ĺؼ���������ȡ��Ӧ��Spu����ͨ����Ӧ��options��ֵ��ѯspu��һЩ������Ϣ
	 */
	public void getSpu_����ָ����ĿId��spu�Ĺؼ����ԺͲ�ѯѡ���ȡSpu����() {
		printLine("getSpu(long categoryId,String keyProperties,QuerySpuOptionDO options)�ӿڵ���");
		categoryId = 50010790L;
		keyProperties = "20000:44926;1625901:135156";
		// ����ʱ����Ϊtrueֵ
		// true��ʾ����һ��spu��¼��������Ϣ, ��������, ����, ͼƬ��, ����������ȡ��ɾ�������ε�spu��¼
		// falseĬ��Ϊ��
		QuerySpuOptionDO options = new QuerySpuOptionDO(true);

		// Ҳ�����Զ����Լ���Ҫ��ѯ��ѡ��
		/*
		 * QuerySpuOptionDO options = new QuerySpuOptionDO();
		 * options.setIncludeSpuImage(true);
		 * options.setIncludePropertyImage(true);
		 */
		try {
			ResultDO<SpuDO> result = spuServiceClient.getSpu(categoryId,
					keyProperties, options);
			printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("getSpu(long categoryId,String keyProperties,QuerySpuOptionDO options)�ӿڵ���");
	}

	public void updateSpu_����spu() {
		this.printLine("updateSpu�ӿڵ���");
		System.out.println("���Բ��սӿڵ���ʵ��-updateSpuForMall_�̳����һ�С������spu��Ϣ()");
		this.printLine("updateSpu�ӿڵ���");
	}

	/**
	 * �̳����Ҹ���spu
	 */
	public void updateSpuForMall_�̳����һ�С������spu��Ϣ() {
		this.printLine("updateSpuForMall�ӿڵ���");
		spuId = 88799944;
		SpuDO spu = SpuUtils.getSpu(spuId);
		if (spu == null) {
			System.out.println("��õ�Spu����Ϊ�գ�spuId=" + spuId);
			return;
		}
		// �����Ϊ�գ����޸�spu�ı���
		spu.setName("B�̼��޸�Spu������");
		try {
			ResultDO<SpuDO> result = this.spuServiceClient
					.updateSpuForMall(spu);
			System.out.println(result);
			this.printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.printLine("updateSpuForMall�ӿڵ���");
	}

	/**
	 * ����spu
	 */
	public void insertSpu_����Spu() {
		this.printLine("insertSpu(SpuDO spu)�ӿڵ���");
		SpuDO spu = new SpuDO();
		spu.setName("����Ʒ��Ŀ����");
		spu.setCategoryId(50012472);
		spu.setXiaoer(false);
		spu.setPrice(30000L);
		spu.setOwnerId(110010107L);
		List<SpuImageDO> spuImages = new ArrayList<SpuImageDO>();
		SpuImageDO spuImage = new SpuImageDO();
		spuImages.add(spuImage);
		spu.setSpuImages(spuImages);
		ResultDO<SpuDO> result;
		try {
			result = this.spuServiceClient.insertSpu(spu);
			System.out.println(result);
			this.printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.printLine("insertSpu(SpuDO spu)�ӿڵ���");
	}

	public void insertSpuForMall_����Spu() {
		this.printLine("insertSpuForMall(SpuDO spu)�ӿڵ���");
		// ���Բ���insertSpu_����Spu()����
		//this.spuServiceClient.insertSpuForMall(spu)
		System.out.println("���Բ���insertSpu_����Spu()����");
		this.printLine("insertSpuForMall(SpuDO spu)�ӿڵ���");
	}

	/**
	 * ����spuͼƬ
	 */
	public void uploadImage_����spuͼƬ() {
		this.printLine("uploadImage�ӿڵ���");
		spuId = 88799944;
		SpuImageDO image = new SpuImageDO();
		image.setSpuId(spuId);
		image.setUserId(110010107);
		// ����spuͼƬ�ĵ�ַ
		image.setUrl("i6/T1apXXXlOqt0KZRIM8_070040.jpg");
		image.setMajor(true);

		try {
			ResultDO<SpuImageDO> result = this.spuServiceClient
					.uploadImage(image);
			this.printResult(result);
		} catch (IcException e) {
			e.printStackTrace();
		}
		this.printLine("uploadImage�ӿڵ���");
	}

	public void getSpusByOwnerId_ͨ��spu��ownerId��outerId��ȡspu��Ϣ() {
		this.printLine("getSpusByOwnerId");
		long ownerId = 196993935;
		String outerId = "056779000";
		try {
			ResultDO<List<SpuDO>> result = this.spuServiceClient
					.getSpuByOwnerIdAndOuterId(ownerId, outerId);
			this.printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.printLine("getSpusByOwnerId");
	}

	public void completeAuditSpu_���spu() {
		this.printLine("completeAuditSpu�ӿڵ���");
		spuId = 157793;
		SpuDO spu = SpuUtils.getSpu(spuId);
		try {
			ResultDO<SpuDO> result = this.spuServiceClient
					.completeAuditSpu(spu);
			this.printResult(result);
		} catch (IcException e) {
			e.printStackTrace();
		}
		this.printLine("completeAuditSpu�ӿڵ���");
	}

	public void xiaoerUpdateSpu_С������spu() {
		this.printLine("xiaoerUpdateSpu�ӿڵ���");
		//this.spuServiceClient.xiaoerUpdateSpu(spu);
		System.out.println("���Բ���updateSpuForMall_�̳����һ�С������spu��Ϣ()����");
		this.printLine("xiaoerUpdateSpu�ӿڵ���");
	}

	public void xiaoerInsertSpu_С������spu() {
		this.printLine("xiaoerInsertSpu�ӿڵ���");
		//this.spuServiceClient.xiaoerInsertSpu(spu);
		System.out.println("���Բ���insertSpu_����Spu()����");
		this.printLine("xiaoerInsertSpu�ӿڵ���");
	}
	
	public void getRelatedSpus_��ȡ�������spu(){
		this.printLine("getRelatedSpus�ӿڵ���");
		//spu_relation��,spu�������ѯ����Ϣ
		spuId = 87154539;
		String relation = "INCLUDE";
		try {
			ResultDO<List<SpuDO>> result = this.spuServiceClient.getRelatedSpus(spuId, relation);
			this.printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.printLine("getRelatedSpus�ӿڵ���");
	}
	
	/**
	 * ��spu��˱����һ����¼
	 */
	public void insertAuditSpu_����һ������˵�spu��¼(){
		this.printLine("insertAuditSpu�ӿڵ���");
		System.out.println("���Բ���insertSpu_����Spu()����");
		//this.spuServiceClient.insertAuditSpu(spu);
		this.printLine("insertAuditSpu�ӿڵ���");
	}
	
	public void xiaoerUpdateSpuStatus_С������spu��״̬(){
		this.printLine("xiaoerUpdateSpuStatus�ӿڵ���");
		spuId = 88799944;
		int status = SpuConstants.STATUS_ENABLE;
		try {
			BaseResultDO result = this.spuServiceClient.xiaoerUpdateSpuStatus(spuId, status);
			System.out.println(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.printLine("xiaoerUpdateSpuStatus�ӿڵ���");
	}
	
	public void deleteImage_ɾ��spuͼƬ(){
		this.printLine("deleteImage�ӿڵ���");
		spuId = 277401;
		long imageId = 351;
		SpuImageDO image = new SpuImageDO();
		image.setId(imageId);
		image.setSpuId(spuId);
		image.setUserId(137825125);
		try {
			ResultDO<SpuImageDO> result = this.spuServiceClient.deleteImage(image);
			this.printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.printLine("deleteImage�ӿڵ���");
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
