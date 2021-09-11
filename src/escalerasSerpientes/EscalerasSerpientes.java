package escalerasSerpientes;

import java.util.ArrayList;
import java.util.Random;

public class EscalerasSerpientes {

	private ArrayList<ArrayList<Integer>> escaleras = new ArrayList<ArrayList<Integer>>(),
			serpientes = new ArrayList<ArrayList<Integer>>();

	public EscalerasSerpientes() {
		// TODO Auto-generated constructor stub
		initEscaleras();
		initSerpientes();
	}

	private void initEscaleras() {
		// TODO Auto-generated method stub
		Random temp = new Random();

		int randomN1 = temp.nextInt(5) + 25;
		int aumenta = 18;

		int zona = 0;

		while (!(escaleras.size() == 5)) {

			if (!isContained(randomN1, serpientes, escaleras) && !isContained(randomN1 + 15, serpientes, escaleras)) {
				ArrayList<Integer> temporal = new ArrayList<Integer>(2);

				System.out.println(randomN1 + "==" + (randomN1 + aumenta));
				
				temporal.add(randomN1);
				temporal.add(randomN1 + aumenta);

				zona += 11;

//			aumenta = temp.nextInt(5) + 8;

				escaleras.add(temporal);
//				temporal.clear();
			}
			randomN1 = temp.nextInt(3) + 25 + zona;
		}
	}

	private void initSerpientes() {
		// TODO Auto-generated method stub
		Random temp = new Random();

		int randomN1 = temp.nextInt(5) + 25;
		int reduce = 18;

		int zona = 0;

		while (!(serpientes.size() == 5)) {

			if (!isContained(randomN1, serpientes, escaleras) && !isContained(randomN1 - 18, serpientes, escaleras)) {
				ArrayList<Integer> temporal = new ArrayList<Integer>(2);

//				System.out.println(randomN1 + "++" + (randomN1 - reduce));

				temporal.add(randomN1);
				temporal.add(randomN1 - reduce);

				zona += 11;

				serpientes.add(temporal);
			}
			randomN1 = temp.nextInt(5) + (25 + zona);

		}

	}

	private boolean isContained(Integer in, ArrayList<ArrayList<Integer>> array, ArrayList<ArrayList<Integer>> array2) {

		for (ArrayList<Integer> lista : array) {
			for (Integer integ : lista) {
				if (integ == in)
					return true;
			}
		}

		for (ArrayList<Integer> lista : array2) {
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
