package confluence.caloriecounter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FoodDatabase {
    private final Map<String, FoodItemInfo> foodDatabase = new HashMap<>();

    public FoodItemInfo getFoodItemInfo(String foodName) {
        return foodDatabase.get(foodName);
    }

    public void addFoodItem(String foodName, double protein, double carbs, double calories) {
        if (Objects.nonNull(foodName) && !foodName.trim().isEmpty() && protein >= 0 && carbs >= 0 && calories >= 0) {
            foodDatabase.put(foodName, new FoodItemInfo(protein, carbs, calories));
            DatabaseIO.saveToDatabase(foodName, protein, carbs, calories);
        }
    }
}
