package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.ERole;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.RoleRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;

import java.util.List;

public class RoleServiceImpl {
    private UserRepository userRepository;
    private RoleRepository roleRepository ;

    public RoleServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

}
