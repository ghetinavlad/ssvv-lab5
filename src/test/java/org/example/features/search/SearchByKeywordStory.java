package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import org.example.steps.serenity.EndUserSteps;
import org.example.steps.serenity.EmagUserSteps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SerenityRunner.class)
public class SearchByKeywordStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserSteps anna;

    @Steps
    public EmagUserSteps emg;

    @Test
    public void lab5Tests() {
        List<String> inputWords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("words.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                inputWords = Arrays.asList(line.split(","));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //search_valid_goodResults(inputWords.get(0));
        //search_invalid_badResults(inputWords.get(1));
        filter_valid_goodResults(inputWords.get(0),inputWords.get(2));
        //filter_invalid_badResults(inputWords.get(0),inputWords.get(3));
    }




    private void  search_valid_goodResults(String searchTerm){
        emg.is_the_home_page();
        emg.looks_for(searchTerm);
        emg.should_see_results(1);
    }

    private void  search_invalid_badResults(String searchTerm){
        emg.is_the_home_page();
        emg.looks_for(searchTerm);
        emg.should_see_invalid_results(0);
    }
    private void  filter_valid_goodResults(String searchTerm,String filter){
        emg.is_the_home_page();
        emg.looks_for(searchTerm);
        emg.should_see_results(1);
        emg.should_filter_results(filter);
        emg.should_see_results(1);
    }


    private void  filter_invalid_badResults(String searchTerm,String filter){
        emg.is_the_home_page();
        emg.looks_for(searchTerm);
        emg.should_see_results(1);
        emg.should_filter_results(filter);
        emg.should_see_invalid_results(0);
    }




    @Issue("#WIKI-1")
    @Test
    public void searching_by_keyword_apple_should_display_the_corresponding_article() {
        anna.is_the_home_page();
        anna.looks_for("apple");
        anna.should_see_definition("A common, round fruit produced by the tree Malus domestica, cultivated in temperate climates.");

    }

    @Test
    public void searching_by_keyword_banana_should_display_the_corresponding_article() {
        anna.is_the_home_page();
        anna.looks_for("pear");
        anna.should_see_definition("An edible fruit produced by the pear tree, similar to an apple but typically elongated towards the stem.");
    }
    @Test
    public void searching_by_keyword_pineapple_should_display_the_corresponding_article() {
        anna.is_the_home_page();
        anna.looks_for("pineapple");
        anna.should_see_definition("A tropical plant, Ananas comosus, native to South America, having thirty or more long, spined and pointed leaves surrounding a thick stem.");
    }
    @Test
    public void searching_by_keyword_carrot_should_display_the_corresponding_article() {
        anna.is_the_home_page();
        anna.looks_for("carrot");
        anna.should_see_definition("A vegetable with a nutritious, juicy, sweet root that is often orange in colour, Daucus carota, especially the subspecies sativus in the family Apiaceae.");
    }

    @Pending @Test
    public void searching_by_ambiguious_keyword_should_display_the_disambiguation_page() {
    }
} 