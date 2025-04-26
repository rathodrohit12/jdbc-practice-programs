package com.imagehandler;
import com.dbconn.JdbcConnectionProvider;

import java.io.*;
import java.nio.file.*;
import java.sql.*;

public class ImageHandlerPath {
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    /**
     * Stores the image on the filesystem and saves its path in the database.
     *
     * @param imageName     The name of the image.
     * @param sourcePath    The path to the source image file.
     * @param destinationDir The directory to save the image file.
     */
    public void storeImagePath(String imageName, String sourcePath, String destinationDir) {
        String sql = "INSERT INTO ImagePaths (name, image_path) VALUES (?, ?)";
        try {
            // Ensure the destination directory exists
            File directory = new File(destinationDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Copy the image to the destination directory
            File sourceFile = new File(sourcePath);
            File destinationFile = new File(destinationDir, sourceFile.getName());
            Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Save the image path in the database
            connection = JdbcConnectionProvider.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, imageName);
            statement.setString(2, destinationFile.getAbsolutePath());
            statement.executeUpdate();

            System.out.println("Image stored successfully with path: " + destinationFile.getAbsolutePath());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    /**
     * Retrieves the image path from the database and prints it.
     *
     * @param imageId The ID of the image to retrieve.
     * @return The file path of the image.
     */
    public String retrieveImagePath(int imageId) {
        String sql = "SELECT name, image_path FROM ImagePaths WHERE id = ?";
        String imagePath = null;

        try {
            connection = JdbcConnectionProvider.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, imageId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                imagePath = resultSet.getString("image_path");
                System.out.println("Image Path Retrieved: " + imagePath);
            } else {
                System.out.println("No image found with ID: " + imageId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return imagePath;
    }

    /**
     * Utility method to close database resources.
     */
    private void closeResources() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Example main method to test the functionality.
     */
    public static void main(String[] args) {
        // Using Path handler
        ImageHandlerPath pathHandler = new ImageHandlerPath();

        // Store an image
        String sourcePath = "src/resources/images/src/test.png";
        String destinationDir = "src/resources/images/out";
        //pathHandler.storeImagePath("example_image", sourcePath, destinationDir);

        // Retrieve the image path
        pathHandler.retrieveImagePath(1);
    }





}
