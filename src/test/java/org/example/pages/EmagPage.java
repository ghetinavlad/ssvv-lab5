package org.example.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.Arrays;
import java.util.stream.Collectors;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;

import java.util.List;

@DefaultUrl("http://www.emag.ro")
public class EmagPage extends PageObject {

    @FindBy(name="query")
    private WebElementFacade searchTerms;

    @FindBy(className = "searchbox-submit-button")
    //searchbox-backdrop
    private WebElementFacade lookupButton;

    @FindBy(className = "title-phrasing-sm")
    private WebElementFacade searchResult;


    public void enter_keywords(String keyword) {
        searchTerms.type(keyword);
    }

    public void lookup_terms() {
        lookupButton.click();
    }

    public int getResults() {
        String[] resultsArray = searchResult.getText().split(" ");

        int resultsCount = 0;

        try{
            resultsCount = Integer.parseInt(resultsArray[0]);
        }
        catch (Exception e){

        }

        return resultsCount;
    }

    public void selectFilter(String filterName){
        WebElementFacade definitionList = find(By.tagName("a"));
        definitionList.findElement(By.xpath("//a[@href='https://www.emag.ro/search/laptopuri/brand/asus/laptop/c?ref=lst_leftbar_8354_18']")).click();
    }
}