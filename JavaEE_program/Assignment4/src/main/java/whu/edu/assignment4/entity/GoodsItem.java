package whu.edu.assignment4.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.awt.HKSCS;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "商品实体")
public class GoodsItem {

    @ApiModelProperty("商品编号")
    long id;

    @ApiModelProperty("商品名称")
    String name;

    @ApiModelProperty("商品价格")
    double price;

    @ApiModelProperty("商品数量")
    int number;

    @ApiModelProperty("供应商")
    String provider;

}
