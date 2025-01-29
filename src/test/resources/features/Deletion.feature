Feature: Moon and planet deletion

  Scenario: User should be able to delete planet by clicking the delete button next to a planet, along with its associated moons
    Given the user is properly logged in on the home page
    When the user clicks the delete button next to a planet
    And confirm the deletion by clicking ok in the pop up window
    Then the planet should be deleted from the table
    And the moons owned by that planet should be removed

  Scenario: User should be able to delete moon by clicking the delete button next to a moon
    Given the user is properly logged in on the home page
    When the user clicks the delete button next to a moon
    And confirm the deletion by clicking ok in the pop up window
    Then the moon should be deleted from the table