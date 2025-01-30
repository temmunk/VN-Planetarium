package com.revature.planetarium.repository.moon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.utility.DatabaseConnector;

public class MoonDaoImp implements MoonDao {

    @Override
    public Optional<Moon> createMoon(Moon moon) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO moons (name, myPlanetId, image) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, moon.getMoonName());
            stmt.setInt(2, moon.getOwnerId());
            stmt.setBytes(3, moon.imageDataAsByteArray());

            Pattern p = Pattern.compile(
                    "^[\\w\\-\\s]+$", Pattern.CASE_INSENSITIVE);
            //this regex pattern allows alphanumeric characters, dashes, underscores and spaces
            Matcher m=p.matcher(moon.getMoonName());
            boolean b=m.matches();

            if (moon.getImageData() != null) {
                if (!moon.getImageData().startsWith("/9j/") && !moon.getImageData().startsWith("iVBORw0KGgo")) {
                    //Jpg images encoded in base64 usually start with "/9j/" and png start with "iVBORw0KGgo"
                    throw new MoonFail("Invalid file type");
                }
            }

            if (moon.getMoonName().length() < 1 || moon.getMoonName().length() > 30) {
                throw new MoonFail("Invalid moon name");
            }
            if (!b) {
                throw new MoonFail("Invalid moon name");
            }
            Optional<Moon> existingMoon = readMoon(moon.getMoonName());
            if(existingMoon.isPresent()) {
                throw new MoonFail("Invalid moon name");
            }



            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()){
                if (rs.next()) {
                    int newMoonId = rs.getInt(1);
                    moon.setMoonId(newMoonId);
                    return Optional.of(moon);
                }
            }
        } catch (Exception e) {

            if (e.getMessage().contains("moons_myplanetid_fkey")){
                throw new MoonFail("Invalid planet ID");
            }
            throw new MoonFail(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Moon> readMoon(int id) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM moons WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Moon moon = new Moon();
                moon.setMoonId(rs.getInt("id"));
                moon.setMoonName(rs.getString("name"));
                moon.setOwnerId(rs.getInt("myPlanetId"));
                byte[] byteImageData = rs.getBytes("image");
                if (byteImageData != null){
                    String base64ImageData = Base64.getEncoder().encodeToString(byteImageData);
                    moon.setImageData(base64ImageData);
                }
                return Optional.of(moon);
            }
        } catch (SQLException e) {

            throw new MoonFail(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Moon> readMoon(String name) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM moons WHERE name = ?")) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Moon moon = new Moon();
                moon.setMoonId(rs.getInt("id"));
                moon.setMoonName(rs.getString("name"));
                moon.setOwnerId(rs.getInt("myPlanetId"));
                byte[] byteImageData = rs.getBytes("image");
                if (byteImageData != null){
                    String base64ImageData = Base64.getEncoder().encodeToString(byteImageData);
                    moon.setImageData(base64ImageData);
                }
                return Optional.of(moon);
            }
        } catch (SQLException e) {

            throw new MoonFail(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Moon> readAllMoons() {
        List<Moon> moons = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM moons")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Moon moon = new Moon();
                moon.setMoonId(rs.getInt("id"));
                moon.setMoonName(rs.getString("name"));
                moon.setOwnerId(rs.getInt("myPlanetId"));
                byte[] byteImageData = rs.getBytes("image");
                if (byteImageData != null){
                    String base64ImageData = Base64.getEncoder().encodeToString(byteImageData);
                    moon.setImageData(base64ImageData);
                }
                moons.add(moon);
            }
        } catch (SQLException e) {

            throw new MoonFail(e.getMessage());
        }
        return moons;
    }

    @Override
    public List<Moon> readMoonsByPlanet(int planetId) {
        List<Moon> moons = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM moons WHERE myPlanetId = ?")) {
            stmt.setInt(1, planetId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Moon moon = new Moon();
                moon.setMoonId(rs.getInt("id"));
                moon.setMoonName(rs.getString("name"));
                moon.setOwnerId(rs.getInt("myPlanetId"));
                byte[] byteImageData = rs.getBytes("image");
                if (byteImageData != null){
                    String base64ImageData = Base64.getEncoder().encodeToString(byteImageData);
                    moon.setImageData(base64ImageData);
                }
                moons.add(moon);
            }
        } catch (SQLException e) {

            throw new MoonFail(e.getMessage());
        }
        return moons;
    }

    @Override
    public Optional<Moon> updateMoon(Moon moon) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE moons SET name = ?, myPlanetId = ?, image = ? WHERE id = ?")) {
            stmt.setString(1, moon.getMoonName());
            stmt.setInt(2, moon.getOwnerId());
            stmt.setBytes(3, moon.imageDataAsByteArray());
            stmt.setInt(4, moon.getMoonId());

            Optional<Moon> existingMoon = readMoon(moon.getMoonName());
            if(existingMoon.isPresent()) {
                throw new MoonFail("Invalid moon name");
            }

            if (moon.getImageData() != null) {
                if (!moon.getImageData().startsWith("/9j/") && !moon.getImageData().startsWith("iVBORw0KGgo")) {
                    throw new MoonFail("Invalid file type");
                }
            }
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0 ? Optional.of(moon) : Optional.empty();
        } catch (SQLException e) {
            if (e.getMessage().contains("moons_myplanetid_fkey")) throw new MoonFail("Invalid planet id");
            throw new MoonFail("Invalid moon name");
        }
    }

    @Override
    public boolean deleteMoon(int id) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM moons WHERE id = ?")) {
            stmt.setInt(1, id);
            Optional<Moon> existingMoon = readMoon(id);
            if(existingMoon.isEmpty()) {
                throw new MoonFail("Invalid moon name");
            }

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {

            throw new MoonFail(e.getMessage());
        }
    }

    @Override
    public boolean deleteMoon(String name) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM moons WHERE name = ?")) {
            stmt.setString(1, name);
            Optional<Moon> existingMoon = readMoon(name);
            if(existingMoon.isEmpty()) {
                throw new MoonFail("Invalid moon name");
            }
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {
            throw new MoonFail(e.getMessage());
        }
    }
    
}
