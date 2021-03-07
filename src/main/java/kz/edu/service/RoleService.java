package kz.edu.service;

import kz.edu.dao.RoleRepository;
import kz.edu.model.Role;
import kz.edu.service.interfaces.IEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService implements IEntityService<Role> {

    private final RoleRepository roleRepository;


    @Override
    public void add(Role entity) {
        roleRepository.save(entity);
    }

    @Override
    public void update(Role entity) {
        roleRepository.save(entity);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(long id) {
        roleRepository.deleteById(id);
    }

    public Role getByName(String name){
        return roleRepository.getByName(name);
    }
}
