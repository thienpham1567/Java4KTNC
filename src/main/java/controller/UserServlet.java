package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(urlPatterns = { "/user", "/user/create", "/user/update", "/user/edit", "/user/delete", "/user/reset" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String url = request.getRequestURL().toString();
		if (url.contains("delete")) {
			delete(request, response);
		} else if (url.contains("edit")) {
			edit(request, response);
		} else if (url.contains("reset")) {
			request.setAttribute("user", new User());
		}
		findAll(request, response);
		request.getRequestDispatcher("/views/User.jsp").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String url = request.getRequestURL().toString();
		if (url.contains("create")) {
			create(request, response);
		} else if (url.contains("delete")) {
			delete(request, response);
		} else if (url.contains("update")) {
			update(request, response);
		} else if (url.contains("reset")) {
			request.setAttribute("user", new User());
		}
		findAll(request, response);
		request.getRequestDispatcher("/views/User.jsp").forward(request, response);
	}

	protected void create(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			UserDAO dao = new UserDAO();
			dao.create(user);
			request.setAttribute("message", "Thêm mới thành công!!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Lỗi: " + e.getMessage());
			request.getRequestDispatcher("/views/User.jsp").forward(request, response);
		}
	}

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			UserDAO dao = new UserDAO();
			User user = dao.findById(id);
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Lỗi: " + e.getMessage());
			request.getRequestDispatcher("/views/User.jsp").forward(request, response);
		}
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			UserDAO dao = new UserDAO();
			dao.update(user);
			request.setAttribute("user", user);
			request.setAttribute("message", "Cập nhật thành công!!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Lỗi: " + e.getMessage());
		}
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			UserDAO dao = new UserDAO();
			dao.delete(id);
			request.setAttribute("message", "Xoá thành công!!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Lỗi: " + e.getMessage());
			request.getRequestDispatcher("/views/User.jsp").forward(request, response);
		}
	}

	protected void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UserDAO dao = new UserDAO();
			List<User> list = dao.findAll();
			request.setAttribute("users", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Lỗi: " + e.getMessage());
			request.getRequestDispatcher("/views/User.jsp").forward(request, response);
		}
	}
}
