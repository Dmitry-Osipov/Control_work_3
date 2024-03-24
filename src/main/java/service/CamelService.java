package service;

import annotation.annotations.Component;
import annotation.annotations.InjectByInterface;
import dao.IDao;
import dao.JdbcDao;
import entity.Camel;
import utils.AnimalBirthDateComparator;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CamelService implements IService<Camel> {
    @InjectByInterface(clazz = JdbcDao.class)
    private IDao dao;

    @Override
    public void add(Camel entity) throws SQLException, IllegalAccessException {
        dao.save(entity);
    }

    @Override
    public List<String> getAllCommands(int id) throws SQLException, NoSuchFieldException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return dao.getOne(id, Camel.class).getCommands();
    }

    @Override
    public void addNewCommand(Camel entity, String command) throws SQLException, IllegalAccessException {
        List<String> list = new ArrayList<>(entity.getCommands());
        entity.setCommands(list);
        entity.getCommands().add(command);
        dao.update(entity);
    }

    @Override
    public List<Camel> getAllAnimals() throws SQLException, NoSuchFieldException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return dao.getAll(Camel.class).stream().sorted(new AnimalBirthDateComparator()).toList();
    }

    @Override
    public int getTotalAnimals() throws SQLException, NoSuchFieldException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        return dao.getAll(Camel.class).size();
    }
}
