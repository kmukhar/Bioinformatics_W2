import java.util.ArrayList;
import java.util.Collections;

public class Convolutioner {
	public ArrayList<Integer> convolute(ArrayList<Integer> input) {
		Collections.sort(input);
		ArrayList<Integer> result = new ArrayList<>();

		for (int i = 1; i < input.size(); i++)
			for (int j = 0; j < i; j++)
				result.add(input.get(i) - input.get(j));

		Integer zero = new Integer(0);
		while (result.remove(zero))
			;
		return result;
	}
}
