@TainaraMartins_PublicHolidays
@CreateHoliday
Feature: Create a holiday

  Scenario Outline: Sucessfully create a new holiday
    When I sucessfully create a new holiday with local <cityName> and name <holidayName> and date "<date>" and contry "<countryCode>" and year <year>
    Then the create status code must be 201
    And the id must be not null in the response

    Examples:
    | cityName      | holidayName          | date       | countryCode | year      |
    | Santa Rita    | Padroeira            | 2023-05-22 | BR          | 2023      |
    | Natercia      | Emancipação Politica | 2023-07-20 | BR          | 2023      |