import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Managment managment = new Managment();
        Car[] cars = managment.getCars();
        boolean istrue = true;
        boolean chekBalanceUser = true;
        System.out.println("ДОБРО ПОЖАЛОВАТЬ 🙌   ");
        while (true) {
            System.out.println("""
                    1.РЕГИСТРАЦИЯ
                    2.УЖЕ ЕСТЬ АККАУНТ.ВОЙТИ
                    3.Админ
                    3.ВЫЙТ
                    """);
            System.out.print("ВВЕДИТЕ КОМАНДУ:");
            switch (scanner.nextLine().toUpperCase()) {
                case "1", "РЕГИСТРАЦИЯ" -> managment.setUsers(User.regstration(new User(), managment.getUsers()));
                case "2", "ВОЙТИ" -> {
                    User currentUser = User.login(managment.getUsers());
                    if (currentUser != null) {
                        Innerloop:
                        while (true) {
                            managment.infoCar();
                            System.out.print("Ваш выбор (\"0\" чтобы закончить программу):");
                            int id = scanner.nextInt();
                            if (id != 0 && id < 8) {
                                if (cars[id - 1].isBook()) {
                                    chekBalanceUser = currentUser.bookCar(cars[id - 1]);
                                    if (!chekBalanceUser) {
                                        System.out.print("К сожалению, на вашем счету нет достаточно денег." +
                                                "Оплата не прошла.Не достаточно средств на счету хотите взять кредит (да/нет)?");
                                        istrue = currentUser.deposit(new Scanner(System.in).nextLine());
                                        if (!istrue) {
                                            break Innerloop;
                                        }
                                    }
                                } else System.out.println("Машина забронировано❗️\n");
                            } else break Innerloop;
                        }

                    } else System.out.println("Неверный email или пароль❗Повторите попытку.");
                }
                case "3" -> {
                    if (managment.admin()) {
                    inner:
                    while (true) {
                            System.out.println(STR."""
                                    1.КЛИЕНТЫ
                                    2.МАШИНЫ
                                    3.ВОДИТЕЛИ
                                    4.СЧЕТ КОМПАНИИ
                                    5.ВЫЙТИ
                                    """);
                            System.out.print("ВВЕДИТЕ КОМАНДУ:");
                            switch (scanner.nextLine()) {
                                case "1" -> managment.getUsersInfo();
                                case "2" -> managment.infoCar();
                                case "3" -> managment.getDriversInfo();
                                case "4" -> System.out.println(managment.getCompanyBalance());
                                case "5" -> {
                                    break inner;
                                }
                                default -> System.out.println("Неправильный выбор❗️");
                            }
                        }
                    }
                }
                case "4" -> System.exit(0);
            }

        }
    }
}