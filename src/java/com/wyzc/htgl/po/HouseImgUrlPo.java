package com.wyzc.htgl.po;

import com.bn.javax.po.BasePo;

public class HouseImgUrlPo extends BasePo {
    private String imgName;
    private String imgUrl;
    private String searchMo;

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSearchMo() {
        return searchMo;
    }

    public void setSearchMo(String searchMo) {
        this.searchMo = searchMo;
    }

    @Override
    public String toString() {
        return "HouseImgUrlPo [imgName=" + imgName + ", imgUrl=" + imgUrl + ", searchMo=" + searchMo + ", getPage()="
                + getPage() + ", getRows()=" + getRows() + ", getSort()=" + getSort() + ", getOrder()=" + getOrder()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                + "]";
    }

    public HouseImgUrlPo(String imgName, String imgUrl, String searchMo) {
        super();
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        this.searchMo = searchMo;
    }

    public HouseImgUrlPo() {
        super();
        // TODO Auto-generated constructor stub
    }
}
