package net.sparkzz.workwiz.util;

import net.sparkzz.workwiz.model.ShoppingItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockShoppingItems {

    private static final Random random = new Random();
    private static final String[] names = {
        "Apple",
        "Banana",
        "Orange",
        "Grape",
        "Strawberry",
        "Blueberry",
        "Raspberry",
        "Blackberry"
    };
    private static final String[] descriptions = {
        "A delicious fruit",
        "A yellow fruit",
        "A citrus fruit",
        "A small fruit",
        "A red fruit",
        "A blue fruit",
        "A red fruit",
        "A black fruit"
    };

    public static ShoppingItem generateRandomItem() {
        long id = random.nextLong();
        String name = names[random.nextInt(names.length)];
        String description = descriptions[random.nextInt(descriptions.length)];
        double price = random.nextDouble() * 100;
        ShoppingItem item = new ShoppingItem();

        item.setId(id);
        item.setName(name);
        item.setDescription(description);
        item.setPrice(BigDecimal.valueOf(price));

        return item;
    }

    public static List<ShoppingItem> generateRandomItems(int count) {
        List<ShoppingItem> items = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            items.add(generateRandomItem());
        }

        return items;
    }
}
