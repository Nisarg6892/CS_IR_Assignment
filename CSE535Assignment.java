package project_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class ValueComparator3 implements Comparator<String> {

	Map<String, Integer> map;

	public ValueComparator3(Map<String, Integer> base) {
		this.map = base;
	}

	public int compare(String a, String b) {
		if (map.get(a) >= map.get(b)) {
			return -1;
		} else {
			return 1;
		} // returning 0 would merge keys
	}
}

class Sort implements Comparator<Integer> {

	@Override
	public int compare(Integer e1, Integer e2) {
		if (e1 > e2) {
			return 1;
		} else {
			return -1;
		}
	}
}

public class CSE535Assignment {

	ArrayList<String> a = new ArrayList<String>();
	LinkedList<Integer> l1;
	LinkedList<Integer> l2;

	HashMap<String, LinkedList<Integer>> hashMap1 = new HashMap<String, LinkedList<Integer>>();

	HashMap<String, Integer> dt = new HashMap<String, Integer>();
	TreeMap<String, Integer> sortedMap;
	HashMap<String, LinkedList<Integer>> hashMap2 = new HashMap<String, LinkedList<Integer>>();

	HashMap<String, Integer> d = new HashMap<String, Integer>();
	TreeMap<String, Integer> sortedMapForTopK;

	String split_data[];
	String split_data_2[];
	String split_data_3[];
	String split_data_4[];
	String temp_Split_data[];

	LinkedList<Integer> l3;
	LinkedList<Integer> l4;

	HashMap<String, Integer> TAAT_And = new HashMap<String, Integer>();
	HashMap<String, Integer> TAAT_Or = new HashMap<String, Integer>();
	TreeMap<String, Integer> sortedMapForTAAT_And;
	TreeMap<String, Integer> sortedMapForTAAT_Or;
	ArrayList<String> Terms_TAAT_And = new ArrayList<String>();
	ArrayList<String> sortedTerms_TAAT_And = new ArrayList<String>();
	ArrayList<String> sortedTerms_TAAT_Or = new ArrayList<String>();

	LinkedList<Integer> result = new LinkedList<Integer>();
	LinkedList<Integer> result_sorted = new LinkedList<Integer>();
	LinkedList<Integer> result_sorted_Or = new LinkedList<Integer>();
	LinkedList<Integer> answer = new LinkedList<Integer>();
	LinkedList<Integer> answer_sorted = new LinkedList<Integer>();
	LinkedList<Integer> answer_union_TAAT = new LinkedList<Integer>();
	LinkedList<Integer> answer_DAAT_And = new LinkedList<Integer>();
	LinkedList<Integer> answer_DAAT_Or = new LinkedList<Integer>();

	int count_TAAT_And;
	int count_TAAT_And_sorted;
	int count_TAAT_Or_sorted;
	int count_TAAT_Or;
	int count_DAAT_And;
	int count_DAAT_Or;

	ArrayList<String> Terms_TAAT_Or;
	LinkedList<Integer> result_TAAT_Or;

	ArrayList<String> Terms_DAAT_And = new ArrayList<String>();
	LinkedList<Integer> result_DAAT_And = new LinkedList<Integer>();

	ArrayList<String> Terms_DAAT_Or = new ArrayList<String>();
	LinkedList<Integer> result_DAAT_Or = new LinkedList<Integer>();

	// ArrayList<Integer> first_TAAT = new ArrayList<Integer>();
	// ArrayList<Integer> second_TAAT = new ArrayList<Integer>();

	// LinkedList<Integer> l5;
	// LinkedList<Integer> l6;

	static PrintWriter writer;
	static int index_not_found;

	ArrayList<LinkedList<Integer>> groupOfLinkedList;
	ArrayList<Integer> sizeOfLinkedList;
	ArrayList<Integer> pointers;
	ArrayList<Integer> pointersForMinimum;
	LinkedList<Integer> DAAT_And_answer;

	ArrayList<LinkedList<Integer>> groupOfLinkedList_Or;
	ArrayList<Integer> sizeOfLinkedList_Or;
	ArrayList<Integer> pointers_Or;
	ArrayList<Integer> pointersForMinimum_Or;
	LinkedList<Integer> answer_DAAT_Or2;

