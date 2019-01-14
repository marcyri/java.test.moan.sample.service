package dao;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UserDAO implements IUserDAO<User, String> {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);

    private List<User> users = new ArrayList<>();

    private List<User> init() {
        LOGGER.info("[init] Run!");
        List<User> initUsers = new ArrayList<>();
        initUsers.add(new User("101", "Ann", "200"));
        initUsers.add(new User("102", "Gerrit", "1"));
        initUsers.add(new User("103", "Yon", "200"));
        initUsers.add(new User("104", "CI", "2"));
        initUsers.add(new User("105", "Jacks", "200"));
        saveUserList(initUsers);
        LOGGER.info("[init] Finish!");
        return initUsers;
    }

    @Override
    public List<User> findAll() {
        readData();
        return users;
    }

    @Override
    public List<User> findById(String s) {
        readData();
        return Collections.singletonList(findUserById(s));
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> var1) {
        readData();
        List<S> saveUsers = new ArrayList<>();
        var1.forEach(saveUsers::add);
        addToUserData(saveUsers);
        LOGGER.info("[UsersDAO saveAll] return");
        return saveUsers;
    }

    @Override
    public <S extends User> S save(S var1) {
        readData();
        addToUserData(var1);
        return var1;
    }

    @Override
    public User delete(String var1) {
        readData();
        User userToDelete = findUserById(var1);
        List<User> leftUsers = users.stream()
                .filter(e -> e == userToDelete)
                .collect(Collectors.toList());
        saveUserList(leftUsers);
        return userToDelete;
    }

    @Override
    public Map<String, List<User>> findAllGroupByGroupId() {
        readData();
        return users.stream()
                .collect(Collectors.groupingBy(User::getGroupId));
    }

    private User findUserById(String s) {
        return users.stream()
                .filter(e -> Objects.equals(e.getId(), s))
                .findFirst()
                .get();
    }

    private void readData() {
        try {
            File file = new File("Users.dat");
            if (!file.exists()) {
                LOGGER.info("[readData] !file.exists() run init");
                users = init();
            } else {
                LOGGER.info("[readData] file.exists() get from file");
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                users = (List<User>) ois.readObject();
                ois.close();
                fis.close();
            }
        } catch (IOException e) {
            LOGGER.info("[IOException] !!!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            LOGGER.info("[ClassNotFoundException] !!!");
            e.printStackTrace();
        }
    }

    private <S extends User> void addToUserData(Iterable<S> users) {
        List<User> usersLst = new ArrayList<>();
        users.forEach(usersLst::add);
        saveUserList(usersLst);
    }

    private void addToUserData(User user) {
        saveUserList(Collections.singletonList(user));
    }

    private void saveUserList(List<User> userList){
        List<User> usersForAdd = new ArrayList<>();
        usersForAdd.addAll(userList);
        try {
            File file = new File("Users.dat");
            if (file.exists()) {
                LOGGER.info("[saveUserList] file.exists()");
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                List<User> usersFromFile = (List<User>) ois.readObject();
                usersForAdd.addAll(usersFromFile);
                LOGGER.info("[saveUserList] usersFromFile = " + usersFromFile);
                LOGGER.info("[saveUserList] read from file");
                ois.close();
                fis.close();
            }
            LOGGER.info("[saveUserList] usersForAdd = " + usersForAdd);
            FileOutputStream fos;
            fos = new FileOutputStream(file, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(usersForAdd);
            oos.flush();
            oos.close();
            fos.close();
            LOGGER.info("[saveUserList] all users add");
        } catch (FileNotFoundException e) {
            LOGGER.info("[saveUserList] [FileNotFoundException] !!!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            LOGGER.info("[saveUserList] [ClassNotFoundException] !!!");
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.info("[saveUserList] [IOException] !!!");
            e.printStackTrace();
        }
    }
}
