package confluence.caloriecounter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:derby:memory:CalorieCounter;create=true";

public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database.");
                createTablesIfNotExist(conn);
            }
        } catch (SQLException e) {
            System.out.println("Error for connecting to database: " + e.getMessage());
        }
}

    public static Connection getConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            return DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("errorrr"+e.getMessage());
            JOptionPane.showMessageDialog(null, "Error bout connecting to database: " + e.getMessage(),
                    "Database Connection Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    static void createTablesIfNotExist(Connection conn) {
        try {
            if (!doesTableExist(conn, "FOODDATABASE")) {
                createFoodDatabaseTable(conn);
            } else {
                System.out.println("FoodDatabase table already exists.");
            }

            if (!doesTableExist(conn, "FOODEATENTODAY")) {
                createFoodEatenTodayTable(conn);
            } else {
                System.out.println("FoodEatenToday table already exists.");
            }

            if (!doesTableExist(conn, "MACROTARGETS")) {
                createMacroTargetsTable(conn);
            } else {
                System.out.println("MacroTargets table already exists.");
            }
        } catch (SQLException e) {
            System.out.println("Error checking/creating tables: " + e.getMessage());
        }
    }

    private static boolean doesTableExist(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData dbMetaData = conn.getMetaData();
        try (ResultSet rs = dbMetaData.getTables(null, null, tableName.toUpperCase(), null)) {
            return rs.next();
        }
    }

    private static void createFoodDatabaseTable(Connection conn) {
        String createFoodDatabaseTable = "CREATE TABLE FoodDatabase (" +
                                         "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                                         "name VARCHAR(100)," +
                                         "calories DOUBLE," +
                                         "protein DOUBLE," +
                                         "carbs DOUBLE)";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createFoodDatabaseTable);
            System.out.println("FoodDatabase table created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating FoodDatabase table: " + e.getMessage());
        }
    }

    private static void createFoodEatenTodayTable(Connection conn) {
        String createFoodEatenTodayTable = "CREATE TABLE FoodEatenToday (" +
                                           "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                                           "food_id INT," +
                                           "quantity DOUBLE," +
                                           "FOREIGN KEY (food_id) REFERENCES FoodDatabase(id))";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createFoodEatenTodayTable);
            System.out.println("FoodEatenToday table created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating FoodEatenToday table: " + e.getMessage());
        }
    }

    private static void createMacroTargetsTable(Connection conn) {
        String createMacroTargetsTable = "CREATE TABLE MacroTargets (" +
                                         "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                                         "target_calories DOUBLE," +
                                         "target_protein DOUBLE," +
                                         "target_carbs DOUBLE)";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createMacroTargetsTable);
            System.out.println("MacroTargets table created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating MacroTargets table: " + e.getMessage());
        }
    }
}
