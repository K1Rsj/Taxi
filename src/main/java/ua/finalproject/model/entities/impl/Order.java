package ua.finalproject.model.entities.impl;

import ua.finalproject.model.entities.Entity;
import ua.finalproject.model.entities.EntityBuilder;

public class Order implements Entity<Integer> {

    private Integer id;
    private String departureStreet;
    private String destinationStreet;
    private Status status;
    private User user;
    private Car.Type type;

    public enum Status {
        PENDING, DENIED, ACCEPTED
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car.Type getType() {
        return type;
    }

    public void setType(Car.Type type) {
        this.type = type;
    }

    public static final class OrderBuilder implements EntityBuilder<Order> {

        private Integer id;
        private String departureStreet;
        private String destinationStreet;
        private Status status;
        private User user;
        private Car.Type type;

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

        public OrderBuilder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public OrderBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public OrderBuilder setCarType(Car.Type type) {
            this.type = type;
            return this;
        }


        @Override
        public Order build() {
            Order order = new Order();
            order.setId(id);
            order.setDepartureStreet(departureStreet);
            order.setDestinationStreet(destinationStreet);
            order.setStatus(status);
            order.setUser(user);
            order.setType(type);
            return order;
        }
    }
}
