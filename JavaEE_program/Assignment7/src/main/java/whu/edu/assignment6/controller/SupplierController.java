package whu.edu.assignment6.controller;


import whu.edu.assignment6.domain.Supplier;
import whu.edu.assignment6.service.impl.SupplierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierServiceImpl supplierService;

    @PostMapping("")
    public Supplier addSupplier(@RequestBody Supplier supplier){

        if(supplierService.addSupplier(supplier)!=null){
            return supplier;
        }else{
            return null;
        }
    }


    public List<Supplier> findSupplierByName(String Name){
        return supplierService.findSupplierByName(Name);
    }



}

