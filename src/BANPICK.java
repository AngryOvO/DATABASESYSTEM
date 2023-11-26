import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class BANPICK {

    private ArrayList<String> championlist;
    private ArrayList<String> BlueBanlist;
    private ArrayList<String> RedBanlist;
    private ArrayList<String> BluePicklist;
    private ArrayList<String> RedPicklist;





    public void start(String team1, String team2)
    {
        Scanner scanner = new Scanner(System.in);
        // 블루팀 레드팀 선정
        String text1 = "블루 팀과 레드 팀을 무작위로 선정합니다.";
        texttyping texttyping = new texttyping(text1);
        texttyping.start();
        // 0~1 사이의 난수 생성
        double rand = Math.random();
        String BlueTeam = "";
        String RedTeam = "";
        if(rand < 0.5)
        {
            BlueTeam = team1;
            RedTeam = team2;
        }
        else
        {
            BlueTeam = team2;
            RedTeam = team1;
        }

        String nexttext = "PRESS ENTER TO NEXT";
        texttyping.changeText(nexttext);
        texttyping.start();
        scanner.nextLine();
        // 선정 결과 출력

        String text2 = "블루팀 : " + BlueTeam + "\n레드팀 : " + RedTeam + "\n";
        texttyping.changeText(text2);
        texttyping.start();

        //게임 시작
        String text3 = "\n\n게임을 시작합니다.\n\n";
        texttyping.changeText(text3);
        texttyping.start();

        // 챔피언 금지 단계
        String text4 = "\n\n[챔피언 금지 단계]\n\n";
        texttyping.changeText(text4);
        texttyping.start();

        // 블루팀 금지 챔피언 선정
        String text5 = "\n\n1.블루팀 벤\n\n";
        texttyping.changeText(text5);
        texttyping.start();

        // 인터페이스 출력
        maininterface(1,1);
        maininterface(1,1);
        maininterface(1,1);

        // 레드팀 금지 챔피언 선정
        String text6 = "\n\n2.레드팀 벤\n\n";
        texttyping.changeText(text6);
        texttyping.start();

        // 인터페이스 출력
        maininterface(1,2);
        maininterface(1,2);
        maininterface(1,2);

        // 챔피언 선택 단계
        String text7 = "\n\n[챔피언 선택 단계]\n\n";
        texttyping.changeText(text7);
        texttyping.start();

        // 블루팀 챔피언 선택
        String text8 = "\n\n1.블루팀 픽\n\n";
        texttyping.changeText(text8);
        texttyping.start();

        // 인터페이스 출력
        maininterface(2,1);

        // 레드팀 챔피언 선택
        String text9 = "\n\n2.레드팀 픽\n\n";
        texttyping.changeText(text9);
        texttyping.start();

        // 인터페이스 출력
        maininterface(2,2);
        maininterface(2,2);

        // 블루팀 챔피언 선택
        texttyping.changeText(text8);
        texttyping.start();

        maininterface(2,1);
        maininterface(2,1);

        // 레드팀 챔피언 선택
        texttyping.changeText(text9);
        texttyping.start();

        maininterface(2,2);

        // 챔피언 금지 단계
        String text10 = "\n\n[챔피언 금지 단계]\n\n";
        texttyping.changeText(text10);
        texttyping.start();

        // 블루팀 금지
        texttyping.changeText(text5);
        texttyping.start();

        maininterface(1,1);
        maininterface(1,1);

        // 레드팀 금지
        texttyping.changeText(text6);
        texttyping.start();

        maininterface(1,2);
        maininterface(1,2);

        // 챔피언 선택 단계
        String text11 = "\n\n[챔피언 선택 단계]\n\n";
        texttyping.changeText(text11);
        texttyping.start();

        // 레드팀 선택
        texttyping.changeText(text9);
        texttyping.start();

        maininterface(2,2);

        // 블루팀 선택
        texttyping.changeText(text8);
        texttyping.start();

        maininterface(2,1);
        maininterface(2,1);

        // 레드팀 선택

        texttyping.changeText(text9);
        texttyping.start();

        maininterface(2,2);

        // 결과 계산 및 출력
        String text12 = "\n\n[결과]\n\n";
        texttyping.changeText(text12);
        texttyping.start();

        calculate();
        showresult();
    }

    public void maininterface(int pickban, int team) // ban = 1, pick = 2 // team = 1 : blue, 2 : red
    {

        String text1 = "== 챔피언 리스트 보기 ==\n";
        texttyping texttyping = new texttyping(text1);
        texttyping.start();

        String text2 = "1. 이름 순\n2. 포지션 순\n3. 티어 순\n";
        texttyping.changeText(text2);
        texttyping.start();

        Scanner scanner = new Scanner(System.in);
        System.out.print("입력 : ");

        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("1")) {
            // 이름 순으로 정렬
            showchampionlist();
            String text3 = "1. 챔피언 선택\n2. 뒤로 가기\n";
            texttyping.changeText(text3);
            texttyping.start();
            System.out.print("입력 : ");
            String input2 = scanner.nextLine();
            if (input2.equalsIgnoreCase("1")) {
                // 챔피언 선택
                if(pickban == 1)
                {
                    String text4 = "금지할 챔피언의 이름을 입력해주세요.\n";
                    texttyping.changeText(text4);
                    texttyping.start();
                    System.out.print("입력 : ");
                    String pickchampion = scanner.nextLine();
                    ban(pickchampion, team);
                }
                else if(pickban == 2)
                {
                    pick();
                }
                else
                {
                    System.out.println("잘못된 입력입니다.\n");
                }
            } else if (input2.equalsIgnoreCase("2")) {
                // 뒤로 가기
                maininterface(pickban, team);
            } else {
                System.out.println("잘못된 입력입니다.\n");
            }
        } else if (input.equalsIgnoreCase("2")) {
            // 포지션 순으로 정렬
            showchampionlist();
            String text3 = "1. 챔피언 선택\n2. 뒤로 가기\n";
            texttyping.changeText(text3);
            texttyping.start();
            System.out.print("입력 : ");
            String input2 = scanner.nextLine();
            if (input2.equalsIgnoreCase("1")) {
                // 챔피언 선택
                if(pickban == 1)
                {
                    String text4 = "금지할 챔피언의 이름을 입력해주세요.\n";
                    texttyping.changeText(text4);
                    texttyping.start();
                    System.out.print("입력 : ");
                    String pickchampion = scanner.nextLine();
                    ban(pickchampion, team);
                }
                else if(pickban == 2)
                {
                    pick();
                }
                else
                {
                    System.out.println("잘못된 입력입니다.\n");
                }
            } else if (input2.equalsIgnoreCase("2")) {
                // 뒤로 가기
                maininterface(pickban, team);
            } else {
                System.out.println("잘못된 입력입니다.\n");
            }
        } else if (input.equalsIgnoreCase("3")) {
            // 티어 순으로 정렬
            showchampionlist();
            String text3 = "1. 챔피언 선택\n2. 뒤로 가기\n";
            texttyping.changeText(text3);
            texttyping.start();
            System.out.print("입력 : ");
            String input2 = scanner.nextLine();
            if (input2.equalsIgnoreCase("1")) {
                // 챔피언 선택
                if(pickban == 1)
                {
                    String text4 = "금지할 챔피언의 이름을 입력해주세요.\n";
                    texttyping.changeText(text4);
                    texttyping.start();
                    System.out.print("입력 : ");
                    String pickchampion = scanner.nextLine();
                    ban(pickchampion, team);
                }
                else if(pickban == 2)
                {
                    pick();
                }
                else
                {
                    System.out.println("잘못된 입력입니다.\n");
                }
            } else if (input2.equalsIgnoreCase("2")) {
                // 뒤로 가기
                maininterface(pickban, team);
            } else {
                System.out.println("잘못된 입력입니다.\n");
            }
        } else {
            System.out.println("잘못된 입력입니다.\n");
        }
    }
    public void showchampionlist()
    {
        DBConnect dbConnect = new DBConnect();

        String url = "jdbc:mysql://192.168.56.102:4567/WORDS";
        String username = "root";
        String password = "1234";

        dbConnect.connect(url, username, password);

        String query = "SELECT * FROM CHAMPION";
        ResultSet resultSet = dbConnect.getResult(query);
        System.out.println(resultSet);

    }

    public void ban(String pickchampion, int team)
    {
        if(team == 1)
        {
            BlueBanlist.add(pickchampion);
        }
        else if(team == 2)
        {
            RedBanlist.add(pickchampion);
        }
    }

    public void pick()
    {

    }

    public void calculate()
    {

    }

    public void showresult()
    {

    }
}
