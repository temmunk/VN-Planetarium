#This feature allows registered users to login to their planetarium to view and interact with their homepage
#according to User Story 2
  Feature: User Login

    # in this scenario, successful logins dont cause an alert so the 2 paths should be separated
    Scenario:valid credentials should pass
      Given the user is on the login page
      When the user provides valid login credentials
      And the user clicks login
      Then the user should be redirected to the Home page

        # in this scenario we use negative test data to achieve expected results
    Scenario Outline:invalid credentials should return alert
      Given the user is on the login page
      When the user provides a username for login"<Username>"
      And the user provides a password for login"<Password>"
      And the user submits the credentials
      Then the user should see Invalid Credentials alert
      And the user should be redirected to the Login page



      Examples:
        |Username|Password|
        |Batman    |b0Ts	           |
        |Robin     |Iamthenight1939	   |
        |Robin     |b0Ts               |
