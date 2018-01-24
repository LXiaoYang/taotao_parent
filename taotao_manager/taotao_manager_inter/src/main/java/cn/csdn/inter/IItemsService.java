package cn.csdn.inter;

import cn.csdn.pojo.TbItem;

import java.util.List;

public interface IItemsService {
    public List<TbItem> selectItems(Integer start, Integer rows);
}
