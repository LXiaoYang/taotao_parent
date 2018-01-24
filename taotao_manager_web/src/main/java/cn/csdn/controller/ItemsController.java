package cn.csdn.controller;

import cn.csdn.inter.IItemsService;
import cn.csdn.pojo.TbItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ItemsController {

    @Resource
    private IItemsService itemsService;

    //展示商品列表
    @RequestMapping("/{page}")
    public String items(@PathVariable String page){
        return page;
    }

    //分页查询数据
    @RequestMapping("/item/list")
    @ResponseBody
    public Map<String,Object> limitSelectItems(Integer page,Integer rows){

        //查询数据
        List<TbItem> items = itemsService.selectItems(page,rows);

        //封装数据
        Map<String,Object>map = new HashMap<String,Object>();
        map.put("total",items.size());
        map.put("rows",items);
        return map;
    }

}
