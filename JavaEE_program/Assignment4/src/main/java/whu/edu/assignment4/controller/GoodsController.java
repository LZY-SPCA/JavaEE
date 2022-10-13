package whu.edu.assignment4.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import whu.edu.assignment4.entity.GoodsItem;
import whu.edu.assignment4.service.GoodsService;

import java.util.List;

@Schema(description = "商品清单")
@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @ApiOperation("根据ID查询商品")
    @GetMapping("/{id}")
    public ResponseEntity<GoodsItem> getGoodsItem(@ApiParam("商品id")@PathVariable long id){
        GoodsItem result = goodsService.getGoods(id);
        if(result == null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
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
    public ResponseEntity<GoodsItem> addGoodsItem(@RequestBody GoodsItem goodsItem){
        GoodsItem result = goodsService.addGoods(goodsItem);
        return ResponseEntity.ok(result);
    }

    @ApiOperation("修改商品")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGoodsItem(@PathVariable("id") long id,@RequestBody GoodsItem goodsItem){
        try{goodsService.updateGoods(id,goodsItem);}
        catch (NullPointerException e){
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok().build();
    }


    @ApiOperation("删除商品")
    @DeleteMapping ("/{id}")
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
