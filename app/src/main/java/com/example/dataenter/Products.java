package com.example.dataenter;

public class Products {
    String pro_id;
    String pro_name;
    String Units;
    String pro_desc;
    int pro_pri;
    int stck;

    public Products() {

    }

    public Products(String pro_id, String pro_name, String units, String pro_desc, int pro_pri, int stck) {
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.Units = units;
        this.pro_desc = pro_desc;
        this.pro_pri = pro_pri;
        this.stck = stck;
    }

    public void setPro_id(String pro_id) {
        this.pro_id = pro_id;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public void setUnits(String units) {
        Units = units;
    }

    public void setPro_desc(String pro_desc) {
        this.pro_desc = pro_desc;
    }

    public void setPro_pri(int pro_pri) {
        this.pro_pri = pro_pri;
    }

    public void setStck(int stck) {
        this.stck = stck;
    }

    public String getPro_id() {
        return pro_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public String getPro_desc() {
        return pro_desc;
    }

    public int getPro_pri() {
        return pro_pri;
    }

    public int getStck() {
        return stck;
    }

    public String getUnits() {
        return Units;
    }
}
