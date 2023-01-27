@TainaraMartins_PublicHolidays
@CreateHoliday
Feature: Create a holiday

  Scenario: Sucessfully create a new holiday
    Given "Santa Rita" is not yet registered
    Given "Santa Rita" is provided as cityName
    And "2023-05-22" is provided as date
    When post a new holiday
    Then the statusCode must be "201"
    And the holidayName should be "Padroeira"
    And the countryCode should be "BR"
    And the year should be "2023"



