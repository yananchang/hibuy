package com.hibuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hibuy.common.pojo.EasyUITreeNode;
import com.hibuy.service.ItemCatService;

/**
 * Controller for item category mgmt
 * @author Yanan Chang
 *
 */
@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService ItemCatService;
	
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> getItemCatList(@RequestParam(name="id", defaultValue="0")Long parentId){
		List<EasyUITreeNode> list = ItemCatService.getItemCatList(parentId);
		return list;
	}
}
