package lld_inventorymanagement_v1.product;

import lld_inventorymanagement_v1.enums.ProductCategory;
import lld_inventorymanagement_v1.product.productBuilders.ClothingBuilder;
import lld_inventorymanagement_v1.product.productBuilders.ElectronicsBuilder;
import lld_inventorymanagement_v1.product.productBuilders.GroceryBuilder;
import lld_inventorymanagement_v1.product.products.Clothing;
import lld_inventorymanagement_v1.product.products.Electronics;
import lld_inventorymanagement_v1.product.products.Grocery;

import java.util.Date;

public class ProductDirector {
    public Grocery createGroceryProduct(String sku, String name, double price, int quantity, int threshold,
                                        ProductCategory productCategory, Date expirationDate, boolean needRefrigeration) {
        return new GroceryBuilder()
                .setSku(sku)
                .setName(name)
                .setPrice(price)
                .setQuantity(quantity)
                .setThreshold(threshold)
                .setProductCategory(productCategory)
                .setExpirationDate(expirationDate)
                .setNeedRefrigeration(needRefrigeration)
                .build();

    }

    public Electronics createElectronicsProduct(String sku, String name, double price, int quantity, int threshold,
                                                ProductCategory productCategory, int warrantyInMonths, boolean hasInbuildBattery) {
        return new ElectronicsBuilder()
                .setSku(sku)
                .setName(name)
                .setPrice(price)
                .setQuantity(quantity)
                .setThreshold(threshold)
                .setProductCategory(productCategory)
                .setWarrantyInMonths(warrantyInMonths)
                .setHasInbuildBattery(hasInbuildBattery)
                .build();
    }

    public Clothing createClothingProduct(String sku, String name, double price, int quantity, int threshold,
                                          ProductCategory productCategory, String size, String material) {
        return new ClothingBuilder()
                .setSku(sku)
                .setName(name)
                .setPrice(price)
                .setQuantity(quantity)
                .setThreshold(threshold)
                .setProductCategory(productCategory)
                .setSize(size)
                .setMaterial(material)
                .build();
    }
}
