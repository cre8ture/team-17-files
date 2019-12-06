README for Final Project - Team 17
Team Members: Brie Daley, Shiao X. Wu, Kai Kleinbard

Dear Grader,
Team 17s’ project gathers passages based on users inputted key themes from various sources. We have described the 3 “in-progress” components below [each of them has jUnit tests, and non-trivial methods]. NOTE: Each \*starred\* title refers to a folder inside our repository that we’d like you to view.

\*Thesaurus\*
1) Thesaurus class scans thesaurus.txt file to create a list of keywords, synonyms and antonyms.  A getSynonymList method matches 
keywords to corresponding synonyms in a Hashmap.  We will work on finishing a getAntonymList method that works similarly in case we add that 
function.
2) To test getSynonymList method, run main method in ThemeSelector with Thesaurus and thesaurus.txt.
3) Enter a theme or keyword.  If keyword is found, a synonym list is returned.  If keyword is not found, user receives message of 
“Theme not found.”
4) JUnit tests for Thesaurus and ThemeSelector have been started and will be completed later.

\*API\*
1) This is a maven project. Please make sure to have maven installed before running this program 
2) Enter a theme or a keyword you’d like to search for, for example “love”. This will be connected with user input in ThemeSelector 
class. 
3) The program returns a list of songs including song titles, song urls, artists, artists urls. 
4) We will work on methods that return specific lyrics from a song later. 
5) We will work on other APIs later, for example books API, to return a list of books, including book titles, book urls, authors, and 
authors urls. 
6) Please ignore the cookie rejected warning for now. We will take a deeper look at it. 
7) Please go to test/java/com/finalproject/maven/quickstart, and then refer to APIRunnerTest.java for a JUnit test for APIRunner class. 

\*GUI\*
1) This class uses Swing to create a user interface.
2) Open the “GUI” folder
3) Open and run “PassageFinderInterface”. This will open the interface.
4) Currently it has partial functionality and will connect to the other methods/classes we are building
5) It will however allow user to open a file if user pulls down “Search your own file” and it will read all text in the file
6)There is also a JUnit test for the GUI that uses a Robot() function in Java to interact with the GUI. To access this JUnit test, 
open the “GUI” folder, and open “PassageFinderInterfaceTest” and run.


\*ALSO NOTE:
Our CRC cards are at this link: 
https://docs.google.com/document/d/1wo198RjRl5lluDBb-4DlGQxvhKP58jO9bcbpx8hBWag/edit?usp=sharing 
and uploaded as “CRC Cards for Final Project - Team 17.docx” in our repository.
