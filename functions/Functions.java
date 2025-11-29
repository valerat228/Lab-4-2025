package functions;

import functions.meta.*;

//класс содержащий вспомогательные статические методы для работы с функциями
public final class Functions {
    //запрещаем создавать объекты этого класса
    private Functions() {
        throw new AssertionError("нельзя создать экземпляр класса Functions");
    }

    //создает функцию сдвинутую по осям х и у , исходная_функция(x - shiftX) + shiftY
    public static Function shift(Function f, double shiftX, double shiftY) {
        return new Shift(f, shiftX, shiftY);
    }

    //создает функцию масштабированную по осям х и у, scaleY * исходная_функция(x / scaleX)
    public static Function scale(Function f, double scaleX, double scaleY) {
        return new Scale(f, scaleX, scaleY);
    }

    //создает функцию возведенную в степень
    public static Function power(Function f, double power) {
        return new Power(f, power);
    }

    //создает функцию - сумму двух функций
    public static Function sum(Function f1, Function f2) {
        return new Sum(f1, f2);
    }

    //создает функцию - произведение двух функций
    public static Function mult(Function f1, Function f2) {
        return new Mult(f1, f2);
    }

    //создает композицию двух функций, f2(f1(x))
    public static Function composition(Function f1, Function f2) {
        return new Composition(f1, f2);
    }
}