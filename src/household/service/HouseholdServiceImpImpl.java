package household.service;

import java.util.ArrayList;
import java.util.Scanner;

import household.dao.HouseholdDAO;
import household.dto.HouseholdDTO;

public class HouseholdServiceImpImpl implements HouseholdService{
	private HouseholdDAO dao;
	public HouseholdServiceImpImpl() {
		dao = new HouseholdDAO();
	}
	HouseholdDTO d = new HouseholdDTO();
	@Override
	public void money() {
		Scanner sc = new Scanner(System.in);
		System.out.print("입력 : ");
		d.setTotal(sc.nextInt());
		System.out.println("입력되었습니다!!!");
		System.out.println(d.getTotal());
	}

	@Override
	public void view() {
		System.out.println("--------목록 확인--------");
		ArrayList<HouseholdDTO> arr = new ArrayList<HouseholdDTO>();
		arr = dao.list();
		int etc = 0;
		System.out.println("총 금액 : " + d.getTotal());
		for(HouseholdDTO dto : arr) {
			System.out.printf("종류 : %-10s" , dto.getType());
			//System.out.println("\t| 금액 : " + dto.getMoney());
			System.out.printf("| 금액 : %4d\n" , dto.getMoney());
			etc += dto.getMoney();
		}
		System.out.println("여유 자금 : " + (d.getTotal() - etc));
	}

	@Override
	public void add() {
		String type;
		int money;

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("추가할 종류 입력 : ");
			type = sc.next();
			int result = dao.typeCheck(type);
			if (result == 0) {
				break;
			}else if (result == 1) {
				System.out.println("이미 추가되어 있습니다.");
			}
		}
		System.out.print("추가한 종류 가격 입력 : ");
		money = sc.nextInt();
		int result = dao.add(type, money);
		if (result == 1) {
			System.out.println("추가 성공!!!");
		}else {
			System.out.println("추가 실패...");
		}
	}

	@Override
	public void modify() {
		String type;
		int money;
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("종류 입력 : ");
			type = sc.next();
			int result = dao.typeCheck(type);
			if (result == 0) {
				System.out.println("포함되어 있지 않습니다.");
			}else if (result == 1) {
				break;
			}
		}
		System.out.print("수정할 가격 입력 : ");
		money = sc.nextInt();
		int result = dao.modify(type, money);
		if (result == 1) {
			System.out.println("수정 성공!!!");
		}else {
			System.out.println("수정 실패...");
		}
	}

	@Override
	public void delete() {
		String type;
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 종류 입력 : ");
		type = sc.next();
		int result = dao.delete(type);
		if (result == 1) {
			System.out.println("삭제 성공!!!");
		}else {
			System.out.println("삭제 실패...");
		}
	}
}