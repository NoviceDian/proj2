package com.dian.util;

import java.sql.Connection;

public class Test {
	public static void main(String[] args) {
		Connection connection = DBConnection.getConnection();
		System.out.println(connection);
		DBConnection.close(connection);
	}
}
