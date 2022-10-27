package whu.edu.assignment6.service.impl;

import whu.edu.assignment6.dao.GoodsItemDao;
import whu.edu.assignment6.domain.Goods_item;
import whu.edu.assignment6.domain.Supplier;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import whu.edu.assignment6.exception.ProductAdminException;
import whu.edu.assignment6.service.IGoods_itemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzy
 * @since 2022-10-25
 */
@Service
public class GoodsItemServiceImpl extends ServiceImpl<GoodsItemDao, Goods_item> implements IGoods_itemService {



    @SelectKey(statement = "select last_insert_id()",keyProperty = "id",keyColumn = "id",resultType = Long.class,before = true)
    public Goods_item addGoodsItem(Goods_item goods_item){
        LambdaQueryWrapper<Goods_item> lqw = new LambdaQueryWrapper<>();
        lqw.like(Goods_item::getName,goods_item.getName());
        if(getBaseMapper().selectList(lqw).size()==0){
            getBaseMapper().insert(goods_item);
        }
        else{
            return null;
        }
        return goods_item;
    }


    public Goods_item findById(long id){
        return getBaseMapper().selectById(id);
    }

    public Goods_item updateGood(long id,Goods_item goods){

        deleteById(id);
        if(addGoodsItem(goods)!=null){
            return goods;
        }else{
            return null;
        }
    }

    public void deleteById(long id){
        int i = getBaseMapper().deleteById(id);

        System.out.println(i+"--------------------------");
    }

    public int typeCount(String type){
        LambdaQueryWrapper<Goods_item> lqw = new LambdaQueryWrapper<>();
        lqw.like(Goods_item::getType,type);
        return getBaseMapper().selectCount(lqw);
    }

    public Goods_item findByName(String name) throws ProductAdminException {
        LambdaQueryWrapper<Goods_item> lqw = new LambdaQueryWrapper<>();
        lqw.like(Goods_item::getName,name);
        Goods_item result= new Goods_item();
        try {
            result = getBaseMapper().selectList(lqw).get(0);
        }catch (Exception e) {
            throw new ProductAdminException("查找结果为空");
        }
        return result;
    }

}
