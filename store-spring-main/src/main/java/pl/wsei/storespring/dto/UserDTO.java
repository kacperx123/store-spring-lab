package pl.wsei.storespring.dto;

import jakarta.validation.constraints.*;
import pl.wsei.storespring.model.User;

public class UserDTO {
    @Null(message = "ID cannot be modified")
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Size(min = 2, message = "Surname must be at least 2 characters long")
    private String surname;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Login must contain only letters and numbers")
    private String login;

    @Email(message = "Email should be in a valid format")
    private String email;
    private Long basketId;

    public UserDTO() {}

    public UserDTO(Long id, String name, String surname, String login, String email, Long basketId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.email = email;
        this.basketId = basketId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }

    public static UserDTO fromEntity(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getLogin(),
                user.getEmail(),
                user.getBasket() != null ? user.getBasket().getId() : null
        );
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());
        return user;
    }
}

