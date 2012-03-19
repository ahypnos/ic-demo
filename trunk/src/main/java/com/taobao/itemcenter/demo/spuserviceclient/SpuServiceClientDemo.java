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
 * spu相关操作接口使用
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
		spuServiceDemo.getSpu_通过spuId获取对应的spu对象();
		spuServiceDemo.getSpu_通过类目ID和关键属性获取对应的spu();
		spuServiceDemo.getSpu_通过指定spuId和参数查询选项查询对应的spu();
		spuServiceDemo.getSimpleSpus_获取默认信息的多个spu对象();
		spuServiceDemo.getSpu_查找指定类目Id和spu的关键属性和查询选项获取Spu对象();
		spuServiceDemo.updateSpu_更新spu();
		spuServiceDemo.updateSpuForMall_商城卖家或小二更新spu信息();
		spuServiceDemo.insertSpu_插入Spu();
		spuServiceDemo.insertSpuForMall_插入Spu();
		spuServiceDemo.uploadImage_更新spu图片();
		spuServiceDemo.getSpusByOwnerId_通过spu的ownerId和outerId获取spu信息();
		spuServiceDemo.completeAuditSpu_审核spu();
		spuServiceDemo.xiaoerInsertSpu_小二插入spu();
		spuServiceDemo.xiaoerUpdateSpu_小二更新spu();
		spuServiceDemo.getRelatedSpus_获取相关联的spu();
		spuServiceDemo.insertAuditSpu_插入一条侍审核的spu记录();
		spuServiceDemo.xiaoerUpdateSpuStatus_小二更新spu的状态();
		spuServiceDemo.deleteImage_删除spu图片();
		System.exit(0);
	}

	/**
	 * 通过指定spuId获取对应的spu对象
	 */
	public void getSpu_通过spuId获取对应的spu对象() {
		printLine("getSpu(long spuId)接口调用");
		spuId = 263059L;
		try {
			ResultDO<SpuDO> result = spuServiceClient.getSpu(spuId);
			printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("getSpu(long spuId)接口调用");
	}

	/**
	 * 通过指定类目ID和关键属性字符串获取对应的spu对象
	 */
	public void getSpu_通过类目ID和关键属性获取对应的spu() {
		printLine("getSpu(long categoryId,String keyProperties)接口调用");
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
		printLine("getSpu(long categoryId,String keyProperties)接口调用");
	}

	/**
	 * 通过指定spuId和参数查询选项查询对应的spu 参数查询选项可以自定义设置
	 */
	public void getSpu_通过指定spuId和参数查询选项查询对应的spu() {
		printLine("getSpu(long spuId,QuerySpuOptionDO options)接口调用");
		spuId = 263059L;

		// 创建时设置为true值
		// true表示包含一条spu记录的所有信息, 比如描述, 属性, 图片等, 但不包括获取已删除和屏蔽的spu记录
		// false默认为空
		QuerySpuOptionDO option = new QuerySpuOptionDO(true);

		// 也可以自定义自己需要查询的选项
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
		printLine("getSpu(long spuId,QuerySpuOptionDO options)接口调用");
	}

	/**
	 * 查询多个spu简单对象，即不查询spu的一些附属信息(相当于QuerySpuOptionsDO为默认的值) 传入一个spuId列表
	 */
	public void getSimpleSpus_获取默认信息的多个spu对象() {
		printLine("getSpu(long[] spuIds)接口调用");
		long[] spuIds = { 263103, 263102, 261545, 261535 };
		try {
			ResultDO<List<SpuDO>> result = spuServiceClient
					.getSimpleSpus(spuIds);
			printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("getSpu(long[] spuIds)接口调用");
	}

	/**
	 * 查找指定类目Id和spu的关键属性来获取对应的Spu对象，通过对应的options的值查询spu的一些附属信息
	 */
	public void getSpu_查找指定类目Id和spu的关键属性和查询选项获取Spu对象() {
		printLine("getSpu(long categoryId,String keyProperties,QuerySpuOptionDO options)接口调用");
		categoryId = 50010790L;
		keyProperties = "20000:44926;1625901:135156";
		// 创建时设置为true值
		// true表示包含一条spu记录的所有信息, 比如描述, 属性, 图片等, 但不包括获取已删除和屏蔽的spu记录
		// false默认为空
		QuerySpuOptionDO options = new QuerySpuOptionDO(true);

		// 也可以自定义自己需要查询的选项
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
		printLine("getSpu(long categoryId,String keyProperties,QuerySpuOptionDO options)接口调用");
	}

	public void updateSpu_更新spu() {
		this.printLine("updateSpu接口调用");
		System.out.println("可以参照接口调用实例-updateSpuForMall_商城卖家或小二更新spu信息()");
		this.printLine("updateSpu接口调用");
	}

	/**
	 * 商城卖家更新spu
	 */
	public void updateSpuForMall_商城卖家或小二更新spu信息() {
		this.printLine("updateSpuForMall接口调用");
		spuId = 88799944;
		SpuDO spu = SpuUtils.getSpu(spuId);
		if (spu == null) {
			System.out.println("获得的Spu对象为空，spuId=" + spuId);
			return;
		}
		// 如果不为空，则修改spu的标题
		spu.setName("B商家修改Spu的名字");
		try {
			ResultDO<SpuDO> result = this.spuServiceClient
					.updateSpuForMall(spu);
			System.out.println(result);
			this.printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.printLine("updateSpuForMall接口调用");
	}

	/**
	 * 插入spu
	 */
	public void insertSpu_插入Spu() {
		this.printLine("insertSpu(SpuDO spu)接口调用");
		SpuDO spu = new SpuDO();
		spu.setName("保健品类目测试");
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
		this.printLine("insertSpu(SpuDO spu)接口调用");
	}

	public void insertSpuForMall_插入Spu() {
		this.printLine("insertSpuForMall(SpuDO spu)接口调用");
		// 可以参照insertSpu_插入Spu()例子
		//this.spuServiceClient.insertSpuForMall(spu)
		System.out.println("可以参照insertSpu_插入Spu()例子");
		this.printLine("insertSpuForMall(SpuDO spu)接口调用");
	}

	/**
	 * 更新spu图片
	 */
	public void uploadImage_更新spu图片() {
		this.printLine("uploadImage接口调用");
		spuId = 88799944;
		SpuImageDO image = new SpuImageDO();
		image.setSpuId(spuId);
		image.setUserId(110010107);
		// 设置spu图片的地址
		image.setUrl("i6/T1apXXXlOqt0KZRIM8_070040.jpg");
		image.setMajor(true);

		try {
			ResultDO<SpuImageDO> result = this.spuServiceClient
					.uploadImage(image);
			this.printResult(result);
		} catch (IcException e) {
			e.printStackTrace();
		}
		this.printLine("uploadImage接口调用");
	}

	public void getSpusByOwnerId_通过spu的ownerId和outerId获取spu信息() {
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

	public void completeAuditSpu_审核spu() {
		this.printLine("completeAuditSpu接口调用");
		spuId = 157793;
		SpuDO spu = SpuUtils.getSpu(spuId);
		try {
			ResultDO<SpuDO> result = this.spuServiceClient
					.completeAuditSpu(spu);
			this.printResult(result);
		} catch (IcException e) {
			e.printStackTrace();
		}
		this.printLine("completeAuditSpu接口调用");
	}

	public void xiaoerUpdateSpu_小二更新spu() {
		this.printLine("xiaoerUpdateSpu接口调用");
		//this.spuServiceClient.xiaoerUpdateSpu(spu);
		System.out.println("可以参照updateSpuForMall_商城卖家或小二更新spu信息()例子");
		this.printLine("xiaoerUpdateSpu接口调用");
	}

	public void xiaoerInsertSpu_小二插入spu() {
		this.printLine("xiaoerInsertSpu接口调用");
		//this.spuServiceClient.xiaoerInsertSpu(spu);
		System.out.println("可以参照insertSpu_插入Spu()例子");
		this.printLine("xiaoerInsertSpu接口调用");
	}
	
	public void getRelatedSpus_获取相关联的spu(){
		this.printLine("getRelatedSpus接口调用");
		//spu_relation表,spu表关联查询的信息
		spuId = 87154539;
		String relation = "INCLUDE";
		try {
			ResultDO<List<SpuDO>> result = this.spuServiceClient.getRelatedSpus(spuId, relation);
			this.printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.printLine("getRelatedSpus接口调用");
	}
	
	/**
	 * 在spu审核表插入一条记录
	 */
	public void insertAuditSpu_插入一条侍审核的spu记录(){
		this.printLine("insertAuditSpu接口调用");
		System.out.println("可以参照insertSpu_插入Spu()例子");
		//this.spuServiceClient.insertAuditSpu(spu);
		this.printLine("insertAuditSpu接口调用");
	}
	
	public void xiaoerUpdateSpuStatus_小二更新spu的状态(){
		this.printLine("xiaoerUpdateSpuStatus接口调用");
		spuId = 88799944;
		int status = SpuConstants.STATUS_ENABLE;
		try {
			BaseResultDO result = this.spuServiceClient.xiaoerUpdateSpuStatus(spuId, status);
			System.out.println(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.printLine("xiaoerUpdateSpuStatus接口调用");
	}
	
	public void deleteImage_删除spu图片(){
		this.printLine("deleteImage接口调用");
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
		this.printLine("deleteImage接口调用");
	}

	/**
	 * 打印输出结果
	 * 
	 * @param result
	 */
	private void printResult(ResultDO<?> result) {
		// TODO Auto-generated method stub
		if (result.isSuccess()) {
			System.out.println(String.format("操作结果:%s ;结果为:%s",
					result.isSuccess(), result.getModule()));
		} else {
			System.out.println(String.format("操作结果:%s ;错误信息为:%s",
					result.isSuccess(), result.getErrorStr()));
		}
	}

	/**
	 * 打印一行，带标题
	 * 
	 * @param title
	 *            标题
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
