package demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
//			//主键查找
//			String str = "dao.UserMapper.findById";
//			User user = session.selectOne(str, 1L);
//			System.out.println(user.toString());
			
			//批量插入
			String batchInsert = "dao.UserMapper.batchInsert";
			List<User> insertList = new ArrayList<User>();
			for(int i = 0; i< 5 ; i++){
				User u  = new User();
				u.setAge(i*10L);
				u.setName(String.valueOf(i*i*i));
				u.setRealName(String.valueOf('a'+i));
				u.setDes("第几个:"+i);
				insertList.add(u);
			}
			int insert = session.insert(batchInsert, insertList);
			System.out.println(insert);
			session.commit();
			
			//查询所有
			String str1 = "dao.UserMapper.findAll";
			List<User> list = session.selectList(str1);
			for (User u : list) {
				System.out.println(u.toString());
			}
			
			//单一插入
//			String str2 = "dao.UserMapper.insertUser";
//			User i = new User();
//			i.setName("dd");
//			i.setRealName("大大");
//			i.setAge(10L);
//			i.setDes("VC可");
//			int insert = session.insert(str2, i);
//			System.out.println(insert);
//			session.commit();
			//equal查询
//			String str3 = "dao.UserMapper.findByName";
//			List<User> selectList = session.selectList(str3, "dd");
//			System.out.println(selectList.size());
		}finally{
			session.close();
		}
	}
}
