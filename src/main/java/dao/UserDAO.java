package dao;

import model.User;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class UserDAO implements IUserDAO<User, String> {

    private List<User> users = new ArrayList<>();
    {
        users = readData();
    }

    private List<User> init() {
        List<User> initUsers = new ArrayList<>();
        initUsers.add(new User("101", "Ann", "200"));
        initUsers.add(new User("102", "Gerrit", "1"));
        initUsers.add(new User("103", "Yon", "200"));
        initUsers.add(new User("104", "CI", "2"));
        initUsers.add(new User("105", "Jacks", "200"));
        saveUserList(initUsers, false);
        return initUsers;
    }

    @Override
    public List<User> findAll() {
        users = readData();
        return users;
    }

    @Override
    public List<User> findById(String s) {
        users = readData();
        return Collections.singletonList(findUserById(s));
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> var1) {
        List<S> saveUsers = new ArrayList<>();
        var1.forEach(saveUsers::add);
        addToUserData(saveUsers);
        return saveUsers;
    }

    @Override
    public <S extends User> S save(S var1) {
        addToUserData(var1);
        return var1;
    }

    @Override
    public User delete(String var1) {
        users = readData();
        User userToDelete = findUserById(var1);
        List<User> leftUsers = users.stream()
                .filter(e -> e != userToDelete)
                .collect(Collectors.toList());
        saveUserList(leftUsers, false);
        users.remove(userToDelete);
        return userToDelete;
    }

    @Override
    public Map<String, List<User>> findAllGroupByGroupId() {
        return users.stream()
                .collect(Collectors.groupingBy(User::getGroupId));
    }

    private User findUserById(String s) {
        return users.stream()
                .filter(e -> Objects.equals(e.getId(), s))
                .findFirst()
                .get();
    }

    private List<User> readData() {
        List<User> readUsers = new ArrayList<>();
        try {
            File file = new File("Users.dat");
            if (!file.exists()) {
                readUsers = init();
            } else {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                readUsers = (List<User>) ois.readObject();
                ois.close();
                fis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return readUsers;
    }

    private <S extends User> void addToUserData(Iterable<S> users) {
        List<User> usersLst = new ArrayList<>();
        users.forEach(usersLst::add);
        saveUserList(usersLst, true);
    }

    private void addToUserData(User user) {
        saveUserList(Collections.singletonList(user), true);
    }

    private void saveUserList(List<User> userList, Boolean append){
        List<User> usersForAdd = new ArrayList<>(userList);
        try {
            File file = new File("Users.dat");
            if (append && file.exists()) {
                List<User> usersFromFile = readData();
                usersForAdd.addAll(usersFromFile);
            }

            file = new File("Users.dat");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(usersForAdd);
            oos.flush();
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
