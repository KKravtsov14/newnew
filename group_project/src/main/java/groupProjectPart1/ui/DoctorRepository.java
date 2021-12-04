package groupProjectPart1.ui;


public interface DoctorRepository {

    Iterable<Doctor> findAll();

    Doctor save(Doctor doctor);

    Doctor findDoctor(Long id);

}