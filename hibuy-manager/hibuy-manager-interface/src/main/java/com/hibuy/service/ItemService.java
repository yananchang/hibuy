package com.hibuy.service;

import com.hibuy.common.pojo.EasyUIDataGridResult;
import com.hibuy.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(long itemId);
	EasyUIDataGridResult getItemList(int page, int rows);
}
