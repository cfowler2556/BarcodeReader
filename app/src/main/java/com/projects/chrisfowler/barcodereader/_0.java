package com.projects.chrisfowler.barcodereader;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class _0 {

    private String productname;
    private String imageurl;
    private String producturl;
    private String price;
    private String currency;
    private String saleprice;
    private String storename;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The productname
     */
    public String getProductname() {
        return productname;
    }

    /**
     *
     * @param productname
     * The productname
     */
    public void setProductname(String productname) {
        this.productname = productname;
    }

    /**
     *
     * @return
     * The imageurl
     */
    public String getImageurl() {
        return imageurl;
    }

    /**
     *
     * @param imageurl
     * The imageurl
     */
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    /**
     *
     * @return
     * The producturl
     */
    public String getProducturl() {
        return producturl;
    }

    /**
     *
     * @param producturl
     * The producturl
     */
    public void setProducturl(String producturl) {
        this.producturl = producturl;
    }

    /**
     *
     * @return
     * The price
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @param currency
     * The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     *
     * @return
     * The saleprice
     */
    public String getSaleprice() {
        return saleprice;
    }

    /**
     *
     * @param saleprice
     * The saleprice
     */
    public void setSaleprice(String saleprice) {
        this.saleprice = saleprice;
    }

    /**
     *
     * @return
     * The storename
     */
    public String getStorename() {
        return storename;
    }

    /**
     *
     * @param storename
     * The storename
     */
    public void setStorename(String storename) {
        this.storename = storename;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