	public void readFile1(File f) throws IOException {

		FileInputStream fis = new FileInputStream(f);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line = null;

		while ((line = br.readLine()) != null) {

			index_not_found = 0;

			count_TAAT_And = 0;
			count_TAAT_And_sorted = 0;
			count_TAAT_Or = 0;
			count_DAAT_And = 0;
			count_DAAT_Or = 0;

			a.clear();
			l1 = new LinkedList<Integer>();
			dt.clear();
			l2 = new LinkedList<Integer>();

			// Declarations
			split_data = new String[3];
			split_data_2 = new String[3];
			split_data_3 = new String[3];
			split_data_4 = new String[2];
			temp_Split_data = new String[2];

			line.replace("\\", "\\\\");
			temp_Split_data = line.split("\\\\m");

			split_data = temp_Split_data[0].split("\\\\c");

			for (int i = 0; i < split_data_2.length - 1; ++i) {
				split_data_2[i] = split_data[i];
			}
			split_data_2[2] = temp_Split_data[1];

			split_data_2[2] = split_data_2[2].substring(1, split_data_2[2].length() - 1);
			split_data_3 = split_data_2[2].split(", ");

			for (int i = 0; i < split_data_3.length; ++i) {
				split_data_4 = split_data_3[i].split("/");
				dt.put(split_data_4[0], Integer.parseInt(split_data_4[1]));
				l1.add(Integer.parseInt(split_data_4[0]));
			}

			d.put(split_data_2[0], Integer.parseInt(split_data_2[1]));

			Collections.sort(l1);

			hashMap1.put(split_data[0], l1);

			sortedMap = SortByValue(dt);

			for (String key : sortedMap.keySet()) {
				l2.add(Integer.parseInt(key));
			}

			hashMap2.put(split_data[0], l2);

		}

		sortedMapForTopK = SortByValue(d);
		System.out.println(sortedMapForTopK);

		br.close();

	}

	public void getTopK(int K) throws IOException {
		int count = 0;
		ArrayList<String> terms = new ArrayList<String>();

		writer.println();
		writer.println("FUNCTION: getTopK " + K);
		writer.print("Result: ");

		for (String key : sortedMapForTopK.keySet()) {
			if (count < K) {
				// System.out.println(key);

				terms.add(key);
				++count;
			} else {
				break;
			}
		}
		for (int i = 0; i < terms.size(); ++i) {
			if (i == terms.size() - 1) {
				writer.print(terms.get(i));
			} else {
				writer.print(terms.get(i) + ", ");
			}
		}
	}

	public void getPostings(String query_term) throws IOException {

		int found = 0;
		int count_1 = 0;
		int count_2 = 0;
		ArrayList<Integer> a1 = new ArrayList<Integer>();
		writer.println();
		writer.println("FUNCTION: getPostings " + query_term);

		for (String key : d.keySet()) {

			if (key.equals(query_term)) {

				for (Integer doc : hashMap1.get(key)) {

					a1.add(doc);
					++count_1;
				}
				found = 1;
				break;
			}
		}

		if (found == 1) {
			writer.print("Ordered by doc IDs: ");
			for (int i = 0; i < a1.size(); ++i) {
				if (i == a1.size() - 1) {
					writer.print(a1.get(i));
				} else {
					writer.print(a1.get(i) + ", ");
				}
			}
			writer.println();
			writer.print("Ordered by TF: ");

			for (String key : d.keySet()) {
				if (key.equals(query_term)) {
					// System.out.println(hashMap2.get(key));
					for (Integer doc : hashMap2.get(key)) {
						if (count_2 == hashMap2.get(key).size() - 1) {
							writer.print(doc);
						} else {
							writer.print(doc + ", ");
						}

						++count_2;
					}
					found = 1;
					break;
				}
			}
		}

		if (found == 0) {
			System.out.println("term not found");
			writer.print("term not found");
			index_not_found = 1;
		}
		// writer.close();
	}

