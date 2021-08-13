package hrms.core.utilities.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import hrms.core.utilities.validation.email.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//***********WARNING***********
//Not yet applied to the system.
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
@NoArgsConstructor
@AllArgsConstructor
public class EmailConfig {
    private String host;

    private Integer port;

    @Email
    private String username;

    private String password;
}