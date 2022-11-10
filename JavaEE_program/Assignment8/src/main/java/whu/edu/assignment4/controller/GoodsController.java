package whu.edu.assignment4.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import whu.edu.assignment4.entity.GoodsItem;
import whu.edu.assignment4.entity.Supplier;
import whu.edu.assignment4.service.GoodsService;
import whu.edu.assignment4.service.SupplierService;

import javax.transaction.Transactional;
import java.io.Console;
import java.util.List;
import java.util.Optional;

@Schema(description = "商品清单")
@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @Autowired
    SupplierService supplierService;

    @ApiOperation("根据ID查询商品")
    @GetMapping("/{id}")
    public ResponseEntity<GoodsItem> getGoodsItem(@ApiParam("商品id")@PathVariable long id){
        Optional<GoodsItem> result = goodsService.getGoods(id);
        if(!result.isPresent()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result.get());
        }
    }

    @ApiOperation("根据名字和供应商查询商品")
    @GetMapping("")
    public ResponseEntity<List<GoodsItem>> findGoodsItem(@ApiParam("商品名称")String name,@ApiParam("供应商")String provider){
        List<GoodsItem> result = goodsService.findGoods(name, provider);
        if(result.size() > 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.noContent().build();
        }

    }

    @ApiOperation("添加商品")
    @PostMapping("")
    @Transactional(rollbackOn = Exception.class)
    @PreAuthorize("hasAuthority('goods/manage')")
    public ResponseEntity<GoodsItem> addGoodsItem(@RequestBody GoodsItem goodsItem) throws Exception {
        GoodsItem result = null;
        //try {
        goodsItem.getSuppliers().forEach(supplier -> {
            List<Supplier> suppliers = supplierService.findSuppliers(supplier.getName());
            if(suppliers.isEmpty()){
                Supplier supplier1 = supplierService.addSupplier(supplier);
                supplier.setId(supplier1.getId());
            }
            else {
                supplier.setId(suppliers.get(0).getId());
            }
        });
            System.out.println(goodsItem.getId());
            result = goodsService.addGoods(goodsItem);

            return ResponseEntity.ok(result);
//        } catch (Exception e) {
//            System.out.println(e.getClass());
//            throw new Exception("添加错误");
//        }


    }

    @ApiOperation("修改商品")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('goods/manage')")
    public ResponseEntity<Void> updateGoodsItem(@PathVariable("id") long id,@RequestBody GoodsItem goodsItem){
        try{
            goodsItem.getSuppliers().forEach(supplier -> {
                List<Supplier> suppliers = supplierService.findSuppliers(supplier.getName());
                if(suppliers.isEmpty()){
                    Supplier supplier1 = supplierService.addSupplier(supplier);
                    supplier.setId(supplier1.getId());
                }
                else {
                    supplier.setId(suppliers.get(0).getId());
                }
            });
            goodsService.updateGoods(id,goodsItem);
        }
        catch (NullPointerException e){
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok().build();
    }


    @ApiOperation("删除商品")
    @DeleteMapping ("/{id}")
    @PreAuthorize("hasAuthority('goods/manage')")
    public ResponseEntity<Void> deleteGoodsItem(@PathVariable("id") long id) {
        try{
            goodsService.deleteGoods(id);
        }
        catch (NullPointerException e){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }



}
