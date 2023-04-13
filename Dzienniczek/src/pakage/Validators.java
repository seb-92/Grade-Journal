package pakage;

public class Validators {

    static String getName(String text) throws InvalidNameFormatException, InvalidNameAmountException{
        try{
            Integer test = 10/text.trim().length();
            text = text.trim();
            String textArray[] = text.split(" ");
            if (textArray.length < 2) {
                throw new InvalidNameAmountException();
            } else {
                return text;
            }
        }catch (ArithmeticException exception) {
            throw new InvalidNameFormatException();
        }
    }

    static Integer getGrade(String text) throws InvalidGradeFormatException, InvalidGradeValueException {
        try {
            Integer newInt = Integer.parseInt(text);
            if (newInt < 2 || newInt > 5){
                throw new InvalidGradeValueException();
            }
            return newInt;
        }catch (NumberFormatException exception){
            throw new InvalidGradeFormatException();
        }
    }
}
