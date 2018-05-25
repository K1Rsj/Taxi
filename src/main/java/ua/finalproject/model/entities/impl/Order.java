package ua.finalproject.model.entities.impl;

import ua.finalproject.model.entities.Entity;
import ua.finalproject.model.entities.EntityBuilder;

public class Order implements Entity<Integer> {

    private Integer id;
    private String departureStreet;
    private String destinationStreet;
    private Car car;
    private User user;
    private CarType type;
    private Long price;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartureStreet() {
        return departureStreet;
    }

    public void setDepartureStreet(String departureStreet) {
        this.departureStreet = departureStreet;
    }

    public String getDestinationStreet() {
        return destinationStreet;
    }

    public void setDestinationStreet(String destinationStreet) {
        this.destinationStreet = destinationStreet;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CarType getCarType() {
        return type;
    }

    public void setCarType(CarType type) {
        this.type = type;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * Order builder
     */
    public static final class OrderBuilder implements EntityBuilder<Order> {

        private Integer id;
        private String departureStreet;
        private String destinationStreet;
        private Car car;
        private User user;
        private CarType type;
        private Long price;

        public OrderBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public OrderBuilder setDepartureStreet(String departureStreet) {
            this.departureStreet = departureStreet;
            return this;
        }

        public OrderBuilder setDestinationStreet(String destinationStreet) {
            this.destinationStreet = destinationStreet;
            return this;
        }

        public OrderBuilder setCar(Car car) {
            this.car = car;
            return this;
        }

        public OrderBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public OrderBuilder setCarType(CarType type) {
            this.type = type;
            return this;
        }

        public OrderBuilder setPrice(Long price) {
            this.price = price;
            return this;
        }


        @Override
        public Order build() {
            Order order = new Order();
            order.setId(id);
            order.setDepartureStreet(departureStreet);
            order.setDestinationStreet(destinationStreet);
            order.setCar(car);
            order.setUser(user);
            order.setCarType(type);
            order.setPrice(price);
            return order;
        }
    }
}
