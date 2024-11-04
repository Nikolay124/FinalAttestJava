import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Множество ноутбуков
        Set<Laptop> laptops = new HashSet<>();

        // Добавление нескольких экземпляров ноутбуков
        laptops.add(new Laptop("Dell XPS", 16, 512, "Windows 10", "Silver"));
        laptops.add(new Laptop("MacBook Pro", 8, 256, "macOS", "Space Gray"));
        laptops.add(new Laptop("HP Spectre x360", 12, 1024, "Ubuntu", "Black"));
        laptops.add(new Laptop("Lenovo ThinkPad", 32, 2048, "Linux Mint", "Gray"));
        laptops.add(new Laptop("Acer Aspire", 24, 4096, "Chrome OS", "White"));

        // Меню для выбора критериев фильтрации
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("\t1 - ОЗУ");
        System.out.println("\t2 - Объем ЖД");
        System.out.println("\t3 - Операционная система");
        System.out.println("\t4 - Цвет");

        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> filters = new TreeMap<>(); // Хранение фильтров

        boolean continueFiltering = true;
        while (continueFiltering) {
            System.out.print("Выберите критерий: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Минимальный объем ОЗУ (ГБ): ");
                    int minRam = scanner.nextInt();
                    filters.put("RAM", minRam);
                    break;
                case 2:
                    System.out.print("Минимальный размер ЖД (ГБ): ");
                    int minHDD = scanner.nextInt();
                    filters.put("HDD_SIZE", minHDD);
                    break;
                case 3:
                    System.out.print("Операционная система: ");
                    scanner.nextLine(); // Чистка буфера после nextInt()
                    String os = scanner.nextLine().trim();
                    filters.put("OS", os.length());
                    break;
                case 4:
                    System.out.print("Цвет: ");
                    scanner.nextLine();
                    String color = scanner.nextLine().toLowerCase().trim(); // Приводим к нижнему регистру
                    filters.put("COLOR", color.length()); // Длина строки вместо самого значения для сравнения
                    break;
                default:
                    System.err.println("Неверный выбор!");
                    break;
            }

            System.out.println(filters); // Для дебага

            // Запрашиваем у пользователя, хочет ли он продолжить добавление критериев
            System.out.println("Хотите добавить ещё один критерий? (Y/N)");
            char answer = Character.toUpperCase(scanner.next().charAt(0));
            if (answer != 'Y') {
                continueFiltering = false;
            } else {
                scanner.nextLine();
            }
        }

        List<Laptop> filteredLaptops = filterLaptops(laptops, filters);
        System.out.println(filteredLaptops.size() + " ноутбуков подходят под ваши фильтры:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }

    /**
     * Метод для фильтрации ноутбуков по заданным критериям
     */
    private static List<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, Integer> criteria) {
        List<Laptop> result = new ArrayList<>();
        for (Laptop laptop : laptops) {
            boolean matchesCriteria = true;
            for (Map.Entry<String, Integer> entry : criteria.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();

                switch (key) {
                    case "RAM":
                        if (laptop.getRam() < value) {
                            matchesCriteria = false;
                        }
                        break;
                    case "HDD_SIZE":
                        if (laptop.getHddSize() < value) {
                            matchesCriteria = false;
                        }
                        break;
                    case "OS":
                        String osFilter = laptop.getOs().toLowerCase();
                        if (!osFilter.contains(entry.getKey().toLowerCase())) {
                            matchesCriteria = false;
                        }
                        break;
                    case "COLOR":
                        String colorFilter = laptop.getColor().toLowerCase();
                        if (!colorFilter.contains(entry.getKey().toLowerCase())) {
                            matchesCriteria = false;
                        }
                        break;
                }
            }
            if(matchesCriteria){
                result.add(laptop);
            }
        }
        return result;
    }
}