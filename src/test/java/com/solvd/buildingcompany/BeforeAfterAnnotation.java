package com.solvd.buildingcompany;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

public abstract class BeforeAfterAnnotation {

    private static final Logger LOGGER = LogManager.getLogger(BeforeAfterAnnotation.class);

    @BeforeSuite
    public void beforeSuite() {
        LOGGER.info("This method will run before any test class method.");
    }

    @BeforeGroups
    public void beforeGroups() {
        LOGGER.info("This method annotates the methods that will be executed before the first method in any of the " +
                "specified groups.");
    }

    @BeforeTest
    public void beforeTest() {
        LOGGER.info("This method method will run up to all test methods.");
    }

    @BeforeMethod
    public void beforeMethod() {
        LOGGER.info("This method will run before each test method.");
    }

    @AfterTest
    public void afterTest() {
        LOGGER.info("This method will run after all test methods belonging to classes within the <test> tag.");
    }

    @AfterMethod
    public void afterMethod() {
        LOGGER.info("This method will run after each test method.");
    }

    @AfterClass
    public void afterClass() {
        LOGGER.info("This method will run after all test methods in the current class.");
    }

    @AfterGroups
    public void afterGroups() {
        LOGGER.info("This will be performed after all methods in any of the specified groups.");
    }

    @AfterSuite
    public void afterSuite() {
        LOGGER.info("This method will run after all test class methods.");
    }
}
