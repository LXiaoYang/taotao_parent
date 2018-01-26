package cn.csdn.controller;

import cn.csdn.common.pojo.Item;
import cn.csdn.common.result.TaotaoResult;
import cn.csdn.inter.ICatagoryService;
import cn.csdn.inter.IItemsService;
import cn.csdn.pojo.TbContentCategory;
import cn.csdn.pojo.TbItem;
import cn.csdn.pojo.TbItemCat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
 @SuppressWarnings("all")
public class ItemsController {

    @Resource
    private IItemsService itemsService;

    @Resource
    private ICatagoryService catagoryService;

    //展示商品列表
    @RequestMapping("/{page}")
    public String selectItems(@PathVariable String page){
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


    //查询类目
    @RequestMapping("/item/cat/list")
    @ResponseBody                                    //父菜单id            如果没有传参数,那就是第一次初始化顶级目录,顶级目录的 pid 是0
    public List<Item> getType(@RequestParam(value = "id",defaultValue = "0") Long parentId){

        List<Item> catList = itemsService.getCatList(parentId);

        return catList;
    }
    @RequestMapping("/item/save")
    @ResponseBody
    public TaotaoResult save(String desc,TbItem tbItem){


        itemsService.saveItem(desc, tbItem);
        return new TaotaoResult("200");
    }

//    /content/category/list  查询分类功能.....
    @ResponseBody
    @RequestMapping("/content/category/list")  //如果没有传id,就是父节点,,
    public List<Item> findItemCat(@RequestParam(value = "id",defaultValue = "0") Long parentId){

        return catagoryService.findItemCat(parentId);
    }

//    /content/category/create  //增加分类的方法
    @RequestMapping("/content/category/create")
    @ResponseBody
    public TaotaoResult createCatagory(TbContentCategory category){

        TbContentCategory catagory = catagoryService.createCatagory(category);
        TaotaoResult result = new TaotaoResult("200");
        result.setData(catagory);

        return result;
    }
}
