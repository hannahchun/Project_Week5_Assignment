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
			//SQLite JDBC üũ
			Class.forName("org.sqlite.JDBC");
			
			//SQLite �����ͺ��̽� ���Ͽ� ���� 
			String dbFile = "myfirst.db";
			con=DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			//������ ��ȸ
			System.out.println("\n*** ������ ��ȸ ***");
			//Statement ��ü ����
			Statement stat1 = con.createStatement();
			
			//artists ������ ��ȸ
			System.out.println("\n[artists]");
			String sql1 = "select * from artists";
			//���� ���� �� ����� �������� (���� ���� ǥ ���·� �ҷ���)
			ResultSet rs1 = stat1.executeQuery(sql1);
			while(rs1.next()) {
				String id = rs1.getString("id");
				String name = rs1.getString("name");
				System.out.println(id + " " + name);
			}
			stat1.close(); 
			
			//g_artists ������ ��ȸ
			System.out.println("\n[g_artists]");
			String sql2 = "select * from g_artists";
			//���� ���� �� ����� �������� (���� ���� ǥ ���·� �ҷ���)
			ResultSet rs2 = stat1.executeQuery(sql2);
			while(rs2.next()) {
				String id = rs2.getString("id");
				String name = rs2.getString("name");
				System.out.println(id + " " + name);
			}
			stat1.close(); 
			
			//������ �߰� -artists table
			System.out.println("\n*** �� ������ �߰� ***");
			Statement stat2 = con.createStatement();
			
			String sql3= "insert into artists (name, a_type, a_year, debut, regdate)" + 
					" values ('���座��', '����', '2010���, 2020���', '2014��', datetime('now', 'localtime'));";
			//��� ���� Result set�� �ƴ϶� �� query �������� �� ���� ������ �� (���⼭�� ������ 1�� �� �߰��ؼ� 1�� ����)
			int cnt = stat2.executeUpdate(sql3);
			if (cnt>0)
				System.out.println("���ο� �����Ͱ� �߰��Ǿ����ϴ�!");
			else
				System.out.println("[Error] ������ �߰� ����!");
			stat2.close(); 

			//������ ���� -artists table
			System.out.println("\n*** ������ ���� ***");
			Statement stat3 = con.createStatement();
			String sql4 = "delete from artists where id=7;";
			int cnt2 = stat3.executeUpdate(sql4);
			if(cnt2>0) 
				System.out.println("�����Ͱ� �����Ǿ����ϴ�!");
			else
				System.out.println("[Error] ������ ���� �䱸");
			stat3.close(); 
			
			//������ ���� -g_artists table
			System.out.println("\n*** ������ ���� ***");
			Statement stat4 = con.createStatement();
			String sql5 = "update g_artists set debut='2015-Marvin Gaye'" + "where id=5;";
			int cnt3 = stat4.executeUpdate(sql5);
			if(cnt3>0)
				System.out.println("�����Ͱ� �����Ǿ����ϴ�!");
			else
				System.out.println("[Error] ������ ���� �䱸");
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
