package ua.finalproject.model.entities.full;

import ua.finalproject.model.entities.Entity;
import ua.finalproject.model.entities.EntityBuilder;
import ua.finalproject.model.entities.lazy.CarLazy;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "cars")
public class Car implements Entity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cars", nullable = false)
    private Integer id;

    @Column(name = "model", nullable = false, length = 45)
    private String model;

    @Column(name = "number", nullable = false, length = 45, unique = true)
    private String number;

    @Column(name = "state", nullable = false, insertable = false, length = 45)
    private State state;

    @Column(name = "driver", nullable = false, length = 45)
    private String driver;

    @ManyToOne
    @JoinColumn(name = "car_type_id", referencedColumnName = "id_car_type", nullable = false)
    private CarType carType;


    public Integer getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getNumber() {
        return number;
    }

    public Car.State getState() {
//        if (state != null) {
//            return state;
//        } else {
//            return State.FREE;
//        }
        return state;
    }

    public String getDriver() {
        return driver;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setDriver(String driver) {
        this.driver = driver;
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
