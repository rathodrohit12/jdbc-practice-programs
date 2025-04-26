package com.imagehandler;

import com.dbconn.JdbcConnectionProvider;

import java.io.*;
import java.sql.*;

public class ImageHandlerBlob {
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    /**
     * Storing Images as Binary Data (BLOB).
     *
     * @param imageName The name of the image.
     * @param imagePath The path to the image file.
     */
    public void storeImageAsBlob(String imageName, String imagePath) {
        String sql = "INSERT INTO Images (name, image_data) VALUES (?, ?)";
        try {
            connection = JdbcConnectionProvider.getConn();
            statement = connection.prepareStatement(sql);

            // Reading the image as a file stream
            FileInputStream inputStream = new FileInputStream(imagePath);

            statement.setString(1, imageName);
            statement.setBlob(2, inputStream);
            statement.executeUpdate();

            System.out.println("Image stored successfully as BLOB!");
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    /**
     * Retrieves an image from the database as a BLOB and saves it to a file.
     *
     * @param imageId    The ID of the image to retrieve.
     * @param outputPath The path to save the retrieved image file.
     */
    public void retrieveImageAsBlob(int imageId, String outputPath) {
        String sql = "SELECT name, image_data FROM Images WHERE id = ?";
        try {
            connection = JdbcConnectionProvider.getConn();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, imageId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String imageName = resultSet.getString("name");
                InputStream inputStream = resultSet.getBinaryStream("image_data");

                // Saving the image to the specified output path
                File outputFile = new File(outputPath, imageName + ".jpg");
                FileOutputStream outputStream = new FileOutputStream(outputFile);

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                outputStream.close();
                System.out.println("Image retrieved and saved to: " + outputFile.getAbsolutePath());
            } else {
                System.out.println("No image found with ID: " + imageId);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
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
        // Using BLOB handler
        ImageHandlerBlob handler = new ImageHandlerBlob();

        // Storing an image
        String imagePath = "src/resources/images/src/test.png";
//        handler.storeImageAsBlob("example_image", imagePath);

        // Retrieving the same image
        handler.retrieveImageAsBlob(1, "src/resources/images/out");
    }
}
