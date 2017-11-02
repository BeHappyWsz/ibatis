package demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.User;

public class Demo1 {

	public static void main(String[] args) throws IOException {
		String resource = "mybatis.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = sf.openSession();
		try{
			String str = "dao.UserMapper.findById";
			User user = session.selectOne(str, 1L);
			System.out.println(user.toString());
			
			String str1 = "dao.UserMapper.findAll";
			List<User> list = session.selectList(str1);
			for (User u : list) {
				System.out.println(u.toString());
			}
//			UserMapper mapper = session.getMapper(UserMapper.class);
//			User user = mapper.findById(1L);
		}finally{
			session.close();
		}
	}
}