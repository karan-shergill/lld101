package lld_inventorymanagement_v1.product.products;

import lld_inventorymanagement_v1.product.Product;

public class Electronics extends Product {
    private int warrantyInMonths;
    private boolean hasInbuildBattery;

    public int getWarrantyInMonths() {
        return warrantyInMonths;
    }

    public void setWarrantyInMonths(int warrantyInMonths) {
        this.warrantyInMonths = warrantyInMonths;
    }

    public boolean isHasInbuildBattery() {
        return hasInbuildBattery;
    }

    public void setHasInbuildBattery(boolean hasInbuildBattery) {
        this.hasInbuildBattery = hasInbuildBattery;
    }
}
