package cn.com.kai.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName:CommonResult
 * Package:cn.com.kai.common
 * Description:
 *
 * @Author:gkr
 * @Date:2022-01-10 16:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private int code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
