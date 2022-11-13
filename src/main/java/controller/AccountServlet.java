package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import dao.UserDAO;
import model.User;

@WebServlet(urlPatterns = { "/account/sign-in", "/account/sign-up", "/account/sign-out", "/account/forgot-password",
		"/account/change-password", "/account/edit-profile" })
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AccountServlet() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		if (uri.contains("sign-in")) {
			doSignIn(request, response);
		} else if (uri.contains("sign-up")) {
			doSignUp(request, response);
		} else if (uri.contains("sign-out")) {
			doSignOut(request, response);
		} else if (uri.contains("edit-profile")) {
			doEditProfile(request, response);
		} else if (uri.contains("forgot-password")) {

		} else if (uri.contains("change-password")) {

		}
	}

	private void doSignUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		if (method.equalsIgnoreCase("POST")) {
			try {
				User entity = new User();
				BeanUtils.populate(entity, request.getParameterMap());
				UserDAO dao = new UserDAO();
				dao.create(entity);
				request.setAttribute("message", "Đăng ký thành công!");
				response.setHeader("Refresh", "1;url=/JAVA4_Lab5/account/sign-in"); // chuyen den trang tiep theo trong 1s
			} catch (Exception e) {
				request.setAttribute("message", "Lỗi đăng ký!");
			}
		}
		request.getRequestDispatcher("/views/account/sign-up.jsp").forward(request, response);
	}

	private void doSignIn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		if (method.equalsIgnoreCase("POST")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("password");
			try {
				UserDAO dao = new UserDAO();
				User user = dao.findById(id);
				if (!user.getPassword().equals(pw)) {
					request.setAttribute("message", "Sai mật khẩu!");
				} else {
					request.setAttribute("message", "Đăng nhập thành công!");
					request.getSession().setAttribute("user", user);
					response.setHeader("Refresh", "1;url=/JAVA4_Lab5/account/edit-profile"); // chuyen trang
				}
			} catch (Exception e) {
				request.setAttribute("message", "Sai tên đăng nhập!");
			}
		}
		request.getRequestDispatcher("/views/account/sign-in.jsp").forward(request, response);
	}

	private void doEditProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String method = request.getMethod();
		if (method.equalsIgnoreCase("POST")) {
			try {
				BeanUtils.populate(user, request.getParameterMap());
				UserDAO dao = new UserDAO();
				dao.update(user);
				request.setAttribute("message", "Cập nhật tài khoản thành công!");
			} catch (Exception e) {
				request.setAttribute("message", "Lỗi cập nhật tài khoản!");
			}
		}
		request.getRequestDispatcher("/views/account/edit-profile.jsp").forward(request, response);
	}

	private void doSignOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.removeAttribute("id");
		session.invalidate();
		request.setAttribute("message", "Đăng xuất thành công!");
		request.getRequestDispatcher("/views/account/sign-in.jsp").forward(request, response);
	}
}
