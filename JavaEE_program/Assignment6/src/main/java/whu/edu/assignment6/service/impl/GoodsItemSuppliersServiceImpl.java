package whu.edu.assignment6.service.impl;

import whu.edu.assignment6.dao.GoodsItemSuppliersDao;
import whu.edu.assignment6.domain.Goods_item;
import whu.edu.assignment6.domain.Goods_item_suppliers;
import whu.edu.assignment6.domain.Supplier;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import whu.edu.assignment6.service.IGoods_item_suppliersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class GoodsItemSuppliersServiceImpl extends ServiceImpl<GoodsItemSuppliersDao, Goods_item_suppliers> implements IGoods_item_suppliersService {

    public Goods_item_suppliers addNew(Goods_item goodsItem){
        Goods_item_suppliers combined = new Goods_item_suppliers();
        for(Supplier supplier:goodsItem.getSuppliers()){
            combined.setGoodsItemId(goodsItem.getId());
            combined.setSuppliersId(supplier.getId());
            getBaseMapper().insert(combined);
        }
        return combined;
    }

    public List<Supplier> findByGoodsId(long id){
        List<Supplier> result = new ArrayList<>();
        LambdaQueryWrapper<Goods_item_suppliers> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Goods_item_suppliers::getGoodsItemId,id);
        List<Goods_item_suppliers> combined = getBaseMapper().selectList(lqw);
        for(Goods_item_suppliers com:combined){
            Supplier supplier = new Supplier();
            supplier.setId(com.getSuppliersId());
            result.add(supplier);
        }
        return result;
    }

    public void deleteByGoodsId(long id){
        LambdaQueryWrapper<Goods_item_suppliers> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods_item_suppliers::getGoodsItemId,id);
        getBaseMapper().delete(wrapper);
    }

}
