package ua.finalproject.controller.commands;

import ua.finalproject.model.services.CarTypeService;

import javax.servlet.http.HttpServletRequest;

public class AddDiscountCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        CarTypeService carTypeService = new CarTypeService();
        String type = request.getParameter("type");
        Integer discount = Integer.parseInt(request.getParameter("discount"));
        carTypeService.updateDiscount(type, discount);
        request.setAttribute("discountInformation", "Discount added successfully");
        return "/WEB-INF/admin/admin_foundation.jsp";
    }
}
