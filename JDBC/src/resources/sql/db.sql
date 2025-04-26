use temp;

CREATE TABLE Images (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for each image
    name VARCHAR(255) NOT NULL,         -- Name of the image
    image_data LONGBLOB NOT NULL,       -- Binary data of the image
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Timestamp of when the image was uploaded
);


CREATE TABLE ImagePaths (
    id INT AUTO_INCREMENT PRIMARY KEY,       -- Unique identifier for each image
    name VARCHAR(255) NOT NULL,              -- Name of the image
    image_path VARCHAR(500) NOT NULL,        -- Path to the image file on the server
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Timestamp of when the image was added
);
