#this feature allows users to update moons in their planetarium

Feature: Moon Updating

  Background:
    Given the user is properly logged in on the home page
    When  the user clicks the edit button for a moon

  Scenario Outline: User provides valid moon data
    When the user enters a moon name "<moon name>" in the moon update form
    And  the user selects a file "<file>" in the moon update form
    And  the user enters an ownerId <owner id> in the moon update form
    And  the user clicks submit in the moon update form
    Then the user should get a browser alert saying "<alert message>"
    And  the moon in the table should be updated

    Examples:
      |moon name |owner id|file        |alert message             |
      |Mo-on 6_16|2       |            |Moon updated successfully!|
      |Mo-on 6_16|2       |moon-5.jpg  |Moon updated successfully!|
      |Mo-on 6_16|2       |Moon png.png|Moon updated successfully!|

  Scenario Outline: User provides invalid moon data
    When the user enters a moon name "<moon name>" in the moon update form
    And  the user selects a file "<file>" in the moon update form
    And  the user enters an ownerId <owner id> in the moon update form
    And  the user clicks submit in the moon update form
    Then the user should get a browser alert saying "<alert message>"

    Examples:
      |moon name                      |owner id|file           |alert message    |
      |                               |2       |               |Invalid moon name|
      |iDontKnowWhatToNameThisMoon1234|2       |               |Invalid moon name|
      |M##n                           |2       |               |Invalid moon name|
      |Titan                          |2       |               |Invalid moon name|
      |Mo-on 6_16                     |2       |InvalidMoon.gif|Invalid file type|
      |Mo-on 6_16                     |3       |               |Invalid planet id|