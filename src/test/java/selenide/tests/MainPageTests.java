package selenide.tests;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import java.util.List;
import static io.qameta.allure.SeverityLevel.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainPageTests extends BaseTest{

    @Test
    @Severity(CRITICAL)
    void shouldFilterItemsAndReturnPhones() {

        List<String> filteredItems = mainPage.filterItems("phone");

        assertFalse(filteredItems.isEmpty());

        List<String> allowedBrands = List.of("samsung", "nokia", "nexus", "iphone", "sony", "htc");

        for (String item : filteredItems) {
            String lowerItem = item.toLowerCase();
            boolean matchesBrand = allowedBrands.stream().anyMatch(lowerItem::contains);
            assertTrue(matchesBrand);
        }

    }

    @Test
    @Severity(CRITICAL)
    void shouldFilterItemsAndReturnLaptops() {

        List<String> filteredItems = mainPage.filterItems("laptop");

        assertFalse(filteredItems.isEmpty());

        List<String> allowedBrands = List.of("sony vaio", "macbook", "dell");

        for (String item : filteredItems) {
            String lowerItem = item.toLowerCase();
            boolean matchesBrand = allowedBrands.stream().anyMatch(lowerItem::contains);
            assertTrue(matchesBrand);
        }

    }


    @Test
    @Severity(CRITICAL)
    void shouldFilterItemsAndReturnMonitors() {

        List<String> filteredItems = mainPage.filterItems("monitor");

        assertFalse(filteredItems.isEmpty());

        List<String> allowedBrands = List.of("apple", "asus");

        for (String item : filteredItems) {
            String lowerItem = item.toLowerCase();
            boolean matchesBrand = allowedBrands.stream().anyMatch(lowerItem::contains);
            assertTrue(matchesBrand);
        }

    }
}


