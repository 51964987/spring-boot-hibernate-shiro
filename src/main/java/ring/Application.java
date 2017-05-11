package ring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages="ring")
@ServletComponentScan // 扫描使用注解方式的servlet
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}