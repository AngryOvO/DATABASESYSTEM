import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;
import java.sql.*;


public class BANPICK {

    static class Champion
    {
        public String championname;
        public String position;

        int championid;

        public Champion()
        {
            this.championname = "";
            this.position = "";
            this.championid = 0;
        }
        public Champion(String a, String b, int c)
        {
            this.championname = a;
            this.position = b;
            this.championid = c;
        }

        public String getchampionname()
        {
            return championname;
        }
        public String getPosition()
        {
            return position;
        }
        public int getChampionid()
        {
            return championid;
        }
    }


    Connection con;
    Map<Champion, Boolean> championlist_top =  new HashMap<>();
    Map<Champion, Boolean> championlist_jungle =  new HashMap<>();
    Map<Champion, Boolean> championlist_mid =  new HashMap<>();
    Map<Champion, Boolean> championlist_adc =  new HashMap<>();
    Map<Champion, Boolean> championlist_support =  new HashMap<>();

    Champion team1banlist[] = new Champion[5];
    Champion team2banlist[] = new Champion[5];
    Champion team1picklist[] = new Champion[5];
    Champion team2picklist[] = new Champion[5];

    int team1tierpoint = 0;
    int team2tierpoint = 0;
    int team1counterpoint = 0;
    int team2counterpoint = 0;

    int winnerteam = 0;


    public void setWinnerteam(int winnerteam) {
        this.winnerteam = winnerteam;
    }

    public int getWinnerteam() {
        return winnerteam;
    }

    public void DBConnect() throws SQLException {
        String url = "jdbc:mysql://192.168.56.102:4567/WORDS";
        String username = "root";
        String password = "1234";


        con = DriverManager.getConnection(url, username, password);
    }

    public void setChampionList() throws SQLException
    {
        String sql = "SELECT championname, position, championid FROM CHAMPION WHERE position = 'TOP'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next())
        {
            Champion champion = new Champion(rs.getString("championname"), rs.getString("position"), rs.getInt("championid"));
            championlist_top.put(champion, false);
        }

        sql = "SELECT championname, position, championid FROM CHAMPION WHERE position = 'JUG'";
        st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next())
        {
            Champion champion = new Champion(rs.getString("championname"), rs.getString("position"), rs.getInt("championid"));
            championlist_jungle.put(champion, false);
        }

