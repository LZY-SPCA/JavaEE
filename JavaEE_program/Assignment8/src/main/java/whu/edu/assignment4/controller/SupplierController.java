package whu.edu.assignment4.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import whu.edu.assignment4.entity.GoodsItem;
import whu.edu.assignment4.entity.Supplier;
import whu.edu.assignment4.service.SupplierService;

import java.util.Optional;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplier(@ApiParam("供应商id")@PathVariable long id){
        Optional<Supplier> result = supplierService.getSupplier(id);
        if(!result.isPresent()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result.get());
        }
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('supplier/mange')")
    public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier){
        supplierService.addSupplier(supplier);
        return ResponseEntity.ok(supplier);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('supplier/manage')")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable long id, @RequestBody Supplier supplier) {
        try {
            supplierService.updateSupplier(id, supplier);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(supplier);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('supplier/manage')")
    public ResponseEntity deleteSupplier(@PathVariable long id) {
        try {
            supplierService.deleteSupplier(id);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
