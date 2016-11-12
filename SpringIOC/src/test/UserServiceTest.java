package test;

import model.User;
import service.UserService;
import Spring.BeanFactory;
import Spring.ClassPathXmlApplicationContext;

// ���Դ���
public class UserServiceTest {

	public void testAdd() throws Exception {
		BeanFactory applicationContext = new ClassPathXmlApplicationContext(); // ��ȡ������
		UserService service = (UserService) applicationContext
				.getBean("userService"); // Springװ��Bean
		User user = new User();
		user.setUserName("zhangsan");
		user.setPassword("123456");
		service.add(user); // ��user�������
	}

}