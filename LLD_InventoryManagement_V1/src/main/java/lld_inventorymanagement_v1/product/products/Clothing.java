package lld_inventorymanagement_v1.product.products;

import lld_inventorymanagement_v1.product.Product;

public class Clothing extends Product {
    private String size;
    private String material;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
