package model;

public class IngredientFactory {
    public static Ingredient createTomato(int quantity) {
        return new Ingredient("tomato", quantity);
    }
    public static Ingredient createProsciutto(int quantity) {
        return new Ingredient("prosciutto", quantity);
    }
    public static Ingredient createTon(int quantity) {
        return new Ingredient("ton", quantity);
    }
    public static Ingredient createSpicyPepper(int quantity) {
        return new Ingredient("spicy pepper", quantity);
    }

    public static Ingredient createSalami(int quantity) {
        return new Ingredient("salami", quantity);
    }

    public static Ingredient createMushrooms(int quantity) {
        return new Ingredient("mushrooms", quantity);
    }

    public static Ingredient createFlour(int quantity) {
        return new Ingredient("flour", quantity);
    }

    public static Ingredient createCheese(int quantity) {
        return new Ingredient("cheese", quantity);
    }


}
