package Map;
import Character.*;
import Main.*;
import java.util.Scanner;

public class weaponStore 
{
	public static void Store(Hero hero)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("***** 무기 상점에 오신 걸 환영합니다! *****");
        System.out.println("전용 무기 구매시 힘 10 증가와 3번 기술의 데미지가 2개가 됩니다.");
        System.out.println("1. 엑스칼리버 (전사 전용, 200골드)");
        System.out.println("2. 프리렌의 지팡이 (마법사 전용, 200골드)");
        System.out.println("3. 에인션트 보우 (궁수 전용, 200골드)");
        System.out.println("4. 나가기");

        while (true)
        {
            System.out.print("번호를 입력하세요 : ");
            int choice = in.nextInt();
            if (choice == 1)
            {
                if (hero instanceof Warrior)
                {
                    if (hero.money >= 200)
                    {
                        hero.power += 10;
                        hero.money -= 200;
                        MainRPGTextGame.specialWeapon = true;
                        System.out.println("엑스칼리버를 구매해 착용했습니다!");
                    }
                    else
                    {
                        System.out.println("돈이 부족해요!");
                    }
                }
                else
                {
                    System.out.println("전사만 구매할 수 있습니다.");
                }
            }
            else if (choice == 2)
            {
                if (hero instanceof Wizard)
                {
                    if (hero.money >= 200)
                    {
                        hero.power += 10;
                        hero.money -= 200;
                        MainRPGTextGame.specialWeapon = true;
                        System.out.println("프리렌의 지팡이를 구매해 착용했습니다!");
                    }
                    else
                    {
                        System.out.println("돈이 부족해요!");
                    }
                }
                else
                {
                    System.out.println("마법사만 구매할 수 있습니다.");
                }
            }
            else if (choice == 3)
            {
                if (hero instanceof Archor)
                {
                    if (hero.money >= 200)
                    {
                        hero.power += 10;
                        hero.money -= 200;
                        MainRPGTextGame.specialWeapon = true;
                        System.out.println("에인션트 보우를 구매해 착용했습니다!");
                    }
                    else
                    {
                        System.out.println("돈이 부족해요!");
                    }
                }
                else
                {
                    System.out.println("궁수만 구매할 수 있습니다.");
                }
            }
            else if (choice == 4)
            {
                System.out.println("무기 상점을 나갑니다.");
                break;
            }
            else
            {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
