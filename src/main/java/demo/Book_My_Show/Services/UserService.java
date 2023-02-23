package demo.Book_My_Show.Services;

import demo.Book_My_Show.EntryDtos.UserEntryDto;
import demo.Book_My_Show.Models.User;
import demo.Book_My_Show.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDto userEntryDto){

        //convert DTO --> object to save into repo.
        /*
            Old method : create an object and set attributes.

         */

        //set in one line only
        User user = User.builder().name(userEntryDto.getName()).email(userEntryDto.getEmail()).
                age(userEntryDto.getAge()).mobileNo(userEntryDto.getMobileNo()).address(userEntryDto.getAddress())
                .build();

        userRepository.save(user);

        return "user added successfully";
    }
}
