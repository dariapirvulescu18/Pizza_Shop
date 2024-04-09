package model;

public class ingredient_factory {
    public static ingredient createTomato(int quantity) {
        return new ingredient("tomato", quantity);
    }
    public static ingredient createProsciutto(int quantity) {
        return new ingredient("prosciutto", quantity);
    }
    public static ingredient createTon(int quantity) {
        return new ingredient("ton", quantity);
    }
    public static ingredient createSpicyPepper(int quantity) {
        return new ingredient("spicy pepper", quantity);
    }

    public static ingredient createSalami(int quantity) {
        return new ingredient("salami", quantity);
    }

    public static ingredient createMushrooms(int quantity) {
        return new ingredient("mushrooms", quantity);
    }

    public static ingredient createFlour(int quantity) {
        return new ingredient("flour", quantity);
    }

    public static ingredient createCheese(int quantity) {
        return new ingredient("cheese", quantity);
    }


}
