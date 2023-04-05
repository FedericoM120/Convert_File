package util;

import java.util.*;

public class Normalizer {
    private static String normalize(String cell) {
        if (cell.isEmpty())
            return "N/A";
        try {
            int ival = Integer.parseInt(cell);
            String rv = String.format("%+010d", ival);
            /*"" + Math.abs(ival);
            for(int i = 0; i < 9 - ("" + Math.abs(ival)).length();i++)
                rv = "0" + rv;
            rv = (ival >= 0?"+": "-") + rv;*/
            return rv;
        } catch (NumberFormatException exp) {
            try {
                //non-integer
                double dval = Double.parseDouble(cell);
                if (dval > 100 || dval < .01)
                    return String.format("%.2e", dval);
                else
                    return String.format("%.2f", dval);
            } catch (NumberFormatException exp2) {
                //non-numerical
                if (cell.length() > 13)
                    return String.format("%.10s...", cell);
                else
                    return cell;
            }
        }
    }

    public static ArrayList<String> normalizeTable(ArrayList<String> rows, String fileFormat) {
        ArrayList<String> list = new ArrayList<String>();
        //Your code here:
        //Step 1: Break down every row into cells
        String[] cells;
        String normalizedRow;

        if (fileFormat.equalsIgnoreCase("txt")) {
            for (int i = 0; i < rows.size(); i++) {

                cells = rows.get(i).split(" ");
                for (String c: cells) {
                    normalize(c);
                }
                normalizedRow = String.join(" ", cells);
                list.add(normalizedRow);
            }
        } else {
            for (int i = 0; i < rows.size(); i++) {
                cells = rows.get(i).split(",");
                for (int j = 0; j < cells.length; j++) {
                    cells[j] = normalize(cells[j]);
                }
                normalizedRow = String.join(",", cells);
                list.add(normalizedRow);
            }
        }
        return list;
    }
    //Step 2: call normalize to normalize each cell
    //Step 3: merge normalized cells with the same separator that you used to break them down.
}

