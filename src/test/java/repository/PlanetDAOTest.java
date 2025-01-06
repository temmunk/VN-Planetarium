package repository;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utility.Setup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class PlanetDAOTest {

    protected PlanetDao planetDao;

    @Before
    public void setup() {
        Setup.resetTestDatabase();
        planetDao = new PlanetDaoImp();
    }

    protected String getImageData(String filePath) {
        String absolutePath = Paths.get(filePath).toAbsolutePath().toString();
        String data = "";
        try {
            byte[] fileBytes = Files.readAllBytes(Path.of(absolutePath));
            data = Base64.getEncoder().encodeToString(fileBytes);
        } catch (IOException e) {
            System.out.println("Error getting image data: " + e.getMessage());
        }

        return data;
    }
}
