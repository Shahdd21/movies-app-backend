package com.moviesappbackend.demo.enums;

import jakarta.persistence.AttributeConverter;

public class UserRoleConverter implements AttributeConverter<UserRole, String> {
    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        if(userRole == null) return null;

        return userRole.name();
    }

    @Override
    public UserRole convertToEntityAttribute(String dbValue) {
        if(dbValue == null) return null;

        for(UserRole role : UserRole.values()){
            if(role.name().equalsIgnoreCase(dbValue)) return role;
        }

        throw new IllegalArgumentException("Unknown role: " + dbValue);
    }
}
