package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

/**
 * Spring Application starts here.
 *
 */

@SpringBootApplication
public class App extends SpringBootServletInitializer {
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(App.class, args);
		System.out.println(applicationContext.containsBean("gameController"));
	}
}
