package whu.edu.assignment4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import whu.edu.assignment4.Dao.GoodsRepository;
import whu.edu.assignment4.Dao.SupplierRepository;
import whu.edu.assignment4.entity.GoodsItem;
import whu.edu.assignment4.entity.Supplier;
import whu.edu.assignment4.service.GoodsService;
import whu.edu.assignment4.service.SupplierService;

import javax.transaction.Transactional;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class GoodsServiceTests {
    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    GoodsService goodsService;

    @Autowired
    SupplierService supplierService;


    @BeforeEach
    void init(){
        Supplier supplier = new Supplier();

        List<Supplier> suppliersList = new ArrayList<Supplier>();
        suppliersList.add(supplier);
        supplier.setName("机械革命");
        GoodsItem goodsItem = new GoodsItem();
        goodsItem.setName("显卡");
        goodsItem.setNumber(10);
        goodsItem.setPrice(5199);
        goodsItem.setSuppliers(suppliersList);
        supplierService.addSupplier(supplier);
        goodsService.addGoods(goodsItem);

        Supplier supplier2 = new Supplier();
        List<Supplier> suppliersList2 = new ArrayList<Supplier>();
        suppliersList2.add(supplier2);
        supplier2.setName("机械");
        GoodsItem goodsItem2 = new GoodsItem();
        goodsItem2.setName("硬盘");
        goodsItem2.setNumber(10);
        goodsItem2.setPrice(5199);
        goodsItem2.setSuppliers(suppliersList2);
        supplierService.addSupplier(supplier2);
        goodsService.addGoods(goodsItem2);
    }

    @Test
    public void testAddandFind(){
        Supplier supplier3 = new Supplier();

        List<Supplier> suppliersList3 = new ArrayList<Supplier>();
        suppliersList3.add(supplier3);
        supplier3.setName("瑞昱");
        GoodsItem goodsItem3 = new GoodsItem();
        //goodsItem3.setId(10);
        goodsItem3.setName("网卡");
        goodsItem3.setNumber(10);
        goodsItem3.setPrice(5199);
        goodsItem3.setSuppliers(suppliersList3);
        supplierService.addSupplier(supplier3);
        goodsService.addGoods(goodsItem3);
        List<GoodsItem> result = new ArrayList<GoodsItem>();
        result = goodsService.findGoods("网卡",null);
        assertNotNull(result);
        assertEquals(10,result.get(0).getNumber());
    }

    @Test
    public void testMultiProvider(){
        Supplier supplier = new Supplier();
        supplier.setName("瑞昱");
        List<Supplier> suppliersList = new ArrayList<Supplier>();
        suppliersList.add(supplier);

        Supplier supplier2 = new Supplier();
        supplier2.setName("NV");
        suppliersList.add(supplier2);
        GoodsItem goodsItem = new GoodsItem();
        //goodsItem3.setId(10);
        goodsItem.setName("有线网卡");
        goodsItem.setNumber(10);
        goodsItem.setPrice(5199);
        goodsItem.setSuppliers(suppliersList);
        goodsItem.getSuppliers().forEach(esupplier -> {
            List<Supplier> suppliers = supplierService.findSuppliers(supplier.getName());
            if(suppliers.isEmpty()){
                Supplier supplier1 = supplierService.addSupplier(supplier);
                esupplier.setId(supplier1.getId());
            }
            else {
                esupplier.setId(suppliers.get(0).getId());
            }
        });
        System.out.println(goodsItem.getId());
        GoodsItem result = goodsService.addGoods(goodsItem);
        assertNotNull(result);
        assertEquals(5199,result.getPrice());
    }

    @Test
    @Transactional
    public void testDeleteGoods(){
        List<GoodsItem> result = new ArrayList<GoodsItem>();
        result =goodsService.findGoods("硬盘",null);
        goodsService.deleteGoods(result.get(0).getId());
        assertEquals(Optional.empty(),goodsService.getGoods(result.get(0).getId()));
    }

    @Test
    public void testUpdateGoods(){
        List<GoodsItem> result = new ArrayList<GoodsItem>();
        result =goodsService.findGoods("硬盘",null);
        //goodsService.deleteGoods(result.get(0).getId());
        Supplier supplier2 = new Supplier();
        List<Supplier> suppliersList2 = new ArrayList<Supplier>();
        suppliersList2.add(supplier2);
        supplier2.setName("机械");
        GoodsItem goodsItem2 = new GoodsItem();
        goodsItem2.setName("硬盘");
        goodsItem2.setNumber(10);
        goodsItem2.setPrice(8888);
        goodsItem2.setSuppliers(suppliersList2);

        //goodsRepository.flush();
        goodsItem2.getSuppliers().forEach(esupplier -> {
            List<Supplier> suppliers = supplierService.findSuppliers(supplier2.getName());
            if(suppliers.isEmpty()){
                Supplier supplier1 = supplierService.addSupplier(supplier2);
                esupplier.setId(supplier1.getId());
            }
            else {
                esupplier.setId(suppliers.get(0).getId());
            }
        });
        System.out.println(result.get(0).getId());
        goodsService.updateGoods(result.get(0).getId(),goodsItem2);
        result.clear();
        result =goodsService.findGoods("硬盘",null);
        assertEquals(8888,result.get(0).getPrice());

    }
}
