package ua.finalproject.controller.commands.car_type;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.messages.Messages;
import ua.finalproject.model.entities.full.CarType;
import ua.finalproject.model.services.impl.CarTypeServiceImpl;
import ua.finalproject.util.BundleManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AllCarTypesCommandTest {

    private AllCarTypesCommand allCarTypesCommand;
    private CarTypeServiceImpl carTypeServiceImpl;
    private HttpServletRequest request;

    @Before
    public void setUp() {
        carTypeServiceImpl = mock(CarTypeServiceImpl.class);
        allCarTypesCommand = new AllCarTypesCommand(carTypeServiceImpl);
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void execute() {
        List carTypes = mock(List.class);
        when(carTypeServiceImpl.showAllCarTypes()).thenReturn(Optional
                .of(carTypes));
        String page = allCarTypesCommand.execute(request);
        verify(request).setAttribute(RequestAttributes.CAR_TYPES,
                carTypes);
        assertNotNull(page);
        assertEquals(page, JSPPages.ALL_CAR_TYPES_PAGE);
    }

    @Test
    public void executeWithEmptyList() {
        List<CarType> carTypes = new ArrayList<>();
        when(carTypeServiceImpl.showAllCarTypes()).thenReturn(Optional
                .of(carTypes));
        String page = allCarTypesCommand.execute(request);
        verify(request).setAttribute(RequestAttributes.MESSAGE,
                BundleManager.INSTANCE
                        .getString(Messages.THERE_ARE_NO_CAR_TYPES_IN_DB));
        assertNotNull(page);
        assertEquals(page, JSPPages.ALL_CAR_TYPES_PAGE);
    }
}