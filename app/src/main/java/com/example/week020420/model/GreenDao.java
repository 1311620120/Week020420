package com.example.week020420.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/20 11:40:03
 * @Description:
 */
@Entity
public class GreenDao {

    private int commodityId;
    private String commodityName;
    private String masterPic;
    private int price;
    private int saleNum;
    @Generated(hash = 199559799)
    public GreenDao(int commodityId, String commodityName, String masterPic,
            int price, int saleNum) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.masterPic = masterPic;
        this.price = price;
        this.saleNum = saleNum;
    }
    @Generated(hash = 766040118)
    public GreenDao() {
    }
    public int getCommodityId() {
        return this.commodityId;
    }
    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }
    public String getCommodityName() {
        return this.commodityName;
    }
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public String getMasterPic() {
        return this.masterPic;
    }
    public void setMasterPic(String masterPic) {
        this.masterPic = masterPic;
    }
    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getSaleNum() {
        return this.saleNum;
    }
    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }
}
