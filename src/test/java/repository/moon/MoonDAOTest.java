package repository.moon;

import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import org.junit.Before;
import utility.Setup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class MoonDAOTest {


    protected MoonDao moonDao;

    @Before
    public void setup(){
        moonDao = new MoonDaoImp();
        Setup.resetTestDatabase();
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
