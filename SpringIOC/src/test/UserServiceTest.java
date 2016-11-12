package test;

import model.User;
import service.UserService;
import Spring.BeanFactory;
import Spring.ClassPathXmlApplicationContext;

// 测试代码
public class UserServiceTest {

	public void testAdd() throws Exception {
		BeanFactory applicationContext = new ClassPathXmlApplicationContext(); // 获取上下文
		UserService service = (UserService) applicationContext
				.getBean("userService"); // Spring装配Bean
		User user = new User();
		user.setUserName("zhangsan");
		user.setPassword("123456");
		service.add(user); // 将user保存入库
	}

}