package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.model.Address;
import argmus.restaurantwebapp.repository.AddressRepository;
import argmus.restaurantwebapp.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address saveAddress(Address address) {
        return this.addressRepository.save(address);
    }
}
