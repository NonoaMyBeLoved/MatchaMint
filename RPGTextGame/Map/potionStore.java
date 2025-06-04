package Map;
import Character.*;
import java.util.Scanner;

public class potionStore 
{
	public static void Store(Hero hero)
	{
		Scanner in = new Scanner(System.in);
        System.out.println("***** 포션 상점에 오신 걸 환영합니다! *****");
        System.out.println("1. HP 포션 (10골드)");
        System.out.println("2. MP 포션 (10골드)");
        System.out.println("3. 힘 강화! (20골드)");
        System.out.println("4. 나가기");
        while (true)
        {
            System.out.print("번호를 입력하세요 : ");
            int choice = in.nextInt();
            if (choice == 1)
            {
                if (hero.money >= 10)
                {
                    hero.hp += 40;
                    hero.money -= 10;
                    System.out.println("HP 포션을 사용했습니다. HP +40!");
                }
                else
                {
                    System.out.println("돈이 부족해요!");
                }
            }
            else if (choice == 2)
            {
                if (hero.money >= 10)
                {
                    hero.mp += 30;
                    hero.money -= 10;
                    System.out.println("MP 포션을 사용했습니다. MP +30!");
                }
                else
                {
                    System.out.println("돈이 부족해요!");
                }
            }
            else if (choice == 3)
            {
                if (hero.money >= 20)
                {
                    hero.power += 5;
                    hero.money -= 20;
                    System.out.println("힘이 강해졌습니다! 힘 +5!");
                }
                else
                {
                    System.out.println("돈이 부족해요!");
                }
            }
            else if (choice == 4)
            {
                System.out.println("상점을 나갑니다.");
                break;
            }
            else
            {
                System.out.println("잘못된 입력입니다.");
            }
        }
	}
}
