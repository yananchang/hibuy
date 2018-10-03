package com.hibuy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hibuy.common.pojo.EasyUIDataGridResult;
import com.hibuy.common.pojo.TaotaoResult;
import com.hibuy.common.utils.IDUtils;
import com.hibuy.mapper.TbItemDescMapper;
import com.hibuy.mapper.TbItemMapper;
import com.hibuy.pojo.TbItem;
import com.hibuy.pojo.TbItemDesc;
import com.hibuy.pojo.TbItemExample;
import com.hibuy.service.ItemService;

/**
 * Product Mgmt Service
 * @author Yanan Chang
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	
	public TbItem getItemById(long itemId) {
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		return item;
	}


	public EasyUIDataGridResult getItemList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//取查询结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		
		//返回结果
		return result;
	}


	public TaotaoResult addItem(TbItem item, String desc) {
		// 生成商品的id
		long itemId = IDUtils.genItemId();
		//补全item的属性
		item.setId(itemId);
		//商品状态, 1-正常, 2-下架, 3-删除
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//向商品表中插入数据
		itemMapper.insert(item);
		//创建商品描述表对应的pojo
		TbItemDesc itemDesc = new TbItemDesc();
		//补全pojo的属性
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(new Date());
		itemDesc.setCreated(new Date());
		//向商品描述表插入数据
		itemDescMapper.insert(itemDesc);
		//返回结果
		return TaotaoResult.ok();
	}

}
