package com.beatdev.api.entity.converter;

import com.beatdev.api.entity.UserStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * UserStatusConverter class for convert UserStatus enum to integer for database.
 */
@Converter(autoApply = true)
public class UserStatusConverter implements AttributeConverter<UserStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserStatus orderStatus) {
        return orderStatus == null ? -1 : orderStatus.getNumber();
    }

    @Override
    public UserStatus convertToEntityAttribute(Integer integer) {
        return UserStatus.of(integer);
    }
}
