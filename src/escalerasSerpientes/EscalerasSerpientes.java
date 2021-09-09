package escalerasSerpientes;

import java.util.ArrayList;
import java.util.Random;

public class EscalerasSerpientes {

	private ArrayList<ArrayList<Integer>> escaleras = new ArrayList<ArrayList<Integer>>(),
			serpientes = new ArrayList<ArrayList<Integer>>();

	public EscalerasSerpientes() {
		// TODO Auto-generated constructor stub
//		initEscaleras();
		initSerpientes();
	}

	private void initEscaleras() {
		// TODO Auto-generated method stub
		int zona = 0, i = 0;
		Random temp = new Random();

		int randomN1 = temp.nextInt(3) + 5;
		int randomN2 = temp.nextInt(5) + 8;

		ArrayList<Integer> temporal = new ArrayList<Integer>(2);

		while (!(escaleras.size() == 6)) {

//			System.out.println(randomN1 + "==" + (randomN2 + randomN1));

			temporal.add(randomN1);
			temporal.add(randomN1 + randomN2);

			zona += randomN2 * 1.4;

//			System.out.println("Zona permitida " + zona + "+");

			randomN1 = temp.nextInt(3) + 5 + zona;
			randomN2 = temp.nextInt(5) + 8;

			escaleras.add(temporal);
			temporal.clear();
		}
	}

	private void initSerpientes() {
		// TODO Auto-generated method stub
		Random temp = new Random();

		int randomN1 = temp.nextInt(5) + 25;
		int randomN2 = 18;

		int zona = 0;

		while (!(serpientes.size() == 6)) {

			ArrayList<Integer> temporal = new ArrayList<Integer>(2);

			System.out.println(randomN1 + "++" + (randomN1 - randomN2));

			temporal.add(randomN1);
			temporal.add(randomN1 - randomN2);

			zona += 11;

			randomN1 = temp.nextInt(5) + (25 + zona);
//			randomN2 = temp.nextInt(4) + 9;

			serpientes.add(temporal);
//			temporal.clear();
		}

	}

	public ArrayList<ArrayList<Integer>> getEscaleras() {
		return escaleras;
	}

	public ArrayList<ArrayList<Integer>> getSerpientes() {
		return serpientes;
	}

}
