package main.com.sun.h5weixin.common.model;

/**
 * Created by SUN on 2017/2/27.
 */
public class Refresh {
    private int success;
    private String mobile;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

}
