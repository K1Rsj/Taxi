package ua.finalproject.model.util;

import ua.finalproject.model.entities.impl.Order;

public class OrderPriceGenerator {
    public static Long getOrderPrice(Long moneySpent, Order order) {
        Integer distance = getOrderDistance(order.getDepartureStreet(), order.getDestinationStreet());
        Integer discount = getDiscountBasedOnMoneySpent(moneySpent);
        return (long) (distance * discount);
    }

    private static Integer getOrderDistance(String departureStreet, String destinationStreet) {
//        departureStreet = "Tupoleva 24";
//        destinationStreet = "Sherbakova 5";
        return departureStreet.length() * destinationStreet.length();
    }

    private static Integer getDiscountBasedOnMoneySpent(Long moneySpent) {
        Integer discountRate;
        if (moneySpent <= 100000) {
            discountRate = 0;
        } else if (moneySpent <= 250000) {
            discountRate = 5;
        } else if (moneySpent <= 500000) {
            discountRate = 10;
        } else {
            discountRate = 15;
        }
        return discountRate;
    }
}
