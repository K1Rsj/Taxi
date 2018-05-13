package ua.finalproject.controller.commands;

import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.model.services.CarService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class AllCarsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        CarService carService = new CarService();
        Optional<List<Car>> allCars = carService.showAllCars();
        if (allCars.isPresent()) {
            request.setAttribute("cars", allCars.get());
            return "/WEB-INF/admin/all_cars.jsp";
        }
        request.setAttribute("message", "There are no orders");
        return "/WEB-INF/admin/all_cars.jsp";
    }
}
