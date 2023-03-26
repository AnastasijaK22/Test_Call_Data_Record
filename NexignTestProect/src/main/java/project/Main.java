package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        String separator = File.separator;
        Map<String, List<CallInfo>> users = new HashMap<>();
        File file = new File("cdr.txt");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            CallInfo callInfo = ParserInput.parseInputLine(input);
            String numberPhone = callInfo.getNumberPhone();
            if (users.get(numberPhone) == null) {
                List<CallInfo> callInfos = new ArrayList<>();
                callInfos.add(callInfo);
                users.put(numberPhone, callInfos);
            } else {
                users.get(numberPhone).add(callInfo);
            }
        }
        scanner.close();
        int count = 1;
        for (String key : users.keySet()) {
            String answer = ResultProcessor.processUser(users.get(key));
            File outputFile = new File("reports" + separator + "report" + Integer.toString(count));
            PrintWriter pw = new PrintWriter(outputFile);
            pw.println(answer);
            pw.close();
            count++;
        }
    }
}
