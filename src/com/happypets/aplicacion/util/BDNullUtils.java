package com.happypets.aplicacion.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class BDNullUtils {
	public static final void setNull(PreparedStatement ps, int pos, String value) 
			throws SQLException {
		if (value==null) {
			ps.setNull(pos, Types.VARCHAR);
		} else { 
			ps.setString(pos, value);
		}
	}

	
	public static final void setNull(PreparedStatement ps, int pos, Long value) 
			throws SQLException {
		if (value==null) {
			ps.setNull(pos, Types.BIGINT);
		} else { 
			ps.setLong(pos, value);
		}
	}
	public  static void toNull(PreparedStatement p, int pos, String value) throws SQLException{
		if(value == null) {
			p.setNull(pos, Types.VARCHAR);
		}
		else {
			p.setString(pos, value);
		}
	}
	public  static void toNull(PreparedStatement p, int pos, Boolean value) throws SQLException{
		if(value == null) {
			p.setNull(pos, Types.BOOLEAN);
		}
		else {
			p.setBoolean(pos, value);
		}
	}
	public static void toNull(PreparedStatement p, int pos, Integer value) throws SQLException{
		if(value == null) {
			p.setNull(pos, Types.INTEGER);
		}
		else {
			p.setInt(pos, value);
		}
	}
	public static void toNull(PreparedStatement p, int pos, Long value) throws SQLException{
		if(value == null) {
			p.setNull(pos, Types.BIGINT);
		}
		else {
			p.setLong(pos, value);
		}
	}
	public static void toNull(PreparedStatement p, int pos, Character value) throws SQLException{
		if(value == null) {
			p.setNull(pos, Types.CHAR);
		}
		else {
			p.setString(pos, String.valueOf(value));
		}
	}
}
