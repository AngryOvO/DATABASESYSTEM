import java.sql.*;

public class DBConnect {
    private Connection connection;

    // 데이터베이스에 연결하는 메서드
    public void connect(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("데이터베이스 연결 성공!");
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 실패: " + e.getMessage());
        }
    }

    // 데이터베이스에 쿼리를 실행하는 메서드 (입력, 수정, 삭제 등)
    public void executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("쿼리 실행 완료: " + query);
        } catch (SQLException e) {
            System.out.println("쿼리 실행 실패: " + e.getMessage());
        }
    }

    // 데이터베이스에서 쿼리를 실행하고 결과를 반환하는 메서드 (조회 등)
    public ResultSet getResult(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("쿼리 실행 실패: " + e.getMessage());
        }
        return resultSet;
    }

    // 연결 해제 메서드
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("데이터베이스 연결 종료");
            } catch (SQLException e) {
                System.out.println("연결 종료 실패: " + e.getMessage());
            }
        }
    }
}
