Feature: Buy individual tickets
  In order to save time at the station
  As a casual traveller
  I want to be able to buy train tickets online


  Scenario: Tracy wants to travel from London City to Newark Liberty for a festival
  Tracy prefers low cost tickets to flexibility or arriving at a specific time.
  She isn't sure when she will return, so she wants to keep her options open.

    Given that Tracy has decided to check available tickets
    When she looks at a trip from London City to Newark Liberty leaving tomorrow
    Then she should be shown the cheapest ticket price from London City to Newark Liberty

  Scenario: Bill wants to travel from London City to Birmingham for a work trip
    Bill needs to travel on precise dates.

    Given that Bill has decided to check available tickets
    When he looks at a return trip from London City to Birmingham leaving tomorrow and returning in 2 days
    Then he should be shown the cheapest return ticket price from London to Birmingham
