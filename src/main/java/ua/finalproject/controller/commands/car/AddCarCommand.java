package ua.finalproject.controller.commands.car;

import org.hibernate.exception.ConstraintViolationException;
import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.jsp.RequestParameters;
import ua.finalproject.constants.messages.ExceptionMessages;
import ua.finalproject.constants.messages.Messages;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.util.CreateEntityFromRequest;
import ua.finalproject.controller.util.DataValidation;
import ua.finalproject.model.entities.full.Car;
import ua.finalproject.model.services.impl.CarServiceImpl;
import ua.finalproject.util.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;

/**
 * Command for adding car to taxi DB
 */
public class AddCarCommand implements Command {

    private CarServiceImpl carServiceImpl;

    public AddCarCommand(CarServiceImpl carServiceImpl) {
        this.carServiceImpl = carServiceImpl;
    }

    /**
     * @param request request from user
     * @return path to admin foundation page if validation was successful
     * or else return path to add car page
     */
    @Override
    public String execute(HttpServletRequest request) {
        if (DataValidation.carDataValidation(request)) {
            Car car = CreateEntityFromRequest.getCarFromRequest(request);
            String type = request.getParameter(RequestParameters.TYPE);
            try {
                carServiceImpl.addCar(car, type);
            } catch (ConstraintViolationException e) {
                request.setAttribute(RequestAttributes
                        .INFORMATION_MESSAGE, bundleManager
                        .getString(ExceptionMessages
                                .CAR_WITH_THIS_NUMBER_ALREADY_EXISTS));
                logger.info(LogMessageBuilder.INSTANCE
                        .duplicateCarNumberInfo(car.getNumber
                                ()));
                return JSPPages.ADD_CAR_PAGE;
            }
            request.setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                    bundleManager
                            .getString(Messages.CAR_SUCCESSFULLY_ADDED));
            logger.info(LogMessageBuilder.INSTANCE.newCarInfo(car
                    .getNumber()));
            return JSPPages.ADMIN_FOUNDATION_PAGE;
        } else {
            return JSPPages.ADD_CAR_PAGE;
        }
    }
}
