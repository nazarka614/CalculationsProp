package dailyLoss;

import java.util.Scanner;

public class DailyLossCalculatorDayEntryEquity {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод данных пользователем
        System.out.print("Введите Day Entry Equity (или нажмите Enter, чтобы пропустить): ");
        String dayEntryEquityInput = scanner.nextLine();
        Double dayEntryEquity = parseDoubleOrNull(dayEntryEquityInput);

        System.out.print("Введите Daily Drawdown % (или нажмите Enter, чтобы пропустить): ");
        String dailyDrawdownPercentInput = scanner.nextLine();
        Double dailyDrawdownPercent = parseDoubleOrNull(dailyDrawdownPercentInput);

        System.out.print("Введите текущий капитал (Current Equity) (или нажмите Enter, чтобы пропустить): ");
        String currentEquityInput = scanner.nextLine();
        Double currentEquity = parseDoubleOrNull(currentEquityInput);

        System.out.print("Введите минимальный капитал за сегодня (Lowest Today's Equity) (или нажмите Enter, чтобы пропустить): ");
        String lowestTodayEquityInput = scanner.nextLine();
        Double lowestTodayEquity = parseDoubleOrNull(lowestTodayEquityInput);

        // Проверка и расчет
        if (dayEntryEquity != null && lowestTodayEquity != null && dailyDrawdownPercent != null) {
            // Расчет Max Daily Loss Recorded
            Double maxDailyLossRecorded = calculateMaxDailyLoss(dayEntryEquity, lowestTodayEquity);
            System.out.printf("Max Daily Loss Recorded: %.2f\n", maxDailyLossRecorded);

            // Расчет Max Daily Loss Percent
            Double maxDailyLoss = dayEntryEquity * (dailyDrawdownPercent / 100.0);
            Double maxDailyLossPercent = calculateMaxDailyLossPercent(maxDailyLossRecorded, maxDailyLoss);
            System.out.printf("Max Daily Loss Percent: %.2f%%\n", maxDailyLossPercent);

            // Расчет уровня Daily Loss
            Double dailyLossLevel = calculateDailyLossLevel(dayEntryEquity, dailyDrawdownPercent);
            System.out.printf("Daily Loss Level: %.2f\n", dailyLossLevel);

            // Расчет процента Daily Loss
            if (currentEquity != null) {
                Double dailyLossPercent = calculateDailyLossPercent(dayEntryEquity, currentEquity, maxDailyLoss);
                System.out.printf("Daily Loss Percent: %.2f%%\n", dailyLossPercent);
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

    // Метод для расчета Max Daily Loss Recorded
    private static Double calculateMaxDailyLoss(Double dayEntryEquity, Double lowestTodayEquity) {
        return dayEntryEquity - lowestTodayEquity;
    }

    // Метод для расчета Max Daily Loss Percent
    private static Double calculateMaxDailyLossPercent(Double maxDailyLossRecorded, Double maxDailyLoss) {
        if (maxDailyLoss == null || maxDailyLoss == 0) return null;
        return (maxDailyLossRecorded / maxDailyLoss) * 100.0;
    }

    // Метод для расчета уровня Daily Loss
    private static Double calculateDailyLossLevel(Double dayEntryEquity, Double dailyDrawdownPercent) {
        return dayEntryEquity - (dayEntryEquity * dailyDrawdownPercent / 100.0);
    }

    // Метод для расчета процента Daily Loss
    private static Double calculateDailyLossPercent(Double dayEntryEquity, Double currentEquity, Double maxDailyLoss) {
        if (maxDailyLoss == null || maxDailyLoss == 0) return null;
        Double currentDailyLoss = dayEntryEquity - currentEquity;
        return (currentDailyLoss / maxDailyLoss) * 100.0;
    }
}
