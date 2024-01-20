package com.onlinebookstore.reporting;

import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {
    private static final ExtentFactory instance = new ExtentFactory();
    private static final ThreadLocal<ExtentTest> extent = new ThreadLocal<>();

    public ExtentFactory() {

    }

    public static ExtentFactory getInstance() {
        return instance;
    }

    public synchronized ExtentTest getExtent() {
        return (ExtentTest) extent.get();
    }

    public void setExtent(ExtentTest extenParam) {
        extent.set(extenParam);
    }

    public void removeExtentObject() {
        extent.remove();
    }
}