	public void termAtATimeQueryAnd(String... args) {

		long start_TAAT_And = System.currentTimeMillis();
		count_TAAT_And = 0;
		count_TAAT_And_sorted = 0;

		writer.println();
		writer.print("FUNCTION: termAtATimeQueryAnd ");
		LinkedList<Integer> result = new LinkedList<Integer>();
		ArrayList<String> a = new ArrayList<String>();

		int index = 0;
		int index_sorted = 0;
		int empty = 0;
		// // QueryTerms -> NumberOfDocuments
		for (String arg : args) {
			TAAT_And.put(arg, d.get(arg));
			Terms_TAAT_And.add(arg);
			a.add(arg);
		}
		for (int i = 0; i < a.size(); ++i) {
			if (i == a.size() - 1) {
				writer.println(a.get(i));
			} else {
				writer.print(a.get(i) + ", ");
			}
		}
		// Sorted QueryTerms By Number of Documents in Increasing Order
		sortedMapForTAAT_And = SortByValue(TAAT_And);

		// Sorted Query Terms in ArrayList
		for (String key : sortedMapForTAAT_And.keySet()) {
			sortedTerms_TAAT_And.add(key);
		}
		Collections.reverse(sortedTerms_TAAT_And);

		result = hashMap2.get(Terms_TAAT_And.get(index));
		Terms_TAAT_And.remove(index);

		while (Terms_TAAT_And.size() != 0 && result.size() != 0) {
			result = intersect_TAAT(result, hashMap2.get(Terms_TAAT_And.get(index)));
			if (result.isEmpty()) {
				System.out.println("zero term found");
				empty = 1;
				break;
			}
			Terms_TAAT_And.remove(index);
		}

		result_sorted = hashMap2.get(sortedTerms_TAAT_And.get(index_sorted));
		sortedTerms_TAAT_And.remove(index_sorted);
		//
		while (sortedTerms_TAAT_And.size() != 0 && result_sorted.size() != 0) {
			result_sorted = intersect_TAAT_sorted(result_sorted, hashMap2.get(sortedTerms_TAAT_And.get(index_sorted)));
			sortedTerms_TAAT_And.remove(index_sorted);
		}

		long elapsedTime_TAAT_And = System.currentTimeMillis() - start_TAAT_And;
		Collections.sort(result);
		System.out.println(result.size() + " documents are found");
		writer.println(result.size() + " documents are found");
		System.out.println(count_TAAT_And + " comparisons are made");
		writer.println(count_TAAT_And + " comparisions are made");
		System.out.println(elapsedTime_TAAT_And / 1000.0);
		writer.println(elapsedTime_TAAT_And / 1000.0 + " seconds are used");
		System.out.println(count_TAAT_And_sorted + " comparisons are made with optimization (optional bonus part)");
		writer.println(count_TAAT_And_sorted + " comparisons are made with optimization (optional bonus part)");

		if (empty == 0) {

			System.out.println("Result: ");
			writer.print("Result: ");
			for (int r = 0; r < result.size(); ++r) {
				if (r == result.size() - 1) {
					writer.println(result.get(r));
				} else {
					writer.print(result.get(r) + ", ");
				}
			}
			System.out.println(result);
		}
	}

