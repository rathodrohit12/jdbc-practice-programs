
# Steps to Connect Java Applications with Database

## Step 1: Import the Packages
```
import java.sql.*;
```

## Step 2: Load the Drivers Using the forName() Method **(Optional)**

`Class.forName("com.mysql.cj.jdbc.Driver");`

## Step 3: Register the Drivers Using DriverManager **(Optional)**
`DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());`

## Step 4: Establish a Connection Using the Connection Class Object

`Connection connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/yourDatabaseName", "username", "password");`

## Step 5: Create a Statement

`Statement statement = connection.createStatement();`

## Step 6: Execute the Query

`ResultSet resultSet = statement.executeQuery("SELECT * FROM yourTableName");`


## Step 7: Close the Connections
```
resultSet.close();
statement.close();
connection.close();
```

## Why Explicit Driver Loading is Unnecessary


Modern JDBC drivers include a file in their JAR under `META-INF/services/java.sql.Driver`
that specifies the fully qualified name of the driver class (`com.mysql.cj.jdbc.Driver` in this case). 
When your application calls ``DriverManager.getConnection()``, the DriverManager class uses 
the `ServiceLoader` mechanism to load and initialize the driver classes specified in these files.


1. **Call to DriverManager.getConnection(...)**
When you invoke a method like:

```
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306//yourDatabaseName", "username", "password");
```
The DriverManager class is asked to find an appropriate driver that can handle the given JDBC URL (jdbc:mysql://...).

2. **Static Initialization of DriverManager**
The DriverManager class includes a static initialization block that runs when the class is first loaded. This block is responsible for setting up the driver discovery mechanism:

```
static {
    loadInitialDrivers();
    println("JDBC DriverManager initialized");
}
```
#### Note:

This static block was found in Java 8. In the latest JDK, the name of the `loadInitialDrivers()` method has been changed to `ensureDriversInitialized()`.




3. **loadInitialDrivers() Calls ServiceLoader**
Inside the loadInitialDrivers() method, the ServiceLoader is used to discover driver implementations by scanning the META-INF/services/java.sql.Driver files available on the classpath.

```
private static void loadInitialDrivers() {
    // Use the ServiceLoader to load drivers
    ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);

    // Register each discovered driver
    for (Driver driver : loadedDrivers) {
        try {
            registerDriver(driver);
        } catch (SQLException ex) {
            throw new RuntimeException("Error registering driver!", ex);
        }
    }
}
```

4. **ServiceLoader Reads META-INF/services/java.sql.Driver**
When `ServiceLoader.load(Driver.class)` is called, the following happens:


The ServiceLoader looks for all files named `META-INF/services/java.sql.Driver` in the JARs available on the classpath.
Each file is read to find the fully qualified names of the driver classes (e.g., `com.mysql.cj.jdbc.Driver`).
For each class name found, ServiceLoader attempts to load the class into memory.

5. **Driver Class Initialization**
When the driver class (e.g., `com.mysql.cj.jdbc.Driver`) is loaded into memory, its static block is executed. In the MySQL JDBC driver, the static block registers the driver with the DriverManager:

```
static {
    try {
        DriverManager.registerDriver(new Driver());
    } catch (SQLException e) {
        throw new RuntimeException("Can't register driver!", e);
    }
}
```

The DriverManager.registerDriver() method adds the driver to its internal list of registered drivers. This list is then used to find an appropriate driver when DriverManager.getConnection() is called.
