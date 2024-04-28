import org.example.BasketSplitter;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class BasketSplitterTest {
    @Test
    public void testBasket() {
        // given
        String pathToConfig = "src/main/resources/config.json";
        BasketSplitter basketSplitter = new BasketSplitter(pathToConfig);
        LinkedList<String> items = new LinkedList<>(Arrays.asList(
                "Cocoa Butter",
                "Tart - Raisin And Pecan",
                "Table Cloth 54x72 White",
                "Flower - Daisies",
                "Fond - Chocolate",
                "Cookies - Englishbay Wht"
        ));

        // when
        Map<String, List<String>> result = basketSplitter.split(items);


        // then
        assertEquals(2, result.size());
        assertEquals(5, result.get("Courier").size());
        assertEquals(1, result.get("Pick-up point").size());
    }
    @Test
     public void testSingleProductBasket() throws IOException {
        BasketSplitter splitter = new BasketSplitter("src/main/resources/config.json");
        List<String> items = List.of("Fond - Chocolate");

        Map<String, List<String>> groups = splitter.split(items);

        assertEquals(1, groups.size());
        assertTrue(groups.containsKey("Pick-up point"));
        assertEquals(items, groups.get("Pick-up point"));
    }
    @Test
    public void testBasket2(){
        BasketSplitter splitter = new BasketSplitter("src/main/resources/config.json");
        List<String> items = List.of("Fond - Chocolate", "Chocolate - Unsweetened", "Nut - Almond, Blanched, Whole", "Haggis", "Mushroom - Porcini Frozen", "Cake - Miini Cheesecake Cherry", "Sauce - Mint", "Longan", "Bag Clear 10 Lb", "Nantucket - Pomegranate Pear", "Puree - Strawberry", "Numi - Assorted Teas", "Apples - Spartan", "Garlic - Peeled", "Cabbage - Nappa", "Bagel - Whole White Sesame", "Tea - Apple Green Tea");

        Map<String, List<String>> result = splitter.split(items);
        assertNotNull(result);
        assertFalse(result.isEmpty());

        int totalItemsInBasket = items.size();
        int totalItemsInGroups = result.values().stream().mapToInt(List::size).sum();
        assertEquals(totalItemsInBasket, totalItemsInGroups);
    }


    @Test
    public void test100productBasket(){
        String pathToConfig = "src/main/resources/config.json";
        BasketSplitter basketSplitter = new BasketSplitter(pathToConfig);
        LinkedList<String> items = new LinkedList<>(Arrays.asList(
                "Compound - Mocha",
                "Fish - Soup Base, Bouillon",
                "Pork Ham Prager",
                "Yogurt - Cherry, 175 Gr",
                "Butter - Salted, Micro",
                "Emulsifier",
                "Cheese - St. Andre",
                "Garlic - Peeled",
                "Steam Pan - Half Size Deep",
                "Appetizer - Escargot Puff",
                "Bread - Crumbs, Bulk",
                "Sugar - Cubes",
                "Brandy - Bar",
                "Juice - Ocean Spray Cranberry",
                "Wine - Fontanafredda Barolo",
                "Mix - Cocktail Ice Cream",
                "Fond - Chocolate",
                "Beer - Alexander Kieths, Pale Ale",
                "Nut - Almond, Blanched, Whole",
                "Wakami Seaweed",
                "Wine - Magnotta - Cab Sauv",
                "Capers - Ox Eye Daisy",
                "Spinach - Frozen",
                "Puree - Guava",
                "Flavouring - Rum",
                "Cookies - Englishbay Wht",
                "Oil - Olive, Extra Virgin",
                "Pepper - Julienne, Frozen",
                "Beef Cheek Fresh",
                "Chickhen - Chicken Phyllo",
                "Ecolab - Medallion",
                "Nantucket - Pomegranate Pear",
                "Cookie - Oreo 100x2",
                "Shrimp - 21/25, Peel And Deviened",
                "Dc Hikiage Hira Huba",
                "Cheese Cloth",
                "Tea - Apple Green Tea",
                "Apples - Spartan",
                "Lamb - Whole, Fresh",
                "English Muffin",
                "Cabbage - Nappa",
                "Cake - Miini Cheesecake Cherry",
                "Puree - Strawberry",
                "Onions - White",
                "Longos - Chicken Curried",
                "Tart - Raisin And Pecan",
                "Energy Drink - Redbull 355ml",
                "Numi - Assorted Teas",
                "Cake Circle, Paprus",
                "Gatorade - Lemon Lime",
                "Oxtail - Cut",
                "Salt - Rock, Course",
                "Dried Peach",
                "Syrup - Monin - Blue Curacao",
                "Bread - Flat Bread",
                "Crush - Orange, 355ml",
                "Carbonated Water - Raspberry",
                "Cloves - Ground",
                "Sauce - Salsa",
                "Pork Salted Bellies",
                "Ocean Spray - Ruby Red",
                "Corn Syrup",
                "Wine - Champagne Brut Veuve",
                "Beans - Green",
                "V8 Splash Strawberry Banana",
                "Peach - Fresh",
                "Chocolate - Unsweetened",
                "Cheese - Sheep Milk",
                "Garbage Bags - Clear",
                "Wine - Sherry Dry Sack, William",
                "Cocoa Butter",
                "Fudge - Chocolate Fudge",
                "Table Cloth 54x72 White",
                "The Pop Shoppe - Grape",
                "Mushroom - Porcini Frozen",
                "Otomegusa Dashi Konbu",
                "Sauce - Mint",
                "Pork - Hock And Feet Attached",
                "Haggis",
                "Juice - Apple, 1.36l",
                "Sole - Dover, Whole, Fresh",
                "Longan",
                "Bag Clear 10 Lb",
                "Pepper - Green, Chili",
                "Wine - Port Late Bottled Vintage",
                "Flour - Buckwheat, Dark",
                "Pineapple - Canned, Rings",
                "Vinegar - Red Wine",
                "Cheese - Mix",
                "Flower - Daisies",
                "Nantucket Apple Juice",
                "Bread - Petit Baguette",
                "Pasta - Fusili Tri - Coloured",
                "Mustard - Dry, Powder",
                "Beer - Muskoka Cream Ale",
                "Cookies Oatmeal Raisin",
                "Bagel - Whole White Sesame",
                "Pepper - Red, Finger Hot",
                "Soup - Campbells Mac N Cheese",
                "Gingerale - Diet - Schweppes"
        ));

        Map<String, List<String>> result = basketSplitter.split(items);
        assertEquals(7, result.size());
        assertEquals(65, result.get("In-store pick-up").size());
        assertEquals(17, result.get("Parcel locker").size());
    }
    @Test
    public void jsonLoaderTest() throws Exception {
        String pathToJson = "src/main/resources/config.json";
        BasketSplitter splitter = new BasketSplitter(pathToJson);
        splitter.loadConfigFromJson(pathToJson);
        Map<String, List<String>> output = splitter.getDeliveryTypes();

        String configJson = new String(Files.readAllBytes(Paths.get(pathToJson)));
        JSONParser parser = new JSONParser();
        JSONObject configJsonObject = (JSONObject) parser.parse(configJson);
        JSONObject outputJsonObject = new JSONObject(output);
        assertEquals(configJsonObject.toJSONString(), outputJsonObject.toJSONString());
    }

}