	public void termAtATimeQueryOr(String... args) {

		long start_TAAT_Or = System.currentTimeMillis();

		int index_TAAT_Or = 0;
		int empty_TAAT_Or = 0;
		int index_sorted_Or = 0;
		count_TAAT_Or = 0;
		count_TAAT_Or_sorted = 0;

		TAAT_Or = new HashMap<String, Integer>();
		Terms_TAAT_Or = new ArrayList<String>();
		sortedTerms_TAAT_Or = new ArrayList<String>();
		result_TAAT_Or = new LinkedList<Integer>();

		// writer.println();
		writer.print("FUNCTION: termAtATimeQueryOr ");
		ArrayList<String> a = new ArrayList<String>();

		for (String arg : args) {
			TAAT_Or.put(arg, d.get(arg));
			Terms_TAAT_Or.add(arg);
			a.add(arg);
		}

		for (int i = 0; i < a.size(); ++i) {
			if (i == a.size() - 1) {
				writer.println(a.get(i));
			} else {
				writer.print(a.get(i) + ", ");
			}
		}

		sortedMapForTAAT_Or = SortByValue(TAAT_Or);

		for (String key : sortedMapForTAAT_Or.keySet()) {
			sortedTerms_TAAT_Or.add(key);
		}
		Collections.reverse(sortedTerms_TAAT_Or);

		result_TAAT_Or = hashMap2.get(Terms_TAAT_Or.get(index_TAAT_Or));
		Terms_TAAT_Or.remove(index_TAAT_Or);

		while (Terms_TAAT_Or.size() != 0 && result_TAAT_Or.size() != 0) {
			result_TAAT_Or = union_TAAT(result_TAAT_Or, hashMap2.get(Terms_TAAT_Or.get(index_TAAT_Or)));
			if (result_TAAT_Or.isEmpty()) {
				System.out.println("zero term found");
				empty_TAAT_Or = 1;
				break;
			}
			Terms_TAAT_Or.remove(index_TAAT_Or);
		}

		result_sorted_Or = hashMap2.get(sortedTerms_TAAT_Or.get(index_sorted_Or));
		sortedTerms_TAAT_Or.remove(index_sorted_Or);
		//
		while (sortedTerms_TAAT_Or.size() != 0 && result_sorted_Or.size() != 0) {
			result_sorted_Or = union_TAAT_sorted(result_sorted_Or,
					hashMap2.get(sortedTerms_TAAT_Or.get(index_sorted_Or)));
			sortedTerms_TAAT_Or.remove(index_sorted_Or);
		}

		long elapsedTime_TAAT_Or = System.currentTimeMillis() - start_TAAT_Or;
		Collections.sort(result_TAAT_Or);
		System.out.println(result_TAAT_Or.size() + " documents are found");
		writer.println(result_TAAT_Or.size() + " documents are found");
		System.out.println(count_TAAT_Or + " comparisons are made");
		writer.println(count_TAAT_Or + " comparisions are made");
		writer.println(elapsedTime_TAAT_Or / 1000.0 + " seconds are used");
		System.out.println(count_TAAT_Or_sorted + " comparisons are made with optimization");
		writer.println(count_TAAT_Or_sorted + " comparisons are made with optimization (optional bonus part)");

		if (empty_TAAT_Or == 0) {

			System.out.println("Result: ");
			writer.print("Result: ");
			for (int r = 0; r < result_TAAT_Or.size(); ++r) {
				if (r == result_TAAT_Or.size() - 1) {
					writer.println(result_TAAT_Or.get(r));
				} else {
					writer.print(result_TAAT_Or.get(r) + ", ");
				}
			}
			System.out.println(result_TAAT_Or);
		}
	}

	public LinkedList<Integer> intersect_TAAT(LinkedList<Integer> ll3, LinkedList<Integer> ll4) {
		answer = new LinkedList<Integer>();
		for (int i = 0; i < ll3.size(); ++i) {
			for (int j = 0; j < ll4.size(); ++j) {
				count_TAAT_And = count_TAAT_And + 1;
				if (ll3.get(i).equals(ll4.get(j))) {
					answer.add(ll3.get(i));
				}
			}
		}
		return answer;
	}

	public LinkedList<Integer> intersect_TAAT_sorted(LinkedList<Integer> ll5, LinkedList<Integer> ll6) {
		answer_sorted = new LinkedList<Integer>();
		for (int i = 0; i < ll5.size(); ++i) {
			for (int j = 0; j < ll6.size(); ++j) {
				count_TAAT_And_sorted = count_TAAT_And_sorted + 1;
				if (ll5.get(i).equals(ll6.get(j))) {
					answer_sorted.add(ll5.get(i));
				}
			}
		}
		return answer_sorted;
	}

	public LinkedList<Integer> union_TAAT_sorted(LinkedList<Integer> ll5, LinkedList<Integer> ll6) {
		answer_sorted = new LinkedList<Integer>();
		for (int i = 0; i < ll5.size(); ++i) {
			for (int j = 0; j < ll6.size(); ++j) {
				count_TAAT_Or_sorted = count_TAAT_Or_sorted + 1;
				if (ll5.get(i).equals(ll6.get(j))) {
					answer_sorted.add(ll5.get(i));
					add_sort(ll5.get(i), answer_sorted);
				} else {
					add_sort(ll5.get(i), answer_sorted);
					add_sort(ll6.get(j), answer_sorted);
				}
			}
		}
		return answer_sorted;
	}

