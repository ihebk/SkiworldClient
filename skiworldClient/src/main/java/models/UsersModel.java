package models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.table.DefaultTableModel;

import business.UserDelegate;
import contracts.UserCrudEJBRemote;
import entities.User;

public class UsersModel {

	public List<User> usersList;

	public DefaultTableModel userModel() throws SQLException, IOException {
		InitialContext ctx;

		DefaultTableModel userModel = null;
		// ctx = new InitialContext();
		//
		// UserCrudEJBRemote proxyUser = (UserCrudEJBRemote) ctx
		// .lookup("/SkiWorld-ear/SkiWorld-ejb/UserCrudEJB!services.UserCrudEJBRemote");
		usersList = UserDelegate.findAllUsers();

		String col[] = { "Username", "Email", "Phone", "Role" };
		userModel = new DefaultTableModel(col, 0);
		for (int i = 0; i < UserDelegate.findAllUsers().size(); i++) {
			String username = UserDelegate.findAllUsers().get(i).getUsername();
			String email = UserDelegate.findAllUsers().get(i).getEmail();
			int phone = UserDelegate.findAllUsers().get(i).getPhone();
			String role = UserDelegate.findAllUsers().get(i).getRole();

			Object[] data = { username, email, phone, role };
			userModel.addRow(data);
			System.out.println(userModel.toString());

		}
		return userModel;

	}
}
