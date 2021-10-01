package com.example.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			//SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");
			
			//SQLite 데이터베이스 파일에 연결 
			String dbFile = "myfirst.db";
			con=DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			//데이터 조회
			System.out.println("\n*** 데이터 조회 ***");
			//Statement 객체 생성
			Statement stat1 = con.createStatement();
			
			//artists 데이터 조회
			System.out.println("\n[artists]");
			String sql1 = "select * from artists";
			//쿼리 실행 후 결과값 가져오기 (열과 행의 표 형태로 불러짐)
			ResultSet rs1 = stat1.executeQuery(sql1);
			while(rs1.next()) {
				String id = rs1.getString("id");
				String name = rs1.getString("name");
				System.out.println(id + " " + name);
			}
			stat1.close(); 
			
			//g_artists 데이터 조회
			System.out.println("\n[g_artists]");
			String sql2 = "select * from g_artists";
			//쿼리 실행 후 결과값 가져오기 (열과 행의 표 형태로 불러짐)
			ResultSet rs2 = stat1.executeQuery(sql2);
			while(rs2.next()) {
				String id = rs2.getString("id");
				String name = rs2.getString("name");
				System.out.println(id + " " + name);
			}
			stat1.close(); 
			
			//데이터 추가 -artists table
			System.out.println("\n*** 새 데이터 추가 ***");
			Statement stat2 = con.createStatement();
			
			String sql3= "insert into artists (name, a_type, a_year, debut, regdate)" + 
					" values ('레드벨벳', '여성', '2010년대, 2020년대', '2014년', datetime('now', 'localtime'));";
			//결과 값은 Result set이 아니라 이 query 생성했을 때 생긴 데이터 수 (여기서는 데이터 1개 더 추가해서 1이 나옴)
			int cnt = stat2.executeUpdate(sql3);
			if (cnt>0)
				System.out.println("새로운 데이터가 추가되었습니다!");
			else
				System.out.println("[Error] 데이터 추가 오류!");
			stat2.close(); 

			//데이터 삭제 -artists table
			System.out.println("\n*** 데이터 삭제 ***");
			Statement stat3 = con.createStatement();
			String sql4 = "delete from artists where id=7;";
			int cnt2 = stat3.executeUpdate(sql4);
			if(cnt2>0) 
				System.out.println("데이터가 수정되었습니다!");
			else
				System.out.println("[Error] 데이터 수정 요구");
			stat3.close(); 
			
			//데이터 수정 -g_artists table
			System.out.println("\n*** 데이터 수정 ***");
			Statement stat4 = con.createStatement();
			String sql5 = "update g_artists set debut='2015-Marvin Gaye'" + "where id=5;";
			int cnt3 = stat4.executeUpdate(sql5);
			if(cnt3>0)
				System.out.println("데이터가 수정되었습니다!");
			else
				System.out.println("[Error] 데이터 수정 요구");
			stat4.close(); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
					
				}
				catch(Exception e) {}
			}
		}
	}
	
}
