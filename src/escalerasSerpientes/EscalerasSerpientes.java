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

		int randomN1 = temp.nextInt(5) + 10;
		int randomN2 = 10;

		ArrayList<Integer> temporal = new ArrayList<Integer>(2);

		while (!(escaleras.size() == 6)) {

			temporal.add(randomN1);
			temporal.add(randomN1 + randomN2);

			zona += randomN2 * 1.4;

			randomN1 = temp.nextInt(3) + 5 + zona;
//			randomN2 = temp.nextInt(5) + 8;

			escaleras.add(temporal);
			temporal.clear();
		}
	}

	private void initSerpientes() {
		// TODO Auto-generated method stub
		Random temp = new Random();

		int randomN1 = temp.nextInt(5) + 25;
		int reduce = 18;

		int zona = 0;

		while (!(serpientes.size() == 5)) {

			if (!isContained(randomN1, serpientes) && !isContained(randomN1 - 18, serpientes)) {
				ArrayList<Integer> temporal = new ArrayList<Integer>(2);

				System.out.println(randomN1 + "++" + (randomN1 - reduce));

				temporal.add(randomN1);
				temporal.add(randomN1 - reduce);

				zona += 11;

				randomN1 = temp.nextInt(5) + (25 + zona);

				serpientes.add(temporal);
			} else {
				randomN1 = temp.nextInt(5) + (25 + zona);
			}

		}

	}

	private boolean isContained(Integer in, ArrayList<ArrayList<Integer>> array) {
		for (ArrayList<Integer> lista : array) {
			for (Integer integ : lista) {
				if (integ == in)
					return true;
			}
		}
		return false;
	}

	public ArrayList<ArrayList<Integer>> getEscaleras() {
		return escaleras;
	}

	public ArrayList<ArrayList<Integer>> getSerpientes() {
		return serpientes;
	}

}
