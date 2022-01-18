package com.mizu.definitions;

import com.mizu.pageObjects.ProductsPage;
import com.mizu.utils.Log4j;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.Map;

public class ProductsPageSteps {

    ProductsPage productsPage = new ProductsPage();

    @Given("I go to {string}")
    public void goTo(String url) {
        productsPage.getUrl(url);
    }

    @Then("Checking that {int} products came on each page for the first {int} pages")
    public void checkingCountProductsOnThePage(int productCount, int pageCount) {
        String flowerPageUrl = "https://www.mizu.com/flowers";
        Map<String, Integer> linkAndProductsMap = productsPage.getLinkAndProductCount(pageCount);
        for (int i = 0; i < pageCount; i++) {
            String key = (String) linkAndProductsMap.keySet().toArray()[i];
            int value = linkAndProductsMap.get(key);
            //System.out.println("Product Size: " + value + " , Link :" + key);
            if (i == 0) {
                Assert.assertEquals(flowerPageUrl, key);
                Assert.assertEquals(productCount, value);
                Log4j.info("Product Size: " + value + " , Link :" + key);
            } else {
                Assert.assertEquals((flowerPageUrl + "?page=" + (i + 1)), key);
                Assert.assertEquals(productCount, (value - (productCount * i)));
                Log4j.info("Product Size: " + (value - (productCount * i)) + " , Link :" + key);
            }
        }
    }

    @And("Click {string} from filter field")
    public void clickSortFromFilterField(String filter) {
        productsPage.selectSortFilter(filter);
    }

    @And("Select {string} from sort filter field")
    public void selectFromSortFilterField(String sortRadioButton) {
        productsPage.selectSortPriceHighToLow(sortRadioButton);
    }

    @Then("Checking that the products are listed in the correct price sort")
    public void checkingCorrectPriceOrder() {
        productsPage.scrollLastPage();
        if (!productsPage.checkPriceSort()) {
            Assert.fail("Not is decreasing order");
        }
    }
}
