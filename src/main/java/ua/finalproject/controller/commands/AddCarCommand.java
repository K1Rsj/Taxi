package ua.finalproject.controller.commands;

import ua.finalproject.controller.util.DataValidation;
import ua.finalproject.model.services.CarService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class AddCarCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (DataValidation.carDataValidation(request)) {
            CarService carService = new CarService();
            String model = request.getParameter("model");
            String number = request.getParameter("number");
            String driver = request.getParameter("driver");
            String type = request.getParameter("type");
            try {
                carService.addCar(model, number, driver, type);
            } catch (SQLIntegrityConstraintViolationException e) {
                request.setAttribute("informationMessage", "Car with this number is already exist");
                return "/WEB-INF/admin/add_car_page.jsp";
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("informationMessage","Add car error");
                return "/WEB-INF/admin/add_car_page.jsp";
            }
            request.setAttribute("informationMessage", "Car added successfully");
            return "/WEB-INF/admin/admin_foundation.jsp";
        }
        else {
            return "/WEB-INF/admin/add_car_page.jsp";
        }
    }
}
