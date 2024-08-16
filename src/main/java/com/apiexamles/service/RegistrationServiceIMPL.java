package com.apiexamles.service;

import com.apiexamles.entity.Registration;
import com.apiexamles.exception.ResourceNotFound;
import com.apiexamles.payload.RegistrationDto;
import com.apiexamles.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RegistrationServiceIMPL implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public RegistrationServiceIMPL(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public RegistrationServiceIMPL() {
    }

    @Override
    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        Registration registration = mapToEntity(registrationDto);
        Registration savedEntity = registrationRepository.save(registration);
        return mapToDto(savedEntity);

    }

    @Override
    public void deleteRegisrationById(long id) {

    }

    @Override
    public RegistrationDto updateRegistration(long id, RegistrationDto registrationDto) {
        Optional<Registration> opReg = registrationRepository.findById(id);
        if (opReg.isPresent()) {
            Registration registration = opReg.get();
            registration.setName(registrationDto.getName());
            registration.setEmail(registrationDto.getEmail());
            registration.setMobile(registrationDto.getMobile());
            Registration savedEntity = registrationRepository.save(registration);
            RegistrationDto dto = mapToDto(registration);
            return dto;
        } else {

            throw new IllegalArgumentException("Registration entity with id " + id + " does not exist");
        }
    }

    @Override
    public List<RegistrationDto> getAllRegistrations(int pageNo, int pageSize, String sortBy, String sortDir) {
//        List<Registration> registrations = registrationRepository.findAll();
//        Ternary Operator

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(Sort.Direction.ASC, sortBy)
                : Sort.by(Sort.Direction.DESC, sortDir);
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Registration> all = registrationRepository.findAll(pageable);
        List<Registration> registrations = all.getContent();
        System.out.println(all.getTotalPages());
        System.out.println(all.isLast());
        System.out.println(all.isFirst());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        return registrations.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RegistrationDto getAllRegistrationsById(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Registration not found with Id:" + id)
        );

        RegistrationDto registrationDto = mapToDto(registration);
        return registrationDto;
    }


    public void deleteRegistrationById(Long id) {
        registrationRepository.deleteById(id);
    }


    Registration mapToEntity(RegistrationDto dto){
        Registration entity = new Registration();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setMobile(dto.getMobile());
        return entity;
    }

        RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto = new RegistrationDto();
        dto.setId(registration.getId());
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setMobile(registration.getMobile());
        return dto;
    }

}
