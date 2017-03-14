package models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.table.DefaultTableModel;

import business.PostDelagate;
import entities.Post;

public class PostsModel {

	public List<Post> postsList;

	public PostsModel() {
		// TODO Auto-generated constructor stub
	}

	public DefaultTableModel postModel() throws SQLException, IOException, NamingException {
		// PostBusiness proxy = new PostBusiness();

		DefaultTableModel postModel = null;
		postsList = PostDelagate.findReportedPosts();

		String col[] = { "Content", "number of likes", "number of Reports" };
		postModel = new DefaultTableModel(col, 0);
		for (int i = 0; i < PostDelagate.findReportedPosts().size(); i++) {
			String content = PostDelagate.findReportedPosts().get(i).getContent();
			int nbrLikes = PostDelagate.findReportedPosts().get(i).getNbrLikes();
			int nbrReports = PostDelagate.findReportedPosts().get(i).getNbrReport();
			// String role =
			// proxy.getProxy().findReportedPosts().get(i).getRole();

			Object[] data1 = { content, nbrLikes, nbrReports };
			postModel.addRow(data1);
			System.out.println(postModel.toString());

		}

		return postModel;

	}

	public List<Post> getPost() {
		return postsList;
	}

}
