package by.pvt.pojo.cmd;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpCmd {

    private String name;
    private String login;
    private String password;
    private String repeatPassword;
}
