package com.revature.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.connection.ConnFactory;
import com.revature.daos.EventTypeDao;
import com.revature.models.EventType;
import com.revature.models.ReimbursementUser;

public class EventTypeDaoImpl implements EventTypeDao {
	private Connection conn;
	private static EventTypeDaoImpl eventTypeDao = new EventTypeDaoImpl();

	private EventTypeDaoImpl() {

	}

	public static EventTypeDaoImpl getInstance() {
		return eventTypeDao;
	}

	@Override
	public ArrayList<EventType> getTypes() {
		// TODO Auto-generated method stub
		ArrayList<EventType> eventTypes = new ArrayList<>();
		try {
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT et_id,et_desc,reimbursement_coverage FROM EVENT_TYPE";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				eventTypes.add(new EventType(rs.getInt(1),rs.getString(2),rs.getInt(3)));
			}
			conn.close();
			return eventTypes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
