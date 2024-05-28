package ru.specialist;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

	public static void main(String[] args) throws IOException {
		Configuration cfg = new Configuration();
		Properties p = new Properties();
		
		p.load(Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("hibernate.properties"));
		cfg.setProperties(p);
		
		SessionFactory factory = cfg
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		Session session = factory.openSession();
		
		List<Course> courses = session
				.createSelectionQuery("from Course c", Course.class) // HQL
				.list(); // execute
		
		for(Course c : courses)
			System.out.println(c);
		
		/*Stream<Course> courses = session
			.createSelectionQuery("from Course c", Course.class) // HQL
			.stream(); 
		
		courses.forEach(System.out::println); // execute
		*/
		
		session.close();
		
	}

}
