package demo.Book_My_Show.Services;

import demo.Book_My_Show.Converters.UserConverter;
import demo.Book_My_Show.DTOs.EntryDtos.UserEntryDto;
import demo.Book_My_Show.Models.User;
import demo.Book_My_Show.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDto userEntryDto) throws Exception{

        //convert DTO --> object to save into repo.
        /*
            Old method : create an object and set attributes.

         */

        //set in one line only
//        User user = User.builder().name(userEntryDto.getName()).email(userEntryDto.getEmail()).age(userEntryDto.getAge()).mobileNo(userEntryDto.getMobileNo()).address(userEntryDto.getAddress()).build();

        //converter function called
        User user = UserConverter.convertEntryDtoToEntity(userEntryDto);

        userRepository.save(user);

        return "user added successfully";
    }
}
