package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.User;

public class JpaProgram {

	private static void create() {
		// Nạp persistence.xml và tạo EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			// Tạo Entity
			User entity = new User("TeoNV", "123456", "teonv@gmail.com", "Nguyễn Văn Tèo", false);
			// Insert vào CSDL
			em.persist(entity);

			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
			System.out.println("Thêm mới thành công!");
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Thêm mới thất bại!");
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	private static void update() {
		// Nạp persistence.xml và tạo EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			// Truy vấn thực thể theo id
			User entity = em.find(User.class, "TeoNV");
			// Thay đổi thông tin thực thể
			entity.setPassword("teonv123");
			entity.setFullname("Ahihi");
			entity.setAdmin(true);
			// Cập nhật thực thể
			em.merge(entity);

			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
			System.out.println("Cập nhật thành công!");
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Cập nhật thất bại!");
		} finally {
			em.close();
			emf.close();
		}
	}

	private static void delete(String id) {
		// Nạp persistence.xml và tạo EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			// Truy vấn thực thể theo id
			User entity = em.find(User.class, id);
			// Xoá thực thể
			em.remove(entity);

			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
			System.out.println("Xoá thành công!");
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Xoá thất bại!");
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	private static void findAll() {
		// Nạp persistence.xml và tạo EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			// Câu lệnh truy vấn JPQL
			String jpql = "SELECT o FROM User o";
			// Tạo đối tượng truy vấn
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			// Truy vấn
			List<User> list = query.getResultList();
			// Hiển thị kết quả truy vấn
			for (User user : list) {
				System.out.println(">>ID: " + user.getId());
				System.out.println(">>Fullname: " + user.getFullname());
				System.out.println(">>Is Admin: " + user.isAdmin());
			}

			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
			System.out.println("Tìm kiếm thành công!");
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Tìm kiếm thất bại!");
		} finally {
			em.close();
			emf.close();
		}
	}

	private static void findByRole(boolean role) {
		// Nạp persistence.xml và tạo EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			// Câu lệnh truy vấn JPQL
			String jpql = "SELECT o FROM User o WHERE o.admin = :role";
			// Tạo đối tượng truy vấn
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("role", role);
			// Truy vấn
			List<User> list = query.getResultList();
			// Hiển thị kết quả truy vấn
			for (User user : list) {
				System.out.println(">>ID: " + user.getId());
				System.out.println(">>Fullname: " + user.getFullname());
				System.out.println(">>Is Admin: " + user.isAdmin());
			}

			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
			System.out.println("Tìm kiếm thành công!");
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Tìm kiếm thất bại!");
		} finally {
			em.close();
			emf.close();
		}
	}

	private static void findByKeyword(String keyword) {
		// Nạp persistence.xml và tạo EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			// Câu lệnh truy vấn JPQL
			String jpql = "SELECT o FROM User o WHERE o.fullname LIKE :key";
			// Tạo đối tượng truy vấn
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("key", "%" + keyword + "%");
			// Truy vấn
			List<User> list = query.getResultList();
			// Hiển thị kết quả truy vấn
			for (User user : list) {
				System.out.println(">>ID: " + user.getId());
				System.out.println(">>Fullname: " + user.getFullname());
				System.out.println(">>Is Admin: " + user.isAdmin());
			}
			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
			System.out.println("Tìm kiếm thành công!");
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Tìm kiếm thất bại!");
		} finally {
			em.close();
			emf.close();
		}
	}

	private static void findOne(String username, String password) {
		// Nạp persistence.xml và tạo EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			// Câu lệnh truy vấn JPQL
			String jpql = "SELECT o FROM User o WHERE o.id = :id AND o.password = :pw";
			// Tạo đối tượng truy vấn
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("id", username);
			query.setParameter("pw", password);
			// Truy vấn một thực thể
			User user = query.getSingleResult();
			// Hiển thị kết quả truy vấn
			System.out.println(">>Fullname: " + user.getFullname());
			System.out.println(">>Is Admin: " + user.isAdmin());

			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
			System.out.println("Tìm kiếm thành công!");
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Tìm kiếm thất bại!");
		} finally {
			em.close();
			emf.close();
		}
	}

	private static void findPage(int page, int size) {
		// Nạp persistence.xml và tạo EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			// Câu lệnh truy vấn JPQL
			String jpql = "SELECT o FROM User o";
			// Tạo đối tượng truy vấn
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setFirstResult(page * size);
			query.setMaxResults(size);
			// Truy vấn
			List<User> list = query.getResultList();
			// Hiển thị kết quả truy vấn
			for (User user : list) {
				System.out.println(">>Fullname: " + user.getFullname());
				System.out.println(">>Is Admin: " + user.isAdmin());
			}

			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
			System.out.println("Tìm trang thành công!");
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println("Tìm trang thất bại!");
		} finally {
			em.close();
			emf.close();
		}
	}

	public static void main(String[] args) {
		create();
		update();
		delete("TeoNV");
		findAll();
		findByRole(true);
		findByKeyword("duy");
		findOne("admin", "123");
		findPage(1, 2);
	}
}
