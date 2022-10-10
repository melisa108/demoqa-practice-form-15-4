package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RegistrationFormWithPageObjectsTestsHomework {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1500x600";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {
        String name = "Elon";
        String lastName = "Musk";
        String email = "elon@gmail.com";
        String gender = "Male";
        String mobile = "1234567891";
        String dayOfBirth = "30";
        String monthOfBirth = "September";
        String yearOfBirth = "1965";
        String subject = "Physics";
        String hobby = "Music";
        String address = "St. Petersburg, Nevskiy pr, 100";
        String state = "Rajasthan";
        String city = "Jaipur";
        
        registrationFormPage.openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setNumber(mobile)
                .setBirthDate(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubject(subject)
                .setHobbies(hobby)
                .setPicture("img/random.jpg")
                .setCurrentAddress(address)
                .setAddress(state, city)
                .clickSubmit();

        registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", name + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", "random.jpg")
                .checkResult("Address", address)
                .checkResult("State and City", state + " " + city);
    }

    @Test
    void fillFormWithMinimumDataTest() {
        registrationFormPage.openPage()
                .setFirstName("Alex")
                .setLastName("Egorov")
                .setGender("Male")
                .setNumber("1234567890")
                .clickSubmit();

        registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", "Alex Egorov")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "1234567890");
    }
}