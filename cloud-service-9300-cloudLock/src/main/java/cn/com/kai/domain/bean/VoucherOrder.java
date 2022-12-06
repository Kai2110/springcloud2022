package cn.com.kai.domain.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VoucherOrder {
    private long id;
    private long userId;
    private long voucherId;
    private byte payType;//支付方式：1-余额；2-支付宝；3-微信
    private byte status;//订单状态：1
}
