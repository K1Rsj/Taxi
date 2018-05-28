package ua.finalproject.controller.commands.car_type;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.messages.Messages;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.model.entities.full.CarType;
import ua.finalproject.model.services.impl.CarTypeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Command for getting all car types from taxi DB
 */
public class AllCarTypesCommand implements Command {

    private CarTypeServiceImpl carTypeServiceImpl;

    public AllCarTypesCommand(CarTypeServiceImpl carTypeServiceImpl) {
        this.carTypeServiceImpl = carTypeServiceImpl;
    }

    /**
     * @param request request from user
     * @return path to all car types page
     */
    @Override
    public String execute(HttpServletRequest request) {
        List<CarType> carTypes = carTypeServiceImpl.showAllCarTypes().get();
        if (carTypes.isEmpty()) {
            request.setAttribute(RequestAttributes.MESSAGE, bundleManager.getString(Messages
                    .THERE_ARE_NO_CAR_TYPES_IN_DB));
            return JSPPages.ALL_CAR_TYPES_PAGE;
        }
        request.setAttribute(RequestAttributes.CAR_TYPES, carTypes);
        return JSPPages.ALL_CAR_TYPES_PAGE;
    }
}
