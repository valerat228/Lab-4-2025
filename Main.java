import functions.FunctionPoint;
import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.InappropriateFunctionPointException;
import functions.Function;
import functions.Functions;
import functions.TabulatedFunctions;
import functions.basic.*;
import functions.meta.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws InappropriateFunctionPointException {
        // Твои оригинальные тесты 1-9
        System.out.println("=== ТЕСТ 1: СОЗДАНИЕ ФУНКЦИИ ===");
        double[] values = {0, 1, 4, 9, 16};
        TabulatedFunction func = new ArrayTabulatedFunction(0, 4, values);

        // Выводим все точки после создания
        System.out.println("Точки после создания функции:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }

        System.out.println("\n=== ТЕСТ 2: ВЫЧИСЛЕНИЕ ЗНАЧЕНИЙ ===");
        double[] testX = {-1, 0, 1.5, 4, 5};
        for (double x : testX) {
            double y = func.getFunctionValue(x);
            if (Double.isNaN(y)) {
                System.out.println("f(" + x + ") = не определено");
            } else {
                System.out.println("f(" + x + ") = " + y);
            }
        }

        System.out.println("\n=== ТЕСТ 3: ДОБАВЛЕНИЕ И УДАЛЕНИЕ ===");

        // Добавление точки
        System.out.println("Добавляем точку (2.5, 6.25)");
        func.addPoint(new FunctionPoint(2.5, 6.25));
        System.out.println("Точки после добавления:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }
        System.out.println("Количество точек: " + func.getPointsCount());

        // Удаление точки
        System.out.println("\nУдаляем точку с индексом 1");
        func.deletePoint(1);
        System.out.println("Точки после удаления:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }
        System.out.println("Количество точек: " + func.getPointsCount());

        // Изменение Y точки
        System.out.println("\nИзменяем Y точки с индексом 2 на 10.0");
        func.setPointY(2, 10.0);
        System.out.println("Точки после изменения Y:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }

        // Попытка добавить точку с существующим X
        System.out.println("\nПытаемся добавить точку (2.0, 100.0) - должна быть отклонена");
        try{
            func.addPoint(new FunctionPoint(2.0, 100.0));
            System.out.println("исключение не выброшено");
        } catch (InappropriateFunctionPointException e) {
            System.out.println("ошибка: " + e.getMessage());
        }
        System.out.println("Точки после попытки добавления дубликата:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }

        // Изменение X точки
        System.out.println("\n=== ТЕСТ 4: ИЗМЕНЕНИЕ X ТОЧЕК ===");

        System.out.println("Изменяем X точки с индексом 1 с " + func.getPointX(1) + " на 1.8");
        func.setPointX(1, 1.8);
        System.out.println("Точки после изменения X точки 1:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }

        // Попытка изменить X на некорректное значение (должно быть отклонено)
        System.out.println("\nПытаемся изменить X точки 0 на -1.0 (должно быть отклонено)");
        try{
            func.setPointX(0, -1.0);
            System.out.println("исключение не выброшено");
        } catch (InappropriateFunctionPointException e) {
            System.out.println("ошибка: " + e.getMessage());
        }
        System.out.println("Точки после НЕУДАЧНОЙ попытки изменения X:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }

        // Замена точки целиком
        System.out.println("\n=== ТЕСТ 5: ЗАМЕНА ТОЧЕК ===");

        System.out.println("Заменяем точку с индексом 2 на (2.2, 15.0)");
        func.setPoint(2, new FunctionPoint(2.2, 15.0));
        System.out.println("Точки после замены точки 2:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }

        // Попытка заменить точку на некорректную (должно быть отклонено)
        System.out.println("\nПытаемся заменить точку 1 на (3.5, 5.0) - X выходит за границы (должно быть отклонено)");
        try{
            func.setPoint(1, new FunctionPoint(3.5, 5.0));
            System.out.println("исключение не выброшено");
        } catch (InappropriateFunctionPointException e) {
            System.out.println("ошибка: " + e.getMessage());
        }
        System.out.println("Точки после НЕУДАЧНОЙ попытки замены:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }

        System.out.println("\n=== ТЕСТ 6: ГРАНИЦЫ И ФИНАЛЬНОЕ СОСТОЯНИЕ ===");
        System.out.println("Левая граница: " + func.getLeftDomainBorder());
        System.out.println("Правая граница: " + func.getRightDomainBorder());
        System.out.println("Финальное количество точек: " + func.getPointsCount());
        System.out.println("Финальные точки функции:");
        for (int i = 0; i < func.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + func.getPointX(i) + ", " + func.getPointY(i) + ")");
        }

        System.out.println("\n=== ТЕСТ 7: LinkedListTabulatedFunction ===");

        // Тестируем связный список
        TabulatedFunction linkedFunc = new LinkedListTabulatedFunction(0, 4, values);
        System.out.println("LinkedListTabulatedFunction создана успешно");
        System.out.println("Левая граница: " + linkedFunc.getLeftDomainBorder());
        System.out.println("Правая граница: " + linkedFunc.getRightDomainBorder());
        System.out.println("Количество точек: " + linkedFunc.getPointsCount());

        // Тестируем добавление точки
        try {
            linkedFunc.addPoint(new FunctionPoint(2.5, 6.25));
            System.out.println("Точка (2.5, 6.25) добавлена в LinkedList");
        } catch (InappropriateFunctionPointException e) {
            System.out.println("Ошибка при добавлении: " + e.getMessage());
        }

        // Тестируем удаление точки
        try {
            linkedFunc.deletePoint(1);
            System.out.println("Точка с индексом 1 удалена из LinkedList");
        } catch (Exception e) {
            System.out.println("Ошибка при удалении: " + e.getMessage());
        }

        // Выводим финальное состояние LinkedList
        System.out.println("Финальные точки LinkedList функции:");
        for (int i = 0; i < linkedFunc.getPointsCount(); i++) {
            System.out.println("  Точка " + i + ": (" + linkedFunc.getPointX(i) + ", " + linkedFunc.getPointY(i) + ")");
        }

        System.out.println("\n=== ТЕСТ 8: ПОЛИМОРФИЗМ ===");

        // Демонстрация полиморфизма - работа с разными реализациями через один интерфейс
        TabulatedFunction[] functions = {
                new ArrayTabulatedFunction(0, 2, new double[]{1, 2, 3}),
                new LinkedListTabulatedFunction(0, 2, new double[]{1, 2, 3})
        };

        for (int i = 0; i < functions.length; i++) {
            System.out.println("Функция " + (i + 1) + " (" + functions[i].getClass().getSimpleName() + "):");
            System.out.println("  f(1.0) = " + functions[i].getFunctionValue(1.0));
            System.out.println("  Количество точек: " + functions[i].getPointsCount());
        }

        System.out.println("\n=== ТЕСТ 9: ИСКЛЮЧЕНИЯ ===");

        // Тестируем исключения для ArrayTabulatedFunction
        try {
            TabulatedFunction badFunc = new ArrayTabulatedFunction(5, 0, 3); // неправильные границы
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано ожидаемое исключение: " + e.getMessage());
        }

        // Тестируем исключения для LinkedListTabulatedFunction
        try {
            TabulatedFunction badFunc = new LinkedListTabulatedFunction(0, 1, 1); // мало точек
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано ожидаемое исключение: " + e.getMessage());
        }

        // НОВЫЕ ТЕСТЫ ДЛЯ ЗАДАНИЙ 3-8
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== ТЕСТЫ ДЛЯ ЛАБОРАТОРНОЙ №4 ===");
        System.out.println("=".repeat(50));

        try {
            // Тест аналитических функций (задание 3)
            System.out.println("\n=== ТЕСТ 10: АНАЛИТИЧЕСКИЕ ФУНКЦИИ ===");

            Sin sin = new Sin();
            Cos cos = new Cos();
            Exp exp = new Exp();
            Log ln = new Log(Math.E);

            System.out.println("sin(π/2) = " + sin.getFunctionValue(Math.PI/2));
            System.out.println("cos(0) = " + cos.getFunctionValue(0));
            System.out.println("exp(1) = " + exp.getFunctionValue(1));
            System.out.println("ln(e) = " + ln.getFunctionValue(Math.E));

            System.out.println("область определения sin: [" + sin.getLeftDomainBorder() + ", " + sin.getRightDomainBorder() + "]");
            System.out.println("область определения ln: [" + ln.getLeftDomainBorder() + ", " + ln.getRightDomainBorder() + "]");

            // Тест мета-функций (задание 4)
            System.out.println("\n=== ТЕСТ 11: МЕТА-ФУНКЦИИ ===");

            Function sumFunc = new Sum(sin, cos);
            Function multFunc = new Mult(sin, cos);
            Function powerFunc = new Power(sin, 2);
            Function shiftFunc = new Shift(sin, 1, 0.5);
            Function scaleFunc = new Scale(sin, 2, 3);
            Function compFunc = new Composition(sin, cos);

            System.out.println("sin(1) + cos(1) = " + sumFunc.getFunctionValue(1));
            System.out.println("sin(1) * cos(1) = " + multFunc.getFunctionValue(1));
            System.out.println("sin²(1) = " + powerFunc.getFunctionValue(1));
            System.out.println("sin(1-1) + 0.5 = " + shiftFunc.getFunctionValue(1));
            System.out.println("3 * sin(1/2) = " + scaleFunc.getFunctionValue(1));
            System.out.println("sin(cos(1)) = " + compFunc.getFunctionValue(1));

            // Тест класса Functions (задание 5)
            System.out.println("\n=== ТЕСТ 12: КЛАСС Functions ===");

            Function f1 = Functions.sum(sin, cos);
            Function f2 = Functions.power(sin, 2);
            Function f3 = Functions.shift(sin, 0.5, 1);
            Function f4 = Functions.composition(sin, cos);

            System.out.println("Functions.sum(sin, cos)(1) = " + f1.getFunctionValue(1));
            System.out.println("Functions.power(sin, 2)(1) = " + f2.getFunctionValue(1));
            System.out.println("Functions.shift(sin, 0.5, 1)(1) = " + f3.getFunctionValue(1));
            System.out.println("Functions.composition(sin, cos)(1) = " + f4.getFunctionValue(1));

            // Тест табулирования (задание 6)
            System.out.println("\n=== ТЕСТ 13: ТАБУЛИРОВАНИЕ ===");

            TabulatedFunction tabulatedSin = TabulatedFunctions.tabulate(sin, 0, Math.PI, 5);
            System.out.println("табулированный sin на [0, π] с 5 точками:");
            for (int i = 0; i < tabulatedSin.getPointsCount(); i++) {
                System.out.printf("  точка %d: (%.2f, %.4f)%n", i, tabulatedSin.getPointX(i), tabulatedSin.getPointY(i));
            }

            // Тест ввода/вывода (задание 7)
            System.out.println("\n=== ТЕСТ 14: ВВОД/ВЫВОД ===");

            // Бинарный формат
            try (FileOutputStream fos = new FileOutputStream("test_binary.dat")) {
                TabulatedFunctions.outputTabulatedFunction(tabulatedSin, fos);
                System.out.println("функция записана в бинарный файл");
            }

            TabulatedFunction readFromBinary;
            try (FileInputStream fis = new FileInputStream("test_binary.dat")) {
                readFromBinary = TabulatedFunctions.inputTabulatedFunction(fis);
                System.out.println("функция прочитана из бинарного файла");
            }

            // Текстовый формат
            try (FileWriter fw = new FileWriter("test_text.txt")) {
                TabulatedFunctions.writeTabulatedFunction(tabulatedSin, fw);
                System.out.println("функция записана в текстовый файл");
            }

            TabulatedFunction readFromText;
            try (FileReader fr = new FileReader("test_text.txt")) {
                readFromText = TabulatedFunctions.readTabulatedFunction(fr);
                System.out.println("функция прочитана из текстового файла");
            }

            // Сравнение
            System.out.println("сравнение исходной и считанных функций:");
            for (int i = 0; i < tabulatedSin.getPointsCount(); i++) {
                double original = tabulatedSin.getPointY(i);
                double fromBinary = readFromBinary.getPointY(i);
                double fromText = readFromText.getPointY(i);
                System.out.printf("  точка %d: исходная=%.4f, бинарная=%.4f, текстовая=%.4f%n",
                        i, original, fromBinary, fromText);
            }

            // Тест сложной композиции (задание 8)
            System.out.println("\n=== ТЕСТ 15: СЛОЖНАЯ КОМПОЗИЦИЯ ===");

            // ln(exp(x)) должна быть близка к x
            Function lnOfExp = Functions.composition(exp, ln);
            TabulatedFunction tabulatedLnExp = TabulatedFunctions.tabulate(lnOfExp, 0.1, 5, 10);

            System.out.println("ln(exp(x)) на отрезке [0.1, 5]:");
            for (int i = 0; i < tabulatedLnExp.getPointsCount(); i++) {
                double x = tabulatedLnExp.getPointX(i);
                double y = tabulatedLnExp.getPointY(i);
                double error = Math.abs(x - y);
                System.out.printf("  x=%.1f: ln(exp(x))=%.4f, ошибка=%.6f%n", x, y, error);
            }


            System.out.println("\n=== ТЕСТ 16: СЕРИАЛИЗАЦИЯ ===");

            //создаем композицию ln(exp(x)) - должна быть близка к x
            Exp expFunc = new Exp();
            Log lnFunc = new Log(Math.E);
            Function composition = Functions.composition(expFunc, lnFunc);

            //табулируем композицию от 0 до 10 с шагом 1
            TabulatedFunction tabulatedComposition = TabulatedFunctions.tabulate(composition, 0, 10, 11);

            System.out.println("Исходная функция ln(exp(x)) на [0, 10]:");
            for (int i = 0; i < tabulatedComposition.getPointsCount(); i++) {
                double x = tabulatedComposition.getPointX(i);
                double y = tabulatedComposition.getPointY(i);
                System.out.printf("  x=%.1f: ln(exp(x))=%.4f (должно быть %.1f)%n", x, y, x);
            }


            System.out.println("\n=== Serializable ===");
            //сериализуем с использованием Serializable (LinkedListTabulatedFunction)
            TabulatedFunction serializableFunc = new LinkedListTabulatedFunction(0, 10, 11);
            //заполняем значениями
            for (int i = 0; i < serializableFunc.getPointsCount(); i++) {
                double x = serializableFunc.getPointX(i);
                double y = composition.getFunctionValue(x);
                serializableFunc.setPointY(i, y);
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("serializable.dat"))) {
                oos.writeObject(serializableFunc);
                System.out.println("функция сериализована в serializable.dat (Serializable)");
            }

            //десериализуем
            TabulatedFunction deserializedSerializable;
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serializable.dat"))) {
                deserializedSerializable = (TabulatedFunction) ois.readObject();
                System.out.println("функция десериализована из serializable.dat");
            }


            System.out.println("\n=== Externalizable ===");
            //сериализуем с использованием Externalizable (ArrayTabulatedFunction)
            TabulatedFunction externalizableFunc = new ArrayTabulatedFunction(0, 10, 11);
            //заполняем значениями
            for (int i = 0; i < externalizableFunc.getPointsCount(); i++) {
                double x = externalizableFunc.getPointX(i);
                double y = composition.getFunctionValue(x);
                externalizableFunc.setPointY(i, y);
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("externalizable.dat"))) {
                oos.writeObject(externalizableFunc);
                System.out.println("функция сериализована в externalizable.dat (Externalizable)");
            }

            //десериализуем
            TabulatedFunction deserializedExternalizable;
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("externalizable.dat"))) {
                deserializedExternalizable = (TabulatedFunction) ois.readObject();
                System.out.println("функция десериализована из externalizable.dat");
            }

            //сравниваем все функции
            System.out.println("\nСравнение всех функций:");
            boolean allMatch = true;
            for (int i = 0; i < tabulatedComposition.getPointsCount(); i++) {
                double original = tabulatedComposition.getPointY(i);
                double ser = deserializedSerializable.getPointY(i);
                double ext = deserializedExternalizable.getPointY(i);
                double errorSer = Math.abs(original - ser);
                double errorExt = Math.abs(original - ext);

                System.out.printf("  точка %d: исходная=%.4f, ser=%.4f(err=%.6f), ext=%.4f(err=%.6f)%n",
                        i, original, ser, errorSer, ext, errorExt);

                if (errorSer > 1e-10 || errorExt > 1e-10) {
                    allMatch = false;
                }
            }

            if (allMatch) {
                System.out.println("Обе сериализации работают правильно - все значения совпадают!");
            } else {
                System.out.println("Ошибка сериализации - значения не совпадают!");
            }

            //анализ файлов
            File serializableFile = new File("serializable.dat");
            File externalizableFile = new File("externalizable.dat");
            File binaryFile = new File("test_binary.dat");
            File textFile = new File("test_text.txt");

            System.out.println("\nСравнение размеров файлов:");
            System.out.printf("  Serializable (serializable.dat): %d байт%n", serializableFile.length());
            System.out.printf("  Externalizable (externalizable.dat): %d байт%n", externalizableFile.length());
            if (binaryFile.exists()) {
                System.out.printf("  Бинарный формат (test_binary.dat): %d байт%n", binaryFile.length());
            }
            if (textFile.exists()) {
                System.out.printf("  Текстовый формат (test_text.txt): %d байт%n", textFile.length());
            }

            System.out.println("\n=== ВСЕ ТЕСТЫ ЗАВЕРШЕНЫ! ===");

        } catch (Exception e) {
            System.out.println("ошибка в новых тестах: " + e.getMessage());
            e.printStackTrace();
        }
    }
}