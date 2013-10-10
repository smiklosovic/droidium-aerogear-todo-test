package org.arquillian.droidium.openblend.fragment.web;

import org.arquillian.droidium.openblend.drones.Browser;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginWebFragment {

    @Root
    private WebElement root;
    
    @Drone
    @Browser
    private WebDriver browser;
    
    @FindBy(id = "login-username")
    private WebElement usernameField;
    
    @FindBy(id = "login-password")
    private WebElement passwordField;
    
    @FindBy(id = "login-submit")
    private WebElement loginButton;
    
    public void writeUsername(String username) {
        usernameField.sendKeys(username);
    }
    
    public void writePassword(String password) {
        passwordField.sendKeys(password);
    }
    
    public void submitForm() {
        loginButton.click();
        Graphene.waitGui(browser).until().element(root).is().not().visible();
        try { // for presentation purposes, so people have some time to figure out what is happening
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
