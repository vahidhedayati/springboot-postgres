@ui
Feature: YouTube Test

  Scenario: Verify YouTube channel name
    When I go to page "https://www.youtube.com/c/FlicksAndTheCity2"
    Then I verify YouTube channel name "Flicks And The City Clips"
