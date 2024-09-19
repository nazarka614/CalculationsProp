package overallLoss;

import java.util.Scanner;

public class OverallLossCalculatorInitialBalance {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод данных пользователем
        System.out.print("Введите начальный баланс (Initial Balance) (или нажмите Enter, чтобы пропустить): ");
        String initialBalanceInput = scanner.nextLine();
        Double initialBalance = parseDoubleOrNull(initialBalanceInput);

        System.out.print("Введите текущий капитал (Current Equity) (или нажмите Enter, чтобы пропустить): ");
        String currentEquityInput = scanner.nextLine();
        Double currentEquity = parseDoubleOrNull(currentEquityInput);

        System.out.print("Введите минимальный капитал за весь период (Lowest Equity) (или нажмите Enter, чтобы пропустить): ");
        String lowestEquityInput = scanner.nextLine();
        Double lowestEquity = parseDoubleOrNull(lowestEquityInput);

        System.out.print("Введите общий процент убытков (Overall Drawdown %) (или нажмите Enter, чтобы пропустить): ");
        String overallDrawdownPercentInput = scanner.nextLine();
        Double overallDrawdownPercent = parseDoubleOrNull(overallDrawdownPercentInput);

        // Проверка и расчет
        if (initialBalance != null && lowestEquity != null && overallDrawdownPercent != null) {
            // Расчет Max Overall Loss Recorded
            Double maxOverallLossRecorded = calculateMaxOverallLossRecorded(initialBalance, lowestEquity);
            System.out.printf("Max Overall Loss Recorded: %.2f\n", maxOverallLossRecorded);

            // Расчет Max Overall Loss Percent
            Double maxOverallLoss = initialBalance * (overallDrawdownPercent / 100.0);
            Double maxOverallLossPercent = calculateMaxOverallLossPercent(maxOverallLossRecorded, maxOverallLoss);
            System.out.printf("Max Overall Loss Percent: %.2f%%\n", maxOverallLossPercent);

            // Расчет Overall Loss Level
            Double overallLossLevel = calculateOverallLossLevel(initialBalance, overallDrawdownPercent);
            System.out.printf("Overall Loss Level: %.2f\n", overallLossLevel);

            // Расчет процента потерь начального баланса
            if (currentEquity != null) {
                Double initialBalanceLoss = initialBalance - currentEquity;
                Double initialBalanceLossPercent = calculateInitialAccountBalanceLossPercent(initialBalanceLoss, initialBalance, overallDrawdownPercent);
                System.out.printf("Initial Account Balance Loss Percent: %.2f%%\n", initialBalanceLossPercent);
            }
        } else {
            System.out.println("Недостаточно данных для выполнения расчетов.");
        }
    }

    private static Double parseDoubleOrNull(String input) {
        try {
            return input.isEmpty() ? null : Double.parseDouble(input.replace(',', '.'));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // Метод для расчета Max Overall Loss Recorded
    private static Double calculateMaxOverallLossRecorded(Double initialBalance, Double lowestEquity) {
        return initialBalance - lowestEquity;
    }

    // Метод для расчета Max Overall Loss Percent
    private static Double calculateMaxOverallLossPercent(Double maxOverallLossRecorded, Double maxOverallLoss) {
        if (maxOverallLoss == null || maxOverallLoss == 0) return null;
        return (maxOverallLossRecorded / maxOverallLoss) * 100.0;
    }

    // Метод для расчета Overall Loss Level
    private static Double calculateOverallLossLevel(Double initialBalance, Double overallDrawdownPercent) {
        return initialBalance - (initialBalance * overallDrawdownPercent / 100.0);
    }

    // Метод для расчета Initial Account Balance Loss Percent
    private static Double calculateInitialAccountBalanceLossPercent(Double initialBalanceLoss, Double initialBalance, Double overallDrawdownPercent) {
        Double maxOverallLoss = initialBalance * (overallDrawdownPercent / 100.0);
        if (maxOverallLoss == null || maxOverallLoss == 0) return null;
        return (initialBalanceLoss / maxOverallLoss) * 100.0;
    }
}
