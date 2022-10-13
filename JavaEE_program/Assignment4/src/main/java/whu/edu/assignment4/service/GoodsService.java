package whu.edu.assignment4.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import whu.edu.assignment4.entity.GoodsItem;

import java.util.*;

@Service

@Repository
public class GoodsService {

    private Map<Long, GoodsItem> goods = Collections.synchronizedMap(new HashMap<Long, GoodsItem>());

    public GoodsItem addGoods(GoodsItem good){
        goods.put(good.getId(), good);
        return good;
    }

    public GoodsItem getGoods(long id){
        return goods.get(id);
    }

    public List<GoodsItem> findGoods(String name, String provider){
        List<GoodsItem> result=new ArrayList<>();
        for(GoodsItem good:goods.values()){
            if(name!=null && !good.getName().contains(name)) {
                continue;
            }
            if(provider!=null && !good.getProvider().contains(provider)){
                continue;
            }
            result.add(good);
        }
        return result;
    }
    public void updateGoods(long id,GoodsItem good) throws NullPointerException{
        GoodsItem good2 = goods.get(id);

            good2.setName(good.getName());
            good2.setPrice(good.getPrice());
            good2.setProvider(good.getProvider());
            goods.put(id,good2);


    }

    public void deleteGoods(long id) throws NullPointerException{
        Object obj = goods.remove(id);
        if(obj==null){
            throw new NullPointerException();
        }


    }
}
