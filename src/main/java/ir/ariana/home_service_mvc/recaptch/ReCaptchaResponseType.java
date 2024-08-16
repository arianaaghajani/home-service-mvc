package ir.ariana.home_service_mvc.recaptch;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReCaptchaResponseType {

    @Getter
    private boolean success;
    private String challenge_ts;
    private String hostname;

}
