package whu.edu.assignment6.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

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
public class Goods_item implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type =IdType.ASSIGN_ID )
    private Long id;

    private String name;

    private String type;

    private Integer number;

    private Double price;

    @TableField(exist = false)
    private List<Supplier> suppliers;
}
