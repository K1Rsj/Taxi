package ua.finalproject.controller.commands.car_type;

import ua.finalproject.controller.commands.Command;
import ua.finalproject.model.entities.impl.CarType;
import ua.finalproject.model.services.CarTypeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class AllCarTypesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        CarTypeService carTypeService = new CarTypeService();
        Optional<List<CarType>> carTypes = carTypeService.showAllCarTypes();
        if (carTypes.isPresent()) {
            request.setAttribute("carTypes", carTypes.get());
            return "/WEB-INF/user/all_car_types.jsp";
        }
        request.setAttribute("message", "There are no car types");
        return "/WEB-INF/user/all_car_types.jsp";
    }
}
