package dailyLoss;

import java.util.Scanner;

public class DayEntryEquityAndInitialBalance {

    // Метод для расчета Max Daily Loss Recorded
    public static Double calculateMaxDailyLoss(Double dayEntryEquity, Double lowestTodayEquity) {
        if (dayEntryEquity != null && lowestTodayEquity != null) {
            return dayEntryEquity - lowestTodayEquity;
        }
        return null; // Если данных недостаточно, возвращаем null
    }

    // Метод для расчета процента Max Daily Loss
    public static Double calculateMaxDailyLossPercent(Double maxDailyLossRecorded, Double initialBalance, Double dailyDrawdownPercent) {
        if (maxDailyLossRecorded != null && initialBalance != null && dailyDrawdownPercent != null) {
            double maxDailyLossLimit = initialBalance * (dailyDrawdownPercent / 100);
            return (maxDailyLossRecorded / maxDailyLossLimit) * 100;
        }
        return null; // Если данных недостаточно, возвращаем null
    }

    // Метод для расчета процента потери начального баланса
    public static Double calculateInitialAccountBalanceLossPercent(Double initialBalanceLoss, Double initialBalance, Double overallDrawdownPercent) {
        if (initialBalanceLoss != null && initialBalance != null && overallDrawdownPercent != null) {
            double maxOverallLoss = initialBalance * (overallDrawdownPercent / 100);
            return (initialBalanceLoss / maxOverallLoss) * 100;
        }
        return null; // Если данных недостаточно, возвращаем null
    }

    // Метод для расчета уровня Daily Loss
    public static Double calculateDailyLossLevel(Double dayEntryEquity, Double initialBalance, Double dailyDrawdownPercent) {
        if (dayEntryEquity != null && initialBalance != null && dailyDrawdownPercent != null) {
            return dayEntryEquity - (dailyDrawdownPercent / 100) * initialBalance;
        }
        return null; // Если данных недостаточно, возвращаем null
    }

    // Метод для расчета процента Daily Loss
    public static Double calculateDailyLossPercent(Double dayEntryEquity, Double currentEquity, Double initialBalance, Double dailyDrawdownPercent) {
        if (dayEntryEquity != null && currentEquity != null && initialBalance != null && dailyDrawdownPercent != null) {
            double currentDailyLoss = dayEntryEquity - currentEquity;
            double maxDailyLoss = initialBalance * (dailyDrawdownPercent / 100);
            return (currentDailyLoss / maxDailyLoss) * 100;
        }
        return null; // Если данных недостаточно, возвращаем null
    }

    // Метод для парсинга данных, чтобы избежать ошибок при вводе
    public static Double parseDoubleOrNull(String input) {
        try {
            // Заменяем запятую на точку, если есть
            input = input.replace(",", ".");
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return null; // Если данных нет, возвращаем null
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод данных (можно нажать Enter, чтобы пропустить)
        System.out.print("Введите Day Entry Equity (или нажмите Enter, чтобы пропустить): ");
        Double dayEntryEquity = parseDoubleOrNull(scanner.nextLine());

        System.out.print("Введите начальный баланс (Initial Balance) (или нажмите Enter, чтобы пропустить): ");
        Double initialBalance = parseDoubleOrNull(scanner.nextLine());

        System.out.print("Введите Daily Drawdown % (или нажмите Enter, чтобы пропустить): ");
        Double dailyDrawdownPercent = parseDoubleOrNull(scanner.nextLine());

        System.out.print("Введите текущий капитал (Current Equity) (или нажмите Enter, чтобы пропустить): ");
        Double currentEquity = parseDoubleOrNull(scanner.nextLine());

        System.out.print("Введите минимальный капитал за сегодня (Lowest Today's Equity) (или нажмите Enter, чтобы пропустить): ");
        Double lowestTodayEquity = parseDoubleOrNull(scanner.nextLine());

        System.out.print("Введите общий процент убытков (Overall Drawdown %) (или нажмите Enter, чтобы пропустить): ");
        Double overallDrawdownPercent = parseDoubleOrNull(scanner.nextLine());

        // Расчет Max Daily Loss Recorded
        Double maxDailyLossRecorded = calculateMaxDailyLoss(dayEntryEquity, lowestTodayEquity);
        if (maxDailyLossRecorded != null) {
            System.out.printf("Max Daily Loss Recorded: %.2f\n", maxDailyLossRecorded);
        }

        // Расчет процента Max Daily Loss
        Double maxDailyLossPercent = calculateMaxDailyLossPercent(maxDailyLossRecorded, initialBalance, dailyDrawdownPercent);
        if (maxDailyLossPercent != null) {
            System.out.printf("Max Daily Loss Percent: %.2f%%\n", maxDailyLossPercent);
        }

        // Расчет процента потери начального баланса
        if (initialBalance != null && currentEquity != null) {
            Double initialBalanceLoss = initialBalance - currentEquity;
            Double initialBalanceLossPercent = calculateInitialAccountBalanceLossPercent(initialBalanceLoss, initialBalance, overallDrawdownPercent);
            if (initialBalanceLossPercent != null) {
                System.out.printf("Initial Account Balance Loss Percent: %.2f%%\n", initialBalanceLossPercent);
            }
        }

        // Расчет уровня Daily Loss
        Double dailyLossLevel = calculateDailyLossLevel(dayEntryEquity, initialBalance, dailyDrawdownPercent);
        if (dailyLossLevel != null) {
            System.out.printf("Daily Loss Level: %.2f\n", dailyLossLevel);
        }

        // Расчет процента Daily Loss
        Double dailyLossPercent = calculateDailyLossPercent(dayEntryEquity, currentEquity, initialBalance, dailyDrawdownPercent);
        if (dailyLossPercent != null) {
            System.out.printf("Daily Loss Percent: %.2f%%\n", dailyLossPercent);
        }

        scanner.close();
    }
}
