package service.impl;


import entity.Student;
import repository.StudentRepository;
import service.StudentService;
import util.AuthHolder;

import java.time.LocalDate;
import java.util.Random;


public class StudentServiceImpl extends BaseEntityServiceImpl<StudentRepository, Student, Integer> implements StudentService {
private  final AuthHolder authHolder;
    public StudentServiceImpl(StudentRepository repository, AuthHolder authHolder) {
        super(repository);
        this.authHolder = authHolder;
    }

    @Override
    public Student findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Student login(String username, String password) {
        if (!username.isBlank() && !password.isBlank()) return repository.login(username, password);
        return null;
    }

    @Override
    public Student save(Student entity) {
        entity.setUsername(entity.getNationalCode());
        String password = generateSecurePassword(8);
        entity.setPassword(password);
        return repository.save(entity);
    }
    public String generateSecurePassword(int length) {
        if (length < 4) {
            throw new IllegalArgumentException("Password length must be at least 4");
        }

        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String specialCharacters = "@#$%";
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        password.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
        password.append(upperCase.charAt(random.nextInt(upperCase.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        String allCharacters = lowerCase + upperCase + digits + specialCharacters;
        for (int i = 4; i < length; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }
        return shuffleString(password.toString());
    }

    private String shuffleString(String input) {
        char[] characters = input.toCharArray();
        Random random = new Random();
        for (int i = characters.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = characters[i];
            characters[i] = characters[index];
            characters[index] = temp;
        }
        return new String(characters);
    }

    @Override
    public Boolean isGraduated(LocalDate currentDate) {
        Student student = repository.findById(authHolder.getTokenId());
        Integer entryYear = student.getEntryYear();
        int diff = currentDate.getYear() - entryYear;
        switch (student.getDegree()){
            case Associate:
            case DiscontinuousBachelor:
            case DiscontinuousMaster:
            {
                if (diff>2){
                    return true;
                }
            }
            case ContinuousBachelor:
            case DisContinuousPhD:
            case ProfessionalPHD:{
                if (diff>4){
                    return true;
                }
            }
            case ContinuousMaster:{
                if (diff>6){
                    return true;
                }
            }
            case ContinuousPhD:{
                if (diff>8){
                    return true;
                }
            }
        }
        return false;
    }

}
