package whu.edu.assignment4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import whu.edu.assignment4.Dao.GoodsRepository;
import whu.edu.assignment4.entity.GoodsItem;

import javax.persistence.criteria.Predicate;
import java.util.*;


@Service

public class GoodsService {

    @Autowired
    GoodsRepository repository;

    //private Map<Long, GoodsItem> goods = Collections.synchronizedMap(new HashMap<Long, GoodsItem>());

    public GoodsItem addGoods(GoodsItem good){

        return repository.saveAndFlush(good);
    }

    public Optional<GoodsItem> getGoods(long id){

        return repository.findById(id);
    }



    public List<GoodsItem> findGoods(String name, String provider){
        Specification<GoodsItem> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if(name != null){
                predicateList.add(criteriaBuilder.like(root.get("name"),"%" + name + "%"));
            }
            if(provider!=null){
                predicateList.add(criteriaBuilder.like(root.get("provider"),"%" + provider +"%"));
            }
            Predicate[] predicates = predicateList.toArray(new Predicate[predicateList.size()]);
            return criteriaBuilder.and(predicates);

        };
        List<GoodsItem> result = repository.findAll(specification);
        return result;
    }
    public void updateGoods(long id,GoodsItem good) throws NullPointerException{
        repository.deleteById(id);
        repository.save(good);
    }

    public void deleteGoods(long id) throws NullPointerException{
        repository.deleteById(id);


    }
}
