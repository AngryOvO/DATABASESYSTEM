import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {


        Scanner scanner = new Scanner(System.in);

        String url = "jdbc:mysql://192.168.56.102:4567/WORDS";
        String username = "root";
        String password = "1234";



        try(Connection con = DriverManager.getConnection(url, username, password)) {
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

            while(true)
            {
                //메인 메뉴
                String text1 = "1. 게임 시작\n2. 팀 목록 보기\n3. 챔피언 목록 보기\n4. 설정\n5. 종료\n";
                texttyping texttyping = new texttyping(text1);
                texttyping.start();

                System.out.print("입력 : ");
                String input = scanner.nextLine();

                if(input.equalsIgnoreCase("1"))
                {
                    texttyping.changeText("2023 LOL WORLDS에 오신 것을 환영합니다!\n\n");
                    texttyping.start();

                    String text2 = "첫 번째 감독님의 이름은 무엇인가요?\n";
                    texttyping.changeText(text2);
                    texttyping.start();



                    System.out.print("입력 : ");
                    String name = scanner.nextLine();

                    String text4 = "감독님의 팀을 선택해주세요!\n";
                    texttyping.changeText(text4);
                    texttyping.start();

                    Team team = new Team();

                    int currentImageIndex = 0;
                    int player1teamindex = 0;

                    while (true) {
                        // 현재 이미지 출력
                        team.printTeam(currentImageIndex);

                        // 방향키 입력 받기
                        System.out.println("이전 팀 : 1\n다음 팀 : 2\n팀 선택 : 3");
                        String input2 = scanner.nextLine();

                        if (input2.equalsIgnoreCase("1")) {
                            // 다음 이미지로 이동
                            currentImageIndex = (currentImageIndex + 1) % team.TEAMS.length;
                        } else if (input2.equalsIgnoreCase("2")) {
                            // 이전 이미지로 이동
                            currentImageIndex = (currentImageIndex - 1 + team.TEAMS.length) % team.TEAMS.length;
                        } else if (input2.equalsIgnoreCase("3")) {
                            player1teamindex = currentImageIndex;
                            PLAYER player1 = new PLAYER(name, team.TEAM_VAL[currentImageIndex] , currentImageIndex);
                            break;
                        } else {
                            System.out.println("잘못된 입력입니다. 방향키(← or →) 또는 Enter를 눌러주세요.");
                        }
                    }

                    String text3 = "두 번째 감독님의 이름은 무엇인가요?\n";
                    texttyping.changeText(text3);
                    texttyping.start();

                    System.out.print("입력 : ");
                    String name2 = scanner.nextLine();

                    String text5 = "감독님의 팀을 선택해주세요!\n";
                    texttyping.changeText(text5);
                    texttyping.start();

                    int currentImageIndex2 = 0;

                    while (true) {


                        // 현재 이미지 출력
                        team.printTeam(currentImageIndex2);

                        // 방향키 입력 받기
                        System.out.println("이전 팀 : 1\n다음 팀 : 2\n팀 선택 : 3");
                        String input4 = scanner.nextLine();

                        if (input4.equalsIgnoreCase("1"))
                        {
                            // 다음 이미지로 이동
                            currentImageIndex2 = (currentImageIndex2 + 1) % team.TEAMS.length;

                        }
                        else if (input4.equalsIgnoreCase("2"))
                        {
                            // 이전 이미지로 이동
                            currentImageIndex2 = (currentImageIndex2 - 1 + team.TEAMS.length) % team.TEAMS.length;
                        }
                        else if (input4.equalsIgnoreCase("3"))
                        {
                            if(player1teamindex == currentImageIndex2)
                            {
                                System.out.println("이미 선택된 팀입니다. 다른 팀을 선택해주세요.");
                                continue;
                            }
                            else
                            {
                                PLAYER player2 = new PLAYER(name2, team.TEAM_VAL[currentImageIndex2] , currentImageIndex2);
                                break;
                            }

                        }
                        else
                        {
                            System.out.println("잘못된 입력입니다. 방향키(← or →) 또는 Enter를 눌러주세요.");
                        }
                    }

                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

                    String backgroundSound = "(음향 효과와 함께)\n[배경음: 전투의 소리와 환호성이 울려 퍼진다. 강렬한 음악이 흐른다.]\n\n(소리 효과가 사라지고 조용해진다.)\n\n";
                    texttyping.changeText(backgroundSound);
                    texttyping.start();

                    String narration = "나레이션:\n\"환영합니다, 여러분. 여기는 현실과 무궁무진한 상상력이 만나는 곳입니다. 여기서 우리는 놀라운 모험을 시작하게 될 겁니다.\"\n\n";
                    texttyping.changeText(narration);
                    texttyping.start();

                    String commentator = "해설자:\n\"안녕하세요, 여러분! 오늘은 전설적인 대결의 시작을 알리는 특별한 순간입니다. 이곳은 어느 전설적인 전장일까요?\"\n\n";
                    texttyping.changeText(commentator);
                    texttyping.start();

                    String worldChampionship = "나레이션:\n\"이곳은 '월드 챔피언십'의 현장입니다. 이곳에서 우리는 히어로들의 충돌과 전쟁을 목격하게 될 것입니다. 그리고 이 모든 것은 여러분의 손에 달렸습니다.\"\n\n";
                    texttyping.changeText(worldChampionship);
                    texttyping.start();

                    String leadHeroes = "나레이션:\n\"여러분이 선택한 영웅들을 이끌고, 전략을 세우며, 미래를 결정하세요. 당신이 선택한 영웅은 이 승부의 전설이 될 것입니다.\"\n\n";
                    texttyping.changeText(leadHeroes);
                    texttyping.start();

                    String beginningLegend = "나레이션:\n\"그리고 이제, 새로운 전설의 서막이 올라갑니다. 모든 것이 시작됩니다. 지금부터, 당신은 이야기의 주인공입니다. 모든 선택은 당신의 손에 달려 있습니다. 지금, 우리의 모험을 시작합시다!\"\n\n";
                    texttyping.changeText(beginningLegend);
                    texttyping.start();

                    System.out.println("PRESS ENTER TO NEXT");
                    scanner.nextLine();


                    String text7 = "===== 1. 스위스 스테이지 설명 보기=====\n===== 2. 넘기기=====\n";
                    texttyping.changeText(text7);
                    texttyping.start();

                    System.out.print("입력 : ");
                    String input3 = scanner.nextLine();

                    if(input3.equalsIgnoreCase("1"))
                    {
                        String text8 = "스위스 스테이지는 16개의 팀이 시드 순위가 같은 팀을 기준으로 처음 조를 배정합니다.\n" + "1번 시드 조는 4번 시드의 조와 상대하고\n" + "2번 시드 조는 3번 시드의 조와 상대합니다.\n";
                        text8 = text8 + "이후 승패 여부에 따라 같은 전적을 가진 팀끼리 새 조를 구성합니다.\n" + "이 과정에서 3패를 하기 전 3승을 먼저 한 상위 8개 팀이 토너먼트 리그에 진입합니다.\n";
                        texttyping.changeText(text8);
                        texttyping.start();

                        System.out.println("PRESS ENTER TO NEXT");
                        scanner.nextLine();
                    }

                    String text9 = "스위스 스테이지 조 추첨\n";
                    texttyping.changeText(text9);
                    texttyping.start();

                    int[] seed1 = {1,5,9,13};
                    int[] seed2 = {2,6,10,14};
                    int[] seed3 = {3,7,11,15};
                    int[] seed4 = {4,8,12,16};

                    shuffleArray(seed1);
                    shuffleArray(seed2);
                    shuffleArray(seed3);
                    shuffleArray(seed4);

                    // 1번 시드와 4번시드에서 한 팀 씩 뽑아 매칭
                    int[] match1 = {seed1[0], seed4[0]};
                    int[] match2 = {seed1[1], seed4[1]};
                    int[] match3 = {seed1[2], seed4[2]};
                    int[] match4 = {seed1[3], seed4[3]};
                    int[] match5 = {seed2[0], seed3[0]};
                    int[] match6 = {seed2[1], seed3[1]};
                    int[] match7 = {seed2[2], seed3[2]};
                    int[] match8 = {seed2[3], seed3[3]};

                    // 대진표 생성
                    int[][] bracket = {match1, match2, match3, match4, match5, match6, match7, match8};
                    //대진표 출력
                    for(int i = 0; i < bracket.length; i++)
                    {
                        String text10 = "매치 " + (i+1) + " : " + team.getTEAM_VAL(bracket[i][0]) + " vs " + team.getTEAM_VAL(bracket[i][1]) + "\n";
                        texttyping.changeText(text10);
                        texttyping.start();
                    }

                    for(int i = 0; i <1 ; i++) //bracket.length
                    {
                        Integer a = i+1;

                        String text11 = "\n" + a.toString() + "번 경기.\n";
                        texttyping.changeText(text11);
                        texttyping.start();

                        String text12 = team.getTEAM_VAL(bracket[i][0]) + " vs " + team.getTEAM_VAL(bracket[i][1]) + "\n";
                        texttyping.changeText(text12);
                        texttyping.start();
                        System.out.println(bracket[i][0] + " vs " + bracket[i][1]);

                        LocalDate date = LocalDate.now();
                        //date to string
                        String date2 = date.toString();
                        String query = "INSERT INTO GAMERESULT (resultnum, gamedate, firstteamid, secondteamid) VALUES (?, ?, ?, ?)";
                        Statement statement = con.createStatement();
                        PreparedStatement preparedStatement = con.prepareStatement(query);
                        preparedStatement.setInt(1, i+1);
                        preparedStatement.setString(2, date2);
                        preparedStatement.setInt(3, bracket[i][0]);
                        preparedStatement.setInt(4, bracket[i][1]);
                        preparedStatement.executeUpdate();

                        String query2 = "INSERT INTO MATCHING (matchnum, resultid) VALUE (?,?)";
                        Statement statement2 = con.createStatement();
                        PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                        preparedStatement2.setInt(1, i+1);
                        preparedStatement2.setInt(2, i+1);
                        preparedStatement2.executeUpdate();

                        BANPICK banpick = new BANPICK();
                        banpick.start(bracket[i][0], bracket[i][1], a);
                        String query4 = "UPDATE MATCHING SET setwinteamid = ? WHERE (matchnum = " + a.toString() +");";
                        Statement statement4 = con.createStatement();
                        PreparedStatement preparedStatement4 = con.prepareStatement(query4);
                        preparedStatement4.setInt(1, banpick.getWinnerteam());
                        preparedStatement4.executeUpdate();


                        String query3 = "UPDATE GAMERESULT SET winnerid = ? WHERE (resultnum = " + a.toString() +");";
                        Statement statement3 = con.createStatement();
                        PreparedStatement preparedStatement3 = con.prepareStatement(query3);
                        preparedStatement3.setInt(1, banpick.getWinnerteam());
                        preparedStatement3.executeUpdate();


                    }






                }
                else if(input.equalsIgnoreCase("2"))
                {
                    //DB에서 팀 목록 출력
                    String query = "SELECT * FROM TEAMS;";
                    Statement statement = con.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    System.out.printf("%-10s %-15s %-15s %-10s %-10s\n", "팀번호", "팀이름", "감독", "지역", "시드번호");
                    while(resultSet.next())
                    {
                        int teamnum = resultSet.getInt("teamnum");
                        String teamname = resultSet.getString("teamname");
                        String headcoachname = resultSet.getString("headcoachname");
                        String region = resultSet.getString("region");
                        int seednum = resultSet.getInt("seednum");
                        System.out.printf("%-10s   %-15s   %-15s   %-10s   %-10s\n", teamnum, teamname, headcoachname, region, seednum);
                    }

                    //선수 목록을 보고싶은 팀 선택
                    String text11 = "선수 목록을 보고싶은 팀번호를 적어주세요!\n";
                    texttyping.changeText(text11);
                    texttyping.start();

                    System.out.print("입력 : ");
                    String input2 = scanner.nextLine();


                    String query2 = "SELECT * FROM TEAMS WHERE teamnum = ?";
                    PreparedStatement statement2 = con.prepareStatement(query2);
                    statement2.setString(1, input2);
                    ResultSet resultSet2 = statement2.executeQuery();
                    System.out.printf("%-10s %-15s %-15s %-10s %-10s\n", "팀번호", "팀이름", "감독", "지역", "시드번호");
                    while(resultSet2.next())
                    {
                        int teamnum = resultSet2.getInt("teamnum");
                        String teamname = resultSet2.getString("teamname");
                        String headcoachname = resultSet2.getString("headcoachname");
                        String region = resultSet2.getString("region");
                        int seednum = resultSet2.getInt("seednum");
                        System.out.printf("%-10s   %-15s   %-15s   %-10s   %-10s\n\n", teamnum, teamname, headcoachname, region, seednum);
                    }




                    String query3 = "SELECT * FROM PLAYERS WHERE teamnum = ?";
                    PreparedStatement statement3 = con.prepareStatement(query3);
                    statement3.setString(1, input2);
                    ResultSet resultSet3 = statement3.executeQuery();
                    System.out.printf("%-10s %-15s %-15s %-10s\n", "선수번호", "닉네임", "포지션", "국적");
                    while (resultSet3.next())
                    {
                        int playernum = resultSet3.getInt("playerid");
                        String nickname = resultSet3.getString("nickname");
                        String position = resultSet3.getString("position");
                        String region = resultSet3.getString("nationality");
                        System.out.printf("%-10s   %-15s   %-15s   %-10s\n", playernum, nickname, position, region);
                    }

                }
                else if(input.equalsIgnoreCase("3"))
                {
                    //이름 순 or 티어 순 or 포지션 순 선택
                    String text12 = "1. 이름 순으로 보기\n2. 티어 순으로 보기\n3. 포지션 순으로 보기\n";
                    texttyping.changeText(text12);
                    texttyping.start();

                    System.out.print("입력 : ");
                    String input2 = scanner.nextLine();

                    if(input2.equalsIgnoreCase("1"))
                    {
                        //이름 순으로 정렬
                        String query = "SELECT * FROM CHAMPION ORDER BY championname;";
                        Statement statement = con.createStatement();
                        ResultSet resultSet = statement.executeQuery(query);

                        // 출력
                        System.out.printf("%-10s %-15s %-15s %-10s %-10s %-10s\n", "번호", "챔피언 이름", "포지션", "티어", "카운터 챔피언번호", "상대하기 쉬운 챔피언 번호");
                        resultSet.next();
                        while(resultSet.next())
                        {
                            int championid = resultSet.getInt("championid");
                            String championname = resultSet.getString("championname");
                            String position = resultSet.getString("position");
                            String tier = resultSet.getString("tier");
                            int counterchampionid = resultSet.getInt("counterid");
                            int easychampionid = resultSet.getInt("easierid");
                            System.out.printf("%-10s   %-15s   %-15s   %-10s   %-10s   %-10s\n", championid, championname, position, tier, counterchampionid, easychampionid);
                        }

                    }
                    else if(input2.equalsIgnoreCase("2"))
                    {
                        //티어 순으로 정렬
                        String query = "SELECT * FROM CHAMPION ORDER BY tier;";
                        Statement statement = con.createStatement();
                        ResultSet resultSet = statement.executeQuery(query);

                        //출력
                        System.out.printf("%-10s %-15s %-15s %-10s %-10s %-10s\n", "번호", "챔피언 이름", "포지션", "티어", "카운터 챔피언번호", "상대하기 쉬운 챔피언 번호");
                        resultSet.next();
                        while(resultSet.next())
                        {
                            int championid = resultSet.getInt("championid");
                            String championname = resultSet.getString("championname");
                            String position = resultSet.getString("position");
                            String tier = resultSet.getString("tier");
                            int counterchampionid = resultSet.getInt("counterid");
                            int easychampionid = resultSet.getInt("easierid");
                            System.out.printf("%-10s   %-15s   %-15s   %-10s   %-10s   %-10s\n", championid, championname, position, tier, counterchampionid, easychampionid);
                        }
                    }
                    else if(input2.equalsIgnoreCase("3"))
                    {
                        //포지션 순으로 정렬
                        String query = "SELECT * FROM CHAMPION ORDER BY position;";
                        Statement statement = con.createStatement();
                        ResultSet resultSet = statement.executeQuery(query);

                        //출력
                        System.out.printf("%-10s %-15s %-15s %-10s %-10s %-10s\n", "번호", "챔피언 이름", "포지션", "티어", "카운터 챔피언번호", "상대하기 쉬운 챔피언 번호");
                        resultSet.next();
                        while(resultSet.next())
                        {
                            int championid = resultSet.getInt("championid");
                            String championname = resultSet.getString("championname");
                            String position = resultSet.getString("position");
                            String tier = resultSet.getString("tier");
                            int counterchampionid = resultSet.getInt("counterid");
                            int easychampionid = resultSet.getInt("easierid");
                            System.out.printf("%-10s   %-15s   %-15s   %-10s   %-10s   %-10s\n", championid, championname, position, tier, counterchampionid, easychampionid);
                        }
                    }
                    else
                    {
                        System.out.println("잘못된 입력입니다.\n");
                    }
                }
                else if(input.equalsIgnoreCase("4"))
                {
                    //팀 정보 수정 또는 챔피언 정보 수정
                    String text13 = "1. 팀 정보 수정\n2. 챔피언 정보 수정\n3. 선수 번호 수정\n4. 이전으로 돌아가기\n";
                    texttyping.changeText(text13);
                    texttyping.start();

                    System.out.print("입력 : ");
                    String input2 = scanner.nextLine();


                    if(input2.equalsIgnoreCase("1"))
                    {
                        UpdateTeamInfo updateTeamInfo = new UpdateTeamInfo();
                    }
                    else if(input2.equalsIgnoreCase("2"))
                    {
                        UpdateChampionInfo updateChampionInfo = new UpdateChampionInfo();
                    }
                    else if(input2.equalsIgnoreCase("3"))
                    {
                        UpdatePlayerInfo updatePlayerInfo = new UpdatePlayerInfo();
                    }
                    else
                    {
                        System.out.println("잘못된 입력입니다.\n");
                    }
                }
                else if (input.equalsIgnoreCase("5"))
                {
                    System.out.println("게임을 종료합니다.");
                    break;
                }
                else
                {
                    System.out.println("잘못된 입력입니다.\n");
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public static void shuffleArray(int[] array) {
        Collections.shuffle(Arrays.asList(array));
    }

    static class UpdateTeamInfo {
        public UpdateTeamInfo() {
            try {
                // MySQL 연결
                Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/WORDS", "root", "1234");

                // 사용자로부터 입력 받기
                Scanner scanner = new Scanner(System.in);
                System.out.print("수정할 팀 번호를 입력하세요: ");
                int teamNumToUpdate = scanner.nextInt();

                System.out.print("새로운 팀 이름을 입력하세요: ");
                String updatedTeamName = scanner.next();

                System.out.print("새로운 감독 이름을 입력하세요: ");
                String updatedCoachName = scanner.next();

                System.out.print("새로운 지역을 입력하세요: ");
                String updatedRegion = scanner.next();

                System.out.print("새로운 시드 번호를 입력하세요: ");
                int updatedSeedNum = scanner.nextInt();

                // SQL 쿼리 작성
                String updateQuery = "UPDATE TEAMS SET teamname = ?, headcoachname = ?, region = ?, seednum = ? WHERE teamnum = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
                preparedStatement.setString(1, updatedTeamName);
                preparedStatement.setString(2, updatedCoachName);
                preparedStatement.setString(3, updatedRegion);
                preparedStatement.setInt(4, updatedSeedNum);
                preparedStatement.setInt(5, teamNumToUpdate);

                // 쿼리 실행
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("팀 정보가 업데이트되었습니다.");
                } else {
                    System.out.println("해당 팀 번호를 찾을 수 없습니다.");
                }

                // 연결 종료
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static class UpdatePlayerInfo
    {
        public UpdatePlayerInfo()
        {
            try {

                Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/WORDS", "root", "1234");

                Scanner scanner = new Scanner(System.in);
                System.out.print("수정할 선수 번호를 입력하세요: ");
                int playerNumToUpdate = scanner.nextInt();

                System.out.print("새로운 선수 닉네임을 입력하세요: ");
                String updatedPlayerName = scanner.next();

                System.out.print("새로운 포지션을 입력하세요: ");
                String updatedPosition = scanner.next();

                System.out.print("새로운 국적을 입력하세요: ");
                String updatedNationality = scanner.next();

                System.out.print("새로운 팀 번호를 입력하세요: ");
                int updatedTeamNum = scanner.nextInt();

                // SQL 쿼리 작성
                String updateQuery = "UPDATE PLAYERS SET nickname = ?,  position = ?, nationality = ?, teamnum = ? WHERE playerid = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
                preparedStatement.setString(1, updatedPlayerName);
                preparedStatement.setString(2, updatedPosition);
                preparedStatement.setString(3, updatedNationality);
                preparedStatement.setInt(4, updatedTeamNum);
                preparedStatement.setInt(5, playerNumToUpdate);

                // 쿼리 실행
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("선수 정보가 업데이트되었습니다.");
                } else {
                    System.out.println("해당 선수 번호를 찾을 수 없습니다.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class UpdateChampionInfo
    {
        public UpdateChampionInfo()
        {
            try {
                // MySQL 연결
                Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/WORDS", "root", "1234");

                // 사용자로부터 입력 받기
                Scanner scanner = new Scanner(System.in);
                System.out.print("수정할 챔피언 번호를 입력하세요: ");
                int championNumToUpdate = scanner.nextInt();

                System.out.print("새로운 챔피언 이름을 입력하세요: ");
                String updatedChampionName = scanner.next();

                System.out.print("새로운 포지션을 입력하세요: ");
                String updatedPosition = scanner.next();

                System.out.print("새로운 티어를 입력하세요: ");
                String updatedTier = scanner.next();

                System.out.print("새로운 카운터 챔피언 번호를 입력하세요: ");
                int updatedCounterChampionNum = scanner.nextInt();

                System.out.print("새로운 상대하기 쉬운 챔피언 번호를 입력하세요: ");
                int updatedEasyChampionNum = scanner.nextInt();

                // SQL 쿼리 작성
                String updateQuery = "UPDATE CHAMPION SET championname = ?, position = ?, tier = ?, counterid = ?, easierid = ? WHERE championid = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
                preparedStatement.setString(1, updatedChampionName);
                preparedStatement.setString(2, updatedPosition);
                preparedStatement.setString(3, updatedTier);
                preparedStatement.setInt(4, updatedCounterChampionNum);
                preparedStatement.setInt(5, updatedEasyChampionNum);
                preparedStatement.setInt(6, championNumToUpdate);

                // 쿼리 실행
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("챔피언 정보가 업데이트되었습니다.");
                } else {
                    System.out.println("해당 챔피언 번호를 찾을 수 없습니다.");
                }

                // 연결 종료
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}