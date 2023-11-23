public class texttyping
{
    private String text;

    public texttyping(String text)
    {
        this.text = text;
    }

    public void start()
    {
        try {
            for (int i = 0; i < text.length(); i++) {
                System.out.print(text.charAt(i));
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void changeText(String text)
    {
        this.text = text;
    }
}