        sql = "SELECT championname, position, championid FROM CHAMPION WHERE position = 'MID'";
        st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next())
        {
            Champion champion = new Champion(rs.getString("championname"), rs.getString("position"), rs.getInt("championid"));
            championlist_mid.put(champion, false);
        }

        sql = "SELECT championname, position, championid FROM CHAMPION WHERE position = 'ADC'";
        st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next())
        {
            Champion champion = new Champion(rs.getString("championname"), rs.getString("position"), rs.getInt("championid"));
            championlist_adc.put(champion, false);
        }

        sql = "SELECT championname, position, championid FROM CHAMPION WHERE position = 'SUP'";
        st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next())
        {
            Champion champion = new Champion(rs.getString("championname"), rs.getString("position"), rs.getInt("championid"));
            championlist_support.put(champion, false);
        }
    }

    public void start(int team1, int team2, int gamenum) throws SQLException {

        DBConnect();
        Scanner scanner = new Scanner(System.in);
        Team team = new Team();
        setChampionList();

        for(int i = 0; i < 5; i++)
        {
            team1banlist[i] = new Champion();
            team2banlist[i] = new Champion();
            team1picklist[i] = new Champion();
            team2picklist[i] = new Champion();
        }


        // 블루팀 레드팀 선정
        String text1 = "블루 팀과 레드 팀을 무작위로 선정합니다.\n";
        texttyping texttyping = new texttyping(text1);
        texttyping.start();
        // 0~1 사이의 난수 생성
        double rand = Math.random();
        String BlueTeam = "";
        String RedTeam = "";

        if(rand < 0.5)
        {
            BlueTeam = team.getTEAM_VAL(team1);
            RedTeam = team.getTEAM_VAL(team2);
        }
        else
        {
            BlueTeam = team.getTEAM_VAL(team1);
            RedTeam = team.getTEAM_VAL(team2);
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


        // 인터페이스 출력
        String blueban1 = "\n\n블루팀 첫 번째 벤\n\n";
        texttyping.changeText(blueban1);
        texttyping.start();
        maininterface(1,1,1);
        updatebanpickindb(gamenum * 20 - 19, team1, gamenum, 1, 1, team1banlist[0].championid, 1);


        String redban1 = "\n\n레드팀 첫 번째 벤\n\n";
        texttyping.changeText(redban1);
        texttyping.start();
        maininterface(1,2,1);
        updatebanpickindb(gamenum * 20 - 18, team2, gamenum, 2, 1, team2banlist[0].championid, 1);


        String blueban2 = "\n\n블루팀 두 번째 벤\n\n";
        texttyping.changeText(blueban2);
        texttyping.start();
        maininterface(1,1,2);
        updatebanpickindb(gamenum * 20 - 17, team1, gamenum, 1, 2, team1banlist[1].championid, 1);

        String redban2 = "\n\n레드팀 두 번째 벤\n\n";
        texttyping.changeText(redban2);
        texttyping.start();
        maininterface(1,2,2);
        updatebanpickindb(gamenum * 20 - 16, team2, gamenum, 2, 2, team2banlist[1].championid, 1);

        String blueban3 = "\n\n블루팀 세 번째 벤\n\n";
        texttyping.changeText(blueban3);
        texttyping.start();
        maininterface(1,1,3);
        updatebanpickindb(gamenum * 20 - 15, team1, gamenum, 1, 3, team1banlist[2].championid, 1);

        String redban3 = "\n\n레드팀 세 번째 벤\n\n";
        texttyping.changeText(redban3);
        texttyping.start();
        maininterface(1,2,3);
        updatebanpickindb(gamenum * 20 - 14, team2, gamenum, 2, 3, team2banlist[2].championid, 1);






        // 챔피언 선택 단계
        String text7 = "\n\n[챔피언 선택 단계]\n\n";
        texttyping.changeText(text7);
        texttyping.start();



        // 인터페이스 출력
        String bluepick1 = "\n\n블루팀 1픽\n\n";
        texttyping.changeText(bluepick1);
        texttyping.start();
        maininterface(2,1,1);
        updatebanpickindb(gamenum * 20 - 13, team1, gamenum, 1, 1, team1picklist[0].championid, 2);



        // 인터페이스 출력
        String redpick1 = "\n\n레드팀 1픽\n\n";
        texttyping.changeText(redpick1);
        texttyping.start();
        maininterface(2,2,1);
        updatebanpickindb(gamenum * 20 - 12, team2, gamenum, 2, 1, team2picklist[0].championid, 2);
        String redpick2 = "\n\n레드팀 2픽\n\n";
        texttyping.changeText(redpick2);
        texttyping.start();
        maininterface(2,2,2);
        updatebanpickindb(gamenum * 20 - 11, team2, gamenum, 2, 2, team2picklist[1].championid, 2);




        String bluepick2 = "\n\n블루팀 2픽\n\n";
        texttyping.changeText(bluepick2);
        texttyping.start();
        maininterface(2,1,2);
        updatebanpickindb(gamenum * 20 - 10, team1, gamenum, 1, 2, team1picklist[1].championid, 2);
        String bluepick3 = "\n\n블루팀 3픽\n\n";
        texttyping.changeText(bluepick3);
        texttyping.start();
        maininterface(2,1,3);
        updatebanpickindb(gamenum * 20 - 9, team1, gamenum, 1, 3, team1picklist[2].championid, 2);



        String redpick3 = "\n\n레드팀 3픽\n\n";
        texttyping.changeText(redpick3);
        texttyping.start();
        maininterface(2,2,3);
        updatebanpickindb(gamenum * 20 - 8, team2, gamenum, 2, 3, team2picklist[2].championid, 2);

        // 챔피언 금지 단계
        String text10 = "\n\n[챔피언 금지 단계]\n\n";
        texttyping.changeText(text10);
        texttyping.start();




        String blueban4 = "\n\n블루팀 네 번째 벤\n\n";
        texttyping.changeText(blueban4);
        texttyping.start();
        maininterface(1,1,4);
        updatebanpickindb(gamenum * 20 - 7, team1, gamenum, 1, 4, team1banlist[3].championid, 1);

        String redban4 = "\n\n레드팀 네 번째 벤\n\n";
        texttyping.changeText(redban4);
        texttyping.start();
        maininterface(1,2,4);
        updatebanpickindb(gamenum * 20 - 6, team2, gamenum, 2, 4, team2banlist[3].championid, 1);

        String blueban5 = "\n\n블루팀 다섯 번째 벤\n\n";
        texttyping.changeText(blueban5);
        texttyping.start();
        maininterface(1,1,5);
        updatebanpickindb(gamenum * 20 - 5, team1, gamenum, 1, 5, team1banlist[4].championid, 1);

        String redban5 = "\n\n레드팀 다섯 번째 벤\n\n";
        texttyping.changeText(redban5);
        texttyping.start();
        maininterface(1,2,5);
        updatebanpickindb(gamenum * 20 - 4, team2, gamenum, 2, 5, team2banlist[4].championid, 1);

        // 챔피언 선택 단계
        String text11 = "\n\n[챔피언 선택 단계]\n\n";
        texttyping.changeText(text11);
        texttyping.start();



        String redpick4 = "\n\n레드 4픽\n\n";
        texttyping.changeText(redpick4);
        texttyping.start();
        maininterface(2,2,4);
        updatebanpickindb(gamenum * 20 - 3, team2, gamenum, 2, 4, team2picklist[3].championid, 2);



        String bluepick4 = "\n\n블루 4픽\n\n";
        texttyping.changeText(bluepick4);
        texttyping.start();
        maininterface(2,1,4);
        updatebanpickindb(gamenum * 20 - 2, team1, gamenum, 1, 4, team1picklist[3].championid, 2);
        String bluepick5 = "\n\n블루 5픽\n\n";
        texttyping.changeText(bluepick5);
        texttyping.start();
        maininterface(2,1,5);
        updatebanpickindb(gamenum * 20 - 1, team1, gamenum, 1, 5, team1picklist[4].championid, 2);



        String redpick5 = "\n\n레드 5픽\n\n";
        texttyping.changeText(redpick5);
        texttyping.start();
        maininterface(2,2,5);
        updatebanpickindb(gamenum * 20, team2, gamenum, 2, 5, team2picklist[4].championid, 2);

        // 결과 계산 및 출력
        String text12 = "\n\n[결과]\n\n";
        texttyping.changeText(text12);
        texttyping.start();

        calculate();

    }

    public void maininterface(int pickban, int team, int order) // ban = 1, pick = 2 // team = 1 : blue, 2 : red
    {
        Scanner scanner = new Scanner(System.in);

        while(true)
        {
            String text1 = "== 챔피언 리스트 보기 ==\n";
            texttyping texttyping = new texttyping(text1);
            texttyping.start();


            showchampionlist();
            System.out.println("\n == 현재 밴/픽 현황 ==\n");
            showresult();

            String text3 = "\n1. 챔피언 선택\n2. 뒤로 가기\n";
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
                    ban(pickchampion, team, order);
                }
                else if(pickban == 2)
                {
                    String text4 = "선택할 챔피언의 이름을 입력해주세요.\n";
                    texttyping.changeText(text4);
                    texttyping.start();
                    System.out.print("입력 : ");
                    String pickchampion = scanner.nextLine();
                    pick(pickchampion, team, order);
                    break;
                }
                else
                {
                    System.out.println("잘못된 입력입니다.\n");
                }
                break;
            } else if (input2.equalsIgnoreCase("2")) {
                continue;
            } else {
                System.out.println("잘못된 입력입니다.\n");
            }
        }
    }
    public void showchampionlist()
    {
        System.out.println("\n====TOP====");
        int count = 0;
        for (Champion key : championlist_top.keySet()) {
            if(!championlist_top.get(key))
            {
                System.out.print(key.getchampionname() + "\t");
            }
            else
            {
                System.out.print("\t\t");
            }
                count++;
                if (count % 5 == 0) { // 다섯 개씩 출력하고 나면 줄바꿈
                    System.out.println();
                }

        }
        if (count % 5 != 0) { // 마지막 줄이 완전하지 않을 경우에 줄바꿈 추가
            System.out.println();
        }

        System.out.println("\n====JUG====");
        count = 0;
        for (Champion key : championlist_jungle.keySet()) {
            if(!championlist_jungle.get(key))
            {
                System.out.print(key.getchampionname() + "\t");
            }
            else
            {
                System.out.print("\t\t");
            }
            count++;
            if (count % 5 == 0) { // 다섯 개씩 출력하고 나면 줄바꿈
                System.out.println();
            }

        }
        if (count % 5 != 0) { // 마지막 줄이 완전하지 않을 경우에 줄바꿈 추가
            System.out.println();
        }

        System.out.println("\n====MID====");
        count = 0;
        for (Champion key : championlist_mid.keySet()) {
            if(!championlist_mid.get(key))
            {
                System.out.print(key.getchampionname() + "\t");
            }
            else
            {
                System.out.print("\t\t");
            }
            count++;
            if (count % 5 == 0) { // 다섯 개씩 출력하고 나면 줄바꿈
                System.out.println();
            }

        }
        if (count % 5 != 0) { // 마지막 줄이 완전하지 않을 경우에 줄바꿈 추가
            System.out.println();
        }

        System.out.println("\n====ADC====");
        count = 0;
        for (Champion key : championlist_adc.keySet()) {
            if(!championlist_adc.get(key))
            {
                System.out.print(key.getchampionname() + "\t");
            }
            else
            {
                System.out.print("\t\t");
            }
            count++;
            if (count % 5 == 0) { // 다섯 개씩 출력하고 나면 줄바꿈
                System.out.println();
            }

        }
        if (count % 5 != 0) { // 마지막 줄이 완전하지 않을 경우에 줄바꿈 추가
            System.out.println();
        }

        System.out.println("\n====SUP====");
        count = 0;
        for (Champion key : championlist_support.keySet()) {
            if(!championlist_support.get(key))
            {
                System.out.print(key.getchampionname() + "\t");
            }
            else
            {
                System.out.print("\t\t");
            }
            count++;
            if (count % 5 == 0) { // 다섯 개씩 출력하고 나면 줄바꿈
                System.out.println();
            }

        }
        if (count % 5 != 0) { // 마지막 줄이 완전하지 않을 경우에 줄바꿈 추가
            System.out.println();
        }



    }

    public void ban(String pickchampion, int team, int picknumber)
    {
        if(team == 1)
        {
            for (Champion key : championlist_top.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_top.put(key, true);
                    team1banlist[picknumber-1].championname = pickchampion;
                    team1banlist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
            for (Champion key : championlist_jungle.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_jungle.put(key, true);
                    team1banlist[picknumber-1].championname = pickchampion;
                    team1banlist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
            for (Champion key : championlist_mid.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_mid.put(key, true);
                    team1banlist[picknumber-1].championname = pickchampion;
                    team1banlist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
            for (Champion key : championlist_adc.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_adc.put(key, true);
                    team1banlist[picknumber-1].championname = pickchampion;
                    team1banlist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
            for (Champion key : championlist_support.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_support.put(key, true);
                    team1banlist[picknumber-1].championname = pickchampion;
                    team1banlist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
        }
        else if(team == 2)
        {
            for (Champion key : championlist_top.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_top.put(key, true);
                    team2banlist[picknumber-1].championname = pickchampion;
                    team2banlist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
            for (Champion key : championlist_jungle.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_jungle.put(key, true);
                    team2banlist[picknumber-1].championname = pickchampion;
                    team2banlist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
            for (Champion key : championlist_mid.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_mid.put(key, true);
                    team2banlist[picknumber-1].championname = pickchampion;
                    team2banlist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
            for (Champion key : championlist_adc.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_adc.put(key, true);
                    team2banlist[picknumber-1].championname = pickchampion;
                    team2banlist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
            for (Champion key : championlist_support.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_support.put(key, true);
                    team2banlist[picknumber-1].championname = pickchampion;
                    team2banlist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
        }

    }

    public void pick(String pickchampion, int team, int picknumber)
    {
        if(team == 1)
        {
            for (Champion key : championlist_top.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_top.put(key, true);
                    team1picklist[picknumber-1].championname = pickchampion;
                    team1picklist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
            for (Champion key : championlist_jungle.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_jungle.put(key, true);
                    team1picklist[picknumber-1].championname = pickchampion;
                    team1picklist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
            for (Champion key : championlist_mid.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_mid.put(key, true);
                    team1picklist[picknumber-1].championname = pickchampion;
                    team1picklist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
            for (Champion key : championlist_adc.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_adc.put(key, true);
                    team1picklist[picknumber-1].championname = pickchampion;
                    team1picklist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
            for (Champion key : championlist_support.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_support.put(key, true);
                    team1picklist[picknumber-1].championname = pickchampion;
                    team1picklist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
        }
        else if(team == 2)
        {
            for (Champion key : championlist_top.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_top.put(key, true);
                    team2picklist[picknumber-1].championname = pickchampion;
                    team2picklist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
            for (Champion key : championlist_jungle.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_jungle.put(key, true);
                    team2picklist[picknumber-1].championname = pickchampion;
                    team2picklist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
            for (Champion key : championlist_mid.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_mid.put(key, true);
                    team2picklist[picknumber-1].championname = pickchampion;
                    team2picklist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
            for (Champion key : championlist_adc.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_adc.put(key, true);
                    team2picklist[picknumber-1].championname = pickchampion;
                    team2picklist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
            for (Champion key : championlist_support.keySet()) {
                if (key.getchampionname().equalsIgnoreCase(pickchampion)) {
                    championlist_support.put(key, true);
                    team2picklist[picknumber-1].championname = pickchampion;
                    team2picklist[picknumber-1].championid = key.getChampionid();
                    break;
                }
            }
        }

    }

    public void calculate() throws SQLException {
        String sql = "SELECT championid, championname, tier, counterid, easierid FROM CHAMPION";
        PreparedStatement pstmt = con.prepareStatement(sql);

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();

        while (rs.next())
        {
            //DB에 pick한 챔피언이 있는지 확인
            for(int i = 0; i < 5; i++)
            {
                if(rs.getString("championname").equalsIgnoreCase(team1picklist[i].championname))
                {
                    team1tierpoint += rs.getInt("tier");
                    break;
                }
            }
            for(int i = 0; i < 5; i++)
            {
                if(rs.getString("championname").equalsIgnoreCase(team2picklist[i].championname))
                {
                    team2tierpoint += rs.getInt("tier");
                    break;
                }
            }

            //pick한 챔피언의 카운터 챔피언이 있는지 확인
            for(int i = 0; i < 5; i++)
            {
                if(rs.getString("championname").equalsIgnoreCase(team1picklist[i].championname))
                {
                    for(int j = 0; j < 5; j++)
                    {
                        if(rs.getInt("counterid") == team2picklist[j].championid)
                        {
                            team2counterpoint++;
                            break;
                        }
                    }
                    break;
                }

            }

            for(int i = 0; i < 5; i++)
            {
                if(rs.getString("championname").equalsIgnoreCase(team2picklist[i].championname))
                {
                    for(int j = 0; j < 5; j++)
                    {
                        if(rs.getInt("counterid") == team1picklist[j].championid)
                        {
                            team1counterpoint++;
                            break;
                        }
                    }
                    break;
                }
            }


        }

        showresult();
        String text1 = "BLUE TEAM TIER POINT : " + team1tierpoint + "\nBLUE TEAM COUNTER POINT : " + team1counterpoint + "\nRED TEAM TIER POINT : " + team2tierpoint + "\nRED TEAM COUNTER POINT : " + team2counterpoint + "\n";
        texttyping texttyping = new texttyping(text1);
        texttyping.start();

        if(team1tierpoint - team1counterpoint < team2tierpoint - team2counterpoint)
        {
            String text2 = "BLUE TEAM WIN\n";
            texttyping.changeText(text2);
            texttyping.start();
            setWinnerteam(1);
        }
        else if(team1tierpoint - team1counterpoint > team2tierpoint - team2counterpoint)
        {
            String text2 = "RED TEAM WIN\n";
            texttyping.changeText(text2);
            texttyping.start();
            setWinnerteam(2);
        }
        else
        {
            String text2 = "DRAW\n";
            texttyping.changeText(text2);
            texttyping.start();
            setWinnerteam(0);
        }


    }

    public void showresult()
    {
        // 블루팀 벤 목록
        System.out.println("블루팀 벤 목록");
        for(int i = 0; i < 5; i++)
        {
            System.out.print(team1banlist[i].championname + "\t");
        }
        System.out.println();
        System.out.println();
        System.out.println();

        // 레드팀 벤 목록
        System.out.println("레드팀 벤 목록");
        for(int i = 0; i < 5; i++)
        {
            System.out.print(team2banlist[i].championname + "\t");
        }
        System.out.println();
        System.out.println();
        System.out.println();

        // 블루팀 픽 목록
        System.out.println("블루팀 픽 목록");
        for(int i = 0; i < 5; i++)
        {
            System.out.print(team1picklist[i].championname + "\t");
        }
        System.out.println();
        System.out.println();
        System.out.println();

        // 레드팀 픽 목록
        System.out.println("레드팀 픽 목록");
        for(int i = 0; i < 5; i++)
        {
            System.out.print(team2picklist[i].championname + "\t");
        }
        System.out.println();
        System.out.println();
        System.out.println();


    }
    public void updatebanpickindb(int banpickid, int teamid, int matchid, int teamcolor, int orders, int champid, int banorpick) throws SQLException
    {
        String sql = "INSERT INTO BANPICK (banpickid, teamid, matchid, teamcolor, orders, champid, banorpick) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, banpickid);
        pstmt.setInt(2, teamid);
        pstmt.setInt(3, matchid);
        pstmt.setInt(4, teamcolor);
        pstmt.setInt(5, orders);
        pstmt.setInt(6, champid);
        pstmt.setInt(7, banorpick);
        pstmt.executeUpdate();
    }

}
