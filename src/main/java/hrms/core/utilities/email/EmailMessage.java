package hrms.core.utilities.email;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import hrms.core.utilities.validation.email.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage {
    @NotBlank
    @Email
    private List<String> toAddresses;
    
    @NotBlank
    private String subject;

    @NotBlank
    private String text;

    private Date date;

    private String path;
}