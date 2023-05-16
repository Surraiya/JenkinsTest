package Framework.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private String firstname;
    private String lastname;
    private int age;
    private String email;
    private int salary;
    private String department;
}
