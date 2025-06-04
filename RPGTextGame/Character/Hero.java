package Character;
import Main.*;

public class Hero extends Character 
{
	public static boolean mission = false;
	int goblin_count = 0;
	public int power;
	public int defense;
	public int money = 100;
	public int experience = 0;
	public int level = 1;	
	
	public int attack()
	{
		return power * 10;
	}
	public void attacked(int sum)
	{
		hp -= sum;
	}
	public void levelup()
	{
		if(experience >= level * 40)
		{
			System.out.println("********레벨업!********");
			level += 1;
			power += 5;
			defense += 2;
			money += level * 40;
			mp += level * 20;
			experience = 0;					
		}
		if(level == 3 && !mission)
		{
			System.out.println("****3레벨 달성! 다음 미션을 완료하면 3번째 기술을 얻습니다.****");
			System.out.println("고블린 3마리 처치");
			mission = true;
		}
	}
	public void goblinMission()
	{
		if(mission && !MainRPGTextGame.three)
		{
			goblin_count++;
			System.out.println("현재 잡은 고블린 : " + goblin_count + "마리");
			if(goblin_count >= 3)
			{
				System.out.println("****미션완료! 3번째 기술 해금****");
				MainRPGTextGame.three = true;
			}
		}
	}
}
