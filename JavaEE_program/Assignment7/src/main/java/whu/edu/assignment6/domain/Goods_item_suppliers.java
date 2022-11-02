package whu.edu.assignment6.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lzy
 * @since 2022-10-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Goods_item_suppliers implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("goods_item_id")
    private Long goodsItemId;

    @TableField("suppliers_id")
    private Long suppliersId;


}
