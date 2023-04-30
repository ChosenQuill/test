package com.todoapp.plus.todoapp.plus;

import com.todoapp.plus.todoapp.plus.models.Category;
import com.todoapp.plus.todoapp.plus.models.TodoModel;
import com.todoapp.plus.todoapp.plus.repository.CategoryRepository;
import com.todoapp.plus.todoapp.plus.repository.TodoRepository;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

@SpringBootApplication
public class TodoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoBackendApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("Todo API").version(appVersion)
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

	@Bean
	public CommandLineRunner demoData(TodoRepository todoRepository,
									  CategoryRepository categoryRepository) {
		return (args) -> {
			Category tasks = new Category("tasks");
			Category alarms = new Category("alarms");

			categoryRepository.save(tasks);
			categoryRepository.save(alarms);

			TodoModel task1 = new TodoModel("Create Spring Project",
					Date.from(Instant.now().plus(1, ChronoUnit.DAYS)),
					"Create a starter project for us to build on.", 1,
					tasks, null);
			todoRepository.save(task1);
		};
	}
}
