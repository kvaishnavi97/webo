package collection;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SetOperation {
	public static void main(String[] args) {
		
		LinkedList<Set<Integer>> list=new LinkedList<>();
		Set<Integer> s1 = Set.of(1,2,3);
		Set<Integer> s2 = Set.of(1,4,5);
		Set<Integer> s3 = Set.of(1,2);
		Set<Integer> s4 = Set.of(1,9);
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		int n=4; //(No of sets present)
		System.out.println("Print Arraylist: "+list);
		
		Set<Integer> result=unionOperation(s1,s2);
		for(int i=2;i<n;i++) {
			  result=unionOperation(result,list.get(i));
			
		}
		
		System.out.println("Union operation Result: "+result);
		
		
		Set<Integer> resultone=intersectionOperation(s1,s2);
		for(int i=2;i<n;i++) {
			  resultone=intersectionOperation(resultone,list.get(i));		
		}
		
		System.out.println("Intersection operation Result: "+resultone);
		
		
		
		

	}
	
	public static Set<Integer> unionOperation(Set<Integer> s1, Set<Integer> s2){
		Set<Integer> union = Stream.concat(s1.stream(),s2.stream()).collect(Collectors.toSet());
		
		return union;
	}
	
	public static Set<Integer> intersectionOperation(Set<Integer> s1, Set<Integer> s2){
		
		Set<Integer> intersect = s1.stream().filter(s2::contains).collect(Collectors.toSet());
		return intersect;
		
	}

}
