
package project.model;

import java.util.Date;
import java.util.List;

public class User {
    
    private int id;
    private String username;
    private String password;
    private String email;
    private Double balance;
    private String phone;
    private String namesurname;    
    private Date birthDate;
    private Cart cart;
    private List <Product> assets;
    
    //kurslara sahip olma ile ilgili düşünülecek

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNamesurname() {
        return namesurname;
    }

    public void setNamesurname(String namesurname) {
        this.namesurname = namesurname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Product> getAssets() {
        return assets;
    }

    public void setAssets(List<Product> assets) {
        this.assets = assets;
    }
    
  
}
