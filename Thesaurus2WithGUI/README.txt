Hey Everone,
just some notes about the updated GUI.

The main GUI is located at "PassageFinderInterface2" Class

The runner for this is in ThesaurusRunner Class

Brie: the thesaurus maker i copied from yours is in the tempThes class
-I temporarily commented out your code just to get the antonyms working
-I left some comments in the doc if you'd like add functionality from your code into 
the code currently written
-the hashmap uses keywords along with a SynAnt object that stores synonyms and antonyms 
for each keyword
-FEEL FREE TO LET ME KNOW IF YOU GET STUCK ON UNDERSTANDING THE CODE I MADE
  --I don't do a good job always with comments/readability

FEW OTHER NOTES
-ScanText primaril scans text and then adds up occurences of antonyms [soon to add synonyms]
-ScoreWords scores up antonyms and synonyms [it works alongside ScanText to obtain score 
for each line of text]


TO DO
Shiao a method that has a parameter as theme
it returns a hashmap<string, string> string of key song titles/artist, string of lyrics 
