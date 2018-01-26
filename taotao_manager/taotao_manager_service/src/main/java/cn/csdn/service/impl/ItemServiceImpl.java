package cn.csdn.service.impl;

import cn.csdn.common.pojo.Item;
import cn.csdn.common.utils.IDUtils;
import cn.csdn.inter.IItemsService;
import cn.csdn.mapper.TbItemCatMapper;
import cn.csdn.mapper.TbItemDescMapper;
import cn.csdn.mapper.TbItemMapper;
import cn.csdn.pojo.TbItem;
import cn.csdn.pojo.TbItemCat;
import cn.csdn.pojo.TbItemCatExample;
import cn.csdn.pojo.TbItemDesc;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements IItemsService {

    @Resource
    private TbItemMapper itemMapper; //商品项 mapper

    @Resource
    private TbItemDescMapper itdescMapper; //商品描述mapper

    @Resource
    private TbItemCatMapper catMapper; //分类项

   public  List<TbItem> selectItems(Integer start,Integer rows){

       //使用分页助手
       PageHelper.startPage(start,rows);

       return  itemMapper.selectByExample(null);
   }


   public List<Item> getCatList(Long parentId){

       TbItemCatExample example = new TbItemCatExample();
       example.createCriteria().andParentIdEqualTo(parentId);

       List<TbItemCat> tbItemCats = catMapper.selectByExample(example);

       List<Item> items = new ArrayList<>();
       tbItemCats.stream().forEach(cat -> {

           //封装对象
           Item item = new Item();
           item.setId(cat.getId());
           item.setState(cat.getIsParent() ? "closed" : "open");
           item.setText(cat.getName());
           items.add(item);
       });
       return items;
   }

   public void saveItem(String desc,TbItem tbItem){

       //获取id
       long itemId = IDUtils.genItemId();

       //创建描述对象
       TbItemDesc tbDesc = new TbItemDesc();

       //封装参数
       tbItem.setId(itemId);
       tbItem.setCreated(new Date());
       tbItem.setUpdated(new Date());
       tbItem.setStatus((byte)1);  //商品上架

       tbDesc.setItemId(itemId);
       tbDesc.setItemDesc(desc);
       tbDesc.setCreated(new Date());
       tbDesc.setUpdated(new Date());

       //插入数据
       itemMapper.insert(tbItem);
       itdescMapper.insert(tbDesc);
   }
}
