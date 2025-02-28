import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MinecraftResourceTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Double> resourceValues = new HashMap<>();
        resourceValues.put("Wood", 1.5);
        resourceValues.put("Stone", 2.0);
        resourceValues.put("Iron", 5.0);
        resourceValues.put("Gold", 10.0);
        resourceValues.put("Diamond", 20.0);

        Map<String, Integer> resourceQuantities = new HashMap<>();
        resourceQuantities.put("Wood", 0);
        resourceQuantities.put("Stone", 0);
        resourceQuantities.put("Iron", 0);
        resourceQuantities.put("Gold", 0);
        resourceQuantities.put("Diamond", 0);

        while (true) {
            System.out.print("Enter resource type (Wood, Stone, Iron, Gold, Diamond): ");
            String resourceType = scanner.next();

            if (resourceType.equals("Exit")) {
                break;
            } else if (resourceType.equals("None")) {
                System.out.println("Skipping this entry...");
                continue;
            } else if (!resourceValues.containsKey(resourceType)) {
                System.out.println("Invalid resource type. Please enter a valid resource.");
                continue;
            }

            System.out.print("Enter quantity collected: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // consume invalid input
                continue;
            }

            int quantity = scanner.nextInt();
            if (quantity < 0) {
                System.out.println("Invalid input. Quantity cannot be negative.");
                continue;
            }

            resourceQuantities.put(resourceType, resourceQuantities.get(resourceType) + quantity);

            System.out.println("Current Totals:");
            for (String key : resourceQuantities.keySet()) {
                System.out.println(key + ": " + resourceQuantities.get(key));
            }
        }

        System.out.println("\nFinal Totals:");
        double grandTotalValue = 0;
        for (String key : resourceQuantities.keySet()) {
            int quantity = resourceQuantities.get(key);
            double totalValue = quantity * resourceValues.get(key);
            grandTotalValue += totalValue;
            System.out.printf("%s - Quantity: %d | Total Value: %.2f%n", key, quantity, totalValue);
        }

        System.out.printf("\nGrand Total Value of All Resources Collected: %.2f%n", grandTotalValue);

        scanner.close();
    }
}


