package groupProjectPart1.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SampleWebUiApplication {

	@Bean
	public DoctorRepository doctorRepository() {
		return new MysqlRepository();
	}

	@Bean
	public Converter<String, Doctor> messageConverter() {
		return new Converter<String, Doctor>() {
			@Override
			public Doctor convert(String id) {
				return doctorRepository().findDoctor(Long.valueOf(id));
			}
		};
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleWebUiApplication.class, args);
	}

}
