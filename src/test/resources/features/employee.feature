Feature: Employee browser test

  Scenario: Open chrome and click add employee link
    Given chrome browser is opened and employee page is loaded up
    When the add employee link is clicked
    Then we see a form asking for employee information



