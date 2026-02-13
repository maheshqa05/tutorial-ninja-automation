package automation.validators;

import automation.pages.RegistrationPage;

public class InvalidEmailValidator implements FieldValidator {

    @Override
    public void invalidate(RegistrationPage page) {
        page.enterEmail("invalid-email");
    }

}
