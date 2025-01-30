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

## API Layer Testing Requirements
___
### Update Planet
- **Method:** PATCH
- **URL:** /planetarium/planet
- **Body:**
```json
// with image data
{
  "planetName": "",  
  "planetId": "",  
  "ownerId": {ownerId},
  "imageData": ""  
}
```
```json
// without image data
{
    "planetName": "",
    "ownerId": {ownerId},
    "planetId":""
}
```
- **Response**:
    - status code:
        - success: 200
        - fail: 400
```json
// sucess
{
    "planetId": 0, 
    "planetName": "",
    "ownerId": 0, 
    "imageData": ""
}

// fail: Invalid name
{
    "message": "Invalid planet name",
}
```
```json
// fail: invalid file type
{
    "message": "Invalid file type"
}
```
### Update Moon
- **Method:** PATCH
- **URL:** /planetarium/moon
- **Body:**
```json
// with image data
{
  "moonName": "",  
  "moonId": "",  
  "ownerId": {ownerId},
  "imageData": ""  
}
```
```json
// without image data
{
    "moonName": "",
    "ownerId": {ownerId},
    "moonId":""
}
```
- **Response**:
    - status code:
        - success: 200
        - fail: 400
```json
// sucess
{
    "moonId": 0, 
    "moonName": "",
    "ownerId": 0, 
    "imageData": ""
}

// fail: Invalid name
{
    "message": "Invalid moon name",
}
```
```json
// fail: invalid file type
{
    "message": "Invalid file type"
}
```
