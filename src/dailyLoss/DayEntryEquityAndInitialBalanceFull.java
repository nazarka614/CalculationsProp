package dailyLoss;

import java.util.Scanner;

public class DayEntryEquityAndInitialBalanceFull {

    // Метод для расчета Max Daily Loss Recorded (без учета пустых значений)
    public static Double calculateMaxDailyLoss(Double dayEntryEquity, Double lowestTodayEquity) {
        if (dayEntryEquity != null && lowestTodayEquity != null) {
            return dayEntryEquity - lowestTodayEquity;
        }
        return null;
    }

    // Метод для расчета процента Max Daily Loss
    public static Double calculateMaxDailyLossPercent(Double maxDailyLossRecorded, Double initialBalance, Double dailyDrawdownPercent) {
        if (maxDailyLossRecorded != null && initialBalance != null && dailyDrawdownPercent != null) {
            double maxDailyLossLimit = initialBalance * (dailyDrawdownPercent / 100);
            return (maxDailyLossRecorded / maxDailyLossLimit) * 100;
        }
        return null;
    }

    // Метод для расчета процента потери начального баланса
    public static Double calculateInitialAccountBalanceLossPercent(Double initialBalanceLoss, Double initialBalance, Double overallDrawdownPercent) {
        if (initialBalanceLoss != null && initialBalance != null && overallDrawdownPercent != null) {
            double maxOverallLoss = initialBalance * (overallDrawdownPercent / 100);
            return (initialBalanceLoss / maxOverallLoss) * 100;
        }
        return null;
    }

    // Метод для расчета уровня Daily Loss
    public static Double calculateDailyLossLevel(Double dayEntryEquity, Double initialBalance, Double dailyDrawdownPercent) {
        double result = 0.0;
        if (dayEntryEquity != null) {
            result += dayEntryEquity;
        }
        if (initialBalance != null && dailyDrawdownPercent != null) {
            result -= (dailyDrawdownPercent / 100) * initialBalance;
        }
        return result != 0.0 ? result : null;
    }

    // Метод для расчета процента Daily Loss
    public static Double calculateDailyLossPercent(Double dayEntryEquity, Double currentEquity, Double initialBalance, Double dailyDrawdownPercent) {
        if (dayEntryEquity != null && currentEquity != null && initialBalance != null && dailyDrawdownPercent != null) {
            double currentDailyLoss = dayEntryEquity - currentEquity;
            double maxDailyLoss = initialBalance * (dailyDrawdownPercent / 100);
            return (currentDailyLoss / maxDailyLoss) * 100;
        }
        return null;
    }

    // Обновление Day Entry Equity после выплаты
    public static Double updateDayEntryEquityAfterPayout(Double previousDayEntryEquity, Double payoutAmount, Double revenueShareAmount) {
        double result = 0.0;
        if (previousDayEntryEquity != null) {
            result += previousDayEntryEquity;
        }
        if (payoutAmount != null) {
            result -= payoutAmount;
        }
        if (revenueShareAmount != null) {
            result -= revenueShareAmount;
        }
        return result != 0.0 ? result : null;
    }

    public static Double parseDoubleOrNull(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод данных (можно нажать Enter, чтобы пропустить ввод)
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

        System.out.print("Введите сумму выплаты (Payout Amount) (или нажмите Enter, чтобы пропустить): ");
        Double payoutAmount = parseDoubleOrNull(scanner.nextLine());

        System.out.print("Введите сумму доли прибыли (Revenue Share Amount) (или нажмите Enter, чтобы пропустить): ");
        Double revenueShareAmount = parseDoubleOrNull(scanner.nextLine());

        // Расчет Max Daily Loss Recorded
        Double maxDailyLoss = calculateMaxDailyLoss(dayEntryEquity, lowestTodayEquity);
        if (maxDailyLoss != null) {
            System.out.printf("Max Daily Loss Recorded: %.2f\n", maxDailyLoss);
        } else {
            System.out.println("Недостаточно данных для расчета Max Daily Loss Recorded.");
        }

        // Расчет процента Max Daily Loss
        Double maxDailyLossPercent = calculateMaxDailyLossPercent(maxDailyLoss, initialBalance, dailyDrawdownPercent);
        if (maxDailyLossPercent != null) {
            System.out.printf("Max Daily Loss Percent: %.2f%%\n", maxDailyLossPercent);
        } else {
            System.out.println("Недостаточно данных для расчета Max Daily Loss Percent.");
        }

        // Расчет процента потери начального баланса
        if (initialBalance != null && currentEquity != null) {
            Double initialBalanceLoss = initialBalance - currentEquity;
            Double initialBalanceLossPercent = calculateInitialAccountBalanceLossPercent(initialBalanceLoss, initialBalance, overallDrawdownPercent);
            if (initialBalanceLossPercent != null) {
                System.out.printf("Initial Account Balance Loss Percent: %.2f%%\n", initialBalanceLossPercent);
            } else {
                System.out.println("Недостаточно данных для расчета Initial Account Balance Loss Percent.");
            }
        }

        // Расчет уровня Daily Loss
        Double dailyLossLevel = calculateDailyLossLevel(dayEntryEquity, initialBalance, dailyDrawdownPercent);
        if (dailyLossLevel != null) {
            System.out.printf("Daily Loss Level: %.2f\n", dailyLossLevel);
        } else {
            System.out.println("Недостаточно данных для расчета Daily Loss Level.");
        }

        // Расчет процента Daily Loss
        Double dailyLossPercent = calculateDailyLossPercent(dayEntryEquity, currentEquity, initialBalance, dailyDrawdownPercent);
        if (dailyLossPercent != null) {
            System.out.printf("Daily Loss Percent: %.2f%%\n", dailyLossPercent);
        } else {
            System.out.println("Недостаточно данных для расчета Daily Loss Percent.");
        }

        // Обновление Day Entry Equity после выплаты
        Double newDayEntryEquity = updateDayEntryEquityAfterPayout(dayEntryEquity, payoutAmount, revenueShareAmount);
        if (newDayEntryEquity != null) {
            System.out.printf("New Day Entry Equity after Payout: %.2f\n", newDayEntryEquity);
        } else {
            System.out.println("Недостаточно данных для обновления Day Entry Equity после выплаты.");
        }

        scanner.close();
    }
}