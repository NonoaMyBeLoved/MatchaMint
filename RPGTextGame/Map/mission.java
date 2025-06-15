package Map;
import Main.*;
import Character.*;

public class mission 
{
	private Hero hero;
    private int goblinCount = 0;
    private boolean message = false;
    private boolean done = false;

    public mission(Hero hero)
    {
        this.hero = hero;
    }

    public void checkLevelUp()
    {
        if (hero.level == 3 && !message)
        {
            System.out.println("****3레벨 달성! 고블린 3마리 처치하면 3번째 기술 해금!****");
            goblinCount = 0;
            message = true;
        }
    }

    public void killGoblin()
    {
        if (hero.level >= 3 && !done)
        {
            goblinCount++;
            System.out.println("현재 잡은 고블린: " + goblinCount + "마리");
            if (goblinCount >= 3)
            {
                System.out.println("****미션 완료! 3번째 기술 해금!****");
                Main.MainRPGTextGame.three = true;
                done = true;
            }
        }
    }
}
