package ShoppingListApp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;
import ShoppingListApp.data.services.UserServiceModel;
import ShoppingListApp.repositories.UserRepository;
import ShoppingListApp.services.UserService;
import ShoppingListApp.data.entities.UserEntity;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        userRepository.saveAndFlush(modelMapper.map(userServiceModel, UserEntity.class));
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        UserEntity userEntity = userRepository.findByUsernameAndPassword(username, password);

        if (userEntity == null) {
            return null;
        }

        return modelMapper.map(userEntity, UserServiceModel.class);
    }

    @Override
    public String exportErrorMessages(List<ObjectError> allErrors) {
        StringBuilder sb = new StringBuilder();
        allErrors.forEach(e -> sb.append(e.getDefaultMessage()).append(System.lineSeparator()));
        return sb.toString();
    }
}
