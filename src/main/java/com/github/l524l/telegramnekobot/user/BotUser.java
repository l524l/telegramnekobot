package com.github.l524l.telegramnekobot.user;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bot_user")
public class BotUser {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    @ColumnDefault("'none'")
    private String firstName;

    @Column(name = "last_name")
    @ColumnDefault("'none'")
    private String lastName;

    @Column(name = "user_short_name")
    @ColumnDefault("'none'")
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="settings_uuid")
    private UserSettings userSettings;

    @Enumerated
    @ElementCollection
    @CollectionTable(name = "user_roles")
    private List<UserRole> roles;

    @Column(name = "banned")
    @ColumnDefault("false")
    private boolean banned;

    public BotUser(){}

    public BotUser(long id, String firstName, String lastName, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.userSettings = new UserSettings();
        this.roles = new ArrayList<>();
        this.roles.add(UserRole.USER);
    }
    public BotUser(long id, String firstName){
        this(id, firstName,null, null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public void addRole(UserRole role) {
        if (!roles.contains(role)){
            roles.add(role);
        }
    }

    public void removeRole(UserRole role) {
        roles.remove(role);
    }

    public boolean hasRole(UserRole role) {
        return roles.contains(role);
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
