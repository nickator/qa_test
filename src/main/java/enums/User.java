package enums;
import lombok.Getter;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum User {
    OTEC("otec", "heslo", AccessRights.STANDARD),
    MATKA("matka", "heslo", AccessRights.STANDARD),
    DITE("dite", "heslo", AccessRights.VIEW_0NLY),
    REST("rest", "heslo", AccessRights.REST);

    @Getter
    private final String name;

    @Getter
    private final String password;

    @Getter
    private final AccessRights accessRights;

    private static final EnumSet<User> ALL_USERS = EnumSet.allOf(User.class);
    private static final EnumSet<User> ALL_USERS_CAN_RECEIVE_MONEY = EnumSet.of(OTEC, MATKA, DITE);

    User(String name, String password, AccessRights accessRights) {
        this.name = name;
        this.password = password;
        this.accessRights = accessRights;
    }

    public static Optional<User> getRandomUserWithSpecificAccessRights(AccessRights accessRights) {
        List<User> standardUsers = ALL_USERS.stream()
                .filter(user -> user.getAccessRights().equals(accessRights))
                .collect(Collectors.toList());
        Collections.shuffle(standardUsers);
        return standardUsers.isEmpty() ? Optional.empty() : Optional.of(standardUsers.get(0));
    }

    public static User getUserByName(String userName) {
        return ALL_USERS.stream()
                .filter(user -> user.getName().equals(userName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("There is no user with name: %s!", userName)));
    }

    public static User getRandomUserToReceiveMoney(User sender) {
        return ALL_USERS_CAN_RECEIVE_MONEY.stream()
                .filter(user -> user.equals(sender))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no user available!"));
    }

}

