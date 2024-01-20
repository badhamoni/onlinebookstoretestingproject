package com.onlinebookstore.pages;

import com.onlinebookstore.testbase.TestBase;
import org.openqa.selenium.By;

import java.util.HashMap;

public class RegisterPage extends TestBase {
    String pageNameReg = "Register Page";

    private By lbl_ReristerPageHader = By.xpath("//a[@class='nav-link active']");
    private By txt_Email = By.xpath("//input[@id='Email']");
    private By txt_Password = By.xpath("//input[@id='passWord']");
    private By txt_FirstName = By.xpath("//input[@id='firstName']");
    private By txt_LastName = By.xpath("//input[@id='lastName']");
    private By txt_Address = By.xpath("//textarea[@id='address']");
    private By txt_Phone = By.xpath("//input[@id='phno']");
    private By chk_Acceptance = By.xpath("//input[@name='acceptance']");
    private By btn_RegisterMe = By.xpath("//input[@class='btn btn-success']");

    public RegisterPage() {

    }

    public By getLbl_ReristerPageHader() {
        return lbl_ReristerPageHader;
    }

    public By getTxt_Email() {
        return txt_Email;
    }

    public By getTxt_Password() {
        return txt_Password;
    }

    public By getTxt_FirstName() {
        return txt_FirstName;
    }

    public By getTxt_LastName() {
        return txt_LastName;
    }

    public By getTxt_Address() {
        return txt_Address;
    }

    public By getTxt_Phone() {
        return txt_Phone;
    }

    public By getChkAcceptance() {
        return chk_Acceptance;
    }

    public By getBtn_RegisterMe() {
        return btn_RegisterMe;
    }

    public void confirmationRegistratuinPageNavigation() {
        if (isElementExists(lbl_ReristerPageHader)) {
            System.out.println(String.format("Page Successfully Navigated to %s", this.pageNameReg));
        } else {
            System.out.println(String.format("Not abel to Navigate to %s", this.pageNameReg));
        }
    }

    public LoginPage performUserRegistration(HashMap<String, String> hashMap) {
        String email = hashMap.get("Email");
        if (email.toLowerCase().contains("random")) {
            email = email.replace("random", generateRandomString(8));
        }
        testStep().info("Enter email address to register as: " + email);
        setText(txt_Email, email);
        testStep().info("Enter password to register");
        setText(txt_Password, hashMap.get("Password"));
        testStep().info("Enter first name to register as: " + hashMap.get("FirstName"));
        setText(txt_FirstName, hashMap.get("FirstName"));
        testStep().info("Enter last name to register as: " + hashMap.get("LastName"));
        setText(txt_LastName, hashMap.get("LastName"));
        testStep().info("Enter user address to register as: " + hashMap.get("Address"));
        setText(txt_Address, hashMap.get("Address"));
        testStep().info("Enter phone number to register as: " + hashMap.get("MobileNumber"));
        setText(txt_Phone, hashMap.get("MobileNumber"));
        testStep().info("Select the checkbox");
        click(chk_Acceptance);
        testStep().info("Click on Register Me button to register the user");
        click(btn_RegisterMe);
        return new LoginPage();
    }

}
