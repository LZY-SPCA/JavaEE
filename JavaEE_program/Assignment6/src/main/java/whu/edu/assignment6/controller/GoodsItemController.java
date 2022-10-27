package whu.edu.assignment6.controller;


import whu.edu.assignment6.domain.Goods_item;
import whu.edu.assignment6.domain.Goods_item_suppliers;
import whu.edu.assignment6.domain.Supplier;
import whu.edu.assignment6.service.impl.GoodsItemServiceImpl;
import whu.edu.assignment6.service.impl.GoodsItemSuppliersServiceImpl;
import whu.edu.assignment6.service.impl.SupplierServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzy
 * @since 2022-10-25
 */

@RestController
@RequestMapping("/goods")
public class GoodsItemController {
    @Autowired
    GoodsItemServiceImpl goodsItemService;

    @Autowired
    GoodsItemSuppliersServiceImpl combinedService;

    @Autowired
    SupplierServiceImpl supplierService;

    @ApiOperation("加入商品")
    @PostMapping("")
    public Goods_item addGoodsItem(@RequestBody Goods_item goodsItem){
        Goods_item result = goodsItemService.addGoodsItem(goodsItem);
        if(result!=null){
            //获取生产者ID
            for(Supplier su:goodsItem.getSuppliers()){
                su.setId(supplierService.findSupplierByName(su.getName()).get(0).getId());
            }

            combinedService.addNew(goodsItem);
            return result;
        }
        else{
            return null;
        }
    }

    @ApiOperation("ID查找商品")
    @GetMapping("/{id}")
    public Goods_item findById(@PathVariable long id){
        List<Supplier> suppliers = combinedService.findByGoodsId(id);
        Goods_item goods = goodsItemService.findById(id);

        for(Supplier su : suppliers){
            su.setName(supplierService.findNameById(su.getId()));
        }
        goods.setSuppliers(suppliers);
        return goods;
    }

    @PutMapping("/{id}")
    public Goods_item update(@PathVariable long id, @RequestBody Goods_item goods){
        combinedService.deleteByGoodsId(id);
        Goods_item good = goodsItemService.updateGood(id, goods);
        System.out.println(good);
        for(Supplier su:good.getSuppliers()){
            su.setId(supplierService.findSupplierByName(su.getName()).get(0).getId());
        }
        combinedService.addNew(goods);
        return good;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        goodsItemService.deleteById(id);
        combinedService.deleteByGoodsId(id);
    }

    @GetMapping("")
    public int typeCount(@RequestParam(defaultValue = "a")String type){
        return goodsItemService.typeCount(type);

    }






}

