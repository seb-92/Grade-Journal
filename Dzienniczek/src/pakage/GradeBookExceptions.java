package pakage;

abstract class GradeBookExceptions extends Exception {}

class InvalidGradeFormatException extends GradeBookExceptions{
    @Override
    public String toString() {
        return "Niewłaściwy format oceny";
    }
}

class InvalidGradeValueException extends GradeBookExceptions{
    @Override
    public String toString() {
        return "Błędna ocena";
    }
}

class InvalidNameFormatException extends GradeBookExceptions{
    @Override
    public String toString() {
        return "Puste pole Imię i Nazwisko";
    }
}

class InvalidNameAmountException extends GradeBookExceptions{
    @Override
    public String toString() {
        return "Podano tylko Imię";
    }
}
