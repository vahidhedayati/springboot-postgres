Feature: test user list feature
  Scenario: A few users are added
    Given The following users
    |name|status|
    |John|1|
    |Dave|2|
    When the users are listed
    Then the above list is returned
