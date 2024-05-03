import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void getAllCustomer() {
        Connection connection = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            connection = MySQLConnectionDB.getMySQLConnection();
            stm = connection.createStatement();
            String query = "SELECT * FROM customers";
            rs = stm.executeQuery(query);

            while (rs.next()) {
                System.out.println("===========");
                System.out.println("Mã khách hàng: " + rs.getInt("customers_id"));
                System.out.println("Tên: " + rs.getString("firt_name"));
                System.out.println("Họ: " + rs.getString("last_name"));
                System.out.println("Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("Đã xảy ra lỗi khi lấy danh sách khách hàng: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Đã xảy ra lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
    }

    public static void addCustomer(int customerId, String firstName, String lastName, String email) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = MySQLConnectionDB.getMySQLConnection();
            String query = "INSERT INTO customers (customers_id, firt_name, last_name, email) VALUES (?, ?, ?, ?)";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, customerId);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
            System.out.println("Thêm khách hàng thành công.");
        } catch (SQLException e) {
            System.out.println("Đã xảy ra lỗi khi thêm khách hàng: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Đã xảy ra lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
    }

    public static void updateCustomer(int customerId, String firstName, String lastName, String email) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = MySQLConnectionDB.getMySQLConnection();
            String query = "UPDATE customers SET firt_name = ?, last_name = ?, email = ? WHERE customer_id = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setInt(4, customerId);
            pstmt.executeUpdate();
            System.out.println("Cập nhật khách hàng thành công.");
        } catch (SQLException e) {
            System.out.println("Đã xảy ra lỗi khi cập nhật khách hàng: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Đã xảy ra lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
    }

    public static void deleteCustomer(int customerId) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = MySQLConnectionDB.getMySQLConnection();
            String query = "DELETE FROM customers WHERE customers_id = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, customerId);
            pstmt.executeUpdate();
            System.out.println("Xóa khách hàng thành công.");
        } catch (SQLException e) {
            System.out.println("Đã xảy ra lỗi khi xóa khách hàng: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Đã xảy ra lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("========= MENU =========");
            System.out.println("1. Hiển thị danh sách khách hàng");
            System.out.println("2. Thêm khách hàng mới");
            System.out.println("3. Cập nhật thông tin khách hàng");
            System.out.println("4. Xóa khách hàng");
            System.out.println("5. Thoát");
            System.out.print("Chọn một lựa chọn từ menu: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character from the buffer

            switch (choice) {
                case 1:
                    getAllCustomer();
                    break;
                case 2:
                    System.out.println("Nhập thông tin mới của khách hàng:");
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Clear the newline character from the buffer
                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    addCustomer(id, firstName, lastName, email);
                    break;
                case 3:
                    System.out.println("Nhập ID của khách hàng cần cập nhật:");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Clear the newline character from the buffer
                    System.out.println("Nhập thông tin cập nhật:");
                    System.out.print("First Name: ");
                    String updateFirstName = scanner.nextLine();
                    System.out.print("Last Name: ");
                    String updateLastName = scanner.nextLine();
                    System.out.print("Email: ");
                    String updateEmail = scanner.nextLine();
                    updateCustomer(updateId, updateFirstName, updateLastName, updateEmail);
                    break;
                case 4:
                    System.out.println("Nhập ID của khách hàng cần xóa:");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine(); // Clear the newline character from the buffer
                    deleteCustomer(deleteId);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
            }
        }

        System.out.println("Ứng dụng đã kết thúc.");
    }


}
