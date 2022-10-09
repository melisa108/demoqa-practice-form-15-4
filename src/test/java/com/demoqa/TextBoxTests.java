package com.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1500x650";
        Configuration.holdBrowserOpen = true;

    }

    @Test
    void fillFormTest() {
        String name = "Egor";

        open("/text-box");

        $("#userName").setValue(name);
        $("#userEmail").setValue("Egor@egor.com");
        $("#currentAddress").setValue("Some address 1");
        $("#permanentAddress").setValue("Another address 2");

        //access to Submit button
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("#submit").click();

        $("#output #name").shouldHave(text(name));
        $("#output #email").shouldHave(text("Egor@egor.com"));
        $("#output #currentAddress").shouldHave(text("Some address 1"));
        $("#output #permanentAddress").shouldHave(text("Another address 2"));
    }

}
