package project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class ParserInput {
    private static final SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
    private static Rate[] rates = Rate.values();
    private static CallType[] types = CallType.values();
    public static CallInfo parseInputLine(String input) throws ParseException {
        if (input == null) {
            return null;
        }
        CallInfo callInfo = new CallInfo();
        String parts[] = input.split(", ");

        if (parts.length != 5) {
            return null;
        }

        for (int ind = 0; ind < types.length; ind++) {
            if (parts[0].equals(types[ind].getCode())) {
                callInfo.setCallType(types[ind]);
                break;
            }
        }
        callInfo.setNumberPhone(parts[1]);
        callInfo.setStartCall(inputFormatter.parse(parts[2]));
        callInfo.setFinishCall(inputFormatter.parse(parts[3]));

        for (int ind = 0; ind < rates.length; ind++) {
            if (parts[4].equals(rates[ind].getCode())) {
                callInfo.setRate(rates[ind]);
                break;
            }
        }
        return callInfo;
    }

}
