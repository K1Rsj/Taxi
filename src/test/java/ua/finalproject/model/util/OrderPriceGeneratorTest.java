package ua.finalproject.model.util;

import org.junit.Test;
import ua.finalproject.model.entities.full.CarType;

import static org.junit.Assert.*;

public class OrderPriceGeneratorTest {

    @Test
    public void getOrderPrice() {
        Long moneySpent = 125671L;
        String departureStreet = "Astrakhanskaya 20";
        String destinationStreet = "Revuckogo 5";
        CarType carType = new CarType();
        carType.setDiscount(5);
        carType.setPricePerKilometer(500);
        carType.setStartingPrice(3000);

        Long actualOrderPrice = OrderPriceGenerator.getOrderPrice
                (moneySpent,
                        departureStreet, destinationStreet, carType);
        Long orderPrice = 15300L;
        assertEquals(orderPrice, actualOrderPrice);
    }

    @Test
    public void getDiscountBasedOnMoneySpent() {

        Integer discount = 5;
        Long moneySpent = 125671L;

        Integer actualDiscount = OrderPriceGenerator
                .getDiscountBasedOnMoneySpent(moneySpent);
        assertEquals(discount, actualDiscount);

    }
}