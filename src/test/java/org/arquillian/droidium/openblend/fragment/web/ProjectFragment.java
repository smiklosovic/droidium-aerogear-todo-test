package org.arquillian.droidium.openblend.fragment.web;

import org.arquillian.droidium.openblend.drones.Browser;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.arquillian.droidium.openblend.utils.Utils.*;

public class ProjectFragment {

    @Root
    private WebElement root;
    
    @Browser
    @Drone
    private WebDriver browser;
    
    @FindBy(className = "add-project")
    private WebElement addProject;
    
    @FindBy(id = "project-title")
    private WebElement projectTitle;

    @FindBy(className = "add-project-form")
    private WebElement addProjectForm;
    
    @FindBy(className = "submit-btn")
    private WebElement addProjectButton;
    
    @FindBy(css = "#project-container .project.project-255-255-255")
    private WebElement addedProject;
    
    public void click() {
        addProject.click();
        Graphene.waitGui(browser).until().element(addProject).attribute("style").contains("display: none;");
    }

    public void addProject(String projectTitle) {
        this.projectTitle.sendKeys(projectTitle);
        addProjectButton.click();
        Graphene.waitGui(browser).until().element(addedProject).is().present();
        waitUtil();
    }

    public WebElement getAddedProject() {
        return addedProject;
    }
    
}
