package ua.finalproject.model.util;

import ua.finalproject.model.entities.full.CarType;

/**
 * Generates price for order
 */
public class OrderPriceGenerator {

    /**
     * Calculates order price
     *
     * @param moneySpent        money spent by user
     * @param departureStreet   departure street
     * @param destinationStreet destination street
     * @param carType           car type
     * @return order price
     */
    public static Long getOrderPrice(Long moneySpent, String
            departureStreet, String
                                             destinationStreet, CarType
            carType) {
        Integer distance = getOrderDistance(departureStreet,
                destinationStreet);
        Integer discount = getDiscountBasedOnMoneySpent(moneySpent) +
                carType.getDiscount();
        Integer orderPrice = (carType.getStartingPrice() + (distance *
                carType
                        .getPricePerKilometer()));
        if (discount.equals(0)) {
            return (long) orderPrice;
        }
        orderPrice = orderPrice - (orderPrice / 100) * discount;
        return (long) orderPrice;
    }

    /**
     * Calculate disctance
     *
     * @param departureStreet   departure street
     * @param destinationStreet destination street
     * @return trip distance
     */
    private static Integer getOrderDistance(String departureStreet,
                                            String destinationStreet) {
        return departureStreet.length() + destinationStreet.length();
    }

    /**
     * Choses discount depending on money spent by user
     *
     * @param moneySpent money spent by user
     * @return user discount
     */
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