	public LinkedList<Integer> union_TAAT(LinkedList<Integer> ll3, LinkedList<Integer> ll4) {

		for (int i = 0; i < ll3.size(); ++i) {
			for (int j = 0; j < ll4.size(); ++j) {
				if (ll3.get(i).equals(ll4.get(j))) {
					count_TAAT_Or += 1;
					add(ll3.get(i), answer_union_TAAT);
				} else {
					count_TAAT_Or += 1;
					add(ll3.get(i), answer_union_TAAT);
					add(ll4.get(j), answer_union_TAAT);
				}
			}
		}

		Collections.sort(answer_union_TAAT, new Sort());
		// System.out.println(answer_union_TAAT);
		return answer_union_TAAT;
	}

	public void add(int i, LinkedList<Integer> answer_union_TAAT) {
		int dupli = 0;
		for (int element : answer_union_TAAT) {
			if (element == i) {
				count_TAAT_Or += 1;
				dupli = 1;
				break;
			}
		}
		if (dupli == 0) {
			answer_union_TAAT.add(i);
		}
	}

	public void add_sort(int i, LinkedList<Integer> answer_sorted) {
		int dupli = 0;
		for (int element : answer_sorted) {
			if (element == i) {
				count_TAAT_Or_sorted += 1;
				dupli = 1;
				break;
			}
		}
		if (dupli == 0) {
			answer_sorted.add(i);
		}
	}

	public void add_DAAT(int i, LinkedList<Integer> answer_DAAT_Or) {
		int duplii = 0;
		for (int element : answer_DAAT_Or) {
			if (element == i) {
				count_DAAT_Or += 1;
				duplii = 1;
				break;
			}
		}
		if (duplii == 0) {
			answer_DAAT_Or.add(i);
		}
	}

