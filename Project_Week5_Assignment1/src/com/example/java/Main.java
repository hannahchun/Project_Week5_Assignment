package com.example.java;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

	public static void main(String[] args) {
		Connection con=null;
		try {
			//SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");
			
			//SQLite 데이터베이스 파일에 연결
			String dbFile="myfirst.db";
			con=DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			//데이터 조회
			System.out.println("\n*** 데이터 조회 ***");
			Statement stat1 = con.createStatement();
			String sql1 = "select * from g_artists";
			ResultSet rs1 = stat1.executeQuery(sql1);
			while(rs1.next()) {
				String id = rs1.getString("id");
				String name = rs1.getString("name");
				System.out.println(id + " " + name);
			}
			stat1.close();
			
			/*
			//데이터 추가
			System.out.println("\n*** 데이터 추가 ***");
			Statement stat2 = con.createStatement();
			String sql2 = "insert into g_artists (name, a_type, a_year, debut, regdate)" + 
			" values ('방탄소년단', '남성', '2010년대', '2013년', datetime('now', 'localtime')) ;";
			int cnt = stat2.executeUpdate(sql2);
			//결과 값은 Result set이 아니라 이 query 생성했을 때 생긴 데이터 수 (여기서는 데이터 1개 더 추가해서 1이 나옴)
			if(cnt>0) {
				System.out.println("새로운 데이터가 추가되었습니다!");
			}
			else
				System.out.println("[Error] 데이터 추가 오류!");
			stat2.close(); */
			
			//데이터 또 추가 (다른 방법)
			System.out.println("\n*** 데이터 추가 ***");
			String sql5= "insert into g_artists ( name, a_type, a_year, debut, regdate)" + 
			"values (?,?,?,?,datetime('now','localtime'));";
			
			PreparedStatement pstmt = con.prepareStatement(sql5);
			pstmt.setString(1, "레드벨벳");
			pstmt.setString(2, "여성");
			pstmt.setString(3, "2010년대");
			pstmt.setString(4, "2014년");
			int count = pstmt.executeUpdate();
			if(count>0) {
				System.out.println("새로운 데이터가 추가되었습니다!");
			}
			else
				System.out.println("[Error] 데이터 추가 오류!");
			pstmt.close();
			
			/*
			//데이터 수정
			System.out.println("\n*** 데이터 수정 ***");
			Statement stat3 = con.createStatement();
			String sql3 = "update g_artists set a_year = '2000년대, 2010년대, 2020년대' " + "where id=1 ;";
			int cnt2 = stat3.executeUpdate(sql3);
			//결과 값은 Result set이 아니라 이 query 생성했을 때 생긴 데이터 수 (여기서는 데이터 1개 수정해서 1이 나옴)
			if(cnt2>0) {
				System.out.println("데이터가 수정되었습니다!");
			}
			else
				System.out.println("[Error] 데이터 수정 오류!");
			stat3.close(); 
			
			//데이터 삭제 
			System.out.println("\n*** 데이터 삭제 ***");
			Statement stat4 = con.createStatement();
			String sql4 = "delete from g_artists where id=3 ;";
			int cnt3 = stat4.executeUpdate(sql4);
			if(cnt3>0) {
				System.out.println("데이터가 삭제되었습니다!");
			}
			else
				System.out.println("[Error] 데이터 삭제 오류!");
			stat4.close(); */
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null) {
				try {
					con.close();
				} catch(Exception e) {}
			}
		}
	}
	
}
