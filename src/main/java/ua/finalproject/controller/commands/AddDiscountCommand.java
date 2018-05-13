package ua.finalproject.controller.commands;

import ua.finalproject.controller.util.DataValidation;
import ua.finalproject.model.services.CarTypeService;

import javax.servlet.http.HttpServletRequest;

public class AddDiscountCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String type = request.getParameter("type");
        Integer discount = Integer.parseInt(request.getParameter("discount"));
        if (DataValidation.checkDiscountAmount(discount)) {
            CarTypeService carTypeService = new CarTypeService();
            carTypeService.updateDiscount(type, discount);
            request.setAttribute("informationMessage", "Discount added successfully");
            return "/WEB-INF/admin/admin_foundation.jsp";
        } else {
            request.setAttribute("informationMessage", "Wrong amount of discount");
            return "/WEB-INF/admin/add_discount_page.jsp";
        }
    }
}
