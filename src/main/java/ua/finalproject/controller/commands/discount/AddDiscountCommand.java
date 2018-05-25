package ua.finalproject.controller.commands.discount;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.jsp.RequestParameters;
import ua.finalproject.constants.messages.ExceptionMessages;
import ua.finalproject.constants.messages.Messages;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.util.DataValidation;
import ua.finalproject.model.services.CarTypeService;
import ua.finalproject.util.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;

/**
 * Command for adding discount for car type
 */
public class AddDiscountCommand implements Command {

    private CarTypeService carTypeService;

    public AddDiscountCommand(CarTypeService carTypeService) {
        this.carTypeService = carTypeService;
    }

    /**
     *
     * @param request request from user
     * @return path to admin foundation page if validation was successful
     * or else return path to add discount page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String type = request.getParameter(RequestParameters.TYPE);
        Integer discount = Integer.parseInt(request.getParameter(RequestParameters.DISCOUNT));
        if (DataValidation.checkDiscountAmount(discount)) {
            carTypeService.updateDiscount(type, discount);
            request.setAttribute(RequestAttributes.INFORMATION_MESSAGE, bundleManager.getString(Messages.DISCOUNT_SUCCESSFULLY_ADDED));
            logger.info(LogMessageBuilder.INSTANCE.newDiscountInfo(type, discount));
            return JSPPages.ADMIN_FOUNDATION_PAGE;
        } else {
            request.setAttribute(RequestAttributes.INFORMATION_MESSAGE, bundleManager.getString(ExceptionMessages.WRONG_DISCOUNT_AMOUNT));
            return JSPPages.ADD_DISCOUNT_PAGE;
        }
    }
}
