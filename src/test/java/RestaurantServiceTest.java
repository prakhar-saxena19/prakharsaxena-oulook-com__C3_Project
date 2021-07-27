import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    LocalTime openingTime;
    LocalTime closingTime;

//    @BeforeEach
//    public void setup(){
//        openingTime = LocalTime.parse("10:30:00");
//        closingTime = LocalTime.parse("22:00:00");
//        addDefaultRestaurant();
//    }


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
//        Restaurant expectedRestaurant = new Restaurant("Amelie's cafe", "Bareilly", openingTime, closingTime);
        setDefaultTime("10:30:00", "22:00:00");
        addDefaultRestaurant(openingTime, closingTime);
        assertEquals(service.findRestaurantByName("Amelie's cafe").getName(),
                restaurant.getName());
    }



    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        setDefaultTime("10:30:00", "22:00:00");
        addDefaultRestaurant(openingTime, closingTime);
        assertThrows(restaurantNotFoundException.class,
                ()->service.findRestaurantByName("Prakhar's cafe"));
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        setDefaultTime("10:30:00", "22:00:00");
        addDefaultRestaurant(openingTime, closingTime);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        setDefaultTime("10:30:00", "22:00:00");
        addDefaultRestaurant(openingTime, closingTime);
        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        setDefaultTime("10:30:00", "22:00:00");
        addDefaultRestaurant(openingTime, closingTime);
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>

    //added by Prakhar for Refactoring
    private void addDefaultRestaurant(LocalTime openingTime, LocalTime closingTime) {
        restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    private void setDefaultTime(String openingTimeStr, String closingTimeStr) {
        openingTime = LocalTime.parse(openingTimeStr);
        closingTime = LocalTime.parse(closingTimeStr);
    }
}