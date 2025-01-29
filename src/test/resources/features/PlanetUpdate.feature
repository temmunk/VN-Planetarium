#this feature allows users to update planets in their planetarium

Feature: Planet Updating

  Background:
    Given the user is properly logged in on the home page
    When  the user clicks the edit button for a planet

  Scenario Outline: User provides valid planet name and valid file type
      When the user enters a planet name "<planet name>" in the planet update form
      And  the user selects a file "<file>" in the planet update form
      And  the user clicks submit in the planet update form
      Then the user should get a browser alert saying "<alert message>"
      And  the planet in the table should be updated

      Examples:
      |planet name      |file          |alert message               |
      |E-arth 6_16      |              |Planet updated successfully!|
      |E-arth 6_16      |planet-5.jpg  |Planet updated successfully!|
      |E-arth 6_16      |Planet png.png|Planet updated successfully!|

    Scenario Outline: User provides invalid planet name or invalid file type
      When the user enters a planet name "<invalid planet name>" in the planet update form
      And  the user selects a file "<invalid file>" in the planet update form
      And  the user clicks submit in the planet update form
      Then the user should get a browser alert saying "<alert message>"

      Examples:
      |invalid planet name            |invalid file     |alert message      |
      |                               |                 |Invalid planet name|
      |iDontKnowWhatToNameThisPlanet11|                 |Invalid planet name|
      |M@r$                           |                 |Invalid planet name|
      |Mars                           |                 |Invalid planet name|
      |E-arth 6_16                    |InvalidPlanet.gif|Invalid file type  |

