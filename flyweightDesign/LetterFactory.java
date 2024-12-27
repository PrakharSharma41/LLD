package flyweightDesign;

import java.util.HashMap;
import java.util.Map;

public class LetterFactory {
    private static Map<Character,ILetter>mp=new HashMap<>();
    public static ILetter creaLetter(char characterValue){
        if(mp.containsKey(characterValue)){
            return mp.get(characterValue);
        }else{
            DocumentCharacter characterObj=new DocumentCharacter(characterValue, "Arial", 10);
            mp.put(characterValue, characterObj);
            return  characterObj;
        }
    }
}
