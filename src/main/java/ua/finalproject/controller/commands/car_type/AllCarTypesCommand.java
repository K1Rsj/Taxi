package ua.finalproject.controller.commands.car_type;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.messages.Messages;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.model.entities.impl.CarType;
import ua.finalproject.model.services.CarTypeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Command for getting all car types from taxi DB
 */
public class AllCarTypesCommand implements Command {

    private CarTypeService carTypeService;

    public AllCarTypesCommand(CarTypeService carTypeService) {
        this.carTypeService = carTypeService;
    }

    /**
     *
     * @param request request from user
     * @return path to all car types page
     */
    @Override
    public String execute(HttpServletRequest request) {
        List<CarType> carTypes = carTypeService.showAllCarTypes().get();
        if (carTypes.isEmpty()) {
            request.setAttribute(RequestAttributes.MESSAGE, bundleManager.getString(Messages.THERE_ARE_NO_CAR_TYPES_IN_DB));
            return JSPPages.ALL_CAR_TYPES_PAGE;
        }
        request.setAttribute(RequestAttributes.CAR_TYPES, carTypes);
        return JSPPages.ALL_CAR_TYPES_PAGE;
    }
}
