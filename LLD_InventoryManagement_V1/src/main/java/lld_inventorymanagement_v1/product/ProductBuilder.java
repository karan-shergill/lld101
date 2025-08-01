package lld_inventorymanagement_v1.product;

import lld_inventorymanagement_v1.enums.ProductCategory;

public interface ProductBuilder {
    ProductBuilder setSku(String sku);
    ProductBuilder setName(String name);
    ProductBuilder setPrice(double price);
    ProductBuilder setQuantity(int quantity);
    ProductBuilder setThreshold(int threshold);
    ProductBuilder setProductCategory(ProductCategory productCategory);
    Product build();
}
