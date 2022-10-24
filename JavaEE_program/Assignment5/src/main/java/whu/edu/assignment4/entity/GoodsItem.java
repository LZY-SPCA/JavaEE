package whu.edu.assignment4.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "商品实体")
public class GoodsItem {

    @Id
    @GeneratedValue
    @ApiModelProperty("商品编号")
    long id;

    @NonNull
    @ApiModelProperty("商品名称")
    String name;

    @ApiModelProperty("商品价格")
    double price;

    @ApiModelProperty("商品数量")
    int number;

    //@ApiModelProperty("供应商")
    //String provider;

    //@ApiModelProperty("供应商")
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    private List<Supplier> suppliers = new ArrayList<>();

}
