package lld_inventorymanagement_v1.product.productBuilders;

import lld_inventorymanagement_v1.enums.ProductCategory;
import lld_inventorymanagement_v1.product.Product;
import lld_inventorymanagement_v1.product.ProductBuilder;
import lld_inventorymanagement_v1.product.products.Grocery;

import java.util.Date;

public class GroceryBuilder implements ProductBuilder {
    private Grocery grocery;

    public GroceryBuilder() {
        this.grocery = new Grocery();
    }

    @Override
    public GroceryBuilder setSku(String sku) {
        this.grocery.setSku(sku);
        return this;
    }

    @Override
    public GroceryBuilder setName(String name) {
        this.grocery.setName(name);
        return this;
    }

    @Override
    public GroceryBuilder setPrice(double price) {
        this.grocery.setPrice(price);
        return this;
    }

    @Override
    public GroceryBuilder setQuantity(int quantity) {
        this.grocery.setQuantity(quantity);
        return this;
    }

    @Override
    public GroceryBuilder setThreshold(int threshold) {
        this.grocery.setThreshold(threshold);
        return this;
    }

    @Override
    public GroceryBuilder setProductCategory(ProductCategory productCategory) {
        this.grocery.setProductCategory(productCategory);
        return this;
    }

    public GroceryBuilder setExpirationDate(Date expirationDate) {
        this.grocery.setExpirationDate(expirationDate);
        return this;
    }

    public GroceryBuilder setNeedRefrigeration(boolean needRefrigeration) {
        this.grocery.setNeedRefrigeration(needRefrigeration);
        return this;
    }

    @Override
    public Grocery build() {
        return this.grocery;
    }
}
