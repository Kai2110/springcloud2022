package cn.com.kai.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * ClassName:Order
 * Package:cn.com.kai.domain
 * Description:
 *
 * @Author:gkr
 * @Date:2022-01-11 21:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private long id;
    private long userId;
    private long productId;
    private int count;
    private BigDecimal money;
    /** * 订单状态：0：创建中；1：已完结 */
    private int status;
}
