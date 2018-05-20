package ua.finalproject.controller.commands.car;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.messages.Messages;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.util.ControllerUtil;
import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.model.services.CarService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllCarsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        CarService carService = new CarService();
        List<Car> allCars = carService.showAllCars().get();
        if (allCars.isEmpty()) {
            request.setAttribute(RequestAttributes.MESSAGE, bundleManager.getString(Messages.THERE_ARE_NO_CARS_IN_DB));
            return JSPPages.ALL_CARS_PAGE;
        }
        request.setAttribute(RequestAttributes.CARS, ControllerUtil.getSubListForPagination(request, allCars));
        return JSPPages.ALL_CARS_PAGE;
    }
}
