package com.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

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
        String mobile = "1234567891";
        String monthOfBirth = "April";
        String yearOfBirth = "1978";
        String subject = "Physics";
        String hobby = "Sports";
        String address = "St. Petersburg, Nevskiy pr, 101";
        String state = "Rajasthan";
        String city = "Jaipur";


        open("/automation-practice-form");

        //access to Submit button
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        //name, last name, email
        $("#firstName").setValue(name);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);

        //gender
        //selectRadio() doesn't work
        $("[for=gender-radio-1]").click();

        //mobile
        $("#userNumber").setValue(mobile);

        //date of birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-dropdown-container--select").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOptionByValue(yearOfBirth);
        $(".react-datepicker__day--009").click();

        //subjects
        $("#subjectsContainer").click();
        $("#subjectsInput").setValue(subject).pressEnter();

        //hobbies
        $(byText(hobby)).click();  // or $("[for=hobbies-checkbox-1]").click();

        //picture
        $("#uploadPicture").uploadFile(new File("src/test/resources/random.jpg"));

        //current address
        $("#currentAddress").setValue(address);

        //state and city
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();

        //submit button
        $("#submit").click();

        //validation form
        $(".table-responsive tbody tr:nth-child(1) td:nth-child(2)").shouldHave(text(name + " " + lastName));
        $(".table-responsive tbody tr:nth-child(2) td:nth-child(2)").shouldHave(text(email));
        $(".table-responsive tbody tr:nth-child(3) td:nth-child(2)").shouldHave(text("Male"));
        $(".table-responsive tbody tr:nth-child(4) td:nth-child(2)").shouldHave(text(mobile));
        $(".table-responsive tbody tr:nth-child(5) td:nth-child(2)").shouldHave(text("09 " + (monthOfBirth) + "," + (yearOfBirth)));
        $(".table-responsive tbody tr:nth-child(6) td:nth-child(2)").shouldHave(text(subject));
        $(".table-responsive tbody tr:nth-child(7) td:nth-child(2)").shouldHave(text(hobby));
        $(".table-responsive tbody tr:nth-child(8) td:nth-child(2)").shouldHave(text("random.jpg"));
        $(".table-responsive tbody tr:nth-child(9) td:nth-child(2)").shouldHave(text(address));
        $(".table-responsive tbody tr:nth-child(10) td:nth-child(2)").shouldHave(text(state + " " + city));



    }
}

