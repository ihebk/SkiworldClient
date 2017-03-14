package business;

import java.util.List;

import contracts.PostCrudEJBRemote;
import entities.Post;

public class PostDelagate {

	private static final String JNDI = "/SkiWorld-ear/SkiWorld-ejb/PostCrudEJB!contracts.PostCrudEJBRemote";

	private static PostCrudEJBRemote getProxy() {
		return (PostCrudEJBRemote) ServiceLocator.getInstance().getProxy(JNDI);
	}

	public static List<Post> findReportedPosts() {
		return getProxy().findReportedPosts();

	}

	public static void updatePost(Post post) {
		getProxy().updatePost(post);
	}

	public static boolean removePost(Post post) {
		try {
			getProxy().removePost(post);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
