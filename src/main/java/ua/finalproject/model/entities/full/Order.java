package ua.finalproject.model.entities.full;

import ua.finalproject.model.entities.Entity;
import ua.finalproject.model.entities.EntityBuilder;
import ua.finalproject.model.entities.lazy.OrderLazy;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "orders")
public class Order implements Entity<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orders", nullable = false)
    private Integer id;

    @Column(name = "departure_street", nullable = false, length = 45)
    private String departureStreet;

    @Column(name = "destination_street", nullable = false, length = 45)
    private String destinationStreet;

    @ManyToOne
    @JoinColumn(name= "cars_id", referencedColumnName = "id_cars", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id_users", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_type_id", referencedColumnName = "id_car_type", nullable = false)
    private CarType type;

    @Column(name = "price", nullable = false)
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
    public static final class OrderBuilder implements
            EntityBuilder<Order> {

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

        public OrderLazy buildLazy() {
            OrderLazy orderLazy = new OrderLazy();
            orderLazy.setId(id);
            orderLazy.setDepartureStreet(departureStreet);
            orderLazy.setDestinationStreet(destinationStreet);
            orderLazy.setPrice(price);
            return orderLazy;
        }
    }
}
