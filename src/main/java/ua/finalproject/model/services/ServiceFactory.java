package ua.finalproject.model.services;

import ua.finalproject.model.services.impl.CarServiceImpl;
import ua.finalproject.model.services.impl.CarTypeServiceImpl;
import ua.finalproject.model.services.impl.OrderServiceImpl;
import ua.finalproject.model.services.impl.UserServiceImpl;

public class ServiceFactory {

    private ServiceFactory() {
    }

    private static class ServiceFactoryHolder {
        private static ServiceFactory instance = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return ServiceFactoryHolder.instance;
    }


    public UserServiceImpl createUserService(){
        return UserServiceImpl.getInstance();
    }

    public OrderServiceImpl createOrderService(){
        return OrderServiceImpl.getInstance();
    }

    public CarServiceImpl createCarService(){
        return CarServiceImpl.getInstance();
    }

    public CarTypeServiceImpl createCarTypeService(){
        return CarTypeServiceImpl.getInstance();
    }
}
