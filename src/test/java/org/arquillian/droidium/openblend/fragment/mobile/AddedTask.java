package org.arquillian.droidium.openblend.fragment.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AddedTask {

    private WebElement addedTask;

    public AddedTask(WebElement addedTask) {
        this.addedTask = addedTask;
    }

    public String getDate() {
        return addedTask.findElement(By.cssSelector(".task-upper .task-date")).getText();
    }

    public String getTitle() {
        return addedTask.findElement(By.cssSelector(".task-upper .task-title")).getText();
    }

    public String getDescription() {
        return addedTask.findElement(By.cssSelector(".task-desc")).getText();
    }

}
