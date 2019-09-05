package chap03;
//제네릭 클래스의 예
class GenericClassTester {
	static class GenericClass<T>{
		private T xyz;
		GenericClass(T t){
			this.xyz = t;
		}
		T getXyz() {
			return xyz;
		}
	}
	//문자열, 숫자 둘 다 가능
	public static void main(String[] args) {
		GenericClass<String> s = new GenericClass<String>("ABC");
		GenericClass<Integer> n = new GenericClass<Integer>(15);
		
		System.out.println(s.getXyz());
		System.out.println(n.getXyz());
		
	}//main
}//class
