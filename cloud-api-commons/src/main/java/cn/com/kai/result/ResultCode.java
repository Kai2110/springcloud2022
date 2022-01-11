package cn.com.kai.result;

/**
 * ClassName:ResultCode
 * Package:cn.com.kai.result
 * Description:
 *
 * @Author:gkr
 * @Date:2022-01-11 13:16
 */
public enum  ResultCode {
    SUCCESS(200,"成功"),
    FAIL(500,"失败");


    private int code;

    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
