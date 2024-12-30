#this feature allows users to add moons to their planetarium
#according to User Story 4

Feature: Moon addition
   #because the user has to be properly logged in to see the moon creation input, we can set up a background
  Background:
    Given the user is properly logged in on the home page
    When user clicks moon creation input

    # in this scenario we use positive test data with adding an image
  Scenario:provided valid moon name, owner planet and image type, new moon should be added
    And the user provides valid moon name
    And the user provides valid owner planet
    When the user decides to upload an image
    And the user provides a valid filetype
    And the user clicks submit
    Then the table should refresh after moon added
    And the user should be able to see the new moon added

  #in this scenario we use positive test data without adding an image
  Scenario:provided valid moon name and owner planet without image, moon should be added
    And the user provides valid moon name
    And the user provides valid owner planet
    When the user decides not to upload an image
    And the user clicks submit
    Then the table should refresh after moon added
    And the user should be able to see the new moon added


   #in this scenario we use negative test data to test image format requirement
  Scenario: provided valid moon name and owner planet but wrong image format, alert should pop up
    And the user provides valid moon name
    And the user provides valid owner planet
    When the user decides to upload an image
    And the user uploads invalid file type
    And the user clicks submit
    Then the user should get a browser alert saying Invalid filetype
    And the user should stay on the home page

  #in this scenario we use negative test data to test moons requiring owner planets
  Scenario:provided invalid owner planet, alert should pop up
    And the user provides valid moon name
    And the user provides invalid owner planet
    And the user clicks submit
    Then the user should get a browser alert saying Invalid planet id
    And the user should stay on the home page

     #in this scenario we we negative test data to test moon name requirements
  Scenario Outline: provided invalid moon name, alert should pop up
    Given the user is properly logged in on the home page
    When user clicks moon creation input
    And the user provides moon name"<moon name>"
    And the user clicks submit
    Then the user should get a browser alert saying Invalid moon name
    And the user should stay on the home page

    Examples:
      |moon name|
      |           |
      |iDontKnowWhatToNameThisMoon1234|
      |M##n|
      |Luna|
