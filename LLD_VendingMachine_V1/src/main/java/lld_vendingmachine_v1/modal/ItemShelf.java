package lld_vendingmachine_v1.modal;

import java.util.ArrayList;
import java.util.List;

public class ItemShelf {
    private int code;
    private List<Item> items;
    private boolean isSoldOut;

    public ItemShelf(int code) {
        this.code = code;
        items = new ArrayList<>();
        this.isSoldOut = false;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        if(isSoldOut)
            setSoldOut(false); // Update the sold-out status when items are set
    }

    public boolean isSoldOut() {
        return isSoldOut;
    }

    public void setSoldOut(boolean soldOut) {
        isSoldOut = soldOut;
    }

    // Add an item to the shelf
    public void addItem(Item item) {
        items.add(item);
        if(isSoldOut)
            setSoldOut(false); // Update sold-out status after adding an item
    }

    // Remove an item from the shelf
    public void removeItem(Item item) {
        items.remove(item);
        if(items.isEmpty())
            setSoldOut(true); // Update sold-out status after removing an item
    }

    public String checkIsSoldOut() {
        return "";
    }
}
