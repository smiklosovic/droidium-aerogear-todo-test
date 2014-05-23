/**
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.arquillian.droidium.jeeconf;

import static org.arquillian.droidium.jeeconf.utils.Utils.openWebPageUrl;

import java.io.File;
import java.net.URL;

import org.arquillian.droidium.container.api.AndroidDevice;
import org.arquillian.droidium.jeeconf.drones.Browser;
import org.arquillian.droidium.jeeconf.drones.Mobile;
import org.arquillian.droidium.jeeconf.fragment.mobile.LoginMobilePage;
import org.arquillian.droidium.jeeconf.fragment.mobile.TaskMobilePage;
import org.arquillian.droidium.jeeconf.fragment.web.LoginWebFragment;
import org.arquillian.droidium.jeeconf.fragment.web.ProjectFragment;
import org.arquillian.droidium.jeeconf.fragment.web.TaskWebFragment;
import org.arquillian.droidium.native_.api.Instrumentable;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@RunWith(Arquillian.class)
@RunAsClient
public class AeroGearTestCase {

    @Drone
    @Browser
    WebDriver browser;

    @Drone
    @Mobile
    WebDriver mobile;

    @Deployment(name = "todo-mobile-app")
    @Instrumentable(viaPort = 8081)
    @TargetsContainer("android")
    public static JavaArchive getAndroidDeployment() {
        return ShrinkWrap.createFromZipFile(JavaArchive.class, new File("android-todos.apk"));
    }

    @Deployment(name = "todo-ear-app")
    @TargetsContainer("jbossas")
    public static EnterpriseArchive getJBossASDeployment() {
        return ShrinkWrap.createFromZipFile(EnterpriseArchive.class, new File("todo-ear.ear"));
    }

    @Browser
    @FindBy(id = "login-box")
    private LoginWebFragment loginFragment;

    @Browser
    @FindBy(id = "project-list")
    private ProjectFragment projectFragment;

    @Browser
    @FindBy(id = "task-container")
    private TaskWebFragment taskFragment;

    @Browser
    @FindBy(id = "logout-btn")
    private WebElement logoutButton;

    @Mobile
    @Page
    private LoginMobilePage loginMobilePage;

    @Mobile
    @Page
    private TaskMobilePage taskMobileFragment;

    @Test
    @InSequence(1)
    @OperateOnDeployment("todo-ear-app")
    public void loginUserInWebClient(@ArquillianResource URL context) {
        openWebPageUrl(browser, context);
        loginFragment.login("john", "123");
    }

    @Test
    @InSequence(2)
    @OperateOnDeployment("todo-ear-app")
    public void addProject() {

        projectFragment.addProject("groceries");

        Assert.assertEquals(projectFragment.getAddedProject().getText(), "groceries");
    }

    @Test
    @InSequence(3)
    @OperateOnDeployment("todo-ear-app")
    public void addTask() {

        taskFragment.addTask("groceries", "buy some milk", "2020", "10", "20", "buy some fresh milk around the corner");

        Assert.assertEquals(
            taskFragment.getAddedTask().getTitle(),
            "buy some milk");

        Assert.assertEquals(
            taskFragment.getAddedTask().getDescription(),
            "buy some fresh milk around the corner");

    }

    @Test
    @InSequence(4)
    @OperateOnDeployment("todo-mobile-app")
    public void loginUserInMobile(@ArquillianResource AndroidDevice device) {

        device.getActivityManagerProvider()
            .getActivityManager()
            .startActivity("org.jboss.aerogear.todo.activities.LoginActivity");

        loginMobilePage.login("john", "123");
    }

    @Test
    @InSequence(5)
    @OperateOnDeployment("todo-mobile-app")
    public void addMobileTask() {
        taskMobileFragment.addTask("mobile task",
            "2014-10-20", "task from mobile phone!");
    }

    @Test
    @InSequence(6)
    @OperateOnDeployment("todo-ear-app")
    public void seeMobileTaskInWebClient() {
        browser.navigate().refresh();
    }

    @Test
    @InSequence(7)
    @OperateOnDeployment("todo-mobile-app")
    public void logoutFromMobileClient() {
        taskMobileFragment.logout();
    }

    @Test
    @InSequence(8)
    @OperateOnDeployment("todo-ear-app")
    public void logoutFromWebClient() {
        logoutButton.click();
    }

}