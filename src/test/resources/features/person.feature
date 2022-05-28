Feature: Person feature

  Scenario: A few people are added
    Given the following people
    |name|
    |FRED|
    |John|
    |Wilma|
    |Eric|
    When the user requests all the people
    Then all the people are returned



