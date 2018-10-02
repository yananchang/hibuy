package com.hibuy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibuy.mapper.TbItemMapper;
import com.hibuy.pojo.TbItem;
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
	
	
	public TbItem getItemById(long itemId) {
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		return item;
	}

}
