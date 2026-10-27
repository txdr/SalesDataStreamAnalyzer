import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the Java Streams Sales Analyzer");

        String fileName = "sales.csv";
        List<Sale> sales = loadSalesFromFile(fileName);

        if (sales.isEmpty()) {
            System.out.println("No sales found.");
            return;
        }

        displayAllSales(sales);
        countSalesOver1000(sales);
        calculateTotalSales(sales);
        calculateAverageSale(sales);
        displayHighestSale(sales);
        displayLowestSale(sales);
        displaySalesByRegion(sales, "east");
        displaySalesOver500(sales);
        displayEmployeeNames(sales);
    }

    public static void displayAllSales(List<Sale> sales) {
        System.out.println("All sales: ");
        sales.stream().forEach(System.out::println);
        System.out.println();
    }

    public static void displaySalesOver500(List<Sale> sales) {
        System.out.println("Sales over 500: ");
        sales.stream().filter(s -> s.getAmount() > 500).forEach(System.out::println);
        System.out.println();
    }

    public static void countSalesOver1000(List<Sale> sales) {
        long count = sales.stream().filter(s -> s.getAmount() > 1000).count();

        System.out.println("Number of sales over $1,000: " + count);
        System.out.println();
    }

    public static void calculateTotalSales(List<Sale> sales) {
        double total = sales.stream().mapToDouble(Sale::getAmount).sum();
        System.out.println("Total sales: $" + String.format("%.2f", total));
        System.out.println();
    }

    public static void calculateAverageSale(List<Sale> sales) {
        double total = sales.stream().mapToDouble(Sale::getAmount).average().orElse(0);
        System.out.println("Average sale: $" + String.format("%.2f", total));
        System.out.println();
    }

    public static void displayHighestSale(List<Sale> sales) {
        Optional<Sale> highestSale = sales.stream()
                .max(Comparator.comparing(Sale::getAmount));
        System.out.println("Highest sale: $" + highestSale.get());
        System.out.println();
    }

    public static void displayLowestSale(List<Sale> sales) {
        Optional<Sale> lowestSale = sales.stream()
                .min(Comparator.comparing(Sale::getAmount));
        System.out.println("Lowest sale: $" + lowestSale.get());
        System.out.println();
    }

    public static void displaySalesByRegion(List<Sale> sales, String selectedRegion) {
            System.out.println("Sales from " + selectedRegion + " region: ");
            sales.stream().filter(s -> s.getRegion().equalsIgnoreCase(selectedRegion)).forEach(System.out::println);
            System.out.println();
    }

    public static void displayEmployeeNames(List<Sale> sales) {
        System.out.println("Employee names: ");
        sales.stream().map(Sale::getEmployeeName).forEach(System.out::println);
        System.out.println();
    }

    public static List<Sale> loadSalesFromFile(String fileName) {
        List<Sale> sales = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner fileReader = new Scanner(file);

            if (fileReader.hasNextLine()) {
                fileReader.nextLine();
            }

            while(fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 4) {
                    int saleId = Integer.parseInt(parts[0]);
                    String employeeName = parts[1];
                    String region = parts[2];
                    double amount = Double.parseDouble(parts[3]);

                    Sale sale = new Sale(saleId, employeeName, region, amount);
                    sales.add(sale);
                }
            }
            fileReader.close();
        } catch (FileNotFoundException error) {
            System.out.println("File error: " +  error.getMessage());
        } catch (NumberFormatException error) {
            System.out.println("Invalid input: " +  error.getMessage());
        }
        return sales;
    }

}
