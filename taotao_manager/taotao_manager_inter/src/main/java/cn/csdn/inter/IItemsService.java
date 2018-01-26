package cn.csdn.inter;

import cn.csdn.common.pojo.Item;
import cn.csdn.pojo.TbItem;

import java.util.List;

public interface IItemsService {
    public List<TbItem> selectItems(Integer start, Integer rows);
    public List<Item> getCatList(Long parentId);
    public void saveItem(String desc,TbItem tbItem);
}
