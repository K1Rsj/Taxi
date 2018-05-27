package ua.finalproject.model.entities.full;

import ua.finalproject.model.entities.Entity;
import ua.finalproject.model.entities.EntityBuilder;

public class CarType implements Entity<Integer> {

    private Integer id;
    private String type;
    private Integer startingPrice;
    private Integer pricePerKilometer;
    private Integer discount;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Integer startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Integer getPricePerKilometer() {
        return pricePerKilometer;
    }

    public void setPricePerKilometer(Integer pricePerKilometer) {
        this.pricePerKilometer = pricePerKilometer;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     * Car type builder
     */
    public static final class CarTypeBuilder implements EntityBuilder<CarType> {

        private Integer id;
        private String type;
        private Integer startingPrice;
        private Integer pricePerKilometer;
        private Integer discount;

        public CarTypeBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public CarTypeBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public CarTypeBuilder setStartingPrice(Integer startingPrice) {
            this.startingPrice = startingPrice;
            return this;
        }

        public CarTypeBuilder setPricePerKilometer(Integer pricePerKilometer) {
            this.pricePerKilometer = pricePerKilometer;
            return this;
        }

        public CarTypeBuilder setDiscount(Integer discount) {
            this.discount = discount;
            return this;
        }

        @Override
        public CarType build() {
            CarType carType = new CarType();
            carType.setId(id);
            carType.setType(type);
            carType.setStartingPrice(startingPrice);
            carType.setPricePerKilometer(pricePerKilometer);
            carType.setDiscount(discount);
            return carType;
        }
    }
}
