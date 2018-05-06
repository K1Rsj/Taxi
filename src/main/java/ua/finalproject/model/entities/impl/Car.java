package ua.finalproject.model.entities.impl;

import ua.finalproject.model.entities.Entity;
import ua.finalproject.model.entities.EntityBuilder;

public class Car implements Entity<Integer> {

    private Integer id;
    private String model;
    private String number;
    private State state;
    private String driver;
    private Type type;


    public enum State {
        FREE, BUSY
    }

    public enum Type {
        STANDARD, COMFORT, MINIBUS, PREMIUM
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public static final class CarBuilder implements EntityBuilder<Car> {

        private Integer id;
        private String model;
        private String number;
        private State state;
        private String driver;
        private Type type;

        public CarBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public CarBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder setNumber(String number) {
            this.number = number;
            return this;
        }

        public CarBuilder setState(State state) {
            this.state = state;
            return this;
        }

        public CarBuilder setDriver(String driver) {
            this.driver = driver;
            return this;
        }

        public CarBuilder setType(Type type) {
            this.type = type;
            return this;
        }


        @Override
        public Car build() {
            Car car = new Car();
            car.setId(id);
            car.setModel(model);
            car.setNumber(number);
            car.setState(state);
            car.setDriver(driver);
            car.setType(type);
            return car;
        }
    }
}
