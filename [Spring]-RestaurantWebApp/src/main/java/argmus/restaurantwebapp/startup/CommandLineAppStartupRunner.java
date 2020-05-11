package argmus.restaurantwebapp.startup;

import argmus.restaurantwebapp.exception.InitRolesFailedException;
import argmus.restaurantwebapp.model.Role;
import argmus.restaurantwebapp.service.RoleService;
import argmus.restaurantwebapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    public CommandLineAppStartupRunner(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Role> roles = this.roleService.initRoles();

        if (!roles.isEmpty())
            this.userService.initAdmin(roles.get(0));
        else
            throw new InitRolesFailedException();
    }
}
