package main.com.sun.h5weixin.user.model;

/**
 * Created by SUN on 2017/2/23.
 */
public class User {
    private Integer id;
    private String mobile;
    private String certificationStatus;
    private String pMobile;
    private String awardStatus;
    private Integer inviteNumber;
    private String award;
    private String province;
    private String city;
    private String awardTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCertificationStatus() {
        return certificationStatus;
    }

    public void setCertificationStatus(String certificationStatus) {
        certificationStatus = certificationStatus;
    }

    public String getpMobile() {
        return pMobile;
    }

    public void setpMobile(String pMobile) {
        this.pMobile = pMobile;
    }

    public String getAwardStatus() {
        return awardStatus;
    }

    public void setAwardStatus(String awardStatus) {
        this.awardStatus = awardStatus;
    }

    public Integer getInviteNumber() {
        return inviteNumber;
    }

    public void setInviteNumber(Integer inviteNumber) {
        this.inviteNumber = inviteNumber;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAwardTotal() {
        return awardTotal;
    }

    public void setAwardTotal(String awardTotal) {
        this.awardTotal = awardTotal;
    }
}
