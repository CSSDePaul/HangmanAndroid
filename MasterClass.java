import java.io.IOException;


public class MasterClass {
	
	private static String answer;
	private static final int maxGuesses = 6; //as agreed upon
	private static int wrongGuesses = 0;
	private static boolean[] letterGuessed = new boolean[26]; //to be implemented later
	private static boolean[] revealedLetters;
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		answer = generateAnswer();
		revealedLetters = new boolean[answer.length()];
		println("Welcome to Hangman.");
		printAnswerSoFar();
		println("Take a guess");
		
		while (wrongGuesses < maxGuesses){
			char letter = getNextGuess();
			if(answer.indexOf(letter) != -1){//letter is in answer
				correctGuess(letter);
			}
			else{
				incorrectGuess(letter);
			}
			printAnswerSoFar();
		}
		userLost(); //ran out of guesses
		

	}
	
	//helper methods to ease transition into android
	public static void print(String s){
		System.out.print(s);
	}
	
	public static void print(char c){
		System.out.print(c);
	}
	
	public static void println(String s){
		System.out.println(s);
	}
	
	//should return a random word to guess
	public static String generateAnswer(){
		return "hello";
	}
	
	public static char getNextGuess(){
		try {
			char letter = (char) System.in.read();
			while(letter == '\n'){ //so that it ignores new lines when user hits enter
				letter = (char) System.in.read();
			}
			return letter; 
		} catch (IOException e) {
			println("Nice going. You broke the input, dirtbag. You get minus one try for your insolence.");
			return 0; //guaranteed not to be in the answer string
		}
	}
	
	public static void correctGuess(char letter){
		println("correct guess: "+letter);
		int curIndex = answer.indexOf(letter);
		while(curIndex != -1 && curIndex < answer.length()){ //keep searching for letter until can't find it anymore
			revealedLetters[curIndex] = true; //that letter should be revealed
			curIndex = answer.indexOf(letter, curIndex+1); //find next letter
		}
		checkForWin();
	}
	
	public static void checkForWin(){
		for(int i=0; i<revealedLetters.length; i++){
			if(!revealedLetters[i]){
				return; //there is at least one letter not revealed, so no win
			}
		}
		//if at this point, all letters were revealed so user won
		println("You win! Congratulations!");
		endTheGame();
	}
	
	public static void userLost(){
		println("You lose. The answer was "+answer);
	}
	
	public static void endTheGame(){
		System.exit(0);
	}
	
	public static void printAnswerSoFar(){
		for(int i=0; i<answer.length(); i++){ //print the revealed letters
			if(revealedLetters[i]){
				print(answer.charAt(i));
			}
			else{
				print("_");
			}
			print(" ");
		}
		println("");//new line
	}
	
	public static void incorrectGuess(char letter){
		wrongGuesses++;
		println("incorrect guess: "+letter+". Number of guesses left: "+(maxGuesses-wrongGuesses));
	}
	
	

}
