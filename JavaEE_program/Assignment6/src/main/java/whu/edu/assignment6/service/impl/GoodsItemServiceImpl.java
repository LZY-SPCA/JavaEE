package whu.edu.assignment6.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import whu.edu.assignment6.dao.GoodsItemDao;
import whu.edu.assignment6.domain.Goods_item;
import whu.edu.assignment6.domain.Supplier;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import whu.edu.assignment6.exception.ProductAdminException;
import whu.edu.assignment6.service.IGoods_itemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Goods_item addGoodsItem(Goods_item goods_item) throws ProductAdminException{
        LambdaQueryWrapper<Goods_item> lqw = new LambdaQueryWrapper<>();
        lqw.like(Goods_item::getName,goods_item.getName());
        if(getBaseMapper().selectList(lqw).size()==0){
            getBaseMapper().insert(goods_item);
        }
        else{
            throw new ProductAdminException("商品已存在");
        }
        return goods_item;
    }


    public Goods_item findById(long id){
        return getBaseMapper().selectById(id);
    }

    public Goods_item updateGood(long id,Goods_item goods) throws ProductAdminException{

        deleteById(id);
        if(addGoodsItem(goods)!=null){
            return goods;
        }else{
            throw new ProductAdminException("更新商品失败");
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

    public IPage findPageByType(){
        IPage result = new Page<>(1,5).addOrder(OrderItem.asc("price"));
        IPage returnPage;
        try {
           returnPage  = getBaseMapper().selectPage(result, null);
        }
        catch (Exception e){
            throw new ProductAdminException("查询页表失败");
        }
        return returnPage;
    }

    public List<Goods_item> findPriceBtw(double low,double high){
        LambdaQueryWrapper<Goods_item> lqw = new LambdaQueryWrapper<>();
        lqw.gt(Goods_item::getPrice,low).lt(Goods_item::getPrice,high);
        return getBaseMapper().selectList(lqw);
    }

}