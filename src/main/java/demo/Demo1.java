package demo;

import dao.UserMapper;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Demo1 {

//	public static void main(String[] args) throws IOException {
//		String resource = "mybatis.xml";
//		InputStream is = Resources.getResourceAsStream(resource);
//		SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
//		SqlSession session = sf.openSession();
//		try{
////			//主键查找
////			String str = "dao.UserMapper.findById";
////			User user = session.selectOne(str, 1L);
////			System.out.println(user.toString());
//			UserMapper mapper = session.getMapper(UserMapper.class);
////			User findById = mapper.findById(1L);
////			System.out.println(findById);
//
////			//批量插入
////			String batchInsert = "dao.UserMapper.batchInsert";
////			List<User> insertList = new ArrayList<User>();
////			for(int i = 0; i< 5 ; i++){
////				User u  = new User();
////				u.setAge(i*10L);
////				u.setName(String.valueOf(i*i*i));
////				u.setRealName(String.valueOf('a'+i));
////				u.setDes("第几个:"+i);
////				insertList.add(u);
////			}
////			int insert = session.insert(batchInsert, insertList);
////			System.out.println(insert);
////			session.commit();
//
//			//查询所有
////			String str1 = "dao.UserMapper.findAll";
////			UserMapper mapper1 = session.getMapper(UserMapper.class);
////			List<User> list = mapper1.findAll();
//////			List<User> list = session.selectList(str1);
////			for (User u : list) {
////				System.out.println(u.toString());
////			}
//
//			//单一插入
//			User i = new User();
//			i.setName("dd");
//			i.setRealName("大大");
//			i.setAge(10L);
//			i.setDes("VC可");
//			Date date = new Date();
//			int insert = mapper.insertUser(i);
//			System.out.println(insert);
//			session.commit();
//			//equal查询
////			String str3 = "dao.UserMapper.findByName";
////			List<User> selectList = session.selectList(str3, "dd");
////			System.out.println(selectList.size());
//		}finally{
//			session.close();
//		}
//	}

	public static void main(String[] args) throws Exception {
		decodePassword();
	}
	public static void decodePassword() throws IOException {
		SqlSessionFactory sqlSessionFactory = null;

		InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
		Reader reader = new InputStreamReader(inputStream);

		InputStream inputStream1 = Resources.getResourceAsStream("jdbc.properties");
		Reader reader1 = new InputStreamReader(inputStream1);

		Properties properties = new Properties();
		properties.load(reader1);
		properties.setProperty("username",decode(properties.getProperty("jdbc.username")));
		properties.setProperty("password",decode(properties.getProperty("jdbc.password")));

		synchronized (Demo1.class){
			if(sqlSessionFactory == null){
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader,properties);
			}
		}
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper mapper1 = session.getMapper(UserMapper.class);
		List<User> list = mapper1.findAll();
		for (User u : list) {
			System.out.println(u.toString());
		}
		session.close();
	}
	public static String decode(String str){
		return str;
	}
}
