package ua.finalproject.controller.commands.car;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.messages.Messages;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.util.ControllerUtil;
import ua.finalproject.model.entities.full.Car;
import ua.finalproject.model.services.impl.CarServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Command for getting all cars from DB
 */
public class AllCarsCommand implements Command {

    private CarServiceImpl carServiceImpl;

    public AllCarsCommand(CarServiceImpl carServiceImpl) {
        this.carServiceImpl = carServiceImpl;
    }

    /**
     * @param request request from user
     * @return path to all cars page
     */
    @Override
    public String execute(HttpServletRequest request) {
        List<Car> allCars = carServiceImpl.showAllCars().get();
        if (allCars.isEmpty()) {
            request.setAttribute(RequestAttributes.MESSAGE,
                    bundleManager.getString(Messages
                            .THERE_ARE_NO_CARS_IN_DB));
            return JSPPages.ALL_CARS_PAGE;
        }
        request.setAttribute(RequestAttributes.CARS, ControllerUtil
                .getSubListForPagination
                        (request, allCars));
        return JSPPages.ALL_CARS_PAGE;
    }
}