	public void documentAtATimeQueryAnd(String... args) {

		long start_DAAT_And = System.currentTimeMillis();

		int index_DAAT_And = 0;
		int empty_DAAT_And = 0;
		count_DAAT_And = 0;
		int timeToTerminate = 0;
		groupOfLinkedList = new ArrayList<LinkedList<Integer>>();
		sizeOfLinkedList = new ArrayList<Integer>();
		pointers = new ArrayList<Integer>();
		pointersForMinimum = new ArrayList<Integer>();
		DAAT_And_answer = new LinkedList<Integer>();
		Terms_DAAT_And = new ArrayList<String>();

		// writer.println();
		writer.print("FUNCTION: documentAtATimeQueryAnd ");
		ArrayList<String> a = new ArrayList<String>();

		for (String arg : args) {
			Terms_DAAT_And.add(arg);
			a.add(arg);
		}

		for (int i = 0; i < a.size(); ++i) {
			if (i == a.size() - 1) {
				writer.println(a.get(i));
			} else {
				writer.print(a.get(i) + ", ");
			}
		}

		for (int i = 0; i < Terms_DAAT_And.size(); ++i) {
			groupOfLinkedList.add(hashMap1.get(Terms_DAAT_And.get(i)));
		}

		pointers.clear();

		for (int i = 0; i < groupOfLinkedList.size(); ++i) {
			sizeOfLinkedList.add(groupOfLinkedList.get(i).size());
			pointers.add(0);
		}

		while (true) {

			// Termination Logic
			for (int p = 0; p < pointers.size(); ++p) {
				int g = pointers.get(p);
				int h = sizeOfLinkedList.get(p);
				if (g == h) {
					++count_DAAT_And;
					timeToTerminate = 1;
					break;
				} else {
					++count_DAAT_And;
				}
			}
			if (timeToTerminate == 1) {
				break;
			}

			// Equal Numbers Check Logic
			int allEqual = 1;
			for (int i = 0; i < groupOfLinkedList.size() - 1; ++i) {
				int first;
				int second;
				// if (pointers.get(i) == groupOfLinkedList.get(i).size() - 3) {
				// System.out.println("Testing");
				// }
				first = groupOfLinkedList.get(i).get(pointers.get(i));
				second = groupOfLinkedList.get(i + 1).get(pointers.get(i + 1));
				if (first != second) {
					++count_DAAT_And;
					System.out.println("&&");
					allEqual = 0;
					break;
				} else {
					++count_DAAT_And;
				}
			}

			// If Intersected Element found
			if (allEqual == 1) {
				System.out.println("in***");
				// Add Intersected Element to Answer
				DAAT_And_answer.add(groupOfLinkedList.get(0).get(pointers.get(0)));

				// Increase all the pointers by 1
				for (int p = 0; p < pointers.size(); ++p) {
					int newPointerValue = pointers.get(p) + 1;
					pointers.set(p, newPointerValue);
				}

				System.out.println("pointers");
				System.out.println(pointers);
			}

			// If Different Value Elements found
			else {
				System.out.println("hi");
				// Minimum Elements(One or Probably more than One) Index Logic
				int minimum = groupOfLinkedList.get(0).get(pointers.get(0));
				pointersForMinimum.clear();
				System.out.println(minimum + "min");
				pointersForMinimum.add(0);
				for (int i = 1; i < groupOfLinkedList.size(); ++i) {
					if (groupOfLinkedList.get(i).get(pointers.get(i)) == minimum) {
						++count_DAAT_And;
						pointersForMinimum.add(i);
					} else {
						++count_DAAT_And;
					}

					if (groupOfLinkedList.get(i).get(pointers.get(i)) < minimum) {
						++count_DAAT_And;
						System.out.println(groupOfLinkedList.get(i).get(pointers.get(i)));
						minimum = groupOfLinkedList.get(i).get(pointers.get(i));
						pointersForMinimum.clear();
						pointersForMinimum.add(i);
					} else {
						++count_DAAT_And;
					}

				}
				System.out.println("pointersForMinimum");
				System.out.println(pointersForMinimum);
				// Increase Pointers for all the Minimum Elements
				for (int i = 0; i < pointersForMinimum.size(); ++i) {
					int newPointerValue = pointers.get(pointersForMinimum.get(i)) + 1;
					pointers.set(pointersForMinimum.get(i), newPointerValue);
				}

				System.out.println("pointers");
				System.out.println(pointers);
			}

		}

		long elapsedTime_DAAT_And = System.currentTimeMillis() - start_DAAT_And;

		// System.out.println(result_DAAT_And.size()+" documents are found");
		writer.println(DAAT_And_answer.size() + " documents are found");
		// System.out.println(count_DAAT_And+" comparisons are made");
		writer.println(count_DAAT_And + " comparisions are made");
		writer.println(elapsedTime_DAAT_And / 1000.0 + " seconds are used");
		// System.out.println("Result: ");
		writer.print("Result: ");
		//
		if (empty_DAAT_And == 0) {
			//
			for (int r = 0; r < DAAT_And_answer.size(); ++r) {
				if (r == DAAT_And_answer.size() - 1) {
					writer.println(DAAT_And_answer.get(r));
				} else {
					writer.print(DAAT_And_answer.get(r) + ", ");
				}
			}
			// System.out.println(result_DAAT_And);
		}
		System.out.println("here:");
		System.out.println(DAAT_And_answer);
		System.out.println(count_DAAT_And);
	}

