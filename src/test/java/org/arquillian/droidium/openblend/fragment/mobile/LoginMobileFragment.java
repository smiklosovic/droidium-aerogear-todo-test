package org.arquillian.droidium.openblend.fragment.mobile;

import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginMobileFragment {

    @Root
    WebElement root;

    @FindBy(id = "username_field")
    private WebElement usernameField;

    @FindBy(id = "password_field")
    private WebElement passwordField;

    @FindBy(id = "login_button")
    private WebElement loginButton;

    public void writeUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void writePassword(String password) {
        passwordField.sendKeys(password);
    }

    public void login() {
        loginButton.click();
    }

}
