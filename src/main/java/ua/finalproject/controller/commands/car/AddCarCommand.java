package ua.finalproject.controller.commands.car;

import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.util.CreateEntityFromRequest;
import ua.finalproject.controller.util.DataValidation;
import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.model.services.CarService;
import ua.finalproject.util.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class AddCarCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (DataValidation.carDataValidation(request)) {
            CarService carService = new CarService();
            Car car = CreateEntityFromRequest.getCarFromRequest(request);
            String type = request.getParameter("type");
            try {
                carService.addCar(car, type);
            } catch (SQLIntegrityConstraintViolationException e) {
                request.setAttribute("informationMessage", "Car with this number is already exist");
                logger.info(LogMessageBuilder.INSTANCE.duplicateCarNumberInfo(car.getNumber()));
                return "/WEB-INF/admin/add_car_page.jsp";
            } catch (SQLException e) {
                request.setAttribute("informationMessage","Add car error");
                logger.error("Add car error", e.getMessage());
                return "/WEB-INF/admin/add_car_page.jsp";
            }
            request.setAttribute("informationMessage", "Car added successfully");
            logger.info(LogMessageBuilder.INSTANCE.newCarInfo(car.getNumber()));
            return "/WEB-INF/admin/admin_foundation.jsp";
        }
        else {
            return "/WEB-INF/admin/add_car_page.jsp";
        }
    }
}
