package ua.finalproject.util;

import ua.finalproject.constants.GlobalConstants;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class MoneyFormatterTag extends SimpleTagSupport {

    private Long money;

    public MoneyFormatterTag() {

    }

    public void setMoney(Long money) {
        this.money = money;
    }

    @Override
    public void doTag() throws IOException {
        Double result;
        result = (double) money / 100;
        getJspContext().getOut().write(String.valueOf(result).replace(GlobalConstants.POINT_ZERO, GlobalConstants.EMPTY_STRING));
    }
}
