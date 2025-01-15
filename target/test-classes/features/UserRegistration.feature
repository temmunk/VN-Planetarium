#this feature allows the user to create an account as long as the entered credentials follow requirements
#according to User Story 1
  Feature: User Registration
    # in this scenario we use positive and negative test data to achieve expected results
    Scenario Outline:valid credentials should pass, invalid credentials should return alert
      Given the user is on the login page
      When the user clicks the register link
      When the user provides a username "<Username>"
      And the user provides a password "<Password>"
      And the user submits the credentials
      Then the user should get a browser alert "<ExpectedResult>"
      And the user is redirected to "<Page>"


      Examples:
      |Username|Password|ExpectedResult|Page|
      |Super_man-2001                 |	Krypton-was_2000               |Account created successfully|Planetarium Login|
      |Batman                         |	Krypton-was_2000               |Invalid username            |Account Creation |
      |Bane                           |	Krypton-was_2000               |Invalid username            |Account Creation |
      |wonder_woman_for_the_DC_theming|	Krypton-was_2000               |Invalid username            |Account Creation |
      |2face                          |	Krypton-was_2000               |Invalid username            |Account Creation |
      |joker!!!!!!?)                  |	Krypton-was_2000               |Invalid username            |Account Creation |
      |Super_man-2001                 |	b0Ts                           |Invalid password            |Account Creation |
      |Super_man-2001                 |	AlfredIsTheBestButlerToExist111|Invalid password            |Account Creation |
      |Super_man-2001                 |	3atman                         |Invalid password            |Account Creation |
      |Super_man-2001                 |	A1fredIsTheBestButlerToExist!! |Invalid password            |Account Creation |
      |Super_man-2001                 |	batman1                        |Invalid password            |Account Creation |
      |Super_man-2001	              | BATMAN1	                       |Invalid password            |Account Creation |
      |Super_man-2001                 |	Robin                          |Invalid password            |Account Creation |
