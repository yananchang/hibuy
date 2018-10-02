package com.hibuy.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hibuy.mapper.TbItemMapper;
import com.hibuy.pojo.TbItem;
import com.hibuy.pojo.TbItemExample;

public class TestPageHelper {

	@Test
	public void testPageHelper() throws Exception{
		//1. 先在mybatis的配置文件中配置分页插件
		//2. 在执行查询之前, 配置分页条件, 使用PageHelper的静态方法
		PageHelper.startPage(1, 10);
		
		//3. 执行查询
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		//创建Example对象
		TbItemExample example = new TbItemExample();
//		Criteria criteria = example.createCriteria();
		
		List<TbItem> list = itemMapper.selectByExample(example);
		
		//4. 取分页信息, 使用PageInfo对象取;
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		System.out.println("Total records: " + pageInfo.getTotal());
		System.out.println("Total pagess: " + pageInfo.getPages());
		System.out.println("Returned records: " + list.size());
	}
}

















