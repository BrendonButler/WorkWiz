package net.sparkzz.workwiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * The main entry point for the WorkWiz application.
 *
 * @author Brendon Butler
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class WorkWizRunner {

	/**
	 * The main entry point method for the WorkWiz application.
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(WorkWizRunner.class, args);
	}
}
