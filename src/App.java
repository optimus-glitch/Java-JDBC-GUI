import java.sql.*;

public class App {
    final static String url = "jdbc:mysql://localhost:3306/students";
    final static String user = "root";
    final static String password = "carl";

    public static void main(String[] args) throws Exception {
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Users limit 10";
            resultSet = stmt.executeQuery(sql);
         
            System.out.println("\n");
            
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");            
                String email = resultSet.getString("email");          
                String gender = resultSet.getString("gender"); 
                
                System.out.println("[*] "+id+" "+fname+"-"+lname+" "+email+" "+gender);
            }
            
            System.out.println("\n");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            if(stmt != null) {
                stmt.close();
            }

            if(conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
