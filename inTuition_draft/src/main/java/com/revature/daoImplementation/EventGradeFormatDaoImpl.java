package com.revature.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.connection.ConnFactory;
import com.revature.daos.EventGradeFormatDao;
import com.revature.models.EventGradeFormat;
import com.revature.models.EventType;

public class EventGradeFormatDaoImpl implements EventGradeFormatDao {
	private static EventGradeFormatDaoImpl eventGradeFormatDao = new EventGradeFormatDaoImpl();
	private Connection conn;
	
	private EventGradeFormatDaoImpl() {
		
	}
	public static EventGradeFormatDaoImpl getInstance() {
		return eventGradeFormatDao;
	}
	@Override
	public ArrayList<EventGradeFormat> getGradeTypes() {
		// TODO Auto-generated method stub
		ArrayList<EventGradeFormat> eventGradeTypes = new ArrayList<>();
		try {
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT egf_id,egf_description,egf_format FROM EVENT_GRADE_FORMAT";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				eventGradeTypes.add(new EventGradeFormat(rs.getString("egf_description"),rs.getString("egf_format"),rs.getInt("egf_id")));
			}
			conn.close();
			return eventGradeTypes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
