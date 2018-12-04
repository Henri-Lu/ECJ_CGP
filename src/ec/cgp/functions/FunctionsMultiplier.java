package ec.cgp.functions;


/**
 * 
 * Function set for the even-n-parity problem.
 * 
 * @author David Oranchak, doranchak@gmail.com, http://oranchak.com
 * 
 */
public class FunctionsMultiplier implements Functions {

	/** logical and */
	static int F_AND = 0;
	/** logical or */
	static int F_OR = 1;
	
	/** logical or */
	static int F_AND_INV = 2;

	/** logical or */
	static int F_XOR = 3;

	/** Interpret the given function and apply it to the given inputs. */
	public Object callFunction(Object[] inputs, int function, int numFunctions) {
		if (function == F_AND) {
			return ((long)inputs[0] & (long)inputs[1]);
		} else if (function == F_OR) {
			return (long)inputs[0] | (long)inputs[1];
		} else if (function == F_AND_INV) {
			long result = ( (~(long)inputs[0])&0xFF | (long)inputs[1]);
			return result;
		} else if (function == F_XOR) {
			long result = ((long)inputs[0] ^ (long)inputs[1]);
			return result;
		} else
			throw new IllegalArgumentException("Function #" + function
					+ " is unknown.");
	}
	

	/**
	 * Return a function name, suitable for display in expressions, for the
	 * given function.
	 */
	public String functionName(int fn) {
		if (fn == F_AND)
			return "and";
		if (fn == F_OR)
			return "or";
		if (fn == F_XOR)
			return "xor";
		if (fn == F_AND_INV)
			return "and_inv";
		else
			return "UNKNOWN FUNCTION";
	}

	/** Return the name, suitable for display, for the given input. */
	public String inputName(int inp, Object val) {
		if (inp < 8)
			return "x" + inp; 	// dependent variable. TODO: "8" is hardcoded;
								// it needs to be passed in depending on which
								// parity problem we're running.
		
		return "" + val; 		// a constant value
	}

	/** Return the arity of the given function */
	public int arityOf(int fn) {
		return 2;
	}


}

