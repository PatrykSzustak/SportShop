import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args) {

        String jdbcUrl="jdbc:postgresql://localhost/solshop";
        String user= "postgres";
        String pass= "postgres";



        try {
            System.out.println("Connecting to database "+ jdbcUrl);
            Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
