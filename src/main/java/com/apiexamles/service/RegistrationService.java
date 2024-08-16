package com.apiexamles.service;

import com.apiexamles.payload.RegistrationDto;

import java.util.List;

public interface RegistrationService {

    RegistrationDto createRegistration(RegistrationDto registrationDto);

    void deleteRegisrationById(long id);

    RegistrationDto updateRegistration(long id, RegistrationDto registrationDto);

    List<RegistrationDto> getAllRegistrations(int pageNo, int pageSize, String sortBy, String sortDir);

    RegistrationDto getAllRegistrationsById(long id);
}
