package cn.csdn.service.impl;

import cn.csdn.common.pojo.Item;
import cn.csdn.inter.ICatagoryService;
import cn.csdn.mapper.TbContentCategoryMapper;
import cn.csdn.mapper.TbItemCatMapper;
import cn.csdn.pojo.TbContentCategory;
import cn.csdn.pojo.TbContentCategoryExample;
import cn.csdn.pojo.TbItemCat;
import cn.csdn.pojo.TbItemCatExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICatagoryService {

   @Resource
    private TbContentCategoryMapper tbContentCategoryMapper;

   //查询分类
   public List<Item> findItemCat(Long parentId){

//       /封装参数条件
       TbContentCategoryExample example = new TbContentCategoryExample();
       example.createCriteria().andParentIdEqualTo(parentId);

       //查询
       List<TbContentCategory> catas = tbContentCategoryMapper.selectByExample(example);

       //创建一个容器
       List<Item> items = new ArrayList<Item>();

       for (TbContentCategory cat : catas) {
           Item item = new Item();

           item.setId(cat.getId());
           item.setText(cat.getName());
           item.setState(cat.getIsParent() ? "closed" : "open");

           items.add(item);
       }

       
       return items;
   }

   //增加分类
   public TbContentCategory createCatagory(TbContentCategory contentCatagory){

       contentCatagory.setIsParent(false);
       contentCatagory.setSortOrder(1);
       contentCatagory.setCreated(new Date());
       contentCatagory.setStatus(1);
       contentCatagory.setUpdated(new Date());

       tbContentCategoryMapper.insert(contentCatagory);


       //根据父id 修改父id 状态
       TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
       tbContentCategoryExample.createCriteria().andParentIdEqualTo(contentCatagory.getId());
       List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);

       if(tbContentCategories != null && tbContentCategories.size() > 0){
           TbContentCategory tbContentCategory = tbContentCategories.get(0);
           tbContentCategory.setIsParent(true);
           tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
       }


//
//       itemCatMapper.insert(tbItemCat);

       return contentCatagory;
   }

}
