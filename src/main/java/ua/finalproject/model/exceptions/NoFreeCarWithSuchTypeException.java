package ua.finalproject.model.exceptions;

import ua.finalproject.constants.messages.ExceptionMessages;
import ua.finalproject.util.BundleManager;

/**
 * Throw this exception when there is no free car of some type
 */
public class NoFreeCarWithSuchTypeException extends Exception {

    @Override
    public String getMessage() {
        return BundleManager.INSTANCE.getString(ExceptionMessages.AT_THE_MOMENT_THERE_IS_NO_CAR_OF_THIS_TYPE);
    }

}
