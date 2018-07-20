package ua.finalproject.model.entities.full;

import ua.finalproject.model.entities.Entity;
import ua.finalproject.model.entities.EntityBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "car_type")
public class CarType implements Entity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car_type", nullable = false)
    private Integer id;

    @Column(name = "type", nullable = false, length = 45)
    private String type;

    @Column(name = "starting_price", nullable = false)
    private Integer startingPrice;

    @Column(name = "price_per_km", nullable = false)
    private Integer pricePerKilometer;

    @Column(name = "discount", nullable = false)
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
    public static final class CarTypeBuilder implements
            EntityBuilder<CarType> {

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
