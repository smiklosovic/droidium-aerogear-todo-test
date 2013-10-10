package org.arquillian.droidium.openblend.fragment.mobile;

import static org.arquillian.droidium.openblend.utils.Utils.*;

import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TaskMobileFragment {

    @Root
    private WebElement root;

    @FindBy(id = "name")
    private WebElement mobileName;

    @FindBy(id = "date")
    private WebElement mobileDate;

    @FindBy(id = "description")
    private WebElement mobileDescription;

    @FindBy(id = "buttonSave")
    private WebElement mobileSaveButton;

    @FindBy(id = "add")
    private WebElement mobileAddButton;

    public void addTask() {
        mobileAddButton.click();
        waitUtil();
    }

    public void addName(String taskName) {
        mobileName.sendKeys(taskName);
    }

    public void addDate(String date) {
        mobileDate.sendKeys(date);
    }

    public void addDescription(String description) {
        mobileDescription.sendKeys(description);
    }

    public void submitTask() {
        mobileSaveButton.click();
    }
}
