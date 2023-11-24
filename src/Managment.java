import java.math.BigDecimal;
import java.util.Objects;
import java.util.Scanner;

public class Managment {
    private String email = "gulumkan@gmail.com";
    private String password = "2112.manas";
    public static BigDecimal companyBalance = BigDecimal.valueOf(0);
    private User[] users = new User[0];
    private Car[] cars = Car.values();
    private Driver[] drivers = Driver.values();

    public User[] getUsers() {
        return users;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static BigDecimal getCompanyBalance() {
        return companyBalance;
    }

    public static void setCompanyBalance(BigDecimal balance) {
        companyBalance = companyBalance.add(balance);
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Car[] getCars() {
        return cars;
    }

    public Driver[] getDrivers() {
        return drivers;
    }

    public void infoCar() {
        int id = 1;
        for (int i = 0; i < cars.length; i++) {
            if (cars[i].isBook()) {
                System.out.println((i + 1) + "." + cars[i].name() + "  -  стоимость за 1 день: $ " + cars[i].getPrice() + "    ДОСТУПНО ✅");
            } else {
                System.out.println((i + 1) + "." + cars[i].name() + "  -  стоимость за 1 день: $ " + cars[i].getPrice() + "    НЕ ДОСТУПНО ❌");
            }
        }
        System.out.println();
    }

    public boolean admin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите email:");
        String email = scanner.nextLine();
        System.out.print("Введите пароль:");
        String password = scanner.nextLine();
        if (Objects.equals(this.email, email) && Objects.equals(this.password, password)) {
            return true;
        }
        return false;
    }

    public void getUsersInfo() {
        for (User user : users) {
            System.out.println(
                    STR. """
                   Имя : \{ user.getFirstName() }
                   Фамилия : \{ user.getLastName() }
                   Email:    \{ user.getEmail() }
                   Пароль:   \{ user.getPassword() }
                   Адрес:   \{ user.getAdress() }
                   тел.номер: \{ user.getPhoneNumber() }
                   счет : \{ user.getAvailableBalance() }
                  Машины:   """ );
            user.getcarsUser();
        }
    }

    public void getDriversInfo() {
        for (Driver driver : drivers) {
            System.out.println("Водитель : " + driver.name() + "   тел.номер: " + driver.getPhoneNumber());
        }
    }
}



