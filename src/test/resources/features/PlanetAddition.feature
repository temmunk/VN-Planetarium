#this feature allows users to add planets to their planetarium
#according to User Story 4

  Feature: Planet addition
   #because the user has to be properly logged in to see the planet creation input, we can set up a background
    Background:
      Given the user is properly logged in on the home page
      When user clicks planet creation input

    # in this scenario we use positive test data with adding an image
    Scenario: provided valid name and jpg image, planet should be added
      And the user provides valid planet name
      When the user decides to upload an image
      And the user provides a valid planet image jpg filetype
      And the user clicks submit
      And the user accepts alert
      Then the table should refresh after planet added
      And the user should be able to see the new planet added

    # in this scenario we use positive test data with adding an image
    Scenario: provided valid name and png image, planet should be added
      And the user provides valid planet name
      When the user decides to upload an image
      And the user provides a valid planet image png filetype
      And the user clicks submit
      And the user accepts alert
      Then the table should refresh after planet added
      And the user should be able to see the new planet added

    #in this scenario we use positive test data without adding an image
    Scenario:provided valid name and no image, planet should be added
      And the user provides valid planet name
      When the user decides not to upload an image
      And the user clicks submit
      And the user accepts alert
      Then the table should refresh after planet added
      And the user should be able to see the new planet added

   #in this scenario we use negative test data to test image format requirement
   Scenario:provided valid name but invalid image, browser alert should appear
     And the user provides valid planet name
     When the user decides to upload an image
     And the user uploads invalid planet image file type
     And the user clicks submit
     Then the user should get a browser alert saying Invalid filetype
     And the user should stay on the home page

     #in this scenario we we negative test data to test planet name requirements
    Scenario Outline:provided invalid name, browser alert should appear
      And the user provides planet name "<planet name>"
      And the user clicks submit
      Then the user should get a browser alert saying Invalid planet name
      And the user should stay on the home page

      Examples:
       |planet name|
       |           |
       |iDontKnowWhatToNameThisPlanet11|
       |M@r$|
       |Earth|
