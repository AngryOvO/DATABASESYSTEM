public class PLAYER
{
    private String name;
    private String team;

    private int teamNumber;
    public PLAYER(String name, String team , int teamNumber)
    {
        this.name = name;
        this.team = team;
        this.teamNumber = teamNumber;
    }

    public String getName()
    {
        return name;
    }

    public String getTeam()
    {
        return team;
    }

    public int getTeamNumber()
    {
        return teamNumber;
    }
}


