package dto;

import dto.ContactDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Setter
@Getter
@ToString
@Builder


public class GetAllContactsDTO {
    private List<ContactDTO> contacts;
}

