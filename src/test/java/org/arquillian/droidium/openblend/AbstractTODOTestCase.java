package org.arquillian.droidium.openblend;

import java.io.File;

import org.arquillian.droidium.native_.api.Instrumentable;
import org.arquillian.droidium.openblend.drones.Browser;
import org.arquillian.droidium.openblend.drones.Mobile;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Arquillian.class)
@RunAsClient
public abstract class AbstractTODOTestCase {

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

    @Deployment(name = "todo-ear-app", testable = false)
    @TargetsContainer("jbossas")
    public static EnterpriseArchive getJBossASDeployment() {
        return ShrinkWrap.createFromZipFile(EnterpriseArchive.class, new File("todo-ear.ear"));
    }

}
