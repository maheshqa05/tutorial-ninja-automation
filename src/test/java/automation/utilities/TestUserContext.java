package automation.utilities;

public class TestUserContext {

    private static String email;
    private static String password;

    public static void setUser(String email, String password) {
        TestUserContext.email = email;
        TestUserContext.password = password;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    public static void generateInvalidUser() {
        email = "invalid" + System.currentTimeMillis() + "@test.com";
        password = "wrongPass123";
    }
}

