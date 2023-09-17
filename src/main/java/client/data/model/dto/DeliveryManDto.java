package client.data.model.dto;

import client.data.model.entity.DeliveryMan;
import client.data.model.enums.DeliveryManStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryManDto {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String image_url;
    private DeliveryManStatus status;
    private String password;
    private String phone_number;
    private final Set<Long> orders = new HashSet<>();

    public DeliveryManDto(DeliveryMan deliveryMan) {
        this.id = deliveryMan.getId();
        this.name = deliveryMan.getName();
        this.surname = deliveryMan.getSurname();
        this.login = deliveryMan.getLogin();
        this.image_url = deliveryMan.getImage_url();
        this.status = deliveryMan.getStatus();
        this.password = deliveryMan.getPassword();
        this.phone_number = deliveryMan.getPhone_number();
        if (deliveryMan.getOrders() != null) {
            for (var o : deliveryMan.getOrders()) {
                orders.add(o.getId());
            }
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getImage_url() {
        return image_url;
    }

    public DeliveryManStatus getStatus() {
        return status;
    }

    public Set<Long> getOrders() {
        return orders;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setStatus(DeliveryManStatus status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
