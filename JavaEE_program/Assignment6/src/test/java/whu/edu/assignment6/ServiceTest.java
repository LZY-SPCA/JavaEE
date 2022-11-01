package whu.edu.assignment6;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.annotation.Rollback;
import whu.edu.assignment6.domain.Goods_item;
import whu.edu.assignment6.domain.Goods_item_suppliers;
import whu.edu.assignment6.domain.Supplier;
import whu.edu.assignment6.exception.ProductAdminException;
import whu.edu.assignment6.service.impl.GoodsItemServiceImpl;
import whu.edu.assignment6.service.impl.GoodsItemSuppliersServiceImpl;
import whu.edu.assignment6.service.impl.SupplierServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ServiceTest {
    @Autowired
    GoodsItemServiceImpl goodsItemService;

    @Autowired
    GoodsItemSuppliersServiceImpl combinedService;

    @Autowired
    SupplierServiceImpl supplierService;



    @Test
    public void addTest() {
        Goods_item item = new Goods_item();
        item.setName("手机");
        item.setType("b");
        item.setNumber(99);
        item.setPrice(2999.0);
        Supplier supplier = new Supplier();
        supplier.setName("小米");
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(supplier);
        item.setSuppliers(suppliers);
        Goods_item result = goodsItemService.addGoodsItem(item);
        supplierService.addSupplier(supplier);
        System.out.println(result);
        //获取生产者ID
        for (Supplier su : item.getSuppliers()) {
            su.setId(supplierService.findSupplierByName(su.getName()).get(0).getId());
            combinedService.addNew(item);
        }
        System.out.println(supplierService.findSupplierByName("小米").get(0).getId());
        System.out.println(combinedService.findByGoodsId(goodsItemService.findByName("手机").getId()).size());
        assertNotNull(result);
    }

    @Test
    public void findTest() throws ProductAdminException {
        Goods_item item = new Goods_item();
        item.setName("手机");
        item.setType("b");
        item.setNumber(99);
        item.setPrice(2999.0);
        Supplier supplier = new Supplier();
        supplier.setName("小米");
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(supplier);
        item.setSuppliers(suppliers);
        Goods_item result = goodsItemService.addGoodsItem(item);
        supplierService.addSupplier(supplier);
        System.out.println(result);
        //获取生产者ID
        for (Supplier su : item.getSuppliers()) {
            su.setId(supplierService.findSupplierByName(su.getName()).get(0).getId());
            combinedService.addNew(item);
        }

        Goods_item target = goodsItemService.findById(goodsItemService.findByName("手机").getId());
        assertNotNull(target);
    }

    @Test
    public void deleteTest() {
        Goods_item item = new Goods_item();
        item.setName("手机");
        item.setType("b");
        item.setNumber(99);
        item.setPrice(2999.0);
        Supplier supplier = new Supplier();
        supplier.setName("小米");
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(supplier);
        item.setSuppliers(suppliers);
        Goods_item result = goodsItemService.addGoodsItem(item);
        supplierService.addSupplier(supplier);
        System.out.println(result);
        //获取生产者ID
        for (Supplier su : item.getSuppliers()) {
            su.setId(supplierService.findSupplierByName(su.getName()).get(0).getId());
            combinedService.addNew(item);
        }
        try{
            goodsItemService.deleteById(goodsItemService.findByName("手机").getId());

        }catch (ProductAdminException e){
            assertEquals(e.getMessage(),"查找结果为空");
        }
    }

    @Test
    public void updateTest(){
        System.out.println(combinedService.findByGoodsId(goodsItemService.findByName("拯救者").getId()).size());
        Goods_item item = new Goods_item();
        item.setName("手机");
        item.setType("b");
        item.setNumber(99);
        item.setPrice(2999.0);
        Supplier supplier = new Supplier();
        supplier.setName("小米");
        Supplier supplier1 = new Supplier();
        supplier1.setName("oppo");
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(supplier);
        suppliers.add(supplier1);
        item.setSuppliers(suppliers);

        supplierService.addSupplier(supplier);
        supplierService.addSupplier(supplier1);
        goodsItemService.addGoodsItem(item);

        //获取生产者ID
        for (Supplier su : item.getSuppliers()) {
            su.setId(supplierService.findSupplierByName(su.getName()).get(0).getId());
            combinedService.addNew(item);
        }


        item.setPrice(9999.9);
        long id =goodsItemService.findByName("手机").getId();
        System.out.println(combinedService.findByGoodsId(id).size());

        combinedService.deleteByGoodsId(id);
        goodsItemService.updateGood(id, item);
        for(Supplier su:item.getSuppliers()){
            su.setId(supplierService.findSupplierByName(su.getName()).get(0).getId());
        }
        Goods_item_suppliers re = combinedService.addNew(item);
        id =goodsItemService.findByName("手机").getId();
        List<Supplier> supplierList = combinedService.findByGoodsId(id);
        //assertEquals(2, combinedService.f);
        assertEquals(9999.9,goodsItemService.findByName("手机").getPrice());
        assertEquals(2,combinedService.findByGoodsId(id).size());
    }

    @Test
    public void testPageFind(){
        //需要依赖已有数据
        IPage result = goodsItemService.findPageByType();
        System.out.println(result);
        assertEquals(1,result.getPages());
        assertEquals(4,result.getRecords().size());
    }

    @Test
    public void testPriceBtw(){
        //需要依赖已有数据
        List<Goods_item> result = goodsItemService.findPriceBtw(4100.0,9000.0);
        assertEquals(2,result.size());
    }

}
