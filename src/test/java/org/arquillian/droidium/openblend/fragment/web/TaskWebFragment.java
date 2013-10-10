package org.arquillian.droidium.openblend.fragment.web;

import static org.arquillian.droidium.openblend.utils.Utils.waitUtil;

import java.util.List;

import org.arquillian.droidium.openblend.drones.Browser;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.findby.ByJQuery;
import org.jboss.arquillian.graphene.findby.FindByJQuery;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class TaskWebFragment {

    @Root
    private WebElement root;
    
    @Drone
    @Browser
    private WebDriver browser;

    @FindBy(css = ".add-task.task")
    private WebElement addTask;
    
    @FindBy(id = "task-title")
    private WebElement taskTitle;
    
    @FindBy(id = "task-date")
    private WebElement taskDate;
    
    @FindBy(id = "task-desc")
    private WebElement taskDescription;
    
    @FindBy(className = "submit-btn")
    private WebElement submitButton;
    
    @FindByJQuery("div.task:nth-child(1)")
    private WebElement addedTask;
    
    @FindBy(id = "task-project-select")
    private Select dropDownSelect;
    
    @FindBy(css = "#task-tag-column .tag-select-column")
    private List<WebElement> checkBoxes;
    
    public void click() {
        addTask.click();
        Graphene.waitGui(browser).until().element(addTask).attribute("style").contains("display: none;");
    }

    public void addTitle(String title) {
        taskTitle.sendKeys(title);
    }

    public void addDate(String year, String month, String day) {
        taskDate.clear();

        taskDate.sendKeys(year);
        
        waitUtil(1000);
        
        taskDate.sendKeys(month);
        
        waitUtil(1000);
        
        taskDate.sendKeys(day);
    }

    public void addDescription(String description) {
        taskDescription.sendKeys(description);
    }

    public void addToProject(String toProject) {
        dropDownSelect.selectByValue("1");
    }    

    public void addTag(String tagName) {
        for (WebElement element : checkBoxes) {
            if (element.getText().equals(tagName)) {
                element.findElement(ByJQuery.selector("input[type=checkbox]")).click();
            }
        }
    }
    
    public void add() {
        waitUtil();
        
        submitButton.click();
        Graphene.waitGui(browser).until().element(addedTask).is().present();
        
        waitUtil();
    }

    public AddedTask getAddedTask() {
        return new AddedTask(addedTask);
    }

}
