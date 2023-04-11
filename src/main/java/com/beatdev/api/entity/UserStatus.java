package com.beatdev.api.entity;

import java.util.stream.Stream;

/**
 * UserStatus enum class for User entity.
 */
public enum UserStatus {

    OFFLINE(0),
    ONLINE(1);

    final int number;

    UserStatus(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public static UserStatus of(int number) {
        return Stream.of(UserStatus.values())
                .filter(p -> p.getNumber() == number)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
