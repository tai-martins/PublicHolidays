package br.inatel.idp.PublicHolidays.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/feature"}, glue = {"br.inatel.idp.PublicHolidays.step"})

public class FuntionalTestRunner {

}
