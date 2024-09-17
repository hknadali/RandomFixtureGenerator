import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class RandomFixtureGenerator {
	
	private ArrayList<String> teams;
	String[] home;
	String[] away;
	int rounds;
	int matches;
	
	public RandomFixtureGenerator(ArrayList<String> teams) {
		this.teams = teams;
		
		if(teams.size() % 2 == 1)
			teams.add("Bay");
		
		rounds = (this.teams.size() - 1) * 2;
		matches = this.teams.size() / 2;
		home = new String[rounds * matches];
		away = new String[rounds * matches];
		
	}
	
	public void generateRandomFixture() {
		
		Collections.shuffle(teams);
		
		int d = 0;
		int r = (int)Math.random() * 2;
		for(int i = 0; i < rounds; i++) {
			for(int j = 0; j < matches; j++) {
				if(i % 2 == r) {
					home[d] = teams.get(j);
					away[d] = teams.get(teams.size() - 1 - j);
					
				}
				else {
					home[d] = teams.get(teams.size() - 1 - j);
					away[d] = teams.get(j);
				}
				d++;
			}
			Collections.rotate(teams.subList(1, teams.size()), matches);
		}
	}
	
	public void printFixture() {
		for(int i = 0; i < (teams.size() - 1) * 2; i++) {
			System.out.println("#### Match Day " + (i+1) + " ####");
			for(int j = 0; j < matches; j++) {
				System.out.println(home[i * matches + j ] + " vs " + away[i * matches + j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the number of teams: ");
		int n = sc.nextInt();
		ArrayList<String> teams = new ArrayList<>(n);
		System.out.println("Please enter the team names: ");
		sc.nextLine();
		for(int i = 0; i < n; i++) {
			teams.add(sc.nextLine());
		}
		
		RandomFixtureGenerator rfg = new RandomFixtureGenerator(teams);
		rfg.generateRandomFixture();
		rfg.printFixture();
		
		sc.close();
	}
}
