package demo.Book_My_Show.Converters;

import demo.Book_My_Show.DTOs.EntryDtos.UserEntryDto;
import demo.Book_My_Show.Models.User;

public class UserConverter {

    public static User convertEntryDtoToEntity(UserEntryDto userEntryDto){

        User user = User.builder().name(userEntryDto.getName()).email(userEntryDto.getEmail()).
                age(userEntryDto.getAge()).mobileNo(userEntryDto.getMobileNo()).address(userEntryDto.getAddress())
                .build();

        return user;
    }
}
