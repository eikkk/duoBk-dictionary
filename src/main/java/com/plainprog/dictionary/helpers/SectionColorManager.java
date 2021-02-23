package com.plainprog.dictionary.helpers;

import java.util.*;

public class SectionColorManager {
    private final static ArrayList<String> greenSet = new ArrayList<>(Arrays.asList("344F2D", "207D55", "27DB8D", "67D6A6", "94C973", "A1F08D", "BFB826", "878110"));
    private final static ArrayList<String> violetSet = new ArrayList<>(Arrays.asList("190D1C", "5E1573", "7A009C", "7A009C", "9B46B3"));
    private final static ArrayList<String> redSet = new ArrayList<>(Arrays.asList("A10025", "AD2646", "C9677E", "AB7884", "EDC0CB", "E64B1C", "944128"));
    private final static ArrayList<String> purplePinkSet = new ArrayList<>(Arrays.asList("A10025", "AD2646", "5240A1", "AB7884", "2B1D6B", "A84589", "9C5F88", "F73EBD"));
    private final static ArrayList<String> brownOrangeSet = new ArrayList<>(Arrays.asList("301D04", "453015", "A35F05", "6E624F"));
    private final static ArrayList<String> blueSet = new ArrayList<>(Arrays.asList("3F75E0", "4063A8", "6595F6", "4482FC", "041D4D", "083C9B"));
    private final static ArrayList<String> yellowSet = new ArrayList<>(Arrays.asList("BFB826", "878110", "5C580A", "D4C46E", "D4CC9F", "AB982E", "F0CF16"));

    public static String getMostSuitableColor(List<String> existingColors){
        int greenColors = 0;
        int violetColors = 0;
        int redColors = 0;
        int purplePinkColors = 0;
        int brownOrangeColors = 0;
        int blueColors = 0;
        int yellowColors = 0;
        for (String color : existingColors){
            if (greenSet.contains(color)) greenColors++;
            if (violetSet.contains(color)) violetColors++;
            if (redSet.contains(color)) redColors++;
            if (purplePinkSet.contains(color)) purplePinkColors++;
            if (brownOrangeSet.contains(color)) brownOrangeColors++;
            if (blueSet.contains(color)) blueColors++;
            if (yellowSet.contains(color)) yellowColors++;
        }
        ArrayList<Integer> counts = new ArrayList<Integer>(Arrays.asList(greenColors, violetColors, redColors, purplePinkColors, brownOrangeColors, blueColors, yellowColors));
        int minIndex = counts.indexOf(Collections.min(counts));
        ArrayList<String> colorSetToPickFrom;
        switch (minIndex){
            case 0:
                colorSetToPickFrom = greenSet; break;
            case 1:
                colorSetToPickFrom = violetSet; break;
            case 2:
                colorSetToPickFrom = redSet; break;
            case 3:
                colorSetToPickFrom = purplePinkSet; break;
            case 4:
                colorSetToPickFrom = brownOrangeSet; break;
            case 5:
                colorSetToPickFrom = blueSet; break;
            case 6:
                colorSetToPickFrom = yellowSet; break;
            default:
                colorSetToPickFrom = redSet; break;
        }
        Collections.shuffle(colorSetToPickFrom);
        for (String color : colorSetToPickFrom){
            if (!existingColors.contains(color)) return color;
        }
        return  colorSetToPickFrom.get((new Random().nextInt(colorSetToPickFrom.size())));
    }
}
