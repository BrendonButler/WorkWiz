package net.sparkzz.workwiz;

import net.sparkzz.workwiz.repository.ShoppingItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

@SpringBootTest(properties = {
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
})
class WorkWizRunnerTests {

	@MockBean
	private ShoppingItemRepository repository;

	@Test
	void contextLoads() {
		Mockito.when(repository.findAll()).thenReturn(Collections.emptyList());
	}
}
