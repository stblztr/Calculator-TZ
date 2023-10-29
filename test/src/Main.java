import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int firstValue = 0; // переменная для первого входного значения
        int secondValue = 0; // переменная для второго входного значения
        int result; // переменная для результата в арабских цифрах

        boolean rimNumber1 = false; // переменные для определения римских цифр в примере
        boolean rimNumber2 = false;
        String rimNumbers [] = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"}; // массив с римскими цифрами от 1 до 10
        int rimResult; // переменная для записи результата римскими цифрами
        String ten = "X"; // римская цифра 10 (нужна для вывода)

        Scanner in = new Scanner(System.in);
        String enter = in.nextLine(); // ввод данных со строки консоли
        String[] parts = enter.split(" "); // преобразование в массив с разделителем "пробел"


        if(parts.length>3){ // проверка на длину заданного примера
            throw new IOException();
        }

        for (int i = 0; i < 3; i = i+2){ // вложенный цикл для проверки есть ли римские цифры в примере
            for (int g = 0; g<10; g++){ // с помощью перебора из созданного массива римских цифр

                if ((parts[i].compareTo(rimNumbers[g]) == 0) & i == 0){
                    rimNumber1 = true; // если значение совпали, то в переменной значение false меняется на true
                    firstValue = g+1; // в переменную записывается цифра, переведенного из римского в арабский
                    break;
                }

                if ((parts[i].compareTo(rimNumbers[g]) == 0) & i == 2){
                    rimNumber2 = true;
                    secondValue = g + 1;
                    break;
                }
            }
        }

        if ( (rimNumber1 == true & rimNumber2 == false) | (rimNumber1 == false & rimNumber2 == true)){ // проверка на наличие всего лишь одной римской цифры в примере
            throw new IOException();
        }

        if ( (rimNumber1 == false) & (rimNumber2 == false) ) { // для арабских цифр
            firstValue = Integer.parseInt(parts[0]); // меняется тип данных из string в int
            secondValue = Integer.parseInt(parts[2]);
        }


        if (firstValue < 1 | firstValue > 10 | secondValue < 1 | secondValue > 10){ // проверка на то,
            throw new IOException(); // что обе цифры находятся в диапозоне от 1 до 10
        }

        String operation = parts[1]; // в переменную вносится значение математического оператора
        String operations [] = {"+", "-", "*", "/"};

        for (int i = 0; i < operations.length; i++){ // проверка на правильность написания математического оператора
            if (operation.compareTo(operations[i]) == 0){
                break;
            }
            if (i == 3){
                throw new IOException();
            }
        }


        switch (operation){ // в зависимости от мат. оператора применяется свой case
            case "+":
                result = firstValue + secondValue;
                if (result>10 & rimNumber1 == true){ // если результат больше 10 и первая цифра римская (проверка второй бессмыслена, так как до этого уже была проверка и достаточно проверить лишь одну)
                    rimResult = result - 11; // в переменную вносится значение результата из которого вычитается 10 (в строке вывода прописана) и 1 (индекс)
                    System.out.println(ten+rimNumbers[rimResult]); //выводит ten и значение в массиве, соответствующее числу
                    break;
                } else{
                    System.out.println(result); // если все цифры арабские
                    break;
                    }

            case "-":
                result = firstValue - secondValue;
                if (result<0 & rimNumber1 == true){ // проверка на отрицательный результат в случае с римскими цифрами
                    throw new IOException();
                }else
                    if (rimNumber1 == true){ // вывод для римских цифр
                        result--;
                        System.out.println(rimNumbers[result]);
                        break;
                    }
                    else{ // вывод для арабских
                        System.out.println(result);
                        break;
                    }

            case "*":
                result = firstValue * secondValue;
                if (rimNumber1 == false & rimNumber2 == false){ // вывод для арабских цифр
                    System.out.println(result);
                    break;
                }
                else if (result<=10 & rimNumber1 == true){ // если результат меньше или равен 10 для римских цифр
                    result--;
                    System.out.println(rimNumbers[result]);
                    break;
                }
                else if (result > 10 & rimNumber1 == true) { // если результат больше 10 для римских цифр
                    int x = result / 10; // вводится переменная х, которая обозначает из скольки чисел 10 состоит число
                    if (x < 4){ // если число меньше 40
                        rimResult = result - 10*x - 1;
                        System.out.println((ten.repeat(x))+rimNumbers[rimResult]);
                        break;
                    }
                    if (x == 5){ // если число в промежутке между 50 и 59
                        rimResult = result - 10*x - 1;
                        System.out.println("L"+rimNumbers[rimResult]);
                        break;
                    }
                    if (x > 5 & x < 9){ // если число в промежутке между 60 и 89
                        x = x - 5;
                        rimResult = result - 50 - 10*x - 1;
                        System.out.println("L"+ten.repeat(x)+rimNumbers[rimResult]);
                        break;
                    }
                    if (x==9){ // если число равно 90 (промежуток между 91 и 99 рассматривать бессмысленно)
                        System.out.println("XC");
                        break;
                    }
                    if (x == 10){ // если число равно 100 (большие значения рассматривать бессмысленно)
                        System.out.println("C");
                        break;
                    }
                }

            case "/":
                result = firstValue / secondValue;
                if (rimNumber1 == true){ // вывод для римских чисел
                    result--;
                    System.out.println(rimNumbers[result]);
                    break;
                }
                else{ // вывод для арабских чисел
                    System.out.println(result);
                    break;
                }
        }
        in.close();
    }
}