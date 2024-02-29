package household.main;

import java.util.Scanner;

import household.service.HouseholdService;
import household.service.HouseholdServiceImpImpl;

public class MainClass {
	public static void main(String[] args) {
		HouseholdService hs = new HouseholdServiceImpImpl();
		Scanner sc = new Scanner(System.in);
		int func;
		while (true) {
			System.out.println("--------가계부--------");
			System.out.println("0. 월 고정 수익 입력");
			System.out.println("1. 월 고정 지출금액 확인");
			System.out.println("2. 월 고정 지출금액 추가");
			System.out.println("3. 월 고정 지출금액 수정");
			System.out.println("4. 월 고정 지출금액 삭제");
			System.out.println("5. 종료");
			System.out.print(">>> : ");
			func = sc.nextInt();
			switch (func) {
			case 0:
				hs.money();
				break;
			case 1:
				hs.view();
				break;
			case 2:
				hs.add();
				break;
			case 3:
				hs.modify();
				break;
			case 4:
				hs.delete();
				break;
			case 5:
				System.out.println("종료...!");
				return;
			default:
				break;
			}
		}
	}
}
