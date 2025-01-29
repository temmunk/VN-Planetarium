package com.revature.planetarium.service.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoonServiceImp<T> implements MoonService<T> {
    
    private MoonDao moonDao;
    private PlanetDao planetDao;

    public MoonServiceImp(MoonDao moonDao) {
        this.moonDao = moonDao;
        this.planetDao = new PlanetDaoImp();
    }

    @Override
    public boolean createMoon(Moon moon) {

        Pattern p = Pattern.compile(
                "^[\\w\\-\\s]+$", Pattern.CASE_INSENSITIVE);
        //this regex pattern allows alphanumeric characters, dashes, underscores and spaces
        Matcher m=p.matcher(moon.getMoonName());
        boolean b=m.matches();

        if (moon.getMoonName().length() < 1 || moon.getMoonName().length() > 30) {
            throw new MoonFail("Invalid moon name");
        }
        if (!b) {
            throw new MoonFail("Invalid moon name");
        }

        Optional<Moon> existingMoon = moonDao.readMoon(moon.getMoonName());
        if(existingMoon.isPresent()) {
            throw new MoonFail("Invalid moon name");
        }

        if (moon.getImageData() != null ) {
            if (!moon.getImageData().startsWith("/9j/") && !moon.getImageData().startsWith("iVBORw0KGgo")) {
                //Jpg images encoded in base64 usually start with "/9j/" and png start with "iVBORw0KGgo"
                throw new MoonFail("Invalid file type");
            }
        }




        Optional<Moon> newMoon = moonDao.createMoon(moon);
        if (newMoon.isEmpty()) {
            throw new MoonFail("Could not create new moon");
        }


        return true;
    }


    @Override
    public Moon selectMoon(T idOrName) {
        Optional<Moon> moon;
        if (idOrName instanceof Integer) {
            moon = moonDao.readMoon((Integer) idOrName);
        } else if (idOrName instanceof String) {
            moon = moonDao.readMoon((String) idOrName);
        } else {
            throw new MoonFail("Identifier must be an Integer or String");
        }
        if(moon.isPresent()) {
            return moon.get();
        } else {
            throw new MoonFail("Moon not found");
        }
    }

    @Override
    public List<Moon> selectAllMoons() {
        return moonDao.readAllMoons();
    }

    @Override
    public List<Moon> selectByPlanet(int planetId) {
        return moonDao.readMoonsByPlanet(planetId);
    }

    @Override
    public Moon updateMoon(Moon moon) {
        Pattern p = Pattern.compile("^[\\w\\-\\s]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(moon.getMoonName());

        Optional<Moon> existingMoon = moonDao.readMoon(moon.getMoonId());
        if (existingMoon.isEmpty()) {
            throw new MoonFail("Moon not found, could not update");
        }
        if (moon.getMoonName().length() < 1 || moon.getMoonName().length() > 30 || !matcher.matches()) {
            throw new MoonFail("Invalid moon name");
        }

        if (moon.getImageData() != null && !moon.getImageData().startsWith("/9j/")
                && !moon.getImageData().startsWith("iVBORw0KGgo")) {
            throw new MoonFail("Invalid file type");
        }

        Optional<Moon> moonWithSameName = moonDao.readMoon(moon.getMoonName());
        if (moonWithSameName.isPresent() && moonWithSameName.get().getMoonId() != moon.getMoonId()) {
            throw new MoonFail("Invalid moon name");
        }

        List<Planet> planets = planetDao.readAllPlanets();
        boolean planetFound = false;
        for (Planet planet : planets) {
            if (planet.getPlanetId() == moon.getOwnerId()) planetFound = true;
        }
        if (!planetFound) throw new MoonFail("Invalid planet id");


        Optional<Moon> updatedMoon = moonDao.updateMoon(moon);
        if (updatedMoon.isPresent()) {
            return updatedMoon.get();
        } else {
            throw new MoonFail("Moon update failed, please try again");
        }
    }

    @Override
    public boolean deleteMoon(T idOrName) {
        boolean deleted;
        if (idOrName instanceof Integer) {
            deleted = moonDao.deleteMoon((int) idOrName);
        } else if (idOrName instanceof String) {
            deleted = moonDao.deleteMoon((String) idOrName);
        } else {
            throw new MoonFail("Invalid moon name");
        }
        if (deleted) {
            return true;
        } else {
            throw new MoonFail("Invalid moon name");
        }
    }

}
