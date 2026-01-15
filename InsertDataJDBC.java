package jdbcfirstprogram;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDataJDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/firstquery"; // replace with your DB name
        String user = "root"; // your DB username
        String password = "HOnest617*"; // your DB password

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. Connect to database
            conn = DriverManager.getConnection(url, user, password);

            // 2. Prepare SQL INSERT query including the ID
            String sql = "INSERT INTO student (id, name, age) VALUES (?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            // 3. Set values for placeholders
            pstmt.setInt(1, 6);           // id
            pstmt.setString(2, "James");  // name
            pstmt.setInt(3, 35);          // age
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Student with ID=6 added successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

            

	}

}
