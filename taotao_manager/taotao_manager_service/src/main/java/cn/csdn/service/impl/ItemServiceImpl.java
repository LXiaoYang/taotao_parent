package cn.csdn.service.impl;

import cn.csdn.inter.IItemsService;
import cn.csdn.mapper.TbItemMapper;
import cn.csdn.pojo.TbItem;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemServiceImpl implements IItemsService {

    @Resource
    private TbItemMapper itemMapper;

   public  List<TbItem> selectItems(Integer start,Integer rows){

       //使用分页助手
       PageHelper.startPage(start,rows);

       return  itemMapper.selectByExample(null);
   }
}
