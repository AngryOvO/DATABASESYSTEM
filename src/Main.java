import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        String url = "jdbc:mysql://192.168.56.102:4567/madang";
        String username = "root";
        String password = "1234";

        try (Connection con = DriverManager.getConnection(url, username, password) ) {
            String asciiArt =
                    "   ___           ___           ___           ___           ___           ___     \n" +
                            "/\\ \\  __/\\ \\   /\\  __`\\    /\\  _`\\     /\\ \\       /\\  _`\\    /\\  _`\\     \n" +
                            "\\ \\ \\/\\ \\ \\ \\  \\ \\ \\/\\ \\   \\ \\ \\L\\ \\   \\ \\ \\      \\ \\ \\/\\ \\  \\ \\,\\L\\_\\   \n" +
                            " \\ \\ \\ \\ \\ \\ \\  \\ \\ \\ \\ \\   \\ \\ ,  /    \\ \\ \\  __  \\ \\ \\ \\ \\  \\/_\\__ \\   \n" +
                            "  \\ \\ \\_/ \\_\\ \\  \\ \\ \\_\\ \\   \\ \\ \\\\ \\    \\ \\ \\L\\ \\  \\ \\ \\_\\ \\   /\\ \\L\\ \\ \n" +
                            "   \\ `\\___x___/   \\ \\_____\\   \\ \\_\\ \\_\\   \\ \\____/   \\ \\____/   \\ `\\____\\\n" +
                            "    '\\/__//__/     \\/_____/    \\/_/\\/ /    \\/___/     \\/___/     \\/_____/\n";
            System.out.println(asciiArt);
            System.out.println("=== LOL WORDS PICK GAME ===\n");
            System.out.println("PRESS ENTER TO START");
            scanner.nextLine();

            System.out.println("== 팀 선택 ==");
            Team team = new Team();

            int currentImageIndex = 0;

            while (true) {
                // 현재 이미지 출력
                team.printTeam(currentImageIndex);

                // 방향키 입력 받기
                System.out.println("이전 팀 : 1\n다음 팀 : 2\n팀 선택 : 3");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("1")) {
                    // 다음 이미지로 이동
                    currentImageIndex = (currentImageIndex + 1) % team.TEAMS.length;
                } else if (input.equalsIgnoreCase("2")) {
                    // 이전 이미지로 이동
                    currentImageIndex = (currentImageIndex - 1 + team.TEAMS.length) % team.TEAMS.length;
                } else if (input.equalsIgnoreCase("3")) {
                    // 엔터키로 선택 완료

                    break;
                } else {
                    System.out.println("잘못된 입력입니다. 방향키(← or →) 또는 Enter를 눌러주세요.");
                }
            }

            scanner.close();





        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void printDB(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Book");
        while (rs.next())
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
    }

    public static void insertDB(Connection con, Scanner scanner) throws SQLException {
        System.out.print("Enter the book bookid: ");
        String bookid = scanner.nextLine();
        System.out.print("Enter the book bookname: ");
        String bookname = scanner.nextLine();
        System.out.print("Enter the book publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter the book price: ");
        String price = scanner.nextLine();

        String sql = "INSERT INTO Book (bookid, bookname, publisher, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1,Integer.parseInt(bookid));
            pstmt.setString(2, bookname);
            pstmt.setString(3, publisher);
            pstmt.setInt(4,Integer.parseInt(price));
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Row inserted successfully.");
            } else {
                System.out.println("Failed to insert the row.");
            }
        }
    }

    public static void deleteDB(Connection con, Scanner scanner) throws SQLException {
        System.out.print("Enter the book ID to delete: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        String sql = "DELETE FROM Book WHERE bookid = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Row deleted successfully.");
            } else {
                System.out.println("No matching row found for deletion.");
            }
        }
    }

    public static void searchDB(Connection con, Scanner scanner) throws SQLException {
        System.out.print("Enter the search bookname: ");
        String keyword = scanner.nextLine();

        String sql = "SELECT * FROM Book WHERE bookname = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, keyword);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
        }
    }


}


