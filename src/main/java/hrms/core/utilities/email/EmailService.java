package hrms.core.utilities.email;

import hrms.core.utilities.results.Result;

public interface EmailService {
    Result send(EmailMessage emailMessage);
}