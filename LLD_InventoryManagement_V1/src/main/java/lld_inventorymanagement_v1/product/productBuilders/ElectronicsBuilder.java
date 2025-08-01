package lld_inventorymanagement_v1.product.productBuilders;

import lld_inventorymanagement_v1.enums.ProductCategory;
import lld_inventorymanagement_v1.product.Product;
import lld_inventorymanagement_v1.product.ProductBuilder;
import lld_inventorymanagement_v1.product.products.Electronics;

public class ElectronicsBuilder implements ProductBuilder {
    private Electronics electronics;

    public ElectronicsBuilder() {
        this.electronics = new Electronics();
    }

    @Override
    public ElectronicsBuilder setSku(String sku) {
        this.electronics.setSku(sku);
        return this;
    }

    @Override
    public ElectronicsBuilder setName(String name) {
        this.electronics.setName(name);
        return this;
    }

    @Override
    public ElectronicsBuilder setPrice(double price) {
        this.electronics.setPrice(price);
        return this;
    }

    @Override
    public ElectronicsBuilder setQuantity(int quantity) {
        this.electronics.setQuantity(quantity);
        return this;
    }

    @Override
    public ElectronicsBuilder setThreshold(int threshold) {
        this.electronics.setThreshold(threshold);
        return this;
    }

    @Override
    public ElectronicsBuilder setProductCategory(ProductCategory productCategory) {
        this.electronics.setProductCategory(productCategory);
        return this;
    }

    public ElectronicsBuilder setWarrantyInMonths(int warrantyInMonths) {
        this.electronics.setWarrantyInMonths(warrantyInMonths);
        return this;
    }

    public ElectronicsBuilder setHasInbuildBattery(boolean hasInbuildBattery) {
        this.electronics.setHasInbuildBattery(hasInbuildBattery);
        return this;
    }

    @Override
    public Electronics build() {
        return this.electronics;
    }
}
