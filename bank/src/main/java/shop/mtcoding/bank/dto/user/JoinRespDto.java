package shop.mtcoding.bank.dto.user;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.bank.domain.user.User;

@Getter
@Setter
public class JoinRespDto {
    private Long id;
    private String username;
    private String fullname;

    public JoinRespDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.fullname = user.getFullname();
    }
}
