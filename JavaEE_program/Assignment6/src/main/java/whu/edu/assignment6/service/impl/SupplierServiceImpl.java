package whu.edu.assignment6.service.impl;

import whu.edu.assignment6.domain.Supplier;
import whu.edu.assignment6.dao.SupplierDao;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import whu.edu.assignment6.service.ISupplierService;
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
public class SupplierServiceImpl extends ServiceImpl<SupplierDao, Supplier> implements ISupplierService {


    @SelectKey(statement = "select last_insert_id()",keyProperty = "id",keyColumn = "id",resultType = Long.class,before = true)
    public Supplier addSupplier(Supplier supplier){
        if(findSupplierByName(supplier.getName()).isEmpty()){
            getBaseMapper().insert(supplier);
            return supplier;
        }
        else{
            return null;
        }
    }

    public List<Supplier> findSupplierByName(String name){
        LambdaQueryWrapper<Supplier> suppliers = new LambdaQueryWrapper<>();
        suppliers.like(Supplier::getName,name);
        List<Supplier> resultList = getBaseMapper().selectList(suppliers);
        return resultList;
    }

    public String findNameById(long id){
        Supplier supplier = getBaseMapper().selectById(id);
        return supplier.getName();
    }




}
