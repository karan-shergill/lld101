package lld_inventorymanagement_v1.product.productBuilders;

import lld_inventorymanagement_v1.enums.ProductCategory;
import lld_inventorymanagement_v1.product.Product;
import lld_inventorymanagement_v1.product.ProductBuilder;
import lld_inventorymanagement_v1.product.products.Clothing;

public class ClothingBuilder implements ProductBuilder {
    private Clothing clothing;

    public ClothingBuilder() {
        this.clothing = new Clothing();
    }

    @Override
    public ClothingBuilder setSku(String sku) {
        this.clothing.setSku(sku);
        return this;
    }

    @Override
    public ClothingBuilder setName(String name) {
        this.clothing.setName(name);
        return this;
    }

    @Override
    public ClothingBuilder setPrice(double price) {
        this.clothing.setPrice(price);
        return this;
    }

    @Override
    public ClothingBuilder setQuantity(int quantity) {
        this.clothing.setQuantity(quantity);
        return this;
    }

    @Override
    public ClothingBuilder setThreshold(int threshold) {
        this.clothing.setThreshold(threshold);
        return this;
    }

    @Override
    public ClothingBuilder setProductCategory(ProductCategory productCategory) {
        this.clothing.setProductCategory(productCategory);
        return this;
    }

    public ClothingBuilder setSize(String size) {
        this.clothing.setSize(size);
        return this;
    }

    public ClothingBuilder setMaterial(String material) {
        this.clothing.setMaterial(material);
        return this;
    }

    @Override
    public Clothing build() {
        return this.clothing;
    }
}
