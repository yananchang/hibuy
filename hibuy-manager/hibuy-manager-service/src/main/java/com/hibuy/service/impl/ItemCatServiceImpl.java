package com.hibuy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibuy.common.pojo.EasyUITreeNode;
import com.hibuy.mapper.TbItemCatMapper;
import com.hibuy.pojo.TbItemCat;
import com.hibuy.pojo.TbItemCatExample;
import com.hibuy.pojo.TbItemCatExample.Criteria;
import com.hibuy.service.ItemCatService;

/**
 * Service for item category mgmt
 * @author Yanan Chang
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	public List<EasyUITreeNode> getItemCatList(long parentId) {
		//根据父节点的id查询子节点列表
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		//设置parentId
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//转换成EasyUITreeNode列表
		List<EasyUITreeNode> resultList = new ArrayList<>(); 
		for(TbItemCat tbItemCat:list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			//如果节点下有子节点就"closed", 如果没有子节点就"open"
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			//添加到节点列表
			resultList.add(node);
		}
		return resultList;
	}

}
