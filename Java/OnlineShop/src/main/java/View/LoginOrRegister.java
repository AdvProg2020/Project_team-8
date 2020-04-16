package View;

import Controller.LoginOrRegisterManaging;

public class LoginOrRegister extends Menu{


    public LoginOrRegister(Menu parentMenu) {
        super("", parentMenu);
    }

    private Menu RegisterNewPerson() {
        LoginOrRegisterManaging.register();
        return null;
    }

    private Menu Login() {

        LoginOrRegisterManaging.login();
        return null;
    }

}
