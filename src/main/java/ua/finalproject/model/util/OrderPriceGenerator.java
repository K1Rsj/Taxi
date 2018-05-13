package ua.finalproject.model.util;

import ua.finalproject.model.entities.impl.CarType;

public class OrderPriceGenerator {
    public static Long getOrderPrice(Long moneySpent, String departureStreet, String destinationStreet, CarType carType) {
        Integer distance = getOrderDistance(departureStreet, destinationStreet);
        Integer discount = getDiscountBasedOnMoneySpent(moneySpent) + carType.getDiscount();
        Integer orderPrice = (carType.getStartingPrice() + (distance * carType.getPricePerKilometer()));
        if (discount.equals(0)) {
            return (long) orderPrice;
        }
        orderPrice = orderPrice - (orderPrice / 100) * discount;
        return (long) orderPrice;
    }

    private static Integer getOrderDistance(String departureStreet, String destinationStreet) {
        return departureStreet.length() + destinationStreet.length();
    }

    public static Integer getDiscountBasedOnMoneySpent(Long moneySpent) {
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
