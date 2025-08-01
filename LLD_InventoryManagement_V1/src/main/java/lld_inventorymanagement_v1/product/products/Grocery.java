package lld_inventorymanagement_v1.product.products;

import lld_inventorymanagement_v1.product.Product;

import java.util.Date;

public class Grocery extends Product {
    private Date expirationDate;
    private boolean needRefrigeration;

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isNeedRefrigeration() {
        return needRefrigeration;
    }

    public void setNeedRefrigeration(boolean needRefrigeration) {
        this.needRefrigeration = needRefrigeration;
    }
}
