package overallLoss;

import java.util.Scanner;

public class OverallLossCalculatorMaxRecordedBalance {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод данных пользователем
        System.out.print("Введите Max Recorded Balance (максимальный баланс за весь период) (или нажмите Enter, чтобы пропустить): ");
        Double maxRecordedBalance = parseDoubleOrNull(scanner.nextLine());

        System.out.print("Введите Lowest Equity after Max Balance (минимальный капитал после достижения максимального баланса) (или нажмите Enter, чтобы пропустить): ");
        Double lowestEquityAfterMax = parseDoubleOrNull(scanner.nextLine());

        System.out.print("Введите Overall Drawdown % (общий процент убытков) (или нажмите Enter, чтобы пропустить): ");
        Double overallDrawdownPercent = parseDoubleOrNull(scanner.nextLine());

        System.out.print("Введите Current Equity (текущий капитал) (или нажмите Enter, чтобы пропустить): ");
        Double currentEquity = parseDoubleOrNull(scanner.nextLine());

        System.out.print("Введите Payout Amount (сумма выплаты) (или нажмите Enter, чтобы пропустить): ");
        Double payoutAmount = parseDoubleOrNull(scanner.nextLine());

        System.out.print("Введите Revenue Share Amount (сумма доли прибыли) (или нажмите Enter, чтобы пропустить): ");
        Double revenueShareAmount = parseDoubleOrNull(scanner.nextLine());

        // Обновление Max Recorded Balance после выплат (только для Live Accounts)
        if (payoutAmount != null && revenueShareAmount != null && maxRecordedBalance != null) {
            maxRecordedBalance = updateMaxRecordedBalance(maxRecordedBalance, payoutAmount, revenueShareAmount);
            System.out.printf("Обновленный Max Recorded Balance после выплаты и доли прибыли: %.2f\n", maxRecordedBalance);
        }

        // Расчет Max Overall Loss Recorded
        Double maxOverallLossRecorded = calculateMaxOverallLossRecorded(maxRecordedBalance, lowestEquityAfterMax);
        if (maxOverallLossRecorded != null) {
            System.out.printf("Max Overall Loss Recorded: %.2f\n", maxOverallLossRecorded);
        }

        // Расчет Max Overall Loss
        Double maxOverallLoss = calculateMaxOverallLoss(maxRecordedBalance, overallDrawdownPercent);
        if (maxOverallLoss != null) {
            System.out.printf("Max Overall Loss (поддерживающая формула): %.2f\n", maxOverallLoss);
        }

        // Расчет Max Overall Loss Percent
        Double maxOverallLossPercent = calculateMaxOverallLossPercent(maxOverallLossRecorded, maxOverallLoss);
        if (maxOverallLossPercent != null) {
            System.out.printf("Max Overall Loss Percent: %.2f%%\n", maxOverallLossPercent);
        }

        // Расчет Overall Loss Level
        Double overallLossLevel = calculateOverallLossLevel(maxRecordedBalance, overallDrawdownPercent);
        if (overallLossLevel != null) {
            System.out.printf("Overall Loss Level: %.2f\n", overallLossLevel);
        }

        // Расчет Overall Account Balance Loss
        Double overallAccountBalanceLoss = calculateOverallAccountBalanceLoss(maxRecordedBalance, currentEquity);
        if (overallAccountBalanceLoss != null) {
            System.out.printf("Overall Account Balance Loss: %.2f\n", overallAccountBalanceLoss);
        }

        // Расчет Initial Account Balance Loss Percent
        Double initialAccountBalanceLossPercent = calculateInitialAccountBalanceLossPercent(overallAccountBalanceLoss, maxOverallLoss);
        if (initialAccountBalanceLossPercent != null) {
            System.out.printf("Initial Account Balance Loss Percent: %.2f%%\n", initialAccountBalanceLossPercent);
        }

