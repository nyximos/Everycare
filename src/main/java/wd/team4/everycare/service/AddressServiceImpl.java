package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Address;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.address.AddressFormDTO;
import wd.team4.everycare.dto.address.AddressViewDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.AddressRepository;
import wd.team4.everycare.service.interfaces.AddressService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public ResponseEntity<MyResponse> getAll(PrincipalDetails principalDetails) {

        Member user = principalDetails.getUser();

        List<Address> addresses = addressRepository.findAllByMember(user);
        List<AddressViewDTO> addressViewDTOs = new ArrayList<>();

        if(addresses.isEmpty()) {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("저장된 주소가 없습니다.")
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }

        for (Address address : addresses) {
            AddressViewDTO dto = AddressViewDTO.builder()
                    .id(address.getId())
                    .recipientName(address.getRecipientName())
                    .recipientNumber(address.getRecipientNumber())
                    .zipcode(address.getZipcode())
                    .address(address.getAddress())
                    .detailedAddress(address.getDetailedAddress())
                    .comment(address.getComment())
                    .build();

            addressViewDTOs.add(dto);
        }

        MyResponse<List<AddressViewDTO>> body = MyResponse.<List<AddressViewDTO>>builder()
                .header(StatusEnum.OK)
                .body(addressViewDTOs)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> get(Long id) {

        Optional<Address> address = addressRepository.findById(id);
        Address addressEntity = address.orElse(null);

        AddressViewDTO addressViewDTO = AddressViewDTO.builder()
                .id(addressEntity.getId())
                .recipientName(addressEntity.getRecipientName())
                .recipientNumber(addressEntity.getRecipientNumber())
                .zipcode(addressEntity.getZipcode())
                .address(addressEntity.getAddress())
                .detailedAddress(addressEntity.getDetailedAddress())
                .comment(addressEntity.getComment())
                .build();

        MyResponse<AddressViewDTO> body = MyResponse.<AddressViewDTO>builder()
                .header(StatusEnum.OK)
                .body(addressViewDTO)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> save(PrincipalDetails principalDetails, AddressFormDTO addressFormDTO) {

        Address address = Address.builder()
                .recipientName(addressFormDTO.getRecipientName())
                .recipientNumber(addressFormDTO.getRecipientNumber())
                .zipcode(addressFormDTO.getZipcode())
                .address(addressFormDTO.getAddress())
                .detailedAddress(addressFormDTO.getDetailedAddress())
                .comment(addressFormDTO.getComment())
                .member(principalDetails.getUser())
                .build();

        addressRepository.save(address);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> update(Long id, AddressFormDTO addressFormDTO) {

        Optional<Address> address = addressRepository.findById(id);
        Address addressEntity = address.orElse(null);

        addressEntity.update(addressFormDTO);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> remove(Long id) {

        addressRepository.deleteById(id);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}
