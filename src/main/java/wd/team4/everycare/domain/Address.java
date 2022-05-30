package wd.team4.everycare.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import wd.team4.everycare.dto.address.AddressFormDTO;
import wd.team4.everycare.util.StringUtils;

import javax.persistence.*;

@Getter
@Entity
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "adress_seq_generator",
        sequenceName = "adress_seq",
        initialValue = 1, allocationSize = 1)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adress_seq_generator")
    @Column(name = "adress_id")
    private Long id;

    @Column(name = "address_recipientName")
    private String recipientName;

    @Column(name = "address_recipientNumber")
    private int recipientNumber;

    @Column(name = "address_zipcode", length = 45)
    private String zipcode;

    @Column(name = "address_line")
    private String address;

    @Column(name = "address_detail")
    private String detailedAddress;

    @Column(name = "address_delivery_comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void update(AddressFormDTO addressFormDTO) {
        if(StringUtils.isNotBlank(addressFormDTO.getRecipientName())) {
            this.recipientName = addressFormDTO.getRecipientName();
        }
        if(StringUtils.isNotBlank(String.valueOf(addressFormDTO.getRecipientNumber()))){
            this.recipientNumber = addressFormDTO.getRecipientNumber();
        }
        if(StringUtils.isNotBlank(addressFormDTO.getZipcode())) {
            this.zipcode = addressFormDTO.getZipcode();
        }
        if(StringUtils.isNotBlank(addressFormDTO.getAddress())) {
            this.address = addressFormDTO.getAddress();
        }
        if(StringUtils.isNotBlank(addressFormDTO.getComment())) {
            this.comment = addressFormDTO.getComment();
        }
        if(StringUtils.isNotBlank(addressFormDTO.getDetailedAddress())) {
            this.detailedAddress = addressFormDTO.getDetailedAddress();
        }
    }
}
