package ua.finalproject.model.entities.full;

import ua.finalproject.model.entities.Entity;
import ua.finalproject.model.entities.EntityBuilder;
import ua.finalproject.model.entities.lazy.CarLazy;

public class Car implements Entity<Integer> {

    private Integer id;
    private String model;
    private String number;
    private State state;
    private String driver;
    private CarType carType;

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

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    /**
     * Car states
     */
    public enum State {
        FREE, BUSY
    }

    /**
     * Car builder
     */
    public static final class CarBuilder implements EntityBuilder<Car> {

        private Integer id;
        private String model;
        private String number;
        private State state;
        private String driver;
        private CarType carType;

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

        public CarBuilder setCarType(CarType carType) {
            this.carType = carType;
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
            car.setCarType(carType);
            return car;
        }

        public CarLazy buildLazy() {
            CarLazy carLazy = new CarLazy();
            carLazy.setId(id);
            carLazy.setModel(model);
            carLazy.setNumber(number);
            carLazy.setState(state);
            carLazy.setDriver(driver);
            return carLazy;
        }
    }
}