        // Обработка сценариев
        if (overallLossLevel != null && currentEquity != null) {
            if (currentEquity < overallLossLevel) {
                System.out.println("Lose Scenario: Вы нарушили торговые правила. Ваш аккаунт деактивирован и больше не может продолжать торговлю.");
            } else {
                System.out.println("Win Scenario: Успешное завершение. Фаза 1 аккаунта деактивирована.");
            }
        }

        scanner.close();
    }

    /**
     * Метод для парсинга введенных данных.
     * Заменяет запятую на точку и пытается преобразовать строку в Double.
     * Если ввод пустой или некорректный, возвращает null.
     */
    private static Double parseDoubleOrNull(String input) {
        try {
            if (input.isEmpty()) {
                return null;
            }
            // Заменяем запятую на точку для корректного парсинга
            input = input.replace(",", ".");
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод: " + input);
            return null;
        }
    }

    /**
     * Метод для обновления Max Recorded Balance после выплаты и доли прибыли.
     */
    private static Double updateMaxRecordedBalance(Double maxRecordedBalance, Double payoutAmount, Double revenueShareAmount) {
        return maxRecordedBalance - payoutAmount - revenueShareAmount;
    }

    /**
     * Метод для расчета Max Overall Loss Recorded.
     * Формула: Max Recorded Balance - Lowest Equity after Max Balance
     */
    private static Double calculateMaxOverallLossRecorded(Double maxRecordedBalance, Double lowestEquityAfterMax) {
        if (maxRecordedBalance != null && lowestEquityAfterMax != null) {
            return maxRecordedBalance - lowestEquityAfterMax;
        }
        return null;
    }

    /**
     * Метод для расчета Max Overall Loss.
     * Формула: Max Recorded Balance * Overall Drawdown %
     */
    private static Double calculateMaxOverallLoss(Double maxRecordedBalance, Double overallDrawdownPercent) {
        if (maxRecordedBalance != null && overallDrawdownPercent != null) {
            return maxRecordedBalance * (overallDrawdownPercent / 100.0);
        }
        return null;
    }

    /**
     * Метод для расчета Max Overall Loss Percent.
     * Формула: (Max Overall Loss Recorded / Max Overall Loss) * 100%
     */
    private static Double calculateMaxOverallLossPercent(Double maxOverallLossRecorded, Double maxOverallLoss) {
        if (maxOverallLossRecorded != null && maxOverallLoss != null && maxOverallLoss != 0) {
            return (maxOverallLossRecorded / maxOverallLoss) * 100.0;
        }
        return null;
    }

    /**
     * Метод для расчета Overall Loss Level.
     * Формула: Max Recorded Balance - (Max Recorded Balance * Overall Drawdown %)
     */
    private static Double calculateOverallLossLevel(Double maxRecordedBalance, Double overallDrawdownPercent) {
        if (maxRecordedBalance != null && overallDrawdownPercent != null) {
            return maxRecordedBalance - (maxRecordedBalance * overallDrawdownPercent / 100.0);
        }
        return null;
    }

    /**
     * Метод для расчета Overall Account Balance Loss.
     * Формула: Max Recorded Balance - Current Equity
     */
    private static Double calculateOverallAccountBalanceLoss(Double maxRecordedBalance, Double currentEquity) {
        if (maxRecordedBalance != null && currentEquity != null) {
            return maxRecordedBalance - currentEquity;
        }
        return null;
    }

    /**
     * Метод для расчета Initial Account Balance Loss Percent.
     * Формула: (Overall Account Balance Loss / Max Overall Loss) * 100%
     */
    private static Double calculateInitialAccountBalanceLossPercent(Double overallAccountBalanceLoss, Double maxOverallLoss) {
        if (overallAccountBalanceLoss != null && maxOverallLoss != null && maxOverallLoss != 0) {
            return (overallAccountBalanceLoss / maxOverallLoss) * 100.0;
        }
        return null;
    }
}
