package com.hibuy.service;

import java.util.List;

import com.hibuy.common.pojo.EasyUITreeNode;

public interface ItemCatService {

	List<EasyUITreeNode> getItemCatList(long parentId);
}