	public void documentAtATimeQueryOr(String... args) {

		long start_DAAT_Or = System.currentTimeMillis();

		int index_DAAT_Or = 0;
		int empty_DAAT_Or = 0;
		count_DAAT_Or = 0;
		int timeToTerminate_Or = 0;
		groupOfLinkedList_Or = new ArrayList<LinkedList<Integer>>();
		sizeOfLinkedList_Or = new ArrayList<Integer>();
		pointers_Or = new ArrayList<Integer>();
		pointersForMinimum_Or = new ArrayList<Integer>();
		answer_DAAT_Or2 = new LinkedList<Integer>();
		Terms_TAAT_Or = new ArrayList<String>();

		// System.out.println("Terms_TAAT_Or");
		// System.out.println(Terms_TAAT_Or);
		// System.out.println("groupOfLinkedList_Or");
		// System.out.println(groupOfLinkedList_Or);
		// System.out.println(answer_DAAT_Or2);
		// System.out.println(pointersForMinimum_Or);
		// writer.println();
		writer.print("FUNCTION: documentAtATimeQueryOr ");
		ArrayList<String> a = new ArrayList<String>();

		// System.out.println(a);
		for (String arg : args) {
			Terms_DAAT_Or.add(arg);
			a.add(arg);
		}

		for (int i = 0; i < a.size(); ++i) {
			if (i == a.size() - 1) {
				writer.println(a.get(i));
			} else {
				writer.print(a.get(i) + ", ");
			}
		}

		for (int i = 0; i < Terms_DAAT_Or.size(); ++i) {
			groupOfLinkedList_Or.add(hashMap1.get(Terms_DAAT_Or.get(i)));
		}

		pointers_Or.clear();

		for (int i = 0; i < groupOfLinkedList_Or.size(); ++i) {
			sizeOfLinkedList_Or.add(groupOfLinkedList_Or.get(i).size());
			pointers_Or.add(0);
		}

		while (true) {

			// Termination Logic
			for (int p = 0; p < pointers_Or.size(); ++p) {
				++count_DAAT_Or;
				int g = pointers_Or.get(p);
				int h = sizeOfLinkedList_Or.get(p);
				if (g == h) {
					timeToTerminate_Or = 1;
					break;
				}
			}
			if (timeToTerminate_Or == 1) {
				break;
			}

			// Equal Numbers Check Logic
			int allEqual_Or = 1;
			for (int i = 0; i < groupOfLinkedList_Or.size() - 1; ++i) {
				int a_Or = groupOfLinkedList_Or.get(i).get(pointers_Or.get(i));
				int b_Or = groupOfLinkedList_Or.get(i + 1).get(pointers_Or.get(i + 1));
				if (a_Or != b_Or) {
					++count_DAAT_Or;
					System.out.println("&&");
					allEqual_Or = 0;
					break;
				} else {
					++count_DAAT_Or;
				}
			}

			// If Intersected Element found
			if (allEqual_Or == 1) {
				System.out.println("in***");
				// Add Intersected Element to Answer
				int numberAvailable = 0;
				for (int i = 0; i < answer_DAAT_Or2.size(); ++i) {
					if (groupOfLinkedList_Or.get(0).get(pointers_Or.get(0)) == answer_DAAT_Or2.get(i)) {
						numberAvailable = 1;
						break;
					}
				}
				if (numberAvailable == 0) {
					answer_DAAT_Or2.add(groupOfLinkedList_Or.get(0).get(pointers_Or.get(0)));
				}
				// Increase all the pointers by 1
				for (int p = 0; p < pointers_Or.size(); ++p) {
					int newPointerValue = pointers_Or.get(p) + 1;
					pointers_Or.set(p, newPointerValue);
				}
				System.out.println("pointers");
				System.out.println(pointers_Or);
			}

			// If Different Value Elements found
			else {
				// Minimum Elements(One or Probably more than One) Index Logic
				int minimum = groupOfLinkedList_Or.get(0).get(pointers_Or.get(0));
				pointersForMinimum_Or.clear();
				pointersForMinimum_Or.add(0);

				for (int i = 1; i < groupOfLinkedList_Or.size(); ++i) {
					if (groupOfLinkedList_Or.get(i).get(pointers_Or.get(i)) == minimum) {
						++count_DAAT_Or;
						pointersForMinimum_Or.add(i);
					} else {
						++count_DAAT_Or;
					}

					if (groupOfLinkedList_Or.get(i).get(pointers_Or.get(i)) < minimum) {
						++count_DAAT_Or;
						System.out.println(groupOfLinkedList_Or.get(i).get(pointers_Or.get(i)));
						minimum = groupOfLinkedList_Or.get(i).get(pointers_Or.get(i));

						pointersForMinimum_Or.clear();
						pointersForMinimum_Or.add(i);
					} else {
						++count_DAAT_Or;
					}

				}
				answer_DAAT_Or2.add(minimum);
				System.out.println("pointersForMinimum");
				System.out.println(pointersForMinimum_Or);
				// Increase Pointers for all the Minimum Elements
				for (int i = 0; i < pointersForMinimum_Or.size(); ++i) {
					int newPointerValue = pointers_Or.get(pointersForMinimum_Or.get(i)) + 1;
					pointers_Or.set(pointersForMinimum_Or.get(i), newPointerValue);
				}

				System.out.println("pointers");
				System.out.println(pointers_Or);
			}

		}
		System.out.println(answer_DAAT_Or2);

		for (int i = 0; i < groupOfLinkedList_Or.size(); ++i) {
			int alreadyPrinted_Or = 0;
			while (pointers_Or.get(i) < sizeOfLinkedList_Or.get(i)) {
				for (int j = 0; j < answer_DAAT_Or2.size(); ++j) {
					++count_DAAT_Or;
					int p = groupOfLinkedList_Or.get(i).get(pointers_Or.get(i));
					int q = answer_DAAT_Or2.get(j);
					if (p == q) {
						alreadyPrinted_Or = 1;
						break;
					}
				}
				if (alreadyPrinted_Or == 0) {
					answer_DAAT_Or2.add(groupOfLinkedList_Or.get(i).get(pointers_Or.get(i)));
				}
				int newPointerValue = pointers_Or.get(i) + 1;
				System.out.println(newPointerValue);
				pointers_Or.set(i, newPointerValue);
			}
		}

		System.out.println("------");
		Collections.sort(answer_DAAT_Or2);

		long elapsedTime_DAAT_Or = System.currentTimeMillis() - start_DAAT_Or;

		System.out.println(answer_DAAT_Or2.size() + " documents are found");
		writer.println(answer_DAAT_Or2.size() + " documents are found");
		System.out.println(count_DAAT_Or + " comparisons are made");
		writer.println(count_DAAT_Or + " comparisions are made");
		writer.println(elapsedTime_DAAT_Or / 1000.0 + " seconds are used");
		System.out.println("Result: ");
		writer.print("Result: ");

		if (empty_DAAT_Or == 0) {
			for (int r = 0; r < answer_DAAT_Or2.size(); ++r) {
				if (r == answer_DAAT_Or2.size() - 1) {
					writer.print(answer_DAAT_Or2.get(r));
				} else {
					writer.print(answer_DAAT_Or2.get(r) + ", ");
				}
			}
			System.out.println(answer_DAAT_Or2);
		}
	}

