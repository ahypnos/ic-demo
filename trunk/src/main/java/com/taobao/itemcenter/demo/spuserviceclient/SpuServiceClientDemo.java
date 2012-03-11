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
	 * 通过指定spuId和参数查询选项查询对应的spu
	 * 参数查询选项可以自定义设置
	 */
	public void getSpu_通过指定spuId和参数查询选项查询对应的spu(){
		printLine("getSpu(long spuId,QuerySpuOptionDO options)接口调用");
		spuId = 263059L;
		
		//创建时设置为true值
		//true表示包含一条spu记录的所有信息, 比如描述, 属性, 图片等, 但不包括获取已删除和屏蔽的spu记录
		//false默认为空
		QuerySpuOptionDO option = new QuerySpuOptionDO(true);
		
		//也可以自定义自己需要查询的选项
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
		printLine("getSpu(long spuId,QuerySpuOptionDO options)接口调用");
	}
	
	/**
	 * 查询多个spu简单对象，即不查询spu的一些附属信息(相当于QuerySpuOptionsDO为默认的值)
	 * 传入一个spuId列表
	 */
	public void getSimpleSpus_获取默认信息的多个spu对象(){
		printLine("getSpu(long[] spuIds)接口调用");
		long[] spuIds = {263103,263102,261545,261535};
		try {
			ResultDO<List<SpuDO>> result = spuServiceClient.getSimpleSpus(spuIds);
			printResult(result);
		} catch (IcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLine("getSpu(long[] spuIds)接口调用");
	}
	
	public void updateSpuForMall_商城卖家或小二更新spu信息(){
		
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
