#this feature allows users to delete planets from their planetarium according to US5
# US5: As a user I want to remove planets and moons from the Planetarium so I can correct my findings

  Feature: Planet deletion

    #this scenario uses positive test data
  Scenario:provided valid planet name, it should be deleted along with owned moons
    Given the user is properly logged in on the home page
    When the user clicks the planet deletion input
    And the user provides valid planet name to delete
    Then the table should refresh after planet removed
    And the user should be able to see the named planet removed
    And the moons owned by that planet should be removed

    #this scenario uses negative test data
  Scenario:provided invalid planet name, browser alert appears
    Given the user is properly logged in on the home page
    When the user clicks the planet deletion input
    And the user provides invalid planet name to be deleted
    Then the user should get a browser alert saying Invalid planet name
    And the user should stay on the home page
