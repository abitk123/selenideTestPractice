package selenide.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import selenide.LoadableComponent;
import selenide.utilsAndHelpers.*;

import java.util.List;


import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeLessThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage extends LoadableComponent {

    private final SelenideElement signInButton = $("#signin2");
    private final SelenideElement username = $("#nameofuser");
    private final SelenideElement loginButton = $("#login2");
    private final SelenideElement title = $("a.navbar-brand");
    private final SelenideElement categoryBlock = $(".list-group");
    private final SelenideElement footer = $("div#fotcont");
    private final SelenideElement phonesCategory = categoryBlock.$(byText("Phones"));
    private final SelenideElement laptopsCategory = categoryBlock.$(byText("Laptops"));
    private final SelenideElement monitorsCategory = categoryBlock.$(byText("Monitors"));
    private final ElementsCollection item = $$(".card-title a");
    ItemPage itemPage = new ItemPage();

    @Override
    public void waitUntilLoaded() {
        this.title.shouldBe(visible);
    }

    public void shouldShowWelcome(String name) {
        Allure.step("Поверяем наличие имени юзера на главной странице после логина", () -> {
            username.shouldBe(visible).shouldHave(text(name));
        });

    }

    public SelenideElement getFooter() {
        return footer;
    }

    public SelenideElement getUsernameAfterLogin() {
        return username;
    }

    public void filterPhones() {
        Allure.step("Фильтруем телефоны", () -> {
            phonesCategory.shouldBe(enabled).click();
        });

    }


    public void filterLaptops() {
        Allure.step("Фильтруем ноутбуки", () -> {
            laptopsCategory.shouldBe(enabled).click();
        });

    }

    public void filterMonitors() {
        Allure.step("Фильтруем мониторы", () -> {
            monitorsCategory.shouldBe(enabled).click();
        });

    }

    public ElementsCollection getItems() {
        return item;
    }

    public List<String> filterItems(Item item) {
        this.waitUntilLoaded();
        List<String> initialItems = this.getItems().shouldHave(sizeGreaterThan(0)).texts();
        if (item == Item.PHONE) {
            this.filterPhones();
        } else if (item == Item.LAPTOP) {
            this.filterLaptops();
        } else if (item == Item.MONITOR) {
            this.filterMonitors();
        }


        List<String> filteredItems = this.getItems().shouldHave(sizeLessThan(initialItems.size())).texts();

        return filteredItems;

    }

    public void gotoItem(String title) {
        waitUntilLoaded();
        SelenideElement titleItem = $(By.xpath("//a[text()='" + title + "']"));
        titleItem.shouldBe(enabled).click();
        itemPage.waitUntilLoaded();
    }


    /*Тут будет дополнительная логика работы с навигационным баром - поэтому if else
     */
    public void gotoNavBar(NavBar navBar) {
        if (navBar == NavBar.CART) {
            Allure.step("Переходим в корзину", () -> {
                $("#navbarExample #cartur").shouldBe(enabled).click();
            });

        } else if (navBar == NavBar.LOGIN) {
            Allure.step("Переход к модальному окну логина", () -> {
                this.loginButton.click();
            });

        } else if (navBar == NavBar.SIGN) {
            Allure.step("Переход к модальному окну регистрации", () -> {
                this.signInButton.click();
            });
    }
}
}

