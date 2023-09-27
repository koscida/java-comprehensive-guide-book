public class NinetyNineBottlesOfBeer {
	public static void main( String[] args ) {
		for ( int i = 99; i > 1; i-- ) {
			System.out.print( i );
			System.out.print( " bottles of beer on the wall, " );
			System.out.print( i );
			System.out.println( " bottles of beer. Take one down, pass it around," );
		}
		System.out.println( "1 last bottle of beer on the wall, 1 last bottle of beer."
		);
	}
}