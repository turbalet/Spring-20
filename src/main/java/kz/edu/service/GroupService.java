package kz.edu.service;

import kz.edu.dao.GroupRepository;
import kz.edu.model.Group;
import kz.edu.service.interfaces.IEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupService implements IEntityService<Group> {

    private final GroupRepository groupRepository;


    @Override
    public void add(Group entity) {
        groupRepository.save(entity);
    }

    @Override
    public void update(Group entity) {
        groupRepository.save(entity);
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group getById(long id) {
        return groupRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(long id) {
        groupRepository.deleteById(id);
    }
}
