package com.training.spring.bigcorp;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BigcorpApplicationTests {

	@Test
	public void contextLoads() {
		Assertions.assertThat(true).isTrue();
	}

}
