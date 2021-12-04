package groupProjectPart1.ui;

import java.sql.*;
import java.util.ArrayList;
import org.apache.commons.validator.routines.EmailValidator;


public class MysqlRepository implements DoctorRepository{
    private final static String url = "jdbc:mysql://localhost:3306/hospital_base";
    private final static String user = "root";
    private final static String password = "root";

    // все пароли и емэйлы заранее известны и внесены в базу по условиям задачи
    //пытаемся войти в аккаунт, проверяем верны ли пароль и емэйл
    public Doctor checkAuthorization(String email, String password) throws IncorrentDataForAuthorization {
        //проверка корректности почты
        EmailValidator validator = EmailValidator.getInstance();
        if(validator.isValid(email)){
            //проверка есть ли такой аккаунт
            try (Connection connection = DriverManager.getConnection(url, user, password);
                 Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("select * from" +
                        " `hospital_base`.`doctors_accounts` where email='"
                        + email+ "' and password='"+password+"';");
                if (resultSet.next()) {
                    Doctor doctor = new Doctor();
                    doctor.setId(resultSet.getLong("id"));
                    doctor.setEmail(resultSet.getString("email"));
                    doctor.setPassword(resultSet.getString("password"));
                    doctor.setFirstName(resultSet.getString("first_name"));
                    doctor.setSecondName(resultSet.getString("second_name"));
                    doctor.setLastName(resultSet.getString("last_name"));
                    doctor.setSpecialization(resultSet.getString("specialization"));
                    doctor.setWorkingDays(resultSet.getString("working_days"));
                    doctor.setWorkingHours(resultSet.getString("working_hours"));
                    doctor.setPhoto(resultSet.getString("photo"));
                    return doctor;
                } else {
                    throw new IncorrentDataForAuthorization("Incorrent data.");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            throw new IncorrentDataForAuthorization("Invalid email.");
        }
        return null;
    }

    // проверка пользователь первый раз заходит или нет (есть ли в базе данные из формы или только его пароль и почта)
    public boolean checkFirstVisit(Doctor doctor){
        //так как фамилия  - это обязательное для заполнения поле в форме
        return doctor.getLastName() == null;
    }

    @Override
    public Iterable<Doctor> findAll() {
        // можно посмотреть всех врачей (скорее всего эта функция не пригодится :)
        ArrayList<Doctor> doctors = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from `hospital_base`.`doctors_accounts`;")) {
            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(resultSet.getLong("id"));
                doctor.setEmail(resultSet.getString("email"));
                doctor.setPassword(resultSet.getString("password"));
                doctor.setFirstName(resultSet.getString("first_name"));
                doctor.setSecondName(resultSet.getString("second_name"));
                doctor.setLastName(resultSet.getString("last_name"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                doctor.setWorkingDays(resultSet.getString("working_days"));
                doctor.setWorkingHours(resultSet.getString("working_hours"));
                doctor.setPhoto(resultSet.getString("photo"));
                doctors.add(doctor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return doctors;
    }

    @Override
    public Doctor save(Doctor doctor) {
        // для сохранения данных с формы в базу
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String str1 = "UPDATE `hospital_base`.`doctors_accounts` SET `last_name`" +
                    " = '"+doctor.getLastName()+"', `first_name` = '"
                    +doctor.getFirstName()+"', `second_name` = '"
                    +doctor.getSecondName()+"', `specialization` = '"
                    +doctor.getSpecialization()+"', `working_days` = '"
                    +doctor.getWorkingDays()+"', `working_hours` = '"
                    +doctor.getWorkingHours()+"', `photo` = '"
                    +doctor.getPhoto()+"' WHERE (`id` = '"
                    +doctor.getId()+"');";
            statement.executeUpdate(str1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctor;
    }


    @Override
    public Doctor findDoctor(Long id) {
        //по id находим нужного доктора в базе и возвращаем объект доктор
        Doctor doctor = new Doctor();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from" +
                    " `hospital_base`.`doctors_accounts` where id='"
                    + id + "';");
            if (resultSet.next()) {

                doctor.setId(id);
                doctor.setEmail(resultSet.getString("email"));
                doctor.setPassword(resultSet.getString("password"));
                doctor.setFirstName(resultSet.getString("first_name"));
                doctor.setSecondName(resultSet.getString("second_name"));
                doctor.setLastName(resultSet.getString("last_name"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                doctor.setWorkingDays(resultSet.getString("working_days"));
                doctor.setWorkingHours(resultSet.getString("working_hours"));
                doctor.setPhoto(resultSet.getString("photo"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return doctor;
    }
}
