//package validation;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.util.Map;
//
//public class Validator {
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    public static boolean isValidPattern(String input, String type) {
//        try {
//            Map<String, Object> map = objectMapper.readValue(input, Map.class);
//            switch (type) {
//                case "course":
//                case "courseUpdate":
//                    return map.containsKey("title") ||
//                            map.containsKey("unitCount") ||
//                            map.containsKey("uniqIdDepartment");
//                case "courseTeacher":
//                    return map.containsKey("title") ||
//                            map.containsKey("uniqIdCourse");
//                case "courseTeacherUpdate":
//                    return map.containsKey("courseUniqId") ||
//                            map.containsKey("teacherUniqId") ||
//                            map.containsKey("termUniqId");
//                default:
//                    return false;
//            }
//        } catch (Exception e) {
//            return false;
//        }
//    }
//}