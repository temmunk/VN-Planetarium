#this feature allows users to see and interact with their planetarium, including the planets and moons they added
#according to User Story 3

  Feature: Home page
  #in this scenario we use negative test data to achieve expected results
    Scenario: user cannot see homepage before logging in
      Given the user is not logged in
      When the user tries to directly access the home page
      Then the user should be denied access

    #in this scenario we use positive test data to achieve expected results
    Scenario: valid credentials should pass
      Given the user is properly logged in on the home page
      Then the planets and moons they added should be visible
      And a greeting message should be present for the user


    Rule: Users cannot view each others resources
      Example: New User(User2) logs in after User1, the planetarium table should be empty
        Given the user is properly logged in on the home page
        When the user logs out
        And the user should be redirected to the Login page
        When the user clicks the register link
        And provides new credentials for a second user
        * the user should be redirected to the Login page
        When the second user provides their credentials
        * the user clicks login
        * the user should be redirected to the Home page
        Then the table should be empty






