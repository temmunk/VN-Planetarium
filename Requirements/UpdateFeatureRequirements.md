# New Feature: Update Planets and Moons
___
## Service Layer Testing Requirements
___
### Update Planet
  - **Method:** updatePlanet
  - **Input:** Planet object with `planetName`, `ownerId`, and `imageData` fields set
    - `planetId` should match an existing planet's `planetId`
  - **Expected Output:**
    - **Success:**
      - return `Planet` object with updated planet information, persisted in the database
    - **Failure:**
      - if `planetName` is invalid
        - throw unhandled 'PlanetFail' exception with message "Invalid planet name"
      - if `imageData` is invalid
        - throw unhandled 'PlanetFail' exception with message "Invalid file type"

### Update Moon
- **Method:** updateMoon
- **Input:** Moon object with `moonName`, `ownerId` and `imageData` fields set
  - `moonId` should match an existing moon's `moonId`
- **Expected Output:**
  - **Success:**
    - return `Moon` object with updated moon information, persisted in the database
  - **Failure:**
    - if `moonName` is invalid
      - throw unhandled 'MoonFail' exception with message "Invalid moon name"
    - if `ownerId` is invalid
      - throw unhandled 'MoonFail' exception with message "Invalid planet id"
    - if `imageData` is invalid
      - throw unhandled 'MoonFail' exception with message "Invalid file type"

## Repository Layer Testing Requirements
___
### Update Planet
- **Method:** updatePlanet
- **Input:** Planet object with `planetName`, `ownerId`, and `imageData` fields set
    - `planetId` should match an existing planet's `planetId`
- **Expected Output:**
    - **Success:**
        - return `Planet` object with updated planet information, persisted in the database
    - **Failure:**
        - if `planetName` is invalid
            - throw unhandled 'PlanetFail' exception with message "Invalid planet name"
        - if `imageData` is invalid
            - throw unhandled 'PlanetFail' exception with message "Invalid file type"

### Update Moon
- **Method:** updateMoon
- **Input:** Moon object with `moonName`, `ownerId` and `imageData` fields set
    - `moonId` should match an existing moon's `moonId`
- **Expected Output:**
    - **Success:**
        - return `Moon` object with updated moon information, persisted in the database
    - **Failure:**
        - if `moonName` is invalid
            - throw unhandled 'MoonFail' exception with message "Invalid moon name"
        - if `ownerId` is invalid
            - throw unhandled 'MoonFail' exception with message "Invalid planet id"
        - if `imageData` is invalid
            - throw unhandled 'MoonFail' exception with message "Invalid file type"