	public TreeMap<String, Integer> SortByValue(HashMap<String, Integer> map) {

		ValueComparator3 vc = new ValueComparator3(map);
		TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>(vc);
		sortedMap.putAll(map);
		return sortedMap;
	}

	public static void main(String s[]) throws IOException {

		Scanner s1 = new Scanner(new InputStreamReader(System.in));
		String input = s1.nextLine();
		// String input_array[] = input.split(" ");

		String strFileName = s[0];
		String strOutputFileName = s[1];
		int topKTerms = Integer.parseInt(s[2]);
		String strInputQueryFile = s[3];

		CSE535Assignment ir = new CSE535Assignment();

		File f = new File(strFileName);
		ir.readFile1(f);

		writer = new PrintWriter(strOutputFileName, "UTF-8");

		int K = topKTerms;
		ir.getTopK(K);

		// Write getTopK into output.log

		File f2 = new File(strInputQueryFile);
		FileInputStream fis2 = new FileInputStream(f2);
		BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));

		String line = null;
		while ((line = br2.readLine()) != null) {

			String input_line[] = line.split(" ");
			for (String query_term : input_line) {
				ir.getPostings(query_term);
			}
			if (index_not_found == 1) {
				writer.println();
				writer.println("terms not found");
				writer.println("terms not found");
				writer.println("terms not found");
				writer.println("terms not found");
			}
			ir.termAtATimeQueryAnd(input_line);
			ir.termAtATimeQueryOr(input_line);
			ir.documentAtATimeQueryAnd(input_line);
			ir.documentAtATimeQueryOr(input_line);
		}
		br2.close();
		writer.close();

	}
}
