package com.mizu.pageObjects;

import com.mizu.driver.DriverExec;
import com.mizu.driver.DriverMethods;
import com.mizu.utils.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.*;


public class ProductsPage {

    private static WebDriver driver;
    private static DriverMethods driverMethods;

    public ProductsPage() {
        driver = DriverExec.getDriver();
        PageFactory.initElements(driver, this);
        driverMethods = new DriverMethods();
    }

    public static class ProductsPageLocators {
        public static By categoryInfoText = By.className("category-info__text");
        public static By productsDiv = By.cssSelector("div.products");
        public static By productsItem = By.cssSelector("div.products .products__item");
        public static By filterField = By.cssSelector(".js-filter-sort-item > .dropdown-toggle");
        public static By filterSortRadioChecked = By.cssSelector(".js-filter-dropdown-list-radio:nth-child(2) > .filter__dropdown-label");
        public static By priceIntegerValueBy = By.cssSelector("span.price__integer-value");
        public static By allProductPrice = By.xpath("//div[@class='price'  or @class='price price--new']");
        public static By priceDecimalValueBy = By.cssSelector("span.price__decimal-value-with-currency");
        public static By productFoundCount = By.cssSelector(".js-order-count");
    }

    public void getUrl(String url) {
        driverMethods.navigateTo(url);
        Log4j.info("Opening Page : " + url);
    }

    public Map<String, Integer> getLinkAndProductCount(int pageCount) {
        Map<String, Integer> map = new HashMap<>();
        WebElement categoryInfo = driverMethods.findElement(ProductsPage.ProductsPageLocators.categoryInfoText);
        for (int i = 0; i < pageCount; i++) {
            if (i == 0) {
                List<WebElement> eleList = productsList();
                map.put(driverMethods.getCurrentUrl(), eleList.size());
            } else {
                driverMethods.scrollElementCenter(categoryInfo);
                driverMethods.waitBySeconds(5);
                List<WebElement> eleList2 = productsList();
                map.put(driverMethods.getCurrentUrl(), (eleList2.size()));
            }
            Log4j.info("Page scrolled down " + (pageCount - 1) + " times");
        }
        Log4j.info("Created map with status code and link");
        return sortByValue(map);
    }

    public List<WebElement> productsList() {
        WebElement productsDiv = driverMethods.findElement(ProductsPage.ProductsPageLocators.productsDiv);
        return driverMethods.findElementsWithElement(productsDiv, ProductsPageLocators.productsItem);
    }

    public Map<String, Integer> sortByValue(Map<String, Integer> map) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list
                = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
        // Sort the list using lambda expression
        Collections.sort(list, (i1, i2) -> i1.getValue().compareTo(i2.getValue()));
        // put data from sorted list to hashmap
        HashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            sortedMap.put(aa.getKey(), aa.getValue());
        }
        Log4j.info("Status code and link map sorted by link sort");
        return sortedMap;
    }

    public void selectSortFilter(String filter) {
        WebElement filterField = driver.findElement(ProductsPage.ProductsPageLocators.filterField);
        driverMethods.clickElementWithText(filterField, filter);
        Log4j.info("Click " + filter + " from filter field");
    }

    public void selectSortPriceHighToLow(String sortRadioButton) {
        WebElement filterSortRadioChecked = driver.findElement(ProductsPageLocators.filterSortRadioChecked);
        driverMethods.clickElementWithText(filterSortRadioChecked, sortRadioButton);
        Log4j.info("Select " + sortRadioButton + " from sort filter field");
    }

    public int productCount() {
        driverMethods.waitForAjax(driver);
        WebElement productFoundCount = driverMethods.findElement(ProductsPageLocators.productFoundCount);
        String productCount = driverMethods.getText2(productFoundCount);
        return Integer.parseInt(productCount);
    }


    public void scrollLastPage() {
        driverMethods.waitForAjax(driver);
        int productCountInt = productCount();
        int scrollSize = (int) Math.ceil(productCountInt / 60.0);
        Log4j.info("Page size: " + scrollSize);
        WebElement categoryInfo = driverMethods.findElement(ProductsPage.ProductsPageLocators.categoryInfoText);
        for (int i = 0; i < scrollSize; i++) {
            driverMethods.scrollElementCenter(categoryInfo);
            driverMethods.waitBySeconds(5);
        }
        Log4j.info("Scrolled down to the last product");
    }

    public List<Double> getPrice() {
        List<Double> priceProductList = new ArrayList<>();
        List<WebElement> elementPrice = driver.findElements(ProductsPage.ProductsPageLocators.allProductPrice);
        for (WebElement price : elementPrice) {
            String intPrice = replaceCharacterString(price.findElement(ProductsPage.ProductsPageLocators.priceIntegerValueBy).getText());
            double priceIntegerValue = Double.parseDouble(intPrice);
            double priceDecimalValue = Double.parseDouble(price.findElement(ProductsPage.ProductsPageLocators.priceDecimalValueBy).getText());
            double total = priceIntegerValue + priceDecimalValue;
            priceProductList.add(total);
        }
        Log4j.info("Price list of all products : " + priceProductList);
        return priceProductList;
    }

    public String replaceCharacterString(String str) {
        String strNew = str.replace(",", "");
        return strNew;
    }

    public Boolean checkPriceSort() {
        List<Double> priceList = getPrice();
        for (int i = 0; i < priceList.size() - 1; i++) {
            if (priceList.get(i) >= priceList.get(i + 1))
                Log4j.info("Price order is correct");
                return true;
        }
        Log4j.error("Price order is not correct");
        return false;
    }
}
