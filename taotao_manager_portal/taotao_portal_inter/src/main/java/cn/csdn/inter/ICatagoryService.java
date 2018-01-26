package cn.csdn.inter;

import cn.csdn.common.pojo.Item;
import cn.csdn.pojo.TbContentCategory;
import cn.csdn.pojo.TbItemCat;

import java.util.List;

public interface ICatagoryService {
    public List<Item> findItemCat(Long parentId);

    public TbContentCategory createCatagory(TbContentCategory contentCatagory);
}
