package project;

import java.text.SimpleDateFormat;
import java.util.*;

public class ResultProcessor {

    private static final SimpleDateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String separator = "----------------------------------------------------------------------------";

    public static String processUser(List<CallInfo> callInfos) {
        Collections.sort(callInfos, new Comparator<CallInfo>() {
            @Override
            public int compare(CallInfo o1, CallInfo o2) {
                return o1.getStartCall().compareTo(o2.getStartCall());
            }
        });

        Rate rate = callInfos.get(0).getRate();

        String output = "Tariff index: " + rate.getCode() + "\n";
        output += separator + "\n";
        output += "Report for phone number " + callInfos.get(0).getNumberPhone() + ":\n";
        output += separator + "\n";
        output += "| Call Type |   Start Time        |     End Time        | Duration | Cost  |\n";
        output += separator + "\n";
        List<Double> result = countCost(callInfos, rate);
        double total = result.get(result.size() - 1);

        for (int ind = 0; ind < callInfos.size(); ind++) {
            CallInfo callInfo = callInfos.get(ind);
            // System.out.println(callInfos.get(ind).getCallType().getCode());
            output += "|     " + callInfo.getCallType().getCode() + "    ";
            output += "| " + outputFormatter.format(callInfo.getStartCall()) + " ";
            output += "| " + outputFormatter.format(callInfo.getFinishCall()) + " ";
            output += "| " + callInfo.getPeriod() + " ";
            output += "|  " + Double.toString(result.get(ind)) + " |\n";
        }
        output += separator + "\n";
        output += "|                                           Total Cost: |     " + Double.toString(total) +
                " rubles |\n";
        output += separator + "\n";
        return output;
    }

    private static List<Double> countCost(List<CallInfo> callInfos, Rate rate) {
        List<Double> costs = new ArrayList<>();
        long threshold = rate.getThreshold();
        double costBeforeTh = rate.getCostBeforeTh();
        double costAfterTh = rate.getCostAfterTh();
        boolean isIncomingFree = rate.isIncomingFree();

        double total = 0.0;
        long minuties = 0;

        for (CallInfo callInfo : callInfos) {
            long minutes = callInfo.getPeriodMinute();
            minuties += minutes;
            if (isIncomingFree && callInfo.getCallType() == CallType.INCOMING) {
                costs.add(0.0);
            } else {
                if (threshold - minutes > 0) {
                    costs.add(costBeforeTh * minutes);
                    total += costBeforeTh * minutes;
                    threshold = threshold - minutes;
                } else {
                    if (threshold > 0 && rate == Rate.UNLIMITED) {
                        total += 100;
                    }
                    double cost = threshold * costBeforeTh;
                    cost += (minutes - threshold) * costAfterTh;
                    total += cost;
                    threshold = Math.max(0, threshold - minutes);
                    costs.add(cost);
                }
            }
        }
        costs.add(total);
        //System.out.println(minuties);
        return costs;
    }
}
