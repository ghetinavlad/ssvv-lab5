package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import org.example.pages.EmagPage;
import org.hamcrest.Matchers;

public class EmagUserSteps {

    EmagPage emagPage;

    @Step
    public void enters(String keyword) {
        emagPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        emagPage.lookup_terms();
    }

    @Step
    public void should_see_results(int minResults) {
        assertThat(emagPage.getResults(), Matchers.greaterThan(minResults));
    }

    @Step
    public void should_filter_results(String brand) {
        emagPage.selectFilter(brand);
    }

    @Step
    public void should_see_invalid_results(int maxResults) {
        assertThat(emagPage.getResults(), Matchers.lessThanOrEqualTo(maxResults));
    }

    @Step
    public void is_the_home_page() {
        emagPage.open();
    }

    @Step
    public void looks_for(String term) {
        enters(term);
        starts_search();
    }
}