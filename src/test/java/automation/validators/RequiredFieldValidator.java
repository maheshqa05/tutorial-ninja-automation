package automation.validators;

import automation.pages.RegistrationPage;

public class RequiredFieldValidator implements FieldValidator {

    private final String fieldName;

    public RequiredFieldValidator(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public void invalidate(RegistrationPage page) {
        page.clearField(fieldName);
    }

}
