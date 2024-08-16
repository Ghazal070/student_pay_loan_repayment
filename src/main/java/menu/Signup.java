package menu;

import entity.Student;
import entity.University;
import entity.enumration.AcceptanceType;
import entity.enumration.Degree;
import entity.enumration.UniversityType;
import menu.util.Input;
import menu.util.Message;
import service.StudentService;

import java.time.LocalDate;

public class Signup {
    private final Input input;
    private final StudentService studentService;
    private final Message message;

    public Signup(Input input, StudentService studentService, Message message) {
        this.input = input;
        this.studentService = studentService;
        this.message = message;
    }

    public void show() {
        signup:
        while (true) {
            System.out.println("""
                    1- Enter information
                    2- Previous menu                
                    """);
            switch (input.scanner.next()) {
                case "1": {
                    System.out.println(message.getInputMassage("your information"));
                    String firstname = getInputData("firstname");
                    String lastname = getInputData("lastname");
                    String fatherName = getInputData("fatherName");
                    String motherName = getInputData("motherName");
                    String nationalCode = getInputData("nationalCode");
                    String certificateNumber = getInputData("certificateNumber");
                    String studentNumber = getInputData("studentNumber");
                    String birthDate = getInputData("Birth date must be in the format YYYY-MM-DD");
                    String entryYear = getInputData("entryYear(1990)");
                    String universityName = getInputData("UniversityName");
                    String degreeInput = getInputData("""
                            Please enter degree from below: Associate, ContinuousBachelor,
                            DiscontinuousBachelor,ContinuousMaster, DiscontinuousMaster,
                            ContinuousPhD,DisContinuousPhD, ProfessionalPHD
                            """);
                    String universityTypeInput = getInputData("Please enter UniversityType: Azad, Governmental");
                    String acceptanceTypeInput = getInputData("Please enter AcceptanceType: Daily, Nightly");
                    Degree degree = getEnumFromString(Degree.class, degreeInput);
                    UniversityType universityType = getEnumFromString(UniversityType.class, universityTypeInput);
                    AcceptanceType acceptanceType = getEnumFromString(AcceptanceType.class, acceptanceTypeInput);

                    if (degree == null || universityType == null || acceptanceType == null) {
                        System.out.println("One or more inputs were invalid. Please try again.");
                        break;
                    }
                    Student student = Student.builder()
                            .firstName(firstname)
                            .lastName(lastname)
                            .fatherName(fatherName)
                            .motherName(motherName)
                            .nationalCode(nationalCode)
                            .certificateNumber(certificateNumber)
                            .birthDate(birthDate)
                            .studentNumber(studentNumber)
                            .degree(degree)
                            .entryYear(Integer.valueOf(entryYear))
                            .university(University.builder()
                                    .name(universityName)
                                    .universityType(universityType)
                                    .acceptanceType(acceptanceType)
                                    .build())
                            .build();

                    Student saveStudent = studentService.save(student);
                    if (saveStudent != null) {
                        System.out.println(message.getSuccessfulMassage(saveStudent.getFirstName()));
                        System.out.println("Your username is " + saveStudent.getUsername());
                        System.out.println("Your password is " + saveStudent.getPassword());
                    } else {
                        System.out.println(message.getFailMassage("save student"));
                    }
                    break;
                }
                case "2": {
                    break signup;
                }
                default:
                    System.out.println(message.getInvalidMassage());
            }
        }
    }

    private String getInputData(String prompt) {
        System.out.println(message.getInputMassage(prompt));
        return input.scanner.next();
    }
    public <E extends Enum<E>> E getEnumFromString(Class<E> enumClass, String value) {
        try {
            return Enum.valueOf(enumClass, value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
