package br.inatel.idp.PublicHolidays.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:features/"}, glue = {"br.inatel.idp.PublicHolidays.step", "br.inatel.idp.PublicHolidays.config"})
public class FuntionalTestRunner {

}
