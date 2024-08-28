package menu;

import entity.City;
import entity.Student;
import entity.University;
import entity.enumration.Degree;
import entity.enumration.UniversityType;
import exception.ValidationException;
import menu.util.Input;
import menu.util.Message;
import service.CityService;
import service.StudentService;
import util.AuthHolder;

public class Signup {
    private final Input input;
    private final StudentService studentService;
    private final Message message;
    private final Signin signin;
    private final AuthHolder authHolder;
    private final CityService cityService;

    public Signup(Input input, StudentService studentService, Message message, Signin signin, AuthHolder authHolder, CityService cityService) {
        this.input = input;
        this.studentService = studentService;
        this.message = message;
        this.signin = signin;
        this.authHolder = authHolder;
        this.cityService = cityService;
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
                    try{
                        System.out.println(message.getInputMassage("your information"));
                        String firstname = getInputData("firstname");
                        String lastname = getInputData("lastname");
                        String fatherName = getInputData("fatherName");
                        String motherName = getInputData("motherName");
                        String nationalCode = getInputData("nationalCode(3 digits)");
                        String certificateNumber = getInputData("certificateNumber(must be between 1 and 8)");
                        String studentNumber = getInputData("studentNumber(3 digits)");
                        String birthDate = getInputData("Birth date must be in the format YYYY-MM-DD");
                        String entryYear = getInputData("entryYear(1402)");
                        String universityName = getInputData("UniversityName");
                        String isDormitory = getInputData("isDormitory (yes/no)");
                        Boolean isDormitoryBoolean = getYesNo(isDormitory);
                        String cityName = getInputData("city Name");
                        City city =cityService.findByUniqId(cityName);
                        String degreeInput = getInputData("""
                            Please enter degree from below: Associate, ContinuousBachelor,
                            DiscontinuousBachelor,ContinuousMaster, DiscontinuousMaster,
                            ContinuousPhD,DisContinuousPhD, ProfessionalPHD
                            """);
                        String universityTypeInput = getInputData("""
                            Please enter UniversityType:
                            Azad, DolatiShabane, DolatiRuzane,PayameNur,
                            GHeirEntefaee,Pardis,Mazad,ElmiKarbordi
                            """);
                        Degree degree = getEnumFromString(Degree.class, degreeInput);
                        UniversityType universityType = getEnumFromString(UniversityType.class, universityTypeInput);

                        if (degree == null || universityType == null) {
                            System.out.println("One or more inputs were invalid. Please try again.");
                            break;
                        }
                        if (city==null){
                            city= City.builder().name(cityName).build();
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
                                .isDormitory(isDormitoryBoolean)
                                .city(city)
                                .university(University.builder()
                                        .name(universityName)
                                        .universityType(universityType)
                                        .build())
                                .build();

                        Student saveStudent = studentService.save(student);
                        if (saveStudent != null) {
                            System.out.println(message.getSuccessfulMassage(saveStudent.getFirstName()));
                            System.out.println("Your username is " + saveStudent.getUsername());
                            System.out.println("Your password is " + saveStudent.getPassword());
                            authHolder.tokenId = saveStudent.getId();
                            authHolder.tokenName = saveStudent.getUsername();
                            signin.show();
                        } else {
                            System.out.println(message.getFailMassage("save student"));
                        }
                    }
                    catch (ValidationException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (RuntimeException e) {
                        System.out.println("An unexpected error occurred: " + e.getMessage());
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

    public String getInputData(String prompt) {
        System.out.println(message.getInputMassage(prompt));
        return input.scanner.next();
    }

    private Boolean getYesNo(String prompt) {
        if (prompt.equals("yes")) return true;
        return false;
    }

    public <E extends Enum<E>> E getEnumFromString(Class<E> enumClass, String value) {
        try {
            return Enum.valueOf(enumClass, value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
